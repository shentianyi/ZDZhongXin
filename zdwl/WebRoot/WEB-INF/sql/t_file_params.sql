-- Create table
create table t_file_params
(
  id         number(19) not null,
  mid        number(19),
  type        varchar2(100),
  value        number(19),
  uploaduser      number(19),
  createtime      date
);
-- Add comments to the columns 
comment on table t_file_params is
'上传附件表';
comment on column t_file_params.mid
  is '关联id';
comment on column t_file_params.type
  is '关联模板类型';
comment on column t_file_params.value
  is '附件id';
comment on column t_file_params.uploaduser
  is '上传人';
comment on column t_file_params.createtime
  is '上传时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_file_params
  add constraint pk_t_file_params primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_FILE_PARAMS
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
   't_file_params',
   'SEQ_T_FILE_PARAMS',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
