package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dummy.dao.DepartmentDao;
import com.dummy.domain.Department;

@Repository(value = "departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Department getDepartment(int id) {
		String hql = "from Department d where d.department_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Department) query.uniqueResult();
	}

	@Override
	public List<Department> getAllDepartment() {
		String hql = "from Department";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addDepartment(Department department) {
		sessionFactory.getCurrentSession().save(department);

	}

	@Override
	public boolean delDepartment(int id) {
		String hql = "delete Department d where d.department_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateDepartment(Department department) {
		return false;
	}

	@Override
	public boolean isDepartmentExit(String departmentName) {
		String hql = "from Department d where d.departmentName=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, departmentName);
		int n = query.list().size();
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

}
