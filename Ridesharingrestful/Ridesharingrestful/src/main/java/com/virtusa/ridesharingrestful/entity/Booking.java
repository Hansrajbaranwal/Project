package com.virtusa.ridesharingrestful.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer bookingId;
    
    @DateTimeFormat(iso=ISO.DATE)
    private LocalDate date;
    
    @Column(name = "seats")
    private Integer seats;
    
    
    @ManyToOne
    @JoinColumn(name="userid", referencedColumnName = "userId")
    @JsonIgnore
    private User user;
    
    @ManyToMany
    @JoinColumn(name = "routeid", referencedColumnName = "routeId")
    @JsonIgnore
    private Set<Route> route;
    
   

	public Booking() {
	}

	public Booking(Integer bookingId, LocalDate date, Integer seats, User user) {
		super();
		this.bookingId = bookingId;
		this.date = date;
		this.seats = seats;
		this.user = user;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Route> getRoute() {
		return route;
	}

	public void setRoute(Set<Route> route) {
		this.route = route;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", date=" + date + ", seats=" + seats + "]";
	}

}
