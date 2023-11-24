<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.jamersc.web.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<link type="text/css" rel="stylesheet" href="css/style.css"></link>
</head>
<%
	//get the employees from the request object (sent by servlet)
	List<Employee> theEmployees =
					(List<Employee>) request.getAttribute("EMPLOYEE_LIST"); //DOWN CAST 
%>
<body>

	<div id="wrapper">
		<div id="header">
			<h2>JSP Employees</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>Employee No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Email</th>
					<th>Designation</th>
					<th>Salary</th>
					<th>Employment Date</th>
				</tr>
				<% for (Employee tempEmployees : theEmployees) { %>
				<tr>
					<td><%= tempEmployees.getId() %></td>
					<td><%= tempEmployees.getFirstName() %></td>
					<td><%= tempEmployees.getLastName() %></td>	
					<td><%= tempEmployees.getAge() %></td>	
					<td><%= tempEmployees.getEmail() %></td>	
					<td><%= tempEmployees.getDesignation() %></td>	
					<td><%= tempEmployees.getSalary() %></td>	
					<td><%= tempEmployees.getEmploymentDate() %></td>						
				</tr>		
				<% } %>
			</table>
		</div>
	</div>

</body>
</html>