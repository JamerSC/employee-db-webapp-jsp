/**
 * 
 */

 function validateForm() {
	
	// All error will push to the arrays of error.
	var theErrorFields = [];
	
	//Employee form -> name="employeeForm"
	var employeeForm = document.forms["employeeForm"];
	
	//First name
	var firstName = employeeForm["firstName"].value.trim();;
	if (firstName == "") {
		theErrorFields.push("First name");
	}
	//Last name
	var lastName = employeeForm["lastName"].value.trim();;
	if (lastName == "") {
		theErrorFields.push("Last name");
	}
	//Age
	var age = employeeForm["age"].value.trim();;
	if (age == "") {
		theErrorFields.push("age");
	}
	//Email
	var email = employeeForm["email"].value.trim();;
	if (email == "") {
		theErrorFields.push("Email");
	}
	//Designation
	var designation = employeeForm["designation"].value.trim();;
	if (designation == "") {
		theErrorFields.push("Designation");
	}
	//Salary
	var salary = employeeForm["salary"].value.trim();;
	if (salary == "") {
		theErrorFields.push("Salary");
	}
	//Employment
	var employmentDate = employeeForm["employmentDate"].value.trim();;
	if (employmentDate == "") {
		theErrorFields.push("Employment date");
	}
	
	
	if (theErrorFields.length > 0) {
		alert("Form validation failed. Please add data for following fields: " + theErrorFields);
		return false;
	}
}