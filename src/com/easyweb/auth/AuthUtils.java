package com.easyweb.auth;

import java.util.HashSet;
import java.util.Set;

import com.easyweb.db.model.User;

public final class AuthUtils {
	private AuthUtils() {
	}
	public static int getAuthOfManageUsers(User user) {
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
	public static Set<Integer> getAuthOfManageFiles(User user) {

		Set<Integer> authoritiesSet = new HashSet<Integer>();
		String[] authorities  = new String[] {};
		if (user.getAuthorities() != null) {
			authorities = user.getAuthorities().split(",");
		}
		for (String authority : authorities) {
			int val = Integer.parseInt(authority);
			if (val >= Constants.AUTH_LIST_FILE && val <= Constants.AUTH_DEL_FILE )
				authoritiesSet.add(val);
		}
		if(user.getId()==1) {
			for(int i=Constants.AUTH_LIST_FILE;i<=Constants.AUTH_DEL_FILE;i++)
				authoritiesSet.add(i);
		}
		return authoritiesSet;
	}
}
