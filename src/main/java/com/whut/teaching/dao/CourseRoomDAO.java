package com.whut.teaching.dao;

import com.whut.teaching.dto.CourseDTO;
import com.whut.teaching.dto.CourseRoomDTO;
import com.whut.teaching.model.CourseRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface CourseRoomDAO extends CrudRepository<CourseRoom, String> {

    @Query("select c.courseId from CourseRoom c where c.studentId=:studentId")
    List<String> findCourseId(@Param("studentId") String studentId);

    @Query("select c.studentId from CourseRoom c where c.courseId=:courseId")
    List<String> findStudentId(@Param("courseId") String courseId);

    List<CourseRoom> findByCourseId(String courseId);

    List<CourseRoom> findByStudentId(String studentId);

    List<CourseRoom> findByStudentIdAndCourseId(String studentId, String courseId);

    @Query("select new com.whut.teaching.dto.CourseRoomDTO(cr,c,t,i,i1,s) from CourseRoom cr, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i, " +
            " com.whut.teaching.model.Institute i1, " +
            " com.whut.teaching.model.Student s " +
            " where cr.courseId=:courseId " +
            " and c.courseId=:courseId " +
            " and t.teacherId=c.teacherId " +
            " and i.instituteId=t.instituteId" +
            " and s.studentId=cr.studentId and i1.instituteId=s.instituteId ")
    List<CourseRoomDTO> courseScores(@Param("courseId") String courseId);

    @Query("select new com.whut.teaching.dto.CourseDTO(c,t,i) " +
            " from CourseRoom cr, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where cr.studentId=:studentId " +
            " and c.courseId=cr.courseId " +
            " and t.teacherId=c.teacherId " +
            " and t.instituteId=i.instituteId ")
    List<CourseDTO> studentCourses(@Param("studentId") String studentId);

}
