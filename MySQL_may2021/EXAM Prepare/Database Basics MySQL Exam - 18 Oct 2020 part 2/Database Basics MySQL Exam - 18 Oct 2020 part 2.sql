CREATE SCHEMA IF NOT EXISTS softuni_store_system;
USE softuni_store_system;

CREATE TABLE towns(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) UNIQUE NOT NULL);

CREATE TABLE addresses(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50) UNIQUE NOT NULL,
town_id INT(11) NOT NULL,
CONSTRAINT fk_addresse_towns
FOREIGN KEY (town_id)
REFERENCES towns(id));

CREATE TABLE stores (
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(20) UNIQUE NOT NULL,
rating FLOAT(4) NOT NULL,
has_parking BOOLEAN DEFAULT FALSE,
address_id INT(11) NOT NULL,
CONSTRAINT fk_stores_addresses
FOREIGN KEY (address_id)
REFERENCES addresses(id));


CREATE TABLE employees(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(15) NOT NULL,
middle_name CHAR(1),
last_name VARCHAR(20)  NOT NULL,
salary DECIMAL(19,2) DEFAULT 0,
hire_date DATE NOT NULL,
manager_id INT(11) ,
store_id INT(11) NOT NULL,
CONSTRAINT fk_employees_managers
FOREIGN KEY (manager_id)
REFERENCES employees(id),
CONSTRAINT fk_emplyees_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
);
CREATE TABLE pictures(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
url VARCHAR(100) NOT NULL,
added_on DATETIME NOT NULL
);
CREATE TABLE categories(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL
);
CREATE TABLE products(
id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(40) UNIQUE NOT NULL,
best_before DATE ,
price DECIMAL (10,2) NOT NULL,
`description` TEXT ,
category_id INT(11),
picture_id INT(11),
CONSTRAINT fk_employees_categories
FOREIGN KEY (category_id)
REFERENCES categories(id),
CONSTRAINT fk_employees_pictures
FOREIGN KEY (picture_id)
REFERENCES pictures(id)
);
CREATE TABLE products_stores(
product_id INT(11) NOT NULL,
store_id INT(11) NOT NULL,
CONSTRAINT pk_product_store
PRIMARY KEY (product_id,store_id),
CONSTRAINT FK_products_stores_products
FOREIGN KEY (product_id)
REFERENCES products(id),
CONSTRAINT fk_products_stores_stores
FOREIGN KEY (store_id)
REFERENCES stores(id)
);