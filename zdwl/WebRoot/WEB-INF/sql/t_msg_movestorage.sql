-- Create table
create table t_msg_movestorage
(
  id			number(19) not null,
  vin			varchar2(200),
  price			varchar2(200),
  moveAddress   varchar2(200),
  state			number(19),
  totalPrice	number(19),
  type			number(19),
  createDate	Date
  );
-- Add comments to the columns 
comment on table t_msg_movestorage is
'银行释放消息提醒';
comment on column t_msg_movestorage.id
  is '主键id';
comment on column t_msg_movestorage.vin
  is '车架号';
comment on column t_msg_movestorage.price
  is '价格';
comment on column t_msg_movestorage.moveAddress
  is '移动位置';
comment on column t_msg_movestorage.state
  is '状态';
comment on column t_msg_movestorage.totalPrice
  is '合计价格';
comment on column t_msg_movestorage.type
  is '消息类型';
comment on column t_msg_movestorage.createDate
  is '创建时间';

-- Create sequence 
create sequence SEQ_T_MSG_MOVESTORAGE
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
   't_msg_movestorage',
   'SEQ_T_MSG_MOVESTORAGE',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
