package com.spring.boardweb.controller.board;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.boardweb.BoardFileVO;
import com.spring.boardweb.BoardVO;
import com.spring.boardweb.Criteria;
import com.spring.boardweb.PageVO;
import com.spring.boardweb.UserVO;
import com.spring.boardweb.commons.FileUtils;
import com.spring.boardweb.service.board.BoardService;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired()
	BoardService boardService;
	
	@RequestMapping("/getBoardList.do")
	public String getBoardList(HttpSession session, Model model, @RequestParam Map<String, String> keyword, Criteria cri) {
		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}

		List<BoardVO> boardList = boardService.getBoardList(keyword,cri); //검색기능과 통합
		
		int total = boardService.getBoardCnt(keyword);
		
		for(BoardVO board:boardList) {
			System.out.println(board.toString());
		}
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker", new PageVO(cri,total));
		
		if(keyword.get("searchCondition") != null && !keyword.get("searchCondition").equals("")) {
				model.addAttribute("searchCondition",keyword.get("searchCondition"));
		}
		
		if(keyword.get("searchKeyword") != null && !keyword.get("searchKeyword").equals("")) {
			model.addAttribute("searchKeyword", keyword.get("searchKeyword"));
		}
		
		return "board/getBoardList";
	}

	@RequestMapping("/getBoard.do")
	public String getBoard(HttpSession session, @RequestParam int boardSeq,@RequestParam int pageNum, Model model ) {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}
		boardService.updateBoardCnt(boardSeq);
		
		BoardVO board = boardService.getBoard(boardSeq);
		List<BoardFileVO> fileList = boardService.getBoardFile(boardSeq);
		
		
		model.addAttribute("board",board);
		model.addAttribute("fileList", fileList);
		model.addAttribute("pageNum", pageNum);
		
		
		return "board/getBoard";
	}
	
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.GET)
	public String insertBoardView() {
		return "board/insertBoard";
	}

	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST)
	public String insertBoard(HttpSession session, BoardVO boardVO, HttpServletRequest request, MultipartHttpServletRequest multipartServletRequest) throws IOException {

		UserVO loginUser = (UserVO) session.getAttribute("loginUser");

		if (loginUser == null) {
			return "user/login";
		}
		
		int boardSeq = boardService.getNextBoardSeq();
		
		FileUtils fileUtils  = new FileUtils();
		
		//파일업로드 처리 및 속성 값들이 세팅된 BoardFileVO의 목록 리턴
		List<BoardFileVO> fileList = fileUtils.parseFileInfo(boardSeq, request, multipartServletRequest);
		
		boardService.insertBoard(boardVO);
		
		if(!CollectionUtils.isEmpty(fileList)) {
			boardService.insertBoardFile(fileList);
		}
		
		return "redirect:getBoardList.do";
	}
	
	
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO boardVO, HttpServletRequest request, MultipartHttpServletRequest multipartServletRequest)throws IOException {
		FileUtils fileUtils = new FileUtils();
		
		 int boardSeq = boardVO.getBoardSeq();
		 
		 List<BoardFileVO> fileList = fileUtils.parseFileInfo(boardSeq, request, multipartServletRequest);
		
		 if(!CollectionUtils.isEmpty(fileList)) {
			 boardService.insertBoardFile(fileList);
		 }
		 
		boardService.updateBoard(boardVO);
		
		return "redirect:getBoard.do?boardSeq="+boardVO.getBoardSeq();
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(@RequestParam int boardSeq) {		
		boardService.deleteBoard(boardSeq);
		return "redirect:getBoardList.do";
	}
	
	@RequestMapping("/fileDown.do")
	@ResponseBody
	public ResponseEntity<Resource> fileDown(@RequestParam String fileName, HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/") + "/upload/";
		
		Resource resource = new FileSystemResource(path + fileName);
		
		String resourceName = resource.getFilename();
		
		HttpHeaders header = new HttpHeaders();
		
		try {
			header.add("Content-Disposition","attachment; fileName=" + new String(resourceName.getBytes("UTF-8"), "ISO-8859-1"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Resource>(resource, header, HttpStatus.OK);
	}
	
	@RequestMapping("/deleteBoardFile.do")
	@ResponseBody
	public void deleteBoardFile(BoardFileVO boardFileVO) {
		boardService.deleteBoardFile(boardFileVO);
		System.out.println("jkfhgkhjd");
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
	
