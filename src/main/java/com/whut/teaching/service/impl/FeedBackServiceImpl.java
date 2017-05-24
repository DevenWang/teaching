package com.whut.teaching.service.impl;

import com.whut.teaching.dao.FeedBackDAO;
import com.whut.teaching.dto.FeedbackDTO;
import com.whut.teaching.model.FeedBack;
import com.whut.teaching.service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private FeedBackDAO feedBackDAO;

    @Override
    public FeedBack saveAndUpdate(FeedBack feedBack) {
        return feedBackDAO.save(feedBack);
    }

    @Override
    public List<FeedBack> findByCourseId(String courseId) {
        return feedBackDAO.findByCourseId(courseId);
    }

    @Override
    public List<FeedBack> findByStudentId(String studentId) {
        return feedBackDAO.findByStudentId(studentId);
    }

    @Override
    public List<FeedbackDTO> courseFeedBackDTO(String courseId) {
        return feedBackDAO.courseFeedBack(courseId);
    }
}
