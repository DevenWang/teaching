package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Course {

    public static final int ENDING_COURDSE = 0;
    public static final int STARTING_COURSE = 1;

    @Id
    @Column(length = 20)
    private String courseId;

    @Column(length = 15)
    private String teacherId;

    @Column(length = 20)
    private String name;

    @Column
    private String courseDesc;

    @Temporal(TemporalType.DATE)
    private Date createTime;

    /*
     * 0表示结课或者停课等
     * 1表示正常上课
     */
    @Column
    private int status;

    public Course() {
    }

    public Course(String courseId, String teacherId, String name, String courseDesc, Date createTime, int status) {
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.name = name;
        this.courseDesc = courseDesc;
        this.createTime = createTime;
        this.status = status;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
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
