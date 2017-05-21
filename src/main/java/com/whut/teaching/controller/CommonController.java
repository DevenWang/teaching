package com.whut.teaching.controller;

import com.whut.teaching.model.Institute;
import com.whut.teaching.model.Student;
import com.whut.teaching.service.StudentService;
import com.whut.teaching.vo.VO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wpc on 2017/5/20.
 */
@Api(description = "相同功能")
@RestController
public class CommonController {

    @Autowired
    private StudentService studentService;

    @ApiOperation("获取专业编号")
    @RequestMapping(value = "/institute_get", method = RequestMethod.POST)
    public VO<List<Institute>> getInstitute() {

        List<Institute> institutes = studentService.findAllInstitute();

        return new VO<>(institutes);
    }

}
