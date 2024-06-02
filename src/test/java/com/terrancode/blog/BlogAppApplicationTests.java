package com.terrancode.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.terrancode.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApplicationTests {
	
	
	@Autowired
	private UserRepo userRepo;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void userRepoTest() {
		String className = userRepo.getClass().getName();
		//user repo implementation class name
		//spring boot scans the repositories and creates the impl classes dynamically(proxy classes)  
		String packageName = userRepo.getClass().getPackageName();
		System.out.println(className);
		System.out.println(packageName);
	}

}
