package com.example.tallerdiegogarcia.dao.interfaces;

import java.util.List;

import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

public interface DepartmenthistoryDao {
	public void save(Employeedepartmenthistory edh);
	public void update(Employeedepartmenthistory edh);
	public void delete(Employeedepartmenthistory edh);
	public List<Employeedepartmenthistory> findAll();
	public Employeedepartmenthistory findById(Integer id);
	public List<Employeedepartmenthistory> findByDepartment(Integer id);
}
