package com.springbasetest;

public class Customer implements Runnable{
    private  Apple apple;
    public Customer(Apple apple){
        this.apple=apple;
    }
    public void run() { while (true){
        synchronized (apple){
            for (int i=1;i<100;i++){



        if (!apple.isFlag()){
            if(apple.getNum()>0){
              System.out.println("第"+i+"次customer"+apple.getName()+"-"+apple.getNum());
              apple.setNum(0);
            }else{
                System.out.println("第"+i+"次customer please  wait");
            }
            apple.setFlag(true);
        }else {
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
}
