package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.Department;
import com.dummy.service.DepartmentService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/team")
public class TeamController {
	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@Resource(name = "userService")
	private UserService userService;

	@Resource(name = "teamService")
	private TeamService teamService;

	// user manager index page
	@RequestMapping(value = {"/", "", "/index"})
	public ModelAndView index() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("admin/userManager", map);
	}

}
