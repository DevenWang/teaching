package com.whut.teaching.dao;

import com.whut.teaching.model.Homework;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface HomeworkDAO extends CrudRepository<Homework,String>{

    List<Homework> findByCourseId(String courseId);

}
