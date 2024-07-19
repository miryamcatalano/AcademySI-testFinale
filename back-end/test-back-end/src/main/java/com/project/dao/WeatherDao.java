package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.Weather;

public interface WeatherDao extends CrudRepository<Weather, Integer>{

}
