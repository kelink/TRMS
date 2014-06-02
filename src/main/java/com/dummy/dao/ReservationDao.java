package com.dummy.dao;

import java.util.List;

import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;

public interface ReservationDao {
	public Reservation getReservation(int id);

	public List<Reservation> getAllReservation();

	public void addReservation(Reservation reservation);

	public boolean delReservation(int id);

	public boolean updateReservation(Reservation reservation);

	public List<CalanderDataDomain> getAllReservationInfo(int room_ID);

	public List<ReservationDetial> getReservationByOption(String sql);

	public List<ReservationDetial> getReservationDetialOnPage(int pageNum,
			int pageSize, String optionSql);

	public boolean delReservationByNum(String num);

	public Reservation getReservationByNum(String num);

	public List<Reservation> getReservationByStatus(int status);

	public boolean isBetween(String begin_time, String end_time, int room_ID);
}
