package com.whut.teaching.dao;

import com.whut.teaching.model.FeedBack;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface FeedBackDAO extends CrudRepository<FeedBack,String> {

    List<FeedBack> findByStudentId(String student);

    List<FeedBack> findByCourseId(String courseId);

}
