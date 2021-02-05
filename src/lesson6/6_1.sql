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
-- Table `mydb`.`hdxuat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`hdxuat` (
  `SOHD` INT NOT NULL,
  `NGAYBAN` DATE NULL DEFAULT NULL,
  `TENNMH` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`SOHD`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


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
-- Table `mydb`.`phieunhap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`phieunhap` (
  `SP` VARCHAR(10) NOT NULL,
  `NGAYNHAP` DATE NULL DEFAULT NULL,
  `TENNCC` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`SP`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`nhap_hang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`nhap_hang` (
  `so_luong` INT NULL,
  `don_gia` VARCHAR(45) NULL,
  `mathang_Mã MH` VARCHAR(10) NOT NULL,
  `phieunhap_SP` VARCHAR(10) NOT NULL,
  INDEX `fk_nhap_hang_mathang_idx` (`mathang_Mã MH` ASC) VISIBLE,
  INDEX `fk_nhap_hang_phieunhap1_idx` (`phieunhap_SP` ASC) VISIBLE,
  CONSTRAINT `fk_nhap_hang_mathang`
    FOREIGN KEY (`mathang_Mã MH`)
    REFERENCES `mydb`.`mathang` (`Mã MH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_nhap_hang_phieunhap1`
    FOREIGN KEY (`phieunhap_SP`)
    REFERENCES `mydb`.`phieunhap` (`SP`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`xuat_hang`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`xuat_hang` (
  `so_luong` INT NULL,
  `don_gia` VARCHAR(45) NULL,
  `mathang_Mã MH` VARCHAR(10) NOT NULL,
  `hdxuat_SOHD` INT NOT NULL,
  INDEX `fk_xuat_hang_mathang1_idx` (`mathang_Mã MH` ASC) VISIBLE,
  INDEX `fk_xuat_hang_hdxuat1_idx` (`hdxuat_SOHD` ASC) VISIBLE,
  CONSTRAINT `fk_xuat_hang_mathang1`
    FOREIGN KEY (`mathang_Mã MH`)
    REFERENCES `mydb`.`mathang` (`Mã MH`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_xuat_hang_hdxuat1`
    FOREIGN KEY (`hdxuat_SOHD`)
    REFERENCES `mydb`.`hdxuat` (`SOHD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
