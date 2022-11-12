package com.spring.boardweb.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boardweb.UserVO;
import com.spring.boardweb.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public void join(UserVO userVO) {
		userDAO.join(userVO);
		
	}

	@Override
	public int idCheck(String userId) {
		return userDAO.idCheck(userId);
	}

	@Override
	public void join2(UserVO userVO) {
		userDAO.join2(userVO);
		
	}

	@Override
	public int idCheck2(String userId) {
		return userDAO.idCheck2(userId);
	}

	@Override
	public int pwCheck(UserVO userVO) {
		return userDAO.pwCheck(userVO);
	}

	@Override
	public UserVO login(UserVO userVO) {
		return userDAO.login(userVO);
	}



}
