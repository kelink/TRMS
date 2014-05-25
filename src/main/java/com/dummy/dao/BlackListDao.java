package com.dummy.dao;

import java.util.List;

import com.dummy.domain.BlackList;

public interface BlackListDao {
	public BlackList getBlackList(int id);

	public List<BlackListDao> getAllBlackList();

	public void addBlackList(BlackListDao blackList);

	public boolean delBlackList(int id);

	public boolean updateBlackList(BlackListDao blackList);

}
