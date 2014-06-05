package com.dummy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.common.C;
import com.dummy.domain.DBUser;
import com.dummy.domain.Department;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.DepartmentService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;

/**
 * only for the administrator
 */

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

	// index of DepartmentManager
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		return new ModelAndView("departmentmanager/index");
	}
	// list departments
	@RequestMapping(value = "/departmentList")
	public ModelAndView departmentList() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("departmentmanager/departmentList", map);
	}
	// list teams of a department
	@RequestMapping(value = "/teamList")
	public ModelAndView teamList(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			HttpSession session) {
		List<Team> departments = teamService
				.getTeamsByDepartmentToObject(department_ID);
		HashMap<Integer, ArrayList<Object>> result = new HashMap<Integer, ArrayList<Object>>();
		for (Team team : departments) {
			ArrayList<Object> temp = new ArrayList<Object>();
			if (team.getUser_ID() == C.DB.DEFAULT_TEAM_NOUSER) {
				continue;
			}
			DBUser user = userService.getUser(team.getUser_ID());
			temp.add(team);
			temp.add(user);
			result.put(team.getTeam_ID(), temp);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("result", result);
		return new ModelAndView("departmentmanager/teamList", map);
	}
	// list room
	@RequestMapping(value = "/roomList")
	public ModelAndView roomList() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("departmentmanager/roomList", map);
	}
	// list user
	@RequestMapping(value = "/userList")
	public ModelAndView userList() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("departmentmanager/userList", map);
	}

	// add Department
	@RequestMapping(value = "/addDepartment")
	public ModelAndView addDepartment(HttpServletRequest request,
			HttpSession session) {
		String departmentName = request.getParameter("departmentName").trim();
		if ((departmentName != null) && (!departmentName.equals(""))) {
			Department department = new Department();
			department.setDepartmentName(departmentName);
			departmentService.addDepartment(department);
			return new ModelAndView("redirect:/department/index");
		} else {
			return new ModelAndView("error");
		}
	}
	// get updateForm 等待部门管理
	@RequestMapping(value = "/getUpdateForm")
	public ModelAndView getUpdateForm(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			HttpSession session) {
		// team of current department
		List<Team> teams = teamService
				.getTeamsByDepartmentToObject(department_ID);
		// room of current department
		List<Room> rooms = roomService
				.getRoomsBydepartmentToObject(department_ID);
		ModelMap map = new ModelMap();
		map.addAttribute("teams", teams);
		map.addAttribute("rooms", rooms);
		return new ModelAndView("department/updateForm", map);
	}
	// update Department
	@RequestMapping(value = "/updateDepartment")
	public ModelAndView updateDepartment(HttpServletRequest request,
			HttpSession session) {
		String departmentName = request.getParameter("departmentName").trim();
		if ((departmentName != null) && (!departmentName.equals(""))) {
			Department department = new Department();
			department.setDepartmentName(departmentName);
			departmentService.updateDepartment(department);
			return new ModelAndView("redirect:/department/index");
		} else {
			return new ModelAndView("error");
		}
	}

	// delete Department
	@RequestMapping(value = "/delete")
	public ModelAndView deleteDepartment(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			HttpSession session) {
		if (departmentService.delDepartment(department_ID)) {
			return new ModelAndView("redirect:/department/index");
		} else {
			return new ModelAndView("error");
		}
	}

	// get the addDepartment form
	@RequestMapping(value = "/getAddForm")
	public ModelAndView getAddForm() {
		return new ModelAndView("department/getAddForm");
	}

	// add team for department
	public ModelAndView addTeamForDepartment(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			@RequestParam(value = "teamName", required = true) String teamName,
			HttpSession session) {
		Team team = new Team();
		team.setDepartment_ID(department_ID);
		team.setTeamName(teamName);
		team.setUser_ID(C.DB.DEFAULT_TEAM_NOUSER);
		teamService.addTeam(team);
		return null;
	}
	// delete Team for department

}
