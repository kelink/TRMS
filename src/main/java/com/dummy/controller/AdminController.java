package com.dummy.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Resource(name = "userService")
	private UserService userService;

	// index page
	@RequestMapping(value = "/index")
	public ModelAndView index(@ModelAttribute("currentUser") DBUser currentUser) {
		return new ModelAndView("admin/index");
	}
}
