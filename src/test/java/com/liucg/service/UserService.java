package com.liucg.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.liucg.pojo.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserService {
	@Autowired
	Userservice userService;
	@Test
	public void List() {
		List<User> list=userService.list();
		for(User user:list) {
			System.out.println(user.getName());
		}
	}
}
