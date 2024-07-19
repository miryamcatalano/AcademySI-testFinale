package com.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "[a-zA-Z\\èàùìò]{1,50}", message="nome non ammesso")
	@Column(name = "name")
	private String name;

	@Pattern(regexp = "[a-zA-Z\\èàùìò]{1,50}", message="cognome non ammesso")
	@Column(name = "lastname")
	private String lastname;
	
	@Pattern(regexp = "[a-zA-Z\\èàùìò]{1,50}", message="città non ammessa")
	@Column(name = "city")
	private String city;
	
	@Pattern(regexp = "[A-z0-9\\.\\+_-]+@[A-z0-9\\._-]+\\.[A-z]{2,8}", message="mail non valida")
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
