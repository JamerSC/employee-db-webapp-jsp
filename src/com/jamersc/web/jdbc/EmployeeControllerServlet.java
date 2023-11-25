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
				addEmployee(request, response);
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
		List<Employee> employees = employeeDbUtil.getEmpoyees(); //OBJECT
		
		//add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees); //Set Name
		
		//send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employee.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
