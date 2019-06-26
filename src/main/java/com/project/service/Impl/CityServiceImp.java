package com.project.service.Impl;

import com.project.pojo.City;
import com.project.pojo.CityExample;
import com.project.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CityServiceImp implements CityService {
    public List<City> selectList(HashMap hashMap) {
        return null;
    }

    public City selectCityById(Integer id) {
        return null;
    }

    public void insertCity() {

    }

    /*@Autowired
    private CityMapper cityMapper;

    public List<City> selectList(HashMap hashMap) {
       Set<Map.Entry<String,Object>>  entry = hashMap.entrySet();
       for (Map.Entry<String,Object> item : entry){
           String key=item.getKey();
           Object value=item.getValue();
       }
       CityExample cityExample=new CityExample();
       cityExample.createCriteria().andNameLike("h");
       List<City>  list= cityMapper.selectByExample(cityExample);
       return list;
    }

    public City selectCityById(Integer id) {
        City city1= cityMapper.selectByPrimaryKey(id);
        City city=new City();
        city.setName("ccc");
        city.setDistrict("aaa");
        city.setCountrycode("IDN");
        city.setPopulation(111);
        cityMapper.insert(city);
        city.setName("ddd");
        int i=1/0;
        cityMapper.insert(city);
        return city1;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    public void  insertCity() {

         try{
        System.out.println("插入操作");
        City city=new City();
        city.setName("ccc");
        city.setDistrict("aaa");
        city.setCountrycode("IDN");
        city.setPopulation(111);
        int j= cityMapper.insert(city);
        city.setName("ddd");
        int s=9/0;
        int k= cityMapper.insert(city);
        System.out.println("---111------");

         }catch(Exception e ){
             e.printStackTrace();
             throw  new RuntimeException();
         }

    }*/
}
