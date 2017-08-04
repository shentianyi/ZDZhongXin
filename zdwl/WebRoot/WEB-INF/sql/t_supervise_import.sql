-- Create table
create table t_supervise_import
(
  id                            number(19) not null,
  certificate_date    date,
  certificate_num    varchar2(100),
  car_model      varchar2(100),
  car_structure      varchar2(100),
  displacement      varchar2(100),
  color        varchar2(100),
  engine_num      varchar2(100),
  vin        varchar2(100),
  key_num      varchar2(100),
  money        varchar2(100),
  des        varchar2(100)
);
-- Add comments to the columns 
comment on table t_supervise_import is
'监管物导入';
comment on column t_supervise_import.certificate_date
  is '合格证发证日期';
comment on column t_supervise_import.certificate_num
  is '合格证号';
comment on column t_supervise_import.car_model
  is '车辆型号';
comment on column t_supervise_import.car_structure
  is '车身结构';
comment on column t_supervise_import.displacement
  is '排量';
comment on column t_supervise_import.color
  is '颜色';
comment on column t_supervise_import.engine_num
  is '发动机号';
comment on column t_supervise_import.vin
  is '车架号';
comment on column t_supervise_import.key_num
  is '钥匙号';
comment on column t_supervise_import.money
  is '金额';
comment on column t_supervise_import.des
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 
alter table t_supervise_import
  add constraint pk_t_supervise_import primary key (ID);
  
-- Create sequence 
create sequence SEQ_T_SUPERVISE_IMPORT
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
   't_supervise_import',
   'SEQ_T_SUPERVISE_IMPORT',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
