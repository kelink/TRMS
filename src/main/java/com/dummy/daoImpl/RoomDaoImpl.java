package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dummy.controller.LoginController;
import com.dummy.dao.RoomDao;
import com.dummy.domain.Room;
@Repository(value="roomDao")
public class RoomDaoImpl implements RoomDao{
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	// ªÒ»°session
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public Room getRoom(int id) {
		String hql = "from Room r where r.room_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Room) query.uniqueResult();
	}

	@Override
	public List<Room> getAllRoom() {
		String hql = "from Room";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addRoom(Room room) {
		sessionFactory.getCurrentSession().save(room);		
	}

	@Override
	public boolean delRoom(int id) {
		String hql = "delete Room r where r.room_ID= ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateRoom(Room room) {
		// TODO Auto-generated method stub
		return false;
	}

}
