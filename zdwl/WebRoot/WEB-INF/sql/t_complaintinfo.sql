-- Create table
create table t_complaintinfo
(
  id                         NUMBER(19) not null,
  
  createDate                 DATE,
  createTime                 VARCHAR2(128),
  createUserId               NUMBER(19),
  createUserName             VARCHAR2(128),
  createUserDept             VARCHAR2(128),
  
  phoneType                  VARCHAR2(128),
  complaintObjId             NUMBER(19),
  dealerId                   NUMBER(19),
  financeId                  NUMBER(19),
  brandId                    NUMBER(19),
  brandName					 VARCHAR2(128),
  complainantName     	     VARCHAR2(128),
  complainantPosition        VARCHAR2(19),
  rosterId                   NUMBER(19),
  
  jobNum                     VARCHAR2(128),
  storeName                  VARCHAR2(128),
  bankName                   VARCHAR2(128),
  storeId                    NUMBER(19),
  bankId                     NUMBER(19),
  processingDepartment       NUMBER(19),
  processingId               NUMBER(19),
  
  processingName             VARCHAR2(128),
  contenttext                VARCHAR2(1024),
  treatmentOpinion           VARCHAR2(1024),
  solution                   VARCHAR2(1024),
  trackCondition             VARCHAR2(1024),
  
  treatmentProcessId         NUMBER(19),
  treatmentProcessName       VARCHAR2(128),
  treatmentProcessTime       DATE,
  
  
  solutionOperatorId         NUMBER(19),
  solutionOperatorName       VARCHAR2(128),
  solutionProcessTime        DATE,
  
  trackOperatorId            NUMBER(19),
  trackOperatorName          VARCHAR2(128),
  trackProcessTime           DATE,
  
  status                     VARCHAR2(10)
)
-- Add comments to the table 
comment on table t_complaintinfo
  is '来电投诉记录表';

-- Add comments to the columns 
comment on column t_complaintinfo.id
  is '主键ID';
  
comment on column t_complaintinfo.createDate
  is '创建日期';
comment on column t_complaintinfo.createTime
  is '创建时间';
comment on column t_complaintinfo.createUserId
  is '记录人Id' ;
comment on column t_complaintinfo.createUserName
  is '记录人姓名';
comment on column t_complaintinfo.createUserDept
  is '所属部门';
  
  
comment on column t_complaintinfo.phoneType
  is '来电类型';
comment on column t_complaintinfo.complaintObjId
  is '投诉对象Id';
comment on column t_complaintinfo.dealerId
  is '经销商主键ID';
comment on column t_complaintinfo.financeId
  is '金融机构';
comment on column t_complaintinfo.brandId
  is '品牌Id';
comment on column t_complaintinfo.brandName
  is '品牌姓名';
comment on column t_complaintinfo.complainantName
  is '来电人姓名';
comment on column t_complaintinfo.complainantPosition
  is '职务';
comment on column t_complaintinfo.rosterId
  is '花名册主键Id';
  
  
comment on column t_complaintinfo.jobNum
  is '工号';
comment on column t_complaintinfo.storeName
  is '所在店';
comment on column t_complaintinfo.bankName
  is '所在行';
comment on column t_complaintinfo.storeId
  is '所在店Id';
comment on column t_complaintinfo.bankId
  is '所在行Id';
comment on column t_complaintinfo.processingDepartment
  is '处理部门';
comment on column t_complaintinfo.processingId
  is '处理人员Id';
  
  
comment on column t_complaintinfo.processingName
  is '处理人员姓名';
comment on column t_complaintinfo.contenttext
  is '处理内容';
  comment on column t_complaintinfo.treatmentOpinion
  is '处理意见';
  comment on column t_complaintinfo.solution
  is '解决方案';
  comment on column t_complaintinfo.trackCondition
  is '跟踪情况';
  
  comment on column t_complaintinfo.treatmentProcessId
  is '处理意见经办人Id';
  comment on column t_complaintinfo.treatmentProcessName
  is '处理意见经办人名称';
  comment on column t_complaintinfo.treatmentProcessTime
  is '处理意见的填写时间';
  
  comment on column t_complaintinfo.solutionOperatorId
  is '解决方案经办人Id';
  comment on column t_complaintinfo.solutionOperatorName   
  is '解决方案经办人名称';
  comment on column t_complaintinfo.solutionProcessTime
  is '解决方案处理时间';
  
  comment on column t_complaintinfo.trackOperatorId
  is '跟踪情况经办人Id';
  comment on column t_complaintinfo.trackOperatorName
  is '跟踪情况经办人名称';
  comment on column t_complaintinfo.trackProcessTime
  is '跟踪情况处理时间';
  
  comment on column t_complaintinfo.status
  is '状态 (1：未提交、2：已发送、3：已修正、4：已完成)';
  
  
  
-- Create sequence 
create sequence SEQ_T_COMPLAINTINFO
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
   't_complaintinfo',
   'SEQ_T_COMPLAINTINFO',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');