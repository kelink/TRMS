package com.dummy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/index", "" })
	public String index() {
		logger.info("---�����û���½����-----");
		return "loginPage";
	}

	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public String userAuth(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		String account = request.getParameter("account").trim();
		String password = request.getParameter("password").trim();
		logger.debug("�˺�����---����" + account + password);
		DBUser user = userService.auth(account, password);
		if (user != null) {
			session.setAttribute("currentUser", user);
			return "success";
		} else {
			return "falis";
		}
	}

	@RequestMapping(value = { "/success", "" })
	public String success() {
		logger.info("---�����û���½����-----");
		return "login_success";
	}
}
