package com.dummy.daoImpl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.dummy.dao.ReservationDao;
import com.dummy.domain.Reservation;
import com.dummy.util.CalanderUtil;

@Repository(value = "reservationDao")
public class ReservationDaoImpl implements ReservationDao {

	// ªÒ»°session
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Reservation getReservation(int id) {
		String hql = "from Reservation r where r.reservation_ID=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (Reservation) query.uniqueResult();
	}

	@Override
	public List<Reservation> getAllReservation() {
		String hql = "from Reservation";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Override
	public void addReservation(Reservation reservation) {
		sessionFactory.getCurrentSession().save(reservation);
	}

	@Override
	public boolean delReservation(int id) {
		String hql = "delete Reservation r where r.reservation_ID = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, id);
		return (query.executeUpdate() > 0);
	}

	@Override
	public boolean updateReservationById(int id, Reservation reservation) {
		return false;
	}

	@Override
	public List getAllReservationInfo(int room_ID) {
		String sqlStr = "select Applied_END_Date,Applied_Start_Date,email,order_Time,purpose,Team.teamName from Reservation,Team  where Reservation.team_ID=Team.team_ID and Reservation.status=? and applied_start_date>=? and applied_end_date<=? and reservation.room_ID=?;";
		SQLQuery query = sessionFactory.openSession().createSQLQuery(sqlStr);
		query.setInteger(0, 1);
		query.setString(1, CalanderUtil.getFirstDay());
		query.setString(2, CalanderUtil.getLastDay());
		query.setInteger(3, room_ID);
		List<Object[]> stus = query
				.addScalar("Applied_END_Date", StandardBasicTypes.DATE)
				.addScalar("Applied_Start_Date", StandardBasicTypes.DATE)
				.addScalar("email", StandardBasicTypes.STRING)
				.addScalar("order_Time", StandardBasicTypes.DATE)
				.addScalar("purpose", StandardBasicTypes.STRING)
				.addScalar("teamName", StandardBasicTypes.STRING).list();
		for (Iterator iterator = stus.iterator(); iterator.hasNext();) {
			Object[] rows = (Object[]) iterator.next();
			for (int i = 0; i < rows.length; i++) {
				System.out.println("" + rows[i]);
			}

		}
		return stus;
	}
}
