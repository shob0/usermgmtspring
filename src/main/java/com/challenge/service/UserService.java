package com.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.dao.UserRepository;
import com.challenge.model.User;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public boolean save(User user) {
		boolean transactionStatus = false;
		try {
			log.info("User object updation and database operation");
			//Create hash of the user passowrd before storing it in the DB 
			userRepository.save(user);
			transactionStatus = true;
			log.info("User creation completed");
			
		}catch (Exception e) {
			log.info("User could not be added to the database");
			log.error("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return transactionStatus;
	}
	
	public boolean update(User user) {
		boolean transactionStatus = false;

		try {
			User userObj = userRepository.getById(user.getUsername());
			if(userObj != null) {
				userRepository.save(user);
				transactionStatus = true;
			} else {
				log.info("User not found in the data base:: " + user.getUsername());
			}
		} catch (Exception e) {
			log.info("User details could not be updated in the database");
			log.error("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return transactionStatus;
	}
	
	public boolean delete(String username) {
		boolean userDeleteStatus = false;
		try {
			log.info("Deleting user with the username:: " +username);
			User user = userRepository.getById(username);
			
			if(user != null) {
				userRepository.delete(user);
				log.info("User is deleted from the database");
				userDeleteStatus = true;
			} else {
				log.info("User not found in the DB");
			}
		} catch (Exception e) {
			log.info("User could not be deleted from the database");
			log.error("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return userDeleteStatus;
	}
	
	public List<User> list() {
		List<User> userList = null;
		try {
			userList = userRepository.findAll();
		} catch (Exception e) {
			log.error("Could not fetch user list. " + e.getMessage());
			e.printStackTrace();
		}		
		return userList;

	}

}
