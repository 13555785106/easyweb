package com.easyweb.db.dac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import com.easyweb.db.DB;
import com.easyweb.db.model.Doc;

public class DocDac extends Dac {
	private static DocDac instance = null;

	private DocDac() {
	}

	public static DocDac getInstance() {
		if (instance == null)
			instance = new DocDac();
		return instance;
	}

	public List<Doc> allDocs() {
		List<Doc> docList = null;
		try {
			docList = DB.getInstance().qr().query(getSql("allDocs"), new BeanListHandler<>(Doc.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return docList;
	}

	public Doc getDoc(int id) {
		Doc doc = null;
		try {
			doc = DB.getInstance().qr().query(getSql("getDoc"), new BeanHandler<>(Doc.class), new Object[] { id });
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public void addDoc(Doc doc) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DB.getInstance().qr().getDataSource().getConnection();
			stmt = conn.prepareStatement(getSql("addDoc"), Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, doc.getFileName());
			stmt.setLong(2, doc.getFileSize());
			stmt.setString(3, doc.getPath());
			stmt.setTimestamp(4, doc.getDateTime());
			if (stmt.executeUpdate() == 1) {
				rs = stmt.getGeneratedKeys();
				if (rs.next())
					doc.setId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public boolean delDoc(int id) {
		try {
			return 1 == DB.getInstance().qr().update(getSql("delDoc"), id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
