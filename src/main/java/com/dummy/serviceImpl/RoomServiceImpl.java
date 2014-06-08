package com.dummy.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dummy.common.C;
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

	@Override
	public List<Room> getRoomOnPage(int pageNum, int pageSize) {
		return roomDao.getRoomOnPage(pageNum, pageSize);
	}

	public List<Room> getFreeRooms() {
		return roomDao.getRoomByStatus(C.DB.DEFAULT_FREE_ROOM);
	}

	@Override
	public List<JSONObject> getRoomsBydepartment(int department_ID) {
		List<Room> list = roomDao.getRoomsBydepartment(department_ID);
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		for (Room room : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("room_ID", String.valueOf(room.getRoom_ID()));
			map.put("item", room.getItem());
			map.put("last_Used_Date", format.format(room.getLast_Used_Date()));
			map.put("department_ID", String.valueOf(room.getDepartment_ID()));
			map.put("room_Status", String.valueOf(room.getRoom_Status()));
			JSONObject object = new JSONObject(map);
			result.add(object);
		}
		return result;
	}

	@Override
	public List<Room> getRoomsBydepartmentToObject(int department_ID) {
		return roomDao.getRoomsBydepartment(department_ID);
	}

	@Override
	public List<Room> getRoomByStatus(int status) {
		return roomDao.getRoomByStatus(status);
	}

	@Override
	public boolean isRoomExit(Room room) {
		return roomDao.isRoomExit(room);
	}
}
