package com.spring.boardweb.controller;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.boardweb.HomeVO;
import com.spring.boardweb.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	//@Autowired HomeService 형태를 가진 객체들을 자동으로 주입해주는 어노테이션
	//Autowired는 의존성을 주입할 때 모양만 비교를 함
	//HomeService 모양으로 생성된 객체가 있는지 없는지 찾음
	//있으면 주입 없으면 에러발생
	//HomeService모양의 객체가 여러개가 존재하면 에러가 발생
	
	//@Qualifier는 의존성을 주입할 때 모양과 이름을 비교함
	//같은 모양의 객체가 여러개 존재할 때 이름으로 비교하여 의존성 주입한다.
	//@Qualifier("homeServiceImpl")
	@Autowired
	HomeService homeService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		
		model.addAttribute("hi", "Hello Spring" );
		
		//1. Session Scope
		//System.out.println(":session data: "+ session.getAttribute("hello"));
		
		return "home";
	}
	//화면단에서 전달되는 파라미터 받기
	//.1 @RequestParam 어노테이션을 이용하여 변수에 담아준다
	/*
	 * @RequestMapping(value = "test.do", method = RequestMethod.GET) public String
	 * testGet(@RequestParam("firstName") String firstName,@RequestParam("lastName")
	 * String lastName) { System.out.println("get요청 처리");
	 * System.out.println("get fisrtName: " + firstName);
	 * System.out.println("get lastName: " + lastName); return "home";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "test.do", method = RequestMethod.POST) public String
	 * testPost(@RequestParam("firstName") String
	 * firstName,@RequestParam("lastName") String lastName) {
	 * System.out.println("post요청 처리"); System.out.println("post fisrtName: " +
	 * firstName); System.out.println("post lastName: " + lastName); return "home";
	 * }
	 */
	
	//2. @RequestParam 어노테이션을 이용하여 Map에 할당
	/*
	 * @RequestMapping(value = "test.do", method = RequestMethod.GET) public String
	 * testGet(@RequestParam Map<String, Object> paramMap) {
	 * System.out.println("get요청 처리"); System.out.println("get fisrtName: " +
	 * paramMap.get("firstName")); System.out.println("get lastName: " +
	 * paramMap.get("lastName")); System.out.println("get age: " +
	 * paramMap.get("age")); System.out.println("get job: " + paramMap.get("job"));
	 * // /WEB-INF/views/home.jsp return "home";
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "test.do", method = RequestMethod.POST) public String
	 * testPost(@RequestParam Map<String, Object> paramMap) {
	 * System.out.println("post요청 처리"); System.out.println("post fisrtName: " +
	 * paramMap.get("lastName")); System.out.println("post lastName: " +
	 * paramMap.get("lastName")); System.out.println("post age: " +
	 * paramMap.get("age")); System.out.println("post job: " + paramMap.get("job"));
	 * // redirect: 주소로 바로 이동하도록 설정 가능 // redirect 시 주소도 이동할 주소로 변경됨 return
	 * "redirect:/"; }
	 */
	//3. Command 객체를 이용하여 파라미터 받기
	//   Command 객체: 함수의 매개변수안에 선언된 객체
	@RequestMapping(value = "test.do", method = RequestMethod.GET)
	public String testGet(HomeVO homeVO) {
		System.out.println("get요청 처리");
		System.out.println("get fisrtName: " + homeVO.getFirstName());
		System.out.println("get lastName: " + homeVO.getLastName());
		System.out.println("get age: " + homeVO.getAge());
		System.out.println("get job: " + homeVO.getJob());
		// /WEB-INF/views/home.jsp
		return "home";

	}
	
	@RequestMapping(value = "test.do", method = RequestMethod.POST)
	public String testPost(HomeVO homeVO) {
		System.out.println("post요청 처리");
		System.out.println("post fisrtName: " + homeVO.getFirstName());
		System.out.println("post lastName: " + homeVO.getLastName());
		System.out.println("post age: " + homeVO.getAge());
		System.out.println("post job: " + homeVO.getJob());
		// redirect: 주소로 바로 이동하도록 설정 가능
		// redirect 시 주소도 이동할 주소로 변경됨
		return "redirect:/";
	}
	
	@RequestMapping("/hello.do")
	public String hello(HttpSession session, Model model) {
		//1. Session Scope
		//session.setAttribute("hello", "12345");
		
		//2. Request Scope
		model.addAttribute("hello","1234");
		
		return "Hello";
	}
	@RequestMapping("getNameList.do")
	public String getNameList(Model model) {
		//DB로부터 이름 목록을 조회한 결과는 resultList라는 변수에 담아줌
		List<HomeVO> resultList = homeService.getNameList();
		
		model.addAttribute("nameList", resultList);
		
		for(int i=0; i<resultList.size(); i++) {
			System.out.println("firstName: " + resultList.get(i).getFirstName());
			System.out.println("lastName: " + resultList.get(i).getLastName());
		}
		
		return "getNameList";
	}
	@RequestMapping(value="insertName.do", produces="application/text; charset=UTF-8")
	public String insertName(HomeVO homeVO) {
		//insert, update, delete(쿼리) 실행 시 성공하면1, 싱패하면 0을 리턴
		homeService.insertName(homeVO);
		return "redirect:getNameList.do";
	}
	
	@RequestMapping(value="getName.do", produces="application/text; charset=UTF-8")
	//Command 객체로 파라미터를 선언할 시
	//HomeVO homeVO = new HomeVO(); 생성자 자동으로 실행
	//키 값과 이름이 동일한 변수의 setter 메소드를 호출하여 해당 변수에 값을 담아줌
	public String getName(HomeVO homeVO, Model model) {
		//homeVO.setFirstName("기천");
		//homeVO.setLastName("고");
		model.addAttribute("name", homeVO);
		return "getName";
	}
	
	@RequestMapping(value="updateName.do", produces="application/text; charset=UTF-8")
	public String updateName(@RequestParam Map<String, String> paramMap) {
		homeService.updateName(paramMap);
		return "redirect:getNameList.do";
	}
	
	@RequestMapping(value="deleteName.do", produces="application/text; charset=UTF-8")
	public String deleteName(HomeVO homeVO) {
		homeService.deleteName(homeVO);
		return "redirect:getNameList.do";
	}
}
