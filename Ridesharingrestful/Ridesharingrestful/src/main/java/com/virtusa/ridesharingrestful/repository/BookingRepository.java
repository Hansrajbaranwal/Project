package com.virtusa.ridesharingrestful.repository;



import org.springframework.stereotype.Repository;

import com.virtusa.ridesharingrestful.entity.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Repository
@EnableJpaRepositories
public interface BookingRepository extends JpaRepository<Booking,Integer> {
	

}
