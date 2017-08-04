-- Create table
create table t_fixed_asset_list
(
  id              number(19) not null,
  fid        number(19),
  department      varchar2(100),
  username        varchar2(100),
  trade        varchar2(100),
  trade_province    number(19),
  trade_city      number(19),
  password      varchar2(100),
  key        varchar2(100),
  deposit_time      date,
  out_time      date,
  express      varchar2(100),
  express_num      varchar2(100),
  express_money      varchar2(100),
  repair_time      date,
  repair_money      varchar2(100),
  repair_des      varchar2(300),
  receive_pic      number(19),
  des        varchar2(300)
);
-- Add comments to the columns 
comment on table t_fixed_asset_list is
'�̶��ʲ�����';
comment on column t_fixed_asset_list.fid
  is '�̶��ʲ��id';
comment on column t_fixed_asset_list.department
  is 'ʹ�ò���';
comment on column t_fixed_asset_list.username
  is 'ʹ����';
comment on column t_fixed_asset_list.trade
  is '��ŵ�';
comment on column t_fixed_asset_list.trade_province
  is '��ŵ�ַ��ʡ��';
comment on column t_fixed_asset_list.trade_city
  is '��ŵ�ַ���У�';
comment on column t_fixed_asset_list.password
  is '����';
comment on column t_fixed_asset_list.key
  is 'Կ��';
comment on column t_fixed_asset_list.deposit_time
  is '���ʱ��';
comment on column t_fixed_asset_list.out_time
  is 'ת��ʱ��';
comment on column t_fixed_asset_list.express
  is '���乫˾';
comment on column t_fixed_asset_list.express_num
  is '����';
comment on column t_fixed_asset_list.express_money
  is '�˷�';
comment on column t_fixed_asset_list.repair_time
  is 'ά��ʱ��';
comment on column t_fixed_asset_list.repair_money
  is 'ά�޽��';
comment on column t_fixed_asset_list.repair_des
  is 'ά������';
comment on column t_fixed_asset_list.receive_pic
  is '�豸���յ�';
comment on column t_fixed_asset_list.des
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_fixed_asset_list
  add constraint pk_t_fixed_asset_list primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_FIXED_ASSET_LIST
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
   't_fixed_asset_list',
   'SEQ_T_FIXED_ASSET_LIST',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动录入');
