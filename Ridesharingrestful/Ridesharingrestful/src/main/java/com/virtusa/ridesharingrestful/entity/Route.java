package com.virtusa.ridesharingrestful.entity;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Route {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer routeId;
	
	@Column(name="startPoint", nullable = false)
    private String startPoint;
	
	@Column(name="endPoint", nullable = false)
    private String endPoint;
    
    @DateTimeFormat(iso=ISO.DATE)
    @Column(nullable = false)
    private LocalDate date;
    
    @Column(name="time", nullable = false)
    private String time;
    
    @Column(name="seats", nullable = false)
    private Integer seats;
    
    @Column(name="distance", nullable = false)
    private Integer distance;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, mappedBy="route")
    private List<Booking> booking;
    
    @ManyToMany
    @JoinColumn(name = "employeeid", referencedColumnName = "employeeId")
    @JsonIgnore
    private List<Employee> employee;

	public Route() {
		
	}

	public Route(Integer routeId, String startPoint, String endPoint, LocalDate date, String time, Integer seats,
			Integer distance) {
		super();
		this.routeId = routeId;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		this.date = date;
		this.time = time;
		this.seats = seats;
		this.distance = distance;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public String getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(String startPoint) {
		this.startPoint = startPoint;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public List<Booking> getBooking() {
		return booking;
	}

	public void setBooking(List<Booking> booking) {
		this.booking = booking;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", startPoint=" + startPoint + ", endPoint=" + endPoint + ", date=" + date
				+ ", time=" + time + ", seats=" + seats + ", distance=" + distance + "]";
	}

}
