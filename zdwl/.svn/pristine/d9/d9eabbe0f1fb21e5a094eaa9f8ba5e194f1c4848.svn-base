-- Create table
create table t_distribset
(
  id                            number(19) not null,
  code				varchar2(200),
  value				varchar2(100),
  distribid			number(19));
-- Add comments to the columns 
comment on table t_distribset is
'经销商参数表';
comment on column t_distribset.code
  is '参数代码';
comment on column t_distribset.value
  is '参数值';
comment on column t_distribset.distribid
  is '经销商id';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_distribset
  add constraint pk_t_distribset primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_DISTRIBSET
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
   't_distribset',
   'SEQ_T_DISTRIBSET',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
