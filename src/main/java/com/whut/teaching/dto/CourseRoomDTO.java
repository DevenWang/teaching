package com.whut.teaching.dto;

import com.whut.teaching.model.*;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class CourseRoomDTO {

    private String courseRoomId;
    private StudentDTO studentDTO;
    private CourseDTO courseDTO;
    private Date joinTime;
    private int rollCall;
    private int question;

    public CourseRoomDTO() {
    }

    public CourseRoomDTO(CourseRoom courseRoom, StudentDTO studentDTO, CourseDTO courseDTO) {
        this.courseRoomId = courseRoom.getCourseRoomId();
        this.joinTime = courseRoom.getJoinTime();
        this.rollCall = courseRoom.getRollCall();
        this.question = courseRoom.getQuestion();
        this.studentDTO = studentDTO;
        this.courseDTO = courseDTO;
    }

    public CourseRoomDTO(CourseRoom courseRoom, Course course, Teacher teacher, Institute institute,Institute institute1, Student student) {
        this.courseRoomId = courseRoom.getCourseRoomId();
        this.joinTime = courseRoom.getJoinTime();
        this.rollCall = courseRoom.getRollCall();
        this.question = courseRoom.getQuestion();
        this.studentDTO = new StudentDTO(student, institute1);
        this.courseDTO = new CourseDTO(course, teacher, institute);
    }

    public String getCourseRoomId() {
        return courseRoomId;
    }

    public void setCourseRoomId(String courseRoomId) {
        this.courseRoomId = courseRoomId;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public int getRollCall() {
        return rollCall;
    }

    public void setRollCall(int rollCall) {
        this.rollCall = rollCall;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
