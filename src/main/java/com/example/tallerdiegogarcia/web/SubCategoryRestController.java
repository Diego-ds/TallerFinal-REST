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

import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.services.ProductSubCategoryService;

@RestController
public class SubCategoryRestController {
	
	@Autowired
	ProductSubCategoryService subcatService;
	
	@RequestMapping("/subcategoryRest")
	public Iterable<Productsubcategory> getSubCategories() {
		return subcatService.findAll();
	}
	
	@PostMapping("/subcategoryRest")
	public void addCategory(@RequestBody Productsubcategory prod) {
		subcatService.addProductSubCategory(prod);
	}
	
	@GetMapping("/subcategoryRest/{id}")
	public Optional<Productsubcategory> getCategory(@PathVariable Integer id){
		return subcatService.findById(id);
	}
	
	@DeleteMapping("/subcategoryRest/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Productsubcategory p = subcatService.findById(id).get();
		subcatService.delete(p);
	}
	
	@PutMapping("/subcategoryRest")
	public Productsubcategory updateCategory(@RequestBody Productsubcategory prod){
		return subcatService.editProductSubCategory(prod);
	}
}
