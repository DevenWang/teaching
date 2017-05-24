package com.whut.teaching.dto;

import com.whut.teaching.model.*;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class FeedbackDTO {

    private String feedBackId;
    private CourseDTO courseDTO;
    private StudentDTO studentDTO;
    private String content;
    private Date sendTime;

    public FeedbackDTO() {
    }

    public FeedbackDTO(FeedBack feedBack, CourseDTO courseDTO, StudentDTO studentDTO) {
        this.feedBackId = feedBack.getFeedBackId();
        this.content = feedBack.getContent();
        this.sendTime = feedBack.getSendTime();
        this.courseDTO = courseDTO;
        this.studentDTO = studentDTO;
    }

    public FeedbackDTO(FeedBack feedBack, Course course, Teacher teacher, Institute institute, Student student) {
        this.feedBackId = feedBack.getFeedBackId();
        this.content = feedBack.getContent();
        this.sendTime = feedBack.getSendTime();
        this.courseDTO = new CourseDTO(course, teacher, institute);
        this.studentDTO = new StudentDTO(student, institute);
    }

    public String getFeedBackId() {
        return feedBackId;
    }

    public void setFeedBackId(String feedBackId) {
        this.feedBackId = feedBackId;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
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
