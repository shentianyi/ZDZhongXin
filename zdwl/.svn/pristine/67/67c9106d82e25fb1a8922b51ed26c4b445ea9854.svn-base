-- Create table
create table t_inspection_plan
(
  id                         NUMBER(19) not null,
  
  planCode             		 VARCHAR2(128),
  outControlUserId           NUMBER(19),
  escortUserInfo             VARCHAR2(128),

  dealerId             		 NUMBER(19),
  stock                 	 NUMBER(19),
  predictBeginDate           DATE,
  predictCost                NUMBER(10,2),
  recentCheckTime            VARCHAR2(128),
  remark                	 VARCHAR2(512),
  predictCheckdays  		 NUMBER(10,1),
  reportStatus				 NUMBER(4)
)
-- Add comments to the table 
comment on table t_inspection_plan
  is '巡检计划经销商表';

-- Add comments to the columns 
comment on column t_inspection_plan.id
  is '主键ID';
 
comment on column t_inspection_plan.planCode
  is '巡检编号'
comment on column t_inspection_plan.outControlUserId
  is '外控专员ID';
comment on column t_inspection_plan.escortUserInfo
  is '陪同人员信息';
comment on column t_inspection_plan.dealerId
  is '经销商主键ID';
comment on column t_inspection_plan.stock
  is '库存数量' ;
comment on column t_inspection_plan.predictBeginDate
  is '预计开始时间' ;
comment on column t_inspection_plan.predictCost
  is '预计产生费用' ;
comment on column t_inspection_plan.recentCheckTime
  is '最近检查时间' ;
comment on column t_inspection_plan.remark
  is '备注' ;
comment on column t_inspection_plan.predictCheckdays
  is '预计检查天数(最小单位0.5天)' ;
comment on column t_inspection_plan.reportStatus
  is '巡检报告完成情况 :0未编辑 1：已编辑 2：已提交;' ;
  
  
-- Create sequence 
create sequence SEQ_T_INSPECTIONPLAN
minvalue 1
maxvalue 999999999999999999999999999
start with 123
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
   't_inspection_plan',
   'SEQ_T_INSPECTIONPLAN',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');