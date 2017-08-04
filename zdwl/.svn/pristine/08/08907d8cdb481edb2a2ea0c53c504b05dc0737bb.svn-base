-- Create table
create table t_car_operation
(
  id			number(19) not null,
  cars			varchar2(500),
  userid		number(19),
  type			number(19),
  createtime		date);
-- Add comments to the columns 
comment on table t_car_operation is
'车辆操作表';
comment on column t_car_operation.cars
  is '车辆集合';
comment on column t_car_operation.userid
  is '操作人';
comment on column t_car_operation.type
  is '操作类型';
comment on column t_car_operation.createtime
  is '创建时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_car_operation
  add constraint pk_t_car_operation primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_CAR_OPERATION
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
   't_car_operation',
   'SEQ_T_CAR_OPERATION',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
