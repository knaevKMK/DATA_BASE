USE `gamebar`;
CREATE TABLE `emplyees`(
 `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `first_name` VARCHAR(45) NOT NULL,
 `second_name` VARCHAR(45) NOT NULL
 );
 CREATE TABLE `categories`(
 `id` INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(45) NOT NULL
 );
 CREATE TABLE `products`(
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `name` VARCHAR(45) NOT NULL,
 `category_id` INT NOT NULL,
 
 CONSTRAINT `fk_category_id`
 FOREIGN KEY (`category_id`)
 REFERENCES `gamebar`.`categories` (`id`)
 ON DELETE NO ACTION
 ON UPDATE CASCADE,
 
 INDEX `fk_category_Ind` (`category_id` ASC) INVISIBLE );