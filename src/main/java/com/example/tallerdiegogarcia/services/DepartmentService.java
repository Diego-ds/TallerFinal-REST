package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Department;

public interface DepartmentService {
	public Iterable<Department> findAll();
	public Optional<Department> findById(Integer id);
	public void delete(Department department);
	public Department addDepartment(Department department);
	public Department editDepartment(Department department);
}
