package com.dummy.controller;

import java.util.HashMap;
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
	@RequestMapping(value = {"/getDetail"})
	public ModelAndView getDetial(
			HttpSession session,
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		System.out.println(department_ID);
		HashMap<String, DBUser> userMap = new HashMap<String, DBUser>();
		HashMap<String, List<Team>> teamMap = new HashMap<String, List<Team>>();
		List<DBUser> userList = userService.getUserByDepartment(department_ID);
		// 获取team对应的department
		HashMap<Integer, Department> departments = new HashMap<Integer, Department>();

		for (DBUser dbUser : userList) {
			userMap.put(String.valueOf(dbUser.getUser_ID()), dbUser);
			List<Team> teams = teamService.getTeamByUserDepartment(
					dbUser.getUser_ID(), department_ID);
			for (Team team : teams) {
				int departent_ID = team.getDepartment_ID();
				Department department = departmentService
						.getDepartment(departent_ID);
				departments.put(team.getTeam_ID(), department);
			}
			teamMap.put(String.valueOf(dbUser.getUser_ID()), teams);
		}

		ModelMap map = new ModelMap();
		map.addAttribute("userMap", userMap);// 每个user_id对应dbuser对象
		map.addAttribute("teamMap", teamMap);// 每个user_id对应一个teams list包含多个team
		map.addAttribute("departments", departments);
		System.out.println(map);
		return new ModelAndView("admin/userManagerForm", map);
	}
	// 添加LC(选择部门里面的LC用户)
	@RequestMapping(value = {"/getUserAddForm"})
	public ModelAndView getUserAddForm() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("admin/userAddForm", map);
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

	// add LC for team
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
	// 获取team对应的LC user
	// @RequestMapping(value = {"/getTeamUser"})
	// public ModelAndView getTeamUser(
	// HttpSession session,
	// @RequestParam(value = "department_ID", required = true) int
	// department_ID) {
	// HashMap<Integer, DBUser> userMap = new HashMap<Integer, DBUser>();
	// List<Team> teamList = teamService
	// .getTeamsByDepartmentToObject(department_ID);
	// for (Team team : teamList) {
	// int user_ID = team.getUser_ID();
	// DBUser user = userService.getUser(user_ID);
	// userMap.put(team.getTeam_ID(), user);
	// }
	//
	// ModelMap map = new ModelMap();
	// map.addAttribute("teamList", teamList);// department 下面的team
	// map.addAttribute("userMap", userMap);// 每个team_ID对应dbuser对象
	// System.out.println(map);
	// return new ModelAndView("admin/userManagerForm", map);
	// }
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
}
