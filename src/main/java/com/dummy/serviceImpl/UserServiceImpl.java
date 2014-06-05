package com.dummy.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dummy.dao.UserDao;
import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

	@Resource(name = "userDao")
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public DBUser getUser(int id) {
		return userDao.getUser(id);
	}

	@Override
	public List<DBUser> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(DBUser user) {
		userDao.addUser(user);
	}

	@Override
	public boolean delUser(int id) {
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(DBUser user) {
		return userDao.updateUser(user);
	}

	@Override
	public DBUser getAuthUser(String account, String password) {
		return userDao.getAuthUser(account, password);
	}

	@Override
	public DBUser getUserByAccount(String account) {
		return userDao.getUserByAccount(account);
	}

	@Override
	public List<DBUser> getUserByDepartment(int department_ID) {
		return userDao.getUserByDepartment(department_ID);
	}

	@Override
	public List<DBUser> getUserByRole(int roleType) {
		return userDao.getUserByRole(roleType);
	}

	@Override
	public List<JSONObject> getDepartmentUserByRole(int roleType,
			int department_ID) {
		List<DBUser> list = userDao.getDepartmentUserByRole(roleType,
				department_ID);
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		for (DBUser user : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("user_ID", String.valueOf(user.getUser_ID()));
			map.put("department_ID", String.valueOf(department_ID));
			map.put("account", user.getAccount());
			JSONObject object = new JSONObject(map);
			result.add(object);
		}
		return result;
	}

	@Override
	public int getUserRole(int user_ID) {
		DBUser user = userDao.getUser(user_ID);
		return user.getAccess();
	}
}
