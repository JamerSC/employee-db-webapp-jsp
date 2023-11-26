CREATE DATABASE IF NOT EXISTS my_employee_db;

DROP DATABASE IF EXISTS my_employee_db;

USE my_employee_db;

SHOW TABLES;

DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
	id int NOT NULL AUTO_INCREMENT,
    first_name varchar(45) DEFAULT NULL,
    last_name varchar(45) DEFAULT NULL,
    age int DEFAULT NULL,
    email varchar(45) DEFAULT NULL,
    designation varchar(45) DEFAULT NULL,
    salary decimal(10,2) DEFAULT NULL,
    employment_date date DEFAULT NULL,
    created_by int DEFAULT NULL,
    created_date timestamp default current_timestamp,
    modified_by int DEFAULT NULL,
    modified_date timestamp default current_timestamp on update current_timestamp,
    PRIMARY KEY(id)
);

-- 'YYYY-MM-DD' format
INSERT INTO employee 
(first_name, last_name, age, email, designation, salary, employment_date, created_by)
VALUES ('John', 'Doe', 25, 'jdoe@gmail.com', 'HR', 20000, '2023-11-23', 1),
	   ('Mary', 'Public', 26, 'mpublic@gmail.com', 'Project Manager', 30000, '2023-11-23', 1),
       ('Bill', 'Nilly', 22, 'bnilly@gmail.com', 'Jr. Software Engineer', 25000, '2023-11-23', 1),
       ('Maxwell', 'Dixon', 30, 'dmaxwell@gmail.com', 'Sr. Software Engineer',35000, '2023-11-23', 1),
       ('Ajay', 'Rao', 27, 'arao@gmail.com', 'Document Specialist', 20000, '2023-11-23', 1);
       
SELECT * FROM employee;




