package com.project.service;

import java.util.List;

import com.project.model.Geocoding;
import com.project.model.Weather;

public interface WeatherService {

	public List<Geocoding> search(String city);
	public Weather getWeather(double latitude, double longitude);
}
