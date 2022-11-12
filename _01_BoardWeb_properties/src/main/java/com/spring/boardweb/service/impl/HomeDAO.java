package com.spring.boardweb.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardweb.HomeVO;

//DAO: Data Access Object
//DB에 직접 접근하는 객체
@Repository
public class HomeDAO {
	//DB에 접근해야 하므로
	//SqlSessionTemplate을 하나 꺼내온다.
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<HomeVO> getNameList(){
		//첫 번째 매개변수로는 매퍼의 namespace.쿼리문id
		//두 번째 매개변수로는 쿼리에서 사용될 파라미터들
		return mybatis.selectList("HomeDAO.getNameList");
	}
	
	public void insertName(HomeVO homeVO) {
		mybatis.insert("HomeDAO.insertName", homeVO);
	}
	
	public void updateName(Map<String, String> paramMap) {
		mybatis.update("HomeDAO.updateName", paramMap);
	}
	
	public void deleteName(HomeVO homeVO) {
		mybatis.delete("HomeDAO.deleteName", homeVO);
	}
}
