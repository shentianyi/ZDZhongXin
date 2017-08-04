drop table T_REPOSITORY_TRAIN;

-- Create table
create table T_REPOSITORY_TRAIN
(
  id                         NUMBER not null,
  trainspecialist            NVARCHAR2(20),
  starttime                  DATE,
  endtime                    DATE,
  dealer                     NVARCHAR2(50),
  headbank                   NVARCHAR2(100),
  branchbank                 NVARCHAR2(100),
  subbranchbank              NVARCHAR2(100),
  brand                      NVARCHAR2(10),
  address                    NVARCHAR2(100),
  trainer                    NVARCHAR2(20),
  contactnumber              NVARCHAR2(50),
  staffno                    NVARCHAR2(100),
  trainingcontent            NVARCHAR2(200),
  trainingcontentbasic       NVARCHAR2(200),
  assessmentscore            NUMBER(10,2),
  assessmentcomputerscore    NUMBER(10,2),
  assessmenttheoryscore      NUMBER(10,2),
  assessmentcommunicatescore NUMBER(10,2),
  remark                     NVARCHAR2(500)
)
;
-- Add comments to the columns 
comment on column T_REPOSITORY_TRAIN.id
  is '主键ID';
comment on column T_REPOSITORY_TRAIN.trainspecialist
  is '培训专员';
comment on column T_REPOSITORY_TRAIN.starttime
  is '培训开始时间';
comment on column T_REPOSITORY_TRAIN.endtime
  is '培训结束时间';
comment on column T_REPOSITORY_TRAIN.dealer
  is '经销商';
comment on column T_REPOSITORY_TRAIN.headbank
  is '合作银行（总）';
comment on column T_REPOSITORY_TRAIN.branchbank
  is '合作银行（分）';
comment on column T_REPOSITORY_TRAIN.subbranchbank
  is '合作银行（支）';
comment on column T_REPOSITORY_TRAIN.brand
  is '品牌';
comment on column T_REPOSITORY_TRAIN.address
  is '地址';
comment on column T_REPOSITORY_TRAIN.trainer
  is '培训人';
comment on column T_REPOSITORY_TRAIN.contactnumber
  is '联系方式';
comment on column T_REPOSITORY_TRAIN.staffno
  is '员工工号';
comment on column T_REPOSITORY_TRAIN.trainingcontent
  is '培训内容（显务）';
comment on column T_REPOSITORY_TRAIN.trainingcontentbasic
  is '培训内容（入职基础知识）';
comment on column T_REPOSITORY_TRAIN.assessmentscore
  is '考核得分';
comment on column T_REPOSITORY_TRAIN.assessmentcomputerscore
  is '考核总评（电脑水平）
';
comment on column T_REPOSITORY_TRAIN.assessmenttheoryscore
  is '考核总评（理论知识）
';
comment on column T_REPOSITORY_TRAIN.assessmentcommunicatescore
  is '考核总评（沟通及其它）
';
comment on column T_REPOSITORY_TRAIN.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPOSITORY_TRAIN
  add constraint PK_T_REPOSITORY_TRAIN primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
  
  
-- Create sequence 
create sequence SEQ_T_REPOSITORY_TRAIN
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
   'T_REPOSITORY_TRAIN',
   'SEQ_T_REPOSITORY_TRAIN',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');

