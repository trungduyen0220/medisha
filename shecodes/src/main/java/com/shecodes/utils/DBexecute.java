package com.shecodes.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class DBexecute {

	private static final Logger logger = LogManager.getLogger(DBexecute.class);

	/**
	 * 
	 * @author ntmduyen
	 * @datetime Jul 24, 2020 - 3:32:26 PM
	 * @return
	 */
	public Connection getConnection() {

		logger.debug("connect() start.");

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.URL_USERNAME, Constants.URL_PASSWORD);
			logger.debug("Connected to the driver server successfully.");
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		logger.debug("connect() end.");
		return conn;
	}
}
