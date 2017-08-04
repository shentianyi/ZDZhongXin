-- Create table
create table t_car_manual
(
  id			number(19) not null,
  sid			number(19),
  userid		number(19),
  receiveid		number(19));
-- Add comments to the columns 
comment on table t_car_manual is
'质押监管手工台账';
comment on column t_car_manual.sid
  is '车辆id';
comment on column t_car_manual.userid
  is '交付人';
comment on column t_car_manual.receiveid
  is '接收人';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_car_manual
  add constraint pk_t_car_manual primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_CAR_MANUAL
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

  
  
insert into t_seqencedict
  (id,
   dbname,
   tablename,
   seqname,
   seqinitvalue,
   addseqdate,
   createtime,
   addsequser,
   memo)
values
  ((select max(id) + 1 from t_seqencedict),
   'zddb',
   't_car_manual',
   'SEQ_T_CAR_MANUAL',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
