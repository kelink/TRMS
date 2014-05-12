package com.dummy.domain;
/**
 * 
 * Team Domain
 * 
 * CREATE TABLE `team` (
 `Team_ID` int(11) NOT NULL AUTO_INCREMENT,
 `User_ID` int(11) NOT NULL,
 `Department` varchar(50) NOT NULL,
 PRIMARY KEY (`Team_ID`),
 KEY `Team_FK` (`User_ID`),
 CONSTRAINT `Team_FK` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 *
 */
public class Team {
	private int team_ID;
	private int user_ID;
	private String department;
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
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
}
