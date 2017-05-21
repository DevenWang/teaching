package com.whut.teaching.service;

import com.whut.teaching.model.Homework;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface HomeworkService {

    Homework saveAndUpdate(Homework homework);

    List<Homework> findHomework(String courseId);

}
