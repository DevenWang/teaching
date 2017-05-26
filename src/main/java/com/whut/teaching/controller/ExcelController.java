package com.whut.teaching.controller;

import com.whut.teaching.dto.CourseRoomDTO;
import com.whut.teaching.dto.ScoreDTO;
import com.whut.teaching.dto.StudentDTO;
import com.whut.teaching.model.Teacher;
import com.whut.teaching.service.CourseRoomService;
import com.whut.teaching.service.QuestionService;
import com.whut.teaching.service.RollCallService;
import com.whut.teaching.util.ExcelUtil;
import com.whut.teaching.util.ImportExcelUtil;
import com.whut.teaching.util.MyUtil;
import com.whut.teaching.vo.Empty;
import com.whut.teaching.vo.VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by wpc on 2017/5/25.
 */
@Api(description = "Excel的导入导出")
@RestController
public class ExcelController {

    @Autowired
    private CourseRoomService courseRoomService;

    @Autowired
    private RollCallService rollCallService;

    @Autowired
    private QuestionService questionService;

    @ApiOperation("上传学生名单")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/excel_upload_students", method = RequestMethod.POST)
    public VO<Empty> uploadStudents(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                    @ApiParam(required = true) @RequestParam("file") MultipartFile file,
                                    @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        String newFileName = MyUtil.getStringID();
        String fileName = ImportExcelUtil.uploadFile(file, newFileName);
        if (fileName == null || fileName.length() <= 0) {
            return new VO<>(4001, "上传失败", new Empty());
        }

        File file1 = new File("studentFile/" + fileName);
        List<List<Object>> result = null;
        try {
            result = ImportExcelUtil.getBankListByExcel(new FileInputStream(file1), fileName);
        } catch (Exception e) {
            System.out.println("读取失败");
        }

        List<String> studentId = new ArrayList<>();
        for (int i = 1; i < result.size(); i++) {
            String temp = result.get(i).get(0).toString();
            if (temp != null && temp.length() > 0) {
                studentId.add(temp);
            }
        }

        courseRoomService.addStudentsToCourse(courseId, studentId);

        //删除名单
        if (file1.isFile() && file1.exists()) {
            file1.delete();
        }

        return MyUtil.emptyReturn();
    }

    @ApiOperation("获取平时成绩")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/excel_download_score", method = RequestMethod.GET)
    public void downloadScore(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                              @ApiParam(required = true) @RequestParam("courseId") String courseId,
                              @ApiParam(required = true, value = "点名的分数") @RequestParam("rollCall") double rollCall,
                              @ApiParam(required = true, value = "课堂提问的分数") @RequestParam("question") double question) throws IOException {

        List<CourseRoomDTO> courseRoomDTOS = courseRoomService.courseScores(courseId);
        List<ScoreDTO> scoreDTOS = new ArrayList<>();

        int countRollCall = rollCallService.countRollCall(courseId);
        int countQuestion = questionService.countQuestion(courseId);

        StudentDTO stu;
        for (CourseRoomDTO c : courseRoomDTOS) {
            double r = rollCall * c.getRollCall() / countRollCall;
            double q = question * c.getQuestion() / countQuestion;
            stu = c.getStudentDTO();
            scoreDTOS.add(new ScoreDTO(stu.getStudentId(), stu.getName(), stu.getStudentClass(), stu.getInstitute().getName(), r, q));
        }

        Map<String, String> map = new HashMap<>();
        map.put("title", "成绩单");
        map.put("total", scoreDTOS.size() + "名");
        map.put("date", MyUtil.getDate(new Date()));

        //如果文件路径不存在
        String name = MyUtil.getStringID() + ".xls";
        String file = "scoreFile/" + name;

        File file1 = new File(file);
        if (!file1.getParentFile().exists()) {
            file1.getParentFile().mkdirs();
        }
        try {
            file1.createNewFile();
        } catch (IOException e) {
            System.out.println("创建路径失败");
        }

        try {
            ExcelUtil.getInstance().exportObj2ExcelByTemplate(map, "score-template.xls", new FileOutputStream(file),
                    scoreDTOS, ScoreDTO.class, true);
        } catch (FileNotFoundException e) {
            System.out.println("路径不存在");
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        long fileLength = file1.length();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(name.getBytes("utf-8"), "ISO8859-1"));
        //设置输出长度
        response.setHeader("Content-Length", String.valueOf(fileLength));
        //获取输入流
        bis = new BufferedInputStream(new FileInputStream(file));
        //输出流
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        //关闭流
        bis.close();
        bos.close();

        //删除成绩单
        if (file1.isFile() && file1.exists()) {
            file1.delete();
        }
    }
}
