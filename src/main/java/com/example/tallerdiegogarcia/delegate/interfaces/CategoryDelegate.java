package com.example.tallerdiegogarcia.delegate.interfaces;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Productcategory;

public interface CategoryDelegate {
	public Productcategory addProductCategory(Productcategory productcategory);
	public void editProductCategory(Productcategory productcategory);
	public Iterable<Productcategory> findAll();
	public Optional<Productcategory> findById(Integer id);
	public void delete(Productcategory productcategory);
}
