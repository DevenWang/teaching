package com.whut.teaching.service.impl;

import com.whut.teaching.dao.HomeworkDAO;
import com.whut.teaching.model.Homework;
import com.whut.teaching.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkDAO homeworkDAO;

    @Override
    public Homework saveAndUpdate(Homework homework) {
        return homeworkDAO.save(homework);
    }

    @Override
    public List<Homework> findHomework(String courseId) {
        return homeworkDAO.findByCourseId(courseId);
    }

}
