package com.easyweb.sample.getutf8;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sample/getutf8/UTF8Parameter")
public class UTF8Parameter extends HttpServlet {
	private static final long serialVersionUID = 1L;  

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");//注意，此行代码在GET方式下无作用！尽在POST方式下起作用。
		System.out.println(request.getContentType());
		//URL都是以ASCII方式传递的，所以要自己转！
		String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
		String sex = new String(request.getParameter("sex").getBytes("ISO8859-1"),"UTF-8");
		response.getWriter().append("name="+name+"<br/>").append("sex="+sex+"<br/>");
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
