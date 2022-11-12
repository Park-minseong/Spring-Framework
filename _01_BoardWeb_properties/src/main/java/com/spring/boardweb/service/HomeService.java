package com.spring.boardweb.service;

import java.util.List;
import java.util.Map;

import com.spring.boardweb.HomeVO;

public interface HomeService {
	List<HomeVO> getNameList();
	
	void insertName(HomeVO homeVO);
	void updateName(Map<String, String> paramMap);
	void deleteName(HomeVO homeVO);
}
