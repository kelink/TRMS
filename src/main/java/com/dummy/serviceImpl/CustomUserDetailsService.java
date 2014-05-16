package com.dummy.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dummy.domain.DBUser;

/**
 * 一个自定义的类用来和数据库进行操作. 即以后我们要通过数据库保存权限.则需要我们继承UserDetailsService
 * 
 * @author
 * 
 */
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory
			.getLogger(CustomUserDetailsService.class);
	@Resource(name = "userService")
	private UserServiceImpl userService;

	public UserServiceImpl getUsersService() {
		return userService;
	}

	public void setUsersService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {

			// 搜索数据库以匹配用户登录名.
			// 我们可以通过dao使用Hibernate来访问数据库
			logger.info(username + "   用户页面输入的用户名");
			logger.info("  userService:" + userService);
			DBUser dbUser = userService.getUserByAccount(username);
			logger.info(dbUser.getAccount() + "   数据库取出的账号");
			// 用户名、密码、是否启用、是否被锁定、是否过期、权限
			user = new User(dbUser.getAccount(), dbUser.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(new Integer(dbUser.getAccess())));

		} catch (Exception e) {
			logger.error("用户信息错误！");
			throw new UsernameNotFoundException("异常处理：检索用户信息未通过！");
		}

		return user;
	}

	/**
	 * 获得访问角色权限列表
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer role) {
		logger.info("取得的权限是  :" + role);
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		// 所有的用户默认拥有ROLE_USER权限
		if (role == 1) {
			System.out.println("普通用户");
			logger.debug("取得普通用户权限-->");
			authList.add(new GrantedAuthorityImpl("ROLE_LC"));
		}
		// 如果参数role为1.则拥有ROLE_ADMIN权限
		if (role == 2) {
			logger.debug("取得ADMIN用户权限-->");
			authList.add(new GrantedAuthorityImpl("ROLE_TA"));
		}
		System.out.println(authList.size() + "  权限列表长度");
		return authList;
	}
}