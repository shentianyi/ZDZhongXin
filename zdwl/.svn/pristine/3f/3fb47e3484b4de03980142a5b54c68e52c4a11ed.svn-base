create table t_username_manage(
    id                  number(19) not null,
    distribid           number(19),
    bankname            varchar2(100),
    supervisory_name    varchar2(100),
    loginid             varchar2(100),
    password            varchar2(100),
    des                 varchar2(300)
);

comment on table t_username_manage is
'系统用户名管理';

comment on column t_username_manage.id is
'主键id';

comment on column t_username_manage.distribid is
'经销商id';

comment on column t_username_manage.bankname is
'合作机构';

comment on column t_username_manage.supervisory_name is
'监管员姓名';

comment on column t_username_manage.loginid is
'账号';

comment on column t_username_manage.password is
'密码';

comment on column t_username_manage.des is
'备注';

alter table t_username_manage
  add constraint PK_t_username_manage primary key (ID);

create sequence SEQ_T_USERNAME_MANAGE
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
   't_username_manage',
   'SEQ_T_USERNAME_MANAGE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
