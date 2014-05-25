package com.dummy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.domain.Reservation;
import com.dummy.domain.ReservationDetial;
import com.dummy.domain.Room;
import com.dummy.domain.Team;
import com.dummy.service.ReservationService;
import com.dummy.service.RoomService;
import com.dummy.service.TeamService;
import com.dummy.service.UserService;
import com.dummy.util.ReservationUtil;

@Controller
@SessionAttributes({"currentUser"})
@RequestMapping(value = "/reservation")
public class ReservationController {

	@Resource(name = "teamService")
	private TeamService teamService;

	@Resource(name = "roomService")
	private RoomService roomService;

	@Resource(name = "reservationService")
	private ReservationService reservationService;

	@Resource(name = "userService")
	private UserService userService;

	// reservation list
	@RequestMapping(value = {"/list"})
	public ModelAndView list(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			HttpSession session) {

		String advOption = ReservationUtil.generateQueryOption(request);
		DBUser currentUser = (DBUser) session.getAttribute("currentUser");
		String optionStr = null;
		if (session.getAttribute("currentRole").equals("ROLE_TA")) {
			// 默认搜索的是全部
			if (advOption.length() > 0) {
				optionStr = "where " + advOption;
			}
		}
		if (session.getAttribute("currentRole").equals("ROLE_LC")) {
			// 默认搜索的是当前用户
			if (advOption.length() > 0) {
				optionStr = "where DBUser.user_ID=" + currentUser.getUser_ID()
						+ " and " + advOption;
			} else {
				optionStr = "where DBUser.user_ID=" + currentUser.getUser_ID();
			}

		}
		List<Room> rooms = roomService.getAllRoom();
		List<Team> teams = teamService.getAllTeam();

		int recordCount = reservationService.getReservationByOption(optionStr)
				.size();
		int pageCount = (recordCount + pageSize - 1) / pageSize;

		ModelMap map = new ModelMap();
		map.addAttribute("recordCount", recordCount);
		map.addAttribute("pageCount", pageCount);
		map.addAttribute("rooms", rooms);
		map.addAttribute("teams", teams);
		map.addAttribute("optionStr", optionStr);
		System.out.println("进入方法list       optionStr--------------->>>"
				+ optionStr);
		return new ModelAndView("reservation/list", map);

	}

	// AJAX get the ReservationDetial
	@RequestMapping("/listPageReservation")
	public @ResponseBody List<ReservationDetial> listPageReservation(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "optionStr", required = false, defaultValue = "") String optionStr,
			HttpSession session) {
		System.out.println("接收到的optionStr---" + optionStr);
		List<ReservationDetial> recorderPageList = reservationService
				.getReservationDetialOnPage(pageNum, pageSize, optionStr);
		return recorderPageList;
	}

	@RequestMapping(value = {"/edit"})
	public ModelAndView edit(
			HttpServletRequest request,
			Model model,
			@RequestParam(value = "reservation_ID", required = true) int reservation_ID,
			@RequestParam(value = "room_ID", required = true) int room_ID,
			@RequestParam(value = "team_ID", required = true) int team_ID,
			@RequestParam(value = "applicant_ID", required = true) int applicant_ID,
			@RequestParam(value = "handler_by", required = true) int handler_by,
			HttpSession session) {
		Reservation reservation = reservationService
				.getReservation(reservation_ID);
		Room room = roomService.getRoom(room_ID);
		Team team = teamService.getTeam(team_ID);
		DBUser applicant = userService.getUser(applicant_ID);
		DBUser handler = userService.getUser(handler_by);
		ModelMap map = new ModelMap();
		map.addAttribute("reservation", reservation);
		map.addAttribute("room", room);
		// map.addAttribute("team", team);
		// map.addAttribute("applicant", applicant);
		// map.addAttribute("handler", handler);
		return new ModelAndView("reservation/edit", map);
	}
	// Update the unhandled reservation
	@RequestMapping(value = {"/update"})
	public ModelAndView update(HttpServletRequest request, HttpSession session) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date Applied_Start_Date = null;
		Date Applied_End_Date = null;
		try {
			Applied_Start_Date = format.parse(request
					.getParameter("Applied_Start_Date"));
			Applied_End_Date = format.parse(request
					.getParameter("Applied_End_Date"));

		} catch (ParseException e) {

			e.printStackTrace();
		}

		int reservation_ID = Integer.parseInt(request
				.getParameter("reservation_ID"));
		String email = request.getParameter("email");
		String tele = request.getParameter("tele");
		String purpose = request.getParameter("purpose");

		Reservation reservation = reservationService
				.getReservation(reservation_ID);

		reservation.setApplied_Start_Date(Applied_Start_Date);
		reservation.setApplied_End_Date(Applied_End_Date);
		reservation.setEmail(email);
		reservation.setTele(tele);
		reservation.setPurpose(purpose);
		if (reservationService.updateReservation(reservation)) {
			System.out.println("update success!");
		} else {
			System.out.println("update fail");
		}
		return null;
	}
	@RequestMapping(value = {"/delete"})
	public ModelAndView delete(HttpServletRequest request) {
		return new ModelAndView("reservation/delete");
	}

	// reservation Delete by reservation_id
	@RequestMapping(value = {"/deleteByID"})
	public ModelAndView deleteByID(HttpServletRequest request) {
		int id = Integer.parseInt(request.getParameter("reservation_ID"));
		if (reservationService.delReservation(id) == true) {
			return new ModelAndView("redirect:/reservation/list");
		}
		return new ModelAndView("redirect:/reservation/list");
	}

	// reservation Delete by reservation_num
	@RequestMapping(value = {"/deleteByNum"})
	public ModelAndView deleteByNum(HttpServletRequest request) {
		String num = request.getParameter("reservation_Num");
		if (reservationService.delReservationByNum(num) == true) {
			return new ModelAndView("redirect:/reservation/list");
		}
		return new ModelAndView("redirect:/reservation/list");
	}
	// get reservationDetial by reservation_num

}
