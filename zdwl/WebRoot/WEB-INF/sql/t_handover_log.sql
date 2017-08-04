-- Create table
create table T_HANDOVER_LOG
(
  id                       NUMBER not null,
  dealerid                 NUMBER not null,
  merchantdemand           NVARCHAR2(100),
  accommodationProvider    NVARCHAR2(100),
  bindmerchant             NVARCHAR2(100),
  bindbank                 NVARCHAR2(100),
  deliverer                NUMBER,
  delivererapplicationdate DATE,
  expecteddimissiondate    DATE,
  handovernature           NUMBER,
  resignreason             NUMBER,
  dimissiondate            DATE,
  receiver                 NUMBER,
  receivenature            NUMBER,
  afterhandovernature      NUMBER,
  missiondate              DATE,
  handoverdate             DATE,
  finishtime               DATE,
  deployid                 NUMBER,
  fallowstatus             NVARCHAR2(500),
  situationexplain         NVARCHAR2(500)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table 
comment on table T_HANDOVER_LOG
  is '交接记录表';
-- Add comments to the columns 
comment on column T_HANDOVER_LOG.id
  is '主键ID';
comment on column T_HANDOVER_LOG.dealerid
  is '经销商ID';
comment on column T_HANDOVER_LOG.merchantdemand
  is '店方要求';
comment on column T_HANDOVER_LOG.accommodationProvider
  is '住宿提供方';
comment on column T_HANDOVER_LOG.bindmerchant
  is '绑定店';
comment on column T_HANDOVER_LOG.bindbank
  is '绑定行';
comment on column T_HANDOVER_LOG.deliverer
  is '交付人';
comment on column T_HANDOVER_LOG.delivererapplicationdate
  is '申请时间';
comment on column T_HANDOVER_LOG.expecteddimissiondate
  is '预计离岗时间';
comment on column T_HANDOVER_LOG.handovernature
  is '交接性质：1：辞职，2：辞退，3：正常轮岗，4：投诉轮岗';
comment on column T_HANDOVER_LOG.resignreason
  is '辞职原因：1：个人原因，2：换工作，3：工作地点原因';
comment on column T_HANDOVER_LOG.dimissiondate
  is '离岗时间';
comment on column T_HANDOVER_LOG.receiver
  is '接收人';
comment on column T_HANDOVER_LOG.receivenature
  is '接收性质：1：轮岗，2：新入职，3：二次上岗';
comment on column T_HANDOVER_LOG.afterhandovernature
  is '交接后属性：1：属地，2：外派';
comment on column T_HANDOVER_LOG.missiondate
  is '上岗时间';
comment on column T_HANDOVER_LOG.handoverdate
  is '交接时间';
comment on column T_HANDOVER_LOG.finishtime
  is '完成时间';
comment on column T_HANDOVER_LOG.deployid
  is '调配专员';
comment on column T_HANDOVER_LOG.fallowstatus
  is '跟进情况';
comment on column T_HANDOVER_LOG.situationexplain
  is '情况说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_HANDOVER_LOG
  add constraint PK_T_HANDOVER_LOG primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

  
create sequence SEQ_T_HANDOVER_LOG
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
  ('201608110001',
   'zddb',
   'T_HANDOVER_LOG',
   'SEQ_T_HANDOVER_LOG',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');