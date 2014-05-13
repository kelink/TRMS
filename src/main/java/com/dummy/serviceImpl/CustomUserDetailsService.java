package com.dummy.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dummy.controller.HomeController;
import com.dummy.daoImpl.UserDaoImpl;
import com.dummy.domain.DBUser;

@Service("customUserDetails")
public class CustomUserDetailsService implements UserDetailsService {
	private UserDaoImpl userDao=new UserDaoImpl();
	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Override
	public UserDetails loadUserByUsername(String account)
			throws UsernameNotFoundException, DataAccessException {
		UserDetails user = null;
		System.out.println(account);
		//try {
			System.out.println(userDao);
			DBUser dbUser = userDao.getUserByAccount(account);
			System.out.println(dbUser);
			System.out.println(dbUser.getAccess());
			System.out.println("-------->>>>>>>>>>>");
			user = new User(dbUser.getAccount(), dbUser.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(dbUser.getAccess()));
//		} catch (Exception e) {
//			logger.error("Error in retrieving user");
//			throw new UsernameNotFoundException("Error in retrieving user");
//		}
		return user;
	}

	@SuppressWarnings("deprecation")
	public Collection<GrantedAuthority> getAuthorities(Integer access) {

		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

		if (access.compareTo(0) == 0) {
			authList.add(new GrantedAuthorityImpl("ROLE_LC"));
		}
		if (access.compareTo(1) == 0) {
			authList.add(new GrantedAuthorityImpl("ROLE_TA"));
		}
		return authList;
	}
}
