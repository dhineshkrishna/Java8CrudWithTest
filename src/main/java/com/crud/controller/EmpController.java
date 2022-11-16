package com.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Employee;
import com.crud.service.EmpService;
import com.crud.service.ResourceNotFoundException;

import org.springframework.http.HttpStatus;import org.springframework.http.ResponseEntity;

@RestController
public class EmpController {
	
	@Autowired
	private EmpService empService;
	
	@GetMapping("/get")
	public List<Employee> get(){
		return empService.getData();
	}
	
	@PostMapping("/postValue")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@RequestBody Employee employee) throws ResourceNotFoundException {
		return empService.saveEmployee(employee);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteEmp(@PathVariable("id") int employeeId){
		empService.deleteEmployee(employeeId);
		return new ResponseEntity<String>("Employee deletes",HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id") int employeeId){
		return empService.getByIDval(employeeId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int employeeId,
                                                   @RequestBody Employee employee){
      return empService.getByIDval(employeeId)
    		  .map(saveEmp -> {
    			  saveEmp.setFirstName(employee.getFirstName());
    			  saveEmp.setLastName(employee.getLastName());
    			  saveEmp.setEmail(employee.getEmail());
    			  
    			  Employee emp=empService.updateEmployee(employee);
    			  return new ResponseEntity<>(emp, HttpStatus.OK);
    		  })
    		  .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
