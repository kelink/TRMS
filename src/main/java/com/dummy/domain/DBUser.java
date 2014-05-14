package com.dummy.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * User Table
 *
 *
 * CREATE TABLE `user` ( `User_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'User
 * ���id', `Account` varchar(255) NOT NULL COMMENT 'User�ĵ�½account', `Password`
 * varchar(255) NOT NULL COMMENT 'User�ĵ�½����', `Gender` int(11) NOT NULL COMMENT
 * 'User���Ա�', `LN` varchar(50) NOT NULL COMMENT 'User��LN �����ڷ��ʼ�', `Tele`
 * varchar(50) NOT NULL COMMENT 'User�ĵ绰', PRIMARY KEY (`User_ID`), UNIQUE KEY
 * `Account` (`Account`) ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
 * COMMENT='User �û���½��'
 *
 *
 *
 */
@Entity
@Table(name = "DBUser")
public class DBUser implements Serializable {

	/**
	 * �Ñ���
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int user_ID;

	@Column(length = 11)
	private int access;

	@Column(length = 255)
	private String account;

	@Column(length = 255)
	private String password;

	@Column(length = 11)
	private int gender;

	@Column(length = 50)
	private String LN;

	@Column(length = 50)
	private String Tele;

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

	public String getLN() {
		return LN;
	}

	public void setLN(String lN) {
		LN = lN;
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
				+ ", gender=" + gender + ", LN=" + LN + ", Tele=" + Tele + "]";
	}

	
}