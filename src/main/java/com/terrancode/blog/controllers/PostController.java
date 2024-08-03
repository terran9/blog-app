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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terrancode.blog.config.BlogConstants;
import com.terrancode.blog.dto.BlogAppResponse;
import com.terrancode.blog.dto.BlogPostResponse;
import com.terrancode.blog.dto.PostDto;
import com.terrancode.blog.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto,
			@PathVariable("userId") Integer userId, @PathVariable("categoryId") Integer categoryId) {
		return new ResponseEntity<PostDto>(postService.createPost(postDto, userId, categoryId), HttpStatus.CREATED);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("userId") Integer userId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByUser(userId), HttpStatus.OK);
	}

	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("categoryId") Integer categoryId) {
		return new ResponseEntity<List<PostDto>>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<BlogPostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = BlogConstants.DEF_PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = BlogConstants.DEF_PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = BlogConstants.DEF_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortType", defaultValue = BlogConstants.ASCENDING, required = false) String sortType ) {
		return new ResponseEntity<BlogPostResponse>(postService.getAllPosts(pageNumber, pageSize, sortBy, sortType), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("postId") Integer postId) {
		return new ResponseEntity<PostDto>(postService.getPostById(postId), HttpStatus.OK);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<BlogAppResponse> deletePost(@PathVariable("postId") Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<BlogAppResponse>(new BlogAppResponse("Category deleted successfully", true),
				HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("postId") Integer postId) {
		return new ResponseEntity<PostDto>(postService.updatePost(postDto, postId), HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{searchText}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable("searchText") String searchText) {
		return new ResponseEntity<List<PostDto>>(postService.searchPosts(searchText), HttpStatus.OK);
	}
	

}
