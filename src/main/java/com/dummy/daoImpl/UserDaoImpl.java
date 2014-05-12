package com.dummy.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.dummy.dao.UserDao;
import com.dummy.domain.User;


public class UserDaoImpl implements UserDao {
	//ªÒ»°session
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public User getUser(int id) {
		String hql = "from User u where u.id=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);	
		return (User)query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		String hql = "from User";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addUser(User user) {
		sessionFactory.getCurrentSession().save(user);		
	}

	@Override
	public boolean delUser(int id) {
		String hql = "delete User u where u.id = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);		
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateUser(User user) {
		String hql = "update User u set u.account = ?,u.ln=? where u.gender = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user.getAccount());
		query.setString(1, String.valueOf(user.getUser_ID()));	
		return (query.executeUpdate() > 0);
	}
	
}
