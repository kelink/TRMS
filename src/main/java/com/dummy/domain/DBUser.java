package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DBUser")
public class DBUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int user_ID;

	@Column(length = 11, nullable = false)
	private int access;

	@Column(length = 255, nullable = false)
	private String account;

	@Column(length = 255, nullable = false)
	private String password;

	@Column(length = 11)
	private int gender;

	@Column(length = 50, nullable = false)
	private String Tele;

	private int team_ID;

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getTele() {
		return Tele;
	}

	public void setTele(String tele) {
		Tele = tele;
	}

	@Override
	public String toString() {
		return "DBUser [user_ID=" + user_ID + ", access=" + access
				+ ", account=" + account + ", password=" + password
				+ ", gender=" + gender + ", Tele=" + Tele + "]";
	}

}