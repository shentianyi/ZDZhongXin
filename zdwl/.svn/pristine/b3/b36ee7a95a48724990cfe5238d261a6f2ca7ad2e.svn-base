-- Create table
create table T_CHECKSTOCK_MANAGE
(
  id                   NUMBER not null,
  dealerid             NUMBER not null,
  all_wh_count         NUMBER,
  wh_count             NUMBER,
  two_wh_count         NUMBER,
  move_count           NUMBER,
  actual_all_wh_count  NUMBER,
  actual_wh_count      NUMBER,
  actual_two_wh_count  NUMBER,
  actual_move_count    NUMBER,
  actual_out_count     NUMBER,
  result               NUMBER,
  check_date           DATE,
  normal_difference    VARCHAR2(500),
  un_normal_difference VARCHAR2(500),
  createdate           DATE,
  createuser           NUMBER,
  updatedate           DATE,
  updateuser           NUMBER,
  submitflag           NUMBER,
  checkstocktype       NUMBER
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
comment on column T_CHECKSTOCK_MANAGE.id
  is '主键ID';
comment on column T_CHECKSTOCK_MANAGE.dealerid
  is '经销商ID';
comment on column T_CHECKSTOCK_MANAGE.all_wh_count
  is '在库车辆';
comment on column T_CHECKSTOCK_MANAGE.wh_count
  is '本库车辆';
comment on column T_CHECKSTOCK_MANAGE.two_wh_count
  is '二库车辆';
comment on column T_CHECKSTOCK_MANAGE.move_count
  is '移动车辆';
comment on column T_CHECKSTOCK_MANAGE.actual_all_wh_count
  is '实盘在库';
comment on column T_CHECKSTOCK_MANAGE.actual_wh_count
  is '实盘本库';
comment on column T_CHECKSTOCK_MANAGE.actual_two_wh_count
  is '实盘二库';
comment on column T_CHECKSTOCK_MANAGE.actual_move_count
  is '实盘移动';
comment on column T_CHECKSTOCK_MANAGE.actual_out_count
  is '实盘出库';
comment on column T_CHECKSTOCK_MANAGE.result
  is '盘点结果与实际库存差异 1:一致  2：差异
';
comment on column T_CHECKSTOCK_MANAGE.check_date
  is '盘点时间';
comment on column T_CHECKSTOCK_MANAGE.normal_difference
  is '常规差异';
comment on column T_CHECKSTOCK_MANAGE.un_normal_difference
  is '非常规差异';
comment on column T_CHECKSTOCK_MANAGE.createdate
  is '创建时间';
comment on column T_CHECKSTOCK_MANAGE.createuser
  is '创建人';
comment on column T_CHECKSTOCK_MANAGE.updatedate
  is '修改时间';
comment on column T_CHECKSTOCK_MANAGE.updateuser
  is '修改人';
comment on column T_CHECKSTOCK_MANAGE.submitflag
  is '提交标识 1:未提交 2:已提交';
comment on column T_CHECKSTOCK_MANAGE.checkstocktype
  is '查库方式1：手工查库，2：设备查库';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CHECKSTOCK_MANAGE
  add constraint CHECKSTOCK_MANAGE_PK_ID primary key (ID)
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

  create sequence SEQ_T_CHECKSTOCK_MANAGE
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
  ('201704130001',
   'zddb',
   'T_CHECKSTOCK_MANAGE',
   'SEQ_T_CHECKSTOCK_MANAGE',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');

  
  
  -- Create table
create table T_CHECKSTOCK_CAR
(
  id            NUMBER not null,
  checkstock_id NUMBER not null,
  vin           VARCHAR2(30) not null,
  model         VARCHAR2(50),
  color         VARCHAR2(30),
  currstate     NUMBER,
  actualstate   NUMBER
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
comment on column T_CHECKSTOCK_CAR.id
  is '主键ID';
comment on column T_CHECKSTOCK_CAR.checkstock_id
  is '日查库ID';
comment on column T_CHECKSTOCK_CAR.vin
  is '车架号';
comment on column T_CHECKSTOCK_CAR.model
  is '型号';
comment on column T_CHECKSTOCK_CAR.color
  is '颜色';
comment on column T_CHECKSTOCK_CAR.currstate
  is '车辆当前状态  1:本库  2：二库 3：移动
';
comment on column T_CHECKSTOCK_CAR.actualstate
  is '车辆实际状态  1:本库  2：二库 3：移动 4：出库
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CHECKSTOCK_CAR
  add constraint CHECKSTOCK_CAR_PK_ID primary key (ID)
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

  
create sequence SEQ_T_CHECKSTOCK_CAR
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
  ('201704130001',
   'zddb',
   'T_CHECKSTOCK_CAR',
   'SEQ_T_CHECKSTOCK_CAR',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
