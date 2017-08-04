-- Create table
create table T_REGION
(
  id               NUMBER not null,
  name             NVARCHAR2(20) not null,
  parentid         NUMBER not null,
  createuser       NUMBER not null,
  createtime       DATE not null,
  lastmodifieduser NUMBER not null,
  lastmodifiedtime DATE not null,
  status           NUMBER not null
)

-- Add comments to the columns 
comment on column T_REGION.id
  is '����';
comment on column T_REGION.name
  is '���';
comment on column T_REGION.parentid
  is '����ID';
comment on column T_REGION.createuser
  is '������';
comment on column T_REGION.createtime
  is '����ʱ��';
comment on column T_REGION.lastmodifieduser
  is '����޸���';
comment on column T_REGION.lastmodifiedtime
  is '����޸�ʱ��';
comment on column T_REGION.status
  is '0:ɾ��,1:����';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_REGION
  add constraint ID primary key (ID)
  using index 
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  -- Create sequence 
create sequence SEQ_T_REGION
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
  (201607150000,
   'zddb',
   't_region',
   'SEQ_T_REGION',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');