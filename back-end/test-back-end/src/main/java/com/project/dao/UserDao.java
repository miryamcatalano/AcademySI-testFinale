package com.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.project.model.User;

public interface UserDao extends CrudRepository<User, Integer> {

	User findByEmailAndPassword(String email, String sha256hex);
	
	User findUserByEmail(String email);
	
	boolean existsByEmail(String email);
}
