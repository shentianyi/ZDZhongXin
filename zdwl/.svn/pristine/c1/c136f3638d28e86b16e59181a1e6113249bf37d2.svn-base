-- Create table
create table t_supervisionfee
(
  id                            number(19) not null,
  distribid                     number(19),
  type							number(19),
  financial_institution         varchar2(200),
  bank                          varchar2(200),
  brand                         varchar2(50),
  intime                        date,
  supervisionfee_standard       varchar2(200),
  payment                       varchar2(100),
  pay_standard                  varchar2(100),
  pay_time                      date,
  pay_money                     varchar2(200),
  refund_time                    date,
  refund_money                  varchar2(200),
  refund_des                    varchar2(200),
  des                           varchar2(400)
);
-- Add comments to the columns 
comment on table t_supervisionfee is
'监管费退费表';
comment on column t_supervisionfee.distribid
  is '经销商id';
comment on column t_supervisionfee.type
  is '类型：1.缴费，2.退费';
comment on column t_supervisionfee.financial_institution
  is '金融机构';
comment on column t_supervisionfee.bank
  is '合作银行';
comment on column t_supervisionfee.brand
  is '品牌';
comment on column t_supervisionfee.intime
  is '进店时间';
comment on column t_supervisionfee.supervisionfee_standard
  is '监管费标准';
comment on column t_supervisionfee.payment
  is '付费方式';
comment on column t_supervisionfee.pay_standard
  is '缴费标准';
comment on column t_supervisionfee.pay_time
  is '缴费时间';
comment on column t_supervisionfee.pay_money
  is '缴费金额';
comment on column t_supervisionfee.refund_time
  is '退费时间';
comment on column t_supervisionfee.refund_money
  is '退费金额';  
comment on column t_supervisionfee.refund_des
  is '退费原因'; 
comment on column t_supervisionfee.des
  is '备注'; 
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_supervisionfee
  add constraint pk_t_supervisionfee primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_SUPERVISIONFEE
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
   't_supervisionfee',
   'SEQ_T_SUPERVISIONFEE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
