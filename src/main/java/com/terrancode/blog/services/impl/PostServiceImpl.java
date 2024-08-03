package com.terrancode.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.terrancode.blog.dto.BlogPostResponse;
import com.terrancode.blog.dto.PostDto;
import com.terrancode.blog.entities.Category;
import com.terrancode.blog.entities.Post;
import com.terrancode.blog.entities.User;
import com.terrancode.blog.exceptions.ResourceNotFoundException;
import com.terrancode.blog.repositories.CategoryRepo;
import com.terrancode.blog.repositories.PostRepo;
import com.terrancode.blog.repositories.UserRepo;
import com.terrancode.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));

		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(cat);

		return modelMapper.map(postRepo.save(post), PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		return modelMapper.map(postRepo.save(post), PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		postRepo.delete(post);
	}

	@Override
	public BlogPostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortType) {
		Sort s = "asc".equalsIgnoreCase(sortType) ? Sort.by(sortBy).ascending() :  Sort.by(sortBy).descending(); 
		Pageable p = PageRequest.of(pageNumber, pageSize, s);
//		List<Post> allPosts = postRepo.findAll();
		Page<Post> page = postRepo.findAll(p);
		List<Post> allPosts = page.getContent();
		List<PostDto> posts = allPosts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		BlogPostResponse response = new BlogPostResponse();
		response.setContent(posts);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setLastPage(page.isLast());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		return response;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		return modelMapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", " id ", categoryId));
		List<Post> posts = postRepo.findByCategory(cat);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " id ", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String searchText) {
		List<Post> posts = postRepo.searchByTitleContaining(searchText);
//		List<Post> posts = postRepo.searchByTitle("%" + searchText +"%");
		List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
