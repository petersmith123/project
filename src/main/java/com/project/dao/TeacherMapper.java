package com.project.dao;

import com.project.pojo.Teacher;

import java.util.List;
import java.util.Map;

public interface TeacherMapper {
    Teacher getTeacherByID(int id);
    List<Teacher> getTeacherByMap(Teacher teacher);
    List<Teacher> getTeacherIn(List<Integer> list);
    List<Teacher> getByChose(Integer flag);
    List<Teacher> selectTrim(Teacher teacher);
    int   upadate1(Teacher teacher);
    int   update2(Teacher teacher);
    int  insertByEach(List<Teacher> list);
    int insertTeacher(Teacher teacher);
    int insertTeacher2(Map map);

}
