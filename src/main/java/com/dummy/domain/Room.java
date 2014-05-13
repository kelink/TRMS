package com.dummy.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * Room Domain
 *
 * CREATE TABLE `room` ( `Room_ID` int(11) NOT NULL AUTO_INCREMENT, `item`
 * varchar(50) NOT NULL, `OwnBy` varchar(50) NOT NULL, `Room_Status` int(11)
 * DEFAULT '0', `Last_Used_Date` datetime NOT NULL, PRIMARY KEY (`Room_ID`) )
 * ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
 * 
 *
 */
@Entity
@Table(name = "Room")
public class Room implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -135886627681640355L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int room_ID;

	@Column(length = 50)
	private String item;

	@Column(length = 50)
	private String ownBy;

	@Column(length = 11)
	private int room_Status;

	private Date last_Used_Date;

	public int getRoom_ID() {
		return room_ID;
	}

	public void setRoom_ID(int room_ID) {
		this.room_ID = room_ID;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

	public int getRoom_Status() {
		return room_Status;
	}

	public void setRoom_Status(int room_Status) {
		this.room_Status = room_Status;
	}

	public Date getLast_Used_Date() {
		return last_Used_Date;
	}

	public void setLast_Used_Date(Date last_Used_Date) {
		this.last_Used_Date = last_Used_Date;
	}

}
