package com.dummy.service;

import java.util.List;

import com.dummy.domain.Room;

public interface RoomService {
	public Room getRoom(int id);

	public List<Room> getAllRoom();

	public void addRoom(Room room);

	public boolean delRoom(int id);

	public boolean updateRoom(Room room);

	public List<Room> getRoomOnPage(int pageNum, int pageSize);

	public List<Room> getFreeRooms();
}