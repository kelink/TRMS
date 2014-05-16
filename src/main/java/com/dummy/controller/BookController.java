package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;

@Controller
@RequestMapping(value = { "/book" })
public class BookController {
	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@RequestMapping("/index")
	public ModelAndView index(
			@RequestParam("reservation") Reservation reservation) {
		List<Room> variableRooms = getVariableRooms();
		List<Room> getBusyRooms = getVariableRooms();
		return null;
	}

	public ModelAndView book() {
		return null;
	}

	public List<Room> getVariableRooms() {
		return null;

	}

	public List<Room> getBusyRooms() {
		return null;
	}

	public List<Room> getAllRooms() {
		return null;
	}
}
