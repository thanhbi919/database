-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`mathang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mathang` (
  `Mã MH` VARCHAR(10) NOT NULL,
  `TENHANG` VARCHAR(45) NULL DEFAULT NULL,
  `DVT` VARCHAR(10) NULL DEFAULT NULL,
  `SLHC` INT NULL DEFAULT NULL,
  `GBHT` DECIMAL(10,0) NULL DEFAULT NULL,
  PRIMARY KEY (`Mã MH`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`hdxuat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hdxuat` (
  `SOHD` INT NOT NULL,
  `NGAYBAN` DATE NULL DEFAULT NULL,
  `TENNMH` VARCHAR(45) NULL DEFAULT NULL,
  `MATHANG_Mã MH` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`SOHD`),
  INDEX `fk_HDXUAT_MATHANG1_idx` (`MATHANG_Mã MH` ASC) VISIBLE,
  CONSTRAINT `fk_HDXUAT_MATHANG1`
    FOREIGN KEY (`MATHANG_Mã MH`)
    REFERENCES `mydb`.`mathang` (`Mã MH`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`phieunhap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`phieunhap` (
  `SP` VARCHAR(10) NOT NULL,
  `NGAYNHAP` DATE NULL DEFAULT NULL,
  `TENNCC` VARCHAR(45) NULL DEFAULT NULL,
  `MATHANG_Mã MH` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`SP`),
  INDEX `fk_PHIEUNHAP_MATHANG_idx` (`MATHANG_Mã MH` ASC) VISIBLE,
  CONSTRAINT `fk_PHIEUNHAP_MATHANG`
    FOREIGN KEY (`MATHANG_Mã MH`)
    REFERENCES `mydb`.`mathang` (`Mã MH`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

