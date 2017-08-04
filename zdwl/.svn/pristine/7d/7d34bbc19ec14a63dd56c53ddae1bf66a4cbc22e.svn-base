-- Create table
create table t_open_invoice_list
(
  id                            number(19) not null,
  open_invoice_id               number(19),
  pay_time                      date,
  pay_money                     varchar2(200),
  isinvoice      number(19),
  invoice_type      varchar2(200),
  isTransfer      number(19)
);
-- Add comments to the columns 
comment on table t_open_invoice_list is
'开票管理列表';
comment on column t_open_invoice_list.open_invoice_id
  is '开票管理id';
comment on column t_open_invoice_list.pay_time
  is '缴费时间';
comment on column t_open_invoice_list.pay_money
  is '缴费金额';
comment on column t_open_invoice_list.isinvoice
  is '是否开票';
comment on column t_open_invoice_list.invoice_type
  is '开票方式';
comment on column t_open_invoice_list.isTransfer
  is '是否交接';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_open_invoice_list
  add constraint pk_t_open_invoice_list primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_OPEN_INVOICE_LIST
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
   't_open_invoice_list',
   'SEQ_T_OPEN_INVOICE_LIST',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
