package com.example.sbdemo.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

    /**
    * @ClassName: MyServletContextListener
    * @Description: 实现ServletContextListener接口
    * @author zpj
    * @date 2018年7月24日
    *
    */
    
@WebListener
public class MyServletContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContex销毁");
        System.out.println(arg0.getServletContext().getServerInfo());
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContex初始化");
	}
	
}
