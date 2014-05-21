package com.dummy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/profile")
public class profileController {

	@RequestMapping(value = "/modify")
	public ModelAndView calendar(HttpServletRequest request) {

		return new ModelAndView("user/profile");
	}
}
