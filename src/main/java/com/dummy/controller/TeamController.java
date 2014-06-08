package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.common.C;
import com.dummy.domain.DBUser;
import com.dummy.domain.Department;
import com.dummy.domain.Team;
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

	/*********************************
	 * for administrator
	 *********************************/
	// user manager index page
	@RequestMapping(value = {"/", "", "/index"})
	public ModelAndView index() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("team/index", map);
	}

	// 获取每个用户管理的team
	@RequestMapping(value = {"/getTeamByUser"})
	public ModelAndView getTeamByUser(
			HttpSession session,
			@RequestParam(value = "user_ID", required = true) int user_ID,
			@RequestParam(value = "department_ID", required = true) int department_ID) {

		List<Team> teams = teamService.getTeamByUser(user_ID);
		Department department = departmentService.getDepartment(department_ID);
		ModelMap map = new ModelMap();
		map.addAttribute("teams", teams);// 每个user_id对应dbuser对象
		map.addAttribute("department", department);// 每个user_id对应一个teams
		return new ModelAndView("admin/userManagerForm", map);
	}
	// 获取为用户add team的表单
	@RequestMapping(value = {"/getUserAddTeamForm"})
	public ModelAndView getUserAddTeamForm() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("admin/userAddTeamForm", map);
	}

	// Ajax to get user of department
	@RequestMapping(value = {"/getDepartmentAllCommontUser"})
	public @ResponseBody String getDepartmentAllCommontUser(
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		System.out
				.println("getDepartmentAllCommontUser 中department_ID----------->"
						+ department_ID);
		List<JSONObject> list = userService.getDepartmentUserByRole(
				C.DB.DEFAULT_ROLE_LC, department_ID);
		return list.toString();
	}

	// Ajax to get team of department
	@RequestMapping(value = {"/getDepartmentAllteam"})
	public @ResponseBody String getDepartmentAllteam(
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		List<JSONObject> teamList = teamService
				.getTeamsByDepartment(department_ID);
		System.out.println(teamList);
		return teamList.toString();
	}

	// 同时支持添加多个 LC
	@RequestMapping(value = {"/addUser"})
	public ModelAndView addUser(
			HttpSession session,
			@RequestParam(value = "department_ID", required = true) int department_ID,
			@RequestParam(value = "user_ID", required = true) int user_ID,
			@RequestParam(value = "teams", required = false, defaultValue = "") List<Integer> teams) {
		DBUser addUser = userService.getUser(user_ID);
		addUser.setDept_ID(department_ID);
		userService.updateUser(addUser);
		for (Integer team_ID : teams) {
			Team team = teamService.getTeam(team_ID);
			Team newTeam = new Team();
			newTeam.setDepartment_ID(department_ID);
			newTeam.setTeamName(team.getTeamName());
			newTeam.setUser_ID(addUser.getUser_ID());
			teamService.addTeam(newTeam);
		}
		return null;
	}

	// delete LC for team
	@RequestMapping(value = {"/deleteUser"})
	public @ResponseBody String deleteUser(
			@RequestParam(value = "user_ID", required = true) int user_ID,
			HttpSession session) {
		boolean isOK = teamService.delTeamByUser(user_ID);
		if (isOK) {
			return "delete success!";
		} else {
			return "delete fail";
		}
	}
	// delete team
	@RequestMapping(value = {"/deleteTeam"})
	public @ResponseBody String deleteTeam(
			@RequestParam(value = "team_ID", required = true) int team_ID,
			HttpSession session) {
		boolean isOK = teamService.delTeam(team_ID);
		if (isOK) {
			return "delete success!";
		} else {
			return "delete fail";
		}
	}
	// add team for user
	@RequestMapping(value = {"/addTeamForUser"})
	public @ResponseBody String addTeamForUser(
			@RequestParam(value = "users", required = true) int users,
			@RequestParam(value = "teams", required = true) int teams,
			HttpSession session) {
		Team team = teamService.getTeam(teams);
		if (team == null) {
			return "team does not exist";
		} else {
			team.setUser_ID(users);
			if (teamService.updateTeam(team)) {
				return "add team for user success";
			} else {
				return "fail";
			}
		}
	}
}
