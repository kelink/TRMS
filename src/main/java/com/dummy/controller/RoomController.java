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
import com.dummy.domain.BlackList;
import com.dummy.domain.DBUser;
import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.BlackListService;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;
import com.dummy.util.ReservationUtil;

@Controller
@SessionAttributes({"currentUser"})
@RequestMapping(value = {"/room"})
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

	@Resource(name = "blackListService")
	private BlackListService blackListService;

	// getRoom
	@RequestMapping(value = {"/getForm", ""})
	public ModelAndView getForm(HttpServletRequest request) {
		int room_ID = Integer.parseInt(request.getParameter("room_ID"));
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			month = "0" + month;
		}
		String select_date = year + "-" + month + "-" + day;
		Room room = roomService.getRoom(room_ID);
		List<Team> teams = teamService.getAllTeam();
		ModelMap map = new ModelMap();
		map.addAttribute("room", room);
		map.addAttribute("teams", teams);
		map.addAttribute("select_date", select_date);
		return new ModelAndView("room/form", map);
	}

	// Room list
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

	// AJAX get the roomInfo
	@RequestMapping("/listPageRoom")
	public @ResponseBody List<Room> listPageRoom(
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum) {
		List<Room> roomPageList = roomService.getRoomOnPage(pageNum, pageSize);
		return roomPageList;
	}

	// request calendar
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

	// book room
	@RequestMapping(value = "/bookRoom")
	public ModelAndView bookRoom(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "team_ID", required = true) int team_ID,
			@RequestParam(value = "room_ID", required = true) int room_ID,
			@RequestParam(value = "begin_time", required = true) String begin_time,
			@RequestParam(value = "end_time", required = true) String end_time,
			@RequestParam(value = "email", required = true) String email,
			@RequestParam(value = "tele", required = true) String tele,
			@RequestParam(value = "purpose", required = true) String purpose,
			@ModelAttribute("currentUser") DBUser currentUser) {
		ModelMap map = new ModelMap();
		// 1.whether is in the blacklist
		BlackList blacklist = blackListService.getBlackList(team_ID);
		if (blacklist != null) {
			map.addAttribute("blacklist", blacklist);
			return new ModelAndView("room/fail", map);
		} else {
			DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

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
			// 2. create a reservation
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
			reservation.setReservation_Num(ReservationUtil.getUniqueSequence());
			reservation.setApprove_by(C.DB.DEFAULT_APPROVE_BY);
			System.out.println(reservation);
			// 3.Send Email

			reservationService.addReservation(reservation);
			return new ModelAndView("room/success");
		}

	}
	// get add reservation
	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}
	// for admin to manager the room
	@RequestMapping("/roommanager")
	public ModelAndView roomManager() {
		return new ModelAndView("room/roomManager");
	}
	@RequestMapping("/add")
	public ModelAndView add() {
		return null;
	}
	@RequestMapping("/delete")
	public ModelAndView delete() {
		return null;
	}
	@RequestMapping("/check")
	public ModelAndView check() {
		return null;
	}

}
