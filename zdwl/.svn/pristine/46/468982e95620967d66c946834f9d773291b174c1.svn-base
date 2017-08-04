create table t_rbac_user_role(
    id      number(19) not null,
    role_id    number(19) not null,
    user_id    number(19) not null
);

comment on table t_rbac_user_role is
'用户与角色关系表';

comment on column t_rbac_user_role.id is
'主键id';

comment on column t_rbac_user_role.role_id is
'角色id';

comment on column t_rbac_user_role.user_id is
'用户id';

alter table t_rbac_user_role
  add constraint PK_t_rbac_user_role primary key (ID);

create sequence SEQ_T_RBAC_USER_ROLE
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
   't_rbac_user_role',
   'SEQ_T_RBAC_USER_ROLE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');