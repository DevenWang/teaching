package com.whut.teaching.service;

import com.whut.teaching.model.ResponseRollcall;
import com.whut.teaching.model.RollCall;
import com.whut.teaching.model.Student;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface RollCallService {

    RollCall saveAndUpdate(RollCall rollCall);

    RollCall findByRollCallId(String rollCallId);

    List<RollCall> findAllByCourseId(String courseId);

    List<RollCall> findByCourseId(String courseId, int status);

    List<Student> findLastStudent(String rollCallId);

    ResponseRollcall saveAndUpdate(ResponseRollcall responseRollcall);

}
