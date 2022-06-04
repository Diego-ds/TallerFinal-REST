package com.example.tallerdiegogarcia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.CategoryDao;
import com.example.tallerdiegogarcia.dao.interfaces.SubcategoryDao;
import com.example.tallerdiegogarcia.model.Productsubcategory;
import com.example.tallerdiegogarcia.repositories.ProductRepository;

@Service
@Transactional
public class ProductSubCategoryServiceImp implements ProductSubCategoryService {
	
	@Autowired
	private SubcategoryDao subDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private ProductRepository productRepository;
	
	

	@Override
	public Productsubcategory addProductSubCategory(Productsubcategory productsubcategory) {
		categoryDao.findById(productsubcategory.getProductcategory().getProductcategoryid()).getProductsubcategories().add(productsubcategory);
		subDao.save(productsubcategory);
		return productsubcategory;
	}

	@Override
	public Productsubcategory editProductSubCategory(Productsubcategory productsubcategory) {
		subDao.update(productsubcategory);
		return productsubcategory;
	}
	
	public Productsubcategory findSubCategory(Integer id) {
		return subDao.findById(id);
	}

	@Override
	public Iterable<Productsubcategory> findAll() {
		return subDao.findAll();
	}

	@Override
	public Optional<Productsubcategory> findById(Integer id) {
		return Optional.of(subDao.findById(id));
	}

	@Override
	public void delete(Productsubcategory productsubcategory) {
		if(productRepository.findByProductsubcategoryProductsubcategoryid(productsubcategory.getProductsubcategoryid()).isEmpty()) {
			subDao.delete(productsubcategory);
		}else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public List<Productsubcategory> findByCategory(Integer id) {
		return subDao.findByCategory(id);
	}

}
