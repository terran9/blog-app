package com.terrancode.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
//@Data
public class UserDto {
	
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "User name must contain min 4 characters")
	private String name;
	
	@Email(message = "Email is not valid")
	private String email;
	
	@NotEmpty
	@Size(min = 4, max = 15, message = "Password must contain min 8 characters and max 15")
	private String password;
	
	@NotEmpty
	private String about;
}

