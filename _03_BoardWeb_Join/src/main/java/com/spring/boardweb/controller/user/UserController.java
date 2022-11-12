package com.spring.boardweb.controller.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boardweb.UserVO;
import com.spring.boardweb.service.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
		@Autowired
		UserService userService;
	
	/*
	 * //a링크를 타고 올 때는 Get방식이므로 화면이동은 get방식으로 받아서 화면이동 실행
	 * 
	 * @RequestMapping(value="/join.do", method=RequestMethod.GET) public String
	 * joinView() { return"user/join"; }
	 * 
	 * //form에서 정보를 받아올 때는 POST형식으로 오기때문에 POST로 받아서 가입 실행
	 * 
	 * @RequestMapping(value="/join.do", method=RequestMethod.POST) public String
	 * join() { return"user/login"; }
	 */
	//위 내용을 하나의 메소드롤 구현
	@RequestMapping("/join.do")
	public String join(UserVO userVO) {
		if(userVO.getUserId() == null || userVO.getUserId().equals("")) {
			return "user/join";
		}
		userService.join(userVO);
		return "user/login";
	}
	
	@RequestMapping("/join2.do")
	public String join2(UserVO userVO) throws NoSuchAlgorithmException {
		if(userVO.getUserId() == null || userVO.getUserId().equals("")) {
			return "user/join2";
		}
		userVO.setUserPw(encrypt(userVO.getUserPw()));
		userService.join2(userVO);
		return "user/login";
	}
	
	//비밀번호 암호화 시작
	public String encrypt(String userPw) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    StringBuilder builder = new StringBuilder();
        md.update(userPw.getBytes());
        for(byte b: md.digest()) {
        	builder.append(String.format("%02x",b));
        }
        return builder.toString();
	}
	//비밀번호 암호화 끝
	

	
	@RequestMapping("/login.do")
	public String login() {
		return"user/login";
	}
	
	@RequestMapping("/idCheck.do")
	//ViewResolver를 타게되면 ResponseBody를 HTML 구조로 만드어서 화면을 리턴
	//ViewResolver를 안태우고 반환 값 자체를  ResponseBody로 만들려면 @ResponseBody 사용
	@ResponseBody
	public String idCheck(UserVO userVO) throws JsonProcessingException {
		//Json 객체로 변환해주는 클래스 ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int resultIdCheck = userService.idCheck(userVO.getUserId());
		jsonMap.put("resultIdCheck", resultIdCheck);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
		System.out.println("jsonString==== " + json);
		return json;
	}
	
	@RequestMapping("/idCheck2.do")
	//ViewResolver를 타게되면 ResponseBody를 HTML 구조로 만드어서 화면을 리턴
	//ViewResolver를 안태우고 반환 값 자체를  ResponseBody로 만들려면 @ResponseBody 사용
	@ResponseBody
	public String idCheck2(UserVO userVO) throws JsonProcessingException {
		//Json 객체로 변환해주는 클래스 ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int resultIdCheck = userService.idCheck2(userVO.getUserId());
		jsonMap.put("resultIdCheck", resultIdCheck);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
		System.out.println("jsonString==== " + json);
		return json;
	}
}
