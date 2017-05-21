package com.whut.teaching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class ResponseRollcall {

    @Id
    @Column(length = 20)
    private String responseId;

    @Column(length = 20)
    private String rollcallId;

    @Column(length = 15)
    private String studentId;

    public ResponseRollcall() {
    }

    public ResponseRollcall(String responseId, String rollcallId, String studentId) {
        this.responseId = responseId;
        this.rollcallId = rollcallId;
        this.studentId = studentId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getRollcallId() {
        return rollcallId;
    }

    public void setRollcallId(String rollcallId) {
        this.rollcallId = rollcallId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
