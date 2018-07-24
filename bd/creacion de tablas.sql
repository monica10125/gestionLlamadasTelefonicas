use mydbjdbc;
CREATE TABLE IF NOT EXISTS `Call_empleado` (
  `secuencia_empleado` INT NOT NULL AUTO_INCREMENT,
  `nombre_Empleado` VARCHAR(50) NOT NULL,
  `estado_Empleado` VARCHAR(1) NULL,
  `tipo_empleado` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`secuencia_empleado`)
  )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Call_llamada_tel` (
  `secuencia_llamada` INT NOT NULL AUTO_INCREMENT,
  `fecha_ingreso_llamada` DATETIME(0) NOT NULL,
  `estado_llamada` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`secuencia_llamada`)
  )
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `Call_llamadaT_empleado` (
  `secuencia_emp_llamada` INT NOT NULL AUTO_INCREMENT,
 `fk_secuencia_llamadaT` INT NOT NULL,
 `fk_secuencia_empleado` INT NOT NULL,
 `fecha_inicio_llamada` DATETIME(0) NOT NULL,
 `fecha_terminacion_llamada` DATETIME(0) NULL,
	PRIMARY KEY  (`secuencia_emp_llamada`),
 CONSTRAINT `fk_secuencia_llamada1`
    FOREIGN KEY (`fk_secuencia_llamadaT`)
    REFERENCES `Call_llamada_tel` (`secuencia_llamada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,  
    CONSTRAINT `fk_secuencia_empleado1`
    FOREIGN KEY (`fk_secuencia_empleado`)
    REFERENCES `Call_empleado` (`secuencia_empleado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;

use mydbjdbc;
 alter table  Call_llamadaT_empleado drop column estado_procesamiento;  
	