package com.whut.teaching.dto;

import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Teacher;

/**
 * Created by wpc on 2017/5/23.
 */
public class TeacherDTO {

    private String teacherId;
    private String name;
    private String password;
    private Institute institute;
    private String phone;
    private String email;
    private String portraitUri;

    public TeacherDTO() {
    }

    public TeacherDTO(Teacher teacher, Institute institute) {
        this.teacherId = teacher.getTeacherId();
        this.name = teacher.getName();
        this.password = teacher.getPassword();
        this.phone = teacher.getPhone();
        this.email = teacher.getEmail();
        this.portraitUri = teacher.getPortraitUri();
        this.institute = institute;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Institute getInstitute() {
        return institute;
    }

    public void setInstitute(Institute institute) {
        this.institute = institute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPortraitUri() {
        return portraitUri;
    }

    public void setPortraitUri(String portraitUri) {
        this.portraitUri = portraitUri;
    }
}
