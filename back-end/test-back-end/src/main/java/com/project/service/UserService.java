package com.project.service;

import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserLoginResponseDto;
import com.project.dto.UserRegistrationDto;

public interface UserService {
	
	boolean Login(UserLoginRequestDto userLoginRequestDto);

	public UserLoginResponseDto issueToken(String email);
	
	void userRegistration(UserRegistrationDto userRegDto);
	
	boolean existsUser(String email);
}
