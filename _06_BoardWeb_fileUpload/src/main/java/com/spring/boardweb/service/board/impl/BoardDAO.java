package com.spring.boardweb.service.board.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.boardweb.BoardFileVO;
import com.spring.boardweb.BoardVO;
import com.spring.boardweb.Criteria;

@Repository
public class BoardDAO {
	@Autowired
	SqlSessionTemplate mybatis;
	
	public void insertBoard(BoardVO boardVO) {
		mybatis.insert("BoardDAO.insertBoard", boardVO);
	}

	public List<BoardVO> getBoardList(Map<String, String> keyword, Criteria cri) {
		Map<String, Object> pMap = new HashMap<String, Object>();
		pMap.put("boardSearch", keyword);
		cri.setStartNum((cri.getPageNum()-1) * cri.getAmount());
		pMap.put("cri", cri);
		return mybatis.selectList("BoardDAO.getBoardList",pMap);
	}

	public BoardVO getBoard(int boardSeq) {
		return mybatis.selectOne("BoardDAO.getBoard", boardSeq);
	}

	public void updateBoardCnt(int boardSeq) {
		mybatis.update("BoardDAO.updateBoardCnt",boardSeq);
		
	}

	public void updateBoard(BoardVO boardVO) {
		mybatis.update("BoardDAO.updateBoard", boardVO);
		
	}

	public void deleteBoard(int boardSeq) {
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setBoardSeq(boardSeq);
		
		mybatis.delete("BoardDAO.deleteBoardFile", boardFileVO);
		
		mybatis.delete("BoardDAO.deleteBoard", boardSeq);
		
		mybatis.update("BoardDAO.updateBoardSeq", boardSeq);

			
	}

	public int getBoardCnt(Map<String, String> keyword) {
		return mybatis.selectOne("BoardDAO.getBoardCnt", keyword);
	}
	/*
	 * public List<BoardVO> getBoardListSearch(Map<String, String> keyword) { return
	 * mybatis.selectList("BoardDAO.searchBoardList", keyword); }
	 */

	public int getNextBoardSeq() {
		// TODO Auto-generated method stub
		return mybatis.selectOne("BoardDAO.getNextBoardSeq");
	}

	public void insertBoardFile(List<BoardFileVO> fileList) {
		//List를 insert하는 방법
		//1. for문을 사용하여 List.size만큼 insert문 호출
		/*
		 * for(BoardFileVO boardFile : fileList) {
		 * mybatis.insert("BoardDAO.insertBoardFile", boardFile); }
		 */
		//2. mybatis의 <foreach> 태그 사용
		mybatis.insert("BoardDAO.insertBoardFile", fileList);
		
	}

	public List<BoardFileVO> getBoardFile(int boardSeq) {
		return mybatis.selectList("BoardDAO.getBoardFile", boardSeq);
	}

	public void deleteBoardFile(BoardFileVO boardFileVO) {
		mybatis.delete("BoardDAO.deleteBoardFile", boardFileVO);
		mybatis.update("BoardDAO.updateBoardFileSeq", boardFileVO);
		
	}

}
