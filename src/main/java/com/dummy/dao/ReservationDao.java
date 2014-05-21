package com.dummy.dao;

import java.util.List;

import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.Reservation;

public interface ReservationDao {
	public Reservation getReservation(int id);

	public List<Reservation> getAllReservation();

	public void addReservation(Reservation reservation);

	public boolean delReservation(int id);

	public boolean updateReservationById(int id, Reservation reservation);

	public List<CalanderDataDomain> getAllReservationInfo(int room_ID);

	public List<Reservation> getReservationByOption(String sql);
}
