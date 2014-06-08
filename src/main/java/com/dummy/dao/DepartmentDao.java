package com.dummy.dao;

import java.util.List;

import com.dummy.domain.Department;

public interface DepartmentDao {
	public Department getDepartment(int id);

	public List<Department> getAllDepartment();

	public void addDepartment(Department department);

	public boolean delDepartment(int id);

	public boolean updateDepartment(Department department);

	public boolean isDepartmentExit(String departmentName);

}
