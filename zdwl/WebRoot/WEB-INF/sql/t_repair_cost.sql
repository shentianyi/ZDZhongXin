-- Create table
create table t_repair_cost
(
  id			number(19) not null,
  promoter		number(19),
  repair_project	varchar2(200),
  problem		varchar2(300),
  money			varchar2(200),
  credatetime		date,
  nextApproval		number(19),
  approvalState		number(19));
-- Add comments to the columns 
comment on table t_repair_cost is
'设备维修申请单';
comment on column t_repair_cost.promoter
  is '发起人id';
comment on column t_repair_cost.repair_project
  is '维修项目';
comment on column t_repair_cost.problem
  is '具体问题';
comment on column t_repair_cost.money
  is '维修费用';
comment on column t_repair_cost.credatetime
  is '创建时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_repair_cost
  add constraint pk_t_repair_cost primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_REPAIR_COST
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
   't_repair_cost',
   'SEQ_T_REPAIR_COST',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
