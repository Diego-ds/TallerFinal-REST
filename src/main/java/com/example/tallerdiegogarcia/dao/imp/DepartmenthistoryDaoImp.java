package com.example.tallerdiegogarcia.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.tallerdiegogarcia.dao.interfaces.DepartmenthistoryDao;
import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmenthistoryDaoImp implements DepartmenthistoryDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public DepartmenthistoryDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void save(Employeedepartmenthistory edh) {
		entityManager.persist(edh);		
	}

	@Override
	public void update(Employeedepartmenthistory edh) {
		entityManager.merge(edh);
	}

	@Override
	public void delete(Employeedepartmenthistory edh) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(edh) ? edh : entityManager.merge(edh));
	}

	@Override
	public List<Employeedepartmenthistory> findAll() {
		String request = "Select p from Employeedepartmenthistory p";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Employeedepartmenthistory findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Employeedepartmenthistory.class, id);
	}

}
