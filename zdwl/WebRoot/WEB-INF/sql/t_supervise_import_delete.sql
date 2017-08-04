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
'����ﵼ��ɾ����־��';
comment on column t_supervise_import_delete.deleteUserId
  is 'ɾ����ID';
  comment on column t_supervise_import_delete.delete_date
  is 'ɾ��ʱ��';
comment on column t_supervise_import_delete.certificate_date
  is '�ϸ�֤��֤����';
comment on column t_supervise_import_delete.certificate_num
  is '�ϸ�֤��';
comment on column t_supervise_import_delete.car_model
  is '�����ͺ�';
comment on column t_supervise_import_delete.car_structure
  is '����ṹ';
comment on column t_supervise_import_delete.displacement
  is '����';
comment on column t_supervise_import_delete.color
  is '��ɫ';
comment on column t_supervise_import_delete.engine_num
  is '��������';
comment on column t_supervise_import_delete.vin
  is '���ܺ�';
comment on column t_supervise_import_delete.key_num
  is 'Կ�׺�';
comment on column t_supervise_import_delete.money
  is '���';
comment on column t_supervise_import_delete.des
  is '��ע';
-- Create/Recreate primary, unique and foreign key constraints 


