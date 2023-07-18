package com.virtusa.ridesharingrestful.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ridesharingrestful.entity.Booking;
import com.virtusa.ridesharingrestful.repository.BookingRepository;


@Service
public class BookingService {

	@Autowired
    private BookingRepository bookingRepository;
	
	public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }
	
	public Booking getBookingById(Integer id) {
        return bookingRepository.findById(id).orElse(null);
    }
    
    public Booking deleteBooking(Integer id) {
    	Booking booking=getBookingById(id);
    	if(booking!=null) {
    		bookingRepository.deleteById(id);
    	}
    	return booking;
    }
    
	public Booking insertBooking(Booking booking) {
		
		return  bookingRepository.save(booking);
	}
	
	public Booking updateBooking(Booking booking) {
		
		return bookingRepository.save(booking);
	}
}
