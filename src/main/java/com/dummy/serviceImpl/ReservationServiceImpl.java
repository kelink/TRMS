package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dummy.dao.ReservationDao;
import com.dummy.dao.TeamDao;
import com.dummy.dao.UserDao;
import com.dummy.domain.Reservation;
import com.dummy.service.ReservationService;

@Service(value = "reservationService")
public class ReservationServiceImpl implements ReservationService {
	@Resource(name = "reservationDao")
	private ReservationDao reservationDao;

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "teamDao")
	private TeamDao teamDao;

	@Override
	public Reservation getReservation(int id) {
		return reservationDao.getReservation(id);
	}

	@Override
	public List<Reservation> getAllReservation() {
		return reservationDao.getAllReservation();
	}

	@Override
	public void addReservation(Reservation reservation) {
		reservationDao.addReservation(reservation);
	}

	@Override
	public boolean delReservation(int id) {
		return reservationDao.delReservation(id);
	}

	@Override
	public boolean updateReservationById(int id, Reservation reservation) {
		return reservationDao.updateReservationById(id, reservation);
	}

	@Override
	public List getAllReservationInfo(int room_ID) {
		return reservationDao.getAllReservationInfo(room_ID);
	}

}
