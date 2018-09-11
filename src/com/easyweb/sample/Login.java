package com.easyweb.sample;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.db.dac.UserDac;
import com.easyweb.db.model.LoginForm;
import com.easyweb.db.model.User;
@WebServlet(name="Login",urlPatterns="/Login")
public class Login extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		LoginForm loginForm = hrr.convertAndValidate(LoginForm.class);
		if (loginForm != null && !hrr.hasErrors()) {
			User user = UserDac.getInstance().login(loginForm.getAccount(), loginForm.getPasswd());
			if (user != null) {
				hrr.getRequest().getSession().setAttribute("curUser", user);
				hrr.getResponse().sendRedirect("MainPage");
				return;
			} else {
				hrr.addExeError("登录失败");
			}
		}
		if (hrr.hasErrors()) {
			hrr.forwardByViewName("Input.jsp");
			return;
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
