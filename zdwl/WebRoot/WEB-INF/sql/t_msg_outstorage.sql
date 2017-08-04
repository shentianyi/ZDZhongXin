-- Create table
create table t_msg_outstorage
(
  id			number(19) not null,
  vin			varchar2(200),
  price			varchar2(200),
  state			number(19),
  type			number(19),
  createDate	Date
  );
-- Add comments to the columns 
comment on table t_msg_outstorage is
'银行释放消息提醒';
comment on column t_msg_outstorage.id
  is '主键id';
comment on column t_msg_outstorage.vin
  is '车架号';
comment on column t_msg_outstorage.price
  is '价格';
comment on column t_msg_outstorage.state
  is '状态';
comment on column t_msg_outstorage.type
  is '消息类型';
comment on column t_msg_outstorage.createDate
  is '创建时间';

-- Create sequence 
create sequence SEQ_T_MSG_OUTSTORAGE
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
   't_msg_outstorage',
   'SEQ_T_MSG_OUTSTORAGE',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
