-- Create table
create table t_message
(
  id			number(19) not null,
  userid		number(19),
  name			varchar2(200),
  url			varchar2(300),
  isread		number(19),
  msgtype		number(19));
-- Add comments to the columns 
comment on table t_message is
'消息提醒';
comment on column t_message.userid
  is '用户id';
comment on column t_message.name
  is '消息名称';
comment on column t_message.url
  is 'url';
comment on column t_message.isread
  is '是否读取';
comment on column t_message.msgtype
  is '消息类型';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_message
  add constraint pk_t_message primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_MESSAGE
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
   't_message',
   'SEQ_T_MESSAGE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
