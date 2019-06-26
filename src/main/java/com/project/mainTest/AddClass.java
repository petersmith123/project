package com.project.mainTest;

public class AddClass {

    public  static void  main(String[]args){
        int sum= add1(1,2);
        System.out.println(sum);
    }
    public static  int add1(int num1,int num2){
        int temp=num1^num2;// 不进位
        num2=(num1&num2)<<1;// 进位
        num1=temp;
        if(num2==0){
            return num1;
        }else{
           return add1(num1,num2);
        }
    }
}
