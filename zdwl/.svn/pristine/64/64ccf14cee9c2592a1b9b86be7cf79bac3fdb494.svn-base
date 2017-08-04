-- Create table
create table t_two_address
(
  id                            number(19) not null,
  distribid			number(19),
  two_name			varchar2(200),
  two_address			varchar2(200),
  two_username			varchar2(200),
  phone				varchar2(100),
  distance			varchar2(200),
  des				varchar2(200)
);
-- Add comments to the columns 
comment on table t_two_address is
'二网地址';
comment on column t_two_address.distribid
  is '经销商';
comment on column t_two_address.two_name
  is '二网名称';
comment on column t_two_address.two_address
  is '二网地址';
comment on column t_two_address.two_username
  is '二网联系人';
comment on column t_two_address.phone
  is '联系方式';
comment on column t_two_address.distance
  is '距离';
comment on column t_two_address.des
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_two_address
  add constraint pk_t_two_address primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_TWO_ADDRESS
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
   't_two_address',
   'SEQ_T_TWO_ADDRESS',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
