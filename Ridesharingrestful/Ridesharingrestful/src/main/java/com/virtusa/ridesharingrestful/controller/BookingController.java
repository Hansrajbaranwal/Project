package com.virtusa.ridesharingrestful.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.virtusa.ridesharingrestful.entity.Booking;
import com.virtusa.ridesharingrestful.entity.Route;
import com.virtusa.ridesharingrestful.entity.User;
import com.virtusa.ridesharingrestful.exception.DataNotFoundException;
import com.virtusa.ridesharingrestful.service.BookingService;
import com.virtusa.ridesharingrestful.service.RouteService;
import com.virtusa.ridesharingrestful.service.UserService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user/booking")
public class BookingController {
	
	Logger logger = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private UserService userService;
	

	@GetMapping
	public ResponseEntity<List<Booking>> getBookings() {
		List<Booking> bookings = bookingService.getBookings();

		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Booking> insertBooking(@RequestBody Booking booking) {

		Booking bookingadd = bookingService.insertBooking(booking);
		logger.debug(" {}." , bookingadd);
		return new ResponseEntity<>(bookingadd, HttpStatus.CREATED);
	}
	
	@PostMapping("{uid}/route/{rid}/booking")
	public ResponseEntity<Booking> insertUserBooking(@PathVariable("uid") Integer uid, @PathVariable("rid") Integer rid,@RequestBody Booking booking) {

		User user = userService.getUserById(uid);
		Route rt = routeService.getRouteById(rid);
		
		Booking bk =new Booking();
		bk.setSeats(booking.getSeats());
		bk.setDate(rt.getDate());
		Booking booked =bookingService.insertBooking(bk);
		
		Set<Route> routes = new HashSet<>();
		routes.add(rt);
		booked.setRoute(routes);
		
		List<Booking> bkg = new ArrayList<>();
		bkg.add(booked);
		user.setBookings(bkg);
		rt.setBooking(bkg);
		routeService.updateRoute(rt);
		userService.updateUser(user);
		
		booked.setUser(user);
		bookingService.updateBooking(booked);
		
		logger.debug(" {}." , booked);
		return new ResponseEntity<>(booked, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable("id") Integer bid) {
		Booking booking = bookingService.getBookingById(bid);
		if(booking !=null)
			return new ResponseEntity<>(booking, HttpStatus.OK);
		else
			throw new DataNotFoundException("Booking " + bid + " not found");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteBooking(@PathVariable("id") Integer bid) {

		Booking booking = bookingService.deleteBooking(bid);
		return ResponseEntity.ok(booking.getBookingId());
	}

	@PutMapping("{id}")
	public ResponseEntity<Booking> updateUser(@PathVariable("id") Integer bid, @RequestBody Booking booking) {
		booking.setBookingId(bid);
		Booking bookingupdate = bookingService.updateBooking(booking);
		logger.debug(" {}." , bookingupdate);
		return new ResponseEntity<>(bookingupdate, HttpStatus.OK);
	}
	

}
