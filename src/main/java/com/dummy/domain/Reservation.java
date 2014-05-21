package com.dummy.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reservation")
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int reservation_ID;

	@Column(length = 100)
	private String reservation_Num;

	@Column(length = 11)
	private int team_ID;

	@Column(length = 11)
	private int room_ID;

	@Column(length = 11)
	private int user_ID;

	@Column(length = 255)
	private String purpose;

	@Column(length = 11)
	private int status;

	@Column(length = 255)
	private String email;

	@Column(length = 255)
	private String tele;
	@Column(length = 11)
	private int approve_by;

	private Date Applied_Start_Date;
	private Date Applied_End_Date;
	private Date order_Time;

	public int getReservation_ID() {
		return reservation_ID;
	}

	public void setReservation_ID(int reservation_ID) {
		this.reservation_ID = reservation_ID;
	}

	public String getReservation_Num() {
		return reservation_Num;
	}

	public void setReservation_Num(String reservation_Num) {
		this.reservation_Num = reservation_Num;
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

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getApprove_by() {
		return approve_by;
	}

	public void setApprove_by(int approve_by) {
		this.approve_by = approve_by;
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

	// format 获取时间
	public String getApplied_End_DateByString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(Applied_End_Date);
	}

	public String getApplied_Start_DateByString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(Applied_Start_Date);
	}

	public String getOrder_DateByString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(order_Time);
	}

	// format 解析时间
	public void setApplied_End_DateByString(String dateStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.Applied_End_Date = format.parse(dateStr);
	}

	public void setApplied_Start_DateByString(String dateStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.Applied_Start_Date = format.parse(dateStr);
	}

	public void setOrder_DateByString(String dateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		this.order_Time = format.parse(dateStr);
	}

	@Override
	public String toString() {
		return "Reservation [reservation_ID=" + reservation_ID
				+ ", reservation_Num=" + reservation_Num + ", team_ID="
				+ team_ID + ", room_ID=" + room_ID + ", user_ID=" + user_ID
				+ ", purpose=" + purpose + ", status=" + status + ", email="
				+ email + ", tele=" + tele + ", approve_by=" + approve_by
				+ ", Applied_Start_Date=" + Applied_Start_Date
				+ ", Applied_End_Date=" + Applied_End_Date + ", order_Time="
				+ order_Time + "]";
	}

}
