package com.easyweb.sample.ex03;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.auth.AuthUtils;
import com.easyweb.auth.Constants;
import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.db.dac.UserDac;
import com.easyweb.db.model.User;

@WebServlet(name="sample.ex03.DelUser",urlPatterns="/sample/ex03/DelUser/*")
public class DelUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		int authOfManageUsers = AuthUtils.getAuthOfManageUsers((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (authOfManageUsers < Constants.AUTH_DEL_USER) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		String id = hrr.getRequest().getParamSegments()[0];
		if (id != null) {
			UserDac.getInstance().delUser(Integer.parseInt(id));
		}
		hrr.getResponse().sendRedirect("../ListUser");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		doGet(hrr);
	}
}
