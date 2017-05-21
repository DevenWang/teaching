package com.whut.teaching.dao;

import com.whut.teaching.model.ResponseRollcall;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wpc on 2017/5/19.
 */
public interface ResponseRollCallDAO extends CrudRepository<ResponseRollcall, String> {

    @Query(nativeQuery = true, value = "SELECT s.student_id FROM student s WHERE s.student_id NOT IN " +
            " (SELECT student_id FROM response_rollcall WHERE rollcall_id =?1)")
    List<String> findLastStudentId(String rollCallId);

}
