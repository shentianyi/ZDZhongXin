-- Create table
create table T_OFFICE_EQUIPMENT
(
  id                      NUMBER not null,
  supervisorid            NUMBER not null,
  computerproperty        NUMBER,
  computerpropertyreason  NVARCHAR2(500),
  computeronpassword      NUMBER,
  computerpassword        NVARCHAR2(100),
  computerappearance      NUMBER,
  mouseproperty           NUMBER,
  cameraproperty          NUMBER,
  safetyboxproperty       NUMBER,
  safetyboxappearance     NUMBER,
  safetyboxpasswordstatus NUMBER,
  safetyboxpassword       NVARCHAR2(100),
  keyamount               NUMBER,
  situationexplain        NVARCHAR2(500),
  headsetproperty         NUMBER,
  havecard                NUMBER,
  qqnumber                VARCHAR2(100),
  qqpassword              VARCHAR2(100),
  borrowgoods             NUMBER,
  borrowgoodsremark       VARCHAR2(1000),
  moneystatus             NUMBER,
  moneyremark             NVARCHAR2(500),
  merchantremark          NVARCHAR2(1000),
  safetyboxpropertyreason NVARCHAR2(500)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_OFFICE_EQUIPMENT.id
  is '主键ID';
comment on column T_OFFICE_EQUIPMENT.supervisorid
  is '监管员ID';
comment on column T_OFFICE_EQUIPMENT.computerproperty
  is '电脑性能';
comment on column T_OFFICE_EQUIPMENT.computerpropertyreason
  is '电脑性能不正常原因';
comment on column T_OFFICE_EQUIPMENT.computeronpassword
  is '开机密码状态 0：告知，1：未告知，2：无';
comment on column T_OFFICE_EQUIPMENT.computerpassword
  is '密码';
comment on column T_OFFICE_EQUIPMENT.computerappearance
  is '外观  0：无损，1：有损';
comment on column T_OFFICE_EQUIPMENT.mouseproperty
  is '鼠标性能';
comment on column T_OFFICE_EQUIPMENT.cameraproperty
  is '摄像头性能';
comment on column T_OFFICE_EQUIPMENT.safetyboxproperty
  is '保险柜性能';
comment on column T_OFFICE_EQUIPMENT.safetyboxappearance
  is '保险柜外观';
comment on column T_OFFICE_EQUIPMENT.safetyboxpasswordstatus
  is '保险柜密码状态';
comment on column T_OFFICE_EQUIPMENT.safetyboxpassword
  is '保险柜密码';
comment on column T_OFFICE_EQUIPMENT.keyamount
  is '钥匙数量';
comment on column T_OFFICE_EQUIPMENT.situationexplain
  is '情况说明';
comment on column T_OFFICE_EQUIPMENT.headsetproperty
  is '耳麦状态';
comment on column T_OFFICE_EQUIPMENT.havecard
  is '有无工牌';
comment on column T_OFFICE_EQUIPMENT.qqnumber
  is 'QQ号';
comment on column T_OFFICE_EQUIPMENT.qqpassword
  is 'QQ密码';
comment on column T_OFFICE_EQUIPMENT.borrowgoods
  is '借用物品';
comment on column T_OFFICE_EQUIPMENT.borrowgoodsremark
  is '借用物品备注';
comment on column T_OFFICE_EQUIPMENT.moneystatus
  is '有无欠款';
comment on column T_OFFICE_EQUIPMENT.moneyremark
  is '欠款明细';
comment on column T_OFFICE_EQUIPMENT.merchantremark
  is '店方交接备注';
comment on column T_OFFICE_EQUIPMENT.safetyboxpropertyreason
  is '保险柜异常原因';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_OFFICE_EQUIPMENT
  add constraint PK_T_OFFICE_EQUIPMENT_ID primary key (ID)
  using index 
  tablespace ZD_SPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table T_OFFICE_EQUIPMENT
  add constraint FK_T_OE_SUPERVISORID foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  -- Create sequence 
create sequence SEQ_T_OFFICE_EQUIPMENT
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
  (201608270004,
   'zddb',
   't_office_equipment',
   'SEQ_T_OFFICE_EQUIPMENT',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');