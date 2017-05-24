package com.whut.teaching.dao;

import com.whut.teaching.dto.RollCallDTO;
import com.whut.teaching.model.RollCall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface RollCallDAO extends CrudRepository<RollCall, String> {

    List<RollCall> findByCourseId(String courseId);

    List<RollCall> findByCourseIdAndStatus(String courseId, int status);

    @Query("select new com.whut.teaching.dto.RollCallDTO(r,c,t,i) from RollCall r, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where r.courseId=:courseId and c.courseId=r.courseId and t.instituteId=i.instituteId and t.teacherId=c.teacherId")
    List<RollCallDTO> allRollCallDTOBycourseId(@Param("courseId") String courseId);

    @Query("select new com.whut.teaching.dto.RollCallDTO(r,c,t,i) from RollCall r, " +
            " com.whut.teaching.model.Course c, " +
            " com.whut.teaching.model.Teacher t, " +
            " com.whut.teaching.model.Institute i " +
            " where r.courseId=:courseId and c.courseId=r.courseId and t.instituteId=i.instituteId and t.teacherId=c.teacherId " +
            " and r.status=1")
    List<RollCallDTO> oneRollCallDTOBycourseId(@Param("courseId") String courseId);

}
