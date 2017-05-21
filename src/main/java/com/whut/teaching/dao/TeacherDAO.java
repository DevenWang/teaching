package com.whut.teaching.dao;

import com.whut.teaching.model.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface TeacherDAO extends CrudRepository<Teacher, String> {

    List<Teacher> findByInstituteId(String instituteId);

    List<Teacher> findByName(String name);

    @Query("select t from Teacher t where t.name=:name and t.instituteId=:instituteId")
    Teacher findByNamedAndInstituteIdAnd(@Param("name") String name, @Param("instituteId") String instituteId);

}
