-- Create table
create table t_mailing
(
  id			number(19) not null,
  superviseid		number(19),
  mailnature		varchar2(200),
  mailtime		date,
  express		varchar2(200),
  express_num		varchar2(200),
  des			varchar2(200));
-- Add comments to the columns 
comment on table t_mailing is
'监管员邮寄明细';
comment on column t_mailing.superviseid
  is '监管员id';
comment on column t_mailing.mailnature
  is '快递性质';
comment on column t_mailing.mailtime
  is '邮寄时间';
comment on column t_mailing.express
  is '快递公司';
comment on column t_mailing.express_num
  is '单号';
comment on column t_mailing.des
  is '内容';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_mailing
  add constraint pk_t_mailing primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_MAILING
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
   't_mailing',
   'SEQ_T_MAILING',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
