package com.dummy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = { "/index", "", "/" })
	public String index() {
		logger.info("---进入/login/index界面-----");
		return "login/loginPage";
	}

	@RequestMapping(value = { "/success" })
	public ModelAndView success() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorityList = userDetails
				.getAuthorities();
		Iterator<? extends GrantedAuthority> iterator = authorityList
				.iterator();
		ArrayList<Object> authoritiesList = new ArrayList<Object>();
		while (iterator.hasNext()) {
			authoritiesList.add(iterator.next());
		}
		String role = (String) authoritiesList.get(0).toString();
		DBUser currentUser = userService.getUserByAccount(userDetails
				.getUsername());
		ModelMap modelMap = new ModelMap();
		logger.info(currentUser + "---login success-----");
		if (role.equals("ROLE_LC")) {
			modelMap.addAttribute("currentUser", currentUser);
			return new ModelAndView("redirect:/user/home_LC", modelMap);
		}
		if (role.equals("ROLE_TA")) {
			modelMap.addAttribute("currentUser", currentUser);
			return new ModelAndView("redirect:/user/home_TA", modelMap);
		} else {

			return null;
		}
	}

	@RequestMapping(value = { "/fail" })
	public String fail() {
		logger.info("---进入用户登陆/fail界面-----");
		return "login/login_fail";
	}
}
