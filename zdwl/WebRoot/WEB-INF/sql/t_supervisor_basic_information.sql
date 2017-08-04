-- Create table
create table T_SUPERVISOR_BASIC_INFORMATION
(
  id                           NUMBER not null,
  name                         NVARCHAR2(20) not null,
  gender                       NVARCHAR2(4) not null,
  birthday                     DATE not null,
  idnumber                     NVARCHAR2(50) not null,
  nation                       NVARCHAR2(50) not null,
  educationbackground          NVARCHAR2(50) not null,
  nativeplace                  NVARCHAR2(100) not null,
  politicsstatus               NVARCHAR2(8) not null,
  registeredstatus             NVARCHAR2(8) not null,
  selftelephone                NVARCHAR2(50) not null,
  hometelephone                NVARCHAR2(50),
  fertilitystate               NVARCHAR2(8) not null,
  registeredaddress            NVARCHAR2(100) not null,
  liveaddress                  NVARCHAR2(100) not null,
  emergencycontactor           NVARCHAR2(50) not null,
  emergencycontactnumber       NVARCHAR2(50),
  emergencycontactrelationship NVARCHAR2(20),
  pictureId                    NUMBER,
  job						   NVARCHAR2(100),
  entryTime 				   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_SUPERVISOR_BASIC_INFORMATION.id
  is '主键ID';
comment on column T_SUPERVISOR_BASIC_INFORMATION.name
  is '姓名';
comment on column T_SUPERVISOR_BASIC_INFORMATION.gender
  is '性别';
comment on column T_SUPERVISOR_BASIC_INFORMATION.birthday
  is '出生日期';
comment on column T_SUPERVISOR_BASIC_INFORMATION.idnumber
  is '身份证号';
comment on column T_SUPERVISOR_BASIC_INFORMATION.nation
  is '民族';
comment on column T_SUPERVISOR_BASIC_INFORMATION.educationbackground
  is '学历';
comment on column T_SUPERVISOR_BASIC_INFORMATION.nativeplace
  is '籍贯';
comment on column T_SUPERVISOR_BASIC_INFORMATION.politicsstatus
  is '政治面貌';
comment on column T_SUPERVISOR_BASIC_INFORMATION.registeredstatus
  is '户口性质';
comment on column T_SUPERVISOR_BASIC_INFORMATION.selftelephone
  is '本人联系电话';
comment on column T_SUPERVISOR_BASIC_INFORMATION.hometelephone
  is '家庭电话';
comment on column T_SUPERVISOR_BASIC_INFORMATION.fertilitystate
  is '婚育状况';
comment on column T_SUPERVISOR_BASIC_INFORMATION.registeredaddress
  is '户口所在地';
comment on column T_SUPERVISOR_BASIC_INFORMATION.liveaddress
  is '现居住详细地址';
comment on column T_SUPERVISOR_BASIC_INFORMATION.emergencycontactor
  is '紧急状况联系人';
comment on column T_SUPERVISOR_BASIC_INFORMATION.emergencycontactnumber
  is '紧急状况联系电话';
comment on column T_SUPERVISOR_BASIC_INFORMATION.emergencycontactrelationship
  is '与紧急联系人的关系';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_SUPERVISOR_BASIC_INFORMATION
  add constraint PK_T_SUPERVISOR_BASIC primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;

  
create sequence SEQ_T_SUPERVISOR_BASIC_INFO
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
   'T_SUPERVISOR_BASIC_INFORMATION',
   'SEQ_T_SUPERVISOR_BASIC_INFORMATION',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');