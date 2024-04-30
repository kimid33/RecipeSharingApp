package com.dollop.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dollop.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
