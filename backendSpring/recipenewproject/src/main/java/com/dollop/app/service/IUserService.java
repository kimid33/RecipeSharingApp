package com.dollop.app.service;

import com.dollop.app.model.User;

public interface IUserService {
	public User findUserById(Long userId)throws Exception;
	
	public User findUserByJwt(String jwt)throws Exception;
}
