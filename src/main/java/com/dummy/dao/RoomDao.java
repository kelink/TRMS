package com.dummy.dao;

import java.util.List;

import com.dummy.domain.Room;

public interface RoomDao {
	public Room getRoom(int id);

	public List<Room> getAllRoom();

	public void addRoom(Room room);

	public boolean delRoom(int id);

	public boolean updateRoom(Room room);

	public List<Room> getRoomOnPage(int pageNum, int pageSize);

	public List<Room> getRoomByStatus(int status);

	public List<Room> getRoomsBydepartment(int department_ID);

	public boolean isRoomExit(Room room);
}
