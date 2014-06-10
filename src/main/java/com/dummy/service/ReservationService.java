package com.dummy.service;

import java.util.List;

import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;

public interface ReservationService {
	public Reservation getReservation(int id);

	public List<Reservation> getAllReservation();

	public void addReservation(Reservation reservation);

	public boolean delReservation(int id);

	public boolean updateReservation(Reservation reservation);

	public String getCalanderData(int room_ID);

	public List<ReservationDetial> getReservationByOption(String sql);

	public List<ReservationDetial> getReservationDetialOnPage(int pageNum,
			int pageSize, String optionSql);

	public boolean delReservationByNum(String num);

	public Reservation getReservationByNum(String num);

	public boolean approveOrReject(Reservation reservation);

	public List<Reservation> getReservationByStatus(int status);

	public List<Reservation> getReservationByStatusAndUser(int status,
			int user_ID);

	public String checkApprove(Reservation reservation);

	public String checkReject(Reservation reservation);

	public String checkTime(String beginTime, String endTime, int room_ID);

	public List<ReservationDetial> getConflictReservationDetials(int room_ID);

	public String checkBookRoom(Reservation reservation);
}
