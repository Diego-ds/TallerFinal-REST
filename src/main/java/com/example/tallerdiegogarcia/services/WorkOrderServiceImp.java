package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tallerdiegogarcia.dao.interfaces.ProductDao;
import com.example.tallerdiegogarcia.dao.interfaces.WorkOrderDao;
import com.example.tallerdiegogarcia.model.Workorder;

@Service
@Transactional
public class WorkOrderServiceImp implements WorkOrderService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private WorkOrderDao orderDao;

	@Override
	public Workorder addWorkOrder(Workorder workorder) {
		productDao.findById(workorder.getProduct().getProductid()).getWorkorders().add(workorder);
		orderDao.save(workorder);
		return workorder;
	}

	@Override
	public Workorder editWorkOrder(Workorder workorder) {
		orderDao.update(workorder);
		return workorder;
	}

	@Override
	public Optional<Workorder> findById(Integer id) {
		return Optional.of(orderDao.findById(id));
	}

	@Override
	public Iterable<Workorder> findAll() {
		// TODO Auto-generated method stub
		return orderDao.findAll();
	}

	@Override
	public void delete(Workorder workorder) {
		orderDao.delete(workorder);
	}

}
