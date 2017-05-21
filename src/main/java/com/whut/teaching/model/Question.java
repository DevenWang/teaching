package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Question {

    public static final int ENDING_QUESTION = 0;
    public static final int STARTING_QUESTION = 1;

    @Id
    @Column(length = 20)
    private String questionId;

    @Column(length = 20)
    private String courseId;

    @Column
    private String question;

    @Column(length = 10)
    private String realAnswer;

    private int status;

    public Question() {
    }

    public Question(String questionId, String courseId, String question, String realAnswer, int status, Date createTime) {
        this.questionId = questionId;
        this.courseId = courseId;
        this.question = question;
        this.realAnswer = realAnswer;
        this.status = status;
        this.createTime = createTime;
    }

    @Temporal(TemporalType.DATE)
    private Date createTime;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
