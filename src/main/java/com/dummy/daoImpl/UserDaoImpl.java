package com.dummy.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dummy.dao.UserDao;
import com.dummy.domain.DBUser;

@Repository(value="userDao")
public class UserDaoImpl implements UserDao {
	// ªÒ»°session
	@Autowired
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

	@SuppressWarnings("unchecked")
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
		return false;
	}

	@Override
	public DBUser getAuthUser(String account, String password) {
		String hql = "from DBUser u where u.account=? and u.password=?";
		System.out.println(account);
		System.out.println(password);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, account);
		query.setString(1, password);
		return (DBUser) query.uniqueResult();
	}

	@Override
	public DBUser getUserByAccount(String account) {
		String hql = "from DBUser u where u.account=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, account);
		return (DBUser) query.uniqueResult();
	}

}
