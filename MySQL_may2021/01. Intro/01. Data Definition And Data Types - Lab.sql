CREATE SCHEMA data_definition_and_data_types;

USE data_definition_and_data_types;
#EX_1
CREATE TABLE employees(
id INT AUTO_INCREMENT PRIMARY KEY,
first_name VARCHAR(255)  NOT NULL,
last_name VARCHAR(255) NOT NULL
);

CREATE TABLE categories(
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(255) NOT NULL
);

CREATE TABLE products(
id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(255) NOT NULL,
category_id INT NOT NULL
);


#EX_2
INSERT INTO employees (first_name, last_name) VALUES
 ("Ivan", "Ivanov"),
 ("Peter", "Petrov"),
 ("George", "Gerov");


#EX_3
ALTER TABLE employees
ADD midlle_name VARCHAR(50);


#EX_4
ALTER TABLE products
ADD CONSTRAINT fk_category_id
FOREIGN KEY (category_id) 
REFERENCES categories (id);

#EX_5
ALTER TABLE employees
MODIFY COLUMN midlle_name VARCHAR(100);



#fault 
ALTER TABLE employees
DROP COLUMN midle_name;

DROP TABLE employees;
SELECT * from employees;