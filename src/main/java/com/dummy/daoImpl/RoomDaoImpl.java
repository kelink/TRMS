package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dummy.common.C;
import com.dummy.controller.LoginController;
import com.dummy.dao.RoomDao;
import com.dummy.domain.Room;

@Repository(value = "roomDao")
public class RoomDaoImpl implements RoomDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
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

	@SuppressWarnings("unchecked")
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
		String hql = "update Room set " + "item=?," + "last_Used_Date=?,"
				+ "room_Status=?," + "department_ID=?" + " where room_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, room.getItem());
		query.setDate(1, room.getLast_Used_Date());
		query.setInteger(2, room.getRoom_Status());
		query.setInteger(3, room.getDepartment_ID());
		query.setInteger(4, room.getRoom_ID());
		int n = query.executeUpdate();
		if (n == 1) {
			return true;
		} else {
			return false;
		}
	}
	// get room by page
	@SuppressWarnings("unchecked")
	public List<Room> getRoomOnPage(int pageNum, int pageSize) {
		String hql = "from Room r where r.room_Status=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		int offSet = (pageNum - 1) * pageSize >= 0
				? (pageNum - 1) * pageSize
				: 0;
		query.setInteger(0, C.DB.DEFAULT_FREE_ROOM);
		query.setFirstResult(offSet);
		query.setMaxResults(pageSize);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> getRoomByStatus(int status) {
		String hql = "from Room r where r.room_Status=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, status);
		return query.list();
	}

	@Override
	public List<Room> getRoomsBydepartment(int department_ID) {
		String hql = "from Room r where r.department_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, department_ID);
		return query.list();
	}

	@Override
	public boolean isRoomExit(Room room) {
		String hql = "from Room r where r.item=? and r.department_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, room.getItem());
		query.setInteger(0, room.getDepartment_ID());
		int n = query.list().size();
		if (n > 0) {
			return true;
		} else {
			return false;
		}
	}

}
