package com.whut.teaching.dao;

import com.whut.teaching.dto.StudentDTO;
import com.whut.teaching.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface StudentDAO extends CrudRepository<Student, String> {

    @Query("select new com.whut.teaching.dto.StudentDTO(s,i) from " +
            " Student s,com.whut.teaching.model.Institute i " +
            " where s.instituteId=i.instituteId and s.studentId=:studentId")
    StudentDTO findOneStudentDTO(@Param("studentId") String studentId);

}
