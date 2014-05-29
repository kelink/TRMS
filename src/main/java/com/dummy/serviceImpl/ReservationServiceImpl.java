package com.dummy.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dummy.dao.ReservationDao;
import com.dummy.dao.RoomDao;
import com.dummy.dao.TeamDao;
import com.dummy.dao.UserDao;
import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;
import com.dummy.service.ReservationService;

@Service(value = "reservationService")
public class ReservationServiceImpl implements ReservationService {
	private static final Logger logger = LoggerFactory
			.getLogger(ReservationServiceImpl.class);
	@Resource(name = "reservationDao")
	private ReservationDao reservationDao;

	@Resource(name = "userDao")
	private UserDao userDao;

	@Resource(name = "teamDao")
	private TeamDao teamDao;

	@Resource(name = "roomDao")
	private RoomDao roomDao;

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
	public boolean updateReservation(Reservation reservation) {
		return reservationDao.updateReservation(reservation);
	}

	// get calander info
	@Override
	public String getCalanderData(int room_ID) {
		List<CalanderDataDomain> list = reservationDao
				.getAllReservationInfo(room_ID);
		ArrayList<JSONObject> calanderData = new ArrayList<JSONObject>();
		if (list.isEmpty()) {
			return null;
		}
		for (int i = 0; i < list.size(); i++) {
			ArrayList<JSONObject> jsonArrayList = createCalendarJsonObject(list
					.get(i));
			for (JSONObject jsonObject : jsonArrayList) {
				calanderData.add(jsonObject);
			}
		}
		return calanderData.toString();
	}

	// �������ҷ�װΪ���JSON���󣬱���������ʾ
	private ArrayList<JSONObject> createCalendarJsonObject(
			CalanderDataDomain calanderDataDomain) {
		long onDay = 24 * 60 * 60 * 1000;
		long start = calanderDataDomain.getApplied_Start_Date().getTime();
		long end = calanderDataDomain.getApplied_END_Date().getTime();

		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		if (start == end) {
			HashMap<String, String> map = new HashMap<String, String>();
			int[] dateInfo = ChangeDate(start);
			// �˴�����ֶ�
			map.put("year", String.valueOf(dateInfo[0]));
			map.put("month", String.valueOf(dateInfo[1]));
			map.put("day", String.valueOf(dateInfo[2]));
			map.put("email", calanderDataDomain.getEmail());
			map.put("tele", calanderDataDomain.getTele());
			map.put("applicant",
					userDao.getUser(calanderDataDomain.getUser_ID())
							.getAccount());
			map.put("applicant_Team", calanderDataDomain.getTeamName());

			JSONObject jsonObject = new JSONObject(map);
			result.add(jsonObject);
		} else {
			while (start < end) {
				// ����JSON����
				HashMap<String, String> map = new HashMap<String, String>();
				int[] dateInfo = ChangeDate(start);
				// �˴�����ֶ�
				map.put("year", String.valueOf(dateInfo[0]));
				map.put("month", String.valueOf(dateInfo[1]));
				map.put("day", String.valueOf(dateInfo[2]));
				map.put("email", calanderDataDomain.getEmail());
				map.put("tele", calanderDataDomain.getTele());
				map.put("applicant",
						userDao.getUser(calanderDataDomain.getUser_ID())
								.getAccount());
				map.put("applicant_Team", calanderDataDomain.getTeamName());

				JSONObject jsonObject = new JSONObject(map);
				result.add(jsonObject);
				start += onDay;
				if (start == end) {
					HashMap<String, String> temp = new HashMap<String, String>();
					int[] tempDate = ChangeDate(start);
					// �˴�����ֶ�
					temp.put("year", String.valueOf(tempDate[0]));
					temp.put("month", String.valueOf(tempDate[1]));
					temp.put("day", String.valueOf(tempDate[2]));
					temp.put("email", calanderDataDomain.getEmail());
					temp.put("tele", calanderDataDomain.getTele());
					temp.put("applicant",
							userDao.getUser(calanderDataDomain.getUser_ID())
									.getAccount());
					temp.put("applicant_Team", calanderDataDomain.getTeamName());

					JSONObject last = new JSONObject(temp);
					result.add(last);
				} else {
					continue;
				}
			}
		}
		return result;
	}

	// 0--year,1---month,2---day
	private int[] ChangeDate(long dayTime) {
		Date date = new Date(dayTime);
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = f.format(date);
		String[] temp = dateStr.split("-");
		int[] result = new int[3];
		for (int i = 0; i < temp.length; i++) {
			result[0] = Integer.parseInt(temp[0]);
			result[1] = Integer.parseInt(temp[1]);
			result[2] = Integer.parseInt(temp[2]);
		}
		return result;
	}

	// get page of Reservations
	@Override
	public List<ReservationDetial> getReservationDetialOnPage(int pageNum,
			int pageSize, String optionSql) {
		return reservationDao.getReservationDetialOnPage(pageNum, pageSize,
				optionSql);
	}

	@Override
	public List<ReservationDetial> getReservationByOption(String sql) {
		return reservationDao.getReservationByOption(sql);
	}

	@Override
	public boolean delReservationByNum(String num) {
		return reservationDao.delReservationByNum(num);
	}

	@Override
	public Reservation getReservationByNum(String num) {
		return reservationDao.getReservationByNum(num);
	}
	// get reservation detial by num
	public List<ReservationDetial> getReservationDetialByNum(String num) {
		String optionStr = "where reservation.reservation_Num=" + num;
		return reservationDao.getReservationByOption(optionStr);
	}

	@Override
	public boolean approveOrReject(Reservation reservation) {
		return reservationDao.updateReservation(reservation);
	}

	@Override
	public List<Reservation> getReservationByStatus(int status) {
		return reservationDao.getReservationByStatus(status);
	}

	@Override
	public boolean isBetween(String begin_time, String end_time, int room_ID) {
		return reservationDao.isBetween(begin_time, end_time, room_ID);
	}

	// check whether the reservation can approve

}
