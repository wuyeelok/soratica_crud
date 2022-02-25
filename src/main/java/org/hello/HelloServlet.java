package org.hello;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// JDBC Connection Info
	private final String JDBC_DRIVER = "org.postgresql.Driver";
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/socratica";
	private final String JDBC_USERNAME = "kenneth";
	private final String JDBC_PASSWORD = "kenneth";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		Boolean canConnectDB = false;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

			canConnectDB = true;
		} catch (SQLException e) {
			System.out.println("Fail to Connect!");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver found!");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		response.getWriter().append("Connect to DB status: " + canConnectDB + ".  The Context Path is: ")
				.append(request.getContextPath());
	}

}
