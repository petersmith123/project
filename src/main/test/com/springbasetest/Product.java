package com.springbasetest;

public class Product implements Runnable{
    private  Apple apple;
    public Product(Apple apple){
        this.apple=apple;
    }
    public void run() {

        synchronized (apple){

       for(int i=1;i<100;i++){

        if (apple.isFlag()){
            int   random= (int)(Math.random()*49+1);
            apple.setNum(random);
            System.out.println("第"+i+"次product"+ apple.getName()+"-"+apple.getNum());
            apple.setFlag(false);
        }else{
            try {
                apple.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        apple.notify();


       }

        }
    }
}
