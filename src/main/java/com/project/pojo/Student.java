package com.project.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component(value = "Stu")
public class Student implements Serializable {
    @Value("1")
    private int tid;
    @Value("peter")
    private String tname;
    @Value("18")
    private  int tage;

    private List<Teacher> listTeacer;


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getTage() {
        return tage;
    }

    public void setTage(int tage) {
        this.tage = tage;
    }

    public List<Teacher> getListTeacer() {
        return listTeacer;
    }

    public void setListTeacer(List<Teacher> listTeacer) {
        this.listTeacer = listTeacer;
    }

    @Override
    public String toString() {
        return "Student{" +
                "tid=" + tid +
                ", tname='" + tname + '\'' +
                ", tage=" + tage +
                ", listTeacer=" + listTeacer +
                '}';
    }
}
