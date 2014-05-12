package com.dummy.domain;

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
public class User_Role {
	private int role_ID;
	private int user_ID;
	public int getRole_ID() {
		return role_ID;
	}
	public void setRole_ID(int role_ID) {
		this.role_ID = role_ID;
	}
	public int getUser_ID() {
		return user_ID;
	}
	public void setUser_ID(int user_ID) {
		this.user_ID = user_ID;
	}
	
}
