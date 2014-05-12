package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dummy.service.UserService;
@Controller
public class UserController {
	@Resource(name="userService")
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request){
		request.setAttribute("userList", userService.getAllUser());	
		return "index";
	
	}
}
