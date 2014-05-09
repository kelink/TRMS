package com.dummy.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping(value="/login")
public class LoginController {
	//define a logger for debug
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@RequestMapping(value={"/index",""})
	public String index(){
		logger.info("---进入userAuth-----");
		return "login_index";
	}
	public String userAuth(){
		logger.info("---进入userAuth-----");	
		return "loginPage";	
	}
}
