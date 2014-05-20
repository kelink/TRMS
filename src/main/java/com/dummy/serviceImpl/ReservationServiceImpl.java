package com.dummy.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dummy.dao.ReservationDao;
import com.dummy.dao.TeamDao;
import com.dummy.dao.UserDao;
import com.dummy.domain.CalanderDataDomain;
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

	// 获取日历需要的信息
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

	// 解析并且封装为多个JSON对象，便于日历显示
	private ArrayList<JSONObject> createCalendarJsonObject(
			CalanderDataDomain calanderDataDomain) {
		long onDay = 24 * 60 * 60 * 1000;
		long start = calanderDataDomain.getApplied_Start_Date().getTime();
		long end = calanderDataDomain.getApplied_END_Date().getTime();

		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		if (start == end) {
			HashMap<String, String> map = new HashMap<String, String>();
			int[] dateInfo = ChangeDate(start);
			// 此处添加字段
			map.put("year", String.valueOf(dateInfo[0]));
			map.put("month", String.valueOf(dateInfo[1]));
			map.put("day", String.valueOf(dateInfo[2]));
			JSONObject jsonObject = new JSONObject(map);
			result.add(jsonObject);
		} else {
			while (start < end) {
				// 创建JSON对象
				HashMap<String, String> map = new HashMap<String, String>();
				int[] dateInfo = ChangeDate(start);
				// 此处添加字段
				map.put("year", String.valueOf(dateInfo[0]));
				map.put("month", String.valueOf(dateInfo[1]));
				map.put("day", String.valueOf(dateInfo[2]));
				JSONObject jsonObject = new JSONObject(map);
				result.add(jsonObject);
				start += onDay;
				if (start == end) {
					HashMap<String, String> temp = new HashMap<String, String>();
					int[] tempDate = ChangeDate(start);
					// 此处添加字段
					temp.put("year", String.valueOf(tempDate[0]));
					temp.put("month", String.valueOf(tempDate[1]));
					temp.put("day", String.valueOf(tempDate[2]));
					JSONObject last = new JSONObject(temp);
					result.add(last);
				} else {
					continue;
				}
			}
		}

		return result;
	}

	// 0表示年，1表示月,2表示日
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
}
