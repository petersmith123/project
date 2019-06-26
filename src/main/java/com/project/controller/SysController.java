package com.project.controller;

import com.project.dao.TeacherMapper;
import com.project.pojo.Teacher;
import com.project.service.CityService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/sys")
public class SysController {

    @Autowired
    private TeacherMapper teacherMapper;

    protected  Logger logger = LogManager.getLogger(this.getClass());

    @RequestMapping("/test")
    @ResponseBody
    public String show(HttpServletRequest request){
        String contextPath = request.getSession().getServletContext().getRealPath("/");
        System.out.println(contextPath);
       String keyV= request.getSession().getServletContext().getInitParameter("contextConfigLocation") ;
        System.out.println("访问"+keyV);
        Teacher t1=teacherMapper.getTeacherByID(1);
        System.out.println(t1);
        logger.info("访问成功");
        return "hello";
    }
}
