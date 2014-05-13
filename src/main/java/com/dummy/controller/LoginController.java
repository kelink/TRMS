package com.dummy.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dummy.service.UserService;



@Controller
@RequestMapping(value="/login")
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private UserService userService;
	
	@RequestMapping(value={"/index",""})
	public String index(){
		logger.info("---进入用户界面-----");
		return "index";
	}
	public String userAuth(HttpServletRequest request,HttpServletResponse response){
		logger.info("---进入userAuth-----");	
		String account=request.getParameter("account").trim();
		String password=request.getParameter("password").trim();
		
		logger.info(password+account);
		return "success";	
	}
}
