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
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.crud.entity.Employee;
import com.crud.repo.EmpRepo;
import com.crud.service.EmpService;
import com.crud.service.ResourceNotFoundException;


//@RunWith(SpringRunner.class)
@SpringBootTest
public class RepoTest {

	@Autowired
	private EmpRepo emprepo;
	
	@Test
	public void testSaveEmployee() {
		Employee emp=Employee.builder()
				.id(4)
				.firstName("dhinesh")
				.lastName("k")
				.email("dhinesh@gmail.com")
				.build();
		emprepo.save(emp);
		
		assertThat(emp.getId()).isGreaterThan(0);
	}

	@Test
	public void testListEmp() {
		List<Employee> list=emprepo.findAll();
		assertThat(list.size()).isGreaterThan(1);
	}
	
	@Test
	public void testGetById() {

	Optional<Employee> emp = emprepo.findById(2);
	assertThat(emp.get().getId()).isEqualTo(2);
		
	}
}
