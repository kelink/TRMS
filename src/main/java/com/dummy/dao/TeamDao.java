package com.dummy.dao;

import java.util.List;

import com.dummy.domain.Team;

public interface TeamDao {
	public Team getTeam(int id);

	public List<Team> getAllTeam();

	public void addTeam(Team team);

	public boolean delTeam(int id);

	public boolean updateTeam(Team team);

	public List<Team> getTeamsByDepartment(int department_ID);

	public List<Team> getTeamByUserDepartment(int user_ID, int partment_ID);

	public boolean delTeamByUser(int user_ID);

	public List<Team> getTeamByUser(int user_ID);
}
