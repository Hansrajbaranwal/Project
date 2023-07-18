package com.virtusa.ridesharingrestful.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.virtusa.ridesharingrestful.entity.Employee;
import com.virtusa.ridesharingrestful.entity.Route;
import com.virtusa.ridesharingrestful.exception.DataNotFoundException;
import com.virtusa.ridesharingrestful.service.EmployeeService;
import com.virtusa.ridesharingrestful.service.RouteService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/admin/emp")
public class EmployeeController {

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService empService;

	@Autowired
	private RouteService routeService;

	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = empService.getEmployees();

		return new ResponseEntity<>(employees, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Employee> insertEmployee(@RequestBody Employee employee) {

		Employee employeeadd = empService.insertEmployee(employee);
		logger.debug(" {}." , employeeadd);
		return new ResponseEntity<>(employeeadd, HttpStatus.CREATED);
	}

	// Insert new Route from employee side
	@PostMapping("{id}/route")
	public ResponseEntity<Employee> insertEmpRoute(@PathVariable("id") Integer eid, @RequestBody Route route) {

		Employee emp = empService.getEmployeeById(eid);

		Route rt = routeService.insertRoute(route);
		List<Route> routes = new ArrayList<>();
		routes.add(rt);
		emp.setRoute(routes);
		Employee empupd = empService.updateEmployee(emp);
		
		List<Employee> employee = new ArrayList<>();
		employee.add(emp);
		rt.setEmployee(employee);
		routeService.updateRoute(rt);
		logger.debug(" {}." , empupd);
		return new ResponseEntity<>(empupd, HttpStatus.CREATED);
	}
	
	
	//add Route by using RouteId
	@PutMapping("{id}/route/{rid}")
	public ResponseEntity<Employee> insertEmpRouteId(@PathVariable("id") Integer eid, @PathVariable("rid") Integer rid) {

		Employee emp = empService.getEmployeeById(eid);
		Route rt = routeService.getRouteById(rid);
		
		List<Route> routes = new ArrayList<>();
		routes.add(rt);
		emp.setRoute(routes);
		Employee empupdate = empService.updateEmployee(emp);
		
		List<Employee> employee = new ArrayList<>();
		employee.add(emp);
		rt.setEmployee(employee);
		routeService.updateRoute(rt);
		
		logger.debug(" {}." , empupdate);
		return new ResponseEntity<>(empupdate, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer eid) {
		Employee employee = empService.getEmployeeById(eid);
		if (employee != null)
			return new ResponseEntity<>(employee, HttpStatus.OK);
		else
			throw new DataNotFoundException("Employee " + eid + " not found");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteEmployee(@PathVariable("id") Integer eid) {

		Employee employee = empService.deleteEmployee(eid);
		logger.debug(" {}." , employee);
		return ResponseEntity.ok(employee.getEmployeeId());
	}

	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer eid, @RequestBody Employee employee) {
		employee.setEmployeeId(eid);
		Employee employeeupdate = empService.updateEmployee(employee);
		
		return new ResponseEntity<>(employeeupdate, HttpStatus.OK);
	}

	@GetMapping("searchEmployee/{keyword}")
	public ResponseEntity<List<Employee>> searchUser(@PathVariable("keyword") String keyword) {
		List<Employee> result = empService.searchEmployee(keyword);
		return new ResponseEntity<>(result, HttpStatus.FOUND);
	}
}
