package com.example.tallerdiegogarcia.repositories;

import com.example.tallerdiegogarcia.model.Employee;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

}
