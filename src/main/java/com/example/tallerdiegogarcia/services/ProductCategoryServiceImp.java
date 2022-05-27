package com.example.tallerdiegogarcia.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.CategoryDao;
import com.example.tallerdiegogarcia.model.Productcategory;
import com.example.tallerdiegogarcia.repositories.ProductSubCategoryRepository;

@Service
@Transactional
public class ProductCategoryServiceImp implements ProductCategoryService {
	
	
	@Autowired
	CategoryDao categoryDao;
	@Autowired
	ProductSubCategoryRepository subRepository;

	@Override
	public Productcategory addProductCategory(Productcategory productcategory) {
		if(productcategory !=null) {
			if(productcategory.getName().length() < 3) {
				throw new IllegalArgumentException();
			}else {
				 categoryDao.save(productcategory);
				 return productcategory;
			}
		}else {
			throw new IllegalArgumentException();
		}	
	}

	@Override
	public Productcategory editProductCategory(Productcategory productcategory) {
		categoryDao.update(productcategory);	
		return productcategory;
	}
	

	@Override
	public Iterable<Productcategory> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public Optional<Productcategory> findById(Integer id) {
		return Optional.of(categoryDao.findById(id));
	}

	@Override
	public void delete(Productcategory productcategory) {
		if(subRepository.findByProductcategoryProductcategoryid(productcategory.getProductcategoryid()).isEmpty()) {
			categoryDao.delete(productcategory);
		}else {
			throw new IllegalArgumentException();
		}
	}
	

}
