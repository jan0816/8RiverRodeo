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
  `password` VARCHAR(45) NULL,
  `phone_number` VARCHAR(45) NULL,
  `rank` INT NULL,
  `role` VARCHAR(45) NOT NULL DEFAULT 'user',
  `picture_url` TEXT NULL,
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
  `size_in_cm` DECIMAL NULL,
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
INSERT INTO `team` (`id`, `name`, `password`, `phone_number`, `rank`, `role`, `picture_url`) VALUES (1, 'Flyman', 'password', NULL, NULL, 'user', NULL);
INSERT INTO `team` (`id`, `name`, `password`, `phone_number`, `rank`, `role`, `picture_url`) VALUES (2, 'Pussy power', 'password', NULL, NULL, 'user', NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `fishdb`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (1, 'Seth', 'Schneider', 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `team_id`) VALUES (2, 'Jan', 'Ellsworth', 2);

COMMIT;

