package com.terrancode.blog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.terrancode.blog.dto.BlogAppResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<BlogAppResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
		String message = e.getMessage();
		BlogAppResponse apiResponse = new BlogAppResponse(message, false);
		return new ResponseEntity<BlogAppResponse>(apiResponse, HttpStatus.NOT_FOUND);
	}

}
