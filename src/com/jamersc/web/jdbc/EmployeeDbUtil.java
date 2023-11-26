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

	private static DataSource dataSource;
	
	EmployeeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Employee> getEmpoyees() throws SQLException {
		
		//List of Employees
		List<Employee> employees = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			//get a connection
			myConn = dataSource.getConnection();
			
			//create a sql statement
			//String sql = "SELECT * FROM employee ORDER BY last_name";
			String sql = "SELECT * FROM employee ORDER BY id ASC";
			
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

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
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
			
			//set up to avoid sql injection
			myStmt = myConn.prepareStatement("INSERT INTO employee "
					+ "(first_name, last_name, age, email, designation, salary, employment_date)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			
			// set the parameter values for the employee
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

	public static Employee getEmployee(String theEmployeeId) throws Exception  {
		
		Employee theEmployee = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int employeeId;
		
		try {
			
			//convert employee id into int
			employeeId = Integer.parseInt(theEmployeeId);
			
			//get a connection
			myConn = dataSource.getConnection();
					
			//create a sql query get selected employee
			String sql = "SELECT * FROM employee WHERE id = ?";
			
			//created prepared statement
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, employeeId);
			
			//execute statemet
			myRs = myStmt.executeQuery();
			
			//retrieve data from result set row
			if (myRs.next()) {
				
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int age = myRs.getInt("age");
				String email = myRs.getString("email");
				String designation = myRs.getString("designation");
				double salary = myRs.getDouble("salary");
				Date employmentDate = myRs.getDate("employment_date");
				
				//construct an object
				theEmployee = new Employee(employeeId, firstName, lastName, age, email, designation, salary, employmentDate);
			
			} else {
				throw new Exception("Could not find employee" + employeeId);
			}
				
			
			return theEmployee;
			
		} finally {
			
			close(myConn, myStmt, myRs);
		}

	}

	public static void updateEmployee(Employee theEmployee) throws Exception{
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			//connection to db
			myConn = dataSource.getConnection();
			
			//create sql update statement
			String sql = "UPDATE employee "
					+ "SET first_name=?, last_name=?, age=?, email=?, "
					+ "designation=?, salary=?, employment_date=? "
					+ "WHERE id=?";
			
			//execute statement
			myStmt = myConn.prepareStatement(sql);
			
			// set the parameter values for the student
			myStmt.setString(1, theEmployee.getFirstName());
			myStmt.setString(2, theEmployee.getLastName());
			myStmt.setInt(3, theEmployee.getAge());
			myStmt.setString(4, theEmployee.getEmail());
			myStmt.setString(5, theEmployee.getDesignation());
			myStmt.setDouble(6, theEmployee.getSalary());
			java.util.Date utilDate = theEmployee.getEmploymentDate();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			myStmt.setDate(7, sqlDate);
			myStmt.setInt(8, theEmployee.getId());
			
			// execute sql update
			myStmt.execute();
			
		} finally {
			//clean up JDBC object
			close(myConn, myStmt, null);
		}
		
	}

	public static void deleteEmployee(String theEmployeeId) throws Exception {
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		int employeeId;
		
		try {
			// convert employee id
			employeeId = Integer.parseInt(theEmployeeId);
			
			//get connection
			myConn = dataSource.getConnection();
			
			//Create sql delete statement
			String sql = "DELETE FROM employee WHERE id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			//set params
			myStmt.setInt(1, employeeId);
			
			//execute query
			myStmt.execute();
			
			
		} finally {
			//clean up JDBC code
			close(myConn, myStmt, null);
		}
		
	}

	public static List<Employee> searchEmployees(String searchEmployeeName) throws Exception {
		
		List<Employee> employees = new ArrayList<>();
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		//int employeeId;
		
		try {
			//get connection
			myConn = dataSource.getConnection();
			
			//if the search name is not empty
			if (searchEmployeeName != null && searchEmployeeName.trim().length() > 0) {
				
				//create sql statement to search name
				String sql = "SELECT * FROM employee WHERE "
						+ "LOWER(first_name) LIKE ? OR LOWER(last_name) LIKE ?";
				
				//prepared statement
				myStmt = myConn.prepareStatement(sql);
				
				//set parameters
				String searchEmployeeNameLike = "%" + searchEmployeeName.toLowerCase() + "%";
				
				myStmt.setString(1, searchEmployeeNameLike);
				myStmt.setString(2, searchEmployeeNameLike);
			} else {
				
				// create sql to get all students
                String sql = "SELECT * FROM employee ORDER BY last_name";
                //String sql = "SELECT * FROM employee";
                
                myStmt = myConn.prepareStatement(sql);
			}	
			
			//execute query
			myRs = myStmt.executeQuery();
			
			while(myRs.next()) {
			
				//retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				int age = myRs.getInt("age");
				String email = myRs.getString("email");
				String designation = myRs.getString("designation");
				double salary = myRs.getDouble("salary");
				Date employmentDate = myRs.getDate("employment_date");
				
				Employee tempEmployee = new Employee(id, firstName, lastName, age, email, designation, salary, employmentDate);
				
				employees.add(tempEmployee); 
			}
			
			return employees;
			
		} finally {
			//close JDBC Connection
			close(myConn, myStmt, myRs);
		}
		
		

	}

	
}
