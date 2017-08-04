-- Create table
create table T_SUPERVISOR_WORKEXPERIENCE
(
  id                  NUMBER not null,
  workstarttime       DATE,
  workendtime         DATE,
  serviceorganization NVARCHAR2(100),
  position            NVARCHAR2(50),
  compensation        NUMBER(20,2),
  leavereason         NVARCHAR2(200),
  certifier           NVARCHAR2(10),
  contactnumber       NVARCHAR2(50),
  supervisorid        NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_SUPERVISOR_WORKEXPERIENCE.id
  is '主键ID';
comment on column T_SUPERVISOR_WORKEXPERIENCE.workstarttime
  is '起(工作经历)';
comment on column T_SUPERVISOR_WORKEXPERIENCE.workendtime
  is '止(工作经历)';
comment on column T_SUPERVISOR_WORKEXPERIENCE.serviceorganization
  is '服务机构';
comment on column T_SUPERVISOR_WORKEXPERIENCE.position
  is '职位';
comment on column T_SUPERVISOR_WORKEXPERIENCE.compensation
  is '薪资';
comment on column T_SUPERVISOR_WORKEXPERIENCE.leavereason
  is '离职原因';
comment on column T_SUPERVISOR_WORKEXPERIENCE.certifier
  is '证明人';
comment on column T_SUPERVISOR_WORKEXPERIENCE.contactnumber
  is '联系方式';
comment on column T_SUPERVISOR_WORKEXPERIENCE.supervisorid
  is '监管员ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SUPERVISOR_WORKEXPERIENCE
  add constraint PK_T_SUPERVISOR_WORKEXPERIENCE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_SUPERVISOR_WORKEXPERIENCE
  add constraint FK_T_SUPERVISOR_WORKEXPERIENCE foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  
create sequence SEQ_T_SUPERVISOR_WORKEXPERIENCE
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
   'T_SUPERVISOR_WORKEXPERIENCE',
   'SEQ_T_SUPERVISOR_WORKEXPERIENCE',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');