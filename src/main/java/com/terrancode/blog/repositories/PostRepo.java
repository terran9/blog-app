package com.terrancode.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terrancode.blog.entities.Category;
import com.terrancode.blog.entities.Post;
import com.terrancode.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category cat);
	
}
