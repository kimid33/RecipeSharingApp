package com.dollop.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dollop.app.model.User;
import com.dollop.app.service.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private IUserService userService;
	
	@GetMapping("/api/users/profile")
	public User findUserByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
		
		User user = userService.findUserByJwt(jwt);
		return user;
	}
}
