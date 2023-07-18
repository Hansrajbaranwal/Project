package com.virtusa.ridesharingrestful.controller;

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
import com.virtusa.ridesharingrestful.entity.Route;
import com.virtusa.ridesharingrestful.exception.DataNotFoundException;
import com.virtusa.ridesharingrestful.service.RouteService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user/route")
public class RouteController {
	
	Logger logger = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	private RouteService routeService;

	@GetMapping
	public ResponseEntity<List<Route>> getRoutes() {
		List<Route> listroute = routeService.getRoutes();
		
		return new ResponseEntity<>(listroute, HttpStatus.OK);
	}
	

	@PostMapping
	public  ResponseEntity<Route> insertRoute(@RequestBody Route route) {
		Route routeadded = routeService.insertRoute(route);
		logger.debug(" {}." , routeadded);
		return new ResponseEntity<>(routeadded, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<Route> getRoute(@PathVariable("id") Integer id) {
		Route route = routeService.getRouteById(id);
		if(route !=null)
			return new ResponseEntity<>(route, HttpStatus.OK);
		else
			throw new DataNotFoundException("Route " + id + " not found");
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteRoute(@PathVariable(name = "id") Integer id) {
		Route route = routeService.deleteRoute(id);
		logger.debug(" {}." , route);
		return ResponseEntity.ok(route.getRouteId());
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Route> updateRoute(@PathVariable("id") Integer rid, @RequestBody Route route) {
		route.setRouteId(rid);
		Route routeupdate = routeService.updateRoute(route);
		logger.debug(" {}." , routeupdate);
		return new ResponseEntity<>(routeupdate, HttpStatus.OK);
	}

	@GetMapping("searchRoute/{keyword}")
	public ResponseEntity<List<Route>> searchRoute(@PathVariable("keyword") String keyword) {
		List<Route> result = routeService.searchRoute(keyword);

		return new ResponseEntity<>(result, HttpStatus.FOUND);
	}
	
}
