-- Create table
create table t_mail_cost
(
  id      number(19) not null,
  promoter    number(19),
  fqdate    date,
  mailingitems    varchar2(200),
  parts      varchar2(200),
  other      varchar2(200),
  mailperson    number(19),
  express    varchar2(200),
  money      varchar2(200),
  transportbegin  varchar2(200),
  transportend    varchar2(200),
  receiveid    number(19),
  des      varchar2(200),
  nextApproval    number(19),
  approvalState    number(19));
-- Add comments to the columns 
comment on table t_mail_cost is
'设备邮寄费用表';
comment on column t_mail_cost.promoter
  is '发起人id';
comment on column t_mail_cost.fqdate
  is '发起时间';
comment on column t_mail_cost.mailingitems
  is '邮寄项目';
comment on column t_mail_cost.parts
  is '配件';
comment on column t_mail_cost.other
  is '其他';
comment on column t_mail_cost.mailperson
  is '邮寄人';
comment on column t_mail_cost.express
  is '快递公司';
comment on column t_mail_cost.money
  is '预计金额';
comment on column t_mail_cost.transportbegin
  is '运输城市起';
comment on column t_mail_cost.transportend
  is '运输城市止';
comment on column t_mail_cost.receiveid
  is '接收人';
comment on column t_mail_cost.des
  is '事件描述';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_mail_cost
  add constraint pk_t_mail_cost primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_MAIL_COST
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
   't_mail_cost',
   'SEQ_T_MAIL_COST',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
