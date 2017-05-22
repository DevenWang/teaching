package com.whut.teaching.dao;

import com.whut.teaching.model.CourseRoom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface CourseRoomDAO extends CrudRepository<CourseRoom, String> {

    @Query("select c.courseId from CourseRoom c where c.studentId=?1")
    List<String> findCourseId(String studentId);

    @Query("select c.studentId from CourseRoom c where c.courseId=?1")
    List<String> findStudentId(String courseId);

    List<CourseRoom> findByStudentIdAndCourseId(String studentId, String courseId);

}
