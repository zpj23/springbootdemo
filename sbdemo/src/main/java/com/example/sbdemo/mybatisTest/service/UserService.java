package com.example.sbdemo.mybatisTest.service;

import com.example.sbdemo.mybatisTest.entity.User;

public interface UserService {
	 public User getUserById(int userId);

	 boolean addUser(User record);
}
