package com.dummy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.dao.UserDao;
import com.dummy.domain.DBUser;
import com.dummy.service.UserService;
@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Autowired
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

	// authority the User
	@Override
	public DBUser auth(String account, String password) {
		return userDao.getAuthUser(account, password);
	}

}
