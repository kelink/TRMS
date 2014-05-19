package com.dummy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;

@Controller
@RequestMapping(value = { "/book" })
public class BookController {

	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@RequestMapping(value = { "/index", "" })
	public ModelAndView index() {
		return new ModelAndView("book/index");
	}

	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}

	private List<Room> getFreeRooms() {
		return roomService.getFreeRooms();
	}

	private List<Room> getBusyRooms() {
		return null;
	}

	private List<Room> getAllRooms() {
		return null;
	}

	// 获取所有的队伍信息

	// // 分页
	// @RequestMapping("/listPosition")
	// public String listPosition(
	// Model model,
	// @RequestParam(value = "pagesize", required = false, defaultValue = "5")
	// int pageSize,
	// @RequestParam(value = "pagenum", required = false, defaultValue = "1")
	// int pageNum) {
	// int recordCount = roomService.getAllRoom().size();
	// int pageCount = (recordCount + pageSize - 1) / pageSize;
	// model.addAttribute("pageTitle", "Position List");
	// return "positionlist";
	// }
	//
	// @RequestMapping("/roomList")
	// public @ResponseBody List<Room> roomList(
	// Model model,
	// @RequestParam(value = "pagesize", required = false, defaultValue = "5")
	// int pageSize,
	// @RequestParam(value = "pagenum", required = false, defaultValue = "1")
	// int pageNum) {
	// List<Room> roomPageList = roomService.getRoomOnPage(pageNum, pageSize);
	// return roomPageList;
	// }

	// @RequestMapping(value = { "/book" })
	// public ModelAndView book(Reservation reservation) {
	// List<Room> rooms = this.getFreeRooms();
	// ModelMap map = new ModelMap();
	// map.addAttribute("rooms", rooms);
	// return new ModelAndView("bookRoom", map);
	// }

	// // 测试ajax
	@RequestMapping(value = { "/test" })
	public void test(HttpServletRequest request, HttpServletResponse response) {
		logger.info("进入测试/test");
		String result = "{\"name\":\"" + "test" + "\"}";
		PrintWriter out = null;
		System.out.println(result);
		response.setContentType("application/json");
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
