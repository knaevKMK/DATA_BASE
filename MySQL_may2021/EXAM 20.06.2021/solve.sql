CREATE SCHEMA IF NOT EXISTS stc;
USE stc;

CREATE TABLE categories(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR (10) NOT NULL
);

CREATE TABLE cars(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
make VARCHAR(20) NOT NULL,
model VARCHAR(20) ,
`year`  INT(11) DEFAULT 0 NOT NULL,
mileage INT(11) DEFAULT 0 ,
`condition`  CHAR(1) NOT NULL,
category_id INT(11) NOT NULL,
CONSTRAINT fk_cars_categories
FOREIGN KEY (category_id)
REFERENCES categories(id)
);

CREATE TABLE drivers(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR (30) NOT NULL,
last_name VARCHAR (30) NOT NULL,
age INT(11) NOT NULL,
rating FLOAT DEFAULT 5.5 
);

CREATE TABLE cars_drivers(
car_id INT(11) NOT NULL,
driver_id INT(11) NOT NULL,
CONSTRAINT pk_cars_drivers PRIMARY KEY (car_id, driver_id),
CONSTRAINT fk_cars_drivers_cars FOREIGN KEY (car_id) REFERENCES cars(id),
CONSTRAINT fk_cars_driver_drivers FOREIGN KEY (driver_id) REFERENCES drivers(id));

CREATE TABLE addresses(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR (100) NOT NULL
);

CREATE TABLE clients(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`full_name` VARCHAR (50) NOT NULL,
`phone_number` VARCHAR (20) NOT NULL
);
CREATE TABLE courses(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
from_address_id INT(11) NOT NULL,
`start` DATETIME NOT NULL,
bill DECIMAL(10,2) DEFAULT 10,
car_id INT(11) NOT NULL,
client_id INT(11) NOT NULL,
CONSTRAINT fk_courses_addresses
FOREIGN KEY (from_address_id) REFERENCES addresses(id),
CONSTRAINT fk_courses_cars
FOREIGN KEY (car_id) REFERENCES cars(id),
CONSTRAINT fk_courses_clients
FOREIGN KEY (client_id) REFERENCES clients(id)
);


#EX_2
INSERT INTO  clients (full_name, phone_number)
	SELECT CONCAT(d.first_name,' ' , d.last_name) ,
    CONCAT('(088) 9999', d.id*2)
    FROM drivers AS d
	WHERE d.id BETWEEN 10 AND 20;


#EX_3
UPDATE cars
SET `condition` = (
CASE 
	WHEN 
		mileage  >= 800000  OR mileage  IS NULL
        AND `year`  <=2010 
        AND make <> 'Mercedes-Benz'
	THEN 'C'
    ELSE `condition`=`condition`
END
 );

#EX_4
DELETE cl FROM clients AS cl
WHERE LENGTH(cl.full_name) >3
	AND (SELECT COUNT(co.id) FROM courses AS co WHERE cl.id=co.client_id ) =0;

#EX_5
SELECT make, model,`condition` FROM cars ORDER BY id;

#EX_6
SELECT 
	d.first_name,	d.last_name,	c.make,	c.model,	c.mileage
    FROM drivers AS d 
    JOIN cars_drivers AS cd ON d.id=cd.driver_id
    JOIN cars AS c ON cd.car_id=c.id
    WHERE c.mileage IS NOT NULL
    ORDER BY c.mileage DESC, d.first_name;

#EX_7
SELECT 
	car.id AS car_id,
	car.make,
	car.mileage,
	COUNT(co.id) AS count_of_courses,
	ROUND(AVG(co.bill),2) AS avg_bill
FROM cars AS car
LEFT JOIN courses AS co ON car.id = co.car_id
GROUP BY car.id
HAVING count_of_courses !=2
ORDER BY count_of_courses DESC, car_id;

#EX_8
SELECT 
	cl.full_name,
	COUNT(car.id) AS count_of_cars,
	SUM(co.bill)total_sum
FROM clients AS cl
JOIN courses AS co ON cl.id=co.client_id
JOIN cars AS car ON co.car_id=car.id
WHERE 'a'= SUBSTRING(cl.full_name,2,1)
	GROUP BY cl.id
HAVING count_of_cars>1
ORDER BY cl.full_name;

#EX_9
SELECT 
	ad.`name`,
	(CASE 
   WHEN  HOUR(co.`start`) BETWEEN 6 AND 20 THEN 'Day'
   ELSE 'Night'
    END) AS day_time,	
    co.bill,
	cl.full_name,
	car.make,	
    car.model,
	cat.`name` AS category_name
FROM courses AS co 
JOIN addresses AS ad ON co.from_address_id=ad.id
JOIN clients AS cl ON co.client_id = cl.id
JOIN cars AS car ON co.car_id=car.id
JOIN categories AS cat ON car.category_id = cat.id
ORDER BY co.id;

#EX_10
DELIMITER $$
CREATE FUNCTION udf_courses_by_client (phone_num VARCHAR (20)) 
RETURNS INT(11)
DETERMINISTIC
BEGIN
	RETURN (
    SELECT COUNT(co.id) FROM clients AS c JOIN courses AS co ON c.id=co.client_id WHERE c.phone_number= phone_num
    );
END$$
DELIMITER ;

#EX_11
DELIMITER $$
CREATE PROCEDURE udp_courses_by_address (address_name  VARCHAR (100))
BEGIN
	SELECT 
		ad.`name`,
		cl.full_name,
		(CASE
      WHEN  co.bill <19 THEN 'Low'
      WHEN co.bill BETWEEN 20 AND 30 THEN 'Medium'
      ELSE 'High'
        END) AS level_of_bill,
		car.make,
		car.`condition`,
		cat.`name` AS cat_name
    FROM addresses AS ad
    JOIN courses AS co ON ad.id=co.from_address_id
    JOIN clients AS cl ON co.client_id=cl.id
    JOIN cars AS car ON co.car_id=car.id
    JOIN categories AS cat ON car.category_id=cat.id
    WHERE ad.`name`=	address_name
    ORDER BY car.make, cl.full_name;
END$$
DELIMITER ;

EXTRACT(DAY FROM )