package com.dummy.service;

import java.util.List;

import com.dummy.domain.Department;

public interface DepartmentService {
	public Department getDepartment(int id);

	public List<Department> getAllDepartment();

	public void addDepartment(Department department);

	public boolean delDepartment(int id);

	public boolean updateDepartment(Department department);
}
