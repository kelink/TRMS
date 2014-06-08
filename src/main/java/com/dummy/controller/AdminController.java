package com.dummy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		List<Room> rooms = roomService.getFreeRooms();
		int recordCount = rooms.size();
		int pageCount = (recordCount + pageSize - 1) / pageSize;

		ModelMap map = new ModelMap();
		map.addAttribute("recordCount", recordCount);
		map.addAttribute("pageCount", pageCount);
		map.addAttribute("rooms", rooms);
		return new ModelAndView("admin/index", map);
	}

	// 查询用户
	@RequestMapping("/listUser")
	public ModelAndView listUser(
			@RequestParam(value = "department_ID", required = false, defaultValue = "0") int department_ID) {
		// List<DBUser> commonUsers = userService
		// .getUserByRole(C.DB.DEFAULT_ROLE_LC);
		// List<DBUser> admins =
		// userService.getUserByRole(C.DB.DEFAULT_ROLE_TA);
		// List<Department> departments = departmentService.getAllDepartment();
		// ModelMap map = new ModelMap();
		// map.addAttribute("commonUsers", commonUsers);
		// map.addAttribute("admins", admins);
		// map.addAttribute("departments", departments);
		// return new ModelAndView("admin/listUser", map);

		ModelMap map = new ModelMap();
		// 默认全部的部门
		if (department_ID == 0) {
			List<DBUser> commonUsers = userService
					.getUserByRole(C.DB.DEFAULT_ROLE_LC);
			List<Department> departments = departmentService.getAllDepartment();
			map.addAttribute("commonUsers", commonUsers);
			map.addAttribute("departments", departments);
		} else {
			List<DBUser> commonUsers = userService
					.getDepartmentUserByRoleToObject(C.DB.DEFAULT_ROLE_LC,
							department_ID);
			List<Department> departments = new ArrayList<Department>();
			departments.add(departmentService.getDepartment(department_ID));
			map.addAttribute("commonUsers", commonUsers);
			map.addAttribute("departments", departments);
		}
		return new ModelAndView("admin/listUser", map);
	}
	// 删除用户
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

	// 增加用户
	@RequestMapping("/addUser")
	public @ResponseBody String addUser(HttpSession session,
			HttpServletRequest request) {
		String teleStr = request.getParameter("tele");
		String accountStr = request.getParameter("account");
		String accessStr = request.getParameter("access");
		String genderStr = request.getParameter("gender");
		String passwordStr = request.getParameter("password");
		String dept_IDStr = request.getParameter("dept_ID");
		DBUser user = new DBUser();
		if (teleStr.length() > 0) {
			user.setTele(teleStr.trim());
		}
		if (accountStr.length() > 0) {
			if (userService.isUserExit(accountStr.trim()))
				return "the account exist,Please use other name";
			user.setAccount(accountStr.trim());
		}
		if (accessStr.length() > 0) {
			user.setAccess(Integer.parseInt(accessStr.trim()));
		}
		if (genderStr.length() > 0) {
			user.setGender(Integer.parseInt(genderStr.trim()));
		}
		if (passwordStr.length() > 0) {
			user.setPassword(passwordStr.trim());
		}
		if (dept_IDStr.length() > 0) {
			user.setDept_ID(Integer.parseInt(dept_IDStr.trim()));
		}
		try {
			userService.addUser(user);
			return "<script>alert(\"Add User Success!\");history.back();window.top.location.reload();</script>";
		} catch (Exception e) {
			return "fail operation";
		}
	}
	// ajax 获取部门的名字
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
	// 更新用户
	public @ResponseBody String updateUser(HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "user_ID", required = true) int user_ID) {
		String teleStr = request.getParameter("tele");
		String accountStr = request.getParameter("account");
		String accessStr = request.getParameter("access");
		String genderStr = request.getParameter("gender");
		String passwordStr = request.getParameter("password");
		String dept_IDStr = request.getParameter("dept_ID");
		DBUser user = new DBUser();
		if (teleStr.length() > 0) {
			user.setTele(teleStr.trim());
		}
		if (accountStr.length() > 0) {
			user.setAccount(accountStr.trim());
		}
		if (accessStr.length() > 0) {
			user.setAccess(Integer.parseInt(accessStr.trim()));
		}
		if (genderStr.length() > 0) {
			user.setGender(Integer.parseInt(genderStr.trim()));
		}
		if (passwordStr.length() > 0) {
			user.setPassword(passwordStr.trim());
		}
		if (dept_IDStr.length() > 0) {
			user.setDept_ID(Integer.parseInt(dept_IDStr.trim()));
		}
		if (userService.updateUser(user)) {
			return "update Success";
		} else {
			return "fail operation";
		}
	}
}
