package org.hello;

import java.io.IOException;
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
	private final String SELECT_EARTHQUAKE_SIMPLE = "SELECT * FROM EARTHQUAKE where EARTHQUAKE_ID = ?";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int earthquakeId = 0;
		try {
			earthquakeId = Integer.parseInt(request.getParameter("earthquake_id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		int rowCount = 0;

		Connection conn = null;

		Boolean canConnectDB = false;

		try {
			Class.forName(JDBC_DRIVER);

			conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);

			canConnectDB = true;

			PreparedStatement pre = conn.prepareStatement(SELECT_EARTHQUAKE_SIMPLE);
			pre.setInt(1, earthquakeId);

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

		request.setAttribute("canConnectDB", canConnectDB);
		request.setAttribute("result_row_count", rowCount);

		getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);

	}

}
