package com.whut.teaching.dto;

import com.whut.teaching.util.ExcelResources;

/**
 * Created by wpc on 2017/5/26.
 */
public class ScoreDTO {

    private String studentId;
    private String studentName;
    private String studentClass;
    private String institute;
    private double rollCall;
    private double question;
    private double score;

    private String testScore;
    private String finalScore;

    public ScoreDTO() {
    }

    public ScoreDTO(String studentId, String studentName, String studentClass, String institute, double rollCall, double question) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.institute = institute;
        this.rollCall = rollCall;
        this.question = question;
        this.score = rollCall + question;
        this.testScore=" ";
        this.finalScore=" ";
    }

    @ExcelResources(title = "学号",order = 1)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @ExcelResources(title = "姓名",order = 2)
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @ExcelResources(title = "班级",order = 3)
    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    @ExcelResources(title = "专业",order = 4)
    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @ExcelResources(title = "点名成绩",order = 5)
    public double getRollCall() {
        return rollCall;
    }

    public void setRollCall(double rollCall) {
        this.rollCall = rollCall;
    }

    @ExcelResources(title = "课堂作业成绩",order = 6)
    public double getQuestion() {
        return question;
    }

    public void setQuestion(double question) {
        this.question = question;
    }

    @ExcelResources(title = "平时成绩",order = 7)
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @ExcelResources(title = "考试成绩成绩",order = 8)
    public String getTestScore() {
        return testScore;
    }

    public void setTestScore(String testScore) {
        this.testScore = testScore;
    }

    @ExcelResources(title = "总成绩",order = 9)
    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }
}
