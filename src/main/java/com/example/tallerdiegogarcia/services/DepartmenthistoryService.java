package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

public interface DepartmenthistoryService {
	public Iterable<Employeedepartmenthistory> findAll();
	public Optional<Employeedepartmenthistory> findById(Integer id);
	public void delete(Employeedepartmenthistory history);
	public Employeedepartmenthistory addDepartmenthistory(Employeedepartmenthistory history);
	public Employeedepartmenthistory editDepartmenthistory(Employeedepartmenthistory history);
}
