package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.interfaces.SubCategoryDelegate;
import com.example.tallerdiegogarcia.model.Productsubcategory;

@Component
public class SubCategoryDelegateImp implements SubCategoryDelegate {
	
	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/api/subcategoryRest/";


	@Override
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory) {
		return template.postForObject(WEB_PATH, productsubcategory, Productsubcategory.class);
	}

	@Override
	public void editProductSubCategory(Productsubcategory productsubcategory) {
		 template.put(WEB_PATH, productsubcategory);
	}

	@Override
	public Iterable<Productsubcategory> findAll() {
		Productsubcategory [] subcategories = template.getForObject(WEB_PATH,Productsubcategory[].class);
		return Arrays.asList(subcategories);
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		Productsubcategory subcategory = template.getForObject(WEB_PATH+id, Productsubcategory.class);
		return Optional.of(subcategory);
	}

	@Override
	public void delete(Productsubcategory productsubcategory) {
        template.delete(WEB_PATH+productsubcategory.getProductsubcategoryid()); 
	}

}
