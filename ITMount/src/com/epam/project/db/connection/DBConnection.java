package com.epam.project.db.connection;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {
	private static volatile HikariDataSource instance;

	private DBConnection() {

	}

	private static void initialize() {

		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://localhost:3306/epamfinalproject?useUnicode=true&amp;characterEncoding=utf8");		//SET DATA BASE
		config.setUsername("root");								//CHANGE TO YOUR DB USER NAME
		config.setPassword("root");								//CHANGE TO YOUR DB PASSWORD
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("useServerPrepStmts", "true");
		config.addDataSourceProperty("minimumIdle", 5);
		config.addDataSourceProperty("maximumPoolSize", 20);

		instance = new HikariDataSource(config);

	}

	public static Connection getConnection() {
		HikariDataSource localInstance = instance;
		Connection connection = null;
		if (localInstance == null) {
			synchronized (DBConnection.class) {
				localInstance = instance;
				if (localInstance == null) {
					initialize();
					localInstance = instance;
				}
			}
		}
		try {
			connection = localInstance.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
