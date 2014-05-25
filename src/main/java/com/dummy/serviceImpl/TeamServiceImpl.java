package com.dummy.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dummy.dao.TeamDao;
import com.dummy.domain.Team;
import com.dummy.service.TeamService;

@Service(value = "teamService")
public class TeamServiceImpl implements TeamService {

	@Resource(name = "teamDao")
	private TeamDao teamDao;

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
			JSONObject object = new JSONObject(map);
			result.add(object);
		}
		return result;
	}
}
