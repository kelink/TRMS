package com.dummy.dao;

import java.util.List;

public interface BlackList {
	public BlackList getBlackList(int id);

	public List<BlackList> getAllBlackList();

	public void addBlackList(BlackList blackList);

	public boolean delBlackList(int id);

	public boolean updateBlackList(BlackList blackList);

}
