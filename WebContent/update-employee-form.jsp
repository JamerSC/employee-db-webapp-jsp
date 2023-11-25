<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employee Form</title>
<link type="text/css" rel="stylesheet" href="css/add-employee-form.css"></link>


</head>
<body>

    <header>
        <h1>Update Employee</h1>
    </header>

    <div class="form-container">
        <h2>Update Employee</h2>

        <form action="EmployeeControllerServlet" method="GET">
        
        	<!-- EmployeeControllerServlet will receive the hidden input -->
        	<input type="hidden" name="command" value="UPDATE"/>
        	<!--  -->
        	<input type="hidden" name="employeeId" value="${THE_EMPLOYEE.id}" />
        	
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" value="${THE_EMPLOYEE.firstName}" required>

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" value="${THE_EMPLOYEE.lastName}" required>

            <label for="age">Age</label>
            <input type="number" id="age" name="age" value="${THE_EMPLOYEE.age}" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="${THE_EMPLOYEE.email}" required>

            <label for="designation">Designation</label>
            <input type="text" id="designation" name="designation" value="${THE_EMPLOYEE.designation}" required>

            <label for="salary">Salary</label>
            <input type="number" id="salary" name="salary" value="${THE_EMPLOYEE.salary}" required>

            <label for="employmentDate">Employment Date</label>
            <input type="date" id="employmentDate" name="employmentDate" value="${THE_EMPLOYEE.employmentDate}" required>

            <input type="submit" value="Update"/>
        </form>
        <div style="clear: both;"></div>
        <p>
        	<a href="EmployeeControllerServlet">Back to List of Employee</a>
        </p>
    </div>

</body>
</html>