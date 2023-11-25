<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Employee Form</title>
<link type="text/css" rel="stylesheet" href="css/add-employee-form.css"></link>


</head>
<body>

    <header>
        <h1>Create New Employee</h1>
    </header>

    <div class="form-container">
        <h2>New Employee</h2>

        <form action="EmployeeControllerServlet" method="GET">
        
        	<!-- EmployeeControllerServlet will receive the hidden input -->
        	<input type="hidden" name="command" value="ADD"/>
        	
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="lastName" required>

            <label for="age">Age</label>
            <input type="number" id="age" name="age" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" required>

            <label for="designation">Designation</label>
            <input type="text" id="designation" name="designation" required>

            <label for="salary">Salary</label>
            <input type="number" id="salary" name="salary" required>

            <label for="employmentDate">Employment Date</label>
            <input type="date" id="employmentDate" name="employmentDate" required>

            <input type="submit" value="Save"/>
        </form>
        <div style="clear: both;"></div>
        <p>
        	<a href="EmployeeControllerServlet">Back to List of Employee</a>
        </p>
    </div>

</body>
</html>