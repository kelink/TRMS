package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.common.C;
import com.dummy.domain.DBUser;
import com.dummy.domain.Department;
import com.dummy.domain.Room;
import com.dummy.service.BlackListService;
import com.dummy.service.DepartmentService;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "teamService")
	private TeamService teamService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@Resource(name = "blackListService")
	private BlackListService blackListService;

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	// index page
	@RequestMapping(value = "/index")
	public ModelAndView index(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "7") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		int recordCount = roomService.getAllRoom().size();
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		ModelMap map = new ModelMap();
		map.addAttribute("recordCount", recordCount);
		map.addAttribute("pageCount", pageCount);
		List<Room> rooms = roomService.getFreeRooms();
		map.addAttribute("rooms", rooms);
		return new ModelAndView("admin/index", map);
	}
    
	@RequestMapping("/listUser")
	public ModelAndView listUser() {
		List<DBUser> commonUsers = userService
				.getUserByRole(C.DB.DEFAULT_ROLE_LC);
		DBUser a=commonUsers.get(0);
	
		List<DBUser> admins = userService.getUserByRole(C.DB.DEFAULT_ROLE_TA);
		ModelMap map = new ModelMap();
		map.addAttribute("commonUsers", commonUsers);
		map.addAttribute("admins", admins);
		return new ModelAndView("admin/listUser", map);
	}

	@RequestMapping("/deleteCommonUser")
	public @ResponseBody String deleteCommonUser(
			@RequestParam(value = "user_ID", required = true) int user_ID) {
		if (userService.getUserRole(user_ID) == C.DB.DEFAULT_ROLE_LC) {
			boolean isOk = userService.delUser(user_ID);
			if (isOk) {
				return "delete success";
			} else {
				return "delete fail";
			}
		} else {
			return "You have not right to operator";
		}
	}

	// get department information
	@RequestMapping("/getUserDepartment")
	public @ResponseBody String getUserDepartment(
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		Department department = departmentService.getDepartment(department_ID);
		if (department != null) {
			return department.getDepartmentName() + "";
		} else {
			return null;
		}
	}
}
