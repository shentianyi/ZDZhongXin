create table t_system_manage(
    id                  number(19) not null,
    username		varchar2(100),
    station		varchar2(100),
    loginid		varchar2(100),
    password		varchar2(100),
    des			varchar2(300)
);

comment on table t_system_manage is
'系统用户名管理管理部';

comment on column t_system_manage.id is
'主键id';

comment on column t_system_manage.username is
'姓名';

comment on column t_system_manage.station is
'岗位';

comment on column t_system_manage.loginid is
'系统账号名称';

comment on column t_system_manage.password is
'密码';

comment on column t_system_manage.des is
'备注';
alter table t_system_manage
  add constraint PK_t_system_manage primary key (ID);

create sequence SEQ_T_SYSTEM_MANAGE
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
   't_system_manage',
   'SEQ_T_SYSTEM_MANAGE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动录入');
