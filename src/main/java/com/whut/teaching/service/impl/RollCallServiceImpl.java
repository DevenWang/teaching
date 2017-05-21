package com.whut.teaching.service.impl;

import com.whut.teaching.dao.ResponseRollCallDAO;
import com.whut.teaching.dao.RollCallDAO;
import com.whut.teaching.dao.StudentDAO;
import com.whut.teaching.model.ResponseRollcall;
import com.whut.teaching.model.RollCall;
import com.whut.teaching.model.Student;
import com.whut.teaching.service.RollCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class RollCallServiceImpl implements RollCallService{

    @Autowired
    private RollCallDAO rollCallDAO;

    @Autowired
    private ResponseRollCallDAO responseRollCallDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Override
    public RollCall saveAndUpdate(RollCall rollCall) {
        return rollCallDAO.save(rollCall);
    }

    @Override
    public RollCall findByRollCallId(String rollCallId) {
        return rollCallDAO.findOne(rollCallId);
    }

    @Override
    public List<RollCall> findAllByCourseId(String courseId) {
        return rollCallDAO.findByCourseId(courseId);
    }

    @Override
    public List<RollCall> findByCourseId(String courseId,int status) {
        return rollCallDAO.findByCourseIdAndStatus(courseId, status);
    }

    @Override
    public List<Student> findLastStudent(String rollCallId) {

        List<String> studentIds = responseRollCallDAO.findLastStudentId(rollCallId);
        if (studentIds != null && studentIds.size() > 0) {
            List<Student> lastStudent = (List<Student>) studentDAO.findAll(studentIds);
            return lastStudent;
        }

        return null;
    }

    @Override
    public ResponseRollcall saveAndUpdate(ResponseRollcall responseRollcall) {
        return responseRollCallDAO.save(responseRollcall);
    }

}
