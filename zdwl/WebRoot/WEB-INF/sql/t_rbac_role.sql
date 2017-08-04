create table t_rbac_role(

    id      number(19) not null,
    state    number(19) not null,
    role_name    varchar2(100) not null,
    client_type    number(19) not null,
    des      varchar2(300)
);

comment on table t_rbac_role is
'系统角色表';

comment on column t_rbac_role.id is
'主键id';

comment on column t_rbac_role.state is
'状态';

comment on column t_rbac_role.role_name is
'角色名称';

comment on column t_rbac_role.client_type is
'客户类型';

comment on column t_rbac_role.des is
'描述';

alter table t_rbac_role
  add constraint PK_t_rbac_role primary key (ID);

create sequence SEQ_T_RBAC_ROLE
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
   't_rbac_role',
   'SEQ_T_RBAC_ROLE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');