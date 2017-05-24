package com.whut.teaching.service;

import com.whut.teaching.dto.CourseDTO;
import com.whut.teaching.model.Course;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface CourseService {

    Course saveOrUpdate(Course course);

    Course findById(String id);

    List<Course> findByTeacherId(String teacherId);

    List<Course> findByName(String name);

    List<Course> findByInstitute(String institute);

    List<Course> findByTeacherName(String teacherName);

    List<CourseDTO> allCourseDTO();

    List<CourseDTO> courseDTOByName(String name);

    List<CourseDTO> courseDTOByTeacherName(String name);

}
