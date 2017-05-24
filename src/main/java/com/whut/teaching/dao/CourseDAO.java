package com.whut.teaching.dao;

import com.whut.teaching.dto.CourseDTO;
import com.whut.teaching.model.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface CourseDAO extends CrudRepository<Course, String> {

    List<Course> findByNameLike(String name);

    List<Course> findByTeacherId(String teacherId);

    @Query(value = "SELECT * FROM course c WHERE c.teacher_id IN (SELECT teacher_id FROM teacher WHERE institute_id =?1))", nativeQuery = true)
    List<Course> findByInstitute(String institute);

    @Query(nativeQuery = true, value = "SELECT * FROM course c WHERE c.teacher_id IN (SELECT t.teacher_id FROM teacher t WHERE t.name =?1)")
    List<Course> findByTeacherName(String teacherName);

    @Query("select new com.whut.teaching.dto.CourseDTO(c,t,i) from Course c,com.whut.teaching.model.Teacher t,com.whut.teaching.model.Institute i " +
            " where t.instituteId=i.instituteId and t.teacherId=c.teacherId")
    List<CourseDTO> allCourseDTO();

    @Query("select new com.whut.teaching.dto.CourseDTO(c,t,i) " +
            " from Course c,com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where t.instituteId=i.instituteId and t.teacherId=c.teacherId " +
            " and c.name like :name")
    List<CourseDTO> courseDTOByName(@Param("name") String name);

    @Query("select new com.whut.teaching.dto.CourseDTO(c,t,i) " +
            " from Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where " +
            " i.instituteId=t.instituteId " +
            " and c.teacherId=t.teacherId " +
            " and t.name=:name")
    List<CourseDTO> courseDTOByTeacherName(@Param("name") String name);

}
