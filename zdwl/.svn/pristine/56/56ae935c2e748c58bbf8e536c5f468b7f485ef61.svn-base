-- Create table
create table T_POSTAGE_REQUISITION
(
  id                NUMBER not null,
  initiator         NVARCHAR2(50) not null,
  currentdate       DATE,
  post              NVARCHAR2(50),
  mailcontent       NVARCHAR2(200),
  sender            NVARCHAR2(50),
  senderstuffno     NVARCHAR2(50),
  senderdistribid   NUMBER,
  expresscompany    NVARCHAR2(100),
  estimatedamount   NUMBER,
  senderfinancial   NVARCHAR2(100),
  sendcity          NVARCHAR2(200),
  receivecity       NVARCHAR2(200),
  receiverdistribid NUMBER,
  receiver          NVARCHAR2(50),
  receiverstuffno   NVARCHAR2(50),
  receiverfinancial NVARCHAR2(100),
  remark            NVARCHAR2(500),
  nextapproval      NUMBER(3),
  isapprovalpass    NUMBER(2)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_POSTAGE_REQUISITION.id
  is '主键ID';
comment on column T_POSTAGE_REQUISITION.initiator
  is '发起人';
comment on column T_POSTAGE_REQUISITION.currentdate
  is '日期';
comment on column T_POSTAGE_REQUISITION.post
  is '岗位';
comment on column T_POSTAGE_REQUISITION.mailcontent
  is '邮寄项目';
comment on column T_POSTAGE_REQUISITION.sender
  is '寄件人';
comment on column T_POSTAGE_REQUISITION.senderstuffno
  is '寄件人工号';
comment on column T_POSTAGE_REQUISITION.senderdistribid
  is '寄件人经销商ID';
comment on column T_POSTAGE_REQUISITION.expresscompany
  is '快递公司';
comment on column T_POSTAGE_REQUISITION.estimatedamount
  is '预计金额';
comment on column T_POSTAGE_REQUISITION.senderfinancial
  is '发件人金融机构';
comment on column T_POSTAGE_REQUISITION.sendcity
  is '寄出城市';
comment on column T_POSTAGE_REQUISITION.receivecity
  is '接收城市';
comment on column T_POSTAGE_REQUISITION.receiverdistribid
  is '收件人经销商ID';
comment on column T_POSTAGE_REQUISITION.receiver
  is '收件人';
comment on column T_POSTAGE_REQUISITION.receiverstuffno
  is '收件人工号';
comment on column T_POSTAGE_REQUISITION.receiverfinancial
  is '收件人金融机构';
comment on column T_POSTAGE_REQUISITION.remark
  is '事件描述';
comment on column T_POSTAGE_REQUISITION.nextapproval
  is '下一个审批角色';
comment on column T_POSTAGE_REQUISITION.isapprovalpass
  is '是否审批通过 1：通过 2：不通过';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_POSTAGE_REQUISITION
  add constraint PK_T_POSTAGE_REQUISITION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

create sequence SEQ_T_POSTAGE_REQUISITION
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
   'T_POSTAGE_REQUISITION',
   'SEQ_T_POSTAGE_REQUISITION',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');