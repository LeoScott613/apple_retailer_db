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
        insert into log (lno,io,date,rno,rvar) values(id,'入',current_timestamp,NEW.rno,new.rinv-old.rinv);
    end if;

    if (NEW.rinv<OLD.rinv) then
        insert into log (lno,io,date,rno,rvar) values(id,'出',current_timestamp,NEW.rno,old.rinv-new.rinv);
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
        insert into log (lno,io,date,pno,pvar) values(id,'入',current_timestamp,new.pno,new.pinv-old.pinv);
    end if;

    if (NEW.pinv<OLD.pinv) then
        insert into log (lno,io,date,pno,pvar) values(id,'出',current_timestamp,new.pno,old.pinv-new.pinv);
    end if;
end;
//
delimiter ;

delimiter //
create trigger raw_rcap_insert
before insert on raw
for each row
begin
    update warehouse set rcap=rcap-NEW.rinv where wno=NEW.wno;
end;
//
delimiter ;

delimiter //
create trigger product_rcap_insert
before insert on product 
for each row
begin
    update warehouse set rcap=rcap-NEW.pinv where wno=NEW.wno;
end;
//
delimiter ;

delimiter //
create trigger raw_rcap_update
after update on raw
for each row
begin
    if (NEW.rinv>OLD.rinv) then
        update warehouse set rcap=rcap-(NEW.rinv-OLD.rinv) where wno=NEW.wno;
    end if;

    if (NEW.rinv<OLD.rinv) then
        update warehouse set rcap=rcap+(OLD.rinv-NEW.rinv) where wno=NEW.wno;
    end if;
end;
//
delimiter ;

delimiter //
create trigger product_rcap_update
after update on product
for each row
begin
    if (NEW.pinv>OLD.pinv) then
        update warehouse set rcap=rcap-(NEW.pinv-OLD.pinv) where wno=NEW.wno;
    end if;

    if (NEW.pinv<OLD.pinv) then
        update warehouse set rcap=rcap+(OLD.pinv-NEWpinv) where wno=NEW.wno;
    end if;
end;
//
delimiter ;