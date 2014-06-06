package com.dummy.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import com.dummy.domain.Department;
import com.dummy.domain.Reservation;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.BlackListService;
import com.dummy.service.DepartmentService;
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

	@Resource(name = "departmentService")
	private DepartmentService departmentService;

	// head Information
	@RequestMapping("/headInfo")
	public ModelAndView list(Model model) {

		return new ModelAndView("room/headInfo");
	}

	// getRoom
	@RequestMapping("/getForm")
	public @ResponseBody String getForm(HttpServletRequest request,
			HttpSession session) {

		int room_ID = Integer.parseInt(request.getParameter("room_ID"));
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		if (month.length() == 1) {
			month = "0" + month;
		}
		if (day.length() == 1) {
			day = "0" + day;
		}
		String select_date = year + "-" + month + "-" + day;
		Room room = roomService.getRoom(room_ID);
		List<Team> teams = null;
		// role_LC can book limit room
		String currentRole = (String) session.getAttribute("currentRole");
		DBUser currentUser = (DBUser) session.getAttribute("currentUser");
		if (currentRole.equals("ROLE_LC")) {
			teams = teamService.getTeamByUser(currentUser.getUser_ID());
		} else if (currentRole.equals("ROLE_TA")) {
			teams = teamService.getAllTeam();
		}
		HashMap<String, ArrayList<JSONObject>> result = new HashMap<String, ArrayList<JSONObject>>();

		// 封装teams
		ArrayList<JSONObject> teamList = new ArrayList<JSONObject>();
		for (Team team : teams) {
			HashMap<String, String> tempMap1 = new HashMap<String, String>();
			tempMap1.put("team_ID", String.valueOf(team.getTeam_ID()));
			tempMap1.put("user_ID", String.valueOf(team.getUser_ID()));
			tempMap1.put("department_ID",
					String.valueOf(team.getDepartment_ID()));
			tempMap1.put("teamName", team.getTeamName());
			JSONObject object = new JSONObject(tempMap1);
			teamList.add(object);
		}
		// 封装room
		ArrayList<JSONObject> roomList = new ArrayList<JSONObject>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<String, String> tempMap2 = new HashMap<String, String>();
		tempMap2.put("room_ID", String.valueOf(room.getRoom_ID()));
		tempMap2.put("item", room.getItem());
		tempMap2.put("last_Used_Date", format.format(room.getLast_Used_Date()));
		tempMap2.put("department_ID", String.valueOf(room.getDepartment_ID()));
		tempMap2.put("room_Status", String.valueOf(room.getRoom_Status()));
		JSONObject roomJson = new JSONObject(tempMap2);
		roomList.add(roomJson);

		// 封装select Date
		ArrayList<JSONObject> selectDateList = new ArrayList<JSONObject>();
		HashMap<String, String> tempMap3 = new HashMap<String, String>();
		tempMap3.put("select_date", select_date);
		JSONObject selectDateJson = new JSONObject(tempMap3);
		selectDateList.add(selectDateJson);

		result.put("select_date", selectDateList);
		result.put("roomList", roomList);
		result.put("teamList", teamList);

		JSONObject resultJsonObject = new JSONObject(result);
		System.out.println("resultJsonObject.toString()-------------->>>"
				+ resultJsonObject.toString());
		return resultJsonObject.toString();

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
	@SuppressWarnings("null")
	@RequestMapping(value = "/bookRoom")
	public ModelAndView bookRoom(
			HttpServletRequest request,
			HttpSession session,
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
		// 1. create a reservation
		Reservation reservation = new Reservation();
		String reservation_Num = ReservationUtil.getUniqueSequence();

		reservation.setApplied_End_Date(endDate);
		reservation.setApplied_Start_Date(beginDate);
		reservation.setEmail(email);
		reservation.setOrder_Time(nowDate);
		reservation.setPurpose(purpose);
		reservation.setRoom_ID(room_ID);
		reservation.setTeam_ID(team_ID);
		reservation.setUser_ID(currentUser.getUser_ID());
		reservation.setTele(tele);
		reservation.setReservation_Num(reservation_Num);

		if (session.getAttribute("currentRole").equals("ROLE_LC")) {
			reservation.setHandle_by(C.DB.DEFAULT_APPROVE_BY);
			reservation.setStatus(C.DB.DEFAULT_RESERVATION_UNHANDLE);
		} else if (session.getAttribute("currentRole").equals("ROLE_TA")) {
			reservation.setHandle_by(currentUser.getUser_ID());
			reservation.setStatus(C.DB.DEFAULT_RESERVATION_ACCEPT);
		}

		System.out.println(reservation);
		map.addAttribute("reservation_Num", reservation_Num);
		// 3.Send Email
		// List<DBUser> admins = userService
		// .getUserByRole(C.DB.DEFAULT_ROLE_TA);
		// List<String> toAddressList = new ArrayList<String>();
		// for (DBUser dbUser : admins) {
		// toAddressList.add(dbUser.getAccount());
		// MailSender.sendEmailToAllAdmin(toAddressList, null, null);
		// }

		// 4.add reservation
		reservationService.addReservation(reservation);
		return new ModelAndView("room/success", map);

	}

	// 判断是否属于黑名单
	@RequestMapping("/isInBlackList")
	public @ResponseBody String isInBlackList(
			@RequestParam(value = "team_ID", required = true) int team_ID) {
		System.out.println("接收到的team_ID------》" + team_ID);
		BlackList blacklist = blackListService
				.getBlackListByTeamToObject(team_ID);
		if (blacklist != null) {
			return "team is in the blackList,Please Contact Administrator";
		} else {
			return "";
		}
	}
	// get add reservation
	@RequestMapping("/getAllReservation")
	public List<Reservation> getAllReservation(HttpServletResponse response) {
		return reservationService.getAllReservation();
	}

	/*********************************
	 * for administrator
	 *********************************/
	@RequestMapping("/roommanager")
	public ModelAndView roommanager() {
		List<Department> departments = departmentService.getAllDepartment();
		ModelMap map = new ModelMap();
		map.addAttribute("departments", departments);
		return new ModelAndView("room/roomManager", map);
	}

	// AJAX to get all the room of a department
	@RequestMapping("/getRoomsBydepartment")
	public @ResponseBody String getRoomsBydepartment(
			@RequestParam(value = "department_ID", required = true) int department_ID) {
		List<JSONObject> list = roomService.getRoomsBydepartment(department_ID);
		return list.toString();
	}

	@RequestMapping("/add")
	public ModelAndView add() {
		return null;
	}
	@RequestMapping("/update")
	public ModelAndView Update(
			@RequestParam(value = "department_ID", required = true) int department_ID) {
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