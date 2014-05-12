package com.dummy.domain;
/**
 * BlackList Domain
 * 
 * CREATE TABLE `blacklist` (
 `BL_ID` int(11) NOT NULL AUTO_INCREMENT,
 `Team_ID` int(11) NOT NULL,
 `Reason` varchar(255) NOT NULL,
 PRIMARY KEY (`BL_ID`),
 KEY `BL_FK` (`Team_ID`),
 CONSTRAINT `BL_FK` FOREIGN KEY (`Team_ID`) REFERENCES `team` (`Team_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8

 *
 */
public class BlackList {
	private int bl_ID;
	private int team_ID;
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
