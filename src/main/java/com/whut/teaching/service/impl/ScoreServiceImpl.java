package com.whut.teaching.service.impl;

import com.whut.teaching.dao.ScoreDAO;
import com.whut.teaching.model.Score;
import com.whut.teaching.service.ScoreService;
import com.whut.teaching.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDAO scoreDAO;

    @Override
    public Score saveAndUpdate(Score score) {
        return scoreDAO.save(score);
    }

    @Override
    public List<Score> findScoreByCourseId(String courseId) {
        return scoreDAO.findByCourseId(courseId);
    }

    @Override
    public List<Score> findScoreByStudentId(String studentId) {
        return scoreDAO.findByStudentId(studentId);
    }

    @Override
    public Score findByStuIdAnCoreId(String studentId, String courseId) {
        List<Score> scores = scoreDAO.findByStudentIdAndCourseId(studentId, courseId);
        if (scores != null && scores.size() > 0) {
            return scores.get(0);
        } else {
            Score score = new Score(MyUtil.getStringID(), courseId, studentId, 0, 0);
            return score;
        }
    }

}
