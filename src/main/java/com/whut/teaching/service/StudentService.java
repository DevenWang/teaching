package com.whut.teaching.service;


import com.whut.teaching.dto.StudentDTO;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Student;

import java.util.List;


/**
 * Created by wpc on 2017/5/19.
 */

public interface StudentService {

    Student saveOrUpdate(Student student);

    Student findById(String id);

    List<Institute> findAllInstitute();

    Institute findInstituteById(String instituteId);

    StudentDTO oneStudentDTO(String studentId);

}
