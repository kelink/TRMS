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
import org.springframework.stereotype.Service;

import com.dummy.domain.DBUser;
import com.dummy.service.UserService;

/**
 * һ���Զ��������������ݿ���в���.
 * ���Ժ�����Ҫͨ����ݿⱣ��Ȩ��.����Ҫ���Ǽ̳�UserDetailsService
 * 
 * @author
 * 
 */
@Service(value = "customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger = LoggerFactory
			.getLogger(CustomUserDetailsService.class);
	@Resource(name = "userService")
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {

		UserDetails user = null;

		try {

			// ������ݿ���ƥ���û���¼��.
			// ���ǿ���ͨ��daoʹ��Hibernate��������ݿ�
			logger.info(username + "   �û�ҳ��������û���");
			logger.info("  userService:" + userService);
			DBUser dbUser = userService.getUserByAccount(username);
			logger.info(dbUser.getAccount() + "   ��ݿ�ȡ�����˺�");
			// �û������롢�Ƿ����á��Ƿ����Ƿ���ڡ�Ȩ��
			user = new User(dbUser.getAccount(), dbUser.getPassword()
					.toLowerCase(), true, true, true, true,
					getAuthorities(new Integer(dbUser.getAccess())));

		} catch (Exception e) {
			logger.error("�û���Ϣ����");
			throw new UsernameNotFoundException("�쳣���?�����û���Ϣδͨ��");
		}

		return user;
	}

	/**
	 * ��÷��ʽ�ɫȨ���б�
	 * 
	 * @param access
	 * @return
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer role) {
		logger.info("ȡ�õ�Ȩ����  :" + role);
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();

		// ���е��û�Ĭ��ӵ��ROLE_USERȨ��
		if (role == 1) {
			System.out.println("��ͨ�û�");
			logger.debug("ȡ����ͨ�û�Ȩ��-->");
			authList.add(new GrantedAuthorityImpl("ROLE_LC"));
		}
		// ������roleΪ1.��ӵ��ROLE_ADMINȨ��
		if (role == 2) {
			logger.debug("ȡ��ADMIN�û�Ȩ��-->");
			authList.add(new GrantedAuthorityImpl("ROLE_TA"));
		}
		System.out.println(authList.size() + "  Ȩ���б?��");
		return authList;
	}
}