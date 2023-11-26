package com.jamersc.web.jdbc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class EmployeeControllerServlet
 */
@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EmployeeDbUtil employeeDbUtil; //data member
	
	@Resource(name="jdbc/my_employee_db")
	private DataSource dataSource; //connection pool
	
	//Call by java EE Tomcat server
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		
		//create employee db util and pass data & connection pool
		try {
			employeeDbUtil = new EmployeeDbUtil(dataSource);
		} catch (Exception exc) {
			throw new ServletException(exc);
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//read the "command" parameter
			String theCommand = request.getParameter("command"); 
			
			//if the command is missing
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			switch (theCommand) {
			case "LIST":
				//list employees MVC list-employee.jsp
				listEmployees(request, response);
				break;
			case "ADD":
				//add new employee
				addEmployee(request, response);
				break;
			case "LOAD":
				//fetch selected employee
				loadEmployee(request, response);
				break;
			case "UPDATE":
				//update employee
				updateEmployee(request, response);
				break;
			case "DELETE":
				//Delete selected employe
				deleteEmployee(request, response);
				break;
			case "SEARCH":
				searchEmployees(request, response);
				break;
			default:
				//list employees MVC list-employee.jsp
				listEmployees(request, response);
				
			}
			
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			//exc.printStackTrace();
			throw new ServletException(exc);
		}
		
	}


	private void searchEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read search name from form data
		String searchEmployeeName = request.getParameter("searchEmployeeName");
		
		//search employee in employee db util
		List<Employee> theEmployees = EmployeeDbUtil.searchEmployees(searchEmployeeName);
		
		request.setAttribute("EMPLOYEE_LIST", theEmployees);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		
		dispatcher.forward(request, response);
		
	}


	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String theEmployeeId = request.getParameter("employeeId");
	
		//perform update on the database
		EmployeeDbUtil.deleteEmployee(theEmployeeId);
		
		//send back to the list-employee.jsp
		listEmployees(request, response);
	}


	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read student info from data form
		int id = Integer.parseInt(request.getParameter("employeeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String designation = request.getParameter("designation");
		double salary = Double.parseDouble(request.getParameter("salary"));
		//convert string into date format
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date employmentDate = dateFormat.parse(request.getParameter("employmentDate"));
		
		//create a new student object
		Employee theEmployee = new Employee(id, firstName, lastName, age, email, designation, salary, employmentDate);
		
		//perform update on the database
		EmployeeDbUtil.updateEmployee(theEmployee);
		
		//send back to the list-employee.jsp
		listEmployees(request, response);
		
	}


	private void loadEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//read the employee id from form data
		String theEmployeeId = request.getParameter("employeeId");
		
		//get employee from database (db util)
		Employee theEmployee  = EmployeeDbUtil.getEmployee(theEmployeeId);
		
		//place student in request attribute
		request.setAttribute("THE_EMPLOYEE", theEmployee);
		
		//send to jsp page: update-employee-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-employee-form.jsp");
		
		dispatcher.forward(request, response);
	}


	private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// read the employee from the form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		// Convert String parameters to their respective types
		int age = Integer.parseInt(request.getParameter("age")); //Parse Int
		String email = request.getParameter("email");
		String designation = request.getParameter("designation");
		double salary = Double.parseDouble(request.getParameter("salary")); //Parse Double
		
		//Date employmentDate = request.getParameter("employmentDate"); error!
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date employmentDate;
		try {
			employmentDate = dateFormat.parse(request.getParameter("employmentDate"));
		} catch (Exception exc) {
			exc.printStackTrace(); // Handle the parse exception appropriately
			return;  // Return or handle the error accordingly
		}

		// create a new employee object
		Employee theEmployee = new Employee(firstName, lastName, age, email, designation, salary, employmentDate);
		
		// add the employee to the database
		employeeDbUtil.addEmployee(theEmployee);
		
		// send back to the main page (view employee list)
		listEmployees(request, response); //method
	}


	
	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//get the employees from db util
		List<Employee> theEmployees = employeeDbUtil.getEmpoyees(); //OBJECT
		
		//add employees to the request
		request.setAttribute("EMPLOYEE_LIST", theEmployees); //Set Name
		
		//send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
