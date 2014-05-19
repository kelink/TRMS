package com.dummy.dao;

import java.util.List;

import com.dummy.domain.Team;

public interface TeamDao {
	public Team getTeam(int id);

	public List<Team> getAllTeam();

	public void addTeam(Team team);

	public boolean delTeam(int id);

	public boolean updateTeam(Team team);
}
