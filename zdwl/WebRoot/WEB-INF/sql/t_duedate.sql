-- Create table
create table t_duedate
(
  id      number(19) not null,
  superviseid    number(19),
  type      number(19),
  picid      number(19),
  createtime    date
);
-- Add comments to the columns 
comment on table t_duedate is
'日查库表';
comment on column t_duedate.superviseid
  is '监管员id';
comment on column t_duedate.type
  is '日查库类型';
comment on column t_duedate.picid
  is '图片id';
comment on column t_duedate.createtime
  is '上传时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_duedate
  add constraint pk_t_duedate primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_DUEDATE
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
   't_duedate',
   'SEQ_T_DUEDATE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
