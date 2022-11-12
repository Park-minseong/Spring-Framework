package com.spring.boardweb.service.user;

import com.spring.boardweb.UserVO;

public interface UserService {

	void join(UserVO userVO);

	int idCheck(String userID);

	void join2(UserVO userVO);

	int idCheck2(String userId);

	int pwCheck(UserVO userVO);

	UserVO login(UserVO userVO);

}
