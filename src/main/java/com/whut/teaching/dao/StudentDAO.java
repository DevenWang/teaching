package com.whut.teaching.dao;

import com.whut.teaching.model.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wpc on 2017/5/19.
 */
public interface StudentDAO extends CrudRepository<Student, String> {



}
