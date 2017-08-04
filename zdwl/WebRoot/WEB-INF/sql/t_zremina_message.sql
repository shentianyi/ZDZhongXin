-- Create table
create table T_SUPERVISOR_MESSAGE
(
  id           NUMBER not null,
  name         VARCHAR2(100),
  department   VARCHAR2(100),
  messagetype  VARCHAR2(100),
  greetings    VARCHAR2(500),
  messageid    NUMBER,
  supervisorid NUMBER,
  isread       NUMBER
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_MESSAGE.name
  is '监管员名字';
comment on column T_SUPERVISOR_MESSAGE.department
  is '监管员部门';
comment on column T_SUPERVISOR_MESSAGE.messagetype
  is '消息类别';
comment on column T_SUPERVISOR_MESSAGE.greetings
  is '问候语';
comment on column T_SUPERVISOR_MESSAGE.messageid
  is '信息Id';
comment on column T_SUPERVISOR_MESSAGE.supervisorid
  is '监管员Id';
comment on column T_SUPERVISOR_MESSAGE.isread
  is '1已读2未读';







-- Create table
create table T_SUPERVISOR_REPAIRCOST
(
  id               NUMBER not null,
  name             VARCHAR2(100),
  merchantname     VARCHAR2(3000),
  jobnumber        VARCHAR2(50),
  maintenanceitems VARCHAR2(200),
  messagetype      VARCHAR2(100),
  cost             NUMBER,
  supervisorid     NUMBER,
  isread           NUMBER,
  bankname         VARCHAR2(3000),
  userid           NUMBER,
  messageid        NUMBER
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_REPAIRCOST.name
  is '监管员名称';
comment on column T_SUPERVISOR_REPAIRCOST.merchantname
  is '经销商名称';
comment on column T_SUPERVISOR_REPAIRCOST.jobnumber
  is '工号';
comment on column T_SUPERVISOR_REPAIRCOST.maintenanceitems
  is '维修项目';
comment on column T_SUPERVISOR_REPAIRCOST.messagetype
  is '消息类别';
comment on column T_SUPERVISOR_REPAIRCOST.cost
  is '费用';
comment on column T_SUPERVISOR_REPAIRCOST.supervisorid
  is '监管员Id';
comment on column T_SUPERVISOR_REPAIRCOST.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_REPAIRCOST.bankname
  is '金融机构';
comment on column T_SUPERVISOR_REPAIRCOST.userid
  is '收消息人id';
comment on column T_SUPERVISOR_REPAIRCOST.messageid
  is '消息表Id';




-- Create table
create table T_SUPERVISOR_COSTMAIL
(
  id           NUMBER not null,
  name         VARCHAR2(50),
  merchantname VARCHAR2(2500),
  jobnumber    VARCHAR2(50),
  costmail     VARCHAR2(100),
  supervisorid NUMBER,
  isread       NUMBER,
  bankname     VARCHAR2(3000),
  userid       NUMBER,
  messageid    NUMBER,
  createdate   DATE,
  cost         VARCHAR2(100),
  messagetype  VARCHAR2(50)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 8
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_COSTMAIL.name
  is '监管员的名字';
comment on column T_SUPERVISOR_COSTMAIL.merchantname
  is '经销商名称';
comment on column T_SUPERVISOR_COSTMAIL.jobnumber
  is '工号';
comment on column T_SUPERVISOR_COSTMAIL.costmail
  is '邮寄项目';
comment on column T_SUPERVISOR_COSTMAIL.supervisorid
  is '监管员Id';
comment on column T_SUPERVISOR_COSTMAIL.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_COSTMAIL.bankname
  is '金融机构';
comment on column T_SUPERVISOR_COSTMAIL.userid
  is '收件人Id';
comment on column T_SUPERVISOR_COSTMAIL.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_COSTMAIL.createdate
  is '插入时间';
comment on column T_SUPERVISOR_COSTMAIL.cost
  is '邮寄费用';
comment on column T_SUPERVISOR_COSTMAIL.messagetype
  is '消息类型';






-- Create table
create table T_SUPERVISOR_OVERTIME
(
  id           NUMBER not null,
  name         VARCHAR2(50),
  merchantname VARCHAR2(2500),
  jobnumber    VARCHAR2(50),
  supervisorid NUMBER,
  isread       NUMBER,
  bankname     VARCHAR2(3000),
  userid       NUMBER,
  messageid    NUMBER,
  begindate    DATE,
  enddate      DATE,
  createdate   DATE,
  messagetype  VARCHAR2(100)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_OVERTIME.name
  is '监管员的名字';
comment on column T_SUPERVISOR_OVERTIME.merchantname
  is '经销商名称';
comment on column T_SUPERVISOR_OVERTIME.jobnumber
  is '工号';
comment on column T_SUPERVISOR_OVERTIME.supervisorid
  is '监管员Id';
comment on column T_SUPERVISOR_OVERTIME.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_OVERTIME.bankname
  is '金融机构';
comment on column T_SUPERVISOR_OVERTIME.userid
  is '收件人Id';
comment on column T_SUPERVISOR_OVERTIME.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_OVERTIME.begindate
  is '开始时间';
comment on column T_SUPERVISOR_OVERTIME.enddate
  is '结束时间';
comment on column T_SUPERVISOR_OVERTIME.createdate
  is '插入时间';
comment on column T_SUPERVISOR_OVERTIME.messagetype
  is '消息类型';






-- Create table
create table T_SUPERVISOR_OUTSTOCK
(
  id           NUMBER not null,
  importid     NUMBER,
  brandname    VARCHAR2(50),
  vin          VARCHAR2(50),
  draft_num    VARCHAR2(50),
  outtime      DATE,
  merchantname VARCHAR2(3000),
  bankname     VARCHAR2(3000),
  createdate   DATE,
  messagetype  VARCHAR2(50),
  isread       NUMBER,
  userid       NUMBER,
  messageid    NUMBER
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_OUTSTOCK.importid
  is '监管物Id';
comment on column T_SUPERVISOR_OUTSTOCK.brandname
  is '品牌';
comment on column T_SUPERVISOR_OUTSTOCK.vin
  is '车架号';
comment on column T_SUPERVISOR_OUTSTOCK.draft_num
  is '票号';
comment on column T_SUPERVISOR_OUTSTOCK.outtime
  is '释放时间';
comment on column T_SUPERVISOR_OUTSTOCK.merchantname
  is '经销商名称';
comment on column T_SUPERVISOR_OUTSTOCK.bankname
  is '金融机构';
comment on column T_SUPERVISOR_OUTSTOCK.createdate
  is '插入时间';
comment on column T_SUPERVISOR_OUTSTOCK.messagetype
  is '消息类型';
comment on column T_SUPERVISOR_OUTSTOCK.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_OUTSTOCK.userid
  is '通知人id';
comment on column T_SUPERVISOR_OUTSTOCK.messageid
  is '消息表Id';






-- Create table
create table T_SUPERVISOR_MOVESTOCK
(
  id           NUMBER not null,
  importid     NUMBER,
  brandname    VARCHAR2(300),
  vin          VARCHAR2(100),
  draft_num    VARCHAR2(100),
  movetime     DATE,
  merchantname VARCHAR2(3000),
  bankname     VARCHAR2(3000),
  createdate   DATE,
  messagetype  VARCHAR2(50),
  isread       NUMBER,
  userid       NUMBER,
  messageid    NUMBER,
  moveaddress  VARCHAR2(100)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_MOVESTOCK.importid
  is '监管物Id';
comment on column T_SUPERVISOR_MOVESTOCK.brandname
  is '品牌';
comment on column T_SUPERVISOR_MOVESTOCK.vin
  is '车架号';
comment on column T_SUPERVISOR_MOVESTOCK.draft_num
  is '票号';
comment on column T_SUPERVISOR_MOVESTOCK.movetime
  is '移动时间';
comment on column T_SUPERVISOR_MOVESTOCK.merchantname
  is '经销商名称';
comment on column T_SUPERVISOR_MOVESTOCK.bankname
  is '金融机构';
comment on column T_SUPERVISOR_MOVESTOCK.createdate
  is '插入时间';
comment on column T_SUPERVISOR_MOVESTOCK.messagetype
  is '消息类型';
comment on column T_SUPERVISOR_MOVESTOCK.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_MOVESTOCK.userid
  is '通知人id';
comment on column T_SUPERVISOR_MOVESTOCK.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_MOVESTOCK.moveaddress
  is '移动地点';








-- Create table
create table T_SUPERVISOR_INSPECTION
(
  id          NUMBER,
  messagetype VARCHAR2(200),
  greetings   VARCHAR2(100),
  messageid   NUMBER,
  userid      NUMBER,
  isread      NUMBER,
  createdate  DATE
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_INSPECTION.messagetype
  is '消息类别';
comment on column T_SUPERVISOR_INSPECTION.greetings
  is '提示语';
comment on column T_SUPERVISOR_INSPECTION.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_INSPECTION.userid
  is '通知人Id';
comment on column T_SUPERVISOR_INSPECTION.isread
  is '1已读2未读';
comment on column T_SUPERVISOR_INSPECTION.createdate
  is '插入时间';







-- Create table
create table T_SUPERVISOR_UNINSPECTION_PLAN
(
  id           NUMBER,
  num          VARCHAR2(100),
  director     VARCHAR2(1000),
  plandate     DATE,
  merchantname VARCHAR2(300),
  bankname     VARCHAR2(3000),
  brandname    VARCHAR2(50),
  isread       NUMBER,
  userid       NUMBER,
  messageid    NUMBER,
  messagetype  NUMBER,
  createdate   DATE
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.num
  is '计划编号';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.director
  is '风控专员';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.plandate
  is '预计开始日期';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.merchantname
  is '经销店名称';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.bankname
  is '金融结构';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.brandname
  is '品牌';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.userid
  is '收件人Id';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.messagetype
  is '消息类型';
comment on column T_SUPERVISOR_UNINSPECTION_PLAN.createdate
  is '插入时间';










-- Create table
create table T_SUPERVISOR_UNINSPECT
(
  id           NUMBER,
  director     VARCHAR2(100),
  lastmodified DATE,
  address      VARCHAR2(500),
  isread       NUMBER,
  userid       NUMBER,
  messageid    NUMBER,
  messagetype  NUMBER,
  createdate   DATE,
  content      VARCHAR2(100)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_UNINSPECT.director
  is '风控专员';
comment on column T_SUPERVISOR_UNINSPECT.lastmodified
  is '上次巡店报告上传时间';
comment on column T_SUPERVISOR_UNINSPECT.address
  is '上次巡店报告上传地址';
comment on column T_SUPERVISOR_UNINSPECT.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_UNINSPECT.userid
  is '收件人Id';
comment on column T_SUPERVISOR_UNINSPECT.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_UNINSPECT.messagetype
  is '消息类型';
comment on column T_SUPERVISOR_UNINSPECT.createdate
  is '插入时间';
comment on column T_SUPERVISOR_UNINSPECT.content
  is '页面显示内容';









-- Create table
create table T_SUPERVISOR_INSPECT
(
  id           NUMBER,
  director     VARCHAR2(100),
  merchantname VARCHAR2(2000),
  bankname     VARCHAR2(1000),
  brandname    VARCHAR2(100),
  logistics    VARCHAR2(100),
  isread       NUMBER,
  userid       NUMBER,
  messageid    NUMBER,
  messagetype  NUMBER,
  createdate   DATE,
  modify_time  DATE
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64
    next 1
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_SUPERVISOR_INSPECT.director
  is '风控专员';
comment on column T_SUPERVISOR_INSPECT.merchantname
  is '经销店名称';
comment on column T_SUPERVISOR_INSPECT.bankname
  is '金融结构';
comment on column T_SUPERVISOR_INSPECT.brandname
  is '品牌';
comment on column T_SUPERVISOR_INSPECT.logistics
  is '物流';
comment on column T_SUPERVISOR_INSPECT.isread
  is '1未读2已读';
comment on column T_SUPERVISOR_INSPECT.userid
  is '收件人Id';
comment on column T_SUPERVISOR_INSPECT.messageid
  is '消息表Id';
comment on column T_SUPERVISOR_INSPECT.messagetype
  is '消息类型';
comment on column T_SUPERVISOR_INSPECT.createdate
  is '插入时间';
comment on column T_SUPERVISOR_INSPECT.modify_time
  is '上传日期';





