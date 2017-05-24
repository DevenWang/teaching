package com.whut.teaching.controller;

import com.whut.teaching.dto.*;
import com.whut.teaching.model.*;
import com.whut.teaching.service.*;
import com.whut.teaching.util.JedisUtils;
import com.whut.teaching.util.MyUtil;
import com.whut.teaching.util.RongYunUtil;
import com.whut.teaching.vo.Empty;
import com.whut.teaching.vo.Login;
import com.whut.teaching.vo.VO;
import io.rong.models.CodeSuccessResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Api(description = "学生功能")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRoomService courseRoomService;

    @Autowired
    private RollCallService rollCallService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private HomeworkService homeworkService;

    @ApiOperation("学生注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public VO<Empty> register(@ApiParam(required = true) @RequestParam("id") String id,
                              @ApiParam(required = true) @RequestParam("name") String name,
                              @ApiParam(required = true) @RequestParam("psw") String psw,
                              @ApiParam(required = true) @RequestParam("instituteId") String instituteId,
                              @ApiParam(required = true) @RequestParam("studentClass") String studentClass,
                              @ApiParam(required = true) @RequestParam("phone") String phone) {

        Student student = studentService.findById(id);
        if (student != null) {
            return new VO(409, "此学号已被注册", null);
        }

        student = new Student(id, name, psw, studentClass, instituteId, phone);
        studentService.saveOrUpdate(student);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("学生登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public VO<Login<StudentDTO>> login(@ApiParam(required = true) @RequestParam("id") String id,
                                       @ApiParam(required = true) @RequestParam("pwd") String pwd) {

        Student student = studentService.findById(id);

        if (student == null) {
            return new VO<>(1001, "学号不存在", null);
        }
        if (!student.getPassword().equals(pwd)) {
            return new VO<>(1002, "密码错误", null);
        }

        /*
         * 从融云获取token
         */
        String token = RongYunUtil.getToken(id, student.getName(), student.getPortraitUri());

        JedisUtils.set(token, id);

//        Login<Student> login = new Login<>(token, student);

        StudentDTO studentDTO = studentService.oneStudentDTO(id);

        return new VO<>(new Login<>(token, studentDTO));
    }

    @ApiOperation("注销")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public VO<Empty> logout(@RequestParam("token") String token) {

        JedisUtils.delete(token);
        return MyUtil.emptyReturn();
    }

    @ApiOperation("获取个人信息")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/profile_get", method = RequestMethod.POST)
    public VO<StudentDTO> studentOwnInfo(@ApiIgnore @RequestAttribute("student") Student student) {

//        return new VO<>(student);
        return new VO<>(studentService.oneStudentDTO(student.getStudentId()));
    }

    @ApiOperation("更新个人信息")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/profile_update", method = RequestMethod.POST)
    public VO<Empty> profileUpdate(@ApiIgnore @RequestAttribute("student") Student student,
                                   @ApiParam(required = true) @RequestParam("id") String id,
                                   @ApiParam(required = true) @RequestParam("name") String name,
                                   @ApiParam(required = true) @RequestParam("instituteId") String instituteId,
                                   @ApiParam(required = true) @RequestParam("studentClass") String studentClass,
                                   @ApiParam(required = true) @RequestParam("email") String email,
                                   @ApiParam(required = true) @RequestParam("phone") String phone,
                                   @ApiParam @RequestParam("portraitUri") String portraitUri) {

        student.setStudentId(id);
        student.setName(name);
        student.setInstituteId(instituteId);
        student.setEmail(email);
        student.setPhone(phone);
        student.setStudentClass(studentClass);

        if (portraitUri != null && portraitUri.length() > 0) {
            student.setPortraitUri(portraitUri);
        }

        studentService.saveOrUpdate(student);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("修改密码")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/change_pwd", method = RequestMethod.POST)
    public VO<Empty> changePassword(@ApiIgnore @RequestAttribute("student") Student student,
                                    @ApiParam(required = true) @RequestParam("newPwd") String newPwd) {

        student.setPassword(newPwd);
        studentService.saveOrUpdate(student);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("根据课程名查找课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/find_course_name", method = RequestMethod.POST)
    public VO<List<CourseDTO>> findCourseName(@ApiIgnore @RequestAttribute("student") Student student,
                                              @ApiParam(required = true) @RequestParam("name") String name) {

//        List<Course> courses = courseService.findByName(name);

        return new VO<>(courseService.courseDTOByName(name));
    }

    @ApiOperation("根据老师名查找课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/find_course_teacher_name", method = RequestMethod.POST)
    public VO<List<CourseDTO>> findCourseTeacherName(@ApiIgnore @RequestAttribute("student") Student student,
                                                     @ApiParam(required = true) @RequestParam("name") String name) {

//        List<Course> courses = courseService.findByTeacherName(name);

        return new VO<>(courseService.courseDTOByTeacherName(name));
    }

    @ApiOperation("查找学生选中所有课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/find_course_student_id", method = RequestMethod.POST)
    public VO<List<CourseDTO>> findOwnAllCourse(@ApiIgnore @RequestAttribute("student") Student student,
                                                @ApiParam(required = true) @RequestParam("studentId") String studentId) {

//        List<Course> courses = courseRoomService.findByStudentId(studentId);

        return new VO<>(courseRoomService.studentCourses(studentId));
    }

    @ApiOperation("加入课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/join_course", method = RequestMethod.POST)
    public VO<Empty> joinCourse(@ApiIgnore @RequestAttribute("student") Student student,
                                @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        CourseRoom courseRoom = new CourseRoom(MyUtil.getStringID(), student.getStudentId(), courseId, new Date(), 0, 0);
        courseRoomService.saveOrUpdate(courseRoom);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("查看正在点名")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/all_roll_call", method = RequestMethod.POST)
    public VO<RollCallDTO> allRollCall(@ApiIgnore @RequestAttribute("student") Student student,
                                       @ApiParam(required = true) @RequestParam("courseId") String courseId) {

//        List<RollCall> rollCalls = rollCallService.findAllByCourseId(courseId);

        return new VO<>(rollCallService.oneRollCallByCourseId(courseId));
    }

    @ApiOperation("签到")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/resp_roll_call", method = RequestMethod.POST)
    public VO<Empty> respRollCall(@ApiIgnore @RequestAttribute("student") Student student,
                                  @ApiParam(required = true) @RequestParam("courseId") String inCourseId,
                                  @ApiParam(required = true) @RequestParam("code") String code,
                                  @ApiParam(required = true) @RequestParam("latitude") double latitude,
                                  @ApiParam(required = true) @RequestParam("longitude") double longitude) {

        RollCallDTO rollCallDTO = rollCallService.oneRollCallByCourseId(inCourseId);
        if (rollCallDTO == null) {
            return new VO<>(4003, "已停止签到", new Empty());
        }

        String rollCallId = rollCallDTO.getRollcallId();
        if (rollCallId == null || rollCallId.length() <= 0) {
            return new VO<>(4003, "已停止签到", new Empty());
        }

        RollCall rollCall = rollCallService.findByRollCallId(rollCallId);

        if (rollCall.getStatus() == RollCall.ENDING_ROLLCALL) {
            return new VO<>(4003, "已停止签到", new Empty());
        }

        if (!rollCall.getCode().equals(code)) {
            return new VO<>(4003, "口令错误", new Empty());
        }

        double destince = MyUtil.GetDistance(longitude, latitude, rollCall.getLongitude(), rollCall.getLatitude());
        if (destince > RollCall.DIATANCE) {
            return new VO<>(4003, "距离太远", new Empty());
        }

        ResponseRollcall responseRollcall = new ResponseRollcall(MyUtil.getStringID(), rollCallId, student.getStudentId());
        rollCallService.saveAndUpdate(responseRollcall);

        String courseId = rollCall.getCourseId();
        CourseRoom courseRoom = courseRoomService.findByStuIdAnCoreId(student.getStudentId(), courseId);
        courseRoom.setRollCall(courseRoom.getRollCall() + 1);
        courseRoomService.saveOrUpdate(courseRoom);

        return new VO<>(new Empty());
    }

    @ApiOperation("查看所有问题")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/all_question", method = RequestMethod.POST)
    public VO<List<QuestionDTO>> allQuestion(@ApiIgnore @RequestAttribute("student") Student student) {

//        List<Question> questions = questionService.findAllQuestionByCourseId(courseId);

        return new VO<>(questionService.studentQuestion(student.getStudentId()));
    }

    @ApiOperation("回答问题")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/answer_question", method = RequestMethod.POST)
    public VO<Empty> answerQuestion(@ApiIgnore @RequestAttribute("student") Student student,
                                    @ApiParam(required = true) @RequestParam("questionId") String questionId,
                                    @ApiParam(required = true) @RequestParam("answer") String answer) {

        Question question = questionService.findByQuestionId(questionId);

        if (question.getStatus() == Question.ENDING_QUESTION) {
            return new VO(4003, "问题已关闭", new Empty());
        }

        if (!question.getRealAnswer().equals(answer)) {
            Answer answer1 = new Answer(MyUtil.getStringID(), questionId, student.getStudentId(), answer, new Date());
            questionService.saveAndUpdate(answer1);
            return MyUtil.emptyReturn();
        }

        Answer answer1 = new Answer(MyUtil.getStringID(), questionId, student.getStudentId(), answer, new Date());
        questionService.saveAndUpdate(answer1);

        String courseId = question.getCourseId();
        CourseRoom courseRoom = courseRoomService.findByStuIdAnCoreId(student.getStudentId(), courseId);
        courseRoom.setQuestion(courseRoom.getQuestion() + 1);
        courseRoomService.saveOrUpdate(courseRoom);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("反馈")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public VO<Empty> createFeedBack(@ApiIgnore @RequestAttribute("student") Student student,
                                    @ApiParam(required = true) @RequestParam("courseId") String courseId,
                                    @ApiParam(required = true) @RequestParam("content") String content) {

        FeedBack feedBack = new FeedBack(MyUtil.getStringID(), courseId, student.getStudentId(), content, new Date());
        feedBackService.saveAndUpdate(feedBack);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("查看作业")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public VO<List<HomeworkDTO>> findHomework(@ApiIgnore @RequestAttribute("student") Student student) {

//        List<Homework> homework = homeworkService.findHomework(courseId);

        return new VO<>(homeworkService.studentHomeworks(student.getStudentId()));
    }

    @ApiOperation("私聊老师")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/to_teacher", method = RequestMethod.POST)
    public VO<Empty> toTeacher(@ApiIgnore @RequestAttribute("student") Student student,
                               @ApiParam(required = true) @RequestParam("teacherId") String teacherId,
                               @ApiParam(required = true) @RequestParam("content") String content,
                               @ApiParam(required = true) @RequestParam("objectName") String objectName) {

        /*
         *  融云操作
         */
        CodeSuccessResult codeSuccessResult = RongYunUtil.publishPrivate(student.getStudentId(), teacherId, content);
        if (codeSuccessResult == null || codeSuccessResult.getCode() != 200) {
            return new VO<>(5001, "融云服务器推送失败", new Empty());
        }

        return MyUtil.emptyReturn();
    }

}
