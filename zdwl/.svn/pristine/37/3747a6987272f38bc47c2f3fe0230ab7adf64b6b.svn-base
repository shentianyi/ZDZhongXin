-- Create table
create table t_draft
(
  id                            number(19) not null,
  agreement                     varchar2(100),
  bail_num      varchar2(100),
  distribid      number(19),
  financial_institution         varchar2(200),
  brand                         varchar2(50),
  draft_num      varchar2(100),
  billing_date      date,
  due_date      date,
  billing_money      varchar2(100),
  mortgagecar_money    varchar2(100),
  payment_money      varchar2(100),
  state        number(19)
);
-- Add comments to the columns 
comment on table t_draft is
'汇票表';
comment on column t_draft.agreement
  is '质押协议号';
comment on column t_draft.bail_num
  is '保证金账号';
comment on column t_draft.distribid
  is '经销商';
comment on column t_draft.financial_institution
  is '金融机构';
comment on column t_draft.brand
  is '品牌';
comment on column t_draft.draft_num
  is '汇票号码';
comment on column t_draft.billing_date
  is '开票日';
comment on column t_draft.due_date
  is '到期日';
comment on column t_draft.billing_money
  is '开票金额';
comment on column t_draft.mortgagecar_money
  is '已押车金额';
comment on column t_draft.payment_money
  is '回款金额';
comment on column t_draft.state
  is '状态';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_draft
  add constraint pk_t_draft primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_DRAFT
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
   't_draft',
   'SEQ_T_DRAFT',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
