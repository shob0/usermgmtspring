package com.challenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.model.User;
import com.challenge.service.UserService;

import lombok.extern.slf4j.Slf4j;


@RestController @Slf4j
@RequestMapping("/users")
public class UserServiceImpl {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestBody User user) {
		
		//Input sanitization should be implemented
		
		log.info("User creation request started for user:: " + user.getUsername());
		log.debug("First Name:: " +  user.getFirstName());
		log.debug("Last Name:: " +  user.getLastName());
		log.debug("Contact no:: " +  user.getContactNo());
		log.debug("Email:: " +  user.getEmail());
		
		if(user.getUsername() == null) {
			return ResponseEntity.badRequest().body("Username is missing!");
		}
		if(user.getPassword() == null) {
			return ResponseEntity.badRequest().body("Password is missing!");
		}

		if(user.getFirstName() == null) {
			return ResponseEntity.badRequest().body("Firstname is missing!");
		}
		if(user.getLastName() == null) {
			return ResponseEntity.badRequest().body("Lastname is missing!");
		}
		if(user.getEmail() == null) {
			return ResponseEntity.badRequest().body("Email is missing!");
		}

		
		boolean userCreationStatus = userService.save(user);
		
		if(userCreationStatus) {
			return ResponseEntity.status(HttpStatus.CREATED).body("User created and added to the database");	
		} else {
			return ResponseEntity.internalServerError().body("User creation Failed!!!");
		}
		
		
	}
	
	@PostMapping("/updateuser")
	public void updateUser(User user) {

	}
	
	@DeleteMapping("/deleteuser")
	public ResponseEntity<String> deleteUser(@RequestBody String username) {
		log.info("Delete request started for username: " + username);
		boolean userDeleted = userService.delete(username);
		
		if(userDeleted) {
			return ResponseEntity.ok("User successfully deleted!!");
		} else {
			return ResponseEntity.ok("User could not be deleted!!");
		}
	}
	
	//Read User
	@GetMapping("/listusers")
	public List<User> listUser() {
		log.info("Fetching all users list");
		List<User> userList = userService.list();
		log.info("Fetch user list with size:: " + userList.size());
		return userList;
	}
	
	@GetMapping("/serverhealth")
	public String checkServerHealth() {
		log.info("Server pinging working fine!");
		return "Server is running Successfully!!";
	}

	
}
