package com.whut.teaching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Score {

    @Id
    @Column(length = 20)
    private String scoreId;

    @Column(length = 20)
    private String courseId;

    @Column(length = 15)
    private String studentId;

    @Column
    private int rollcall;

    @Column
    private int question;

    public Score() {
    }

    public Score(String scoreId, String courseId, String studentId, int rollcall, int question) {
        this.scoreId = scoreId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.rollcall = rollcall;
        this.question = question;
    }

    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getRollcall() {
        return rollcall;
    }

    public void setRollcall(int rollcall) {
        this.rollcall = rollcall;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }
}
