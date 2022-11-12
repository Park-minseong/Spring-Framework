package com.spring.boardweb.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/join.do")
	public String join() {
		return"user/join";
	}
	
	@RequestMapping("/login.do")
	public String login() {
		return"user/login";
	}
}
