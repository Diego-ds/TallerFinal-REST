package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.interfaces.ProductDelegate;
import com.example.tallerdiegogarcia.model.Product;

@Controller
public class ProductDelegateImp implements ProductDelegate {

	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/productRest/";

	@Override
	public Iterable<Product> findAll() {
		Product [] products = template.getForObject(WEB_PATH,Product[].class);
		return Arrays.asList(products);
	}

	@Override
	public Optional<Product> findById(Integer id) {
		Product product = template.getForObject(WEB_PATH+id, Product.class);
		return Optional.of(product);
	}

	@Override
	public void delete(Product product) {
		template.delete(WEB_PATH+product.getProductid());
	}

	@Override
	public Product addProduct(Product product) {
		return template.postForObject(WEB_PATH, product, Product.class);
	}

	@Override
	public void editProduct(Product product) {
		template.put(WEB_PATH, product);
	}

}
