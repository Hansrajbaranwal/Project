package com.virtusa.ridesharingrestful.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	@Column(name = "fname", nullable = false)
	private String firstName;

	@Column(name = "lname", nullable = false)
	private String lastName;

	@Column(name = "email", length = 30, nullable = false)
	private String email;

	@Column(name = "mobileno", length = 10, nullable = false)
	private String mobileNumber;

	@Column(name = "vehicleModel", nullable = false)
	private String vehicleModel;

	@Column(name = "vehicleNo", nullable = false)
	private String vehicleNumber;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "employee")
	public List<Route> routes;

	public Employee() {
	}

	public Employee(Integer employeeId, String firstName, String lastName, String email, String mobileNumber,
			String vehicleModel, String vehicleNumber) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.vehicleModel = vehicleModel;
		this.vehicleNumber = vehicleNumber;

	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public List<Route> getRoute() {
		return routes;
	}

	public void setRoute(List<Route> routes) {
		this.routes = routes;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", mobileNumber=" + mobileNumber + ", vehicleModel=" + vehicleModel + ", vehicleNumber="
				+ vehicleNumber + "]";
	}

}
