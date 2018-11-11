package com.sample;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.auth.AuthUtils;
import com.sample.db.model.User;

@WebServlet(name="com.sample.Menu",urlPatterns="/Menu")
public class Menu extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		boolean showManageUsers = false;
		boolean showManageFiles = false;
		User user = (User)hrr.getRequest().getSession().getAttribute("curUser");
		if(AuthUtils.getAuthOfManageUsers(user)>0)
			showManageUsers = true;
		if(AuthUtils.getAuthOfManageFiles(user).size()>0)
			showManageFiles = true;
		hrr.setAttribute("showManageUsers", showManageUsers);
		hrr.setAttribute("showManageFiles", showManageFiles);
		hrr.includeByViewName("Result.jsp");
	}
}
