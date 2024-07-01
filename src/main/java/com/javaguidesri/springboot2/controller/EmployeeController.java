package com.javaguidesri.springboot2.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import com.javaguidesri.springboot2.exception.ResourceNotFoundException;
import com.javaguidesri.springboot2.model.Employee;
import com.javaguidesri.springboot2.repository.EmployeeRepository;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get All employee list
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
		
	}
		
	//create employee rest api
	
	@PostMapping("/employees")
	public Employee creatEmployee(@RequestBody Employee employee) {

		return employeeRepository.save(employee);
	}
	

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee= employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee is not exit with Id" + id ));
		
		return ResponseEntity.ok(employee);
		
	}
	
	//update employee Rest API
	
	@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails ) {
     Employee employee = employeeRepository.findById(id)
    		 			.orElseThrow(() -> new ResourceNotFoundException("Employee is not exist" + id ));   
    		 			
     employee.setFirstName(employeeDetails.getFirstName());
     employee.setLastName(employeeDetails.getLastName());
     employee.setEmailId(employeeDetails.getEmailId());
     
     Employee updatEmployee = employeeRepository.save(employee);
     
     return ResponseEntity.ok(updatEmployee);	
    	
    }

	@DeleteMapping("/employees/{id}")
	public ResponseEntity< Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				           .orElseThrow(() -> new ResourceNotFoundException("Employee is not exist" +id));
		
			employeeRepository.delete(employee);
			Map<String, Boolean> response= new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);
				
	}
	
	
}
