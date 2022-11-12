package com.spring.boardweb.service.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardweb.HomeVO;

//DAO: Data Access Object
//DB�� ���� �����ϴ� ��ü
@Repository
public class HomeDAO {
	//DB�� �����ؾ� �ϹǷ�
	//SqlSessionTemplate�� �ϳ� �����´�.
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public List<HomeVO> getNameList(){
		//ù ��° �Ű������δ� ������ namespace.������id
		//�� ��° �Ű������δ� �������� ���� �Ķ���͵�
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
