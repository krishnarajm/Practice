package com.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@CrossOrigin(origins="http://localhost:4200")  
@RestController
@RequestMapping("/rest/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getAll() {
		List<Employee> list = empService.getAll();
		return new ResponseEntity<List<Employee>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id) {
		Employee employee = empService.getEmployeeById(id);
		return new ResponseEntity<Employee>(employee, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
		Employee newEmployee = empService.createEmployee(employee);
		return new ResponseEntity<Employee>(newEmployee, new HttpHeaders(), HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody Employee employee, @PathVariable("id") Long id) {
		Employee updatedEmployee =  empService.updateEmployee(employee,id);
        return new ResponseEntity<Employee>(updatedEmployee, new HttpHeaders(), HttpStatus.OK);
    }

	@DeleteMapping(value = "/delete/{id}")
	public void deleteEmployee(@PathVariable(name = "id") Long id) {
		empService.deleteEmployeeById(id);
	}
}
