-- Create table

create table T_CHECKSTOCK_MANAGE_PIC(
       csmid integer not null ,
       indexs integer  check(indexs > 0 and indexs < 21),
       picid integer not null
);

create table t_video_report_question(
   id integer not null,
   type integer not null,
   question_classify integer not null,
   question_desc     varchar2(1000) not null,
   depart            integer not null
);

create table T_RBAC_DEALERGROUP
(
  id         NUMBER(19) not null,
  name       VARCHAR2(50) not null,
  createtime DATE not null,
  modifytime DATE not null
);

create table T_RBAC_DEALERGROUP_DEALER
(
  groupid  NUMBER(19) not null,
  dealerid NUMBER(19) not null
);

create table T_RBAC_DEALERGROUP_USER
(
  groupid NUMBER(19) not null,
  userid  NUMBER(19) not null
);

create table T_CHECKSTOCK_MANAGE
(
  id                   NUMBER not null,
  dealerid             NUMBER not null,
  all_wh_count         NUMBER,
  wh_count             NUMBER,
  two_wh_count         NUMBER,
  move_count           NUMBER,
  actual_all_wh_count  NUMBER,
  actual_wh_count      NUMBER,
  actual_two_wh_count  NUMBER,
  actual_move_count    NUMBER,
  actual_out_count     NUMBER,
  result               NUMBER,
  check_date           DATE,
  normal_difference    VARCHAR2(500),
  un_normal_difference VARCHAR2(500),
  createdate           DATE,
  createuser           NUMBER,
  updatedate           DATE,
  updateuser           NUMBER,
  submitflag           NUMBER,
  checkstocktype       NUMBER
);

create table T_CHECKSTOCK_CAR
(
  id            NUMBER not null,
  checkstock_id NUMBER not null,
  vin           VARCHAR2(30) not null,
  model         VARCHAR2(50),
  color         VARCHAR2(30),
  currstate     NUMBER,
  actualstate   NUMBER
);

create table t_supervise_import_delete
(
  id                            number(19) not null,
  certificate_date    date,
  certificate_num    varchar2(100),
  car_model      varchar2(100),
  car_structure      varchar2(100),
  displacement      varchar2(100),
  color        varchar2(100),
  engine_num      varchar2(100),
  vin        varchar2(100),
  key_num      varchar2(100),
  money        varchar2(100),
  des        varchar2(100),
  deleteUserId	integer default 0,
  delete_date date
);

create table t_modechange_log
(
  id   NUMBER not null,
  dealerId Integer not null,
  beforeOperationMode varchar2(20),
  lastOperationMode varchar2(20),
  beforeSuperviseMoneyDate varchar2(20),
  lastSuperviseMoneyDate varchar2(20),
  modifyTime date, 
  changeSuperviseMoneyDate date,
  createUserId Integer,
  lastModifyUserId Integer,
  createDate date,
  lastModifyDate date
);



create table wic_20170603 as (select * from windcontrol_inspec_communion); 




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
  
  create sequence SEQ_MODECHANGE_LOG
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

  create sequence SEQ_T_CHECKSTOCK_CAR
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

  create sequence SEQ_T_CHECKSTOCK_MANAGE
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;  

	create sequence SEQ_T_RBAC_DEALERGROUP_USER
	minvalue 1
	maxvalue 999999999999999999999999999
	start with 1
	increment by 1
	cache 20;  
  
 create sequence SEQ_T_RBAC_DEALERGROUP
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

  create sequence SEQ_T_RBAC_DEALERGROUP_DEALER
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;