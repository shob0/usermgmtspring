package com.challenge.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenge.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
