package com.project.service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.UserDao;
import com.project.dto.UserLoginRequestDto;
import com.project.dto.UserLoginResponseDto;
import com.project.dto.UserRegistrationDto;
import com.project.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void userRegistration(UserRegistrationDto userRegDto) {
		
		String sha256hex = DigestUtils.sha256Hex(userRegDto.getPassword());
		userRegDto.setPassword(sha256hex);
		
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userRegDto, User.class);
		userDao.save(user);
	}
	
	@Override
	public boolean existsUser(String email) {

		return userDao.existsByEmail(email);
	}
	
	@Override
	public boolean Login(UserLoginRequestDto userLoginRequestDto) {

		User user = new User();
		
		user.setEmail(userLoginRequestDto.getEmail());
		user.setPassword(userLoginRequestDto.getPassword());
		
		String sha256hex = DigestUtils.sha256Hex(user.getPassword());
		
		User logUser = userDao.findByEmailAndPassword(user.getEmail(), sha256hex);
		
		return logUser != null ? true : false;
	}
	
	public UserLoginResponseDto issueToken(String email) {
		
		byte[] secret = "CiaoCiao159753245Miryam8745214236521423".getBytes();
		
		Key key = Keys.hmacShaKeyFor(secret);
		
		User infoUser = userDao.findUserByEmail(email);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("name", infoUser.getName());
		map.put("lastname", infoUser.getLastname());
		map.put("email", email);
		
		Date creation = new Date();
		Date end = java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(15L));
		
		String tokenJwts = Jwts.builder()
				.setClaims(map)
				.setIssuer("http://localhost:8080")
				.setIssuedAt(creation)
				.setExpiration(end)
				.signWith(key)
				.compact();
		
		UserLoginResponseDto token = new UserLoginResponseDto();
		
		token.setToken(tokenJwts);
		token.setTokenCreationTime(creation);
		token.setTtl(end);
		
		return token;
	}

}
