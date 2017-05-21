package com.whut.teaching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Student {

    @Id
    @Column(length = 15)
    private String studentId;

    @Column(length = 10)
    private String name;

    @Column(length = 16)
    private String password;

    @Column(length = 20)
    private String studentClass;

    @Column(length = 10)
    private String instituteId;

    @Column(length = 15)
    private String phone;

    @Column(length = 20)
    private String email;

    @Column
    private String portraitUri;

    public Student() {
    }

    public Student(String studentId, String name, String password, String studentClass, String instituteId, String phone) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.studentClass = studentClass;
        this.instituteId = instituteId;
        this.phone = phone;
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

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
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
