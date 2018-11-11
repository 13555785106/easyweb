package com.sample.ex03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.auth.Constants;
import com.sample.db.dac.AuthorityDac;
import com.sample.db.dac.UserDac;
import com.sample.db.model.User;


@WebServlet(name = "com.sample.ex03.ListUser", urlPatterns = "/sample/ex03/ListUser")
public class ListUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		int authOfManageUsers = getAuthOfManageUsers((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (authOfManageUsers >= Constants.AUTH_LIST_USER) {
			hrr.setAttribute("authOfManageUsers", authOfManageUsers);
			hrr.setAttribute("authTypes", AuthorityDac.getInstance().allAuths());
			hrr.setReqResult(UserDac.getInstance().allUsers());
			hrr.forwardByViewName("Result.jsp");
		} else {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
		}
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}

	private int getAuthOfManageUsers(User user) {
		if (user.getId() == 1)
			return Constants.AUTH_DEL_USER;
		String[] authorities = new String[] {};
		if (user.getAuthorities() != null) {
			authorities = user.getAuthorities().split(",");
		}
		for (String authority : authorities) {
			int val = Integer.parseInt(authority);
			if (val >= Constants.AUTH_LIST_USER && val <= Constants.AUTH_DEL_USER)
				return val;
		}
		return 0;
	}
}
