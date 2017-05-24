package com.whut.teaching.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class RollCall {

    public static final int ENDING_ROLLCALL = 0;
    public static final int STARTING_ROLLCALL = 1;
    public static final double DIATANCE = 300.0;

    @Id
    @Column(length = 20)
    private String rollcallId;

    @Column(length = 20)
    private String courseId;

    @Column(length = 6)
    private String code;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @Temporal(TemporalType.DATE)
    private Date createTime;

    private int status;

    public RollCall() {
    }

    public RollCall(String rollcallId, String courseId, String code, double latitude, double longitude, Date createTime, int status) {
        this.rollcallId = rollcallId;
        this.courseId = courseId;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = createTime;
        this.status = status;
    }

    public String getRollcallId() {
        return rollcallId;
    }

    public void setRollcallId(String rollcallId) {
        this.rollcallId = rollcallId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
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

    public void setStatus(int stattus) {
        this.status = stattus;
    }
}
