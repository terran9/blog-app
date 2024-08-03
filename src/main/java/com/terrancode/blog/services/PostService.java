package com.terrancode.blog.services;

import java.util.List;

import com.terrancode.blog.dto.PostDto;
import com.terrancode.blog.entities.Post;

public interface PostService {

	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

	PostDto updatePost(PostDto postDto, Integer postId);

	void deletePost(Integer postId);

	List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);

	PostDto getPostById(Integer postId);

	List<PostDto> getPostsByCategory(Integer categoryId);

	List<PostDto> getPostsByUser(Integer userId);

	List<Post> searchPosts(String searchText);

}
