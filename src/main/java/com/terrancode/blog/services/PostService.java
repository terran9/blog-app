package com.terrancode.blog.services;

import java.util.List;

import com.terrancode.blog.dto.BlogPostResponse;
import com.terrancode.blog.dto.PostDto;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	BlogPostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	List<PostDto> searchPosts(String searchText);

	

}
