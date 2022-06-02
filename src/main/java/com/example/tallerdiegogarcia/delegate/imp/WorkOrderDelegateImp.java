package com.example.tallerdiegogarcia.delegate.imp;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.tallerdiegogarcia.delegate.interfaces.WorkOrderDelegate;
import com.example.tallerdiegogarcia.model.Workorder;

@Component
public class WorkOrderDelegateImp implements WorkOrderDelegate {
	
	@Autowired
	RestTemplate template;
	
	public static final String WEB_PATH = "http://localhost:8080/api/workorderRest/";
	

	@Override
	public Workorder addWorkOrder(Workorder workorder) {
		return template.postForObject(WEB_PATH, workorder, Workorder.class);
	}

	@Override
	public void editWorkOrder(Workorder workorder) {
		template.put(WEB_PATH, workorder);
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		Workorder workorder = template.getForObject(WEB_PATH+id, Workorder.class);
		return Optional.of(workorder);
	}

	@Override
	public Iterable<Workorder> findAll() {
		Workorder [] workorder = template.getForObject(WEB_PATH,Workorder[].class);
		return Arrays.asList(workorder);
	}

	@Override
	public void delete(Workorder workorder) {
		template.delete(WEB_PATH+workorder.getWorkorderid());
	}

}
