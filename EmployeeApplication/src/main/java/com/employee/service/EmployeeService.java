package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repo.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repo;

	public List<Employee> getAll() {
		List<Employee> employeeList = repo.findAll();

		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<Employee>();
		}
	}

	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = repo.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			return null;
		}
	}

	public void deleteEmployeeById(Long id) {
		repo.deleteById(id);
	}

	public Employee updateEmployee(Employee entity,long id) {
		Optional<Employee> employeeObj = repo.findById(id);
		if (employeeObj.isPresent()) {
			Employee updatedEmployee = employeeObj.get();
			updatedEmployee.setId(entity.getId());
			updatedEmployee.setName(entity.getName());
			updatedEmployee.setDesignation(entity.getDesignation());
			updatedEmployee.setClient(entity.getClient());
			updatedEmployee = repo.save(updatedEmployee);
			return updatedEmployee;
		}
		return new Employee();

	}

	public Employee createEmployee(@Valid Employee employee) {
		Employee newEmployee = repo.save(employee);
		return newEmployee;
	}

}
