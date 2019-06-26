package com.project.service;


import com.project.pojo.City;

import java.util.HashMap;
import java.util.List;

public interface CityService {
    List<City> selectList(HashMap hashMap);
    City selectCityById(Integer id);
    void insertCity();

}
