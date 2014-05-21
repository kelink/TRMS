package com.dummy.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class QueryObjectDomain {

	// 标记角色类型
	private int access;

	private int team_ID;
	private int room_ID;
	// 当前用户的id
	private int user_ID;
	private int status;

	private String reservation_Num;
	private String purpose;
	private String email;
	private String tele;

	private Date Applied_Start_Date;
	private Date Applied_End_Date;
	private Date order_Time;

	public QueryObjectDomain() {
		super();
	}

	@Override
	public String toString() {
		return "QueryObjectDomain [access=" + access + ", team_ID=" + team_ID
				+ ", room_ID=" + room_ID + ", user_ID=" + user_ID + ", status="
				+ status + ", reservation_Num=" + reservation_Num
				+ ", purpose=" + purpose + ", email=" + email + ", tele="
				+ tele + ", Applied_Start_Date=" + Applied_Start_Date
				+ ", Applied_End_Date=" + Applied_End_Date + ", order_Time="
				+ order_Time + "]";
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getReservation_Num() {
		return reservation_Num;
	}

	public void setReservation_Num(String reservation_Num) {
		this.reservation_Num = reservation_Num;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public Date getApplied_Start_Date() {
		return Applied_Start_Date;
	}

	public void setApplied_Start_Date(Date applied_Start_Date) {
		Applied_Start_Date = applied_Start_Date;
	}

	public Date getApplied_End_Date() {
		return Applied_End_Date;
	}

	public void setApplied_End_Date(Date applied_End_Date) {
		Applied_End_Date = applied_End_Date;
	}

	public Date getOrder_Time() {
		return order_Time;
	}

	public void setOrder_Time(Date order_Time) {
		this.order_Time = order_Time;
	}

	// 创建sql语句(暂时没有角色控制)
	public String getQuerySql(HashMap<String, String> queryKeys) {
		String queryString = null;
		StringBuilder builder = new StringBuilder();
		Set<String> keys = queryKeys.keySet();
		int i = 0;
		for (String key : keys) {
			if (key.equals("team_ID") || key.equals("room_ID")
					|| key.equals("user_ID") || key.equals("status")) {
				builder.append(key + "=" + queryKeys.get(key).trim());
			} else if (key.equals("purpose") || key.equals("reservation_Num")
					|| key.equals("email") || key.equals("tele")) {
				builder.append(key + " like " + "%'"
						+ queryKeys.get(key).toString().trim() + "'%");
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
		builder.append(";");
		System.out.println(builder.toString());
		return queryString;
	}
}
