-- Create table
create table t_note
(
  id                            number(19) not null,
  userid			number(19),
  title				varchar2(200),
  content			clob);
-- Add comments to the columns 
comment on table t_note is
'便签';
comment on column t_note.userid
  is '用户id';
comment on column t_note.title
  is '标题';
comment on column t_note.content
  is '内容';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_note
  add constraint pk_t_note primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_NOTE
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
   't_note',
   'SEQ_T_NOTE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
