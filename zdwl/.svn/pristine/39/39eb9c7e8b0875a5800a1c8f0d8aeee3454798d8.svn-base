-- Create table
create table t_msg_zeroskudraft
(
  id					number(19) not null,
  dealerId				number(19),
  type					number(19),
  createDate			Date
  );
-- Add comments to the columns 
comment on table t_msg_zeroskudraft is
'开票10个工作日未到车提醒';
comment on column t_msg_zeroskudraft.id
  is '主键id';
comment on column t_msg_zeroskudraft.dealerId
  is '经销商id';
comment on column t_msg_zeroskudraft.type
  is '消息类型';
comment on column t_msg_zeroskudraft.createDate
  is '创建时间';

-- Create sequence 
create sequence SEQ_T_MSG_ZEROSKUDRAFT
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
   't_msg_zeroskudraft',
   'SEQ_T_MSG_ZEROSKUDRAFT',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
