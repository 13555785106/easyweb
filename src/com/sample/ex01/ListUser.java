package com.sample.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.db.dac.UserDac;


@WebServlet(name="com.sample.ex01.ListUser",urlPatterns="/sample/ex01/ListUser")
public class ListUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("reqResult",UserDac.getInstance().allUsers());
		request.getRequestDispatcher("/WEB-INF/jsp/"+this.getClass().getSimpleName()+"-Result.jsp").forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException , IOException{
		doGet(request,response);
	}
}
