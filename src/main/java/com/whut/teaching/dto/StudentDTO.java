package com.whut.teaching.dto;

import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Student;

/**
 * Created by wpc on 2017/5/23.
 */
public class StudentDTO {

    private String studentId;
    private String name;
    private String password;
    private String studentClass;
    private Institute institute;
    private String phone;
    private String email;
    private String portraitUri;

    public StudentDTO() {
    }

    public StudentDTO(Student student, Institute institute) {
        this.studentId = student.getStudentId();
        this.name = student.getName();
        this.password = student.getPassword();
        this.studentClass = student.getStudentClass();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.portraitUri = student.getPortraitUri();
        this.institute = institute;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
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
