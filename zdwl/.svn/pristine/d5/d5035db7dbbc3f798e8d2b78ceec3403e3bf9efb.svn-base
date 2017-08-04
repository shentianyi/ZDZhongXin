-- Create table
create table T_PAPER_TEXT
(
  id                     NUMBER not null,
  supervisorid           NUMBER not null,
  paperbillamount        NUMBER,
  paperbillstarttime     DATE,
  paperbillendtime       DATE,
  towapplyamount         NUMBER,
  towapplystarttime      DATE,
  towapplyendtime        DATE,
  changeapplyamount      NUMBER,
  changeapplystarttime   DATE,
  changeapplyendtime     DATE,
  confirmationamount     NUMBER,
  confirmationstarttime  DATE,
  confirmationendtime    DATE,
  applyfreebookamount    NUMBER,
  applyfreebookstarttime DATE,
  applyfreebookendtime   DATE,
  weekbillamount         NUMBER,
  weekbillstarttime      DATE,
  weekbillendtime        DATE,
  monthbillamount        NUMBER,
  monthbillstarttime     DATE,
  monthbillendtime       DATE,
  authorization          NUMBER,
  informletter           NUMBER,
  keyamount              NUMBER,
  otheramount            NUMBER,
  otherstarttime         DATE,
  otherendtime           DATE,
  remark                 NVARCHAR2(500)
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
comment on column T_PAPER_TEXT.id
  is '主键ID';
comment on column T_PAPER_TEXT.supervisorid
  is '监管员ID';
comment on column T_PAPER_TEXT.paperbillamount
  is '手工台账数量';
comment on column T_PAPER_TEXT.paperbillstarttime
  is '手工台账开始时间';
comment on column T_PAPER_TEXT.paperbillendtime
  is '手工台账结束时间';
comment on column T_PAPER_TEXT.towapplyamount
  is '二网申请数量';
comment on column T_PAPER_TEXT.towapplystarttime
  is '二网申请开始时间';
comment on column T_PAPER_TEXT.towapplyendtime
  is '二网申请结束时间';
comment on column T_PAPER_TEXT.changeapplyamount
  is '换证申请数量';
comment on column T_PAPER_TEXT.changeapplystarttime
  is '换证申请开始时间';
comment on column T_PAPER_TEXT.changeapplyendtime
  is '换证申请结束时间';
comment on column T_PAPER_TEXT.confirmationamount
  is '监管确认书数量';
comment on column T_PAPER_TEXT.confirmationstarttime
  is '监管确认书开始时间';
comment on column T_PAPER_TEXT.confirmationendtime
  is '监管确认书结束时间';
comment on column T_PAPER_TEXT.applyfreebookamount
  is '申领释放书数量';
comment on column T_PAPER_TEXT.applyfreebookstarttime
  is '申领释放书开始时间';
comment on column T_PAPER_TEXT.applyfreebookendtime
  is '申领释放书结束时间';
comment on column T_PAPER_TEXT.weekbillamount
  is '周库存核对清单数量';
comment on column T_PAPER_TEXT.weekbillstarttime
  is '周库存核对清单开始时间';
comment on column T_PAPER_TEXT.weekbillendtime
  is '周库存核对清单结束时间';
comment on column T_PAPER_TEXT.monthbillamount
  is '月库存核对清单数量';
comment on column T_PAPER_TEXT.monthbillstarttime
  is '月库存核对清单开始时间';
comment on column T_PAPER_TEXT.monthbillendtime
  is '月库存核对清单结束时间';
comment on column T_PAPER_TEXT.authorization
  is '授权书 0：有，1：没有';
comment on column T_PAPER_TEXT.informletter
  is '告知函   0：有，1：没有';
comment on column T_PAPER_TEXT.keyamount
  is '合格证、车钥匙数量是否相符  0：相符，1：不相符';
comment on column T_PAPER_TEXT.otheramount
  is '其他数量';
comment on column T_PAPER_TEXT.otherstarttime
  is '其他开始时间';
comment on column T_PAPER_TEXT.otherendtime
  is '其他结束时间';
comment on column T_PAPER_TEXT.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_PAPER_TEXT
  add constraint PK_T_PAPER_TEXT_ID primary key (ID)
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
alter table T_PAPER_TEXT
  add constraint FK_T_PAPER_TEXT_SUPERVISORID foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  -- Create sequence 
create sequence SEQ_T_PAPER_TEXT
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
  (201608270002,
   'zddb',
   't_paper_text',
   'SEQ_T_PAPER_TEXT',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');