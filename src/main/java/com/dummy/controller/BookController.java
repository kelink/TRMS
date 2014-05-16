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

@Controller
@RequestMapping(value = { "/user*/book" })
public class BookController {
	@Resource(name="reservationService")
	private ReservationService reservationService;
	
	@RequestMapping("/bookRoom")
	public ModelAndView bookRoom(
			@RequestParam("reservation") Reservation reservation) {
		return null;
	}

	public List<Room> getVariableRooms() {
		return null;

	}

	public List<Room> getBusyRooms() {
		return null;
	}
}
