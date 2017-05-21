package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class FeedBack {

    @Id
    @Column(length = 20)
    private String feedBackId;

    @Column(length = 20)
    private String courseId;

    @Column(length = 15)
    private String studentId;

    @Column
    private String content;

    @Temporal(TemporalType.DATE)
    private Date sendTime;

    public FeedBack() {
    }

    public FeedBack(String feedBackId, String courseId, String studentId, String content, Date sendTime) {
        this.feedBackId = feedBackId;
        this.courseId = courseId;
        this.studentId = studentId;
        this.content = content;
        this.sendTime = sendTime;
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
