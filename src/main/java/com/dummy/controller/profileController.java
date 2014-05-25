package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@SessionAttributes({"currentUser"})
@Controller
@RequestMapping("/profile")
public class profileController {

	@Resource(name = "userService")
	private UserService userService;

	// @RequestMapping(value = "/modify")
	// public ModelAndView modify(HttpServletRequest request,
	// @ModelAttribute("currentUser") DBUser currentUser) {
	// ModelMap map = new ModelMap();
	// // 获得当前用户的team信息
	// // Team user_Team = departmentService.get(currentUser.getDept_ID());
	// // map.addAttribute("currentUser", currentUser);
	// // map.addAttribute("user_Team", user_Team);
	// // return new ModelAndView("user/profile", map);
	// }

	// check old password
	@RequestMapping(value = "/checkOldPwd")
	public @ResponseBody String checkOldPwd(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		String oldpassword = request.getParameter("oldPassword").trim();
		if (oldpassword.equals(currentUser.getPassword())) {
			return "old password auth";
		} else {
			return "old password fail to auth";
		}
	}

	// Edit your password
	@RequestMapping(value = "/changepwd")
	public ModelAndView changepwd(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		String newPassword = request.getParameter("newPassword").trim();
		currentUser.setPassword(newPassword);
		userService.updateUser(currentUser);
		// 使得当前会话失效，重新登录(后面新增)
		return new ModelAndView("redirect:/login/index");

	}
}
