package com.example.tallerdiegogarcia.controller.implementation;

import java.util.Optional;

import com.example.tallerdiegogarcia.delegate.interfaces.DepartmentDelegate;
import com.example.tallerdiegogarcia.delegate.interfaces.DepartmenthistoryDelegate;
import com.example.tallerdiegogarcia.model.Department;
import com.example.tallerdiegogarcia.model.Employeedepartmenthistory;
import com.example.tallerdiegogarcia.repositories.EmployeeRepository;
import com.example.tallerdiegogarcia.validate.DepartmentValidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DepartmenthistoryControllerImp {
	@Autowired
	DepartmentDelegate departmentService;
	@Autowired
	DepartmenthistoryDelegate historyService;
	@Autowired
	EmployeeRepository employeeRepo;

	@GetMapping("/departmenthistories/add")
	public String addDepartmenthistory(Model model) {
		model.addAttribute("departmenthistory", new Employeedepartmenthistory());
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("employees",employeeRepo.findAll());
		return "departmenthistories/add-departmenthistory";
	}

	@GetMapping("/departmenthistories/del/{id}")
	public String deleteDepartmenthistory(@PathVariable("id") Integer id, Model model) {
		Employeedepartmenthistory history = historyService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
		historyService.delete(history);
		model.addAttribute("departmenthistories", historyService.findAll());
		return "departmenthistories/index";
	}

	@GetMapping("/departmenthistories/")
	public String indexDepartmenthistories(Model model) {
		model.addAttribute("departmenthistories", historyService.findAll());
		return "departmenthistories/index";
	}

	@PostMapping("/departmenthistories/add")
	public String saveDepartmenthistory( @Validated(DepartmentValidation.class) 
			@ModelAttribute Employeedepartmenthistory departmenthistory, 
			BindingResult bindingResult, Model model, 
			@RequestParam(value = "action", required = true) String action) {
		
		if (!action.equals("Cancel")) {
			if (bindingResult.hasErrors()) {
					model.addAttribute("departments", departmentService.findAll());
					model.addAttribute("employees",employeeRepo.findAll());
					return "/departmenthistories/add-departmenthistory";
				 }
			 historyService.addDepartmenthistory(departmenthistory);
		}
		return "redirect:/departmenthistories/";
		
		
	}

	@GetMapping("/departmenthistories/edit/{id}")
	public String showUpdateDepartment(@PathVariable("id") Integer id, Model model) {
		Optional<Employeedepartmenthistory> history = historyService.findById(id);
		if (history == null)
			throw new IllegalArgumentException("Invalid department history Id:" + id);
		
		model.addAttribute("departmenthistory", history.get());
//		model.addAttribute("departmenthistories", historyService.findAll());
		model.addAttribute("departments", departmentService.findAll());
		model.addAttribute("employees",employeeRepo.findAll());
		return "departmenthistories/update-departmenthistory";
	}
	
	@GetMapping("/departmenthistories/associated/{id}")
	public String associatedDepartment(@PathVariable("id") Integer id, Model model) {
		Department dep = departmentService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
		
		model.addAttribute("departmenthistories", dep.getEmployeedepartmenthistories());
		return "departmenthistories/index";
	}
	
//	@GetMapping("/departmenthistories/show/{id}")
//	public String show(@PathVariable("id") Integer id, Model model) {
//		Department d = departmentService.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("Invalid department Id:" + id));
//		List<Department> deps = new ArrayList<Department>();
//		deps.add(d);
//		model.addAttribute("departmenthistories",deps);
//		return "departmenthistories/index";
//	}
	
	

	@PostMapping("/departmenthistories/edit/{id}")
	public String updateDepartmenthistory(@PathVariable("id") Integer id,
			@RequestParam(value = "action", required = true) String action,
			@Validated(DepartmentValidation.class) 
			@ModelAttribute Employeedepartmenthistory departmenthistory,
			BindingResult bindingResult , Model model) {
		if (action != null && !action.equals("Cancel")) {
			 if (bindingResult.hasErrors()) {
				model.addAttribute("departmenthistory", departmenthistory);
				model.addAttribute("departments", departmentService.findAll());
				model.addAttribute("employees",employeeRepo.findAll());
				departmenthistory.setId(id);
				return "departmenthistories/update-departmenthistory";
			 }else {
				departmenthistory.setId(id);
				historyService.editDepartmenthistory(departmenthistory);	
			 }
			model.addAttribute("departmenthistories", historyService.findAll());	
		}
		
		return "redirect:/departmenthistories/";
	}

}
