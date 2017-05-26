package com.whut.teaching.interceptor;

import com.whut.teaching.model.Student;
import com.whut.teaching.model.Teacher;
import com.whut.teaching.service.StudentService;
import com.whut.teaching.service.TeacherService;
import com.whut.teaching.util.GsonUtils;
import com.whut.teaching.util.JedisUtils;
import com.whut.teaching.vo.VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wpc on 2017/5/21.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        String uid;
        String path = request.getServletPath();

        if (path.startsWith("student/login", 1) || path.startsWith("teacher/login", 1)) {
            return true;
        }
        if (path.startsWith("student/register", 1) || path.startsWith("teacher/register", 1)) {
            return true;
        }
        if (path.startsWith("institute_get", 1)) {
            return true;
        }

        if (token == null || (uid = JedisUtils.get(token)) == null) {
            response.getWriter().print(GsonUtils.toJson(VO.INVALID_TOKEN));
            return false;
        }

        if (path.startsWith("student", 1)) {
            Student student = studentService.findById(uid);
            request.setAttribute("student", student);
            return true;
        }

        if (path.startsWith("teacher", 1) || path.startsWith("excel", 1)) {
            Teacher teacher = teacherService.findById(uid);
            request.setAttribute("teacher", teacher);
            return true;
        }

        response.getWriter().print(GsonUtils.toJson(VO.INVALID_TOKEN));
        return false;
    }
}
