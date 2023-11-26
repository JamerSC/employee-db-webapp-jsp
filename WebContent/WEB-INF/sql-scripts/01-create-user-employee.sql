-- Create User:
CREATE USER 'employee'@'localhost' IDENTIFIED BY 'employee';
			-- user id 								user password 

ALTER USER 'employee'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY 'employee';

-- allow users on privileges
GRANT ALL PRIVILEGES ON * . * TO 'employee'@'localhost';

DROP USER 'employee'@'localhost';

ALTER USER 'employee'@'localhost' IDENTIFIED BY 'new_password';

-- revoke all privileges
-- Revoke all privileges for the user
REVOKE ALL PRIVILEGES ON *.* FROM 'employee'@'localhost';

-- Flush privileges to apply the changes
FLUSH PRIVILEGES;