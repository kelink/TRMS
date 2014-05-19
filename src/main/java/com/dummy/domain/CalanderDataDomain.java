package com.dummy.domain;

import java.util.Date;

public class CalanderDataDomain {
	private Date Applied_END_Date;
	private Date Applied_Start_Date;
	private String email;
	private Date order_Time;
	private String purpose;
	private String teamName;
	private int user_ID;
	private String account;
	private int room_ID;
	private String user_Tele;

	public CalanderDataDomain() {
		super();
	}

	public CalanderDataDomain(Date applied_END_Date, Date applied_Start_Date,
			String email, Date order_Time, String purpose, String teamName,
			int user_ID, String account, int room_ID, String user_Tele) {
		super();
		Applied_END_Date = applied_END_Date;
		Applied_Start_Date = applied_Start_Date;
		this.email = email;
		this.order_Time = order_Time;
		this.purpose = purpose;
		this.teamName = teamName;
		this.user_ID = user_ID;
		this.account = account;
		this.room_ID = room_ID;
		this.user_Tele = user_Tele;
	}

	@Override
	public String toString() {
		return "CalanderDataDomain [Applied_END_Date=" + Applied_END_Date
				+ ", Applied_Start_Date=" + Applied_Start_Date + ", email="
				+ email + ", order_Time=" + order_Time + ", purpose=" + purpose
				+ ", teamName=" + teamName + ", user_ID=" + user_ID
				+ ", account=" + account + ", room_ID=" + room_ID
				+ ", user_Tele=" + user_Tele + "]";
	}

	public Date getApplied_END_Date() {
		return Applied_END_Date;
	}

	public void setApplied_END_Date(Date applied_END_Date) {
		Applied_END_Date = applied_END_Date;
	}

	public Date getApplied_Start_Date() {
		return Applied_Start_Date;
	}

	public void setApplied_Start_Date(Date applied_Start_Date) {
		Applied_Start_Date = applied_Start_Date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getOrder_Time() {
		return order_Time;
	}

	public void setOrder_Time(Date order_Time) {
		this.order_Time = order_Time;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getUser_Tele() {
		return user_Tele;
	}

	public void setUser_Tele(String user_Tele) {
		this.user_Tele = user_Tele;
	}

}