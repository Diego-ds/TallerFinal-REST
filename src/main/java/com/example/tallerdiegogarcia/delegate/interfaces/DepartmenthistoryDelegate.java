package com.example.tallerdiegogarcia.delegate.interfaces;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

public interface DepartmenthistoryDelegate {
	public Iterable<Employeedepartmenthistory> findAll();
	public Optional<Employeedepartmenthistory> findById(Integer id);
	public void delete(Employeedepartmenthistory department);
	public Employeedepartmenthistory addDepartmenthistory(Employeedepartmenthistory department);
	public void editDepartmenthistory(Employeedepartmenthistory department);
}
