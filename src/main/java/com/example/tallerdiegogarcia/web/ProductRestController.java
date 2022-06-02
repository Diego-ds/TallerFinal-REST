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

import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.services.ProductService;

@RestController
public class ProductRestController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/api/productRest/")
	public Iterable<Product> getProducts() {
		return productService.findAll();
	}
	
	@PostMapping("/api/productRest/")
	public void addProduct(@RequestBody Product prod) {
		productService.addProduct(prod);
	}
	
	@GetMapping("/api/productRest/{id}")
	public Optional<Product> getProduct(@PathVariable Integer id){
		return productService.findById(id);
	}
	
	@DeleteMapping("/api/productRest/{id}")
	public void deleteProduct(@PathVariable Integer id) {
		Product p = productService.findById(id).get();
		productService.delete(p);
	}
	
	@PutMapping("/api/productRest/")
	public Product updateProduct(@RequestBody Product prod){
		return productService.editProduct(prod);
	}
	
}
