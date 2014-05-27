package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dummy.controller.LoginController;
import com.dummy.dao.UserDao;
import com.dummy.domain.DBUser;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	// ???session
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public DBUser getUser(int id) {
		String hql = "from DBUser u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (DBUser) query.uniqueResult();
	}

	@Override
	public List<DBUser> getAllUser() {
		String hql = "from DBUser";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addUser(DBUser user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public boolean delUser(int id) {
		String hql = "delete DBUser u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateUser(DBUser user) {
		String hql = "update DBUser set password=" + user.getPassword()
				+ " where user_ID=" + user.getUser_ID();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		int n = query.executeUpdate();
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public DBUser getAuthUser(String account, String password) {
		String hql = "from DBUser u where u.account=? and u.password=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		return (DBUser) query.uniqueResult();
	}

	@Override
	public DBUser getUserByAccount(String account) {
		logger.info("userDaImpl getUserByAccount---->>>  " + account);
		logger.info("sessionFactory:" + sessionFactory);
		String hql = "from DBUser u where u.account= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		return (DBUser) query.uniqueResult();
	}

	@Override
	public List<DBUser> getUserByDepartment(int department_ID) {
		String hql = "from DBUser where dept_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, department_ID);
		return query.list();
	}

	@Override
	public List<DBUser> getUserByRole(int roleType) {
		String hql = "from DBUser where access=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, roleType);
		return query.list();
	}

	@Override
	public List<DBUser> getDepartmentUserByRole(int roleType, int department_ID) {
		String hql = "from DBUser where access=? and dept_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter(0, roleType);
		query.setParameter(1, department_ID);
		return query.list();
	}

}
