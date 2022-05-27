package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.interfaces.CategoryDelegate;
import com.example.tallerdiegogarcia.model.Productcategory;

@Component
public class CategoryDelegateImp implements CategoryDelegate {
	
	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/categoryRest/";


	@Override
	public Productcategory addProductCategory(Productcategory productcategory) {
        return template.postForObject(WEB_PATH, productcategory, Productcategory.class);       
	}

	@Override
	public void editProductCategory(Productcategory productcategory) {
		 template.put(WEB_PATH, productcategory);
	}

	@Override
	public Iterable<Productcategory> findAll() {
		Productcategory [] categories = template.getForObject(WEB_PATH,Productcategory[].class);
		return Arrays.asList(categories);
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		Productcategory category = template.getForObject(WEB_PATH+id, Productcategory.class);
		return Optional.of(category);
	}

	@Override
	public void delete(Productcategory productcategory) {
        template.delete(WEB_PATH+productcategory.getProductcategoryid()); 
	}

}
