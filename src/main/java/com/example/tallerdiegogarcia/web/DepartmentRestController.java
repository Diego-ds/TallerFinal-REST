package com.example.tallerdiegogarcia.web;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Department;
import com.example.tallerdiegogarcia.services.DepartmentService;

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
public class DepartmentRestController {
	
	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("/api/departmentRest/")
	public Iterable<Department> getCategories() {
		return departmentService.findAll();
	}
	
	@PostMapping("/api/departmentRest/")
	public void addCategory(@RequestBody Department prod) {
		departmentService.addDepartment(prod);
	}
	
	@GetMapping("/api/departmentRest/{id}")
	public Optional<Department> getCategory(@PathVariable Integer id){
		return departmentService.findById(id);
	}
	
	@DeleteMapping("/api/departmentRest/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Department p = departmentService.findById(id).get();
		departmentService.delete(p);
	}
	
	@PutMapping("/api/departmentRest/")
	public Department updateCategory(@RequestBody Department prod){
		return departmentService.editDepartment(prod);
	}
	
}
