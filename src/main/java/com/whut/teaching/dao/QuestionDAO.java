package com.whut.teaching.dao;

import com.whut.teaching.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface QuestionDAO extends CrudRepository<Question,String> {

    List<Question> findByCourseId(String courseId);

    List<Question> findByCourseIdAndStatus(String courseId, int status);

}
