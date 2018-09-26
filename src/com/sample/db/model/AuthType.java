package com.sample.db.model;

public class AuthType {
	private int id;
	private String name;
	private int mode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	@Override
	public int hashCode() {
		return new Integer(this.id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AuthType) {
			AuthType authType = (AuthType) obj;
			return this.id == authType.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "AuthType [id=" + id + ", name=" + name + ", mode=" + mode + "]";
	}

}
