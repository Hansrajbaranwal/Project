package com.virtusa.ridesharingrestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.ridesharingrestful.entity.Route;

@Repository
@EnableJpaRepositories
public interface RouteRepository extends JpaRepository<Route, Integer> {

	@Query(value = "SELECT r FROM Route r WHERE r.routeId LIKE '%' || :keyword || '%'"
			+ " OR r.startPoint LIKE '%' || :keyword || '%'" + " OR r.endPoint LIKE '%' || :keyword || '%'"
			+ " OR r.date LIKE '%' || :keyword || '%'" + " OR r.time LIKE '%' || :keyword || '%'"
			+ " OR r.seats LIKE '%' || :keyword || '%'" + " OR r.distance LIKE '%' || :keyword || '%'")
	public List<Route> search(@Param("keyword") String keyword);
}
