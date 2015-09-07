SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `QuintoElemento` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `QuintoElemento` ;

-- -----------------------------------------------------
-- Table `QuintoElemento`.`Padrinos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Padrinos` (
  `documento` INT NOT NULL,
  `nombres` VARCHAR(45) NULL,
  `fechaNacimiento` DATE NULL,
  `telefono1` VARCHAR(45) NULL,
  `telefono2` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `correoElectronico1` VARCHAR(45) NOT NULL,
  `correoElectronico2` VARCHAR(45) NULL,
  `estado` VARCHAR(1) NULL,
  `notas` VARCHAR(200) NULL,
  PRIMARY KEY (`documento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`Ninos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Ninos` (
  `documento` INT NOT NULL,
  `nombre` VARCHAR(40) NULL,
  `fechaNacimiento` DATE NULL,
  `telefono1` VARCHAR(45) NULL,
  `telefono2` VARCHAR(45) NULL,
  `direccion` VARCHAR(45) NULL,
  `nombreAcudiente1` VARCHAR(45) NULL,
  `telefono1Acudiente1` VARCHAR(45) NULL,
  `telefono2Acudiente1` VARCHAR(45) NULL,
  `parentescoAcudiente1` VARCHAR(45) NULL,
  `nombreAcudiente2` VARCHAR(45) NULL,
  `telefono1Acudiente2` VARCHAR(45) NULL,
  `telefono2Acudiente2` VARCHAR(45) NULL,
  `parentescoAcudiente2` VARCHAR(45) NULL,
  `habilidad` VARCHAR(45) NULL,
  `estado` VARCHAR(1) NULL COMMENT 'A Activo\nI inactivo',
  `notas` VARCHAR(200) NULL,
  PRIMARY KEY (`documento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`NinosPadrino`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`NinosPadrino` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idPadrino` INT NOT NULL,
  `idNinos` INT NOT NULL,
  PRIMARY KEY (`id`, `idPadrino`, `idNinos`),
  INDEX `idPadrino_idx` (`idPadrino` ASC),
  INDEX `idNino_idx` (`idNinos` ASC),
  CONSTRAINT `idPadrino`
    FOREIGN KEY (`idPadrino`)
    REFERENCES `QuintoElemento`.`Padrinos` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idNino`
    FOREIGN KEY (`idNinos`)
    REFERENCES `QuintoElemento`.`Ninos` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`Pagos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Pagos` (
  `idPagos` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `idPadrino` INT NULL,
  `valor` INT NULL,
  `notas` VARCHAR(200) NULL,
  PRIMARY KEY (`idPagos`),
  INDEX `idPadrino_idx` (`idPadrino` ASC),
  CONSTRAINT `idPadrinoPago`
    FOREIGN KEY (`idPadrino`)
    REFERENCES `QuintoElemento`.`Padrinos` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`MateriaPrima`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`MateriaPrima` (
  `codigo` INT NOT NULL,
  `descripcion` VARCHAR(45) NULL,
  `presentacion` VARCHAR(45) NULL,
  `proveedor1` VARCHAR(45) NULL,
  `telefono1` VARCHAR(45) NULL,
  `proveedor2` VARCHAR(45) NULL,
  `telefono2` VARCHAR(45) NULL,
  `estado` VARCHAR(1) NULL,
  `notas` VARCHAR(200) NULL,
  PRIMARY KEY (`codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`Inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Inventario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `codigoItem` INT NULL,
  `cantidad` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `codigoItem_idx` (`codigoItem` ASC),
  CONSTRAINT `codigoItem`
    FOREIGN KEY (`codigoItem`)
    REFERENCES `QuintoElemento`.`MateriaPrima` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`Movimientos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Movimientos` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `tipo` VARCHAR(1) NULL,
  `fecha` DATE NULL,
  `codigoItem` INT NULL,
  `cantidad` INT NULL,
  `notas` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `codigoItem_idx` (`codigoItem` ASC),
  CONSTRAINT `codigoItemMto`
    FOREIGN KEY (`codigoItem`)
    REFERENCES `QuintoElemento`.`MateriaPrima` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `QuintoElemento`.`Actividades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `QuintoElemento`.`Actividades` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NULL,
  `idNino` INT NULL,
  `detalle` VARCHAR(200) NULL,
  PRIMARY KEY (`id`),
  INDEX `idNino_idx` (`idNino` ASC),
  CONSTRAINT `idNinoAct`
    FOREIGN KEY (`idNino`)
    REFERENCES `QuintoElemento`.`Ninos` (`documento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
