package com.example.sbdemo.mybatisTest.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbdemo.mybatisTest.entity.User;

@RestController
@RequestMapping("/hello")
public class HelloController {

	
	@RequestMapping("/hello")
    public String hello(Model m) throws Exception {
        m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
        if(true){
            throw new Exception("some exception");
        }
        return "hello";
    }
	
	
	@RequestMapping("getuser")
    public User getUser() {
        User user = new User();
        user.setUsername("");
        return user;
    }
	
	@RequestMapping
	public String hello(){
		return "hello spring boot";
	}
	
	 @RequestMapping("/info")
	    public Map<String, String> getInfo(@RequestParam String name) {
	        Map<String, String> map = new HashMap<>();
	        map.put("name", name);
	        return map;
	    }

	    @RequestMapping("/list")
	    public List<Map<String, String>> getList() {
	        List<Map<String, String>> list = new ArrayList<>();
	        Map<String, String> map = null;
	        for (int i = 1; i <= 5; i++) {
	            map = new HashMap<>();
	            map.put("name", "Shanhy-" + i);
	            list.add(map);
	        }
	        return list;
	    }
	    
	    
}
