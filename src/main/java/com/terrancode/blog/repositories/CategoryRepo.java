package com.terrancode.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terrancode.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
