package com.easyweb.db;

import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

import javax.sql.DataSource;

public class DB {
	private static DataSource dataSource = null;
	private static DB instance = null;
	private QueryRunner query = null;

	private DB() throws SQLException {
		if (dataSource == null)
			throw new SQLException("数据源为空!");
		else
			query = new QueryRunner(dataSource);
	}

	public static DB getInstance() {
		if (instance == null) {
			try {
				instance = new DB();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static void setDataSource(DataSource dataSource) {
		DB.dataSource = dataSource;
	}

	public QueryRunner qr() {
		return query;
	}
}
