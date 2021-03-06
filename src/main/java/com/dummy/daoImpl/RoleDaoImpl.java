package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dummy.dao.RoleDao;
import com.dummy.domain.Role;

@Repository(value = "roleDao")
public class RoleDaoImpl implements RoleDao {
	// ��ȡsession
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Role getRole(int id) {
		String hql = "from Role r where r.Role_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Role) query.uniqueResult();
	}

	@Override
	public List<Role> getAllRole() {
		String hql = "from Role";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addRole(Role role) {
		sessionFactory.getCurrentSession().save(role);
	}

	@Override
	public boolean delRole(int id) {
		String hql = "delete Role r where r.Role_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateRole(Role Role) {
		return false;
	}

}
