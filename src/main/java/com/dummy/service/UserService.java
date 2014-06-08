package com.dummy.service;

import java.util.List;

import org.json.JSONObject;

import com.dummy.domain.DBUser;

public interface UserService {
	public DBUser getUser(int id);

	public List<DBUser> getAllUser();

	public void addUser(DBUser user);

	public boolean delUser(int id);

	public boolean updateUser(DBUser user);

	public DBUser getAuthUser(String account, String password);

	public DBUser getUserByAccount(String account);

	public List<DBUser> getUserByDepartment(int department_ID);

	public List<DBUser> getUserByRole(int roleType);

	public List<JSONObject> getDepartmentUserByRole(int roleType,
			int department_ID);

	public List<DBUser> getDepartmentUserByRoleToObject(int roleType,
			int department_ID);

	public int getUserRole(int user_ID);
}
