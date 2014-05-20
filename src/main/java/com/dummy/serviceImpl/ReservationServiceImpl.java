package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

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

	// 后续需要添加类型判断和异常处理
	@Override
	public String getCalanderData(int room_ID) {
		List<CalanderDataDomain> list = reservationDao
				.getAllReservationInfo(room_ID);
		StringBuilder builder = new StringBuilder();
		if (list.isEmpty()) {
			return null;
		}
		int temp = 0;
		builder.append("[");
		for (int i = 0; i < list.size(); i++) {
			builder.append(createCalanderJson(list.get(i)));
			if (temp + 1 != list.size()) {
				builder.append(",");
				++temp;
			}
		}
		builder.append("]");
		return builder.toString();
	}

	// 创建日历类需要的JSON对象字符串
	// 如{year:2014,month:1,day:20,department:"USER_EXPERIENCE",lc:"xx",usage:"xx",usertele:"xx"}
	private String createCalanderJson(CalanderDataDomain calanderDataDomain) {
		StringBuilder builder = new StringBuilder();
		// 构建年月日
		String temp = calanderDataDomain.getApplied_END_Date().toString();
		String[] data = temp.split("-");
		builder.append("{");
		builder.append("year:" + data[0] + ",");
		builder.append("month:" + data[1] + ",");
		builder.append("day:" + data[2] + ",");
		builder.append("department:" + "\"" + calanderDataDomain.getTeamName()
				+ "\",");
		builder.append("lc:" + "\"" + calanderDataDomain.getAccount() + "\",");
		builder.append("usage:" + "\"" + calanderDataDomain.getPurpose()
				+ "\",");
		builder.append("usertele:" + "\"" + calanderDataDomain.getTele() + "\"");
		builder.append("}");
		return builder.toString();
	}

	// private JSONObject createCalendarJsonObject(
	// CalanderDataDomain calanderDataDomain) {
	// Date start = calanderDataDomain.getApplied_Start_Date();
	// Date end = calanderDataDomain.getApplied_END_Date();
	// long day = (Math.abs(end.getTime() - start.getTime()) / (24 * 60 * 60 *
	// 1000)) + 1;
	// for (int i = 0; i < day; i++) {
	//
	// HashMap<String, Object> map = new HashMap<String, Object>();
	// String[] data = start.split("-");
	// map.put(key, value);
	// }
	// JSONObject jsonObject = JSONObject.fromObject(map);
	// return null;
	// }
}
