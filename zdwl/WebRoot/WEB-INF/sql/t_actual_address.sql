-- Create table
create table t_actual_address
(
  id                            number(19) not null,
  distribid      number(19),
  agreement_address    varchar2(200),
  actual_address    varchar2(200),
  relationship      varchar2(200),
  isreport      number(19),
  distance      varchar2(200),
  des        varchar2(200)
);
-- Add comments to the columns 
comment on table t_actual_address is
'实际监管地址';
comment on column t_actual_address.distribid
  is '经销商';
comment on column t_actual_address.agreement_address
  is '协议地址';
comment on column t_actual_address.actual_address
  is '实际监管地址';
comment on column t_actual_address.relationship
  is '关系';
comment on column t_actual_address.isreport
  is '是否上报';
comment on column t_actual_address.distance
  is '距离';
comment on column t_actual_address.des
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_actual_address
  add constraint pk_t_actual_address primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_ACTUAL_ADDRESS
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
   't_actual_address',
   'SEQ_T_ACTUAL_ADDRESS',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
