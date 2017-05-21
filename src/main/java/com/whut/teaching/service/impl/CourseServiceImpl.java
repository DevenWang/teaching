package com.whut.teaching.service.impl;

import com.whut.teaching.dao.CourseDAO;
import com.whut.teaching.model.Course;
import com.whut.teaching.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public Course saveOrUpdate(Course course) {
        return courseDAO.save(course);
    }

    @Override
    public Course findById(String id) {
        return courseDAO.findOne(id);
    }

    @Override
    public List<Course> findByTeacherId(String teacherId) {
        return courseDAO.findByTeacherId(teacherId);
    }

    @Override
    public List<Course> findByName(String name) {
        return courseDAO.findByNameLike(name);
    }

    @Override
    public List<Course> findByInstitute(String institute) {
        return courseDAO.findByInstitute(institute);
    }

    @Override
    public List<Course> findByTeacherName(String teacherName) {
        return courseDAO.findByTeacherName(teacherName);
    }

}
