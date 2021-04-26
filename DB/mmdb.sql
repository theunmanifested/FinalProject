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
-- Table `badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `badge` ;

CREATE TABLE IF NOT EXISTS `badge` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `img_url` VARCHAR(5000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `latitude` DECIMAL(25,20) NULL DEFAULT NULL,
  `longitude` DECIMAL(25,20) NULL DEFAULT NULL,
  `address` VARCHAR(200) NULL DEFAULT NULL,
  `city` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `zip` VARCHAR(45) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `is_public` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_status` ;

CREATE TABLE IF NOT EXISTS `user_status` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `location_id` INT(11) NULL DEFAULT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `enabled` TINYINT(4) NULL DEFAULT 1,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  `first_name` VARCHAR(100) NULL DEFAULT NULL,
  `last_name` VARCHAR(100) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `img_url` VARCHAR(5000) NULL DEFAULT 'https://png.pngitem.com/pimgs/s/22-223968_default-profile-picture-circle-hd-png-download.png',
  `promo_opt` TINYINT(4) NULL DEFAULT 1,
  `about_me` TEXT NULL DEFAULT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  `user_status_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_location1_idx` (`location_id` ASC),
  INDEX `fk_user_user_status1_idx` (`user_status_id` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
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
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant` ;

CREATE TABLE IF NOT EXISTS `restaurant` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `location_id` INT(11) NULL DEFAULT NULL,
  `phone` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `website_url` VARCHAR(5000) NULL DEFAULT NULL,
  `enabled` TINYINT(4) NULL DEFAULT 1,
  `categories` VARCHAR(200) NULL DEFAULT NULL,
  `img_url` VARCHAR(500) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `menu_url` VARCHAR(5000) NULL DEFAULT NULL,
  `military_discount` TINYINT NULL DEFAULT 0,
  `firstresponder_discount` TINYINT NULL DEFAULT 0,
  `senior_discount` TINYINT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  INDEX `fk_restaurant_user1_idx` (`user_id` ASC),
  INDEX `fk_restaurant_location1_idx` (`location_id` ASC),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC),
  CONSTRAINT `fk_restaurant_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_restaurant_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `favorite_restaurant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `favorite_restaurant` ;

CREATE TABLE IF NOT EXISTS `favorite_restaurant` (
  `user_id` INT(11) NOT NULL,
  `restaurant_id` INT(11) NOT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `notes` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`, `restaurant_id`),
  INDEX `fk_favorite_restaurant_user1_idx` (`user_id` ASC),
  INDEX `fk_favorite_restaurant_restaurant1_idx` (`restaurant_id` ASC),
  CONSTRAINT `fk_favorite_restaurant_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_favorite_restaurant_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `friend` ;

CREATE TABLE IF NOT EXISTS `friend` (
  `other_user_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `approved` TINYINT(4) NULL DEFAULT NULL,
  `date_requested` DATETIME NULL DEFAULT NULL,
  `date_approved` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`other_user_id`, `user_id`),
  INDEX `fk_friend_user1_idx` (`user_id` ASC),
  INDEX `fk_friend_friend_idx` (`other_user_id` ASC),
  CONSTRAINT `fk_friend_friend`
    FOREIGN KEY (`other_user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_friend_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `post`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `post` ;

CREATE TABLE IF NOT EXISTS `post` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `created_date` DATETIME NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  `event_date` DATETIME NULL DEFAULT NULL,
  `post_text` VARCHAR(1000) NULL DEFAULT NULL,
  `restaurant_id` INT(11) NULL DEFAULT NULL,
  `flagged` TINYINT NULL DEFAULT 0,
  `enabled` TINYINT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_post_user1_idx` (`user_id` ASC),
  INDEX `fk_post_restaurant1_idx` (`restaurant_id` ASC),
  CONSTRAINT `fk_post_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_post_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review` ;

CREATE TABLE IF NOT EXISTS `review` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `restaurant_id` INT(11) NOT NULL,
  `review_text` VARCHAR(2000) NULL DEFAULT NULL,
  `rating` INT(11) NULL DEFAULT NULL,
  `enabled` TINYINT(4) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  `is_public` TINYINT(4) NULL DEFAULT NULL,
  `flagged` TINYINT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_user1_idx` (`user_id` ASC),
  INDEX `fk_review_restaurant1_idx` (`restaurant_id` ASC),
  CONSTRAINT `fk_review_restaurant1`
    FOREIGN KEY (`restaurant_id`)
    REFERENCES `restaurant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `review_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_comment` ;

CREATE TABLE IF NOT EXISTS `review_comment` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `review_id` INT(11) NOT NULL,
  `user_id` INT(11) NOT NULL,
  `comment_text` VARCHAR(300) NULL DEFAULT NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `updated_date` DATETIME NULL DEFAULT NULL,
  `enabled` TINYINT(4) NULL DEFAULT NULL,
  `is_public` TINYINT(4) NULL DEFAULT NULL,
  `flagged` TINYINT NULL,
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
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `review_like`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `review_like` ;

CREATE TABLE IF NOT EXISTS `review_like` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `review_id` INT(11) NOT NULL,
  `liked` TINYINT(4) NULL DEFAULT NULL,
  `enabled` TINYINT(4) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_like_user1_idx` (`user_id` ASC),
  INDEX `fk_review_like_review1_idx` (`review_id` ASC),
  CONSTRAINT `fk_review_like_review1`
    FOREIGN KEY (`review_id`)
    REFERENCES `review` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_like_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `user_badge`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_badge` ;

CREATE TABLE IF NOT EXISTS `user_badge` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `badge_id` INT(11) NOT NULL,
  `date_received` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_badge_user_idx` (`user_id` ASC),
  INDEX `fk_user_badge_badge1_idx` (`badge_id` ASC),
  CONSTRAINT `fk_user_badge_badge1`
    FOREIGN KEY (`badge_id`)
    REFERENCES `badge` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_badge_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE = '';
DROP USER IF EXISTS mmuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'mmuser'@'localhost' IDENTIFIED BY 'mmuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'mmuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `badge`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `badge` (`id`, `name`, `description`, `img_url`) VALUES (1, 'Future Foodie', 'post your first review and get one like', 'https://cdn4.vectorstock.com/i/thumb-large/51/33/bronze-medal-with-red-ribbon-vector-23835133.jpg');
INSERT INTO `badge` (`id`, `name`, `description`, `img_url`) VALUES (2, 'Regular Foodie', 'active enough to get noticed', 'https://cdn.pixabay.com/photo/2021/04/18/14/46/cupcake-6188629_960_720.png');
INSERT INTO `badge` (`id`, `name`, `description`, `img_url`) VALUES (3, 'Expert Foodie', 'look at you being such an overachiever', 'https://cdn.pixabay.com/photo/2020/01/21/16/56/owl-4783407_960_720.png');

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (1, 39.7452, 104.9922, '1550 Blake St', 'Denver', 'CO', '80202', '3331986776', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (2, 39.7481, 104.9995, '1431 Larimer St', 'Denver', 'CO', '80202', '3037976476', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (3, 39.7532, 105.0019, '1889 16th St Mall', 'Denver', 'CO', '80202', '3337446455', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (4, 39.7561, 105.0091, '2364 15th St', 'Denver', 'CO', '80202', '3037434415', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (5, 39.7529, 105.0000, '1701 Wynkoop St', 'Denver', 'CO', '80202', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (6, NULL, NULL, '9220 Wigham St', 'Denver', 'CO', '80229', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (7, NULL, NULL, '9967 Clayton St', 'Denver', 'CO', '80229', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (8, NULL, NULL, '9025 W Jefferson Ave', 'Denver', 'CO', '80235', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (9, NULL, NULL, '9056 E Lehigh Ave', 'Denver', 'CO', '80237', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (10, NULL, NULL, '9811 E Jewell Ave', 'Denver', 'CO', '80210', '3037439415', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (11, NULL, NULL, '7351 E Warren Ave', 'Denver', 'CO', '80231', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (12, NULL, NULL, '6795 W 19th Pl', 'Lakewood', 'CO', '80214', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (13, NULL, NULL, '506 W Broadway Ave', 'Augusta', 'KS', '67010', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (14, NULL, NULL, '3060 S Hoyt Way', 'Lakewood', 'CO', '80227', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (15, NULL, NULL, '1507 S Ingalls St', 'Lakewood', 'CO', '80232', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (16, NULL, NULL, '3696 Quitman St', 'Denver', 'CO', '80212', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (17, NULL, NULL, '3530 Perry St', 'Denver', 'CO', '80212', '3038434615', 1);
INSERT INTO `location` (`id`, `latitude`, `longitude`, `address`, `city`, `state`, `zip`, `phone`, `is_public`) VALUES (18, NULL, NULL, '3420 Newton St', 'Denver', 'CO', '80212', '3038434615', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_status`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `user_status` (`id`, `name`) VALUES (1, 'hungry');
INSERT INTO `user_status` (`id`, `name`) VALUES (2, 'stuffed');
INSERT INTO `user_status` (`id`, `name`) VALUES (3, 'looking for something quick');
INSERT INTO `user_status` (`id`, `name`) VALUES (4, 'in the mood for fine dining');
INSERT INTO `user_status` (`id`, `name`) VALUES (5, 'hunting for something new');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (1, 6, 'admin', '$2a$10$g/wF2jGxSYYjwwsSaaMXSeLDFKrA6aBQtXDNeDsyh4KP5Uun9cJfi', 1, 'admin', 'Jane', 'Doe', '2021-04-01 00:00:00', 'https://png.pngitem.com/pimgs/s/22-223968_default-profile-picture-circle-hd-png-download.png', TRUE, 'I like to think of myself as a random person with no personality but pleasant to talk and funny enough ', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (2, 7, 'moderator', '$2a$10$T25qupotmcFRE9FCbQmBeum36jVGxIQMMAdfwYvmFx/hhTiJb01be', 1, 'moderator', 'John', 'Smith', '2021-04-01 00:00:00', 'https://png.pngitem.com/pimgs/s/22-223968_default-profile-picture-circle-hd-png-download.png', TRUE, 'I am all things and no thins. I am a relatively existing, and breathing, but you could not prove anything to anybody', '2021-04-02 00:00:00', 2);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (3, 8, 'bokbok', '$2a$10$jC68KeZSSkpOY..6wqIsBuPKMgwCaYEU1xMbDDha2F80KOjT3s2ZK', 1, 'restaurant-owner', 'Elizabeth', 'Hurley', '2021-04-01 00:00:00', 'https://png.pngitem.com/pimgs/s/22-223968_default-profile-picture-circle-hd-png-download.png', TRUE, 'Decent, funny, glamorous, always ready to dress up and go out for something to eat, but I get to pick', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (4, 9, 'pizzathehut', '$2a$10$uDc8X7JFkZx2P.RvFiLrUeLpffT8/R0sMAZYnmKN4bGmqWmvVnVrO', 1, 'standard', 'Elon', 'Musk', '2021-04-01 00:00:00', 'https://i.kym-cdn.com/entries/icons/facebook/000/027/100/_103330503_musk3.jpg', TRUE, 'I like to make things. Shiny techy things that might change the world one day. Maybe sometimes smoke, but mostly trying to eliminate greenhouse gases. I like to tinker.', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (5, 10, 'foodtruckfinder', '$2a$10$miggZDY69ugZqD9akkvzxesS5nTKv.ewoq/10aag/kObJ95/BIuFy', 1, 'standard', 'Kate', 'McKinnon', '2021-04-01 00:00:00', 'https://png.pngitem.com/pimgs/s/22-223968_default-profile-picture-circle-hd-png-download.png', TRUE, 'Never a dull moment, but then again, so is everybody else. I believe I am a good person to go out with, and I do mind it if you don\'t buy the drinks!', '2021-04-02 00:00:00', 5);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (6, 11, 'foodster', '$2a$10$FLBe92td8eOEmus/78FHyeCn16h92x//UnXhazrqId.W.M38LAvvq', 1, 'restaurant-owner', 'Jake', 'Jackson', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/06/26/02/47/man-2442565_960_720.jpg', TRUE, 'I\'m all about people. Eating, working, talking, and enjoying the company of good people to have a good time!', '2021-04-02 00:00:00', 2);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (7, 12, 'bigbertha', '$2a$10$vm8cKLa8RuolqUvGfavYL.8xXnjNpNRqTmO9XZT9lNuxiwwvMgtrC', 1, 'standard', 'Billy', 'Schmidt', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/08/09/13/35/model-2614569_960_720.jpg', TRUE, 'I like food. Period. I like to talk about food. Period. I like to rate food. Period. I like to short sentences. Period.', '2021-04-02 00:00:00', 3);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (8, 13, 'iamrex', '$2a$10$q4owX0hIeqKy4oc1glQve.AJIClUYLG2HbzLeH6xGNPS0ju3UzklG', 1, 'restaurant-owner', 'James', 'Jameson', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/08/01/08/29/woman-2563491_960_720.jpg', TRUE, 'The service business can be very rewarding, and having a place for families and friends to meet, and enjoy a great meal, is always a wonderful event.', '2021-04-02 00:00:00', 4);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (9, 14, 'gogetfoodyou', '$2a$10$XdOeYB1Ty9Y7S42l5hITTuElJ34NlUGLVZ1vpDYV1joOWqU9m49de', 1, 'restaurant-owner', 'Kate', 'Katinson', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/08/01/08/29/woman-2563491_960_720.jpg', TRUE, 'Give me a minute, and I will give you a story and great dish! I believe in sharing our foodie experiences to make the world a better place.', '2021-04-02 00:00:00', 3);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (10, 15, 'iliketoeat', '$2a$10$/O64p2bNshl9e563h9sv7uLNdzvkxpDWcUMKuCgL4BSlXlHFEsL8G', 1, 'restaurant-owner', 'Jill', 'Dakota', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/08/01/08/29/woman-2563491_960_720.jpg', TRUE, 'Maybe I\'m shy in real life, but not online! I can be as loud and as obnoxious as I like. And I do!', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (11, 16, 'nomnoms', '$2a$10$26BJySaGyryzW8zjDUnHxuFNNiCqIT3El6hGqLJe32hsPoGuktGFq', 1, 'standard', 'William', 'Coppola', '2021-04-01 00:00:00', ' https://cdn.pixabay.com/photo/2015/07/31/15/01/man-869215_960_720.jpg', TRUE, 'I always wanted to be a farmer, but I hated the early mornings, lots of dirt and manure. I mean, why can\'t we just have farm fresh veggies without all the hassle?', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (12, 17, 'krustykrab', '$2a$10$9mpuYKVm89SC.O9I/XqjpuTgDNoMKCGjFRQMDu/z8MujGn2YX91ga', 1, 'standard', 'Lenord', 'Wish', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2017/08/06/09/53/people-2590813_960_720.jpg', TRUE, 'Quirky for sure, but I am serious about my food. I take it very seriously and so should you.', '2021-04-02 00:00:00', 1);
INSERT INTO `user` (`id`, `location_id`, `username`, `password`, `enabled`, `role`, `first_name`, `last_name`, `created_date`, `img_url`, `promo_opt`, `about_me`, `updated_date`, `user_status_id`) VALUES (13, 18, 'youmusnt', '$2a$10$5vHbzCaUqChBCaTvVDzbiO6LcHz5kwWwM5ECZ38Lg6WL5jaNlPMY.', 1, 'standard', 'Lisa', 'Kable', '2021-04-01 00:00:00', 'https://cdn.pixabay.com/photo/2019/06/02/17/33/woman-4246954_960_720.jpg', TRUE, 'I\'m a foodie, and proud of it. I like to eat, and not just at one place at a time.party', '2021-04-02 00:00:00', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `restaurant`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `restaurant` (`id`, `user_id`, `location_id`, `phone`, `email`, `website_url`, `enabled`, `categories`, `img_url`, `name`, `description`, `menu_url`, `military_discount`, `firstresponder_discount`, `senior_discount`) VALUES (1, 3, 1, '1234567890', 'feedback@bokbok.com', 'bokbok.com', 1, 'fried chicken, comfort food', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR9hwMNAkYk8d5LfcVe-uSI0gG5sbMwGAzm5g&usqp=CAU', 'Bok Bok Korean Fried Chicken', 'Real Korean fried chicken, real good!', 'https://www.seamless.com/food/bok_bok_chicken/menu', 1, 1, 1);
INSERT INTO `restaurant` (`id`, `user_id`, `location_id`, `phone`, `email`, `website_url`, `enabled`, `categories`, `img_url`, `name`, `description`, `menu_url`, `military_discount`, `firstresponder_discount`, `senior_discount`) VALUES (2, 6, 2, '3035559999', 'gimmefood@riojanot.com', 'http://www.riojadenver.com/', 1, 'seafood, asian, mediterranean', 'http://www.gearbox.design/rioja2/wp-content/uploads/2020/10/Brioche-Crusted-Skate-Wing-400.jpg', 'Rioja', 'Locally sourced, imaginative Mediterranean dishes & wines in a high-energy dining room', 'http://www.gearbox.design/rioja2/menus/dinner/', 0, 1, 0);
INSERT INTO `restaurant` (`id`, `user_id`, `location_id`, `phone`, `email`, `website_url`, `enabled`, `categories`, `img_url`, `name`, `description`, `menu_url`, `military_discount`, `firstresponder_discount`, `senior_discount`) VALUES (3, 8, 3, '5035559323', 'lotsoffood@tavernetta.com', 'https://www.tavernettadenver.com/', 1, 'Italian, pasta, pizza', 'https://images.getbento.com/accounts/27fae721261607dfb815cef2b5d79e15/media/images/78500Tav_Fireplace.jpg', 'Tavernetta', 'Upscale, buzzy Italian hot spot from an acclaimed team with an open kitchen & a fireplace lounge.', 'https://www.tavernettadenver.com/menus/', 0, 0, 0);
INSERT INTO `restaurant` (`id`, `user_id`, `location_id`, `phone`, `email`, `website_url`, `enabled`, `categories`, `img_url`, `name`, `description`, `menu_url`, `military_discount`, `firstresponder_discount`, `senior_discount`) VALUES (4, 9, 4, '3037434415', 'ordersomefood@justbkitchen.com', 'https://justbekitchen.com/', 1, 'American, brunch, comfort', 'https://justbekitchen.com/wp-content/uploads/2019/04/Burrito-2.jpg', 'Just Be Kitchen', 'Breakfast & lunch spot for gluten-free American fare in a cheery, counter-serve environment', 'https://justbekitchen.com/wp-content/uploads/2020/10/JBKMenu_DinnerFallWinter2020-FINAL-with-BLEEDS-AND-WEB.pdf', 1, 1, 1);
INSERT INTO `restaurant` (`id`, `user_id`, `location_id`, `phone`, `email`, `website_url`, `enabled`, `categories`, `img_url`, `name`, `description`, `menu_url`, `military_discount`, `firstresponder_discount`, `senior_discount`) VALUES (5, 10, 5, '3037434415', 'betchacancook@ultreiadenver.com', 'http://www.ultreiadenver.com/', 1, 'Spanish, Portuguese, rustic', 'http://www.ultreiadenver.com/images/ultreia-121.jpg', 'Ultreia', 'Spanish tapas, Portuguese petiscos and more in a mural-lined, split-level Union Station space', 'http://www.ultreiadenver.com/#menus', 1, 0, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `favorite_restaurant`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `favorite_restaurant` (`user_id`, `restaurant_id`, `created_date`, `notes`) VALUES (5, 1, '2021-04-01 00:00:00', 'Ate here with the squad and everyone loved it...best fried chicken spot so far.');

COMMIT;


-- -----------------------------------------------------
-- Data for table `friend`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `friend` (`other_user_id`, `user_id`, `approved`, `date_requested`, `date_approved`) VALUES (5, 4, true, '2021-04-01 00:00:00', '2021-04-01 00:00:00');

COMMIT;


-- -----------------------------------------------------
-- Data for table `post`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `post` (`id`, `user_id`, `created_date`, `updated_date`, `event_date`, `post_text`, `restaurant_id`, `flagged`, `enabled`) VALUES (1, 4, '2021-04-01 00:00:00', '2021-04-01 00:00:00', '2021-04-05 22:00:00', 'Anybody down for a late night meal at BokBoks? I heard good things', 1, NULL, NULL);
INSERT INTO `post` (`id`, `user_id`, `created_date`, `updated_date`, `event_date`, `post_text`, `restaurant_id`, `flagged`, `enabled`) VALUES (2, 5, '2021-04-01 00:00:00', '2021-04-01 00:00:00', NULL, 'Im craving Korean fried chicken...you guys know the place, hit me up...', NULL, NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (1, 5, 1, 'Best fried chicken I\'ve found in Denver so far...and its Korean style! Try the spicy, it\'s worth it.', 5, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (2, 1, 2, 'The seafood is incredibly fresh and you can tell that the staff takes great pride in their craft.', 4, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (3, 4, 3, 'I guess my favorite thing was the sauces because its the foundation of most of their dishes. Amazing!!', 4, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, TRUE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (4, 7, 4, 'The French Toast has landed! I mean, it is rich and buttery, but full of complex flavor', 3, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', FALSE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (5, 4, 5, 'Bravo! Seriously, the tapas at this Spanish restaurants are beautiful and super delicious', 5, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', FALSE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (6, 11, 1, 'Fried to perfection, and the taste is divine. I can eat this all day, all week, everyday', 5, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, TRUE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (7, 12, 2, 'Never been so disappointed in my life, and I like the water in Flint, Michigan', 1, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (8, 13, 3, 'meh\' Its just another Italian restaurant. Service was very good, tho. ', 2, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (9, 5, 4, 'Just what I was looking for! American cuisine done well and they have a large variety of dishes', 4, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);
INSERT INTO `review` (`id`, `user_id`, `restaurant_id`, `review_text`, `rating`, `enabled`, `created_date`, `updated_date`, `is_public`, `flagged`) VALUES (10, 12, 5, 'I truly believe that the best food comes from Portugal and Spain, and this restaurant just confirmed it.', 5, TRUE, '2021-04-01 00:00:00', '2021-04-02 00:00:00', TRUE, FALSE);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_comment`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `review_comment` (`id`, `review_id`, `user_id`, `comment_text`, `created_date`, `updated_date`, `enabled`, `is_public`, `flagged`) VALUES (1, 1, 4, 'Heck yeah, I\'m headed there this weekend...thanks!', '2021-04-01 00:00:00', '2021-04-01 00:00:00', 1, false, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `review_like`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `review_like` (`id`, `user_id`, `review_id`, `liked`, `enabled`) VALUES (1, 4, 1, true, true);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_badge`
-- -----------------------------------------------------
START TRANSACTION;
USE `mmdb`;
INSERT INTO `user_badge` (`id`, `user_id`, `badge_id`, `date_received`) VALUES (1, 5, 1, '2021-04-01 00:00:00');

COMMIT;

