-- Create table
create table t_msg_noprocesscar
(
  id					number(19) not null,
  vin					varchar2(256),
  moveDate				Date,
  money					varchar2(128),
  moveCount				number(19),
  skuTotalCount			number(19),
  moveCarMoney			number(10,2),
  inStockCarMoney		number(10,2),
  car_num				number(19),
  dealerId				number(19),
  type					number(19),
  createDate			Date
);
-- Add comments to the columns 
comment on table t_msg_noprocesscar is
'移动车辆超过25天未处理提醒、移动车辆超过总库存20%提醒、异常车辆超过总库存15%提醒';
comment on column t_msg_noprocesscar.id
  is '主键id';
comment on column t_msg_noprocesscar.vin
  is '车架号';
comment on column t_msg_noprocesscar.moveDate
  is '移动时间';
comment on column t_msg_noprocesscar.money
  is '车辆价值';
comment on column t_msg_noprocesscar.moveCount
  is '移动数量';
comment on column t_msg_noprocesscar.skuTotalCount
  is '库存总量';
comment on column t_msg_noprocesscar.moveCarMoney
  is '移动车辆金额';
comment on column t_msg_noprocesscar.inStockCarMoney
  is '在库车辆金额';
comment on column t_msg_noprocesscar.car_num
  is '台数';
comment on column t_msg_noprocesscar.dealerId
  is '经销商Id';
comment on column t_msg_noprocesscar.type
  is '消息类型';
comment on column t_msg_noprocesscar.createDate
  is '创建时间';

-- Create sequence 
create sequence SEQ_T_MSG_NOPROCESSCAR
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
   't_msg_noprocesscar',
   'SEQ_T_MSG_NOPROCESSCAR',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
