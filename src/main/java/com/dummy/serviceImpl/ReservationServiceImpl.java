package com.dummy.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dummy.dao.ReservationDao;
import com.dummy.dao.TeamDao;
import com.dummy.dao.UserDao;
import com.dummy.domain.CalanderDataDomain;
import com.dummy.domain.Reservation;
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
				// 创建JSON对象
				HashMap<String, String> map = new HashMap<String, String>();
				int[] dateInfo = ChangeDate(start);
				// 此处添加字段
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
					// 此处添加字段
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

	@Override
	public List<Reservation> getReservationByOption(
			HashMap<String, String> queryKeys) {
		StringBuilder builder = new StringBuilder();
		Set<String> keys = queryKeys.keySet();
		int i = 0;
		for (String key : keys) {
			if (key.equals("team_ID") || key.equals("room_ID")
					|| key.equals("user_ID") || key.equals("status")) {
				builder.append(key + "=" + queryKeys.get(key).trim());
			} else if (key.equals("purpose") || key.equals("reservation_Num")
					|| key.equals("email") || key.equals("tele")) {
				builder.append(key + " like " + "'%"
						+ queryKeys.get(key).toString().trim() + "%'");
			} else if (key.equals("Applied_Start_Date")) {
				builder.append(key + ">='" + queryKeys.get(key).trim() + "'");
			} else if (key.equals("Applied_End_Date")) {
				builder.append(key + "<='" + queryKeys.get(key).trim() + "'");
			} else if (key.equals("order_Time")) {
				builder.append(key + "='" + queryKeys.get(key).trim() + "'");
			}

			if ((i + 1) != keys.size()) {
				builder.append(" and ");
			}
			++i;
		}
		logger.info("sql语句---->" + builder.toString());
		List<Reservation> reservations = reservationDao
				.getReservationByOption(builder.toString());
		return reservations;
	}
}
