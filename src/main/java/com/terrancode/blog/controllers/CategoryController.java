package com.terrancode.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrancode.blog.dto.BlogAppResponse;
import com.terrancode.blog.dto.CategoryDto;
import com.terrancode.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategorys(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCategories(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getCategoryById(@Valid @PathVariable("id") Integer id){
		return new ResponseEntity<CategoryDto>(categoryService.getCategory(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("id") Integer id){
		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BlogAppResponse> deleteCategory( @PathVariable("id") Integer id){
		categoryService.deleteCategory(id);
		return new ResponseEntity<BlogAppResponse>(new BlogAppResponse("Category deleted successfully", true), HttpStatus.OK);
	}
	
}
