package com.whut.teaching.controller;

import com.whut.teaching.dao.CourseDAO;
import com.whut.teaching.dao.RollCallDAO;
import com.whut.teaching.dao.TeacherDAO;
import com.whut.teaching.dto.TeacherDTO;
import com.whut.teaching.model.Course;
import com.whut.teaching.model.RollCall;
import com.whut.teaching.service.CourseService;
import com.whut.teaching.util.MyUtil;
import com.whut.teaching.vo.Empty;
import com.whut.teaching.vo.VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by wpc on 2017/5/21.
 */
@Api(description = "测试")
@RestController
public class TestController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    private RollCallDAO rollCallDAO;

    @ApiOperation("测试")
    @RequestMapping(value = "/test/test")
    public VO test(){

        Course course = new Course(MyUtil.getStringID(),"t001","teacher","description",new Date(),1);

//        courseServicrvice.saveOrUpdate(course);
        TeacherDTO teacherDTO = teacherDAO.findTeacherDTOByTeacherId("111");
        return new VO();

    }


}
