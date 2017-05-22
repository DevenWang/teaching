package com.whut.teaching.service.impl;

import com.whut.teaching.dao.CourseDAO;
import com.whut.teaching.dao.CourseRoomDAO;
import com.whut.teaching.dao.StudentDAO;
import com.whut.teaching.model.Course;
import com.whut.teaching.model.CourseRoom;
import com.whut.teaching.model.Student;
import com.whut.teaching.service.CourseRoomService;
import com.whut.teaching.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class CourseRoomServiceImpl implements CourseRoomService {

    @Autowired
    private CourseRoomDAO courseRoomDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private CourseDAO courseDAO;

    @Override
    public CourseRoom saveOrUpdate(CourseRoom courseRoom) {
        return courseRoomDAO.save(courseRoom);
    }

    @Override
    public CourseRoom findById(String id) {
        return courseRoomDAO.findOne(id);
    }

    @Override
    public List<Student> findByCourseId(String courseId) {

        List<String> studentIds = courseRoomDAO.findStudentId(courseId);
        if (studentIds != null && studentIds.size() > 0) {
            return (List<Student>) studentDAO.findAll(studentIds);
        }
        return null;
    }

    @Override
    public List<Course> findByStudentId(String studentId) {
        List<String> courseIds = courseRoomDAO.findCourseId(studentId);
        if (courseIds != null && courseIds.size() > 0) {
            return (List<Course>) courseDAO.findAll(courseIds);
        }
        return null;
    }

    @Override
    public List<CourseRoom> addStudentsToCourse(String courseId, List<String> studentIds) {

        List<CourseRoom> courseRooms = new ArrayList<>();

        for (String studentId : studentIds) {
            /*
             *判断是否已经添加
             */
            List<CourseRoom> find = courseRoomDAO.findByStudentIdAndCourseId(studentId, courseId);
            if (find == null || find.size() <= 0) {
                courseRooms.add(new CourseRoom(MyUtil.getStringID(), studentId, courseId, new Date()));
            }
        }

        if (courseRooms != null && courseRooms.size() > 0) {
            return (List<CourseRoom>) courseRoomDAO.save(courseRooms);
        } else {
            return null;
        }

    }
}
