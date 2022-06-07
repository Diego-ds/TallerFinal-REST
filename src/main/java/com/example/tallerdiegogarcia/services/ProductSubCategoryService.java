package com.example.tallerdiegogarcia.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.tallerdiegogarcia.model.Productsubcategory;

public interface ProductSubCategoryService {
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory);
	public Productsubcategory editProductSubCategory(Productsubcategory productsubcategory);
	public Iterable<Productsubcategory> findAll();
	public Optional<Productsubcategory> findById(Integer id);
	public void delete(Productsubcategory productsubcategory);
	public List<Productsubcategory> findByCategory(Integer id);
	public List<Productsubcategory> findbyDateAndCategories(Integer categoryId, LocalDate sellstartdate, LocalDate sellenddate);
}
