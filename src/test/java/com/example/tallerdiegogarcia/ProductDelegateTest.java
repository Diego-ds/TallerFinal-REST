package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.imp.ProductDelegateImp;
import com.example.tallerdiegogarcia.model.Product;

@SpringBootTest
class ProductDelegateTest {

	@Mock
	RestTemplate template;
	@InjectMocks
	ProductDelegateImp delegate;

	@Test
	public void addProductTest() {
		Product prod = new Product();
		prod.setName("Televisor");
		when(template.postForObject(ProductDelegateImp.WEB_PATH, prod, Product.class)).thenReturn(prod);
		assertEquals(delegate.addProduct(prod).getName(),"Televisor");
		verify(template).postForObject(ProductDelegateImp.WEB_PATH, prod, Product.class);
	}
	@Test
	public void updateProductTest() {
		Product prod = new Product();
		prod.setName("Electronicos");
		delegate.editProduct(prod);
		verify(template).put(ProductDelegateImp.WEB_PATH, prod);
	}
	@Test
	public void getProductTest() {
		Integer id = 1;
		Product prod = new Product();
		prod.setName("Electronicos");
		when(template.getForObject(ProductDelegateImp.WEB_PATH+id, Product.class)).thenReturn(prod);
		assertEquals(delegate.findById(id).get().getName(),"Electronicos");
		verify(template).getForObject(ProductDelegateImp.WEB_PATH+id, Product.class);
	}
	@Test
	public void getAllProductsTest() {
		Product[] lista= new Product[2];
		when(template.getForObject(ProductDelegateImp.WEB_PATH, Product[].class)).thenReturn(lista);
		assertNotNull(delegate.findAll());
		verify(template).getForObject(ProductDelegateImp.WEB_PATH, Product[].class);	
	}
	@Test
	public void deleteProductTest() {
		Integer id = 1;
		Product prod = new Product();
		prod.setName("Electronicos");
		prod.setProductid(1);
		delegate.delete(prod);
		verify(template).delete(ProductDelegateImp.WEB_PATH+id);
	}
	
	@Test
	public void findBySubCategoryTest() {
		Product[] lista= new Product[2];
		when(template.getForObject(ProductDelegateImp.WEB_PATH+"associated/1", Product[].class)).thenReturn(lista);
		assertNotNull(delegate.findBySubcategory(1));
		verify(template).getForObject(ProductDelegateImp.WEB_PATH+"associated/1", Product[].class);	
	}
}
