package com.spring.boardweb.service.board;

import java.util.List;
import java.util.Map;

import com.spring.boardweb.BoardVO;
import com.spring.boardweb.Criteria;

public interface BoardService {

	void insertBoard(BoardVO boardVO);

	List<BoardVO> getBoardList(Map<String, String> keyword, Criteria cri);

	BoardVO getBoard(int boardSeq);

	void updateBoardCnt(int boardSeq);

	void updateBoard(BoardVO boardVO);

	void deleteBoard(int boardSeq);

	int getBoardCnt(Map<String, String> keyword);

	/* List<BoardVO> getBoardListSearch(Map<String, String> keyword); */

}
