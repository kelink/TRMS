package com.dummy.service;

import java.util.List;

import com.dummy.domain.DBUser;

public interface UserService {
	public DBUser getUser(int id);

	public List<DBUser> getAllUser();

	public void addUser(DBUser user);

	public boolean delUser(int id);

	public boolean updateUser(DBUser user);

	public DBUser auth(String account, String password);
}
