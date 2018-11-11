package com.sample.ex02;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.easyweb.core.EasyHttpServlet;
import com.easyweb.core.HttpReqResp;
import com.easyweb.utils.Paging;
import com.sample.db.dac.UserDac;
import com.sample.db.model.TurningPageForm;
import com.sample.db.model.User;


@WebServlet(name="com.sample.ex02.TurningPage",urlPatterns="/sample/ex02/TurningPage")
public class TurningPage extends EasyHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpReqResp hrr) throws ServletException, IOException {
		doPost(hrr);
	}

	@Override
	protected void doPost(HttpReqResp hrr) throws ServletException, IOException {
		TurningPageForm turningPageForm = hrr.convertAndValidate(TurningPageForm.class);

		if (turningPageForm.getLastPage() != null)
			turningPageForm.setPageNo(Integer.MAX_VALUE);
		if (turningPageForm.getFirstPage() != null)
			turningPageForm.setPageNo(0);
		if (turningPageForm.getPreviousPage() != null)
			turningPageForm.setPageNo(turningPageForm.getPageNo()-1);
		if (turningPageForm.getNextPage() != null)
			turningPageForm.setPageNo(turningPageForm.getPageNo()+1);
		List<User> list = UserDac.getInstance().allUsers();
		if (turningPageForm.getAccount()!=null && !turningPageForm.getAccount().isEmpty()) {
			String account = turningPageForm.getAccount();
			List<User> tempList = new ArrayList<User>();
			for(User user : list) {
				if(user.getAccount().contains(account))
					tempList.add(user);
			}
			list = tempList;
			
		}
		Paging<User> paging = new Paging<User>(turningPageForm.getPageSize(), turningPageForm.getPageNo(), list);
		hrr.setReqResult(paging);
		hrr.getRequest().setAttribute("formData", turningPageForm);
		hrr.forwardByViewName("Result.jsp");

	}
}
//list = User2Datas.getInstance().getDatas().stream().filter(o -> o.getAccount().contains(str)).collect(Collectors.toList());