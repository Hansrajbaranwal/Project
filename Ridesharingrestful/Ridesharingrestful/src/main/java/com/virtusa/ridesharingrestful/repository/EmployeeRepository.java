package com.virtusa.ridesharingrestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.virtusa.ridesharingrestful.entity.Employee;

@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

	@Query(value = "SELECT emp FROM Employee emp WHERE emp.employeeId LIKE '%' || :keyword || '%'"
			+ " OR emp.firstName LIKE '%' || :keyword || '%'" + " OR emp.lastName LIKE '%' || :keyword || '%'"
			+ " OR emp.email LIKE '%' || :keyword || '%'" + " OR emp.mobileNumber LIKE '%' || :keyword || '%'"
			+ " OR emp.vehicleModel LIKE '%' || :keyword || '%'" + " OR emp.vehicleNumber LIKE '%' || :keyword || '%'")
	public List<Employee> search(String keyword);

}
