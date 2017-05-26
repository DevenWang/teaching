package com.whut.teaching.service;

import com.whut.teaching.dto.AnswerDTO;
import com.whut.teaching.dto.QuestionDTO;
import com.whut.teaching.model.Answer;
import com.whut.teaching.model.Question;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface QuestionService {

    Question saveAndUpdate(Question question);

    Question findByQuestionId(String questionId);

    List<Question> findAllQuestionByCourseId(String courseId);

    List<Question> findQuestionByCourseId(String courseId, int status);

    List<QuestionDTO> courseQuestionDTOs(String courseId);

    Answer saveAndUpdate(Answer answer);

    List<Answer> checkAnswer(String questionId);

    List<AnswerDTO> questionAnswerDTO(String questionId);

    List<QuestionDTO> studentQuestion(String studentId);

    int countQuestion(String courseId);

}
