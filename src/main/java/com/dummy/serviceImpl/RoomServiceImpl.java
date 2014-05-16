package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dummy.dao.RoomDao;
import com.dummy.domain.Room;
import com.dummy.service.RoomService;

@Service(value = "roomService")
public class RoomServiceImpl implements RoomService {
	@Resource(name = "roomDao")
	private RoomDao roomDao;

	@Override
	public Room getRoom(int id) {
		return roomDao.getRoom(id);
	}

	@Override
	public List<Room> getAllRoom() {
		return roomDao.getAllRoom();
	}

	@Override
	public void addRoom(Room room) {
		roomDao.addRoom(room);
	}

	@Override
	public boolean delRoom(int id) {
		return roomDao.delRoom(id);
	}

	@Override
	public boolean updateRoom(Room room) {
		return roomDao.updateRoom(room);
	}

}
