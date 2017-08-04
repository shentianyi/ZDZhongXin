create table t_rbac_role_resource(
    id      number(19) not null,
    resource_id    number(19) not null,
    role_id    number(19) not null
);

comment on table t_rbac_role_resource is
'资源与角色关系表';

comment on column t_rbac_role_resource.id is
'主键id';

comment on column t_rbac_role_resource.resource_id is
'资源id';

comment on column t_rbac_role_resource.role_id is
'角色id';

alter table t_rbac_role_resource
  add constraint PK_t_rbac_role_resource primary key (ID);

create sequence SEQ_T_RBAC_ROLE_RESOURCE
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
   't_rbac_role_resource',
   'SEQ_T_RBAC_ROLE_RESOURCE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');