package com.dummy.util;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class ReservationUtil {
	// use JDK_UUID schema to generate number of reservation
	public static String getUniqueSequence() {
		return UUID.randomUUID().toString();
	}
	// Generate The Where Query Option
	// not include the user role
	public static String generateQueryOption(HttpServletRequest request) {
		HashMap<String, String> optionMapMap = new HashMap<String, String>();
		String reservation_Num = request.getParameter("reservation_Num");
		String team_ID = request.getParameter("team_ID");
		String room_ID = request.getParameter("room_ID");
		String purpose = request.getParameter("purpose");
		String status = request.getParameter("status");
		String email = request.getParameter("email");
		String tele = request.getParameter("tele");
		String Applied_Start_Date = request.getParameter("Applied_Start_Date");
		String Applied_End_Date = request.getParameter("Applied_End_Date");
		String order_Time = request.getParameter("order_Time");
		if (reservation_Num != null && reservation_Num.length() > 0) {
			optionMapMap.put("reservation_Num", reservation_Num);
		}
		if (team_ID != null && team_ID.length() > 0) {
			optionMapMap.put("team_ID", team_ID);
		}
		if (room_ID != null && room_ID.length() > 0) {
			optionMapMap.put("room_ID", room_ID);
		}
		if (purpose != null && purpose.length() > 0) {
			optionMapMap.put("purpose", purpose);
		}
		if (status != null && status.length() > 0) {
			optionMapMap.put("status", status);
		}
		if (tele != null && tele.length() > 0) {
			optionMapMap.put("tele", tele);
		}
		if (email != null && email.length() > 0) {
			optionMapMap.put("email", email);
		}
		if (Applied_Start_Date != null && Applied_Start_Date.length() > 0) {
			optionMapMap.put("Applied_Start_Date", Applied_Start_Date);
		}
		if (Applied_End_Date != null && Applied_End_Date.length() > 0) {
			optionMapMap.put("Applied_End_Date", Applied_End_Date);
		}
		if (Applied_End_Date != null && Applied_End_Date != null
				&& Applied_End_Date.length() > 0) {
			optionMapMap.put("Applied_End_Date", Applied_End_Date);
		}
		if (order_Time != null && order_Time.length() > 0) {
			optionMapMap.put("order_Time", order_Time);
		}

		StringBuilder builder = new StringBuilder();
		Set<String> keys = optionMapMap.keySet();
		int i = 0;
		for (String key : keys) {
			if (key.equals("team_ID") || key.equals("room_ID")
					|| key.equals("user_ID") || key.equals("status")) {
				builder.append("Reservation." + key + "="
						+ optionMapMap.get(key).trim());
			} else if (key.equals("purpose") || key.equals("reservation_Num")
					|| key.equals("email") || key.equals("tele")) {
				builder.append("Reservation." + key + " like " + "'%"
						+ optionMapMap.get(key).toString().trim() + "%'");
			} else if (key.equals("Applied_Start_Date")) {
				builder.append("Reservation." + key + ">='"
						+ optionMapMap.get(key).trim() + "'");
			} else if (key.equals("Applied_End_Date")) {
				builder.append("Reservation." + key + "<='"
						+ optionMapMap.get(key).trim() + "'");
			} else if (key.equals("order_Time")) {
				builder.append("Reservation." + key + "='"
						+ optionMapMap.get(key).trim() + "'");
			}
			if ((i + 1) != keys.size()) {
				builder.append(" and ");
			}
			++i;
		}
		return builder.toString();
	}
}
