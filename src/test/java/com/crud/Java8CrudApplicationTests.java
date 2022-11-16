package com.crud;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mockitoSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.entity.Employee;
import com.crud.repo.EmpRepo;
import com.crud.service.EmpService;
import com.crud.service.ResourceNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Java8CrudApplicationTests {

	@Autowired
	private EmpService empService;

	@MockBean
	private EmpRepo empRepo;

	@Test
	@Order(1)
	@Rollback(value = false)
	public void testCreateEmployee() throws ResourceNotFoundException {

		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Martin Bingel");
		emp.setLastName("Kolkata");
		emp.setEmail("martin.s2017@gmail.com");

		Mockito.when(empRepo.save(emp)).thenReturn(emp);

		assertThat(empService.saveEmployee(emp)).isEqualTo(emp);


	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void testGetEmployeeById(){
		Optional<Employee> emp = Optional.ofNullable(new Employee(1,"mai","vel","mani@gmail.com"));

		Mockito.when(empRepo.findById(1)).thenReturn(emp);
		assertThat(empService.getByIDval(1)).isEqualTo(emp);
	}

	@Test
	@Order(3)
	@Rollback(value = false)
	public void testDeleteEmployee(){
		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Martin Bingel");
		emp.setLastName("Kolkata");
		emp.setEmail("martin.s2017@gmail.com");

		Mockito.when(empRepo.getById(1)).thenReturn(emp);
		Mockito.when(empRepo.existsById(emp.getId())).thenReturn(false);
		assertFalse(empRepo.existsById(emp.getId()));

	}

	@Test
	@Order(4)
	@Rollback(value = false)
	public void testListEmp() {

		Employee emp = new Employee(1,"mai","vel","mani@gmail.com");
		Employee emp1 = new Employee(2,"mai","vel","nm@gmail.com");
		Employee emp2 = new Employee(3,"mai","vel","kn@gmail.com");
		List <Employee>list=new ArrayList<>();
		list.add(emp);
		list.add(emp1);
		list.add(emp2);
		Mockito.when(empRepo.findAll()).thenReturn(list);
		assertThat(empService.getData()).isEqualTo(list);

	}

	@Test
	@Order(2)
	@Rollback(value = false)
	public void testUpdateEmployee() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setFirstName("Martin Bingel");
		emp.setLastName("Kolkata");
		emp.setEmail("martin.s2017@gmail");
		Mockito.when(empRepo.getById(1)).thenReturn(emp);
		emp.setEmail("anu@gmail.com");
		Mockito.when(empRepo.save(emp)).thenReturn(emp);
		assertThat(empService.updateEmployee(emp)).isEqualTo(emp);
	}
}