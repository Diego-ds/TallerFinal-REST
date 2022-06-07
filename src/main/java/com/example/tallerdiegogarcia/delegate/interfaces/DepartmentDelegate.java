package com.example.tallerdiegogarcia.delegate.interfaces;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Department;

public interface DepartmentDelegate {
	public Iterable<Department> findAll();
	public Optional<Department> findById(Integer id);
	public void delete(Department department);
	public Department addDepartment(Department department);
	public void editDepartment(Department department);
}
