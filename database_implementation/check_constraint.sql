alter table warehouse add constraint cap check(cap>0 and cap<20000);
alter table warehouse add constraint rcap check(rcap>=0 and rcap<=cap);
alter table raw add constraint rinv check(rinv>=0);
alter table product add constraint pinv check(pinv>=0);
alter table log add constraint io check(io='出' or io='入');