package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.common.C;
import com.dummy.domain.DBUser;
import com.dummy.domain.Department;
import com.dummy.domain.Role;
import com.dummy.service.DepartmentService;
import com.dummy.service.RoleService;
import com.dummy.service.UserService;

@SessionAttributes({"currentUser"})
@Controller
@RequestMapping("/profile")
public class profileController {

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@Resource(name = "roleService")
	private RoleService roleService;

	@RequestMapping(value = "/modify")
	public ModelAndView modify(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		ModelMap map = new ModelMap();
		// get current User info
		System.out.println("user departmentID---------------------"
				+ currentUser.getDept_ID());
		Department department = departmentService.getDepartment(currentUser
				.getDept_ID());
		Role role = roleService.getRole(currentUser.getAccess());
		String gender = null;
		if (currentUser.getGender() == 0) {
			gender = "female";
		}
		if (currentUser.getGender() == 1) {
			gender = "male";
		}
		map.addAttribute("currentUser", currentUser);
		map.addAttribute("department", department);
		map.addAttribute("role", role);
		map.addAttribute("gender", gender);
		if (currentUser.getAccess() == C.DB.DEFAULT_ROLE_TA) {
			return new ModelAndView("admin/profile", map);
		} else {
			return new ModelAndView("user/profile", map);
		}

	}
	// check old password
	@RequestMapping(value = "/checkOldPwd")
	public @ResponseBody String checkOldPwd(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		String oldpassword = request.getParameter("oldPassword").trim();
		if (oldpassword.equals(currentUser.getPassword())) {
			return "old password auth";
		} else {
			return "old password fail to auth";
		}
	}

	// Edit your password
	@RequestMapping(value = "/changepwd")
	public ModelAndView changepwd(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		String newPassword = request.getParameter("newPassword").trim();
		currentUser.setPassword(newPassword);
		userService.updateUser(currentUser);
		// 使得当前会话失效，重新登录(后面新增)
		return new ModelAndView("redirect:/login/index");
	}

	// edit user information
	@RequestMapping(value = "/getEditUserForm")
	public ModelAndView getEditUserForm(
			@ModelAttribute("currentUser") DBUser currentUser) {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("currentUser", currentUser);
		map.addAttribute("departments", departments);
		return new ModelAndView("user/edit", map);
	}
	@RequestMapping(value = "/updateUser")
	public ModelAndView updateUser(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		return null;
	}
}
