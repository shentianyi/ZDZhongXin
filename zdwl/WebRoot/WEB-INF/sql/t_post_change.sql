-- Create table
create table T_POST_CHANGE
(
  id              NUMBER not null,
  dimissionnature NVARCHAR2(100),
  dimissiondate   DATE,
  missiondate     DATE,
  missionnature   NVARCHAR2(100),
  rosterid        NUMBER not null
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the columns 
comment on column T_POST_CHANGE.id
  is '主键ID';
comment on column T_POST_CHANGE.dimissionnature
  is '离岗性质';
comment on column T_POST_CHANGE.dimissiondate
  is '离岗日期';
comment on column T_POST_CHANGE.missiondate
  is '上任日期';
comment on column T_POST_CHANGE.missionnature
  is '上岗性质';
comment on column T_POST_CHANGE.rosterid
  is '花名册ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_POST_CHANGE
  add constraint PK_T_POST_CHANGE primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
alter table T_POST_CHANGE
  add constraint FK_T_POST_CHANGE foreign key (ROSTERID)
  references T_ROSTER (ID);

  
create sequence SEQ_T_POST_CHANGE
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
   'T_POST_CHANGE',
   'SEQ_T_POST_CHANGE',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');