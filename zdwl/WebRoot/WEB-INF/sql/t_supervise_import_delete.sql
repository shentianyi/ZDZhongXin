-- Create table
create table t_supervise_import_delete
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
  des        varchar2(100),
  deleteUserId	integer default 0,
  delete_date date
);
-- Add comments to the columns 
comment on table t_supervise_import_delete is
'监管物导入删除日志表';
comment on column t_supervise_import_delete.deleteUserId
  is '删除人ID';
  comment on column t_supervise_import_delete.delete_date
  is '删除时间';
comment on column t_supervise_import_delete.certificate_date
  is '合格证发证日期';
comment on column t_supervise_import_delete.certificate_num
  is '合格证号';
comment on column t_supervise_import_delete.car_model
  is '车辆型号';
comment on column t_supervise_import_delete.car_structure
  is '车身结构';
comment on column t_supervise_import_delete.displacement
  is '排量';
comment on column t_supervise_import_delete.color
  is '颜色';
comment on column t_supervise_import_delete.engine_num
  is '发动机号';
comment on column t_supervise_import_delete.vin
  is '车架号';
comment on column t_supervise_import_delete.key_num
  is '钥匙号';
comment on column t_supervise_import_delete.money
  is '金额';
comment on column t_supervise_import_delete.des
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints 


