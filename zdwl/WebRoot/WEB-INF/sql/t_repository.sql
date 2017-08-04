drop table T_REPOSITORY;

-- Create table
create table T_REPOSITORY
(
  id                  NUMBER not null,
  status              NUMBER not null,
  reason              NUMBER,
  supervisorid        NUMBER,
  interviewee         NVARCHAR2(20),
  interviewscore      NUMBER(10,2),
  interviewcomment    NVARCHAR2(500),
  attribute           NVARCHAR2(20),
  repositorytrainid   NUMBER,
  nativeplaceprovince NVARCHAR2(100),
  nativeplacecity     NVARCHAR2(100),
  nativeplacecounty   NVARCHAR2(100),
  liveaddressprovince NVARCHAR2(100),
  liveaddresscity     NVARCHAR2(100),
  liveaddresscounty   NVARCHAR2(100)
)
;
-- Add comments to the columns 
comment on column T_REPOSITORY.id
  is '主键ID';
comment on column T_REPOSITORY.status
  is '状态：1：已上岗，2：有效，3：无效';
comment on column T_REPOSITORY.reason
  is '原因：1：撤店，2：放弃（已有新工作），3：放弃（工资问题），4：辞退，5：辞职';
comment on column T_REPOSITORY.supervisorid
  is '监管员ID';
comment on column T_REPOSITORY.interviewee
  is '面试人';
comment on column T_REPOSITORY.interviewscore
  is '面试评分';
comment on column T_REPOSITORY.interviewcomment
  is '面试点评';
comment on column T_REPOSITORY.attribute
  is '属性';
comment on column T_REPOSITORY.repositorytrainid
  is '培训信息ID';
comment on column T_REPOSITORY.nativeplaceprovince
  is '籍贯（省）';
comment on column T_REPOSITORY.nativeplacecity
  is '籍贯（市）';
comment on column T_REPOSITORY.nativeplacecounty
  is '籍贯（区/县）';
comment on column T_REPOSITORY.liveaddressprovince
  is '现住址（省）';
comment on column T_REPOSITORY.liveaddresscity
  is '现住址（市）';
comment on column T_REPOSITORY.liveaddresscounty
  is '现住址（区/县）';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPOSITORY
  add constraint PK_T_REPOSITORY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_REPOSITORY
  add constraint FK_T_REPOSITORY foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);
alter table T_REPOSITORY
  add constraint FK_T_REPOSITORYTRAINID foreign key (REPOSITORYTRAINID)
  references T_REPOSITORY_TRAIN (ID);

  
-- Create sequence 
create sequence SEQ_T_REPOSITORY
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
  (201607141121,
   'zddb',
   'T_REPOSITORY',
   'SEQ_T_REPOSITORY',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
