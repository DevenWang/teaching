package com.whut.teaching.dao;

import com.whut.teaching.model.Score;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface ScoreDAO extends CrudRepository<Score,String> {

    List<Score> findByCourseId(String courseId);

    List<Score> findByStudentId(String studentId);

    List<Score> findByStudentIdAndCourseId(String studentId, String courseId);

}
