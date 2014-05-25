package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dummy.dao.RoleDao;
import com.dummy.domain.Role;
import com.dummy.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Resource(name = "roleDao")
	private RoleDao roleDao;

	@Override
	public Role getRole(int id) {
		return roleDao.getRole(id);
	}
	@Override
	public List<Role> getAllRole() {
		return roleDao.getAllRole();
	}

	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

	@Override
	public boolean delRole(int id) {
		return roleDao.delRole(id);
	}

	@Override
	public boolean updateRole(Role Role) {
		return roleDao.updateRole(Role);
	}

}
