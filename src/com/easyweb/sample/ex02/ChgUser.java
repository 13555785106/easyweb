package com.easyweb.sample.ex02;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.bean.BeanUtils;
import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.db.dac.UserDac;
import com.easyweb.db.model.User;

@WebServlet(name="sample.ex02.ChgUser",urlPatterns="/sample/ex02/ChgUser")
public class ChgUser extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpReqResp hrr) throws ServletException, IOException {
		User user = UserDac.getInstance().getUser(Integer.parseInt(hrr.getRequest().getParameter("id")));
		System.out.println(user);
		if (user != null) {
			hrr.setReqParams(BeanUtils.bean2MapStr(user));
		}
		hrr.forwardByViewName("Input.jsp");
	}

	@Override
	public void doPost(HttpReqResp hrr) throws ServletException, IOException {
		User user = hrr.convertAndValidate(User.class);
		if (user != null) {
			if (!user.getPasswd().equals(user.getConfirmPasswd())) {
				hrr.addParamError("confirmPasswd", "确认密码与密码不同");
			}
			if (UserDac.getInstance().accountExist(user.getId(), user.getAccount())) {
				hrr.addParamError("account", "账号已经存在");
			}

			if (hrr.hasErrors()) {
				hrr.forwardByViewName("Input.jsp");
				return;
			} else {
				if (UserDac.getInstance().chgUser(user)) {
					hrr.getResponse().sendRedirect("ListUser");
					return;
				}
			}
		}
		hrr.forward("/WEB-INF/jsp/error.jsp");
	}
}
