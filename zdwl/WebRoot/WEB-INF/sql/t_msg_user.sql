-- Create table
create table t_msg_user
(
  id				number(19),
  userId			number(19),
  type				number(19),
  msgInfoId			number(19),
  msgType			number(19)
  );
-- Add comments to the columns 
comment on table t_msg_user is
'消息提醒详细信息与用户关联表';
comment on column t_msg_user.id
  is '主键id';
comment on column t_msg_user.userId
  is '用户id';
comment on column t_msg_user.type
  is '消息类型';
comment on column t_msg_user.msgInfoId
  is '详细消息id';
comment on column t_msg_user.msgType
  is '消息分类(1:提醒 2:预警)';



-- Create sequence 
create sequence SEQ_T_MSG_USER
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
   't_msg_user',
   'SEQ_T_MSG_USER',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
