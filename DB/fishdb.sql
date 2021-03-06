-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fishdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `fishdb` ;

-- -----------------------------------------------------
-- Schema fishdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fishdb` DEFAULT CHARACTER SET utf8 ;
USE `fishdb` ;

-- -----------------------------------------------------
-- Table `team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `team` ;

CREATE TABLE IF NOT EXISTS `team` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(200) NULL,
  `phone_number` VARCHAR(45) NULL,
  `rank` INT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'user',
  `picture_url` TEXT NULL,
  `enabled` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `river`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `river` ;

CREATE TABLE IF NOT EXISTS `river` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `team_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_team1_idx` (`team_id` ASC),
  CONSTRAINT `fk_user_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fish`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `fish` ;

CREATE TABLE IF NOT EXISTS `fish` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `size_in_cm` DECIMAL(10,1) NULL,
  `river_id` INT NOT NULL,
  `picture_url` TEXT NULL,
  `user_id` INT NOT NULL,
  `day_caught` DATE NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_fish_river1_idx` (`river_id` ASC),
  INDEX `fk_fish_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_fish_river1`
    FOREIGN KEY (`river_id`)
    REFERENCES `river` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fish_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS fish_user;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'fish_user' IDENTIFIED BY 'fishpassword';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'fish_user';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `team`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `team` (`id`, `name`, `password`, `phone_number`, `rank`, `role`, `picture_url`, `enabled`) VALUES (1, 'Flyman', '$2a$10$TynlDhIMm4mCZfULqJjG7OqUzpCkdFYp5fZwzeTDJ/UgKq8YDjUlG', '1239705555', 1, 'user', 'https://i.pinimg.com/originals/96/57/fc/9657fcf983d0497f4813b01da29e12c5.jpg', 1);
INSERT INTO `team` (`id`, `name`, `password`, `phone_number`, `rank`, `role`, `picture_url`, `enabled`) VALUES (2, 'Pussy power', '$2a$10$k1wdvhPRYYDZkS/eg8P0KebRPoBoeAFY32yhc8l0zIYa1dqZ6x4pm', '3211234567', 2, 'user', 'https://i.ytimg.com/vi/cVxTZFpYKMg/hqdefault.jpg', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `river`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `river` (`id`, `name`) VALUES (1, 'Colorado');
INSERT INTO `river` (`id`, `name`) VALUES (2, 'Frying Pan');
INSERT INTO `river` (`id`, `name`) VALUES (3, 'Roaring Fork');
INSERT INTO `river` (`id`, `name`) VALUES (4, 'Crystal');
INSERT INTO `river` (`id`, `name`) VALUES (5, 'Taylor');
INSERT INTO `river` (`id`, `name`) VALUES (6, 'East');
INSERT INTO `river` (`id`, `name`) VALUES (7, 'Spring Creek');
INSERT INTO `river` (`id`, `name`) VALUES (8, 'Gunnison');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (1, 'Seth', 'Schneider', 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (2, 'Jan', 'Ellsworth', 2);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (3, 'Bob', 'Dobbs', 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (4, 'Jane', 'Doe', 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `fish`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (1, 43.5, 1, 'https://guiderecommended.com/wp-content/uploads/2019/01/Fly-Fish-Hacks-to-Catch-More-Fish-1.jpg', 1, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (2, 43, 1, 'https://cdn.shopify.com/s/files/1/0196/1360/files/brook-trout_1893ad83-5507-421c-8b71-2cb3d3d17470_large.jpg?v=1503073627', 2, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (3, 51, 2, NULL, 1, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (4, 47, 2, NULL, 2, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (5, 44.5, 3, NULL, 1, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (6, 37, 3, NULL, 2, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (7, 36, 4, NULL, 1, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (8, 33.5, 4, NULL, 2, '2019-07-26');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (9, 47.5, 5, NULL, 1, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (10, 42, 5, NULL, 2, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (11, 39.5, 6, NULL, 1, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (12, 37, 6, NULL, 2, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (13, 26.5, 7, NULL, 1, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (14, 27, 7, NULL, 2, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (15, 33, 8, NULL, 1, '2019-07-27');
INSERT INTO `fish` (`id`, `size_in_cm`, `river_id`, `picture_url`, `user_id`, `day_caught`) VALUES (16, 21, 8, NULL, 2, '2019-07-27');

COMMIT;

