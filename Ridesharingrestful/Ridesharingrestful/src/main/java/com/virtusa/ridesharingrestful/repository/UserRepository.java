package com.virtusa.ridesharingrestful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virtusa.ridesharingrestful.entity.User;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	@Query(value = "SELECT u FROM User u WHERE u.id LIKE '%' || :keyword || '%'"
			+ " OR u.firstName LIKE '%' || :keyword || '%'" + " OR u.lastName LIKE '%' || :keyword || '%'"
			+ " OR u.email LIKE '%' || :keyword || '%'" + " OR u.mobileNumber LIKE '%' || :keyword || '%'")
	public List<User> search(@Param("keyword") String keyword);

	User findByMobileNumber(String mobileNumber);
}
