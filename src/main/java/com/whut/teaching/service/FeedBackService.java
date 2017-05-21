package com.whut.teaching.service;

import com.whut.teaching.model.FeedBack;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface FeedBackService {

    FeedBack saveAndUpdate(FeedBack feedBack);

    List<FeedBack> findByCourseId(String courseId);

    List<FeedBack> findByStudentId(String studentId);

}
