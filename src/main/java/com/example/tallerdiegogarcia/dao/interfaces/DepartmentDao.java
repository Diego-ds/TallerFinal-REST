package com.example.tallerdiegogarcia.dao.interfaces;

import java.util.List;

import com.example.tallerdiegogarcia.model.Department;

public interface DepartmentDao {
	public void save(Department edh);
	public void update(Department edh);
	public void delete(Department edh);
	public List<Department> findAll();
	public Department findById(Integer id);
}
