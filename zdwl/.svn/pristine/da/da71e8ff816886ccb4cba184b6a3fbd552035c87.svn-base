-- Create table
create table t_video_planinfo
(
  id                         NUMBER(19) not null,
  
  planCode             		 VARCHAR2(128),
  videoUserId                NUMBER(19),
  stores                	 NUMBER(19),
  planExecuteTime            VARCHAR2(128),
  checkHoursAmount           NUMBER(10,2),
  stockAmount           	 NUMBER(19),
  status					 NUMBER(19),
  createDate				 DATE,
  
  unCheckPassCause           VARCHAR2(1024)
)
-- Add comments to the table 
comment on table t_video_planinfo
  is '视频检查计划列表';

-- Add comments to the columns 
comment on column t_video_planinfo.id
  is '主键ID';
  
comment on column t_video_planinfo.planCode
  is '计划编号';
comment on column t_video_planinfo.videoUserId
  is '视频专员Id';
comment on column t_video_planinfo.stores
  is '涉及店数';
comment on column t_video_planinfo.planExecuteTime
  is '计划执行时间' ;
comment on column t_video_planinfo.checkHoursAmount
  is '检查小时合计';
comment on column t_video_planinfo.stockAmount
  is '库存合计';
comment on column t_video_planinfo.status
  is '状态';
comment on column t_video_planinfo.createDate
  is '创建时间';
comment on column t_video_planinfo.unCheckPassCause
  is '审批不通过原因';
  
  
  
-- Create sequence 项目用SEQ_t_video_planinfo序列
create sequence SEQ_T_VIDEOPLANINFO
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
   't_video_planinfo',
   'SEQ_T_VIDEOPLANINFO',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');