package com.dummy.dao;

import java.util.List;

import com.dummy.domain.DBUser;

public interface UserDao {
	public DBUser getUser(int id);

	public List<DBUser> getAllUser();

	public void addUser(DBUser user);

	public boolean delUser(int id);

	public boolean updateUser(DBUser user);

	public DBUser getUserByAccount(String account);

	public DBUser getAuthUser(String account, String password);
}
