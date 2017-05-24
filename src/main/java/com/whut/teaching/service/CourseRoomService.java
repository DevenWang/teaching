package com.whut.teaching.service;

import com.whut.teaching.dto.CourseDTO;
import com.whut.teaching.dto.CourseRoomDTO;
import com.whut.teaching.model.Course;
import com.whut.teaching.model.CourseRoom;
import com.whut.teaching.model.Student;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface CourseRoomService {

    CourseRoom saveOrUpdate(CourseRoom courseRoom);

    CourseRoom findById(String id);

    List<Student> findByCourseId(String courseId);

    List<Course> findByStudentId(String studentId);

    Iterable<CourseRoom> addStudentsToCourse(String courseId, List<String> studentIds);

    List<CourseRoom> findCourseRoomByCourseId(String courseId);

    CourseRoom findByStuIdAnCoreId(String studentId, String courseId);

    List<CourseRoomDTO> courseScores(String courseId);

    List<CourseDTO> studentCourses(String studentId);

    List<String> studentIdsBycourseId(String courseId);

}
