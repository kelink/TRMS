package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dummy.dao.TeamDao;
import com.dummy.domain.Team;

@Repository(value = "teamDao")
public class TeamDaoImpl implements TeamDao {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Team getTeam(int id) {
		String hql = "from Team t where t.team_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Team) query.uniqueResult();
	}

	@Override
	public List<Team> getAllTeam() {
		String hql = "from Team";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addTeam(Team team) {
		sessionFactory.getCurrentSession().save(team);

	}

	@Override
	public boolean delTeam(int id) {
		String hql = "delete Team t where t.team_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateTeam(Team team) {
		return false;
	}

	@Override
	public List<Team> getTeamsByDepartment(int department_ID) {
		String hql = "from Team t where t.department_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, department_ID);
		return query.list();
	}

	@Override
	public List<Team> getTeamByUserDepartment(int user_ID, int partment_ID) {
		String hql = "from Team t where t.user_ID=? and t.department_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, user_ID);
		query.setInteger(1, partment_ID);
		return query.list();
	}

	@Override
	public boolean delTeamByUser(int user_ID) {
		String hql = "delete Team t where t.user_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, user_ID);
		return (query.executeUpdate() > 0);
	}

	@Override
	public List<Team> getTeamByUser(int user_ID) {
		String hql = "from Team t where t.user_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, user_ID);
		return query.list();
	}
}
