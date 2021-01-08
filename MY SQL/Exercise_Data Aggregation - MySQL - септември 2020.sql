#EX_1
SELECT COUNT(*) AS `count`
FROM gringotts.wizzard_depostits;

#EX_2
SELECT MAX (longest_magic_wand) AS longest_magic_wand
FROM wizzard_depostits;

#EX_3
SELECT deposit_group,
 MAX(longest_magic_wand) AS `lmw`
 GROUP BY deposit_group
 ORDER BY `lms`, deposit_group;
 
 #EX_4
 SELECT wz.deposit_group
 FROM wizzard_depostits AS wz
 GROUP BY wz.deposit_group
 ORDER BY AVG(magic_wand_size)
 LIMIT 1;
 
 #EX_5
SELECT `deposit_group`, SUM (`deposit_amount`) AS `total_sum`
FROM wizzard_deposits
GROUP BY `deposit_group`
ORDER BY `total_sum`;

#EX_6
SELECT `deposit_group`, SUM(deposti_amount) AS total_sum
FROM wizzard_deposits
WHERE magic_wand_creator = 'Ollivander Family'
GROUP BY deposit_group
ORDER BY deposti_group;

#EX_7
SELECT deposti_group, SUM (deposit_ampount) AS total_sum
FROM wizzard_depostits
WHERE magic_wand_creator = 'Ollivander Family'
GROUP BY deposit_group
HAVING total_sum < 150000
ORDER BY total_sum DESC;

#EX_8
SELECT deposit_group, magic_wand_creator, 
MIN (deposti_charge) AS `min_deposit_charge`
FROM wizzard_deposits
GROUP BY deposit_group, magic_wand_creator
ORDER BY magic_wand_creator, deopsit_group;

#EX_9
SELECT 
( CASE 
 WHEN age BETWEEN 0 AND 10 THEN `[0-10]`
 WHEN age BETWEEN 11 AND 20 THEN `[11-20]`
 WHEN age BETWEEN 21 AND 30 THEN `[21-30]`
 WHEN age BETWEEN 31 AND 40 THEN `[31-40]`
 WHEN age BETWEEN 41 AND 50 THEN `[41-50]`
 WHEN age BETWEEN 51 AND 60 THEN `[51-60]`
 ELSE `[61+]`
 END
) AS age_group, 
COUNT(*) AS wizzard_count
FROM wizzard_deposit
ORDER BY age_group;

#EX_10
SELECT LEFT (first_name, 1) AS first_letter
FROM wizzard_deposit
WHERE  deposit_type = 'Troll Chest'
GROUP BY first_letter
ORDER BY first_letter;

#EX_11
SELECT deposit_group, is_deposit_expired,
 AVG (deposit_interes) AS average_interest
FROM wizzard_deposit
WHERE deposit_start_date > '1985-01-01'
GROUP BY deposit_group, is_deposit_expired
ORDER BY deposit_group DESC, is_deposit_expired;

#EX_13
SELECT e.department_id, ROUND(MIN(e.salary),2) AS minimum_salary
FROM soft_uni.employees AS e
WHERE e.department_id IN (2,5,7) AND hire_date > '2000-01-01'
GROUP BY department_id
ORDER BY department_id;

#EX_14
CREATE TABLE `temp`
SELECT department_id, AVG( salary) AS avg_salary
FROM employees
WHERE salary >30000
GROUP BY department_id;
 
DELETE FROM `temp`
WHERE manager_id=42;

UPDATE `temp`
SET salary = salary +5000
WHERE department_id=1;

#EX15
 SELECT department_id,
 MAX(salary) AS max_salary
 FROM employess
 GROUP BY department_id
HAVING max_salary NOT BETWEEN 30000 AND 70000
ORDER BY department_id;

#EX_16
SELECT COUNT(manager_id) AS ``
FROM employees
WHERE manager_id IS NULL; 


#EX_17
SELECT  e.department_id, 
(SELECT DISTINCT e2.salary FROM  employees AS e2 
WHERE e2.department=e.department_id
ORDER  BY salary DESC
LIMIT 2,1 ) AS third_avg_salary
FROM employees e
WHERE third_avg_salary IS NOT NULL
GROUP BY e.department_id
HAVING third_avg_salary IS NOT NULL
ORDER BY e.department_id;

#EX_18
SELECT first_name, last_name, department_id
FROM employee AS e
WHERE salary > (SELECT e2.department_id, AVG(e2.salary) AS `avg`
FROM employees AS e2
WHERE e2.department_id= e.department_id
GROUP BY e2.department_id)
ORDER BY department_id, employee_id
LIMIT 10;

#EX_19
SELECT department_id, ROUND(SUM(salary),2) AS total_salary
FROM employees
GROUP BY department_id
ORDER BY department_id;