package com.example.tallerdiegogarcia.services;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Workorder;

public interface WorkOrderService {
	public Workorder addWorkOrder(Workorder workorder);
	public Workorder editWorkOrder(Workorder workorder);
	public Optional<Workorder> findById(Integer id);
	public Iterable<Workorder> findAll();
	public void delete(Workorder workorder);	
}
