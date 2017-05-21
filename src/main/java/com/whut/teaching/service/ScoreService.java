package com.whut.teaching.service;

import com.whut.teaching.model.Score;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
public interface ScoreService {

    Score saveAndUpdate(Score score);

    List<Score> findScoreByCourseId(String courseId);

    List<Score> findScoreByStudentId(String studentId);

    Score findByStuIdAnCoreId(String studentId, String courseId);

}
