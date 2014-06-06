package com.dummy.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dummy.dao.TeamDao;
import com.dummy.dao.UserDao;
import com.dummy.domain.Team;
import com.dummy.service.TeamService;

@Service(value = "teamService")
public class TeamServiceImpl implements TeamService {

	@Resource(name = "teamDao")
	private TeamDao teamDao;

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public Team getTeam(int id) {
		return teamDao.getTeam(id);
	}

	@Override
	public List<Team> getAllTeam() {
		return teamDao.getAllTeam();
	}

	@Override
	public void addTeam(Team team) {
		teamDao.addTeam(team);
	}

	@Override
	public boolean delTeam(int id) {
		return teamDao.delTeam(id);
	}

	@Override
	public boolean updateTeam(Team team) {
		return teamDao.updateTeam(team);
	}

	@Override
	public List<JSONObject> getTeamsByDepartment(int department_ID) {
		List<Team> teams = teamDao.getTeamsByDepartment(department_ID);
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		for (Team team : teams) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("team_ID", String.valueOf(team.getTeam_ID()));
			map.put("department_ID", String.valueOf(team.getDepartment_ID()));
			map.put("teamName", team.getTeamName());
			map.put("user_ID", String.valueOf(team.getUser_ID()));
			map.put("user_Account", userDao.getUser(team.getUser_ID())
					.getAccount());
			JSONObject object = new JSONObject(map);
			result.add(object);
		}
		return result;
	}

	@Override
	public List<Team> getTeamByUserDepartment(int user_ID, int partment_ID) {

		return teamDao.getTeamByUserDepartment(user_ID, partment_ID);
	}

	@Override
	public boolean delTeamByUser(int user_ID) {
		return teamDao.delTeamByUser(user_ID);
	}

	@Override
	public List<Team> getTeamByUser(int user_ID) {
		return teamDao.getTeamByUser(user_ID);
	}

	@Override
	public List<Team> getTeamsByDepartmentToObject(int department_ID) {
		return teamDao.getTeamsByDepartment(department_ID);
	}
}
