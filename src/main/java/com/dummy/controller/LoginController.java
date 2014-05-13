package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping(value={"/index",""})
	public String index(){
		logger.info("---进入用户界面-----");
		return "loginPage";
	}
	@RequestMapping(value = "/userAuth", method = RequestMethod.POST)
	public String userAuth(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String account = request.getParameter("account").trim();
		String password = request.getParameter("password").trim();
		logger.debug("账号密码---》》" + account + password);
		System.out.println(account+""+account);
		DBUser user = userService.auth(account, password);
		if (user != null) {
			session.setAttribute("currentUser", user);
			return "login_success";
		} else {
			return "falis";
		}
	}

	@RequestMapping(value = { "/success"})
	public String success() {
		logger.info("---进入用户登陆界面-----");
		return "login_success";
	}
}
