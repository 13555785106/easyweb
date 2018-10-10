package com.sample.db;

import java.sql.SQLException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;
/*
 * 设置DB类的数据源
 * */
@WebListener
public class InitDB implements ServletContextListener {
	BasicDataSource basicDataSource = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
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


}
