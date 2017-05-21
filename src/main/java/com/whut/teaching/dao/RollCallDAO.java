package com.whut.teaching.dao;

import com.whut.teaching.model.RollCall;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface RollCallDAO extends CrudRepository<RollCall, String> {

    List<RollCall> findByCourseId(String courseId);

    List<RollCall> findByCourseIdAndStatus(String courseId, int status);

}
