package com.example.sbdemo.mybatisTest.dao;

import java.util.List;

import com.example.sbdemo.mybatisTest.entity.User;

public interface UserDao {
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    List<User> hahaha();
    
}
