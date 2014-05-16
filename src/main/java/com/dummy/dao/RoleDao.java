package com.dummy.dao;

import java.util.List;

import com.dummy.domain.Role;

public interface RoleDao {
	public Role getRole(int id);

	public List<Role> getAllRole();

	public void addRole(Role role);

	public boolean delRole(int id);

	public boolean updateRole(Role Role);
}
