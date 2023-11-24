package com.jamersc.web.jdbc;

import java.sql.Date;

public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private String email;
	private String designation;
	private double salary;
	private Date employmentDate;
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param email
	 * @param designation
	 * @param salary
	 * @param employmentDate2
	 */
	public Employee(int id, String firstName, String lastName, int age, String email, String designation, double salary, Date employmentDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.designation = designation;
		this.salary = salary;
		this.employmentDate = employmentDate;
	}
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param age
	 * @param email
	 * @param designation
	 * @param salary
	 * @param employmentDate
	 */
	public Employee(String firstName, String lastName, int age, String email, String designation, double salary,
			Date employmentDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.email = email;
		this.designation = designation;
		this.salary = salary;
		this.employmentDate = employmentDate;
	}



	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	//email, designation, salary, emp date
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
		
	public Date getEmploymentDate() {
		return employmentDate;
	}
	
	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}
	
	@Override
	public String toString() {
		return "Employee [Firstname="+ firstName +", Lastname= "+ lastName +", "
				+ "Age= " + age + ", Email= "+ email +", Designation= "+ designation +","
				+ " Salary= "+ salary +", Employment date= " + employmentDate + "]";
	}
	
	/*
	public static void main(String [] args) {
        LocalDate employmentDate = LocalDate.of(2023, 11, 24); // Example date
        Employee employee = new Employee("John", "Doe", 30, "john.doe@example.com", "Developer", 50000.0, employmentDate);

        // Accessing the employment date
        //LocalDate retrievedEmploymentDate = employee.getEmploymentDate();
        //System.out.println("Employment Date: " + retrievedEmploymentDate);
        
        System.out.println(employee);
	}
	*/
}
