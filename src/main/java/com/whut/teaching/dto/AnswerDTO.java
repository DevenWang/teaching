package com.whut.teaching.dto;

import com.whut.teaching.model.*;

import java.util.Date;

/**
 * Created by wpc on 2017/5/23.
 */
public class AnswerDTO {

    private String answerId;
    private QuestionDTO questionDTO;
    private StudentDTO studentDTO;
    private String answer;
    private Date createTime;

    public AnswerDTO() {
    }

    public AnswerDTO(Answer answer, QuestionDTO questionDTO, StudentDTO studentDTO) {
        this.answerId = answer.getAnswerId();
        this.answer = answer.getAnswer();
        this.createTime = answer.getCreateTime();
        this.questionDTO = questionDTO;
        this.studentDTO = studentDTO;
    }

    public AnswerDTO(Answer answer, Question question, Course course, Teacher teacher, Institute institute, Student student) {
        this.answerId = answer.getAnswerId();
        this.answer = answer.getAnswer();
        this.createTime = answer.getCreateTime();
        this.questionDTO = new QuestionDTO(question, course, teacher, institute);
        this.studentDTO = new StudentDTO(student, institute);
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public QuestionDTO getQuestionDTO() {
        return questionDTO;
    }

    public void setQuestionDTO(QuestionDTO questionDTO) {
        this.questionDTO = questionDTO;
    }

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
