package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;

@Controller
@RequestMapping(value = { "/room" })
public class RoomController {

	private static final Logger logger = LoggerFactory
			.getLogger(RoomController.class);

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "teamService")
	private TeamService teamService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@RequestMapping(value = { "/index", "" })
	public ModelAndView index() {
		List<Room> freeRooms = roomService.getFreeRooms();
		List<Team> teams = teamService.getAllTeam();
		ModelMap map = new ModelMap();
		map.addAttribute("freeRooms", freeRooms);
		map.addAttribute("teams", teams);
		return new ModelAndView("room/index", map);
	}

	public ModelAndView bookRoom() {
		return new ModelAndView("room/success");
	}

	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}

	// 获取所有的队伍信息

	// 分页list 所有的房间
	@RequestMapping("/list")
	public ModelAndView list(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		int recordCount = roomService.getAllRoom().size();
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		ModelMap map = new ModelMap();
		map.addAttribute("recordCount", recordCount);
		map.addAttribute("pageCount", pageCount);
		List<Room> rooms = roomService.getAllRoom();
		map.addAttribute("rooms", rooms);
		return new ModelAndView("room/list", map);
	}

	@RequestMapping("/listPageRoom")
	public @ResponseBody List<Room> listPageRoom(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		List<Room> roomPageList = roomService.getRoomOnPage(pageNum, pageSize);
		return roomPageList;
	}

	@RequestMapping(value = "/calendar")
	public ModelAndView calendar(HttpServletRequest request) {
		// int room_ID = Integer.parseInt(request.getParameter("room_ID"));
		int room_ID = 2;
		String calendarData = reservationService.getCalanderData(room_ID);
		ModelMap map = new ModelMap();
		map.addAttribute("calendarData", calendarData);
		System.out.println(calendarData);
		return new ModelAndView("user/calendar", map);
	}

}
