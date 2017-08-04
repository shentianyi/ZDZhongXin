-- Create table
create table t_supervise_arrears
(
  id				number(19) not null,
  distribid			number(19),
  financial_institution		varchar2(100),
  arrears_begin			date,
  arrears_end			date,
  arrears_money			varchar2(100),
  arrears_user			varchar2(100),
  arrears_phone			varchar2(100),
  follow_date			date,
  follow_user			varchar2(100),
  follow_result			varchar2(300)
);
-- Add comments to the columns 
comment on table t_supervise_arrears is
'欠费台账';
comment on column t_supervise_arrears.distribid
  is '经销商id';
comment on column t_supervise_arrears.financial_institution
  is '金融机构';
comment on column t_supervise_arrears.arrears_begin
  is '欠费起始时间';
comment on column t_supervise_arrears.arrears_end
  is '欠费结束时间';
comment on column t_supervise_arrears.arrears_money
  is '欠费金额';
comment on column t_supervise_arrears.arrears_user
  is '待付款联系人';
comment on column t_supervise_arrears.arrears_phone
  is '联系方式';
comment on column t_supervise_arrears.follow_date
  is '跟进时间';
comment on column t_supervise_arrears.follow_user
  is '跟进人员';
comment on column t_supervise_arrears.follow_result
  is '跟进结果';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_supervise_arrears
  add constraint pk_t_supervise_arrears primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_SUPERVISE_ARREARS
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
   't_supervise_arrears',
   'SEQ_T_SUPERVISE_ARREARS',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
