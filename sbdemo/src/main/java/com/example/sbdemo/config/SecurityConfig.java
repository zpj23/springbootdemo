package com.example.sbdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.sbdemo.mybatisTest.service.UserService;


    /**
    * @ClassName: SecurityConfig
    * @Description: TODO(自定义配置spring security)
    * @author zpj
    * @date 2019年4月2日
    *
    */
    
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//    private UserService userService;
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
////		super.configure(http);
//		http.authorizeRequests().antMatchers("/file/**","/user").access("123456")
//		.antMatchers("/file/**","/**").permitAll()
//		.and().formLogin().loginPage("/hello/hello").failureUrl("/hello");
//	}
//	
//	@Override
//		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//			// TODO Auto-generated method stub
////			super.configure(auth);
//			auth.userDetailsService(new UserDetailsService() {
//				
//				@Override
//				public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
//					// TODO Auto-generated method stub
//					return userService.getUserById(1);
//				}
//			});
//		}
//}
