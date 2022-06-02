package com.example.tallerdiegogarcia;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.imp.WorkOrderDelegateImp;
import com.example.tallerdiegogarcia.model.Workorder;


@SpringBootTest
class WorkOrderDelegateTest {

	@Mock
	RestTemplate template;
	@InjectMocks
	WorkOrderDelegateImp delegate;

	@Test
	public void addProductTest() {
		Workorder order = new Workorder();
		order.setOrderqty(10);
		when(template.postForObject(WorkOrderDelegateImp.WEB_PATH, order, Workorder.class)).thenReturn(order);
		assertEquals(delegate.addWorkOrder(order).getOrderqty(),10);
		verify(template).postForObject(WorkOrderDelegateImp.WEB_PATH, order, Workorder.class);
	}
	@Test
	public void updateProductTest() {
		Workorder order = new Workorder();
		order.setOrderqty(10);
		delegate.editWorkOrder(order);
		verify(template).put(WorkOrderDelegateImp.WEB_PATH, order);
	}
	@Test
	public void getProductTest() {
		Integer id = 1;
		Workorder order = new Workorder();
		order.setOrderqty(10);
		when(template.getForObject(WorkOrderDelegateImp.WEB_PATH+id, Workorder.class)).thenReturn(order);
		assertEquals(delegate.findById(id).get().getOrderqty(),10);
		verify(template).getForObject(WorkOrderDelegateImp.WEB_PATH+id, Workorder.class);
	}
	@Test
	public void getAllProductsTest() {
		Workorder[] lista= new Workorder[2];
		when(template.getForObject(WorkOrderDelegateImp.WEB_PATH, Workorder[].class)).thenReturn(lista);
		assertNotNull(delegate.findAll());
		verify(template).getForObject(WorkOrderDelegateImp.WEB_PATH, Workorder[].class);	
	}
	@Test
	public void deleteProductTest() {
		Integer id = 1;
		Workorder order = new Workorder();
		order.setOrderqty(10);
		order.setWorkorderid(1);
		delegate.delete(order);
		verify(template).delete(WorkOrderDelegateImp.WEB_PATH+id);
	}

}
