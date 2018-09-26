package com.sample.ex02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.db.dac.AuthorityDac;
import com.sample.db.dac.UserDac;


@WebServlet(name="com.sample.ex02.ListUser",urlPatterns="/sample/ex02/ListUser")
public class ListUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.setAttribute("authTypes", AuthorityDac.getInstance().allAuths());
		hrr.setReqResult(UserDac.getInstance().allUsers());
		hrr.forwardByViewName("Result.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
