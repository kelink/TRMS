package com.dummy.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dummy.domain.DBUser;

public class SessionInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(SessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object o) throws Exception {
		DBUser user = (DBUser) request.getSession().getAttribute("currentUser");
		if (user == null) {
			logger.info("user not login");
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(
					request, response);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1,
			Object o, ModelAndView mav) throws Exception {
		hsr1.sendRedirect("/login.jsp");
	}

	@Override
	public void afterCompletion(HttpServletRequest hsr,
			HttpServletResponse hsr1, Object o, Exception excptn)
			throws Exception {
		logger.info("afterCompletion");
	}
}
