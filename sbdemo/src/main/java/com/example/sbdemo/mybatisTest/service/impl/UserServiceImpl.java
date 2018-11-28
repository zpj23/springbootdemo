package com.example.sbdemo.mybatisTest.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.example.sbdemo.mybatisTest.dao.UserDao;
import com.example.sbdemo.mybatisTest.entity.User;
import com.example.sbdemo.mybatisTest.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
    private UserDao userDao;


    public User getUserById(int userId) {
        return userDao.selectByPrimaryKey(userId);
    }

    public boolean addUser(User record){
        boolean result = false;
        try {
            userDao.insertSelective(record);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
