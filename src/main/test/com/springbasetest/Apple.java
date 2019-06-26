package com.springbasetest;


public class Apple {
    private   boolean flag=false;
    private  int  num;
    private String name;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Apple(String name){
       this.name=name;
    }
}
