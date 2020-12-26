ALTER TABLE `gamebar`.`products` 
ADD INDEX `fk_categories_ind` (`category_id` ASC) INVISIBLE;

ALTER TABLE `gamebar`.`products` 
ADD CONSTRAINT `fk_categories_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `gamebar`.`categories` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;
  
ALTER TABLE `gamebar`.`emplyees` 
drop COLUMN `midle_name`;

ALTER TABLE `gamebar`.`emplyees` 
ADD COLUMN `midle_name` VARCHAR(45) NOT NULL AFTER `first_name`;

ALTER TABLE `gamebar`.`emplyees` 
CHANGE COLUMN `midle_name` `midle_name` VARCHAR(100) NOT NULL ;


ALTER TABLE `gamebar`.`products` 
ADD COLUMN category_id INT AUTO_INCREMENT PRIMARY KEY,
ADD CONSTRAINT `fk_categories_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `gamebar`.`categories` (`id`)
  ON DELETE NO ACTION
  ON UPDATE CASCADE;