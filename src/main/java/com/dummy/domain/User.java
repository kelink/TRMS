package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/***
 * User Table
 *
 *
 *CREATE TABLE `user` (
 `User_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'User ���id',
 `Account` varchar(255) NOT NULL COMMENT 'User�ĵ�½account',
 `Password` varchar(255) NOT NULL COMMENT 'User�ĵ�½����',
 `Gender` int(11) NOT NULL COMMENT 'User���Ա�',
 `LN` varchar(50) NOT NULL COMMENT 'User��LN �����ڷ��ʼ�',
 `Tele` varchar(50) NOT NULL COMMENT 'User�ĵ绰',
 PRIMARY KEY (`User_ID`),
 UNIQUE KEY `Account` (`Account`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='User �û���½��'
*
*
*
 */
@Entity
@Table(name="T_USER")
public class User {

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name = "system-uuid",strategy="uuid")
	@Column(length=11)
	private int user_ID;
	
	@Column(length=255)
	private String Account;
	
	@Column(length=255)
	private String Password;
	
	@Column(length=11)
	private int gender;
	
	@Column(length=50)
	private String LN;
	
	@Column(length=50)
	private String Tele;

	public int getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}

	public String getAccount() {
		return Account;
	}

	public void setAccount(String account) {
		Account = account;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
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
	
	
}