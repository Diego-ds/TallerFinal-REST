package com.example.tallerdiegogarcia.services;

import java.util.List;
import java.util.Optional;

import com.example.tallerdiegogarcia.model.Product;

public interface ProductService {
	public Iterable<Product> findAll();
	public Optional<Product> findById(Integer id);
	public void delete(Product product);
	public Product addProduct(Product product);
	public Product editProduct(Product product);
	public List<Product> findBySubcategory(Integer id);
	public List<Product> findByWorkorderQuantity();
}
