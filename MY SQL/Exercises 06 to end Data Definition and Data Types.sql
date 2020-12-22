CREATE TABLE people(
`id` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
`name` VARCHAR(200) NOT NULL,
/*`picture` FILE NULL, */
`height` DOUBLE (4,2) NULL,
`weight` DOUBLE (4,2) NULL,
`gender` CHAR  NOT NULL,
`birthdate` DATE NOT NULL,
`biography` VARCHAR (200)
);