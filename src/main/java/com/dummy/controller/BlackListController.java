package com.dummy.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.BlackList;
import com.dummy.domain.Department;
import com.dummy.domain.Team;
import com.dummy.service.BlackListService;
import com.dummy.service.DepartmentService;
import com.dummy.service.TeamService;

/*****
 * only for the administrator
 */

@Controller
@RequestMapping("/blacklist")
public class BlackListController {

	@Resource(name = "blackListService")
	private BlackListService blackListService;

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	@Resource(name = "teamService")
	private TeamService teamService;

	// index page of blacklist management
	@RequestMapping(value = {"", "/", "/index"})
	public ModelAndView index() {
		return new ModelAndView("blacklist/index");
	}
	// list the blacklist
	@RequestMapping(value = {"/list"})
	public ModelAndView list() {
		List<BlackList> blacklists = blackListService.getAllBlackList();
		// 1.get team by blacklist ID
		HashMap<Integer, Team> teams = new HashMap<Integer, Team>();
		for (BlackList blackList : blacklists) {
			Team team = teamService.getTeam(blackList.getTeam_ID());
			teams.put(blackList.getBl_ID(), team);
		}
		// get department information by teams
		HashMap<Integer, Department> departments = new HashMap<Integer, Department>();
		for (Integer index : teams.keySet()) {
			int departent_ID = teams.get(index).getDepartment_ID();
			Department department = departmentService
					.getDepartment(departent_ID);
			departments.put(index, department);
		}
		ModelMap map = new ModelMap();
		map.addAttribute("blacklists", blacklists);
		map.addAttribute("teams", teams);
		map.addAttribute("departments", departments);
		return new ModelAndView("blacklist/list", map);
	}
	// get the teams information
	@RequestMapping(value = "/getTeamsByDepartment")
	public @ResponseBody String getTeamsByDepartment(
			@RequestParam(value = "department_ID", required = true) int department_ID,
			HttpSession session) {
		List<JSONObject> teams = teamService
				.getTeamsByDepartment(department_ID);
		if (teams == null) {
			return "no teams build";
		}
		return teams.toString();
	}

	// get the black through team
	@RequestMapping(value = "/getReasonByTeam")
	public @ResponseBody String getReasonByTeam(
			@RequestParam(value = "team_ID", required = true) int team_ID,
			HttpSession session) {
		JSONObject blacklist = blackListService.getBlackListByTeam(team_ID);
		if (blacklist == null) {
			return "not in blacklist";
		}
		return blacklist.toString();
	}

	// get AddForm
	@RequestMapping(value = "/getAddForm")
	public ModelAndView getAddForm() {
		ModelMap map = new ModelMap();
		List<Department> departments = departmentService.getAllDepartment();
		map.addAttribute("departments", departments);
		return new ModelAndView("blacklist/form", map);
	}
	// get check Page
	@RequestMapping(value = "/getCheckPage")
	public ModelAndView getCheckPage() {
		ModelMap map = new ModelMap();
		List<Department> departments = departmentService.getAllDepartment();
		map.addAttribute("departments", departments);
		return new ModelAndView("blacklist/check", map);
	}
	// add a blacklist
	@RequestMapping(value = "/add")
	public @ResponseBody String add(
			@RequestParam(value = "team_ID", required = true) int team_ID,
			@RequestParam(value = "reason", required = false, defaultValue = "") String reason,
			HttpSession session) {
		// 判断是否当前team已经存在blacklist中
		if (!blackListService.isInBlackaList(team_ID)) {
			BlackList blackList = new BlackList();
			blackList.setReason(reason);
			blackList.setTeam_ID(team_ID);
			blackListService.addBlackList(blackList);
			return "success";
		} else {
			return "curren team is in the blacklist,do not repet to operate";
		}

	}

	// delete a blacklist
	@RequestMapping(value = "/delete")
	public @ResponseBody String delete(
			@RequestParam(value = "bl_ID", required = true) int bl_ID,
			HttpSession session) {
		if (blackListService.delBlackList(bl_ID) == true) {
			return "delete success";
		} else {
			return "delete fail";
		}
	}

	// delete multiple blacklists
	@RequestMapping(value = "/deleteMutiBlacklist")
	public @ResponseBody String deleteMutiBlacklist(HttpServletRequest request,
			HttpSession session) {
		String[] blackLists = request.getParameterValues("checkbox");
		String message = null;
		for (int i = 0; i < blackLists.length; i++) {
			message = delete(Integer.parseInt(blackLists[i]), session);
		}
		return message;
	}

	// update a blacklist reason
	@RequestMapping(value = "/update")
	public @ResponseBody String update(
			@RequestParam(value = "bl_ID", required = true) int bl_ID,
			HttpServletRequest request, HttpSession session) {
		BlackList blackList = blackListService.getBlackList(bl_ID);
		String reason = request.getParameter("reason");
		blackList.setReason(reason);
		if (blackListService.updateBlackList(blackList) == true) {
			return "'update success";
		} else {
			return "update fail";
		}
	}
}
