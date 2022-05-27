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

import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.services.ProductCategoryService;


@RestController
public class CategoryRestController {
	
	@Autowired
	ProductCategoryService categoryService;
	
	@RequestMapping("/categoryRest")
	public Iterable<Productcategory> getCategories() {
		return categoryService.findAll();
	}
	
	@PostMapping("/categoryRest")
	public void addCategory(@RequestBody Productcategory prod) {
		categoryService.addProductCategory(prod);
	}
	
	@GetMapping("/categoryRest/{id}")
	public Optional<Productcategory> getCategory(@PathVariable Integer id){
		return categoryService.findById(id);
	}
	
	@DeleteMapping("/categoryRest/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Productcategory p = categoryService.findById(id).get();
		categoryService.delete(p);
	}
	
	@PutMapping("/categoryRest")
	public Productcategory updateCategory(@RequestBody Productcategory prod){
		return categoryService.editProductCategory(prod);
	}
	
}
