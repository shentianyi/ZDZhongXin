
-- Create table
create table T_RBAC_DEALERGROUP
(
  id         NUMBER(19) not null,
  name       VARCHAR2(50) not null,
  createtime DATE not null,
  modifytime DATE not null
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_RBAC_DEALERGROUP
  add constraint PK_RBAC_DEALERGROUP primary key (ID)
  using index 
  tablespace ZD_SPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create sequence SEQ_T_RBAC_DEALERGROUP
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
  ('201704140001',
   'zddb',
   'T_RBAC_DEALERGROUP',
   'SEQ_T_RBAC_DEALERGROUP',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
 

-- Create table
create table T_RBAC_DEALERGROUP_DEALER
(
  groupid  NUMBER(19) not null,
  dealerid NUMBER(19) not null
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
  
  
  create sequence SEQ_T_RBAC_DEALERGROUP_DEALER
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
  ('201704140002',
   'zddb',
   'T_RBAC_DEALERGROUP_DEALER',
   'SEQ_T_RBAC_DEALERGROUP_DEALER',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
  
  -- Create table
create table T_RBAC_DEALERGROUP_USER
(
  groupid NUMBER(19) not null,
  userid  NUMBER(19) not null
)
tablespace ZD_SPC
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
	create sequence SEQ_T_RBAC_DEALERGROUP_USER
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
  ('201704140003',
   'zddb',
   'T_RBAC_DEALERGROUP_USER',
   'SEQ_T_RBAC_DEALERGROUP_USER',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
  