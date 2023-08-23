package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.model.Category;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<Category> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void deleteCategoryById(int id) {
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(int id) { //returning optional as category may or may not exist
		return categoryRepository.findById(id);
	}
}
