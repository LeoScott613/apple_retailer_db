create view warehouse_admin as
    select wno as 仓库编号,
    a.ano as 管理员编号,
    aname as 管理员姓名,
    gen as 管理员性别,
    doj as 入职时间
    from warehouse as w
    join admin as a on w.ano=a.ano;

create view raw_supplier as 
    select r.rno as 原料编号,
    r.rname as 原料名称,
    s.sname as 供应商名称,
    r.rinv as 原料库存量
    from raw as r
    join supplier as s on r.sno=s.sno;

create view raw_warehous as 
    select r.rno as 原料编号,
    r.rname as 原料名称,
    w.wno as 仓库编号,
    r.rinv as 库存量
    from raw as r
    join warehouse as w on r.wno=w.wno;

create view product_warehouse as 
    select p.pno as 成品编号,
    p.pname as 成品名称,
    w.wno as 仓库编号,
    p.pinv as 成品库存量
    from product as p
    join warehouse as w on p.wno=w.wno;