ALTER TABLE `apple`.`warehouse` 
ADD INDEX `fk_ano_idx` (`ano` ASC) VISIBLE;
;
ALTER TABLE `apple`.`warehouse` 
ADD CONSTRAINT `fk_ano`
  FOREIGN KEY (`ano`)
  REFERENCES `apple`.`admin` (`ano`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `apple`.`raw` 
ADD INDEX `fk_sno_idx` (`sno` ASC) VISIBLE;
;
ALTER TABLE `apple`.`raw` 
ADD CONSTRAINT `fk_sno`
  FOREIGN KEY (`sno`)
  REFERENCES `apple`.`supplier` (`sno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
ALTER TABLE `apple`.`raw` 
ADD INDEX `fk_wno_idx` (`wno` ASC) VISIBLE;
;
ALTER TABLE `apple`.`raw` 
ADD CONSTRAINT `fk_wno`
  FOREIGN KEY (`wno`)
  REFERENCES `apple`.`warehouse` (`wno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `apple`.`product` 
ADD INDEX `p_fk_wno_idx` (`wno` ASC) VISIBLE;
;
ALTER TABLE `apple`.`product` 
ADD CONSTRAINT `p_fk_wno`
  FOREIGN KEY (`wno`)
  REFERENCES `apple`.`warehouse` (`wno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `apple`.`supplier` 
ADD INDEX `fk_rno_idx` (`rno` ASC) VISIBLE;
;
ALTER TABLE `apple`.`supplier` 
ADD CONSTRAINT `fk_rno`
  FOREIGN KEY (`rno`)
  REFERENCES `apple`.`raw` (`rno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `apple`.`account` 
ADD INDEX `accfk_ano_idx` (`ano` ASC) VISIBLE;
;
ALTER TABLE `apple`.`account` 
ADD CONSTRAINT `acc_fk_ano_1`
  FOREIGN KEY (`ano`)
  REFERENCES `apple`.`admin` (`ano`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `apple`.`log` 
ADD INDEX `logano_idx` (`ano` ASC) VISIBLE,
ADD INDEX `logrno_idx` (`rno` ASC) VISIBLE,
ADD INDEX `logpno_idx` (`pno` ASC) VISIBLE;
;
ALTER TABLE `apple`.`log` 
ADD CONSTRAINT `logano`
  FOREIGN KEY (`ano`)
  REFERENCES `apple`.`admin` (`ano`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `logrno`
  FOREIGN KEY (`rno`)
  REFERENCES `apple`.`raw` (`rno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `logpno`
  FOREIGN KEY (`pno`)
  REFERENCES `apple`.`product` (`pno`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
