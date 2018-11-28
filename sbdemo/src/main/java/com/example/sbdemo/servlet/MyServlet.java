package com.example.sbdemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//// 不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.MyServlet
@WebServlet(urlPatterns="/sevlet/myServlet" ,description="Servlet说明文字")
public class MyServlet extends HttpServlet{

	//DispatcherServlet 默认拦截“/”，MyServlet 拦截“/sevlet/*”，MyServlet2 拦截“/sevlet/myservlet”，那么在我们访问 http://localhost:8080/xs/myservlet 的时候系统会怎么处理呢？如果访问 http://localhost:8080/xs/abc 的时候又是什么结果呢？这里就不给大家卖关子了，其结果是“匹配的优先级是从精确到模糊，复合条件的Servlet并不会都执行”
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = -2115551891245664672L;

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
		//super.doPost(req, resp);
		System.out.println(">>>>>>>>>>doPost()<<<<<<<<<<<");
        resp.setContentType("text/html");  
        PrintWriter out = resp.getWriter();  
        out.println("<html>");  
        out.println("<head>");  
        out.println("<title>Hello World</title>");  
        out.println("</head>");  
        out.println("<body>");  
        out.println("<h1>大家好，我的名字叫Servlet</h1>");  
        out.println("</body>");  
        out.println("</html>"); 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
}
