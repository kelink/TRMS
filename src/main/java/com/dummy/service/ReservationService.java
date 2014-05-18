package com.dummy.service;

import java.util.List;

import com.dummy.domain.Reservation;

public interface ReservationService {
	public Reservation getReservation(int id);

	public List<Reservation> getAllReservation();

	public void addReservation(Reservation reservation);

	public boolean delReservation(int id);

	public boolean updateReservationById(int id, Reservation reservation);

	public List getAllReservationInfo(int room_ID);
}
