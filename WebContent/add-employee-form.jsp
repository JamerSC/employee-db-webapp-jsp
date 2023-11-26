<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Employee Form</title>
<!--  CSS External -->
<link type="text/css" rel="stylesheet" href="css/add-employee-form.css"></link>
<!--  JS External -->
<script type="text/javascript" src="js/form-validation.js"></script>
</head>
<body>

    <header>
        <h1>Create New Employee</h1>
    </header>

    <div class="form-container">
        <h2>New Employee</h2>

        <form action="EmployeeControllerServlet" method="GET"
        	name="employeeForm" onSubmit="return validateForm()">
        
        	<!-- EmployeeControllerServlet will receive the hidden input -->
        	<input type="hidden" name="command" value="ADD"/>
        	
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName">

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName">

            <label for="age">Age</label>
            <input type="number" id="age" name="age">

            <label for="email">Email</label>
            <input type="email" id="email" name="email">

            <label for="designation">Designation</label>
            <input type="text" id="designation" name="designation">

            <label for="salary">Salary</label>
            <input type="number" id="salary" name="salary">

            <label for="employmentDate">Employment Date</label>
            <input type="date" id="employmentDate" name="employmentDate">

            <input type="submit" value="Save"/>
        </form>
        <div style="clear: both;"></div>
        <p>
        	<a href="EmployeeControllerServlet">Back to List of Employee</a>
        </p>
    </div>

</body>
</html>