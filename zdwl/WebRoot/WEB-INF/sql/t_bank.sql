drop table t_bank;

-- Create table
create table t_bank
(
  id              number(19),
  bankName        varchar2(200),
  parentId        number(19),
  customerManager varchar2(50),
  managerPhone    varchar2(50),
  bankType        number
)
;
-- Add comments to the columns 
comment on column t_bank.bankname
  is '银行名称';
comment on column t_bank.parentid
  is 'parentId';
comment on column t_bank.customermanager
  is '客户经理';
comment on column t_bank.managerphone
  is '客户经理电话';
comment on column t_bank.banktype
  is '银行类型 1：总 2：分 3：支';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_bank
  add constraint pk_t_bank primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_BANK
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
  (201607141121,
   'zddb',
   't_bank',
   'SEQ_T_BANK',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
