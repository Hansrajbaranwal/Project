package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategory(Category category);

}
