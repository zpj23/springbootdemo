package com.example.sbdemo.mybatisTest.entity;

import java.io.Serializable;
import java.util.Date;


public class User  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;    
    private String username;
    private String password;
    private Integer age;
    public User(){
    	
    }
    
    public User(String username,String password){
    	this.username=username;
    	this.password=password;
    }
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
    
}
