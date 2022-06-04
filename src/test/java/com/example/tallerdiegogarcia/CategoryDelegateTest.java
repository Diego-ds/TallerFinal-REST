package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.imp.CategoryDelegateImp;
import com.example.tallerdiegogarcia.model.Productcategory;

@SpringBootTest
class CategoryDelegateTest {

	@Mock
	RestTemplate template;
	@InjectMocks
	CategoryDelegateImp delegate;

	@Test
	public void addCategoryTest() {
		Productcategory category = new Productcategory();
		category.setName("Electronicos");
		when(template.postForObject(CategoryDelegateImp.WEB_PATH, category, Productcategory.class)).thenReturn(category);
		assertEquals(delegate.addProductCategory(category).getName(),"Electronicos");
		verify(template).postForObject(CategoryDelegateImp.WEB_PATH, category, Productcategory.class);
	}
	@Test
	public void updateCategoryTest() {
		Productcategory category = new Productcategory();
		category.setName("Electronicos");
		delegate.editProductCategory(category);
		verify(template).put(CategoryDelegateImp.WEB_PATH, category);
	}
	@Test
	public void getCategoryTest() {
		Integer id = 1;
		Productcategory category = new Productcategory();
		category.setName("Electronicos");
		when(template.getForObject(CategoryDelegateImp.WEB_PATH+id, Productcategory.class)).thenReturn(category);
		assertEquals(delegate.findById(id).get().getName(),"Electronicos");
		verify(template).getForObject(CategoryDelegateImp.WEB_PATH+id, Productcategory.class);
	}
	@Test
	public void getAllCategoryTest() {
		Productcategory[] lista= new Productcategory[2];
		when(template.getForObject(CategoryDelegateImp.WEB_PATH, Productcategory[].class)).thenReturn(lista);
		assertNotNull(delegate.findAll());
		verify(template).getForObject(CategoryDelegateImp.WEB_PATH, Productcategory[].class);	
	}
	@Test
	public void deleteCategoryTest() {
		Integer id = 1;
		Productcategory category = new Productcategory();
		category.setName("Electronicos");
		category.setProductcategoryid(1);
		delegate.delete(category);
		verify(template).delete(CategoryDelegateImp.WEB_PATH+id);
	}

}
