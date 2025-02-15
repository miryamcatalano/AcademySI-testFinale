package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.model.Geocoding;
import com.project.model.Weather;
import com.project.service.WeatherService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path ("/weather")
public class WeatherController {
	
	@Autowired
	private WeatherService weatherService;
	
	@GET
	@Path("/city")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCountries(@QueryParam("city") String city) {
		
		try {
			
			List<Geocoding> listCountry = weatherService.search(city);
			return Response.status(Response.Status.OK).entity(listCountry).build();
			
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWeather(@QueryParam("latitude") double latitude, @QueryParam("longitude") double longitude) {
				
		try {
			System.out.println(latitude);
			System.out.println(longitude);
			Weather weather = weatherService.getWeather(latitude, longitude);
			return Response.status(Response.Status.OK).entity(weather).build();
			
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

}
