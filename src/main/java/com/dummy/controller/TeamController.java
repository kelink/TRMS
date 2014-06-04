package com.dummy.controller;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Printer;
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
import com.dummy.serviceImpl.DepartmentServiceImpl;

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
	@RequestMapping(value = { "/", "", "/index" })
	public ModelAndView index() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("admin/userManager", map);
	}

	// get the user and the team that he/she manager
	
//	@RequestMapping(value = { "/getDetail" })
//	public void getDetial(PrintWriter writer,
//			HttpSession session,
//			String searchWord) {
//         System.out.println(searchWord+"-------------------------------------------");
//         writer.write("{\"a\":\""+searchWord+"\"}");
//         writer.flush();
//         writer.close();
//	}

	//lc管理页面初始化
	@RequestMapping("getBoot")
	public void getBoot(PrintWriter writer){
		String result="";
		List<Department> departments=departmentService.getAllDepartment();
		Iterator iterator=departments.iterator();
		result+="{\"result\":[";
		int n=0;
		while(iterator.hasNext())
		{
			
			Department departmentItem=(Department)iterator.next();
			int department_ID=departmentItem.getDepartment_ID();
			System.out.println(department_ID);//输出departmentId
			Department department=departmentService.getDepartment(department_ID);
			List<DBUser> userList = userService.getUserByDepartment(department_ID);
			
			
			
			for (DBUser dbUser : userList) {
				System.out.println(dbUser.getUser_ID());//输出userId
				
				///////////取得user对象///////////////
				if(n==0)
				{
				
					result+="{";
				}
				else {
					result+=",{";
				}
				result+="\"departmentName\":\""+department.getDepartmentName()+"\",";
				result+="\"lcAccount\":\""+dbUser.getAccount()+"\",";
				result+="\"tele\":\""+dbUser.getTele()+"\",";
				
				
				
				List<Team> teams = teamService.getTeamByUserDepartment(
						dbUser.getUser_ID(), department_ID);//取出该department下某个user对应的team
				Iterator iter=teams.iterator();
				
				///////////////取得该department下user对象对应的多个team///////////////
				result+="\"teams\":";
				result+="[";
				int k=0;
				while(iter.hasNext())
				{
					
					Team team=(Team)iter.next();
					System.out.println(team.getTeamName());
					if(k==0)
					{
						result+="\""+team.getTeamName()+"\"";
					}
					else {
						result+=",\""+team.getTeamName()+"\"";
					}
					
					k++;
				}
				result+="]";
				result+="}";
				n++;
			}
			
	        
		}
		result+="]}";
		System.out.println(result);
		writer.write(result);
		writer.flush();
		writer.close();
	}
	
	@RequestMapping(value = { "/getDetail" })
	public void getDetial(PrintWriter writer,String searchWord,
			HttpSession session) {
		 String result="";
	
	
	
		
		if(searchWord=="")
		{
			List<Department> departments=departmentService.getAllDepartment();
			Iterator iterator=departments.iterator();
			result+="{\"result\":[";
			int n=0;
			while(iterator.hasNext())
			{
				
				Department departmentItem=(Department)iterator.next();
				int department_ID=departmentItem.getDepartment_ID();
				System.out.println(department_ID);//输出departmentId
				Department department=departmentService.getDepartment(department_ID);
				List<DBUser> userList = userService.getUserByDepartment(department_ID);
				
				
				
				for (DBUser dbUser : userList) {
					System.out.println(dbUser.getUser_ID());//输出userId
					
					///////////取得user对象///////////////
					if(n==0)
					{
					
						result+="{";
					}
					else {
						result+=",{";
					}
					result+="\"departmentName\":\""+department.getDepartmentName()+"\",";
					result+="\"lcAccount\":\""+dbUser.getAccount()+"\",";
					result+="\"tele\":\""+dbUser.getTele()+"\",";
					
					
					
					List<Team> teams = teamService.getTeamByUserDepartment(
							dbUser.getUser_ID(), department_ID);//取出该department下某个user对应的team
					Iterator iter=teams.iterator();
					
					///////////////取得该department下user对象对应的多个team///////////////
					result+="\"teams\":";
					result+="[";
					int k=0;
					while(iter.hasNext())
					{
						
						Team team=(Team)iter.next();
						System.out.println(team.getTeamName());
						if(k==0)
						{
							result+="\""+team.getTeamName()+"\"";
						}
						else {
							result+=",\""+team.getTeamName()+"\"";
						}
						
						k++;
					}
					result+="]";
					result+="}";
					n++;
				}
				
		        
			}
			result+="]}";
		}
		else{
			int department_ID=Integer.parseInt(searchWord);
			System.out.println(department_ID);//输出departmentId
			Department department=departmentService.getDepartment(department_ID);
			List<DBUser> userList = userService.getUserByDepartment(department_ID);
			
			result+="{\"result\":[";
			int i=0;
			for (DBUser dbUser : userList) {
				System.out.println(dbUser.getUser_ID());//输出userId
				
				///////////取得user对象///////////////
				if(i==0)
				{
				
					result+="{";
				}
				else {
					result+=",{";
				}
				result+="\"departmentName\":\""+department.getDepartmentName()+"\",";
				result+="\"lcAccount\":\""+dbUser.getAccount()+"\",";
				result+="\"tele\":\""+dbUser.getTele()+"\",";
				
				
				
				List<Team> teams = teamService.getTeamByUserDepartment(
						dbUser.getUser_ID(), department_ID);//取出该department下某个user对应的team
				Iterator iter=teams.iterator();
				
				///////////////取得该department下user对象对应的多个team///////////////
				result+="\"teams\":";
				result+="[";
				int k=0;
				while(iter.hasNext())
				{
					
					Team team=(Team)iter.next();
					System.out.println(team.getTeamName());
					if(k==0)
					{
						result+="\""+team.getTeamName()+"\"";
					}
					else {
						result+=",\""+team.getTeamName()+"\"";
					}
					
					k++;
				}
				result+="]";
				result+="}";
	            i++;
			}
			
	        result+="]}";
		}
		

        //writer.write("{\"c\":[{\"a\":[{\"b\":[\"ljj\"]}]}]}");
        System.out.println(result);
		writer.write(result);
		writer.flush();
        writer.close();
	}
	
//	@RequestMapping(value = { "/getDetail" })
//	public ModelAndView getDetial(
//			HttpSession session,
//			@RequestParam(value = "department_ID", required = true) int department_ID) {
//		System.out.println(department_ID);
//		HashMap<String, DBUser> userMap = new HashMap<String, DBUser>();
//		HashMap<String, List<Team>> teamMap = new HashMap<String, List<Team>>();
//
//		List<DBUser> userList = userService.getUserByDepartment(department_ID);
//		for (DBUser dbUser : userList) {
//			userMap.put(String.valueOf(dbUser.getUser_ID()), dbUser);
//			List<Team> teams = teamService.getTeamByUserDepartment(
//					dbUser.getUser_ID(), department_ID);
//			teamMap.put(String.valueOf(dbUser.getUser_ID()), teams);
//		}
//		ModelMap map = new ModelMap();
//		map.addAttribute("userMap", userMap);//每个user_id对应dbuser对象
//		map.addAttribute("teamMap", teamMap);//每个user_id对应一个teams list包含多个team
//		System.out.println(map);
//		return new ModelAndView("admin/userManagerForm", map);
//	}


	// 添加LC （对每一个部门，都只能选择本部门的team ，且只能为本部门里面的user 角色为LC 的用户添加）
	@RequestMapping(value = { "/getUserAddForm" })
	public ModelAndView getUserAddForm() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("admin/userAddForm", map);
	}

	// Ajax to get user of department
	@RequestMapping(value = { "/getDepartmentAllCommontUser" })
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
	@RequestMapping(value = { "/getDepartmentAllteam" })
	public @ResponseBody String getDepartmentAllteam(
			@RequestParam(value = "department_ID", required = true) int department_ID) {

		System.out.println("getDepartmentAllteam 中department_ID----------->"
				+ department_ID);
		List<JSONObject> teamList = teamService
				.getTeamsByDepartment(department_ID);
		System.out.println(teamList);
		return teamList.toString();
	}

	// add LC from team
	@RequestMapping(value = { "/add" })
	public ModelAndView add(
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

	// delete LC from team
	@RequestMapping(value = { "/delete" })
	public @ResponseBody String delete(
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
