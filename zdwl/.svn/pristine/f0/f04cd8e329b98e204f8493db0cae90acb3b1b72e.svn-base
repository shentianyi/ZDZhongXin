-- Create table
create table t_msg_billnocar
(
  id					number(19) not null,
  draft_num				varchar2(200),
  billing_date			Date,
  due_date				Date,
  billing_money			number(10,2),
  nomortgagecar_money	number(10,2),
  type					number(19),
  createDate			Date
  );
-- Add comments to the columns 
comment on table t_msg_billnocar is
'开票10个工作日未到车提醒';
comment on column t_msg_billnocar.id
  is '主键id';
comment on column t_msg_billnocar.draft_num
  is '汇票号';
comment on column t_msg_billnocar.billing_date
  is '开票日';
comment on column t_msg_billnocar.due_date
  is '到期日';
comment on column t_msg_billnocar.billing_money
  is '开票金额';
comment on column t_msg_billnocar.nomortgagecar_money
  is '未押车金额';
comment on column t_msg_billnocar.type
  is '消息类型';
comment on column t_msg_billnocar.createDate
  is '创建时间';

-- Create sequence 
create sequence SEQ_T_MSG_BILLNOCAR
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
   't_msg_billnocar',
   'SEQ_T_MSG_BILLNOCAR',
   1,
   sysdate,
   sysdate,
   '...',
   '手动添加');
