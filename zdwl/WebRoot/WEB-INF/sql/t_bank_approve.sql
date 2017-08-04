-- Create table
create table t_bank_approve
(
  id            number(19) not null,
  sid    number(19),
  state    number(19),
  createtime  date,
  approvetime  date
);
-- Add comments to the columns 
comment on table t_bank_approve is
'银行审批表';
comment on column t_bank_approve.sid
  is '车id';
comment on column t_bank_approve.state
  is '状态';
comment on column t_bank_approve.createtime
  is '创建时间';
comment on column t_bank_approve.approvetime
  is '审批时间';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_bank_approve
  add constraint pk_t_bank_approve primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_BANK_APPROVE
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
   't_bank_approve',
   'SEQ_T_BANK_APPROVE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');