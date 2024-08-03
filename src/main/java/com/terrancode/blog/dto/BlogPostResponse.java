package com.terrancode.blog.dto;

import java.util.List;

import lombok.Data;

@Data
public class BlogPostResponse {
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalPages;
	private long totalElements;
	private boolean lastPage; 
}
