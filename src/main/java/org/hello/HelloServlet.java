package org.hello;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	// SQL
	private final String SELECT_EARTHQUAKE_SIMPLE = "SELECT * FROM EARTHQUAKE order by EARTHQUAKE_ID LIMIT ?";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Connection conn = null;

		Boolean canConnectDB = false;

		int rowCount = 0;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

			canConnectDB = true;

			PreparedStatement pre = conn.prepareStatement(SELECT_EARTHQUAKE_SIMPLE);
			pre.setInt(1, 9);

			ResultSet rs = pre.executeQuery();

			while (rs.next()) {
				rowCount++;
			}

			rs.close();
		} catch (SQLException e) {
			System.out.println("SQL Error!!!\n");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver found!\n");
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			out.println("<h1>DB SQL Result</h1>");
			out.println("<p>Result row count: " + rowCount + "</p>");
			out.println("<footer>DB status: " + canConnectDB + "</footer>");
		} finally {
			out.close();
		}
	}

}
