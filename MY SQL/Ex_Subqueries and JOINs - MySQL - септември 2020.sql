#EX_1
SELECT employee_id, job_title,address_id,address_text
FROM employees AS e
JOIN addresses AS a
ON e.address_id=a.address_id
ORDER BY address_id ASC
LIMIT 5;

#EX_2
SELECT first_name, last_name, t.town_name AS town, address_text
FROM employees AS e
JOIN addresses AS a
ON e.address_id=a.address_id
JOIN towns AS t
ON a.town_id= t.town_id
ORDER BY first_name ASC, last_name ASC
LIMIT 5;

#EX_3
SELECT employee_id, first_name, last_name, d.`name` AS department_name
FROM employees AS e
LEFT JOIN departments AS d
ON e.departmenr_id=d.department_id
WHERE d.`name` ='Sales'
ORDER BY employee_id DESC;

#EX_4
SELECT employee_id, first_name, salary, d.`name` AS departrment_name
FROM employees AS e
JOIN departments AS d
ON e.department_id=d.`name`
WHERE salary > 15000
ORDER BY department_id DESC
LIMIT 5;

#EX_5
SELECT employee_id, first_name
FROM employee_projects AS ep
JOIN employees AS e
ON ep.employee_id=e.employee_id
WHERE ep.project_id IS NULL
ORDER BY employee_id DESC
LIMIT 3;

#EX_6
SELECT first_name, last_name, hire_date, d.`name` ASdept_name
FROM employees AS e
JOIN deparments AS d 
ON e.department_id=d.department_id
WHERE hire_date >'199-01-01' AND department_name IN ('Sales', 'Finance')
ORDER BY hire_date ASC;

#EX_7
SELECT employee_id, first_name, p.`name` AS project_name
FROM employees AS e
JOIN employees_project AS ep
ON e.employee_id=ep.employee_id
JOIN projects AS p
ON p.project_id=ep.project_id
WHERE p.start_date > '2002-08-13'  AND p.end_date IS NULL
ORDER BY first_name, p.name ASC
LIMIT 5; 

#EX_8
SELECT empplyee_id, first_name, (CASE
WHEN (YEAR(p.start_date) < 2005) THEN p.`name`
ELSE 'NULL'
END) AS project_name

FROM employees AS e
JOIN project_employee AS pe
ON e.employee_id = pe.emplloyee_id 
JOIN projects AS p
ON ep.project_id=p.project_id
WHERE employee_id=24
ORDER BY project_name ASC;

#EX_9
SELECT e.emoloyee_id, e.first_name, e.manager_id, e2.fist_name AS magaer_name
FROM empployees AS e
JOIN employee AS e2
ON e.manager_id= e2.employee_id
WHERE e.manager_id IN (3,7)
ORDER BY e.first_name ASC;

#EX_10
SELECT emplyee_id,
CONCAT(first_name + ' ' +last_name) employee_name, 
(CASE 
WHEN e.employee_id IS NOT NULL THEN CONCAT(e2.first_name + ' ' + last_name)
END
) AS manager_name,
d.`name` AS department_name
FROM employees AS e
JOIN employees AS e2
ON e.manager_id=e2.employee_id
JOIN departments AS d
ON e.department_id=d.department_id
ORDER BY ee.empoyee_id
LIMIT 5;

#EX_11

SELECT MIN (avg_salaray) AS min_average_salary
FROM (
SELECT AVG (salary) AS avg_salaray
FROM employees
GROUP BY department_id
) AS min_average_salary;

#EX_12
SELECT country_code, mountais_range, peak_name, elevation
FROM countries AS c
JOIN mountaince_countries AS mc ON c. country_code= mc.country_code
JOIN mountains AS m ON m.mountain_id=m.id
JOIN peaks AS p ON p.mountain_id=m.id
WHERE c.countries_name='Bulgaria' AND p.elevation >2835
ORDER BY evalation DESC;

#EX_13
SELECT country_code, COUNT(mountain_id) AS mountain_range
FROM countries AS c
JOIN mountaince AS m ON c.country_code= m.country_code
GROUP BY mc. coutry_code, c.country_code, country_name
HAVING c.country_name IN ('United States','Rusia','Bulgaria')
ORDER BY country_code;

#Ex-14
SELECT country_name, river_name
FROM country AS c
LEFT JOIN countries_river AS cr
ON c.country_code=cr.country_code
LEFT JOIN rivers AS r ON cr.river_ide=r._id
JOIN continets AS cnt ON c.continent_code = cnt.continent_code
WHERE cnt.continent_name= 'Africa'
ORDER BY country_name ASC
LIMIT 5;

#EX_16
SELECT (
SELECT c2.country_id FROM countries AS c2
WHERE mauntained_id IS NULL
) AS country_count
FROm countries;

#EX_17
SELECT country_name,
 MAX(p.elevation) AS highest_peak_elevation, 
 MAX(r.length) AS longest_river_length
FROM countries AS c
JOIN countrie_river AS cr ON c.country_code= cr.country_code
JOIN rivers AS r ON cr.river_id=r.id
JOIN mountains_country AS mc ON mc.country_code=c.country_code
JOIN mountains AS m ON mc.mountain_id=.id
JOIN peaks AS p ON p.mountain_id=p.id
GROUP By c.country_name
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, country_name ASC
LIMIT  5;