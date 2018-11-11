package com.sample.html5ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.json.JSONObject;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.sample.db.dac.UserDac;
import com.sample.db.model.User;


@WebServlet(name="com.sample.html5ajax.AjaxAndJsonAddOrChgUser",urlPatterns="/sample/html5ajax/AjaxAndJsonAddOrChgUser")
public class AjaxAndJsonAddOrChgUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		hrr.getResponse().setContentType("application/json; charset=UTF-8");
		User user = hrr.convertAndValidate(User.class);
		JSONObject result = new JSONObject();
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if ((user.getId() == -1 && UserDac.getInstance().accountExist(user.getAccount()))
					|| (user.getId() != -1 && UserDac.getInstance().accountExist(user.getId(), user.getAccount())))
				hrr.addParamError("account", "账号已经存在");

			if (!hrr.hasErrors()) {
				if (user.getId() == -1) {
					UserDac.getInstance().addUser(user);
					if (user.getId() == -1)
						hrr.addExeError("添加用户失败");
				} else {
					if (!UserDac.getInstance().chgUser(user))
						hrr.addExeError("修改用户失败");
				}
			}
		}
		if (!hrr.hasErrors()) {
			result.put("status", "success");
		} else {
			result.put("status", "failed");
			result.put("paramErrors", hrr.getParamErrors());
			result.put("exeErrors", hrr.getExeErrors());
		}
		System.out.println(result.toString(4));
		hrr.getResponse().getWriter().append(result.toString(4));
	}
}
