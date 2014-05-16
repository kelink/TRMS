package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Resource(name = "userService")
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request) {
		request.setAttribute("userList", userService.getAllUser());
		return "user/index";

	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(HttpServletRequest request) {
		return "common/deniedpage";
	}

	@RequestMapping(value = "/home_TA")
	public ModelAndView home_TA(
			@ModelAttribute("currentUser") DBUser currentUser) {
		logger.info("���� /user/home_TA �£�currentUser" + currentUser);
		return new ModelAndView("admin/home_TA");
	}

	@RequestMapping(value = "/home_LC")
	public ModelAndView home_LC(
			@ModelAttribute("currentUser") DBUser currentUser) {
		logger.info("���� /user/home_TC �£�currentUser" + currentUser);
		return new ModelAndView("user/home_LC");
	}
}
