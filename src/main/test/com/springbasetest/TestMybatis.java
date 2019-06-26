package com.springbasetest;

import com.project.dao.TeacherMapper;
import com.project.pojo.Teacher;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/spring-mybatis.xml","classpath*:/spring/applicationContext.xml"})
public class TestMybatis {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public  void test1(){
        Teacher teacherByID = teacherMapper.getTeacherByID(1);
        System.out.println(teacherByID);

    }
    @Test
    public  void  getTea(){
        Teacher tea=new Teacher();
        tea.setName("小");
       // tea.setAge(23);
        List<Teacher>  tea2= teacherMapper.getTeacherByMap(tea);
        System.out.println(tea2);
    }
    @Test
    public void getTeaIn(){
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        List<Teacher> list2=teacherMapper.getTeacherIn(list);
        System.out.println(list2);
    }
    @Test
    public  void getByChose(){
      List<Teacher> list= teacherMapper.getByChose(-1);
      System.out.println(list);
    }

    /****
     *测试trim标签的用法
     */

    @Test
    public  void selectTrim(){
        Teacher tea=new Teacher();
        tea.setName("小");
        tea.setAge(23);
        List<Teacher> list=teacherMapper.selectTrim(tea);
        System.out.println(list);
    }
    @Test
    public  void update(){
        Teacher teacher=new Teacher();
        teacher.setId(1);
        teacher.setName("Amy");
        teacher.setAge(18);
       int k= teacherMapper.upadate1(teacher);
       System.out.println(k);
    }
    @Test
    public  void update2(){
        Teacher teacher2=new Teacher();
        teacher2.setId(1);
        teacher2.setName("PETER");
        //teacher2.setAge(18);
        int s= teacherMapper.update2(teacher2);
        System.out.println(s);
    }
    @Test
    public  void insertByeach(){
        long start= System.currentTimeMillis();
        List<Teacher> list=new ArrayList<Teacher>();
        Teacher teacher=null;
        for (int i=1;i<10000;i++){
            teacher=new Teacher();
            teacher.setName("测试"+i);

            teacher.setAge(i);
            list.add(teacher);
        }

        teacherMapper.insertByEach(list);
        long end=System.currentTimeMillis();
        long dif=end-start;
        System.out.println("总计耗时："+dif);

    }
    @Test
    public  void insertByeach2(){
        SqlSession session= sqlSessionFactory.openSession(false);
        TeacherMapper teacherMapper= session.getMapper(TeacherMapper.class);
        long start= System.currentTimeMillis();
        for (int i=1;i<10000;i++){
            Teacher teacher=new Teacher();
            teacher.setName("测试"+i);
            teacher.setAge(i);
            teacherMapper.insertTeacher(teacher);
        }
        session.commit();
        long end=System.currentTimeMillis();
        long dif=end-start;
        System.out.println("总计耗时："+dif);

    }
    @Test
    public void  Testusegeneratedkeys(){
        Teacher t1=new Teacher();
        t1.setName("Jan");
        t1.setAge(23);
        int pkey=teacherMapper.insertTeacher(t1);
        System.out.println(t1);
    }
    @Test
    public void  Testusegeneratedkeys2(){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("name","test1");
        map.put("age",200);
        int pkey=teacherMapper.insertTeacher2(map);
        Object id= map.get("id");
        System.out.println(id.toString());

    }

}
