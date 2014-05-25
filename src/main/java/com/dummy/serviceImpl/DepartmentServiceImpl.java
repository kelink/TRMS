package com.dummy.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dummy.dao.DepartmentDao;
import com.dummy.domain.Department;
import com.dummy.service.DepartmentService;

@Service(value = "departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource(name = "departmentDao")
	private DepartmentDao departmentDao;
	@Override
	public Department getDepartment(int id) {
		return departmentDao.getDepartment(id);
	}

	@Override
	public List<Department> getAllDepartment() {
		return departmentDao.getAllDepartment();
	}

	@Override
	public void addDepartment(Department department) {
		departmentDao.addDepartment(department);
	}

	@Override
	public boolean delDepartment(int id) {
		return departmentDao.delDepartment(id);
	}

	@Override
	public boolean updateDepartment(Department department) {
		return departmentDao.updateDepartment(department);
	}

}
