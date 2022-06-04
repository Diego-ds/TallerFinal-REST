package com.example.tallerdiegogarcia.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.ProductDao;
import com.example.tallerdiegogarcia.model.Product;

@Service
@Transactional
public class ProductServiceImp implements ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public Product addProduct(Product product) {
		productDao.save(product);
		return product;
	}

	@Override
	public Iterable<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return Optional.of(productDao.findById(id));
	}

	@Override
	public void delete(Product product) {
		productDao.delete(product);
	}

	@Override
	public Product editProduct(Product product) {
		productDao.update(product);
		return product;	
	}
	
	@Override
	public List<Product> findBySubcategory(Integer id){
		return productDao.findBySubcategoryid(id);
	}

}
