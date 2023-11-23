package com.jamersc.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Define datasource/connection pool for resource injection
	@Resource(name="jdbc/my_employee_db")
	private DataSource dataSource;
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Step 1: Setting up the print writer
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		//Step 2: Getting the connection
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			
			//Step 3: Create Sequel statement
			String sql = "SELECT * FROM employee";
			
			myStmt = myConn.createStatement();
			
			//Step 4: Execute sql statement
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				String email = myRs.getString("email");
				out.println(email);
			}
		}

		//Step 5: Process result set
		
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	

}
