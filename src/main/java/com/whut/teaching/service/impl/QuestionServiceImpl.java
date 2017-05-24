package com.whut.teaching.service.impl;

import com.whut.teaching.dao.AnswerDAO;
import com.whut.teaching.dao.QuestionDAO;
import com.whut.teaching.dto.AnswerDTO;
import com.whut.teaching.dto.QuestionDTO;
import com.whut.teaching.model.Answer;
import com.whut.teaching.model.Question;
import com.whut.teaching.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private AnswerDAO answerDAO;

    @Override
    public Question saveAndUpdate(Question question) {
        return questionDAO.save(question);
    }

    @Override
    public Question findByQuestionId(String question) {
        return questionDAO.findOne(question);
    }

    @Override
    public List<Question> findAllQuestionByCourseId(String courseId) {
        return questionDAO.findByCourseId(courseId);
    }

    @Override
    public List<Question> findQuestionByCourseId(String courseId, int status) {
        return questionDAO.findByCourseIdAndStatus(courseId, status);
    }

    @Override
    public List<QuestionDTO> courseQuestionDTOs(String courseId) {
        return questionDAO.courseQuestionDTOs(courseId);
    }

    @Override
    public Answer saveAndUpdate(Answer answer) {
        return answerDAO.save(answer);
    }

    @Override
    public List<Answer> checkAnswer(String questionId) {
        return answerDAO.findByQuestionId(questionId);
    }

    @Override
    public List<AnswerDTO> questionAnswerDTO(String questionId) {
        return answerDAO.questionAnswerDTOs(questionId);
    }

    @Override
    public List<QuestionDTO> studentQuestion(String studentId) {
        return questionDAO.studentQuestionDTOs(studentId);
    }

}
