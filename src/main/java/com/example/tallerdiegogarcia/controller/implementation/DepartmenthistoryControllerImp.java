//package com.example.tallerdiegogarcia.controller.implementation;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import com.example.tallerdiegogarcia.delegate.interfaces.DepartmentDelegate;
//import com.example.tallerdiegogarcia.delegate.interfaces.DepartmenthistoryDelegate;
//import com.example.tallerdiegogarcia.model.Department;
//import com.example.tallerdiegogarcia.validate.DepartmentValidation;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class DepartmenthistoryControllerImp {
//	@Autowired
//	DepartmentDelegate departmentService;
//	@Autowired
//	DepartmenthistoryDelegate historyService;
//	
//
//	@GetMapping("/departments/add")
//	public String addDepartment(Model model) {
//		model.addAttribute("department", new Department());
//		return "departments/add-department";
//	}
//
//	@GetMapping("/departments/del/{id}")
//	public String deleteDepartment(@PathVariable("id") Integer id, Model model) {
//		Department department = departmentService.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
//		departmentService.delete(department);
//		model.addAttribute("departments", departmentService.findAll());
//		return "departments/index";
//	}
//
//	@GetMapping("/departments/")
//	public String indexDepartments(Model model) {
//		model.addAttribute("departments", departmentService.findAll());
//		return "departments/index";
//	}
//
//	@PostMapping("/departments/add")
//	public String saveDepartment( @Validated(DepartmentValidation.class) 
//			@ModelAttribute Department department, 
//			BindingResult bindingResult, Model model, 
//			@RequestParam(value = "action", required = true) String action) {
//		
//		if (!action.equals("Cancel")) {
//			if (bindingResult.hasErrors()) {
//					return "/departments/add-department";
//				 }
//			 departmentService.addDepartment(department);
//		}
//		return "redirect:/departments/";
//		
//		
//	}
//
//	@GetMapping("/departments/edit/{id}")
//	public String showUpdateDepartment(@PathVariable("id") Integer id, Model model) {
//		Optional<Department> department = departmentService.findById(id);
//		if (department == null)
//			throw new IllegalArgumentException("Invalid department Id:" + id);
//		
//		model.addAttribute("department", department.get());
//		model.addAttribute("departmenthistories", historyService.findAll());
//		return "departments/update-department";
//	}
//	
//	@GetMapping("/departmenthistories/associated/{id}")
//	public String associatedHistories(@PathVariable("id") Integer id, Model model) {
//		Department dep = departmentService.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid department history Id:" + id));
//		
//		model.addAttribute("departments", dep);
//		return "departments/index";
//	}
//	
//	@GetMapping("/departments/show/{id}")
//	public String show(@PathVariable("id") Integer id, Model model) {
//		Department d = departmentService.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
//		List<Department> deps = new ArrayList<Department>();
//		deps.add(d);
//		model.addAttribute("departments",deps);
//		return "departments/index";
//	}
//	
//	
//
//	@PostMapping("/departments/edit/{id}")
//	public String updateDepartment(@PathVariable("id") Integer id, @RequestParam(value = "action", required = true) String action,
//			@Validated(DepartmentValidation.class) @ModelAttribute Department department,BindingResult bindingResult , Model model) {
//		if (action != null && !action.equals("Cancel")) {
//			 if (bindingResult.hasErrors()) {
//				model.addAttribute("department", department);
//				department.setDepartmentid(id);
//				return "departments/update-department";
//			 }else {
//				department.setDepartmentid(id);
//				departmentService.editDepartment(department);	
//			 }
//			model.addAttribute("departments", departmentService.findAll());	
//		}
//		
//		return "redirect:/departments/";
//	}
//
//}
