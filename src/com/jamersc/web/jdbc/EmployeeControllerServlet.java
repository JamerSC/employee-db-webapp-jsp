package com.jamersc.web.jdbc;

import java.io.IOException;
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
			//list employees MVC
			listEmployees(request, response);
		} catch (Exception exc) {
			// TODO Auto-generated catch block
			//exc.printStackTrace();
			throw new ServletException(exc);
		}
		
	}


	private void listEmployees(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//get the employees from db util
		List<Employee> employees = employeeDbUtil.getEmpoyees(); //OBJECT
		
		//add employees to the request
		request.setAttribute("EMPLOYEE_LIST", employees); //Set Name
		
		//send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-employees.jsp");
		
		dispatcher.forward(request, response);
		
	}

}
