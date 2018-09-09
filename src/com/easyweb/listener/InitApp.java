package com.easyweb.listener;

import java.beans.PropertyEditorManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import com.easyweb.db.DB;

@WebListener
public class InitApp implements ServletContextListener {
	BasicDataSource basicDataSource = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		registerPropertyEditor();
		basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("org.apache.derby.jdbc.EmbeddedDriver");
		basicDataSource
				.setUrl("jdbc:derby:" + sce.getServletContext().getRealPath("/") + "WEB-INF/SampleDB;create=true");
		DB.setDataSource(basicDataSource);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (basicDataSource != null)
			try {
				basicDataSource.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 用来注册属性编辑器。
	 */
	private void registerPropertyEditor() {
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getResourceAsStream("/PropertyEditor.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			try {
				PropertyEditorManager.registerEditor(Class.forName(entry.getKey().toString()),
						Class.forName(entry.getValue().toString()));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
