package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.tallerdiegogarcia.delegate.interfaces.DepartmenthistoryDelegate;
import com.example.tallerdiegogarcia.model.Department;
import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartmenthistoryDelegateImp implements DepartmenthistoryDelegate{

	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/api/departmenthistoryRest/";
	
	@Override
	public Iterable<Employeedepartmenthistory> findAll() {
		Employeedepartmenthistory[] history = template.getForObject(WEB_PATH,Employeedepartmenthistory[].class);
		return Arrays.asList(history);
	}

	@Override
	public Optional<Employeedepartmenthistory> findById(Integer id) {
		Employeedepartmenthistory edh = template.getForObject(WEB_PATH+id, Employeedepartmenthistory.class);
		return Optional.of(edh);
	}

	@Override
	public void delete(Employeedepartmenthistory edh) {
		template.delete(WEB_PATH+edh.getId());
	}

	@Override
	public Employeedepartmenthistory addDepartmenthistory(Employeedepartmenthistory edh) {
		return template.postForObject(WEB_PATH, edh, Employeedepartmenthistory.class);
	}

	@Override
	public void editDepartmenthistory(Employeedepartmenthistory edh) {
		template.put(WEB_PATH, edh);
	}
	
	@Override
	public List<Employeedepartmenthistory> findByDepartment(Integer id) {
		String url = WEB_PATH + "associated/" + id;
		
		Employeedepartmenthistory[] history = template.getForObject(url,Employeedepartmenthistory[].class);
		return Arrays.asList(history);
	}

}
