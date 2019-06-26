package com.springbasetest;

import org.junit.Test;

import java.util.Arrays;

public class Algorithm {

    public static int [] sort(int[]array){
        for(int i=0;i<array.length-1;i++){
            for(int j=0;j<array.length-i-1;j++){
                if(array[j]>array[j+1]){
                    int temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }
    public static int [] sort2(int[]array){
        for(int i=0;i<array.length-1;i++){
            for(int j=i+1;j<array.length-1;j++){
               if (array[i]>array[j]){

                   int temp=array[i];
                   array[i]=array[j];
                   array[j]=temp;

               }
            }
        }
        return array;
    }

    public static void main(String[]args){
        int array[]={4,2,1,9};
        int []array2=sort2(array);
       for (int i=0;i<array2.length;i++){
           System.out.println(array2[i]);
       }
    }
}
