package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.dao.interfaces.DepartmentDao;
import com.example.tallerdiegogarcia.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentServiceImp implements DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	
	@Override
	public Department addDepartment(Department department) {
		departmentDao.save(department);
		return department;
	}

	@Override
	public Iterable<Department> findAll() {
		return departmentDao.findAll();
	}

	@Override
	public Optional<Department> findById(Integer id) {
		return Optional.of(departmentDao.findById(id));
	}

	@Override
	public void delete(Department Department) {
		departmentDao.delete(Department);
	}

	@Override
	public Department editDepartment(Department department) {
		departmentDao.update(department);
		return department;
	}
}
