package com.dummy.service;

import java.util.HashMap;
import java.util.List;

import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;

public interface ReservationService {
	public Reservation getReservation(int id);

	public List<Reservation> getAllReservation();

	public void addReservation(Reservation reservation);

	public boolean delReservation(int id);

	public boolean updateReservationById(int id, Reservation reservation);

	public String getCalanderData(int room_ID);

	public List<ReservationDetial> getReservationByOption(
			HashMap<String, String> hashMap);
}
