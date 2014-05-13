package com.dummy.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Team Domain
 * 
 * CREATE TABLE `team` ( `Team_ID` int(11) NOT NULL AUTO_INCREMENT, `User_ID`
 * int(11) NOT NULL, `Department` varchar(50) NOT NULL, PRIMARY KEY (`Team_ID`),
 * KEY `Team_FK` (`User_ID`), CONSTRAINT `Team_FK` FOREIGN KEY (`User_ID`)
 * REFERENCES `user` (`User_ID`) ON DELETE CASCADE ON UPDATE CASCADE )
 * ENGINE=InnoDB DEFAULT CHARSET=utf8
 *
 */
@Entity
@Table(name = "Team")
public class Team implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8906539116524638309L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int team_ID;

	@Column(nullable = false, length = 50)
	private int user_ID;

	@Column(nullable = false, length = 50)
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
