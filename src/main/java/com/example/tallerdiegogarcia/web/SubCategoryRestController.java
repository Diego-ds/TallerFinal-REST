package com.example.tallerdiegogarcia.web;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
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
	
	@RequestMapping("/api/subcategoryRest/")
	public Iterable<Productsubcategory> getSubCategories() {
		return subcatService.findAll();
	}
	
	@GetMapping("/api/subcategoryRest/associated/{id}")
	public List<Productsubcategory> getByCategory(@PathVariable Integer id) {
		return subcatService.findByCategory(id);
	}
	
	@GetMapping("/api/subcategoryRest/datesandcategoryquery/{id}/{startdate}/{enddate}")
	public List<Productsubcategory> findByCategoryAndDates(@PathVariable String id,
			@PathVariable String startdate, @PathVariable String enddate) {
		Timestamp sdate = new Timestamp(0);
        Timestamp edate = new Timestamp(0);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");

            Date sdateParsed = dateFormat.parse(startdate);
            Date edateParsed = dateFormat.parse(enddate);

            sdate = new java.sql.Timestamp(sdateParsed.getTime());
            edate = new java.sql.Timestamp(edateParsed.getTime());


        } catch(Exception e) { 

        }
        
        Integer categoryid = Integer.parseInt(id);
        
		return subcatService.findbyDateAndCategories(categoryid, sdate, edate);
	}
	@PostMapping("/api/subcategoryRest/")
	public void addCategory(@RequestBody Productsubcategory prod) {
		subcatService.addProductSubCategory(prod);
	}
	
	@GetMapping("/api/subcategoryRest/{id}")
	public Optional<Productsubcategory> getCategory(@PathVariable Integer id){
		return subcatService.findById(id);
	}
	
	
	@DeleteMapping("/api/subcategoryRest/{id}")
	public void deleteCategory(@PathVariable Integer id) {
		Productsubcategory p = subcatService.findById(id).get();
		subcatService.delete(p);
	}
	
	@PutMapping("/api/subcategoryRest/")
	public Productsubcategory updateCategory(@RequestBody Productsubcategory prod){
		return subcatService.editProductSubCategory(prod);
	}
}
