package com.example.sbdemo.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


    /**
    * @ClassName: MyStartupRunner
    * @Description: 服务启动时初始化加载数据
    * @author zpj
    * @date 2018年7月24日
    *
    */
@Component
@Order(value=2)//使用@Order 注解来定义执行顺序 注解的执行优先级是按value值从小到大顺序。
public class MyStartupRunner implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作2222222222<<<<<<<<<<<<<");
	}

}
