package com.whut.teaching.service;

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

    Answer saveAndUpdate(Answer answer);

    List<Answer> checkAnswer(String questionId);

}
