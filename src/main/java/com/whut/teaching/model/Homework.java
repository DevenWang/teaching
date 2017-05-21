package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Homework {

    @Id
    @Column(length = 20)
    private String homeworkId;

    @Column(length = 20)
    private String courseId;

    @Column
    private String content;

    @Temporal(TemporalType.DATE)
    private Date createTime;

    @Column(length = 20)
    private String endTime;

    public Homework() {
    }

    public Homework(String homeworkId, String courseId, String content, Date createTime, String endTime) {
        this.homeworkId = homeworkId;
        this.courseId = courseId;
        this.content = content;
        this.createTime = createTime;
        this.endTime = endTime;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
