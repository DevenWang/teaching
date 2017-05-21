package com.whut.teaching.service.impl;

import com.whut.teaching.dao.TeacherDAO;
import com.whut.teaching.model.Teacher;
import com.whut.teaching.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    public Teacher saveOrUpdate(Teacher teacher) {
        return teacherDAO.save(teacher);
    }

    @Override
    public Teacher findById(String id) {
        return teacherDAO.findOne(id);
    }

    @Override
    public List<Teacher> findByName(String name) {
        return teacherDAO.findByName(name);
    }

    @Override
    public List<Teacher> findByInstitute(String institute) {
        return teacherDAO.findByInstituteId(institute);
    }

}
