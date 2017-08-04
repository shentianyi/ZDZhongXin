create table t_addresslist(
    id                  number(19) not null,
    department          varchar2(100),
    name                varchar2(100),
    quarters            varchar2(100),
    phone               varchar2(100),
    landline            varchar2(100),
    email               varchar2(100),
    fax                 varchar2(100),
    qq                  varchar2(100),
    duty                varchar2(300),
    genre               number(19)
);

comment on table t_addresslist is
'通讯录';

comment on column t_addresslist.id is
'主键id';

comment on column t_addresslist.name is
'姓名';

comment on column t_addresslist.quarters is
'岗位';

comment on column t_addresslist.phone is
'手机号码';

comment on column t_addresslist.landline is
'座机';

comment on column t_addresslist.email is
'邮箱';

comment on column t_addresslist.fax is
'传真';

comment on column t_addresslist.qq is
'QQ号码';

comment on column t_addresslist.duty is
'职责';

comment on column t_addresslist.genre is
'类型';

alter table t_addresslist
  add constraint PK_t_addresslist primary key (ID);

create sequence SEQ_T_ADDRESSLIST
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
   't_addresslist',
   'SEQ_T_ADDRESSLIST',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动添加');
