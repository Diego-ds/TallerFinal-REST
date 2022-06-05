package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tallerdiegogarcia.dao.interfaces.DepartmenthistoryDao;
import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

@Service
@Transactional
public class DepartmenthistoryServiceImp implements DepartmenthistoryService{

	@Autowired
	private DepartmenthistoryDao historyDao;
	
	
	@Override
	public Employeedepartmenthistory addDepartmenthistory(Employeedepartmenthistory department) {
		historyDao.save(department);
		return department;
	}

	@Override
	public Iterable<Employeedepartmenthistory> findAll() {
		return historyDao.findAll();
	}

	@Override
	public Optional<Employeedepartmenthistory> findById(Integer id) {
		return Optional.of(historyDao.findById(id));
	}

	@Override
	public void delete(Employeedepartmenthistory history) {
		historyDao.delete(history);
	}

	@Override
	public Employeedepartmenthistory editDepartmenthistory(Employeedepartmenthistory department) {
		historyDao.update(department);
		return department;
	}
}
