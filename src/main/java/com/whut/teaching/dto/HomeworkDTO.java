package com.whut.teaching.dto;

import com.whut.teaching.model.Course;
import com.whut.teaching.model.Homework;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Teacher;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class HomeworkDTO {

    private String homeworkId;
    private CourseDTO courseDTO;
    private String content;
    private Date createTime;
    private String endTime;

    public HomeworkDTO() {
    }

    public HomeworkDTO(Homework homework, CourseDTO courseDTO) {
        this.homeworkId = homework.getHomeworkId();
        this.content = homework.getContent();
        this.createTime = homework.getCreateTime();
        this.endTime = homework.getEndTime();
        this.courseDTO = courseDTO;
    }

    public HomeworkDTO(Homework homework, Course course, Teacher teacher, Institute institute) {
        this.homeworkId = homework.getHomeworkId();
        this.content = homework.getContent();
        this.createTime = homework.getCreateTime();
        this.endTime = homework.getEndTime();
        this.courseDTO = new CourseDTO(course, teacher, institute);
    }



    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
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
