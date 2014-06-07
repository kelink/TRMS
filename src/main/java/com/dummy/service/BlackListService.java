package com.dummy.service;

import java.util.List;

import org.json.JSONObject;

import com.dummy.domain.BlackList;

public interface BlackListService {
	public BlackList getBlackList(int id);

	public List<BlackList> getAllBlackList();

	public void addBlackList(BlackList blackList);

	public boolean delBlackList(int id);

	public boolean updateBlackList(BlackList blackList);

	public JSONObject getBlackListByTeam(int team_ID);

	public BlackList getBlackListByTeamToObject(int team_ID);

	public boolean isInBlackaList(int team_ID);

	public List<BlackList> getBlackListOnPage(int pageNum, int pageSize);
}
