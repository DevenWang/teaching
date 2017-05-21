package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Answer {

    @Id
    @Column(length = 20)
    private String answerId;

    @Column(length = 20)
    private String questionId;

    @Column(length = 15)
    private String studentId;

    @Column(length = 10)
    private String answer;

    @Temporal(TemporalType.DATE)
    private Date createTime;

    public Answer() {
    }

    public Answer(String answerId, String questionId, String studentId, String answer, Date createTime) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.studentId = studentId;
        this.answer = answer;
        this.createTime = createTime;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
