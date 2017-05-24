package com.whut.teaching.dto;

import com.whut.teaching.model.Course;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Question;
import com.whut.teaching.model.Teacher;

/**
 * Created by wpc on 2017/5/23.
 */
public class QuestionDTO {

    private String questionId;
    private CourseDTO courseDTO;
    private String question;
    private String realAnswer;
    private int status;

    public QuestionDTO() {
    }

    public QuestionDTO(Question question, CourseDTO courseDTO) {
        this.questionId = question.getQuestionId();
        this.question = question.getQuestion();
        this.realAnswer = question.getRealAnswer();
        this.status = question.getStatus();
        this.courseDTO = courseDTO;
    }

    public QuestionDTO(Question question, Course course, Teacher teacher, Institute institute) {
        this.questionId = question.getQuestionId();
        this.question = question.getQuestion();
        this.realAnswer = question.getRealAnswer();
        this.status = question.getStatus();
        this.courseDTO = new CourseDTO(course, teacher, institute);
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRealAnswer() {
        return realAnswer;
    }

    public void setRealAnswer(String realAnswer) {
        this.realAnswer = realAnswer;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
