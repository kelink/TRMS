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
		return new ModelAndView("admin/userManager", map);
	}

	// get the user and the team that he/she manager
	@RequestMapping(value = {"/getDetial"})
	public ModelAndView getDetial(
			HttpSession session,
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		System.out.println(department_ID);
		HashMap<String, DBUser> userMap = new HashMap<String, DBUser>();
		HashMap<String, List<Team>> teamMap = new HashMap<String, List<Team>>();

		List<DBUser> userList = userService.getUserByDepartment(department_ID);
		for (DBUser dbUser : userList) {
			userMap.put(String.valueOf(dbUser.getUser_ID()), dbUser);
			List<Team> teams = teamService.getTeamByUserDepartment(
					dbUser.getUser_ID(), department_ID);
			teamMap.put(String.valueOf(dbUser.getUser_ID()), teams);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("userMap", userMap);
		map.addAttribute("teamMap", teamMap);
		System.out.println(map);
		return new ModelAndView("admin/userManagerForm", map);
	}

	// 添加LC （对每一个部门，都只能选择本部门的team ，且只能为本部门里面的user 角色为LC 的用户添加）
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

		System.out.println("getDepartmentAllteam 中department_ID----------->"
				+ department_ID);
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

	// delete LC for team
	@RequestMapping(value = {"/delete"})
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

}
