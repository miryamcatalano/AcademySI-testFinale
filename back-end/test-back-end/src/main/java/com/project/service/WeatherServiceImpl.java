package com.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.project.dao.WeatherDao;
import com.project.model.Geocoding;
import com.project.model.Weather;

import reactor.core.publisher.Mono;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	@Autowired
	private WeatherDao weatherDao; 
	
	
	public List<Geocoding> search(String city) {
		
		WebClient webClient = WebClient.builder().baseUrl("https://geocoding-api.open-meteo.com/v1").build();
		
		JsonNode json = webClient.get().uri(uriBuilder -> uriBuilder
				.path("/search")
				.queryParam("name", city)
				.build())
				.retrieve()
				.bodyToMono(JsonNode.class)
				.onErrorResume(e -> Mono.empty())
				.block();
		
		json = json.get("results");
		
		List<Geocoding> countries = new ArrayList<>();
		for(JsonNode results : json) {
			Geocoding country = new Geocoding();
			country.setId(results.get("id").intValue());
			country.setName(results.get("name").textValue());
			country.setLatitude(results.get("latitude").doubleValue());
			country.setLongitude(results.get("longitude").doubleValue());
			
			countries.add(country);
		}
		return countries;
	}
	
	public Weather getWeather(double latitude, double longitude) {
		
		WebClient webClient = WebClient.builder().baseUrl("https://api.open-meteo.com/v1").build();
		
		JsonNode json = webClient.get().uri(uriBuilder -> uriBuilder
				.path("/forecast")
				.queryParam("latitude", latitude)
				.queryParam("longitude", longitude)
	            .queryParam("daily", "temperature_2m_max")
	            .queryParam("daily", "temperature_2m_min")
	            .queryParam("hourly", "temperature_2m")
	            .queryParam("daily", "apparent_temperature_min")
	            .queryParam("daily", "apparent_temperature_max")
	            .queryParam("hourly", "precipitation_probability")
	            .queryParam("hourly", "wind_speed_10m")
				.build())
				.retrieve()
				.bodyToMono(JsonNode.class)
				.onErrorResume(e -> {
					e.printStackTrace();
					return Mono.empty();
				})
				.block();
		

		Weather weather = new Weather();
		
		System.out.println(weather);
		
			weather.setTemperature_2m(json.get("hourly").get("temperature_2m").get(0).floatValue());
			weather.setApparent_temperature_max(json.get("daily").get("apparent_temperature_max").get(0).floatValue());
			weather.setApparent_temperature_min(json.get("daily").get("apparent_temperature_min").get(0).floatValue());
			weather.setPrecipitation_probability(json.get("hourly").get("precipitation_probability").get(0).floatValue());
			weather.setWind_speed_10m(json.get("hourly").get("wind_speed_10m").floatValue());
			weather.setTemperature_2m_max(json.get("daily").get("temperature_2m_max").floatValue());
			weather.setTemperature_2m_min(json.get("daily").get("temperature_2m_min").floatValue());
			
		weatherDao.save(weather);
	
		return weather;
	}

}
