package com.dummy.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@SessionAttributes({ "currentUser" })
@Controller
@RequestMapping("/profile")
public class profileController {

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "/modify")
	public ModelAndView modify(HttpServletRequest request,
			@ModelAttribute("currentUser") DBUser currentUser) {
		ModelMap map = new ModelMap();
		map.addAttribute("currentUser", currentUser);
		return new ModelAndView("user/profile", map);
	}

	// Edit your password
	// @RequestMapping(value = "/changeInfo")
	// public ModelAndView changepwd(HttpServletRequest request,
	// @ModelAttribute("currentUser") DBUser currentUser) {
	// String password = request.getParameter("new_password").trim();
	// currentUser.setPassword(password);
	// // 注销当前账号，跳转到登陆界面
	//
	// }

}
