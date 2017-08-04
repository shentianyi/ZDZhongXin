drop table T_REPOSITORY_DISPATCHCITY;

-- Create table
create table T_REPOSITORY_DISPATCHCITY
(
  id           NUMBER not null,
  province     NVARCHAR2(20),
  city         NVARCHAR2(20),
  county       NVARCHAR2(20),
  repositoryid NUMBER
)
;
-- Add comments to the columns 
comment on column T_REPOSITORY_DISPATCHCITY.id
  is '主键ID';
comment on column T_REPOSITORY_DISPATCHCITY.province
  is '可派驻城市（省）
';
comment on column T_REPOSITORY_DISPATCHCITY.city
  is '可派驻城市（市）
';
comment on column T_REPOSITORY_DISPATCHCITY.county
  is '可派驻城市（区）';
comment on column T_REPOSITORY_DISPATCHCITY.repositoryid
  is '储备库ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REPOSITORY_DISPATCHCITY
  add constraint PK_T_DISPATCHCITY primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_REPOSITORY_DISPATCHCITY
  add constraint FK_T_DISPATCHCITY foreign key (REPOSITORYID)
  references T_REPOSITORY (ID);

  
  
-- Create sequence 
create sequence SEQ_T_REPOSITORY_DISPATCHCITY
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
   'T_REPOSITORY_DISPATCHCITY',
   'SEQ_T_REPOSITORY_DISPATCHCITY',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
