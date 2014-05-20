package com.dummy.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.common.C;
import com.dummy.domain.DBUser;
import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;

@Controller
@SessionAttributes({ "currentUser" })
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

	@Resource(name = "userService")
	private UserService userService;

	// Room 管理主界面
	@RequestMapping(value = { "/check", "" })
	public ModelAndView check(HttpServletRequest request) {
		int room_ID = Integer.parseInt(request.getParameter("room_ID"));
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		Date date = new Date(year, month, day);
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String bookDate = format1.format(date);
		Room room = roomService.getRoom(room_ID);
		List<Team> teams = teamService.getAllTeam();
		ModelMap map = new ModelMap();
		map.addAttribute("room", room);
		map.addAttribute("teams", teams);
		map.addAttribute("bookDate", bookDate);
		return new ModelAndView("room/check", map);
	}

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

	// 用于AJAX获取房间信息
	@RequestMapping("/listPageRoom")
	public @ResponseBody List<Room> listPageRoom(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		List<Room> roomPageList = roomService.getRoomOnPage(pageNum, pageSize);
		return roomPageList;
	}

	// 显示日历
	@RequestMapping(value = "/calendar")
	public ModelAndView calendar(HttpServletRequest request) {
		int room_ID = Integer.parseInt(request.getParameter("room_ID"));
		String calendarData = reservationService.getCalanderData(room_ID);
		ModelMap map = new ModelMap();
		map.addAttribute("calendarData", calendarData);
		map.addAttribute("room_ID", room_ID);
		System.out.println(calendarData);
		return new ModelAndView("room/calendar", map);
	}

	// 订房逻辑
	@RequestMapping(value = "/bookRoom")
	public ModelAndView bookRoom(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		// 1.当前时间到月尾内 空闲的房间可以预定
		int room_ID = Integer.parseInt(request.getParameter("room"));
		String begin_time = request.getParameter("begin_time");
		String end_time = request.getParameter("end_time");
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String email = request.getParameter("email");
		String tele = request.getParameter("userTelLine");
		String purpose = request.getParameter("purpose");
		int team_ID = Integer.parseInt(request.getParameter("team"));
		Date beginDate = null;
		Date endDate = null;
		Date nowDate = null;
		try {
			beginDate = (Date) format1.parse(begin_time);
			endDate = (Date) format1.parse(end_time);
			String now = format1.format(new java.util.Date());
			nowDate = (Date) format1.parse(now);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Reservation reservation = new Reservation();
		reservation.setApplied_End_Date(endDate);
		reservation.setApplied_Start_Date(beginDate);
		reservation.setEmail(email);
		reservation.setOrder_Time(nowDate);
		reservation.setPurpose(purpose);
		reservation.setRoom_ID(room_ID);
		reservation.setStatus(C.DB.DEFAULT_RESERVATION_UNHANDLE);
		reservation.setTeam_ID(team_ID);
		reservation.setUser_ID(currentUser.getUser_ID());
		reservation.setTele(tele);
		System.out.println(reservation);
		reservationService.addReservation(reservation);
		return new ModelAndView("room/success");
	}

	// 获得所有订单信息
	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}
}
