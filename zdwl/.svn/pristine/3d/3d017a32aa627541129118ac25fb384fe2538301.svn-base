-- Create table
create table t_open_invoice
(
  id                            number(19) not null,
  distribid                     number(19),
  financial_institution         varchar2(200),
  bank                          varchar2(200),
  brand                         varchar2(50),
  intime                        date,
  supervisionfee_standard       varchar2(200),
  payment                       varchar2(100),
  pay_standard                  varchar2(100)
);
-- Add comments to the columns 
comment on table t_open_invoice is
'开票管理';
comment on column t_open_invoice.distribid
  is '经销商id';
comment on column t_open_invoice.financial_institution
  is '金融机构';
comment on column t_open_invoice.bank
  is '合作银行';
comment on column t_open_invoice.brand
  is '品牌';
comment on column t_open_invoice.intime
  is '进店时间';
comment on column t_open_invoice.supervisionfee_standard
  is '监管费标准';
comment on column t_open_invoice.payment
  is '付费方式';
comment on column t_open_invoice.pay_standard
  is '缴费标准';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_open_invoice
  add constraint pk_t_open_invoice primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_OPEN_INVOICE
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
   't_open_invoice',
   'SEQ_T_OPEN_INVOICE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
