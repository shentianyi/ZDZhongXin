-- Create table
create table t_payment
(
  id                         NUMBER(19) not null,
  
  year             	 		 NUMBER(19),
  month           			 NUMBER(19),
  state             		 NUMBER(19)
)
-- Add comments to the table 
comment on table t_payment
  is '薪酬表';

-- Add comments to the columns 
comment on column t_payment.id
  is '主键ID';
 
comment on column t_payment.year
  is '年份';
comment on column t_payment.month
  is '月份';
comment on column t_payment.state
  is '状态 (0:未审核 1：经理审核 2：部长审核 3：财务审核 4：总监审核 5：审核通过)';
  
  
-- Create sequence 
create sequence SEQ_T_PAYMENT
minvalue 1
maxvalue 999999999999999999999999999
start with 123
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
   't_payment',
   'SEQ_T_PAYMENT',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');