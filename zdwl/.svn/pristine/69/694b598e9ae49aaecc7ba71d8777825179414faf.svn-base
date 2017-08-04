create table t_rbac_resource(
    id      number(19) not null,
    state    number(19) not null,
    resource_level  number(19) not null,
    node_type    number(19) not null,
    parent_id    number(19) not null,
    resource_index  varchar2(20),
    resource_name  varchar2(100) not null,
    resource_shortname  varchar2(100),
    resource_url  varchar2(300),
    des      varchar2(300)
);

comment on table t_rbac_resource is
'系统资源表';

comment on column t_rbac_resource.id is
'主键id';

comment on column t_rbac_resource.state is
'启用状态';

comment on column t_rbac_resource.resource_level is
'资源级别';

comment on column t_rbac_resource.node_type is
'节点类型';

comment on column t_rbac_resource.parent_id is
'上级资源';

comment on column t_rbac_resource.resource_index is
'资源顺序';

comment on column t_rbac_resource.resource_name is
'资源名称';

comment on column t_rbac_resource.resource_shortname is
'资源简称';

comment on column t_rbac_resource.resource_url is
'资源路径';

comment on column t_rbac_resource.des is
'资源描述';

alter table t_rbac_resource
  add constraint PK_t_rbac_resource primary key (ID);

create sequence SEQ_T_RBAC_RESOURCE
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
   't_rbac_resource',
   'SEQ_T_RBAC_RESOURCE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');