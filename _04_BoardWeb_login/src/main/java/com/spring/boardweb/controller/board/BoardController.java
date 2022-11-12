package com.spring.boardweb.controller.board;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boardweb.BoardVO;
import com.spring.boardweb.UserVO;
import com.spring.boardweb.service.board.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired()
	BoardService boardService;
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(HttpSession session, Model model, @RequestParam Map<String, String> keyword) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}

		List<BoardVO> boardList = boardService.getBoardList(keyword); //검색기능과 통합
		
		for(BoardVO board:boardList) {
			System.out.println(board.toString());
		}
		
		model.addAttribute("boardList", boardList);
		
		if(keyword.get("searchCondition") != null && !keyword.get("searchCondition").equals("")) {
				model.addAttribute("searchCondition",keyword.get("searchCondition"));
		}
		
		return "board/getBoardList";
	}

	@RequestMapping("/getBoard.do")
	public String getBoard(HttpSession session, @RequestParam int boardSeq, Model model) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}
		boardService.updateBoardCnt(boardSeq);
		
		BoardVO board = boardService.getBoard(boardSeq);
		
		model.addAttribute("board",board);
		
		
		return "board/getBoard";
	}

	@RequestMapping("/insertBoard.do")
	public String insertBoard(HttpSession session, BoardVO boardVO) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}
			
		if(boardVO.getBoardTitle() == null || boardVO.getBoardTitle().equals("")) {
			
			return "board/insertBoard";
		}
		
		boardService.insertBoard(boardVO);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO boardVO) {

		boardService.updateBoard(boardVO);
		
		return "redirect:getBoard.do?boardSeq="+boardVO.getBoardSeq();
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(@RequestParam int boardSeq) {
		
		boardService.deleteBoard(boardSeq);
		return "redirect:getBoardList.do";
	}
	
	/*
	 * @RequestMapping("/getBoartListSearch.do") public String
	 * getBoardListSearch(Model model, @RequestParam Map<String, String> keyword) {
	 * List<BoardVO> searchBoardList = boardService.getBoardListSearch(keyword);
	 * 
	 * model.addAttribute("boardList", searchBoardList);
	 * 
	 * return"board/getBoardList"; }
	 */
}
	
