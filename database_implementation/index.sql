-- hash index
create index hash_raw on raw(rname) using hash;
-- prefix index
create index prefix_acc on account(acc(4));