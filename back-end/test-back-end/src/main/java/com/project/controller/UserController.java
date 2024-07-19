package com.project.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserRegistrationDto;
import com.project.service.UserService;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path ("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response userRegistration(@Valid @RequestBody UserRegistrationDto userRegDto) {
		
		try {
				if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}", userRegDto.getPassword())) {
					
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
				
				if(userService.existsUser(userRegDto.getEmail())) {
					return Response.status(Response.Status.BAD_REQUEST).build();
				}
					
				userService.userRegistration(userRegDto);
				return Response.status(Response.Status.OK).build();
				
			} catch (Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login (@RequestBody UserLoginRequestDto userLoginRequestDto) {
	  
	    try {
	    	
	    	if (userService.Login(userLoginRequestDto)) {
	        return Response.ok(userService.issueToken(userLoginRequestDto.getEmail())).build();
	      }
	
	    } catch (Exception e) {
	      return Response.status (Response.Status. BAD_REQUEST).build();
	    }
	
	    	return Response.status (Response.Status. BAD_REQUEST).build();
	  	}

}
