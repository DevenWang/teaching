package com.whut.teaching.controller;

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
 * Created by wpc on 2017/5/19.
 */
@Api(description = "教师功能")
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private RollCallService rollCallService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private FeedBackService feedBackService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private CourseRoomService courseRoomService;

    @ApiOperation("教师注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public VO<Empty> register(@ApiParam(required = true) @RequestParam("id") String id,
                              @ApiParam(required = true) @RequestParam("name") String name,
                              @ApiParam(required = true) @RequestParam("psw") String psw,
                              @ApiParam(required = true) @RequestParam("instituteId") String instituteId,
                              @ApiParam(required = true) @RequestParam("phone") String phone) {

        Teacher teacher = teacherService.findById(id);
        if (teacher != null) {
            return new VO(409, "此账号已被注册", null);
        }

        teacher = new Teacher(id, name, psw, instituteId, phone);
        teacherService.saveOrUpdate(teacher);

        return new VO(new Empty());
    }

    @ApiOperation("教师登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public VO<Login<Teacher>> login(@ApiParam(required = true) @RequestParam("id") String id,
                                    @ApiParam(required = true) @RequestParam("pwd") String pwd) {

        Teacher teacher = teacherService.findById(id);

        if (teacher == null) {
            return new VO<>(1001, "学号不存在", null);
        }
        if (!teacher.getPassword().equals(pwd)) {
            return new VO<>(1002, "密码错误", null);
        }

        /*
         * 从融云获取token
         */
        String token = RongYunUtil.getToken(id, teacher.getName(), teacher.getPortraitUri());

        JedisUtils.set(token, id);

        Login<Teacher> login = new Login<>(token, teacher);

        return new VO<>(login);
    }

    @ApiOperation("注销")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public VO<Empty> logout(@RequestParam("token") String token) {

        JedisUtils.delete(token);
        return new VO<>(new Empty());
    }

    @ApiOperation("获取个人信息")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/profile_get", method = RequestMethod.POST)
    public VO<Teacher> profileUpdate(@ApiIgnore @RequestAttribute("teacher") Teacher teacher) {

        return new VO<>(teacher);
    }

    @ApiOperation("更新个人信息")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/profile_update", method = RequestMethod.POST)
    public VO<Empty> profileUpdate(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                   @ApiParam(required = true) @RequestParam("id") String id,
                                   @ApiParam(required = true) @RequestParam("name") String name,
                                   @ApiParam(required = true) @RequestParam("instituteId") String instituteId,
                                   @ApiParam(required = true) @RequestParam("email") String email,
                                   @ApiParam(required = true) @RequestParam("phone") String phone,
                                   @ApiParam @RequestParam("portraitUri") String portraitUri) {

        teacher.setTeacherId(id);
        teacher.setName(name);
        teacher.setInstituteId(instituteId);
        teacher.setEmail(email);
        teacher.setPhone(phone);
        if (portraitUri != null && portraitUri.length() > 0) {
            teacher.setPortraitUri(portraitUri);
        }

        teacherService.saveOrUpdate(teacher);
        return new VO(new Empty());
    }

    @ApiOperation("修改密码")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/change_pwd", method = RequestMethod.POST)
    public VO<Empty> changePassword(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                    @ApiParam(required = true) @RequestParam("newPwd") String newPwd) {

        teacher.setPassword(newPwd);
        teacherService.saveOrUpdate(teacher);

        return new VO(new Empty());
    }

    @ApiOperation("开设课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/create_course", method = RequestMethod.POST)
    public VO<Empty> createCourse(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                  @ApiParam(required = true) @RequestParam("name") String name,
                                  @ApiParam(required = true) @RequestParam("courseDesc") String courseDesc) {

        Course course = new Course(MyUtil.getStringID(), teacher.getTeacherId(), name, courseDesc, new Date(), Course.STARTING_COURSE);
        courseService.saveOrUpdate(course);

        return new VO(new Empty());
    }

    @ApiOperation("查看所有课程信息")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/course_info", method = RequestMethod.POST)
    public VO<List<Course>> courseInfo(@ApiIgnore @RequestAttribute("teacher") Teacher teacher) {

        List<Course> courses = courseService.findByTeacherId(teacher.getTeacherId());

        return new VO<>(courses);
    }

    @ApiOperation("更新课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/update_course", method = RequestMethod.POST)
    public VO<Empty> updateCourse(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                  @ApiParam(required = true) @RequestParam("courseId") String courseId,
                                  @ApiParam(required = true) @RequestParam("name") String name,
                                  @ApiParam(required = true) @RequestParam("courseDesc") String courseDesc) {

        Course course = courseService.findById(courseId);
        course.setCourseDesc(courseDesc);
        course.setName(name);

        courseService.saveOrUpdate(course);

        return new VO(new Empty());
    }

    @ApiOperation("结束课程")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/end_course", method = RequestMethod.POST)
    public VO<Empty> endCourse(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                               @ApiParam(required = true) @RequestParam("courseId") String id) {

        Course course = courseService.findById(id);
        course.setStatus(Course.ENDING_COURDSE);
        courseService.saveOrUpdate(course);

        return new VO(new Empty());
    }

    @ApiOperation("点名")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/roll_call", method = RequestMethod.POST)
    public VO<Empty> rollCall(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                              @ApiParam(required = true) @RequestParam("courseId") String courseId,
                              @ApiParam(required = true) @RequestParam("code") String code,
                              @ApiParam(required = true) @RequestParam("latitude") double latitude,
                              @ApiParam(required = true) @RequestParam("longitude") double longitude) {

        RollCall rollCall = new RollCall(MyUtil.getStringID(), courseId, code, latitude, longitude, new Date(), RollCall.STARTING_ROLLCALL);
        rollCallService.saveAndUpdate(rollCall);

        List<Student> students = courseRoomService.findByCourseId(courseId);
        /*
            融云操作
         */
        CodeSuccessResult codeSuccessResult = RongYunUtil.PublishSystem(teacher.getTeacherId(), (String[]) students.toArray(), "课堂点名");
        if (codeSuccessResult == null || codeSuccessResult.getCode() != 200) {
            return new VO<>(5001, "融云服务器推送失败", new Empty());
        }

        return new VO<>(new Empty());
    }

    @ApiOperation("结束点名")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/end_roll_call", method = RequestMethod.POST)
    public VO<Empty> endRollCall(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                 @ApiParam(required = true) @RequestParam("courseId") String id) {

        List<RollCall> rollCalls = rollCallService.findByCourseId(id, RollCall.STARTING_ROLLCALL);
        if (rollCalls != null) {
            for (RollCall rollCall : rollCalls) {
                rollCall.setStatus(RollCall.ENDING_ROLLCALL);
                rollCallService.saveAndUpdate(rollCall);
            }
        }

        return MyUtil.emptyReturn();
    }


    @ApiOperation("查看所有点名")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/all_roll_call", method = RequestMethod.POST)
    public VO<List<RollCall>> allRollcall(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                          @ApiParam(required = true) @RequestParam("courseId") String id) {

        List<RollCall> rollCalls = rollCallService.findAllByCourseId(id);

        return new VO<>(rollCalls);
    }

    @ApiOperation("查看迟到学生")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/late_student", method = RequestMethod.POST)
    public VO<List<Student>> lateStudent(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                         @ApiParam(required = true) @RequestParam("rollcallId") String rollcallId) {

        List<Student> students = rollCallService.findLastStudent(rollcallId);

        return new VO<>(students);
    }

    @ApiOperation("补签")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/check_in", method = RequestMethod.POST)
    public VO<Empty> checkIn(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                             @ApiParam(required = true) @RequestParam("rollcallId") String rollcallId,
                             @ApiParam(required = true) @RequestParam("studentId") String studentId) {

        ResponseRollcall responseRollcall = new ResponseRollcall(MyUtil.getStringID(), rollcallId, studentId);
        rollCallService.saveAndUpdate(responseRollcall);

        String courseId = rollCallService.findByRollCallId(rollcallId).getCourseId();
        Score score = scoreService.findByStuIdAnCoreId(studentId, courseId);
        score.setRollcall(score.getRollcall() + 1);
        scoreService.saveAndUpdate(score);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("课堂提问")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public VO<Empty> question(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                              @ApiParam(required = true) @RequestParam("courseId") String courseId,
                              @ApiParam(required = true) @RequestParam("question") String question,
                              @ApiParam(required = true) @RequestParam("realAnswer") String realAnswer) {

        Question question1 = new Question(MyUtil.getStringID(), courseId, question, realAnswer, Question.STARTING_QUESTION, new Date());
        questionService.saveAndUpdate(question1);

        List<Student> students = courseRoomService.findByCourseId(courseId);
        /*
            融云操作
         */
        CodeSuccessResult codeSuccessResult = RongYunUtil.PublishSystem(teacher.getTeacherId(), (String[]) students.toArray(), question);
        if (codeSuccessResult == null || codeSuccessResult.getCode() != 200) {
            return new VO<>(5001, "融云服务器推送失败", new Empty());
        }

        return MyUtil.emptyReturn();
    }

    @ApiOperation("查看所有问题")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/all_question", method = RequestMethod.POST)
    public VO<List<Question>> allQuestion(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                          @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        List<Question> questions = questionService.findAllQuestionByCourseId(courseId);

        return new VO<>(questions);
    }

    @ApiOperation("查看问题结果")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/answer_result", method = RequestMethod.POST)
    public VO<List<Answer>> answerResult(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                         @ApiParam(required = true) @RequestParam("questionId") String questionId) {

        Question question = questionService.findByQuestionId(questionId);
        question.setStatus(Question.ENDING_QUESTION);
        questionService.saveAndUpdate(question);

        List<Answer> answers = questionService.checkAnswer(questionId);

        return new VO<>(answers);
    }

    @ApiOperation("布置课后作业")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public VO<Empty> homework(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                              @ApiParam(required = true) @RequestParam("courseId") String courseId,
                              @ApiParam(required = true) @RequestParam("endTime") String endTime,
                              @ApiParam(required = true) @RequestParam("homework") String homework) {

        Homework homework1 = new Homework(MyUtil.getStringID(), courseId, homework, new Date(), endTime);
        homeworkService.saveAndUpdate(homework1);

        return MyUtil.emptyReturn();
    }

    @ApiOperation("查看课程反馈")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public VO<List<FeedBack>> feedBack(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                       @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        List<FeedBack> feedBacks = feedBackService.findByCourseId(courseId);

        return new VO<>(feedBacks);
    }

    @ApiOperation("查看课程平时成绩")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    public VO<List<Score>> score(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                 @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        List<Score> scores = scoreService.findScoreByCourseId(courseId);

        return new VO<>(scores);
    }

    @ApiOperation("查看课程学生")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/all_student", method = RequestMethod.POST)
    public VO<List<Student>> allStudent(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                                        @ApiParam(required = true) @RequestParam("courseId") String courseId) {

        List<Student> students = courseRoomService.findByCourseId(courseId);

        return new VO<>(students);
    }

    @ApiOperation("私聊学生")
    @ApiImplicitParam(value = "token", name = "token", paramType = "query", dataType = "String", required = true)
    @RequestMapping(value = "/to_student", method = RequestMethod.POST)
    public VO<Empty> toStudent(@ApiIgnore @RequestAttribute("teacher") Teacher teacher,
                        @ApiParam(required = true) @RequestParam("studentId") String studentId,
                        @ApiParam(required = true) @RequestParam("content") String content,
                        @ApiParam(required = true) @RequestParam("objectName") String objectName) {

        /*
            融云操作
         */
        CodeSuccessResult codeSuccessResult = RongYunUtil.publishPrivate(teacher.getTeacherId(), studentId, content);
        if (codeSuccessResult == null || codeSuccessResult.getCode() != 200) {
            return new VO<>(5001, "融云服务器推送失败", new Empty());
        }

        return MyUtil.emptyReturn();
    }

}
