CREATE TABLE `apple`.`warehouse` (
  `wno` INT NOT NULL,
  `cat` VARCHAR(50) NOT NULL,
  `ano` INT NOT NULL,
  `cap` INT NOT NULL,
  `rcap` INT NOT NULL,
  PRIMARY KEY (`wno`))
COMMENT = '仓库';

CREATE TABLE `apple`.`admin` (
  `ano` INT NOT NULL,
  `aname` VARCHAR(50) NOT NULL,
  `gen` VARCHAR(3) NOT NULL,
  `doj` DATETIME NOT NULL,
  PRIMARY KEY (`ano`))
COMMENT = '管理员';

CREATE TABLE `apple`.`raw` (
  `rno` INT NOT NULL,
  `rname` VARCHAR(50) NOT NULL,
  `sno` INT NOT NULL,
  `rexp` DATETIME NOT NULL,
  `wno` INT NOT NULL,
  `rinv` INT NOT NULL,
  PRIMARY KEY (`rno`))
COMMENT = '原料';

CREATE TABLE `apple`.`product` (
  `pno` INT NOT NULL,
  `pname` VARCHAR(50) NOT NULL,
  `pexp` DATETIME NOT NULL,
  `wno` INT NOT NULL,
  `pinv` INT NOT NULL,
  PRIMARY KEY (`pno`))
COMMENT = '成品';

CREATE TABLE `apple`.`supplier` (
  `sno` INT NOT NULL,
  `sname` VARCHAR(50) NOT NULL,
  `rno` INT NOT NULL,
  PRIMARY KEY (`sno`))
COMMENT = '供应商';

CREATE TABLE `apple`.`account` (
  `acno` INT NOT NULL,
  `acc` VARCHAR(50) NOT NULL,
  `ano` INT NOT NULL,
  `pass` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`acno`),
  UNIQUE INDEX `pass_UNIQUE` (`pass` ASC) VISIBLE,
  UNIQUE INDEX `acc_UNIQUE` (`acc` ASC) VISIBLE)
COMMENT = '账号';
ALTER TABLE `apple`.`account` 
DROP INDEX `pass_UNIQUE` ;
;

CREATE TABLE `apple`.`log` (
  `lno` INT NOT NULL,
  `io` VARCHAR(8) NOT NULL,
  `date` DATETIME NOT NULL,
  `ano` INT NOT NULL,
  `rno` INT NOT NULL,
  `rvar` INT NOT NULL,
  `pno` INT NOT NULL,
  `pvar` INT NOT NULL,
  PRIMARY KEY (`lno`))
COMMENT = '出入库记录';
