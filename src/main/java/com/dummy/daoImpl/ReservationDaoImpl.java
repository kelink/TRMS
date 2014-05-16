package com.dummy.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.dummy.dao.ReservationDao;
import com.dummy.domain.Reservation;

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

}
