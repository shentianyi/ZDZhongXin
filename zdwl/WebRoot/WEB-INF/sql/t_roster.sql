-- Create table
create table T_ROSTER
(
  id                NUMBER not null,
  staffno           NUMBER,
  repositoryid      NUMBER not null,
  supervisorid      NUMBER not null,
  paycardno         NVARCHAR2(50),
  openbank          NVARCHAR2(100),
  contactnumber     NVARCHAR2(50),
  organizetype      NVARCHAR2(50),
  driveyear         NUMBER,
  drivemonth        NUMBER,
  entrydate         DATE,
  dispatchattribute NVARCHAR2(50),
  dispatchprovince  NVARCHAR2(50),
  dispatchcity      NVARCHAR2(50),
  enliststatus      NVARCHAR2(50),
  recruitchannel    NVARCHAR2(50),
  refereename       NVARCHAR2(50),
  refereebackground NVARCHAR2(100),
  interviewer       NVARCHAR2(50),
  systemaccount     NVARCHAR2(50),
  systempassword    NVARCHAR2(50)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_ROSTER.id
  is '主键ID';
comment on column T_ROSTER.staffno
  is '员工号';
comment on column T_ROSTER.repositoryid
  is '储备库ID';
comment on column T_ROSTER.supervisorid
  is '监管员ID';
comment on column T_ROSTER.paycardno
  is '工资卡号';
comment on column T_ROSTER.openbank
  is '开户行';
comment on column T_ROSTER.contactnumber
  is '联系方式';
comment on column T_ROSTER.organizetype
  is '编制类型';
comment on column T_ROSTER.driveyear
  is '司龄（年）';
comment on column T_ROSTER.drivemonth
  is '司龄（月数）';
comment on column T_ROSTER.entrydate
  is '入职日期';
comment on column T_ROSTER.dispatchattribute
  is '驻派属性';
comment on column T_ROSTER.dispatchprovince
  is '驻派省份';
comment on column T_ROSTER.dispatchcity
  is '驻派市';
comment on column T_ROSTER.enliststatus
  is '服役状态';
comment on column T_ROSTER.recruitchannel
  is '招聘渠道';
comment on column T_ROSTER.refereename
  is '推荐人姓名';
comment on column T_ROSTER.refereebackground
  is '推荐人背景';
comment on column T_ROSTER.interviewer
  is '面试经手人';
comment on column T_ROSTER.systemaccount
  is '系统账号';
comment on column T_ROSTER.systempassword
  is '系统密码';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_ROSTER
  add constraint PK_T_ROSTER primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_ROSTER
  add constraint FK_T_ROSTER_REPOSITORYID foreign key (REPOSITORYID)
  references T_REPOSITORY (ID);
alter table T_ROSTER
  add constraint FK_T_ROSTER_SUPERVISORID foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  
create sequence SEQ_T_ROSTER
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
   'T_ROSTER',
   'SEQ_T_ROSTER',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');