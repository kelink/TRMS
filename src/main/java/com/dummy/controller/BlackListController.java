package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
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
import com.dummy.service.BlackListService;
import com.dummy.service.DepartmentService;
import com.dummy.service.TeamService;

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
	@RequestMapping(value = {"/", "", "/index"})
	public ModelAndView index() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("blacklist/index", map);
	}

	// check the teams info
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
	public @ResponseBody String getblacklistByTeam(
			@RequestParam(value = "team_ID", required = true) int team_ID,
			HttpSession session) {
		JSONObject blacklist = blackListService.getBlackListByTeam(team_ID);
		if (blacklist == null) {
			return "not in blacklist";
		}
		return blacklist.toString();
	}
	// add a blacklist
	@RequestMapping(value = "/add")
	public @ResponseBody String add(
			@RequestParam(value = "team_ID", required = true) int team_ID,
			@RequestParam(value = "reason", required = false, defaultValue = "") String reason,
			HttpSession session) {
		BlackList blackList = new BlackList();
		blackList.setReason(reason);
		blackList.setTeam_ID(team_ID);
		blackListService.addBlackList(blackList);
		return "success";
	}

	// delete a blacklist
	@RequestMapping(value = "/delete")
	public @ResponseBody String delete(
			@RequestParam(value = "bl_ID", required = true) int bl_ID,
			HttpSession session) {
		if (blackListService.delBlackList(bl_ID) == true) {
			return "success";
		} else {
			return "fail";
		}
	}

}
