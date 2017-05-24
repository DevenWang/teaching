package com.whut.teaching.dao;

import com.whut.teaching.dto.FeedbackDTO;
import com.whut.teaching.model.FeedBack;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface FeedBackDAO extends CrudRepository<FeedBack,String> {

    List<FeedBack> findByStudentId(String student);

    List<FeedBack> findByCourseId(String courseId);

    @Query("select new com.whut.teaching.dto.FeedbackDTO(f,c,t,i,s) from FeedBack f, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i, " +
            " com.whut.teaching.model.Student s " +
            " where f.courseId=:courseId " +
            " and s.studentId=f.studentId and s.instituteId=i.instituteId " +
            " and c.courseId=f.courseId " +
            " and t.instituteId=i.instituteId and t.teacherId=c.teacherId")
    List<FeedbackDTO> courseFeedBack(@Param("courseId") String courseId);

}
