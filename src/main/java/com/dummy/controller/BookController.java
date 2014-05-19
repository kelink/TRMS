package com.dummy.controller;

import java.util.List;

import javax.annotation.Resource;
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
@RequestMapping(value = { "/book" })
public class BookController {

	private static final Logger logger = LoggerFactory
			.getLogger(BookController.class);

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
		return new ModelAndView("book/index", map);
	}

	public ModelAndView bookRoom() {
		return new ModelAndView("book/success");
	}

	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}

	// 获取所有的队伍信息

	// 分页list 所有的房间
	@RequestMapping("/listRoom")
	public ModelAndView listRoom(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		int recordCount = roomService.getAllRoom().size();
		int pageCount = (recordCount + pageSize - 1) / pageSize;
		ModelMap map = new ModelMap();
		map.addAttribute("recordCount", recordCount);
		map.addAttribute("pageCount", pageCount);
		return new ModelAndView("book/testPage", map);
	}

	@RequestMapping("/listPageRoom")
	public @ResponseBody List<Room> listPageRoom(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		List<Room> roomPageList = roomService.getRoomOnPage(pageNum, pageSize);
		return roomPageList;
	}

	// // 测试ajax
	// @RequestMapping(value = { "/test" })
	// public void test(HttpServletRequest request, HttpServletResponse
	// response) {
	// logger.info("进入测试/test");
	// String result = "{\"name\":\"" + "test" + "\"}";
	// PrintWriter out = null;
	// System.out.println(result);
	// response.setContentType("application/json");
	// try {
	// out = response.getWriter();
	// out.write(result);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

}
