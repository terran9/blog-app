package com.terrancode.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	private Integer categoryId;
	
	@NotEmpty
	@Size(min = 4, message = "Category title must contain min 4 characters")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 4, message = "Category name must contain min 10 characters")
	private String categoryDescription;
	
}

