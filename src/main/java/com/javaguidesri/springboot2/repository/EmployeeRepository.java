package com.javaguidesri.springboot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javaguidesri.springboot2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
	

}
