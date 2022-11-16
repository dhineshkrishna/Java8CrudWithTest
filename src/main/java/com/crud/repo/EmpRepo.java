package com.crud.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.entity.Employee;
@Repository
public interface EmpRepo extends JpaRepository<Employee, Integer>{

	Optional<Employee> findByEmail(String email);

}
