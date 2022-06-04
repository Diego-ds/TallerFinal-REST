package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.Optional;

import com.example.tallerdiegogarcia.delegate.interfaces.DepartmentDelegate;
import com.example.tallerdiegogarcia.delegate.interfaces.DepartmenthistoryDelegate;
import com.example.tallerdiegogarcia.model.Department;
import com.example.tallerdiegogarcia.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartmentDelegateImp implements DepartmentDelegate{
	
	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/api/departmentRest/";
	
	@Override
	public Iterable<Department> findAll() {
		Department [] departments = template.getForObject(WEB_PATH,Department[].class);
		return Arrays.asList(departments);
	}

	@Override
	public Optional<Department> findById(Integer id) {
		Department department = template.getForObject(WEB_PATH+id, Department.class);
		return Optional.of(department);
	}

	@Override
	public void delete(Department department) {
		template.delete(WEB_PATH+department.getDepartmentid());
	}

	@Override
	public Department addDepartment(Department department) {
		return template.postForObject(WEB_PATH, department, Department.class);
	}

	@Override
	public void editDepartment(Department department) {
		template.put(WEB_PATH, department);
	}

}
