package com.springbasetest;


import com.project.service.CityService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/applicationContext.xml"})
public class Test1 {


    @Autowired
    private CityService cityService;




    @Before
    public  void before(){
        System.out.println(Thread.currentThread().getName()+"-"+Thread.currentThread().getStackTrace());
    }

    @After
    public  void after(){
        System.out.println("Test over");
    }

    @Test
    public void spring_mybatis(){
       cityService.insertCity();
      System.out.println("----------------");
     // City city= cityService.selectCityById(1);
      //System.out.println(city);
    }

    @Test
    public  void  testTransaction(){
        cityService.insertCity();
    }

    @Test
    public  void Test(){
        Apple apple=new Apple("大苹果");
        Customer customer=new Customer(apple);
        Product product=new Product(apple);
        Thread thread1=new Thread(customer);
        thread1.start();
        Thread thread2=new Thread(product);
        thread2.start();
    }
}
