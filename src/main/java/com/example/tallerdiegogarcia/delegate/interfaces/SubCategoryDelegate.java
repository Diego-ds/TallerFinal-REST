package com.example.tallerdiegogarcia.delegate.interfaces;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Productsubcategory;

public interface SubCategoryDelegate {
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory);
	public void editProductSubCategory(Productsubcategory productsubcategory);
	public Iterable<Productsubcategory> findAll();
	public Optional<Productsubcategory> findById(Integer id);
	public void delete(Productsubcategory productsubcategory);
}
