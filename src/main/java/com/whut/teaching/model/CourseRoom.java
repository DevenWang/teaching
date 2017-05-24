package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class CourseRoom {

    @Id
    @Column(length = 20)
    private String courseRoomId;

    @Column(length = 15)
    private String studentId;

    @Column(length = 20)
    private String courseId;

    @Temporal(TemporalType.DATE)
    private Date joinTime;

    @Column
    private int rollCall;

    @Column
    private int question;

    public CourseRoom() {
    }

    public CourseRoom(String courseRoomId, String studentId, String courseId, Date joinTime, int rollCall, int question) {
        this.courseRoomId = courseRoomId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.joinTime = joinTime;
        this.rollCall = rollCall;
        this.question = question;
    }

    public String getCourseRoomId() {
        return courseRoomId;
    }

    public void setCourseRoomId(String courseRoomId) {
        this.courseRoomId = courseRoomId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
