package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.domain.Room;
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
		logger.info("进入 /user/home_TA 下，currentUser" + currentUser);
		List<Room> rooms = getAllRooms();
		ModelMap map = new ModelMap();
		map.addAttribute("rooms", rooms);
		return new ModelAndView("admin/home_TA", map);
	}

	@RequestMapping(value = "/home_LC")
	public ModelAndView home_LC(
			@ModelAttribute("currentUser") DBUser currentUser) {
		logger.info("进入 /user/home_TC 下，currentUser" + currentUser);
		List<Room> rooms = getAllRooms();
		ModelMap map = new ModelMap();
		map.addAttribute("rooms", rooms);
		return new ModelAndView("user/home_LC", map);
	}

	private List<Room> getAllRooms() {
		return roomService.getAllRoom();
	}
}
