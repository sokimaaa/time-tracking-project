-- Schema time_tracking
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `time_tracking`;

-- -----------------------------------------------------
-- Schema time_tracking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `time_tracking` DEFAULT CHARACTER SET utf8 ;
USE `time_tracking` ;

-- -----------------------------------------------------
-- Table `time_tracking`.`access`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`access` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`access` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `message` VARCHAR(45) NOT NULL)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`category` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`category` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `category_name` VARCHAR(45) NOT NULL UNIQUE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`city` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`city` (
                                                      `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                      `city_name` VARCHAR(45) NOT NULL UNIQUE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`status` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`status` (
                                                        `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                        `status_name` VARCHAR(20) NOT NULL UNIQUE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`role` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`role` (
                                                      `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                      `name` VARCHAR(25) NOT NULL UNIQUE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`sex`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`sex` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`sex` (
                                                     `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                                     `sex` VARCHAR(45) NOT NULL UNIQUE)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`user` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`user` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_id` INT NULL DEFAULT '2',
    `username` VARCHAR(255) NOT NULL UNIQUE,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(80) NULL UNIQUE DEFAULT NULL ,
    `phone` VARCHAR(10) NULL UNIQUE DEFAULT NULL,
    `sex_id` INT NULL DEFAULT '1',
    `city_id` INT NULL DEFAULT '1',
    `creating_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `access_id` INT NOT NULL,
    FOREIGN KEY (`city_id`) REFERENCES `time_tracking`.`city` (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `time_tracking`.`role` (`id`),
    FOREIGN KEY (`sex_id`) REFERENCES `time_tracking`.`sex` (`id`),
    FOREIGN KEY (`access_id`) REFERENCES `time_tracking`.`access` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`activity`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`activity` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`activity` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `activity_name` VARCHAR(255) UNIQUE NOT NULL,
    `category_id` INT NOT NULL,
    `city_id` INT NOT NULL,
    `description` VARCHAR(1024) NULL DEFAULT 'None',
    `status_id` INT NOT NULL,
    `count_customers` INT NOT NULL DEFAULT '1',
    `owner_id` INT NOT NULL,
    FOREIGN KEY (`category_id`) REFERENCES `time_tracking`.`category` (`id`),
    FOREIGN KEY (`city_id`) REFERENCES `time_tracking`.`city` (`id`),
    FOREIGN KEY (`status_id`) REFERENCES `time_tracking`.`status` (`id`),
    FOREIGN KEY (`owner_id`) REFERENCES `time_tracking`.`user` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

-- -----------------------------------------------------
-- Table `time_tracking`.`users_activities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `time_tracking`.`users_activities` ;

CREATE TABLE IF NOT EXISTS `time_tracking`.`users_activities` (
                                                                  `activity_id` INT NOT NULL,
                                                                  `user_id` INT NOT NULL,
                                                                  `spent_time_last_update_minute` INT UNSIGNED NULL DEFAULT '0',
                                                                  `total_spent_time_minute` INT UNSIGNED NULL DEFAULT '0',
                                                                  `access_id` INT NOT NULL,
                                                                  PRIMARY KEY (`activity_id`, `user_id`),
    FOREIGN KEY (`activity_id`) REFERENCES `time_tracking`.`activity` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `time_tracking`.`user` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`access_id`) REFERENCES `time_tracking`.`access` (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;