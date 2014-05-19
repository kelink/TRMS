package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

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

}
