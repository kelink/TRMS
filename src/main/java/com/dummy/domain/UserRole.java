package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User_Role Domain
 * 
 * CREATE TABLE `user_role` (
 `User_ID` int(11) NOT NULL,
 `Role_ID` int(11) NOT NULL,
 KEY `Role_FK` (`Role_ID`),
 KEY `User_FK` (`User_ID`),
 CONSTRAINT `Role_FK` FOREIGN KEY (`Role_ID`) REFERENCES `role` (`Role_ID`) ON DELETE CASCADE ON UPDATE CASCADE,
 CONSTRAINT `User_FK` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 *
 */
@Entity
@Table(name="UserRole")
public class UserRole  {
	
	/**
	 * ÓÃ‘ô½ÇÉ«±í
	 */

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	@Column(length=11)
	private int id;  
	@Column(length=11)
	private int user_id;
	@Column(length=11)
	private int role_id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	
}
