package com.example.tallerdiegogarcia.delegate.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.interfaces.CategoryDelegate;
import com.example.tallerdiegogarcia.model.Product;
import com.example.tallerdiegogarcia.model.Productcategory;

@Component
public class CategoryDelegateImp implements CategoryDelegate {
	
	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/api/categoryRest/";

	
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
		//probando
		/*
		List<Productcategory> listcat = new ArrayList();
		Iterable <Productcategory> categoriesResponse = null;
		System.out.println(template.getMessageConverters().size());
		ResponseEntity<Productcategory[]> response = template.getForEntity(WEB_PATH,Productcategory[].class);
		Productcategory [] categories = response.getBody();
		for(int i=0;i<categories.length;i++) {
			listcat.add(categories[i]);
		}
		categoriesResponse = listcat;
		return categoriesResponse;*/
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
