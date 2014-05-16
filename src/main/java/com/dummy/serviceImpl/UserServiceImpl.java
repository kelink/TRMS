package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

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

}
