create table t_system_supervise(
    id                  number(19) not null,
    trade_name		varchar2(100),
    bankid		number(19),
    bank_fen		number(19),
    bank_zhi		number(19),
    province		number(19),
    city		number(19),
    supervise_name	varchar2(100),
    job_num		varchar2(100),
    loginid		varchar2(100),
    password		varchar2(100),
    des			varchar2(300)
);

comment on table t_system_supervise is
'系统用户名管理';

comment on column t_system_supervise.id is
'主键id';

comment on column t_system_supervise.trade_name is
'店名';

comment on column t_system_supervise.bankid is
'金融机构';

comment on column t_system_supervise.bank_fen is
'金融机构分行';

comment on column t_system_supervise.bank_zhi is
'金融机构支行';

comment on column t_system_supervise.province is
'省';

comment on column t_system_supervise.city is
'市';

comment on column t_system_supervise.supervise_name is
'监管员姓名';

comment on column t_system_supervise.job_num is
'员工工号';

comment on column t_system_supervise.loginid is
'系统账号名称';

comment on column t_system_supervise.password is
'密码';

comment on column t_system_supervise.des is
'备注';
alter table t_system_supervise
  add constraint PK_t_system_supervise primary key (ID);

create sequence SEQ_T_SYSTEM_SUPERVISE
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
   't_system_supervise',
   'SEQ_T_SYSTEM_SUPERVISE',
   1,
   sysdate,
   sysdate,
   'luyang',
   '手动录入');
