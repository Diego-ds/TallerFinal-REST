package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.imp.SubCategoryDelegateImp;
import com.example.tallerdiegogarcia.model.Productsubcategory;

@SpringBootTest
class SubCategoryDelegateTest {

	@Mock
	RestTemplate template;
	@InjectMocks
	SubCategoryDelegateImp delegate;

	@Test
	public void addCategoryTest() {
		Productsubcategory subcategory = new Productsubcategory();
		subcategory.setName("Electronicos");
		when(template.postForObject(SubCategoryDelegateImp.WEB_PATH, subcategory, Productsubcategory.class)).thenReturn(subcategory);
		assertEquals(delegate.addProductSubCategory(subcategory).getName(),"Electronicos");
		verify(template).postForObject(SubCategoryDelegateImp.WEB_PATH, subcategory, Productsubcategory.class);
	}
	@Test
	public void updateCategoryTest() {
		Productsubcategory subcategory = new Productsubcategory();
		subcategory.setName("Electronicos");
		delegate.editProductSubCategory(subcategory);
		verify(template).put(SubCategoryDelegateImp.WEB_PATH, subcategory);
	}
	@Test
	public void getCategoryTest() {
		Integer id = 1;
		Productsubcategory subcategory = new Productsubcategory();
		subcategory.setName("Electronicos");
		when(template.getForObject(SubCategoryDelegateImp.WEB_PATH+id, Productsubcategory.class)).thenReturn(subcategory);
		assertEquals(delegate.findById(id).get().getName(),"Electronicos");
		verify(template).getForObject(SubCategoryDelegateImp.WEB_PATH+id, Productsubcategory.class);
	}
	@Test
	public void getAllCategoryTest() {
		Productsubcategory[] lista= new Productsubcategory[2];
		when(template.getForObject(SubCategoryDelegateImp.WEB_PATH, Productsubcategory[].class)).thenReturn(lista);
		assertNotNull(delegate.findAll());
		verify(template).getForObject(SubCategoryDelegateImp.WEB_PATH, Productsubcategory[].class);	
	}
	@Test
	public void deleteCategoryTest() {
		Integer id = 1;
		Productsubcategory category = new Productsubcategory();
		category.setName("Electronicos");
		category.setProductsubcategoryid(1);
		delegate.delete(category);
		verify(template).delete(SubCategoryDelegateImp.WEB_PATH+id);
	}
	
	@Test
	public void findByCategoryTest() {
		Productsubcategory[] lista= new Productsubcategory[2];
		when(template.getForObject(SubCategoryDelegateImp.WEB_PATH+"associated/1", Productsubcategory[].class)).thenReturn(lista);
		assertNotNull(delegate.findByCategory(1));
		verify(template).getForObject(SubCategoryDelegateImp.WEB_PATH+"associated/1", Productsubcategory[].class);	
	}
	

}
