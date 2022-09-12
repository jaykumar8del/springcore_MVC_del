CREATE TABLE `syskan1`.`employee` (
  `employee_id` INT NOT NULL AUTO_INCREMENT,
  `employee_name` VARCHAR(75) NULL,
  `email` VARCHAR(125) NULL,
  `mobile` BIGINT(12) NULL,
  `dob` DATE NULL,
  PRIMARY KEY (`employee_id`));
