package com.terrancode.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.terrancode.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
