-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mmdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mmdb` ;

-- -----------------------------------------------------
-- Schema mmdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mmdb` DEFAULT CHARACTER SET utf8 ;
USE `mmdb` ;

-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `latitude` DECIMAL(25,20) NULL,
  `longitude` DECIMAL(25,20) NULL,
  `address` VARCHAR(200) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `is_public` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_status` ;

CREATE TABLE IF NOT EXISTS `user_status` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `location_id` INT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT NULL,
  `role` VARCHAR(45) NULL,
  `first_ name` VARCHAR(100) NULL,
  `last_name` VARCHAR(100) NULL,
  `created_date` DATETIME NULL,
  `img_url` VARCHAR(5000) NULL,
  `promo_opt` TINYINT NULL,
  `about_me` TEXT NULL,
  `updated_date` DATETIME NULL,
  `user_status_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  INDEX `fk_user_user_status1_idx` (`user_status_id` ASC),
  CONSTRAINT `fk_user_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_status1`
    FOREIGN KEY (`user_status_id`)
    REFERENCES `user_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant` ;

CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `location_id` INT NOT NULL,
  `phone` VARCHAR(45) NULL,
  `email` VARCHAR(100) NULL,
  `website_url` VARCHAR(5000) NULL,
  `enabled` TINYINT NULL,
  `categories` VARCHAR(200) NULL,
  `img_url` VARCHAR(500) NULL,
  `name` VARCHAR(45) NULL,
  `description` TEXT NULL,
  `menu_url` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_restaurant_user1_idx` (`user_id` ASC),
  INDEX `fk_restaurant_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_restaurant_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restaurant_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `restaurant_id` INT NOT NULL,
  `review_text` VARCHAR(2000) NULL,
  `rating` INT NULL,
  `enabled` TINYINT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  `is_public` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_restaurant1_idx` (`restaurant_id` ASC),
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_like` ;

CREATE TABLE IF NOT EXISTS `review_like` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `review_id` INT NOT NULL,
  `liked` TINYINT NULL,
  `enabled` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_like_user1_idx` (`user_id` ASC),
  INDEX `fk_review_like_review1_idx` (`review_id` ASC),
  CONSTRAINT `fk_review_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_like_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `favorite_restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_restaurant` ;

CREATE TABLE IF NOT EXISTS `favorite_restaurant` (
  `user_id` INT NOT NULL,
  `restaurant_id` INT NOT NULL,
  `created_date` DATETIME NULL,
  `notes` TEXT NULL,
  INDEX `fk_favorite_restaurant_user1_idx` (`user_id` ASC),
  INDEX `fk_favorite_restaurant_restaurant1_idx` (`restaurant_id` ASC),
  PRIMARY KEY (`user_id`, `restaurant_id`),
  CONSTRAINT `fk_favorite_restaurant_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_restaurant_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `review_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_comment` ;

CREATE TABLE IF NOT EXISTS `review_comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `review_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `comment_text` VARCHAR(300) NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL,
  `enabled` TINYINT NULL,
  `is_public` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_comment_review1_idx` (`review_id` ASC),
  INDEX `fk_review_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_review_comment_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `updated_date` DATETIME NULL,
  `event_date` DATETIME NULL,
  `post_text` VARCHAR(1000) NULL,
  `restaurant_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_restaurant1_idx` (`restaurant_id` ASC),
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `badge` ;

CREATE TABLE IF NOT EXISTS `badge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(200) NULL,
  `img_url` VARCHAR(5000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_badge` ;

CREATE TABLE IF NOT EXISTS `user_badge` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `badge_id` INT NOT NULL,
  `date_received` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_badge_user_idx` (`user_id` ASC),
  INDEX `fk_user_badge_badge1_idx` (`badge_id` ASC),
  CONSTRAINT `fk_user_badge_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_badge_badge1`
    FOREIGN KEY (`badge_id`)
    REFERENCES `badge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `other_user_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `approved` TINYINT NULL,
  `date_requested` DATETIME NULL,
  `date_approved` DATETIME NULL,
  INDEX `fk_friend_user1_idx` (`user_id` ASC),
  INDEX `fk_friend_friend_idx` (`other_user_id` ASC),
  PRIMARY KEY (`other_user_id`, `user_id`),
  CONSTRAINT `fk_friend_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_friend`
    FOREIGN KEY (`other_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS mmuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'mmuser'@'localhost' IDENTIFIED BY 'mmuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'mmuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_ name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (1, NULL, 'test', 'test', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

COMMIT;

