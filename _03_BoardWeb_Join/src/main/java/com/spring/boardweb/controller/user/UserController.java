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
	 * //a��ũ�� Ÿ�� �� ���� Get����̹Ƿ� ȭ���̵��� get������� �޾Ƽ� ȭ���̵� ����
	 * 
	 * @RequestMapping(value="/join.do", method=RequestMethod.GET) public String
	 * joinView() { return"user/join"; }
	 * 
	 * //form���� ������ �޾ƿ� ���� POST�������� ���⶧���� POST�� �޾Ƽ� ���� ����
	 * 
	 * @RequestMapping(value="/join.do", method=RequestMethod.POST) public String
	 * join() { return"user/login"; }
	 */
	//�� ������ �ϳ��� �޼ҵ�� ����
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
	
	//��й�ȣ ��ȣȭ ����
	public String encrypt(String userPw) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    StringBuilder builder = new StringBuilder();
        md.update(userPw.getBytes());
        for(byte b: md.digest()) {
        	builder.append(String.format("%02x",b));
        }
        return builder.toString();
	}
	//��й�ȣ ��ȣȭ ��
	

	
	@RequestMapping("/login.do")
	public String login() {
		return"user/login";
	}
	
	@RequestMapping("/idCheck.do")
	//ViewResolver�� Ÿ�ԵǸ� ResponseBody�� HTML ������ ���� ȭ���� ����
	//ViewResolver�� ���¿�� ��ȯ �� ��ü��  ResponseBody�� ������� @ResponseBody ���
	@ResponseBody
	public String idCheck(UserVO userVO) throws JsonProcessingException {
		//Json ��ü�� ��ȯ���ִ� Ŭ���� ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int resultIdCheck = userService.idCheck(userVO.getUserId());
		jsonMap.put("resultIdCheck", resultIdCheck);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
		System.out.println("jsonString==== " + json);
		return json;
	}
	
	@RequestMapping("/idCheck2.do")
	//ViewResolver�� Ÿ�ԵǸ� ResponseBody�� HTML ������ ���� ȭ���� ����
	//ViewResolver�� ���¿�� ��ȯ �� ��ü��  ResponseBody�� ������� @ResponseBody ���
	@ResponseBody
	public String idCheck2(UserVO userVO) throws JsonProcessingException {
		//Json ��ü�� ��ȯ���ִ� Ŭ���� ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int resultIdCheck = userService.idCheck2(userVO.getUserId());
		jsonMap.put("resultIdCheck", resultIdCheck);
		
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonMap);
		System.out.println("jsonString==== " + json);
		return json;
	}
}
