package com.example.tallerdiegogarcia.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tallerdiegogarcia.model.Workorder;
import com.example.tallerdiegogarcia.services.WorkOrderService;


@RestController
public class WorkOrderRestController {
	
	@Autowired
	WorkOrderService orderService;
	
	@RequestMapping("/workorderRest")
	public Iterable<Workorder> getWorkorders() {
		return orderService.findAll();
	}
	
	@PostMapping("/workorderRest")
	public void addWorkorder(@RequestBody Workorder prod) {
		orderService.addWorkOrder(prod);
	}
	
	@GetMapping("/workorderRest/{id}")
	public Optional<Workorder> getWorkorder(@PathVariable Integer id){
		return orderService.findById(id);
	}
	
	@DeleteMapping("/workorderRest/{id}")
	public void deleteWorkorder(@PathVariable Integer id) {
		Workorder p = orderService.findById(id).get();
		orderService.delete(p);
	}
	
	@PutMapping("/workorderRest")
	public Workorder updateWorkorder(@RequestBody Workorder prod){
		return orderService.editWorkOrder(prod);
	}
}
