package com.virtusa.ridesharingrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ridesharingrestful.entity.Employee;
import com.virtusa.ridesharingrestful.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository empRepository;
	
	public List<Employee> getEmployees() {
		return empRepository.findAll();
	}

	public Employee getEmployeeById(Integer id) {
		return empRepository.findById(id).orElse(null);
	}

	public Employee deleteEmployee(int id) {
		Employee employee =getEmployeeById(id);
		if(employee!=null) {
			empRepository.deleteById(id);
		}
		return employee;
	}

	public List<Employee> searchEmployee(String keyword) {
		return empRepository.search(keyword);
	}

	public Employee insertEmployee(Employee employee) {
		return empRepository.save(employee);
	}

	public Employee updateEmployee(Employee employee) {
		
		return empRepository.save(employee);
	}

}
