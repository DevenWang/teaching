package com.whut.teaching.dto;

import com.whut.teaching.model.Course;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Teacher;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class CourseDTO {

    private String courseId;
    private TeacherDTO teacherDTO;
    private String name;
    private String courseDesc;
    private Date createTime;
    private int status;

    public CourseDTO() {
    }

    public CourseDTO(Course course, Teacher teacher, Institute institute) {
        this.courseId = course.getCourseId();
        this.name = course.getName();
        this.courseDesc = course.getCourseDesc();
        this.createTime = course.getCreateTime();
        this.status = course.getStatus();
        this.teacherDTO = new TeacherDTO(teacher, institute);
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public TeacherDTO getTeacherDTO() {
        return teacherDTO;
    }

    public void setTeacherDTO(TeacherDTO teacherDTO) {
        this.teacherDTO = teacherDTO;
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
