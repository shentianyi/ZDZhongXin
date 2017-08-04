-- Create table
create table t_refund_invoice
(
  id                            number(19) not null,
  distribid                     number(19),
  financial_institution         varchar2(200),
  bank                          varchar2(200),
  brand                         varchar2(50),
  intime                        date,
  supervisionfee_standard       varchar2(200),
  payment                       varchar2(100),
  pay_standard                  varchar2(100),
  pay_time                      date,
  pay_money                     varchar2(200),
  refund_time                   date,
  refund_money                  varchar2(200),
  refund_des                    varchar2(200),
  isinvoice      number(19),
  invoice_time      date,
  invoice_company    varchar2(200),
  invoice_type      varchar2(200),
  des                           varchar2(400)
);
-- Add comments to the columns 
comment on table t_refund_invoice is
'退款发票管理';
comment on column t_refund_invoice.distribid
  is '经销商id';
comment on column t_refund_invoice.financial_institution
  is '金融机构';
comment on column t_refund_invoice.bank
  is '合作银行';
comment on column t_refund_invoice.brand
  is '品牌';
comment on column t_refund_invoice.intime
  is '进店时间';
comment on column t_refund_invoice.supervisionfee_standard
  is '监管费标准';
comment on column t_refund_invoice.payment
  is '付费方式';
comment on column t_refund_invoice.pay_standard
  is '缴费标准';
comment on column t_refund_invoice.pay_time
  is '缴费时间';
comment on column t_refund_invoice.pay_money
  is '缴费金额';
comment on column t_refund_invoice.refund_time
  is '退费时间';
comment on column t_refund_invoice.refund_money
  is '退费金额';  
comment on column t_refund_invoice.refund_des
  is '退费原因'; 
comment on column t_refund_invoice.isinvoice
  is '是否收到退款发票'; 
comment on column t_refund_invoice.invoice_time
  is '收票时间'; 
comment on column t_refund_invoice.invoice_company
  is '开票单位'; 
comment on column t_refund_invoice.invoice_type
  is '发票类型'; 
comment on column t_refund_invoice.des
  is '备注'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_refund_invoice
  add constraint pk_t_refund_invoice primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_REFUND_INVOICE
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
   't_refund_invoice',
   'SEQ_T_REFUND_INVOICE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
