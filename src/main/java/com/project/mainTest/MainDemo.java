package com.project.mainTest;

import com.project.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MainDemo {
    @Autowired
    @Qualifier("Stu")
    private Student stu;

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public static void main(String[]args){
       Student stu= new MainDemo().getStu();
       System.out.println(stu);
    }

}
