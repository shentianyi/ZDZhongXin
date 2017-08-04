-- Create table
create table T_TRANSFER_REPOSITORY
(
  id           NUMBER not null,
  dealerid     NUMBER not null,
  entrytime    DATE not null,
  entrynature  NVARCHAR2(500) not null,
  repositoryid NUMBER not null
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
comment on column T_TRANSFER_REPOSITORY.id
  is '主键ID';
comment on column T_TRANSFER_REPOSITORY.dealerid
  is '经销商ID';
comment on column T_TRANSFER_REPOSITORY.entrytime
  is '调入时间';
comment on column T_TRANSFER_REPOSITORY.entrynature
  is '调入性质';
comment on column T_TRANSFER_REPOSITORY.repositoryid
  is '储备库ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_TRANSFER_REPOSITORY
  add constraint PK_ID primary key (ID)
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
alter table T_TRANSFER_REPOSITORY
  add constraint FK_TRANSFER_DEALER foreign key (DEALERID)
  references MARKET_DEALER (ID);
alter table T_TRANSFER_REPOSITORY
  add constraint FK_TRANSFER_REPOSITORYID foreign key (REPOSITORYID)
  references T_REPOSITORY (ID);

-- Create sequence 
create sequence SEQ_T_TRANSFER_REPOSITORY
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
  ('201608300001',
   'zddb',
   't_transfer_repository',
   'SEQ_T_TRANSFER_REPOSITORY',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动录入');