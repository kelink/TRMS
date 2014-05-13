package com.dummy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request) {
		request.setAttribute("userList", userService.getAllUser());
		return "index";

	}

	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(HttpServletRequest request) {
		return "denied";
	}

	@RequestMapping(value = "/home_TA")
	public ModelAndView home_TA() {
		return new ModelAndView("home_TA");
	}

	@RequestMapping(value = "/home_LC")
	public ModelAndView home_LC() {
		return new ModelAndView("home_LC");
	}
}
