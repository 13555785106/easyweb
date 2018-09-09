package com.easyweb.db.model;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	@NotBlank(message = "账号为空")
	private String account;
	@NotBlank(message = "密码为空")
	private String passwd;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "LoginForm [account=" + account + ", passwd=" + passwd + "]";
	}
}
