package com.whut.teaching.dao;

import com.whut.teaching.dto.HomeworkDTO;
import com.whut.teaching.model.Homework;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface HomeworkDAO extends CrudRepository<Homework,String>{

    List<Homework> findByCourseId(String courseId);

    @Query("select new com.whut.teaching.dto.HomeworkDTO(h,c,t,i) from Homework h, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i, " +
            " com.whut.teaching.model.CourseRoom cr " +
            " where " +
            " cr.studentId=:studentId " +
            " and c.courseId=cr.courseId " +
            " and h.courseId=c.courseId " +
            " and t.teacherId=c.teacherId " +
            " and i.instituteId=t.instituteId")
    List<HomeworkDTO> studentHomeworkDTOs(@Param("studentId") String studentId);

}
