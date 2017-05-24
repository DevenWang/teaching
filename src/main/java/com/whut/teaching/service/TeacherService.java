package com.whut.teaching.service;


import com.whut.teaching.dto.TeacherDTO;
import com.whut.teaching.model.Teacher;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface TeacherService {

    Teacher saveOrUpdate(Teacher teacher);

    Teacher findById(String id);

    List<Teacher> findByName(String name);

    List<Teacher> findByInstitute(String institute);

    TeacherDTO findTeacherDTOByTeacherId(String teacherId);

}
