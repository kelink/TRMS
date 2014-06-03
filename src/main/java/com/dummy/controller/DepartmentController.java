package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.Department;
import com.dummy.service.DepartmentService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@Resource(name = "teamService")
	private TeamService teamService;

	@Resource(name = "userService")
	private UserService userService;

	// index
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("department/index", map);
	}

	// add
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpSession session) {
		String departmentName = request.getParameter("departmentName").trim();
		if ((departmentName != null) && (!departmentName.equals(""))) {
			Department department = new Department();
			department.setDepartmentName(departmentName);
			departmentService.addDepartment(department);
			return new ModelAndView("redirect:department/index");
		} else {
			return new ModelAndView("error");
		}
	}

	// update
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpSession session) {
		String departmentName = request.getParameter("departmentName").trim();
		if ((departmentName != null) && (!departmentName.equals(""))) {
			Department department = new Department();
			department.setDepartmentName(departmentName);
			departmentService.updateDepartment(department);
			return new ModelAndView("redirect:department/index");
		} else {
			return new ModelAndView("error");
		}
	}

	// delete
	@RequestMapping(value = "/delete")
	public ModelAndView delete(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			HttpSession session) {
		if (departmentService.delDepartment(department_ID)) {
			return new ModelAndView("redirect:department/index");
		} else {
			return new ModelAndView("error");
		}
	}

	// get the addDepaetment form
	@RequestMapping(value = "/c")
	public ModelAndView getAddForm() {
		return new ModelAndView("department/getAddForm");
	}
}
