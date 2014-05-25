package com.dummy.dao;

import java.util.List;

import com.dummy.domain.BlackList;

public interface BlackListDao {
	public BlackList getBlackList(int id);

	public List<BlackList> getAllBlackList();

	public void addBlackList(BlackList blackList);

	public boolean delBlackList(int id);

	public boolean updateBlackList(BlackList blackList);

	public BlackList getBlackListByTeam(int team_ID);
}
