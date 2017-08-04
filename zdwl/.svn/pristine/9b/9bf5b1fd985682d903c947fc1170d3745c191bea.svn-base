-- Create table
create table T_ELECTRONIC_TEXT
(
  id                        NUMBER not null,
  supervisorid              NUMBER not null,
  electronbill              NVARCHAR2(500),
  receivecardetailamount    NUMBER,
  receivecardetailstarttime DATE,
  receivecardetailendtime   DATE,
  weekbillamount            NUMBER,
  weekbillstarttime         DATE,
  weekbillendtime           DATE,
  monthbillamount           NUMBER,
  monthbillstarttime        DATE,
  monthbillendtime          DATE,
  applyfreebookamount       NUMBER,
  applyfreebookstarttime    DATE,
  applyfreebookendtime      DATE,
  confirmationamount        NUMBER,
  confirmationstarttime     DATE,
  confirmationendtime       DATE,
  towapplyamount            NUMBER,
  towapplystarttime         DATE,
  towapplyendtime           DATE,
  otheramount               NUMBER,
  otherstarttime            DATE,
  otherendtime              DATE,
  remark                    VARCHAR2(500)
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
comment on column T_ELECTRONIC_TEXT.id
  is '主键ID';
comment on column T_ELECTRONIC_TEXT.supervisorid
  is '监管员ID';
comment on column T_ELECTRONIC_TEXT.electronbill
  is '电子台账';
comment on column T_ELECTRONIC_TEXT.receivecardetailamount
  is '接车明细数量';
comment on column T_ELECTRONIC_TEXT.receivecardetailstarttime
  is '接车明细开始时间';
comment on column T_ELECTRONIC_TEXT.receivecardetailendtime
  is '接车明细结束时间';
comment on column T_ELECTRONIC_TEXT.weekbillamount
  is '周库存核对清单数量';
comment on column T_ELECTRONIC_TEXT.weekbillstarttime
  is '周库存核对清单开始时间';
comment on column T_ELECTRONIC_TEXT.weekbillendtime
  is '周库存核对清单结束时间';
comment on column T_ELECTRONIC_TEXT.monthbillamount
  is '月库存核对清单数量';
comment on column T_ELECTRONIC_TEXT.monthbillstarttime
  is '月库存核对清单开始时间';
comment on column T_ELECTRONIC_TEXT.monthbillendtime
  is '月库存核对清单结束时间';
comment on column T_ELECTRONIC_TEXT.applyfreebookamount
  is '申领释放书数量';
comment on column T_ELECTRONIC_TEXT.applyfreebookstarttime
  is '申领释放书开始时间';
comment on column T_ELECTRONIC_TEXT.applyfreebookendtime
  is '申领释放书结束时间';
comment on column T_ELECTRONIC_TEXT.confirmationamount
  is '监管确认书数量';
comment on column T_ELECTRONIC_TEXT.confirmationstarttime
  is '监管确认书开始时间';
comment on column T_ELECTRONIC_TEXT.confirmationendtime
  is '监管确认书结束时间';
comment on column T_ELECTRONIC_TEXT.towapplyamount
  is '二网申请数量';
comment on column T_ELECTRONIC_TEXT.towapplystarttime
  is '二网申请开始时间';
comment on column T_ELECTRONIC_TEXT.towapplyendtime
  is '二网申请结束时间';
comment on column T_ELECTRONIC_TEXT.otheramount
  is '其他数量';
comment on column T_ELECTRONIC_TEXT.otherstarttime
  is '其他开始时间';
comment on column T_ELECTRONIC_TEXT.otherendtime
  is '其他结束时间';
comment on column T_ELECTRONIC_TEXT.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_ELECTRONIC_TEXT
  add constraint PK_T_ELECTRONIC_TEXT_ID primary key (ID)
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
alter table T_ELECTRONIC_TEXT
  add constraint FK_ET_SUPERVISORID foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  -- Create sequence 
create sequence SEQ_T_ELECTRONIC_TEXT
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
  (201608270001,
   'zddb',
   't_electronic_text',
   'SEQ_T_ELECTRONIC_TEXT',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');