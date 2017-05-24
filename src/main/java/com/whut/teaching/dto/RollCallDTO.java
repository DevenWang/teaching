package com.whut.teaching.dto;

import com.whut.teaching.model.Course;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.RollCall;
import com.whut.teaching.model.Teacher;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class RollCallDTO {

    private String rollcallId;
    private CourseDTO courseDTO;
    private String code;
    private double latitude;
    private double longitude;
    private Date createTime;
    private int status;

    public RollCallDTO() {
    }

    public RollCallDTO(RollCall rollCall, CourseDTO courseDTO) {
        this.rollcallId = rollCall.getRollcallId();
        this.code = rollCall.getCode();
        this.latitude = rollCall.getLatitude();
        this.longitude = rollCall.getLongitude();
        this.createTime = rollCall.getCreateTime();
        this.status = rollCall.getStatus();
        this.courseDTO = courseDTO;
    }

    public RollCallDTO(RollCall rollCall, Course course, Teacher teacher, Institute institute) {
        this.rollcallId = rollCall.getRollcallId();
        this.code = rollCall.getCode();
        this.latitude = rollCall.getLatitude();
        this.longitude = rollCall.getLongitude();
        this.createTime = rollCall.getCreateTime();
        this.status = rollCall.getStatus();
        this.courseDTO = new CourseDTO(course, teacher, institute);
    }

    public String getRollcallId() {
        return rollcallId;
    }

    public void setRollcallId(String rollcallId) {
        this.rollcallId = rollcallId;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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
