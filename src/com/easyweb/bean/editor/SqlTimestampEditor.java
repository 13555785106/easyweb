package com.easyweb.bean.editor;

/**
 * java.sql.Timestamp与字符串之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlTimestampEditor extends DateEditor {
	public SqlTimestampEditor() {
		super(java.sql.Timestamp.class);
	}
}