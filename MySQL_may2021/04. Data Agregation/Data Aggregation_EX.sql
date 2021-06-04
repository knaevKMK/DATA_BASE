#EX_1
SELECT COUNT(*) AS count FROM wizzard_deposits;

#EX_2
SELECT MAX(magic_wand_size) AS longest_magic_wand FROM wizzard_deposits;

#EX_3
SELECT deposit_group, MAX(magic_wand_size) AS longest_magic_wand FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY longest_magic_wand, deposit_group;

#EX_4
SELECT deposit_group FROM wizzard_deposits
GROUP BY deposit_group
ORDER BY avg(magic_wand_size)
LIMIT 1;

#EX_5
SELECT deposit_group, SUM(deposit_amount) AS total_sum FROM wizzard_deposits
GROUP BY deposit_group 
ORDER BY total_sum;

#EX_6
SELECT deposit_group, SUM(deposit_amount) AS total_sum FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family'
GROUP BY deposit_group 
ORDER BY deposit_group;

#EX_7
SELECT deposit_group, SUM(deposit_amount) AS total_sum FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander family' 
GROUP BY deposit_group 
HAVING total_sum < 150000
ORDER BY total_sum DESC;

#EX_8
SELECT deposit_group, magic_wand_creator, MIN(deposit_charge) AS min_deposit_charge FROM wizzard_deposits
GROUP BY deposit_group , magic_wand_creator
ORDER BY magic_wand_creator, deposit_group;

#EX_9
SELECT (
CASE
WHEN age <= 10 THEN '[0-10]'
WHEN age BETWEEN 11 AND 20 THEN '[11-20]'
WHEN age BETWEEN 21 AND 30 THEN '[21-30]'
WHEN age BETWEEN 31 AND 40 THEN '[31-40]'
WHEN age BETWEEN 41 AND 50 THEN '[41-50]'
WHEN age BETWEEN 51 AND 60 THEN '[51-60]'
ELSE '[61+]'
END) AS age_group,
 COUNT(*) AS wizard_count FROM wizzard_deposits
 GROUP BY age_group 
 ORDER BY age_group;
 
 #EX_10
 SELECT SUBSTRING(first_name,1,1) AS first_letter FROM wizzard_deposits
 WHERE deposit_group = 'Troll Chest'
 GROUP BY first_letter
 ORDER BY first_letter ;
 
 #EX_11
 SELECT deposit_group, is_deposit_expired,
 AVG( deposit_interest) AS average_interest
 FROM wizzard_deposits
 WHERE deposit_start_date >'1985-01-01'
 GROUP BY deposit_group, is_deposit_expired
 ORDER BY deposit_group DESC, is_deposit_expired;
 
 #EX_12
 SELECT department_id, MIN(salary) AS minimum_salary FROM employees
 WHERE department_id IN (2,5,7)
 GROUP BY department_id
 ORDER BY department_id;
 
 #EX_13
CREATE TABLE `temp`
SELECT department_id, salary, manager_id
FROM employees
WHERE salary >30000;
 
 SET SQL_SAFE_UPDATES = 0;
 
DELETE FROM `temp`
WHERE manager_id=42;

UPDATE `temp`
SET salary = salary +5000
WHERE department_id=1;

SELECT department_id, AVG(salary) AS avg_salary FROM temp
GROUP BY department_id
ORDER BY department_id ;

#EX_14
SELECT department_id, MAX(salary) AS max_salary FROM employees
GROUP BY department_id 
HAVING max_salary NOT BETWEEN 30000 AND 70000
ORDER BY department_id ;

#EX_15
SELECT COUNT(*) AS '' FROM employees
WHERE manager_id IS NULL;

#EX_16
SELECT e.department_id, (
	SELECT e2.salary FROM employees  e2
	WHERE e.department_id=e2.department_id
    GROUP BY e2.salary
	ORDER BY e2.salary DESC
	LIMIT 2,1
) AS third_highest_salary FROM employees  e
GROUP BY department_id
HAVING  third_highest_salary IS NOT NULL
ORDER BY department_id;


SELECT e2.salary FROM employees  e2
	WHERE 4=e2.department_id
    GROUP BY e2.salary
	ORDER BY e2.salary DESC
	LIMIT 2,1;
#EX_17
SELECT 
    first_name, last_name, department_id
FROM
    employees AS e
WHERE
    salary > (SELECT 
            AVG(e2.salary)
        FROM
            employees AS e2
        WHERE
            e.department_id = e2.department_id
        GROUP BY e2.department_id)
ORDER BY department_id, employee_id
LIMIT 10;


-- Roberto Tamburello 1
-- Terri Duffy 1
-- Rob Walters 2
-- Ovidiu Cracium 2
-- Stephen Jiang 3
-- Brian Welcker 3
-- Amy Alberts 3
-- Syed Abbas 3
-- Sariya Harnpadoungsataya 4
-- Mary Gibson 4

-- Roberto Tamburello 1
-- Terri Duffy 1
-- Rob Walters 2
-- Ovidiu Cracium 2
-- Brian Welcker 3
-- Stephen Jiang 3
-- Syed Abbas 3
-- Amy Alberts 3
-- Terry Eminhizer 4
-- Mary Gibson 4


#EX_18
SELECT department_id, SUM(salary) AS total_salary FROM employees
GROUP BY department_id
ORDER BY department_id;