package com.sample.db.dac;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.sample.db.DB;
import com.sample.db.model.AuthType;
import com.sample.db.model.Authority;


public class AuthorityDac extends Dac {
	private static AuthorityDac instance = null;

	private AuthorityDac() {
	}

	public static AuthorityDac getInstance() {
		if (instance == null)
			instance = new AuthorityDac();
		return instance;
	}

	public List<Authority> allAuthorities() {
		List<Authority> authorityList = null;
		try {
			authorityList = DB.getInstance().qr().query(getSql("allAuthorities"),
					new BeanListHandler<>(Authority.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authorityList;
	}

	public List<AuthType> allAuthTypes() {
		List<AuthType> authTypeList = null;
		try {
			authTypeList = DB.getInstance().qr().query(getSql("allAuthTypes"), new BeanListHandler<>(AuthType.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authTypeList;
	}

	public Map<AuthType, List<Authority>> allAuths() {
		Map<AuthType, List<Authority>> authTypeMap = new HashMap<AuthType, List<Authority>>();
		Map<Integer, List<Authority>> authorityMap = new HashMap<>();
		for (Authority authority : allAuthorities()) {
			List<Authority> authorityList = authorityMap.get(authority.getType());
			if (authorityList == null) {
				authorityList = new ArrayList<>();
				authorityMap.put(authority.getType(), authorityList);
			}
			authorityList.add(authority);
		}

		for (AuthType authType : allAuthTypes()) {
			authTypeMap.put(authType, authorityMap.get(authType.getId()));
		}
		return authTypeMap;
	}
}
