#EX_1
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS full_name,
    e.department_id,
    (d.`name`) AS department_name
FROM
    departments AS d
        JOIN
    employees AS e 
ON d.manager_id=e.employee_id
ORDER BY e.employee_id
LIMIT 5;
    

#EX_2
SELECT t.town_id , (t.`name`)AS town_name, a.address_text
FROM towns AS t
JOIN addresses AS a
ON a.town_id = t.town_id
WHERE t.name IN ('Sofia', 'San Francisco', 'Carnation')
ORDER BY town_id, address_id;

#EX_3

SELECT 
    e.employee_id,
    e.first_name,
    e.last_name,
    e.department_id,
    e.salary
FROM
    employees AS e
WHERE
    manager_id IS NULL;
    
    
#EX_4
SELECT COUNT(*) AS count FROM employees AS e
WHERE salary > (
SELECT AVG(salary) FROM employees);

SELECT * FROm employees;
SELECT * FROm departments;