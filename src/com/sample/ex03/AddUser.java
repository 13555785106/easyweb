package com.sample.ex03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.auth.AuthUtils;
import com.sample.auth.Constants;
import com.sample.db.dac.AuthorityDac;
import com.sample.db.dac.UserDac;
import com.sample.db.model.User;


@WebServlet(name="com.sample.ex03.AddUser",urlPatterns="/sample/ex03/AddUser")
public class AddUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		int authOfManageUsers = AuthUtils.getAuthOfManageUsers((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (authOfManageUsers < Constants.AUTH_ADD_USER) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		
		hrr.setAttribute("authTypes", AuthorityDac.getInstance().allAuths());
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		int authOfManageUsers = AuthUtils.getAuthOfManageUsers((User) hrr.getRequest().getSession().getAttribute("curUser"));
		if (authOfManageUsers < Constants.AUTH_ADD_USER) {
			hrr.forward("/WEB-INF/jsp/noauthority.jsp");
			return ;
		}
		
		hrr.setAttribute("authTypes", AuthorityDac.getInstance().allAuths());
		User user = hrr.convertAndValidate(User.class);
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if (UserDac.getInstance().accountExist(user.getAccount())) {
				hrr.addParamError("account", "账号已经存在");
			}
			if (hrr.hasErrors()) {
				hrr.forwardByViewName("Input.jsp");
				return;
			} else {
				UserDac.getInstance().addUser(user);
				if (user.getId()>0) {
					hrr.setReqResult(user);
					hrr.forwardByViewName("Success.jsp");
				} else {
					hrr.addExeError("添加用户失败");
					hrr.forwardByViewName("Input.jsp");
				}
				return;
			}
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
