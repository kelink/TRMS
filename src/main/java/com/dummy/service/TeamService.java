package com.dummy.service;

import java.util.List;

import org.json.JSONObject;

import com.dummy.domain.Team;

public interface TeamService {
	public Team getTeam(int id);

	public List<Team> getAllTeam();

	public void addTeam(Team team);

	public boolean delTeam(int id);

	public boolean updateTeam(Team team);

	public List<JSONObject> getTeamsByDepartment(int department_ID);

	public List<Team> getTeamByUserDepartment(int user_ID, int department_ID);

	public List<Team> getTeamsByDepartmentToObject(int department_ID);

	public boolean delTeamByUser(int user_ID);

	public List<Team> getTeamByUser(int user_ID);

	public boolean isOKToAdd(Team team);
	public boolean isTeamExist(Team team);
}
