USE soft_uni;

#EX_1
CREATE FUNCTION ufn_count_employees_by_town(town_name VARCHAR(20)) 
RETURNS INT
DETERMINISTIC
RETURN 
	(SELECT COUNT(employee_id) FROM employees AS e
		JOIN addresses AS a ON a.address_id=e.address_id
        JOIN towns AS t ON a.town_id=t.town_id
        WHERE t.`name`=town_name);
   

SELECT ufn_count_employees_by_town('Sofia') AS `count`;
SELECT ufn_count_employees_by_town('Berlin') AS `count`;
SELECT ufn_count_employees_by_town(NULL) AS `count`;

DROP FUNCTION ufn_count_employees_by_town;

#EX_2
DELIMITER $$
CREATE PROCEDURE usp_raise_salaries(department_name VARCHAR(50))
BEGIN
	UPDATE employees AS e
    JOIN departments AS d ON e.department_id=d.department_id
    SET salary =salary * 1.05  
    WHERE d.`name`= department_name;
END $$
DELIMITER ;

#view
DELIMITER $$
CREATE PROCEDURE usp_get_salaries(department_name VARCHAR(50))
BEGIN
    SELECT first_name, salary
    FROM employees AS e
    JOIN departments AS d ON d.department_id=e.department_id
    WHERE d.`name`= department_name
    ORDER BY first_name;
END$$
DELIMITER ;

SET SQL_SAFE_UPDATES=0;
CALL usp_raise_salaries('Finance');
SET SQL_SAFE_UPDATES=1;

CALL usp_get_salaries('Finance');

DROP PROCEDURE usp_get_salaries;
DROP PROCEDURE usp_raise_salaries;

#EX_3
DELIMITER $$
CREATE PROCEDURE usp_raise_salary_by_id(id INT)
	BEGIN
		UPDATE employees AS e
        SET salary = salary*1.05 
        WHERE e.employee_id=id;
	END $$
 DELIMITER ;
 
 DELIMITER $$
 CREATE PROCEDURE usp_get_salary_by_id(id INT)
	BEGIN
		SELECT salary
        FROM employees AS e
        WHERE e.employee_id=id;
    END $$
 DELIMITER ;
 
 CALL usp_get_salary_by_id (178);
 CALL usp_raise_salary_by_id(178);
 
 DROP PROCEDURE usp_get_salary_by_id;
 DROP PROCEDURE usp_raise_salary_by_id;
 
 #EX_4
 CREATE TABLE deleted_employees(
 employee_id INT PRIMARY KEY AUTO_INCREMENT,
 first_name VARCHAR (50) NOT NULL,
 last_name VARCHAR (50) ,
 middle_name VARCHAR (50),
 job_title VARCHAR(50),
 department_id INT NOT NULL,
 salary DECIMAL (11,2) 
 );
 
DELIMITER $$
 CREATE TRIGGER tr_deleted_employees
 AFTER DELETE ON employees
 FOR EACH ROW
 BEGIN
	INSERT INTO deleted_employees 
    (first_name, last_name, middle_name, job_title, department_id, salary)
    VALUES (OLD.first_name, OLD.last_name, OLD.middle_name,
    OLD.job_title, OLD.department_id, OLD.salary);
 END $$
DELIMITER ;
