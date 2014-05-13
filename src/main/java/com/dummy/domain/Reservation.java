package com.dummy.domain;

import java.sql.Date;

import javax.xml.crypto.Data;
/**
 * 
 * Reservation Domain
 * 
 *CREATE TABLE `reservation` (
 `Reservation_ID` int(11) NOT NULL AUTO_INCREMENT,
 `Team_ID` int(11) NOT NULL,
 `Room_ID` int(11) NOT NULL,
 `User_ID` int(11) NOT NULL,
 `Applied_End_ Date` datetime NOT NULL,
 `Applied_Start_Date` datetime NOT NULL,
 `Status` int(11) NOT NULL DEFAULT '0',
 `Order_Time` datetime NOT NULL,
 `Usage` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`Reservation_ID`),
 KEY `Re_User_Fk` (`User_ID`),
 KEY `RE_Team_Fk` (`Team_ID`),
 KEY `RE_Room_FK` (`Room_ID`),
 CONSTRAINT `RE_Room_FK` FOREIGN KEY (`Room_ID`) REFERENCES `room` (`Room_ID`),
 CONSTRAINT `RE_Team_Fk` FOREIGN KEY (`Team_ID`) REFERENCES `team` (`Team_ID`),
 CONSTRAINT `Re_User_Fk` FOREIGN KEY (`User_ID`) REFERENCES `user` (`User_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
public class Reservation {
	private int reservation_ID;
	private int team_ID;
	private int room_ID;
	private int user_ID;
	private Data Applied_Start_Date;
	private Date Applied_End_Date;
	private int status;
	private Date order_Time;
	private String Usage;
	public int getReservation_ID() {
		return reservation_ID;
	}
	public void setReservation_ID(int reservation_ID) {
		this.reservation_ID = reservation_ID;
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
	public Data getApplied_Start_Date() {
		return Applied_Start_Date;
	}
	public void setApplied_Start_Date(Data applied_Start_Date) {
		Applied_Start_Date = applied_Start_Date;
	}
	public Date getApplied_End_Date() {
		return Applied_End_Date;
	}
	public void setApplied_End_Date(Date applied_End_Date) {
		Applied_End_Date = applied_End_Date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getOrder_Time() {
		return order_Time;
	}
	public void setOrder_Time(Date order_Time) {
		this.order_Time = order_Time;
	}
	public String getUsage() {
		return Usage;
	}
	public void setUsage(String usage) {
		Usage = usage;
	}
	
	
	
}
