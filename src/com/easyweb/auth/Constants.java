package com.easyweb.auth;

public class Constants {
	private Constants() {
	}
	
	
	public static final int AUTH_TYPE_MODE_LEVEL = 0;
	public static final int AUTH_TYPE_MODE_EQUAL= 1;
	
	public static final int AUTH_TYPE_USER = 0;
	public static final int AUTH_TYPE_FILE = 1;
	
	public static final int AUTH_LIST_USER = 10000;
	public static final int AUTH_ADD_USER = 10001;
	public static final int AUTH_CHG_USER = 10002;
	public static final int AUTH_DEL_USER = 10003;

	
	public static final int AUTH_LIST_FILE = 10100;
	public static final int AUTH_UPLOAD_FILE = 10101;
	public static final int AUTH_DOWNLOAD_FILE = 10102;
	public static final int AUTH_DEL_FILE = 10103;
}
