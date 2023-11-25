<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--  Replace JSP scriplet code to JSTL
< % @ page import="java.util.*, com.jamersc.web.jdbc.*" % >
 -->

 <!-- After I add the JSTL Jar files use the JSTL tags-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<!-- CSS -->
<link type="text/css" rel="stylesheet" href="css/list-employee.css"></link>
<!-- Font Aweseome API -->
<script src="https://kit.fontawesome.com/5f04b1dac9.js"></script>
<!-- JSP scriplet code 
< %
	//get the employees from the request object (sent by servlet)
	List<Employee> theEmployees =
					(List<Employee>) request.getAttribute("EMPLOYEE_LIST"); //DOWN CAST 
% >
 -->
<body>

	<div id="wrapper">
		<div id="header">
			<h2>JSP List of Employee</h2>
		</div>
	</div>
	
	<div id="container">
		<div id="content">
			
			<!-- Add new button -->
			<input type="button" value="Add Student"
				onclick="window.location.href='add-employee-form.jsp'; return false;"
				class="add-student-button"
			/>
			
			<table>
				<tr>
					<th>ID No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Email</th>
					<th>Designation</th>
					<th>Salary</th>
					<th>Employment Date</th>
					<th>Update</th>
					<th>Delete</th>
				</tr>
					<!-- remove JSP scriplet
					< % for (Employee tempEmployees : theEmployees) { % > -->
				 	<c:forEach var="tempEmployee" items="${EMPLOYEE_LIST}">
				 	
				 	<!--  Set up a link for each employee & go to update form -->
				 	<c:url var="tempLink" value="EmployeeControllerServlet">
				 		<c:param name="command" value="LOAD" />
				 		<c:param name="employeeId" value="${tempEmployee.id}" />
				 	</c:url>
				 	
				 	<!--  Set up a link to delete employee -->
				 	<c:url var="deleteLink" value="EmployeeControllerServlet">
				 		<c:param name="command" value="DELETE" />
				 		<c:param name="employeeId" value="${tempEmployee.id}" />
				 	</c:url>
				<tr>
					<!-- remove JSP scriplet
					<td>< %= tempEmployees.getId() %></td>
					<td>< %= tempEmployees.getFirstName() %></td>
					<td>< %= tempEmployees.getLastName() %></td>	
					<td>< %= tempEmployees.getAge() %></td>	
					<td>< %= tempEmployees.getEmail() %></td>	
					<td>< %= tempEmployees.getDesignation() %></td>	
					<td>< %= tempEmployees.getSalary() %></td>	
					<td>< %= tempEmployees.getEmploymentDate() %></td>					
					 -->
					 
					 <td>${tempEmployee.id}</td>
					 <td>${tempEmployee.firstName}</td>
					 <td>${tempEmployee.lastName}</td>
					 <td>${tempEmployee.age}</td>
					 <td>${tempEmployee.email}</td>
					 <td>${tempEmployee.designation}</td>
					 <td>${tempEmployee.salary}</td>
					 <td>${tempEmployee.employmentDate}</td>
					 <td><a href="${tempLink}"><i class='fas fa-edit' style='font-size:24px; color:green'></i></a></td>
					 <td><a href="${deleteLink}"
						 	onclick="confirm('Are you sure you want to delete?') ? deleteFunction() : void(0);">
						 	<i class='fas fa-trash-alt' style='font-size:24px; color:red'></i>
					 	</a>
					 </td>
				</tr>		
				 	</c:forEach>
				<!-- remove JSP scriplet 
				 < % } %>
				 -->
			</table>
		</div>
	</div>

</body>
</html>