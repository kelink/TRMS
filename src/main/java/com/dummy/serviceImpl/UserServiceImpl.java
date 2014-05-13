package com.dummy.serviceImpl;

import java.util.List;

import com.dummy.dao.UserDao;
import com.dummy.domain.User;
import com.dummy.service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUser(int id) {	
		return userDao.getUser(id);
	}

	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);		
	}

	@Override
	public boolean delUser(int id) {
		return userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return userDao.updateUser(user);			
	}

	//authority the User
	@Override
	public User auth(String account, String password) {	
		return userDao.auth(account,password);
	}
	
}
