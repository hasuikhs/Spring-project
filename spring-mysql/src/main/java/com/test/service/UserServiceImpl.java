package com.test.service;

import org.springframework.stereotype.Service;

import com.test.domain.UserVO;
import com.test.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

	private UserMapper mapper;
	
	@Override
	public UserVO login(UserVO user) {
		return mapper.login(user);
	}

}
