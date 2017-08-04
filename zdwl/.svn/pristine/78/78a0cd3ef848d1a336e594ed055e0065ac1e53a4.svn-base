-- Create table
create table T_SUPERVISOR_FAMILY
(
  id           NUMBER not null,
  name         NVARCHAR2(20),
  profession   NVARCHAR2(50),
  organization NVARCHAR2(100),
  telephone    NVARCHAR2(50),
  relationship NVARCHAR2(20),
  supervisorid NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_SUPERVISOR_FAMILY.id
  is '主键ID';
comment on column T_SUPERVISOR_FAMILY.name
  is '姓名';
comment on column T_SUPERVISOR_FAMILY.profession
  is '职业';
comment on column T_SUPERVISOR_FAMILY.organization
  is '单位';
comment on column T_SUPERVISOR_FAMILY.telephone
  is '电话';
comment on column T_SUPERVISOR_FAMILY.relationship
  is '关系';
comment on column T_SUPERVISOR_FAMILY.supervisorid
  is '监管员ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SUPERVISOR_FAMILY
  add constraint PK_T_SUPERVISOR_FAMILY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_SUPERVISOR_FAMILY
  add constraint FK_T_SUPERVISOR_FAMILY foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  
create sequence SEQ_T_SUPERVISOR_FAMILY
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
   'T_SUPERVISOR_FAMILY',
   'SEQ_T_SUPERVISOR_FAMILY',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');