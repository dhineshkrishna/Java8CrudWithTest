package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.crud.entity.Employee;
import com.crud.repo.EmpRepo;

@Service
public class EmpService {

	@Autowired
	private EmpRepo empRepo;

	public List<Employee> getData() {
		return empRepo.findAll();
	}

	public Employee saveEmployee(Employee employee) throws ResourceNotFoundException {
		Optional<Employee> savedEmployee = empRepo.findByEmail(employee.getEmail());
		if(savedEmployee.isPresent()){
			throw new ResourceNotFoundException("Employee already exist with given email:" + employee.getEmail());
		}
		return empRepo.save(employee);
	}

	public void deleteEmployee(int employeeId) {
		empRepo.deleteById(employeeId);
		
	}

	public Optional<Employee> getByIDval(int employeeId) {
		return empRepo.findById(employeeId);
	}

	public Employee updateEmployee(Employee empl) {
		return empRepo.save(empl);
	}



}
