package com.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue
	private long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String contactNo;
	private String role;

	
	
}
