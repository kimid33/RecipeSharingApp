package com.dollop.app.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dollop.app.config.JwtProvider;
import com.dollop.app.model.User;
import com.dollop.app.repository.UserRepository;
import com.dollop.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> opt = userRepository.findById(userId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else
		{
			throw new Exception("user not found with id " +userId);
		}
	}

	@Override
	public User findUserByJwt(String jwt) throws Exception {
		// TODO Auto-generated method stub
		String email = jwtProvider.getEmailFromJwtToken(jwt);
		System.out.println(jwt);
		if(email==null)
		{
			throw new Exception("provide valid jwt token...");
		}
		
		User user = userRepository.findByEmail(email);
		
		if(user==null)
		{
			throw new Exception("user not found with email "+email);
		}
		return user;
	}

}
