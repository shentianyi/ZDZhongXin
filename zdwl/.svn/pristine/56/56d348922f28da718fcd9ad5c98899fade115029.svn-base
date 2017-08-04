-- Create table
create table t_flow
(
  id			number(19) not null,
  flowname		varchar2(200),
  explain		varchar2(400),
  pic			number(19),
  uploadid		number(19),
  createtime		date);
-- Add comments to the columns 
comment on table t_flow is
'操作流程';
comment on column t_flow.flowname
  is '流程名称';
comment on column t_flow.explain
  is '说明';
comment on column t_flow.pic
  is '图片id';
comment on column t_flow.uploadid
  is '上传人id';
comment on column t_flow.createtime
  is '创建时间';

-- Create/Recreate primary, unique and foreign key constraints 
alter table t_flow
  add constraint pk_t_flow primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_FLOW
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
   't_flow',
   'SEQ_T_FLOW',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
