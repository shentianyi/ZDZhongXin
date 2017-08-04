-- Create table
create table t_modechange_log
(
  id   NUMBER not null,
  dealerId Integer not null,
  beforeOperationMode varchar2(20),
  lastOperationMode varchar2(20),
  beforeSuperviseMoneyDate varchar2(20),
  lastSuperviseMoneyDate varchar2(20),
  modifyTime date, 
  changeSuperviseMoneyDate date,
  createUserId Integer,
  lastModifyUserId Integer,
  createDate date,
  lastModifyDate date
)

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_modechange_log
  add constraint PK_t_modechange_log primary key (ID);
create index index_t_modechange_log on t_modechange_log (dealerId);



-- Add comments to the table 
comment on table t_modechange_log
  is '经销商操作模式变更日志';
-- Add comments to the columns 
comment on column t_modechange_log.id
  is '主键ID';
comment on column t_modechange_log.dealerId
  is '经销商ID';
comment on column t_modechange_log.beforeOperationMode
  is '变更前操作模式';
comment on column t_modechange_log.lastOperationMode
  is '变更后操作模式';
comment on column t_modechange_log.beforeSuperviseMoneyDate
  is '变更前监管费';
comment on column t_modechange_log.lastSuperviseMoneyDate
  is '变更后监管费';
comment on column t_modechange_log.modifyTime
  is '操作模式变更时间';
comment on column t_modechange_log.changeSuperviseMoneyDate
  is '监管费变更时间';
comment on column t_modechange_log.createUserId
  is '创建人id';
comment on column t_modechange_log.lastModifyUserId
  is '最后修改人id';
comment on column t_modechange_log.createDate
  is '创建时间';
comment on column t_modechange_log.lastModifyDate
  is '最后修改时间';

-- Create/Recreate primary, unique and foreign key constraints 

  
create sequence SEQ_MODECHANGE_LOG
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
  ('201706180001',
   'zddb',
   't_modechange_log',
   'SEQ_MODECHANGE_LOG',
   1,
   sysdate,
   sysdate,
   'sxs',
   '手动添加');