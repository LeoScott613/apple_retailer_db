delimiter //
create trigger raw_log
after update on raw
for each row
begin
    declare id int;
    -- 获取id
    select max(lno) into id from log;
    set id=id+1;

    if (NEW.rinv>OLD.rinv) then
        insert into log (lno,io,date,rno,rvar) values(id,'入',current_timestamp,new.rno-old.rno);
    end if;

    if (NEW.rinv<OLD.rinv) then
        insert into log (lno,io,date,rno,rvar) values(id,'出',current_timestamp,old.rno-new.rno);
    end if;
end;
//
delimiter ;

delimiter //
create trigger product_log
after update on product
for each row
begin
    declare id int;
    -- 获取id
    select max(lno) into id from log;
    set id=id+1;

    if (NEW.pinv>OLD.pinv) then
        insert into log (lno,io,date,pno,pvar) values(id,'入',current_timestamp,new.pno-old.pno);
    end if;

    if (NEW.pinv<OLD.pinv) then
        insert into log (lno,io,date,pno,pvar) values(id,'出',current_timestamp,old.pno-new.pno);
    end if;
end;
//
delimiter ;