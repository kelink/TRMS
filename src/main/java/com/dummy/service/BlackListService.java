package com.dummy.service;

import java.util.List;

import com.dummy.dao.BlackListDao;
import com.dummy.domain.BlackList;

public interface BlackListService {
	public BlackList getBlackList(int id);

	public List<BlackListDao> getAllBlackList();

	public void addBlackList(BlackListDao blackList);

	public boolean delBlackList(int id);

	public boolean updateBlackList(BlackListDao blackList);

}
