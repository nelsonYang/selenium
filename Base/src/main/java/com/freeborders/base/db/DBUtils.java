package com.freeborders.base.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.driver.OracleDriver;

import com.freeborders.base.constant.PathConstant;
import com.freeborders.base.context.TestApplicationContext;
import com.freeborders.base.context.TestApplicationContextBuilderImpl;

public class DBUtils {
	private static ThreadLocal<Connection> thConn = new ThreadLocal<Connection>();
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.registerDriver(new OracleDriver());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		if (thConn.get() == null) {
			String url = TestApplicationContextBuilderImpl.getProperty(PathConstant.JDBC_URL);
			String username = TestApplicationContextBuilderImpl.getProperty(PathConstant.JDBC_USERNAME);
			String password = TestApplicationContextBuilderImpl.getProperty(PathConstant.JDBC_PASSWORD);
			try {
				Connection con = java.sql.DriverManager.getConnection(url, username, password);
				thConn.set(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return thConn.get();
	}

	public static void closeAll(PreparedStatement ps, ResultSet rs) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (thConn.get() != null) {
				thConn.get().close();
			}
			thConn.remove();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void closeAll(PreparedStatement ps) {
		closeAll(ps, null);
	}
}
