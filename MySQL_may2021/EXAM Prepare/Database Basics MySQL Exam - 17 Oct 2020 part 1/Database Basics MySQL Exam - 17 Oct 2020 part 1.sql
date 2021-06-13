CREATE SCHEMA exam_prep01;
USE exam_prep01;


CREATE TABLE categories(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(40) UNIQUE NOT NULL);

CREATE TABLE pictures(
id INT AUTO_INCREMENT PRIMARY KEY,
url VARCHAR(100) NOT NULL,
added_on DATETIME NOT NULL);

CREATE TABLE products(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(40) UNIQUE NOT NULL,
best_before DATE ,
price DECIMAL(10,2) NOT NULL,
`description` TEXT,
category_id INT(11) NOT NULL,
picture_id INT(11) NOT NULL,
CONSTRAINT fk_products_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_products_pictures
FOREIGN KEY (picture_id)
REFERENCES pictures(id)
);

CREATE TABLE towns (
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(20) UNIQUE  NOT NULL);

CREATE TABLE addresses(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(50) UNIQUE  NOT NULL,
town_id INT(11) NOT NULL,
CONSTRAINT fk_address_towns
FOREIGN KEY (town_id)
REFERENCES towns(id)
);

CREATE TABLE stores(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(20) UNIQUE  NOT NULL,
rating FLOAT NOT NULL,
has_parking BOOLEAN,
address_id INT(11) NOT NULL ,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id)
);

CREATE TABLE products_stores(
product_id INT (11) ,
store_id INT(11) ,
CONSTRAINT pk_products_stores
 PRIMARY KEY (product_id,store_id),
CONSTRAINT fk_products_stores_products
FOREIGN KEY (product_id)
REFERENCES products(id),
CONSTRAINT fk_products_stores_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
 );

CREATE TABLE employees(
id INT(11) AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(15) NOT NULL,
middle_name CHAR(1) ,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL (19,2)  NOT NULL DEFAULT 0,
hire_date DATE NOT NULL,
manager_id INT(11) ,
store_id INT(11) NOT NULL,
CONSTRAINT fk_employees_managers
FOREIGN KEY (manager_id)
REFERENCES employees(id),
CONSTRAINT fk_employees_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
);