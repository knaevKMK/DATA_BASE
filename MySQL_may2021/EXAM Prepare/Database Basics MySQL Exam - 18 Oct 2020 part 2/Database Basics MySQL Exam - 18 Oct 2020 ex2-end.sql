#EX_2
INSERT INTO products_stores (product_id, store_id)
	SELECT p.id, 1
    FROM products AS p
WHERE p.id  NOT IN (select product_id FROM products_stores );
SELECT * FROM products_stores;
#EX_3

UPDATE employees AS e
SET e.manager_id = 3 , e.salary=e.salary -500
WHERE YEAR(hire_date) >=2003 AND (
		SELECT s.`name` FROM stores AS s
		WHERE e.store_id=s.id
) NOT IN ('Cardguard','Veribet');

SELECT * FROM employees WHERE manager_id=3;
SELECT * FROM stores WHERE `name` IN ('Cardguard','Veribet');
#EX_4
DELETE e FROM employees AS e
WHERE manager_id Is NOT NULL AND salary >=6000;
#EX_5
SELECT
 first_name,	middle_name,	last_name,	salary,	hire_date
 FROM employees
 ORDER BY hire_date DESC;

#EX_6
SELECT 
	(p.name) as product_name,	
    p.price,	
    p.best_before, 
    CONCAT(SUBSTRING(p.description,1,10),'...') AS	short_description,	
    pic.url
    FROM products AS p
    JOIN pictures AS pic
    ON p.picture_id= pic.id
    WHERE length(p.description) >100 AND YEAR(pic.added_on)<2019 AND p.price >20
    ORDER BY p.price DESC;
#EX_7
SELECT 
    s.`name`,
    COUNT(p.id) AS product_count,
    ROUND( AVG(p.price),2) AS `avg`
FROM
    products AS p
        JOIN
    products_stores AS ps ON p.id = ps.product_id
    RIGHT JOIN
    stores AS s ON s.id=ps.store_id
GROUP BY s.`name`
ORDER BY product_count DESC , `avg` DESC , s.id;


#EX_8
SELECT 
    CONCAT(e.first_name, ' ', e.last_name) AS Full_name,
    (s.`name`) AS Store_name,
    (a.`name`) AS `address`,
  ( e.salary) AS salary
FROM    employees AS e
        JOIN    stores AS s ON e.store_id = s.id
        JOIN    addresses AS a ON a.id = s.address_id
WHERE
    e.salary <= 4000 

	AND LENGTH(s.`name`) >= 8
	AND e.last_name LIKE '%n'
    LIMIT 1,1;
    
    #hardoce
#        AND a.`name` LIKE '%{$5}%' ;

#EX_9
SELECT 
	reverse(s.`name`) AS reversed_name,
	CONCAT( UpPER(t.`name`), '-', a.`name`) AS full_address,
	COUNT(e.id) AS employees_count
    
FROM stores AS s
   JOIN addresses AS a ON s.address_id=a.id
   JOIN towns AS t ON t.id = a.town_id
   JOIN employees AS e ON e.store_id=s.id
   GROUP BY s.`name`
   HAVING employees_count >= 1
   ORDER BY full_address;


#EX_10
DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50)) 
RETURNS VARCHAR(50)
DETERMINISTIC
BEGIN
	RETURN (
    SELECT
		concat(
			e.first_name,' ', 
			e.middle_name, '. ' , 
			e.last_name, ' works in store for ', 
			(YEAR('2020/10/18')- YEAR(e.hire_date)  ), ' years')
    FROM employees AS e
    JOIN stores AS s ON e.store_id=s.id
    WHERE s.`name`=store_name
    ORDER BY e.salary DESC
    LIMIT 1
    );    
END$$
DELIMITER ;

SELECT udf_top_paid_employee_by_store('Stronghold') as 'full_info';
SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';

#EX_11

DELIMITER $$
CREATE PROCEDURE udp_update_product_price (address_name VARCHAR (50))
BEGIN
	UPDATE products
    SET price = price +
    (CASE 
		WHEN (address_name ) LIKE '0%' THEN 100
        	ELSE 200
    END)
    WHERE id IN (
    SELECT pc.product_id
    FROM products_stores AS pc
    JOIN stores AS s ON s.id=pc.store_id
    JOIN addresses AS ad ON ad.id=s.address_id
    WHERE ad.name=address_name
   ) ;
END$$
DELIMITER ;
SET SQL_SAFE_UPDATES = 0;

CALL udp_update_product_price('07 Armistice Parkway');
SELECT name, price FROM products WHERE id IN (15,17);

CALL udp_update_product_price('1 Cody Pass');
SELECT name, price FROM products WHERE id IN (15,17);

 SELECT *
    FROM products_stores AS pc
    JOIN stores AS s ON s.id=pc.store_id
    JOIN addresses AS ad ON ad.id=s.address_id
    JOIN products AS p ON pc.product_id=p.id
    WHERE ad.name= '1 Cody Pass' ;
