package com.sample.ex01;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sample.db.dac.UserDac;
import com.sample.db.model.User;


@WebServlet(name="com.sample.ex01.AddUser",urlPatterns="/sample/ex01/AddUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final String REQ_PARAMS = "reqParams";
	protected final String REQ_ERRORS = "reqErrors";
	protected final String REQ_RESULT = "reqResult";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/" + this.getClass().getSimpleName() + "-Input.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Map<String, String> reqParams = new HashMap<String, String>();
		Map<String, String> reqErrors = new HashMap<String, String>();
		request.setAttribute(REQ_PARAMS, reqParams);
		request.setAttribute(REQ_ERRORS, reqErrors);

		String account = request.getParameter("account");
		if (account == null)
			account = "";
		account = account.trim();
		reqParams.put("account", account);
		if (account.length() < 2 || account.length() > 5) {
			reqErrors.put("account", "账号必须是2至5个字符");
		}

		String passwd = request.getParameter("passwd");
		if (passwd == null)
			passwd = "";
		reqParams.put("passwd", passwd);
		if (passwd.length() < 2 || passwd.length() > 5) {
			reqErrors.put("passwd", "账号必须是2至5个字符");
		}

		String confirmPasswd = request.getParameter("confirmPasswd");
		if (confirmPasswd == null)
			confirmPasswd = "";
		reqParams.put("confirmPasswd", confirmPasswd);
		if (confirmPasswd.length() < 2 || confirmPasswd.length() > 5) {
			reqErrors.put("confirmPasswd", "账号必须是2至5个字符");
		}
		if (!confirmPasswd.equals(passwd)) {
			reqErrors.put("passwd", "确认密码必须与密码相同");
		}

		String birthdayStr = request.getParameter("birthday");
		if (birthdayStr == null)
			birthdayStr = "";
		birthdayStr = birthdayStr.trim();
		reqParams.put("birthday", birthdayStr);
		@SuppressWarnings("unused")
		Date birthday = null;
		if (birthdayStr.length() > 0) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birthday = new Date(sdf.parse(birthdayStr).getTime());
				if (birthday.getTime() > new java.util.Date().getTime())
					reqErrors.put("birthday", "生日不能是将来的时间");
			} catch (ParseException e) {
				e.printStackTrace();
				reqErrors.put("birthday", "生日必须为 yyyy-MM-dd 格式");
			}
		}

		String sex = request.getParameter("sex");
		if (sex == null)
			sex = "";
		sex = sex.trim();
		reqParams.put("sex", sex);
		if (!sex.equals("男") && !sex.equals("女")) {
			reqErrors.put("sex", "性别必须是\"男\"或者\"女\"");
		}

		String salaryStr = request.getParameter("salary");
		if (salaryStr == null)
			salaryStr = "";
		salaryStr = salaryStr.trim();
		reqParams.put("salary", salaryStr);
		@SuppressWarnings("unused")
		float salary = 0;
		if (salaryStr.length() > 0) {
			try {
				salary = Float.parseFloat(salaryStr);
				if (!(salary >= 1200 && salary <= 50000))
					reqErrors.put("salary", "工资必须在1200至50000之间");
			} catch (Exception e) {
				e.printStackTrace();
				reqErrors.put("salary", "工资必须是数值");
			}
		}
		String hobbies = "";
		String[] hobbiesStrs = request.getParameterValues("hobbies");
		if (hobbiesStrs != null) {
			for (int i = 0; i < hobbiesStrs.length; i++) {
				hobbies += hobbiesStrs[i];
				if (i < hobbiesStrs.length - 1)
					hobbies += ",";
			}
			reqParams.put("hobbies", hobbies);
		} else
			reqErrors.put("hobbies", "必须选择一个爱好");
		// 一般情况下，forward或sendRedirect之后，我们不会再执行其他操作，因此别忘了使用return结束函数！
		// 原因是在forward或sendRedirect之后，不允许再调用response进行输出。
		if (reqErrors.size() > 0) {
			request.getRequestDispatcher("/WEB-INF/jsp/" + this.getClass().getSimpleName() + "-Input.jsp").forward(request, response);
			return;
		} else {
			User user = new User();
			user.setAccount(account);
			user.setPasswd(passwd);
			user.setConfirmPasswd(confirmPasswd);
			user.setSex(sex);
			user.setBirthday(birthday);
			user.setSalary(salary);
			user.setHobbies(hobbies);
			UserDac.getInstance().addUser(user);
			if (user.getId()>0) {
				request.setAttribute(REQ_RESULT, user);
				request.getRequestDispatcher("/WEB-INF/jsp/" + this.getClass().getSimpleName() + "-Success.jsp").forward(request, response);
				return;
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
	}
}
