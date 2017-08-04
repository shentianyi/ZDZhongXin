-- Create table
create table T_OTHER_DATA
(
  id               NUMBER not null,
  supervisorid     NUMBER not null,
  situationexplain NVARCHAR2(1000),
  specialoperation NVARCHAR2(1000)
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns 
comment on column T_OTHER_DATA.id
  is '主键ID';
comment on column T_OTHER_DATA.supervisorid
  is '监管员ID';
comment on column T_OTHER_DATA.situationexplain
  is '情况说明';
comment on column T_OTHER_DATA.specialoperation
  is '特殊操作说明';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_OTHER_DATA
  add constraint PK_T_OTHER_DATA_ID primary key (ID)
  using index 
  tablespace ZD_SPC
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
alter table T_OTHER_DATA
  add constraint FK_T_OTHER_DATA_SUPERVISORID foreign key (SUPERVISORID)
  references T_SUPERVISOR_BASIC_INFORMATION (ID);

  -- Create sequence 
create sequence SEQ_T_OTHER_DATA
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
  (201608270003,
   'zddb',
   't_other_data',
   'SEQ_T_OTHER_DATA',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');