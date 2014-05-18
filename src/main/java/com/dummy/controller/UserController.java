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

import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.DBUser;
import com.dummy.domain.Room;
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

	@RequestMapping(value = "/calendar")
	public ModelAndView calendar(HttpServletRequest request) {
		// System.out.println(request.getParameter("room"));
		String result = getAllReservationInfo();
		ModelMap map = new ModelMap();
		map.addAttribute("result", result);
		System.out.println(result);
		return new ModelAndView("user/calendar", map);
	}

	// var
	// bookedDate=[{year:2014,month:1,day:20,department:"USER_EXPERIENCE",lc:"xx",usage:"xx",usertele:"xx"},{year:2014,month:1,day:23,department:"xx",lc:"xx",usage:"xx",usertele:"xx"}];//这里是传入一个存放键值对的对象的数组，说明哪些天被订了。Key有year,month,day，department,lc,usage,usertele。格式参照上面例子。

	public String getAllReservationInfo() {
		int room_ID = 1;
		// String result = "{\"name\":\"" + "test" + "\"}";
		// 拼接json输出
		@SuppressWarnings("unused")
		String result = "";
		@SuppressWarnings("unchecked")
		List<CalanderDataDomain> list = reservationService
				.getAllReservationInfo(room_ID);
		StringBuilder builder = new StringBuilder();

		builder.append("[");
		for (CalanderDataDomain calanderDataDomain : list) {
			builder.append("{");
			String temp = calanderDataDomain.getApplied_END_Date().toString();
			String[] data = temp.split("-");
			builder.append("year:" + data[0] + ",");
			builder.append("month:" + data[1] + ",");
			builder.append("day:" + data[2] + ",");
			builder.append("department:" + "\""
					+ calanderDataDomain.getTeamName() + "\",");
			builder.append("lc:" + "\"" + calanderDataDomain.getUser_ID()
					+ "\",");
			builder.append("usage:" + "\"" + calanderDataDomain.getPurpose()
					+ "\",");
			builder.append("usertele:" + "\"" + calanderDataDomain.getEmail()
					+ "\"");
			builder.append("}");
		}
		builder.append("]");
		System.out.println(builder.toString());
		return builder.toString();
	}
}
