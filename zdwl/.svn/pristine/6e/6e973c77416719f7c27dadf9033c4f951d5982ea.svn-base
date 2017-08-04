-- Create table
create table t_agreement_back
(
  id        number(19) not null,
  distribid      number(19),
  financial_institution    varchar2(100),
  agreement_num      varchar2(100),
  agreement_date    date,
  financial_user    varchar2(100),
  financial_phone    varchar2(100),
  send_address      varchar2(100),
  send_date      date,
  isback      number(19),
  back_date      date,
  deposit_address    varchar2(200),
  des        varchar2(300)
);
-- Add comments to the columns 
comment on table t_agreement_back is
'协议回收表';
comment on column t_agreement_back.distribid
  is '经销商id';
comment on column t_agreement_back.financial_institution
  is '金融机构';
comment on column t_agreement_back.agreement_num
  is '协议编号';
comment on column t_agreement_back.agreement_date
  is '协议邮寄时间';
comment on column t_agreement_back.financial_user
  is '金融机构联系人';
comment on column t_agreement_back.financial_phone
  is '联系方式';
comment on column t_agreement_back.send_address
  is '邮寄地址';
comment on column t_agreement_back.send_date
  is '邮寄时间';
comment on column t_agreement_back.isback
  is '是否收回';
comment on column t_agreement_back.back_date
  is '收回时间';
comment on column t_agreement_back.deposit_address
  is '存放地址';
comment on column t_agreement_back.des
  is '备注';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_agreement_back
  add constraint pk_t_agreement_back primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_AGREEMENT_BACK
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
   't_agreement_back',
   'SEQ_T_AGREEMENT_BACK',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
