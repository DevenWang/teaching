package com.whut.teaching.dao;

import com.whut.teaching.model.Answer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface AnswerDAO extends CrudRepository<Answer,String>{

    List<Answer> findByQuestionId(String questionId);

}
