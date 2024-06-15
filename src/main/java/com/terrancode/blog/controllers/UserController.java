package com.terrancode.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.terrancode.blog.dto.BlogAppResponse;
import com.terrancode.blog.dto.UserDto;
import com.terrancode.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
		return new ResponseEntity<UserDto>(userService.createUser(userDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getUsers(){
		return new ResponseEntity<List<UserDto>>(userService.getAllUsers(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@Valid @PathVariable("id") Integer id){
		return new ResponseEntity<UserDto>(userService.getUserById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") Integer id){
		return new ResponseEntity<UserDto>(userService.updateUser(userDto, id), HttpStatus.OK);
	} 
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BlogAppResponse> deleteUser( @PathVariable("id") Integer id){
		userService.deleteUser(id);
		return new ResponseEntity<BlogAppResponse>(new BlogAppResponse("User deleted successfully", true), HttpStatus.OK);
	}
	
}
