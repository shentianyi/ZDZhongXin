create table t_rbac_user(
       id                 number(19) not null,
       username           varchar2(100) not null,
       loginid            varchar2(100) not null,
       password           varchar2(100) not null,
       password_random    varchar2(256) not null,
       mobilephone        varchar2(100),
       email              varchar2(100),
       state              number(19),
       client_type        number(19),
       client_id          number(19),
       createtime         date);


comment on table t_rbac_user is
'用户表';

comment on column t_rbac_user.id is
'用户表id';
comment on column t_rbac_user.username is
'姓名';
comment on column t_rbac_user.loginid is
'登录名';
comment on column t_rbac_user.password is
'密码';
comment on column t_rbac_user.mobilephone is
'手机';
comment on column t_rbac_user.email is
'邮箱';
comment on column t_rbac_user.state is
'状态';
comment on column t_rbac_user.client_type is
'客户类型';
comment on column t_rbac_user.client_id is
'客户id';
comment on column t_rbac_user.createtime is
'创建时间';


alter table t_rbac_user
  add constraint PK_t_rbac_user primary key (ID)
  
-- Create sequence 
create sequence SEQ_T_RBAC_USER
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

insert into t_rbac_user values(SEQ_T_RBAC_USER.Nextval,'aa','aa','aa','aa','aa','aa',1,1,1,to_date('2016-07-11','yyyy-mm-dd'));

select * from t_rbac_user




create table t_seqencedict(
       id                   number(19) not null,
       dbname               varchar2(300) not null,
       tablename            varchar2(300) not null,
       seqname              varchar2(300) not null,
       seqinitvalue         number(19) not null,
       addseqdate           date,
       createtime           date,
       addsequser           varchar2(300),
       memo                 varchar2(300)
);

alter table t_seqencedict
  add constraint PK_t_seqencedict primary key (ID)
  
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
  (1,
   'zddb',
   't_rbac_user',
   'SEQ_T_RBAC_USER',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
   
   select * from t_seqencedict