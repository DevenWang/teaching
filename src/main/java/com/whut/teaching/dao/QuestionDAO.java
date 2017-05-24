package com.whut.teaching.dao;

import com.whut.teaching.dto.QuestionDTO;
import com.whut.teaching.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface QuestionDAO extends CrudRepository<Question, String> {

    List<Question> findByCourseId(String courseId);

    List<Question> findByCourseIdAndStatus(String courseId, int status);

    @Query("select new com.whut.teaching.dto.QuestionDTO(q,c,t,i) from Question q, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where q.courseId=:courseId and c.courseId=q.courseId and t.instituteId=i.instituteId and t.teacherId=c.teacherId")
    List<QuestionDTO> courseQuestionDTOs(@Param("courseId") String courseId);

    @Query("select new com.whut.teaching.dto.QuestionDTO(q,c,t,i) from Question q, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i, " +
            " com.whut.teaching.model.CourseRoom cr " +
            " where cr.studentId=:studentId " +
            " and c.courseId=cr.courseId " +
            " and q.courseId=cr.courseId " +
            " and t.teacherId=c.teacherId " +
            " and i.instituteId=t.instituteId ")
    List<QuestionDTO> studentQuestionDTOs(@Param("studentId") String studentId);

}
