package com.dummy.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.domain.ReservationDetial;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;

@Controller
@SessionAttributes({ "currentUser" })
@RequestMapping(value = "/reservation")
public class ReservationController {

	@Resource(name = "teamService")
	private TeamService teamService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	// 检索主界面
	@RequestMapping(value = { "/checkIndex", "", "/index" })
	public ModelAndView checkIndex() {
		List<Room> rooms = roomService.getAllRoom();
		List<Team> teams = teamService.getAllTeam();
		ModelMap map = new ModelMap();
		map.addAttribute("rooms", rooms);
		map.addAttribute("teams", teams);
		System.out.println(map);
		return new ModelAndView("reservation/check", map);
	}

	// 按条件检索
	@RequestMapping(value = { "/checkReservations" })
	public ModelAndView checkReservations(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		String reservation_Num = request.getParameter("reservation_Num");
		System.out.println(reservation_Num);
		String team_ID = request.getParameter("team_ID");
		String room_ID = request.getParameter("room_ID");
		String user_ID = String.valueOf(currentUser.getUser_ID());
		String purpose = request.getParameter("purpose");
		String status = request.getParameter("status");
		String email = request.getParameter("email");
		String tele = request.getParameter("tele");
		String Applied_Start_Date = request.getParameter("Applied_Start_Date");
		String Applied_End_Date = request.getParameter("Applied_End_Date");
		String order_Time = request.getParameter("order_Time");
		if (reservation_Num != null && reservation_Num.length() > 0) {
			hashMap.put("reservation_Num", reservation_Num);
		}
		if (team_ID != null && team_ID.length() > 0) {
			hashMap.put("team_ID", team_ID);
		}
		if (room_ID != null && room_ID.length() > 0) {
			hashMap.put("room_ID", room_ID);
		}
		if (user_ID != null && user_ID.length() > 0) {
			hashMap.put("user_ID", user_ID);
		}
		if (purpose != null && purpose.length() > 0) {
			hashMap.put("purpose", purpose);
		}
		if (status != null && status.length() > 0) {
			hashMap.put("status", status);
		}
		if (tele != null && tele.length() > 0) {
			hashMap.put("tele", tele);
		}
		if (email != null && email.length() > 0) {
			hashMap.put("email", email);
		}
		if (Applied_Start_Date != null && Applied_Start_Date.length() > 0) {
			hashMap.put("Applied_Start_Date", Applied_Start_Date);
		}
		if (Applied_End_Date != null && Applied_End_Date.length() > 0) {
			hashMap.put("Applied_End_Date", Applied_End_Date);
		}
		if (Applied_End_Date != null && Applied_End_Date != null
				&& Applied_End_Date.length() > 0) {
			hashMap.put("Applied_End_Date", Applied_End_Date);
		}
		if (order_Time != null && order_Time.length() > 0) {
			hashMap.put("order_Time", order_Time);
		}

		System.out.println("hashMap--------->" + hashMap);
		List<ReservationDetial> reservationDetials = reservationService
				.getReservationByOption(hashMap);
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("reservationDetials", reservationDetials);
		return new ModelAndView("reservation/list", modelMap);
	}
}
