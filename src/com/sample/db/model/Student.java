package com.sample.db.model;

import java.sql.Date;

public class Student {
	private String id;// 非空，可使用uuid
	private String name;// 不能为空，2到6个字符
	private String identityCardNo;// 身份证号，必须符合中国身份证号的格式
	private String sex;// 性别不能为空，必须是男或者女
	private Double tuition;// 学费，可以不填，如果填写必须在2000-5000之间
	private String mobile;// 手机号码，必须为中国有效的手机号码格式
	private String email;// 非空，必须有效的邮箱格式；
	private Date dateOfGraduation;// 毕业日期，可以为空，如果填写必须是一年之后的日期。

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityCardNo() {
		return identityCardNo;
	}

	public void setIdentityCardNo(String identityCardNo) {
		this.identityCardNo = identityCardNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Double getTuition() {
		return tuition;
	}

	public void setTuition(Double tuition) {
		this.tuition = tuition;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfGraduation() {
		return dateOfGraduation;
	}

	public void setDateOfGraduation(Date dateOfGraduation) {
		this.dateOfGraduation = dateOfGraduation;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", identityCardNo=" + identityCardNo + ", sex=" + sex + ", tuition=" + tuition + ", mobile=" + mobile + ", email=" + email + ", dateOfGraduation=" + dateOfGraduation + "]";
	}

}
