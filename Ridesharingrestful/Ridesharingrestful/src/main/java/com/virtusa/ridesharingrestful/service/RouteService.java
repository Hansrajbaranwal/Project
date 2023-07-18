package com.virtusa.ridesharingrestful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ridesharingrestful.entity.Route;
import com.virtusa.ridesharingrestful.repository.RouteRepository;



@Service
public class RouteService {
	
	@Autowired
    private RouteRepository repo;
	
	public List<Route> getRoutes() {
        return repo.findAll();
    }
	
	public Route getRouteById(Integer id) {
        return repo.findById(id).orElse(null);
    }
    
    public Route deleteRoute(Integer id) {
    	Route route=getRouteById(id);
    	if(route!=null) {
    		repo.deleteById(id);
    	}
    	return route;
    }
    
    public List<Route> searchRoute(String keyword) {
        return repo.search(keyword);
    }

	public Route insertRoute(Route route) {
		
		return  repo.save(route);
	}
	
	public Route updateRoute(Route route) {
		
		return repo.save(route);
	}

}
