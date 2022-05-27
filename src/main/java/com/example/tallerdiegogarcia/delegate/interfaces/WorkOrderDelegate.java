package com.example.tallerdiegogarcia.delegate.interfaces;

import java.util.Optional;

import com.example.tallerdiegogarcia.model.Workorder;

public interface WorkOrderDelegate {
	public Workorder addWorkOrder(Workorder workorder);
	public void editWorkOrder(Workorder workorder);
	public Optional<Workorder> findById(Integer id);
	public Iterable<Workorder> findAll();
	public void delete(Workorder workorder);
}
