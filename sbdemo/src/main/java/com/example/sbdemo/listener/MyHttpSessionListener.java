package com.example.sbdemo.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;




    /**
    * @ClassName: MyHttpSessionListener
    * @Description: 监听Session的创建与销毁
    * @author zpj
    * @date 2018年7月24日
    *
    */
    
@WebListener
public class MyHttpSessionListener implements HttpSessionListener  {

		@Override
		public void sessionCreated(HttpSessionEvent arg0) {
			System.out.println("Session 被创建");
		}

		@Override
		public void sessionDestroyed(HttpSessionEvent arg0) {
			System.out.println("Session 被销毁");
		}

}
