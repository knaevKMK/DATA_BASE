CREATE SCHEMA today;
USE today;

#EX_1
CREATE TABLE people (
person_id INT PRIMARY KEY AUTO_INCREMENT,
first_name VARCHAR(45) NOT NULL,
salary DECIMAL(10,2) NOT NULL,
passport_id INT UNIQUE NOT NULL,
CONSTRAINT fk_passport_id
FOREIGN KEY (passport_id)
REFERENCES passport(id)
);

CREATE TABLE passport (
id INT  PRIMARY KEY AUTO_INCREMENT  ,
passport_number VARCHAR (20) UNIQUE NOT NULL
);



INSERT INTO passport (passport_number) VALUES
('N34FG21B'),
('K65LO4R7'),
('ZE657QP2');

SELECT* FROM passport;

INSERT INTO people (person_id,first_name, salary,passport_id) VALUE
(101,'Roberto', 43300.00, 102);
INSERT INTO people (first_name, salary,passport_id) VALUES

('Tom', 56100.00, 103),
('Yana', 60200.00, 101);

DROP SCHEMA today;

#EX_2
CREATE TABLE manufacturers(
manufacturer_id INT AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(20) NOT NULL,
established_on DATE NOT Null
);

CREATE TABLE models(
model_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL,
manufacturer_id INT NOT NULL,
CONSTRAINT fk_manufacturer_id
FOREIGN KEY (manufacturer_id)
REFERENCES manufacturers(manufacturer_id)
);


INSERT INTO manufacturers (`name`, established_on) VALUES 
('BMW', '1916-03-01'),
('Tesla', '2003-01-01'),
('Lada', '1966-05-01');

INSERT INTO models (`name`, manufacturer_id ) VALUES 
('X1', 1),
('i6', 1),
('Model S', 2),
('Model X', 2),
('Nova', 3);

SELECT * FROM models 
JOIN manufacturers AS m
ON models.manufacturer_id=m.manufacturer_id;

#EX_3
CREATE TABLE students(
student_id INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(45) NOT NULL
);

CREATE TABLE exams (
exam_i INT PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(44) NOT NULL
);

INSERT INTO exams (`name`) VALUES
('Spring MVP'),
('Neo4j'),
('Oracle 11g');

INSERT INTO students (`name`) VALUES
('Mila'),
('Toni'),
('Ron');


CREATE TABLE students_exams (
student_id INT ,
exam_id INT,

CONSTRAINT pf_students_exams
PRIMARY KEY (student_id, exam_id),

CONSTRAINT fk_students_exams_students
FOREIGN KEY (student_id)
REFERENCES students(student_id),

CONSTRAINT fk_students_exams_exams
FOREIGN KEY (exam_id)
REFERENCES exams(exam_id)
);

INSERT INTO students_exams VALUES
(1,101),
(1,102),
(2,101),
(3,103),
(2,102),
(2,103);

#EX_4

CREATE TABLE teachers (
teacher_id INT PRIMARY KEY AUTO_INCREMENT =101,
`name` VARCHAR(45) NOT NULL,
manager_id INT 
);

INSERT INTO teachers (`name`, manager_id) VALUES
('John',null),
('Maya',106),
('Silvia',106),
('Ted',105),
('Mark',101),
('Greta',101);

ALTER TABLE teachers
ADD CONSTRAINT fk_teachers_teachers
FOREIGN KEY (manager_id)
REFERENCES teachers(teacher_id);


#EX_5
CREATE TABLE item_types(
item_type_id  INT (11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE items (
item_id INT (11),
`name` VARCHAR (50),
item_type_id INT (11),
CONSTRAINT fk_items_item_types
FOREIGN KEY (item_type_id)
REFERENCES item_types(item_type_id)
);

CREATE TABLE cities (
city_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR (50)
);


CREATE TABLE customers (
customer_id INT (11),
`name` VARCHAR (50),
birthday DATE,
city_id INT (11),
CONSTRAINT fk_customers_cities
FOREIGN KEY (city_id)
REFERENCES cities(city_id)
 );

CREATE TABLE orders (
order_id INT (11) PRIMARY KEY AUTO_INCREMENT,
customer_id INT (11),
CONSTRAINT fk_orders_customers
FOREIGN KEY (customer_id)
REFERENCES customers(customer_id)
);

CREATE TABLE order_items (
order_id INT(11),
item_id INT (11)
);

#EX_6

CREATE TABLE subjects (
subjects_id INT (11) PRIMARY KEY AUTO_INCREMENT,
subject_name VARCHAR(50)
);

CREATE TABLE majors (
major_id INT(11) PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(50)
);

CREATE TABLE students (
student_id INT (11) PRIMARY KEY AUTO_INCREMENT,
student_number  VARCHAR(12),
student_name VARCHAR(50),
major_id INT (11),
 
 CONSTRAINT fk_students_majors
 FOREIGN KEY (major_id)
 REFERENCES majors(major_id)
);

CREATE TABLE payments (
payment_id INT (11) PRIMARY KEY AUTO_INCREMENT,
payment_date DATE,
payment_amount DECIMAL (8,2),
student_id INT (11),

CONSTRAINT fk_payment_students
FOREIGN KEY (student_id)
REFERENCES students(student_id)
);

CREATE TABLE agenda (
student_id INT (11) ,
subject_id INT (11),

CONSTRAINT pk_agenda_gaenda
PRIMARY KEY (student_id, subject_id),

CONSTRAINT fk_agenda_students
FOREIGN KEY (student_id)
REFERENCES students(student_id),
 
 CONSTRAINT fk_agenda_subjects
 FOREIGN KEY (subject_id)
 REFERENCES subjects(subject_id)
);


