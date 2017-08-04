-- Create table
create table t_uploadfile
(
  id                            number(19) not null,
  file_name      varchar2(100),
  file_type      varchar2(100),
  file_path      varchar2(300),
  createtime      date
);
-- Add comments to the columns 
comment on table t_uploadfile is
'附件表';
comment on column t_uploadfile.file_name
  is '附件名称';
comment on column t_uploadfile.file_type
  is '附件类型';
comment on column t_uploadfile.file_path
  is '附件路径';
comment on column t_uploadfile.createtime
  is '上传时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_uploadfile
  add constraint pk_t_uploadfile primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_UPLOADFILE
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
   't_uploadfile',
   'SEQ_T_UPLOADFILE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
