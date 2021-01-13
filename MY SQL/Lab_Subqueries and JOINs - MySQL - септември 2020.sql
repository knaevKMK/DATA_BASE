USE soft_uni;
#EX_1

SELECT employee_id, 
CONCAT(e.first_name+' '+ e.last_name) AS full_name,
e.department_id,
d.`name` AS department_name
FROM departments AS d
JOIN employees AS e
ON d.manager_id=e.employee_id
ORDER BY employee_id
LIMIT 5 ;

#EX_2
SELECT town_id, t.`name` AS town_id, address_text
FROM towns AS t
WHERE t.`name`='San Francisco' OR t.`name`= 'Sofia' OR t.`name`='Carnation')
JOIN addresse AS a
ON t.town_id=a.town_id
ORDER BY town_id, address_id;

#EX_3
SELECT employee_id, first_name, last_name, department_id, salary
FROM employees
WHERE manger_id IS NULL;

#EX_4 
SELECT COUNT (e.employee_id ) AS count
FROM employee AS e
WHERE salary >
(SELECT AVG (salaray) 
FROM employeey
);
