package com.jamersc.web.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EmployeeDbUtil {

	private DataSource dataSource;
	
	EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmpoyees() throws Exception {
		
		//List of Employees
		List<Employee> employees = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create a sql statement
			String sql = "SELECT * FROM employee ORDER BY last_name";
			
			myStmt = myConn.createStatement();
			
			//execute query
			myRs = myStmt.executeQuery(sql);
			
			//process result set & retrieve data 
			while(myRs.next()) {
				//retrieve data from the result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int age = myRs.getInt("age");
				String email = myRs.getString("email");
				String designation = myRs.getString("designation");
				double salary = myRs.getDouble("salary");
				Date employmentDate = myRs.getDate("employment_date");

				//create a new employee object
				Employee tempEmployee = new Employee(id, firstName, lastName, age, email, designation, salary, employmentDate);
				
				//add it to the list of employees
				employees.add(tempEmployee);
			}
			
			//return all the employees queried
			return employees;
			
		} finally {
			//choose jdbc objects
			close(myConn, myStmt, myRs);
		}
		

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			
			if(myConn != null) {
				myConn.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myRs != null) {
				myRs.close();
			}
			
		} catch (Exception exc) {
			// TODO: handle exception
			exc.printStackTrace();
		}
	}
	
}
