package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dummy.controller.LoginController;
import com.dummy.dao.BlackListDao;
import com.dummy.domain.BlackList;

@Repository("blackListDao")
public class BlackListDaoImpl implements BlackListDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public BlackList getBlackList(int id) {
		String hql = "from BlackList b where b.bl_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (BlackList) query.uniqueResult();
	}

	@Override
	public List<BlackListDao> getAllBlackList() {
		String hql = "from BlackList";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addBlackList(BlackListDao blackList) {
		sessionFactory.getCurrentSession().save(blackList);
	}

	@Override
	public boolean delBlackList(int id) {
		String hql = "delete BlackList b where b.bl_ID = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateBlackList(BlackListDao blackList) {
		return false;
	}

}