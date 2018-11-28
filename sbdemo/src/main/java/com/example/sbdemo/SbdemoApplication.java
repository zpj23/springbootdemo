package com.example.sbdemo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan("com.example.sbdemo.mybatisTest.dao")
public class SbdemoApplication {
	
	@Value("${author.name}")
	private String name;
	@Value("${author.age}")
	private String age;
	@Autowired
	private Author author;
	
	@RequestMapping("/")

	public String index(){
		
		System.out.println(author.getAge()+">>>>>"+author.getName());
		return "hello spring boot. "+name+">>>>>"+age;
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(SbdemoApplication.class, args);
	}
}
