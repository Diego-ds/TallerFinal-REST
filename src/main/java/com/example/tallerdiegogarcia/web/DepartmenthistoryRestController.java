package com.example.tallerdiegogarcia.web;

import java.util.List;
import java.util.Optional;

import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;
import com.example.tallerdiegogarcia.services.DepartmenthistoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmenthistoryRestController {
	
	@Autowired
	DepartmenthistoryService historyService;
	
	@RequestMapping("/api/departmenthistoryRest/")
	public Iterable<Employeedepartmenthistory> getHistories() {
		return historyService.findAll();
	}
	
	@PostMapping("/api/departmenthistoryRest/")
	public void addCategory(@RequestBody Employeedepartmenthistory prod) {
		historyService.addDepartmenthistory(prod);
	}
	
	@GetMapping("/api/departmenthistoryRest/{id}")
	public Optional<Employeedepartmenthistory> getCategory(@PathVariable Integer id){
		return historyService.findById(id);
	}
	
	@DeleteMapping("/api/departmenthistoryRest/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Employeedepartmenthistory p = historyService.findById(id).get();
		historyService.delete(p);
	}
	
	@PutMapping("/api/departmenthistoryRest/")
	public Employeedepartmenthistory updateCategory(@RequestBody Employeedepartmenthistory prod){
		return historyService.editDepartmenthistory(prod);
	}
	
	@GetMapping("/api/departmenthistoryRest/associated/{id}")
	public List<Employeedepartmenthistory> findByDepartment(@PathVariable Integer id) {
		return historyService.findByDepartment(id);
	}
	
}
