-- Create table
create table T_SUPERVISOR_RECOMMEND
(
  id                      NUMBER not null,
  isinsiderecommend       NVARCHAR2(8),
  otherchannel            NUMBER,
  recommendername         NVARCHAR2(20),
  recommenderposition     NVARCHAR2(50),
  recommenderphone        NVARCHAR2(50),
  recommenderrelationship NVARCHAR2(20),
  recommenderdepartment   NVARCHAR2(100),
  supervisorid            NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_SUPERVISOR_RECOMMEND.id
  is '主键ID';
comment on column T_SUPERVISOR_RECOMMEND.isinsiderecommend
  is '是否内部人员推荐';
comment on column T_SUPERVISOR_RECOMMEND.otherchannel
  is '其他渠道：1：校园招聘，2：监管员推荐，3：社会招聘';
comment on column T_SUPERVISOR_RECOMMEND.recommendername
  is '推荐人姓名';
comment on column T_SUPERVISOR_RECOMMEND.recommenderposition
  is '推荐人职位';
comment on column T_SUPERVISOR_RECOMMEND.recommenderphone
  is '推荐人联系方式';
comment on column T_SUPERVISOR_RECOMMEND.recommenderrelationship
  is '与推荐人关系';
comment on column T_SUPERVISOR_RECOMMEND.recommenderdepartment
  is '推荐人所在部门或4S店名称';
comment on column T_SUPERVISOR_RECOMMEND.supervisorid
  is '监管员ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SUPERVISOR_RECOMMEND
  add constraint PK_T_SUPERVISOR_RECOMMEND primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_SUPERVISOR_RECOMMEND
  add constraint FK_T_SUPERVISOR_RECOMMEND foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  
create sequence SEQ_T_SUPERVISOR_RECOMMEND
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
   'T_SUPERVISOR_RECOMMEND',
   'SEQ_T_SUPERVISOR_RECOMMEND',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');