-- 增加提交时间字段
 alter table t_video_plan add submittime Date;

-- Create table  
create table t_video_plan
(
  id                         NUMBER(19) not null,
  
  videoUserId                NUMBER(19),
  dealerId                	 NUMBER(19),
  stock               		 NUMBER(19),
  checkMinute             	 NUMBER(19),
  predictCheckDate           DATE,
  
  submitTime				 DATE,
  practicalCheckTime         VARCHAR2(128),
  planCode             		 VARCHAR2(128),
  reportStatus				 NUMBER(4) default(0),
  recentCheckTime			 VARCHAR2(128)
)
-- Add comments to the table 
comment on table t_video_plan
  is '视频检查计划详情';

-- Add comments to the columns 
comment on column t_video_plan.id
  is '主键ID';
  
comment on column t_video_plan.videoUserId
  is '视频专员';
comment on column t_video_plan.dealerId
  is '经销商主键ID';
comment on column t_video_plan.stock
  is '库存数量' ;
comment on column t_video_plan.checkMinute
  is '预计检查分钟';
comment on column t_video_plan.predictCheckDate
  is '预计检查时间';
comment on column t_video_plan.submitTime
  is '提交时间';
comment on column t_video_plan.practicalCheckTime
  is '实际检查时间';
comment on column t_video_plan.planCode
  is '计划编号';
comment on column t_video_plan.reportStatus
  is '视频检查报告完成情况 :0未编辑 1：已编辑 2：已提交';
comment on column t_video_plan.reportStatus
  is '最近检查时间';
  
  
  
-- Create sequence 目前已改为SEQ_t_video_plan
create sequence SEQ_T_VIDEOPLAN
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
   't_video_plan',
   'SEQ_T_VIDEOPLAN',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');