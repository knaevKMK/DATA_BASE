#EX_1
SELECT e.employee_id, e.job_title, a.address_id, a.address_text
FROM addresses AS a
JOIN employees AS e
ON  e.address_id=a.address_id
ORDER BY address_id
LIMIT 5;

#EX_2
SELECT e.first_name, 
e.last_name, 
(t.`name`) AS town, 
a.address_text
FROM addresses AS a
JOIN employees AS e
ON e.address_id=a.address_id
JOIN towns AS t
ON a.town_id=t.town_id
ORDER BY e.first_name, e.last_name
LIMIT 5;


#EX_3
SELECT 
e.employee_id,
e.first_name, 
e.last_name, 
(d.`name`)AS department_name
FROM employees AS e
JOIN departments AS d
ON d.department_id=e.department_id
WHERE d.`name`= 'Sales'
ORDER BY employee_id DESC;

#EX_4
SELECT 
    e.employee_id,
    e.first_name,
    e.salary,
    (d.`name`) AS department_name
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    e.salary > 15000
ORDER BY d.department_id DESC
LIMIT 5;

#EX_5
SELECT e.employee_id, e.first_name 
FROM employees AS e 
LEFT JOIN employees_projects AS ep
ON ep.employee_id= e.employee_id
WHERE ep.project_id IS NULL
ORDER BY employee_id DESC
LIMIT 3;

#EX_6
SELECT 
    e.first_name,
    e.last_name,
    e.hire_date,
    (d.`name`) AS dept_name
FROM
    employees AS e
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE
    d.name IN ('Sales' , 'Finance')
        AND DATE(e.hire_date) > '1999/1/1'
ORDER BY e.hire_date ASC;


#EX_7
SELECT
	e.employee_id,
    e.first_name,
    (p.`name`) AS project_name
FROM employees AS e
JOIN employees_projects AS ep
	ON ep.employee_id=e.employee_id
JOIN projects AS p
	ON ep.project_id = p.project_id
WHERE DATE(p.start_date)>'2002/08/13' 
	AND p.end_date IS NULL
ORDER BY e.first_name, project_name
LIMIT 5;


#EX_8
SELECT 
	e.employee_id,
    e.first_name,
    (
    CASE
		WHEN YEAR(p.start_date) >= 2005 THEN NULL
		ELSE p.`name`
    END
    )AS project_name
FROM employees AS e
JOIN employees_projects AS ep
	ON ep.employee_id=e.employee_id
JOIN projects AS p
	ON p.project_id=ep.project_id
WHERE e.employee_id =24
ORDER BY project_name ASC;


#EX_9
SELECT
	e.employee_id,
    e.first_name,
    e.manager_id,
    (e2.first_name) AS manager_name
FROM employees AS e
JOIN employees AS e2
ON e2.employee_id=e.manager_id
WHERE e.manager_id IN (3,7)
ORDER BY e.first_name;

#EX_10
SELECT 
    e.employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS emplyess_name,
    CONCAT(e2.first_name, ' ', e2.last_name) AS manager_name,
    (d.`name`) AS department_name
FROM
    employees AS e
        JOIN
    employees AS e2 ON e2.employee_id = e.manager_id
        JOIN
    departments AS d ON e.department_id = d.department_id
WHERE e.manager_id IS NOT NULL
ORDER BY employee_id
LIMIT 5;


#EX_11
SELECT AVG(e.salary) AS min_average_salary
FROM employees AS e
GROUP BY e.department_id
ORDER BY min_average_salary
LIMIT 1;

#EX_12
SELECT 
    c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
        JOIN
    mountains AS m ON m.id = mc.mountain_id
        JOIN
    peaks AS p ON p.mountain_id = m.id
WHERE
    c.country_code = 'BG'
        AND p.elevation > 2835
ORDER BY p.elevation DESC;


#EX_13
SELECT 
    c.country_code, COUNT(mc.mountain_id) AS mountain_range
FROM
    countries AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
WHERE
    c.country_name IN ('United States' , 'Russia', 'Bulgaria')
GROUP BY c.country_code
ORDER BY mountain_range DESC;

#EX_14
SELECT 
    c.country_name, r.river_name
FROM
    `countries` AS c
       JOIN
    countries_rivers AS cr ON c.country_code = cr.country_code
        JOIN
    rivers AS r ON r.id = cr.river_id
        JOIN
    continents AS cn ON cn.continent_code = c.continent_code
WHERE
    cn.continent_name = 'Africa'
ORDER BY c.country_name
LIMIT 5;

#EX_15
SELECT 
    c.continent_code,
    c.currency_code,
    COUNT(*) AS currency_usage
FROM
    `countries` AS c
GROUP BY c.continent_code , c.currency_code
HAVING currency_usage>1
	AND currency_usage =(
    SELECT COUNT(8) AS cn
    FROM `countries` AS c2
    WHERE c2.continent_code=c.continent_code
    GROUP BY c2.currency_code
    ORDER BY cn DESC
    LIMIT 1
    )
ORDER BY c.continent_code , c.currency_code;


#EX_16
SELECT 
    COUNT(*) AS country_count
FROM
    (SELECT 
        mc.country_code AS mc_country_code
    FROM
        mountains_countries AS mc
    GROUP BY mc.country_code) AS d
        RIGHT JOIN
    `countries` AS c ON c.country_code = d.mc_country_code
WHERE
    d.mc_country_code IS NULL;

#EX_17
SELECT 
    c.country_name,
    MAX(p.elevation) AS 'highest_peak_elevation',
    MAX(r.length) AS 'longest_river_length'
FROM
    `countries` AS c
        JOIN
    mountains_countries AS mc ON c.country_code = mc.country_code
        JOIN
    peaks AS p ON mc.mountain_id = p.mountain_id
        JOIN
    countries_rivers AS cr ON c.country_code = cr.country_code
        JOIN
    rivers AS r ON cr.river_id= r.id
GROUP BY c.country_name
ORDER BY highest_peak_elevation DESC , longest_river_length DESC
LIMIT 5;

