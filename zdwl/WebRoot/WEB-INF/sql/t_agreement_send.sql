-- Create table
create table t_supervise_pay
(
  id				number(19) not null,
  distribid			number(19),
  financial_institution		varchar2(100),
  supervise_expire		date,
  payment_period		varchar2(100),
  pay_money			varchar2(100),
  already_pay			varchar2(100),
  not_pay			varchar2(100),
  change_payment		varchar2(100),
  balance_payment		varchar2(100)
);
-- Add comments to the columns 
comment on table t_supervise_pay is
'协议发送表';
comment on column t_supervise_pay.distribid
  is '经销商id';
comment on column t_supervise_pay.financial_institution
  is '金融机构';
comment on column t_supervise_pay.supervise_expire
  is '监管费到期日';
comment on column t_supervise_pay.payment_period
  is '缴费账期';
comment on column t_supervise_pay.pay_money
  is '缴费金额';
comment on column t_supervise_pay.already_pay
  is '已缴';
comment on column t_supervise_pay.not_pay
  is '未缴';
comment on column t_supervise_pay.change_payment
  is '变费补差';
comment on column t_supervise_pay.balance_payment
  is '变费余额';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_supervise_pay
  add constraint pk_t_supervise_pay primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_SUPERVISE_PAY
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
   't_supervise_pay',
   'SEQ_T_SUPERVISE_PAY',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
