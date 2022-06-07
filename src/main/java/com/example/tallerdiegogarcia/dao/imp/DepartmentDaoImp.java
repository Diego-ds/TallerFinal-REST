package com.example.tallerdiegogarcia.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.tallerdiegogarcia.dao.interfaces.DepartmentDao;
import com.example.tallerdiegogarcia.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDaoImp implements DepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	public DepartmentDaoImp(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void save(Department dep) {
		entityManager.persist(dep);		
	}

	@Override
	public void update(Department dep) {
		entityManager.merge(dep);
	}

	@Override
	public void delete(Department dep) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(dep) ? dep : entityManager.merge(dep));
	}

	@Override
	public List<Department> findAll() {
		String request = "Select p from Department p";
		return 	entityManager.createQuery(request).getResultList();
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Department.class, id);
	}

}
