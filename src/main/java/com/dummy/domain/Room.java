package com.dummy.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 11)
	private int room_ID;

	@Column(length = 255)
	private String item;

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

	@Override
	public String toString() {
		return item;
	}

}
