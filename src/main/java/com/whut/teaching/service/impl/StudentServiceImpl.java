package com.whut.teaching.service.impl;

import com.whut.teaching.dao.InstituteDAO;
import com.whut.teaching.dao.StudentDAO;
import com.whut.teaching.dto.StudentDTO;
import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Student;
import com.whut.teaching.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private InstituteDAO instituteDAO;

    @Override
    public Student saveOrUpdate(Student student) {
        return studentDAO.save(student);
    }

    @Override
    public Student findById(String id) {
        return studentDAO.findOne(id);
    }

    @Override
    public List<Institute> findAllInstitute() {
        return (List<Institute>) instituteDAO.findAll();
    }

    @Override
    public Institute findInstituteById(String instituteId) {
        return instituteDAO.findOne(instituteId);
    }

    @Override
    public StudentDTO oneStudentDTO(String studentId) {
        return studentDAO.findOneStudentDTO(studentId);
    }

}
