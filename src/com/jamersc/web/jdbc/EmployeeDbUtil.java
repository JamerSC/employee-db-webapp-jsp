package com.jamersc.web.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public void addEmployee(Employee theEmployee) throws SQLException {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			// db connection
			myConn = dataSource.getConnection();
			
			// create sql for insert
			//String sql = "INSERT INTO employee "
			//		+ "(first_name, last_name, age, email, designation, salary, employment_date)"
			//		+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			myStmt = myConn.prepareStatement("INSERT INTO employee "
					+ "(first_name, last_name, age, email, designation, salary, employment_date)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			// set the parameter values for the student
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setInt(3, theEmployee.getAge());
			myStmt.setString(4, theEmployee.getEmail());
			myStmt.setString(5, theEmployee.getDesignation());
			myStmt.setDouble(6, theEmployee.getSalary());
		
			//myStmt.setDate(7, theEmployee.getEmploymentDate()); //error!
			
	        // Convert java.util.Date to java.sql.Date
	        java.util.Date utilDate = theEmployee.getEmploymentDate();
	        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	        myStmt.setDate(7, sqlDate);
	        
			// execute sql insert
	        myStmt.execute();
		
		} finally {
			// clean up JDBC Objects
			close(myConn, myStmt, null);
		}
	}

	
}
