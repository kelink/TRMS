package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// LC index
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("redirect:/room/list");
	}
	// All User
	@RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request) {
		request.setAttribute("userList", userService.getAllUser());
		return "user/index";
	}

	// Deny a page
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String denied(HttpServletRequest request) {
		return "common/deniedpage";
	}

}
