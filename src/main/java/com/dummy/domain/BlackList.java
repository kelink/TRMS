package com.dummy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BlackList Domain
 * 
 * CREATE TABLE `blacklist` ( `BL_ID` int(11) NOT NULL AUTO_INCREMENT, `Team_ID`
 * int(11) NOT NULL, `Reason` varchar(255) NOT NULL, PRIMARY KEY (`BL_ID`), KEY
 * `BL_FK` (`Team_ID`), CONSTRAINT `BL_FK` FOREIGN KEY (`Team_ID`) REFERENCES
 * `team` (`Team_ID`) ON DELETE CASCADE ON UPDATE CASCADE ) ENGINE=InnoDB
 * DEFAULT CHARSET=utf8
 * 
 *
 */
@Entity
@Table(name = "BlackList")
public class BlackList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int bl_ID;

	@Column(length = 11)
	private int team_ID;

	@Column(length = 255)
	private String reason;

	public int getBl_ID() {
		return bl_ID;
	}

	public void setBl_ID(int bl_ID) {
		this.bl_ID = bl_ID;
	}

	public int getTeam_ID() {
		return team_ID;
	}

	public void setTeam_ID(int team_ID) {
		this.team_ID = team_ID;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
