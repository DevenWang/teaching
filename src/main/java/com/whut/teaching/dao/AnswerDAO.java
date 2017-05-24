package com.whut.teaching.dao;

import com.whut.teaching.dto.AnswerDTO;
import com.whut.teaching.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface AnswerDAO extends CrudRepository<Answer, String> {

    List<Answer> findByQuestionId(String questionId);

    @Query("select new com.whut.teaching.dto.AnswerDTO(a,q,c,t,i,s) from Answer a, " +
            " com.whut.teaching.model.Question q, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i, " +
            " com.whut.teaching.model.Student s " +
            " where a.questionId=:questionId " +
            " and q.questionId=a.questionId " +
            " and s.studentId=a.studentId and s.instituteId=i.instituteId " +
            " and c.courseId=q.courseId " +
            " and t.instituteId=i.instituteId and t.teacherId=c.teacherId")
    List<AnswerDTO> questionAnswerDTOs(@Param("questionId") String questionId);
}
