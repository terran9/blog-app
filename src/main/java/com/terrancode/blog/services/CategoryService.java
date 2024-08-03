package com.terrancode.blog.services;

import java.util.List;

import com.terrancode.blog.dto.CategoryDto;

public interface CategoryService {

	
	
	CategoryDto createCategory(CategoryDto category);
	CategoryDto updateCategory(CategoryDto category, Integer categoryId);
	CategoryDto getCategory(Integer categoryId);
	List<CategoryDto> getAllCategories();
	void deleteCategory(Integer categoryId); 
	
}
