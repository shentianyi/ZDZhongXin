-- Create table
create table T_SUPERVISOR_EDUCATION
(
  id                 NUMBER not null,
  educationstarttime DATE,
  educationendtime   DATE,
  schoolname         NVARCHAR2(50),
  major              NVARCHAR2(40),
  certificate        NVARCHAR2(40),
  degree             NVARCHAR2(20),
  supervisorid       NUMBER
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_SUPERVISOR_EDUCATION.id
  is '主键ID';
comment on column T_SUPERVISOR_EDUCATION.educationstarttime
  is '起(教育时间)';
comment on column T_SUPERVISOR_EDUCATION.educationendtime
  is '止(教育时间)';
comment on column T_SUPERVISOR_EDUCATION.schoolname
  is '学校名称';
comment on column T_SUPERVISOR_EDUCATION.major
  is '主修专业';
comment on column T_SUPERVISOR_EDUCATION.certificate
  is '获得证书';
comment on column T_SUPERVISOR_EDUCATION.degree
  is '学位';
comment on column T_SUPERVISOR_EDUCATION.supervisorid
  is '监管员ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SUPERVISOR_EDUCATION
  add constraint PK_T_SUPERVISOR_EDUCATION primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_SUPERVISOR_EDUCATION
  add constraint FK_T_SUPERVISOR_EDUCATION foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  
create sequence SEQ_T_SUPERVISOR_EDUCATION
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
   'T_SUPERVISOR_EDUCATION',
   'SEQ_T_SUPERVISOR_EDUCATION',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');