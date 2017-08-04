----------------------置顶-------(后面代码贴到下面)------------------------------------------------
--command window 下运行  
--解决sql包含&字符时弹出变量框 
set define off;


--------------sxs------------------------2017-7-2 17:43:11---------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706300001,'zddb','t_payment','seq_t_payment','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706300002,'zddb','t_payment_info','seq_t_paymentinfo','1',SYSDATE,SYSDATE,'sxs','手动录入');

-------LXH-------------------------2017-6-28 16:12-----------------------------------------------
需求233
insert into t_rbac_role (id,state,role_name,client_type,des)values(SEQ_T_RBAC_ROLE.NEXTVAL,1,'经销商集团',14,'经销商集团');

---------sxs----------------------------2017-6-29 16:41:40---------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290004,'zddb','T_ATTENDANCE','SEQ_T_ATTENDANCE','1',SYSDATE,SYSDATE,'sxs','手动录入');


--------cym---------------------------2017-6-29 11:30:53---------------------------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290003,'zddb','t_extrawork_apply','SEQ_T_EXTRAWORK_APPLY','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290002,'zddb','t_leave_replace','seq_t_leave_replace','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290001,'zddb','t_supervisory_leaveApply','seq_t_supervisory_leaveapply','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;
-------cym-------------------------2017-6-27 15:54:53-----------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706270001,'zddb','T_SIGN_RECORD','seq_t_sign_record','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;

-------sxs------------------------2017-6-26 11:43:59-----------------------------------------------

insert into t_rbac_resource (id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(SEQ_T_RBAC_RESOURCE.Nextval,1,2,1,37,10,'业务进驻流转单进驻前3天未匹配人员提醒','业务进驻流转单进驻前3天未匹配人员提醒','/message.do?method=messagePageList&messagequery.type=108','业务进驻流转单进驻前3天未匹配人员提醒');

insert into t_rbac_role_resource(id,resource_id,role_id)  select SEQ_T_RBAC_ROLE_RESOURCE.Nextval as id,id as resource_id,80 as role_id from t_rbac_resource where resource_name like '%业务进驻流转单进驻前3天未匹配人员提醒%';



----------------sxs---------------------2017-6-18 22:03:10------------------------------------------
--add ---------t_modechange_log.sql------------------------------------------------------------

-------------------sxs-----------------2017-6-18 18:24:37---------------------------------------------------


update T_MESSAGE MSG 
set MSG.name = (select count(id) from T_WARING_INFO_USER WIU where WIU.USERID=MSG.USERID  AND MSG.TYPE=WIU.MSGTYPE);


------------------cym------------------2017-6-15 21:15:18---------------------------------------------------
T_EXTEND_DISTRIBSET表中的SPECIAL_OPER字段长度更改为varchar2(80)

------------------cym------------------2017-6-15 21:03:05--------------------------------------------------
create table wic_20170603 as (select * from windcontrol_inspec_communion); 

alter table windcontrol_inspec_communion add (car_count_xx varchar2(10));

update windcontrol_inspec_communion set car_count_xx = car_count;

ALTER TABLE windcontrol_inspec_communion RENAME COLUMN car_count TO car_count_xy;
ALTER TABLE windcontrol_inspec_communion RENAME COLUMN car_count_xx TO car_count;

alter table windcontrol_inspec_communion drop column car_count_xy;
commit;

-----------------sxs--------------2017-6-14 21:54:34--------------------------------------
--为所有角色授权公告菜单
insert into t_rbac_role_resource(id,role_id,resource_id) (select seq_t_rbac_role_resource.nextval, id,145 from t_rbac_role where id not in (select role_id from t_rbac_role_resource where resource_id = 145))

update t_video_report set check_minute = 0 where check_minute is null;
update t_video_report set actual_stock = 0 where actual_stock is null;
update t_video_report set way = 0 where way is null;
update t_video_report set actual_way = 0 where actual_way is null;

-- 处理生产问题
update windcontrol_inspec_result set danger_level = 0 where danger_level is null;
update windcontrol_inspec_result set dealer_level = 0 where dealer_level is null;
update windcontrol_inspec_result set supervisor_level = 0 where supervisor_level is null;

---------------cym-----2017-6-11 18:34:24----------------------------------------------

update t_supervise_import t set t.NEXTAPPROVAL=0 where t.NEXTAPPROVAL is null;
commit;


insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110007,'zddb','T_CHECKSTOCK_CAR','seq_t_checkstock_car','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110006,'zddb','T_CHECKSTOCK_MANAGE','seq_t_checkstock_manage','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;

---------------cym-----2017-6-11 17:45:55----------------------------------------------

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110005,'zddb','market_dealer_bank','seq_market_dealer_bank','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;
----------------cym----2017-6-11 17:26:02----------------------------------------------

update windcontrol_inspec_result set dealer_level=0 where dealer_level is null;
commit;

----------------cym-----2017-6-9 16:20:43--------------------------------------------

alter table t_sign_record add (approveDate date);
comment on column t_sign_record.approveDate is '审批时间';

alter table t_sign_record add (approveOpinion varchar2(250));
comment on column t_sign_record.approveOpinion is '审批意见';

----------sxs------------------------2017-6-11 15:10:56----------------------------------

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110004,'zddb','t_extrawork_apply','SEQ_T_EXTRAWORK_APPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');


insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110001,'zddb','t_resign_apply','SEQ_T_RESIGN_APPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110002,'zddb','t_current_dealer','t_current_dealer','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110003,'zddb','t_supervisory_leaveapply','SEQ_T_SUPERVISORY_LEAVEAPPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');


----------sxs-----------------------2017-6-10 22:13:15----------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706100001,'zddb','t_rbac_dealergroup','SEQ_T_RBAC_DEALERGROUP','1',SYSDATE,SYSDATE,'sxs','手动录入');



-----------中都未执行SQL-------sxs------------2017-6-9 15:31:49--------------------------
-- 给业务专员分配监管物入库审批菜单

--系统管理与配置--> 经销商集团管理
--/rbac/dealerGroup.do?method=groupList
--分配角色：超级管理员


--监管物管理--> 日查库管理新
--/supervisor/checkStockManage.do?method=findList
--分配角色：监管员

--台账管理-->日查库管理新台账
--/ledger/checkStockManage.do?method=findList
--分配角色：监管员



-- Add/modify columns 
alter table T_SUPERVISE_IMPORT add nextapproval number;
-- Add comments to the columns 
comment on column T_SUPERVISE_IMPORT.nextapproval is '下一审批人';
  
  
  
-- 给业务专员分配监管物入库审批菜单
  
update  t_supervise_import set  nextApproval = 0;
 
 --(将查询结果的nextApproval )修改为31
update t_supervise_import t set  t.nextApproval = 31 where t.state = 2 and t.apply = 1 ;


alter table  T_DRAFT   add bailscale varchar2(50) default 0;
comment on column   T_DRAFT.bailscale  is '保证金比例';

 update T_DRAFT d set d.bailscale=0;
 
 
 -- Create table
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
)
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
-- Add comments to the columns 
comment on column T_CHECKSTOCK_MANAGE.id
  is '主键ID';
comment on column T_CHECKSTOCK_MANAGE.dealerid
  is '经销商ID';
comment on column T_CHECKSTOCK_MANAGE.all_wh_count
  is '在库车辆';
comment on column T_CHECKSTOCK_MANAGE.wh_count
  is '本库车辆';
comment on column T_CHECKSTOCK_MANAGE.two_wh_count
  is '二库车辆';
comment on column T_CHECKSTOCK_MANAGE.move_count
  is '移动车辆';
comment on column T_CHECKSTOCK_MANAGE.actual_all_wh_count
  is '实盘在库';
comment on column T_CHECKSTOCK_MANAGE.actual_wh_count
  is '实盘本库';
comment on column T_CHECKSTOCK_MANAGE.actual_two_wh_count
  is '实盘二库';
comment on column T_CHECKSTOCK_MANAGE.actual_move_count
  is '实盘移动';
comment on column T_CHECKSTOCK_MANAGE.actual_out_count
  is '实盘出库';
comment on column T_CHECKSTOCK_MANAGE.result
  is '盘点结果与实际库存差异 1:一致  2：差异
';
comment on column T_CHECKSTOCK_MANAGE.check_date
  is '盘点时间';
comment on column T_CHECKSTOCK_MANAGE.normal_difference
  is '常规差异';
comment on column T_CHECKSTOCK_MANAGE.un_normal_difference
  is '非常规差异';
comment on column T_CHECKSTOCK_MANAGE.createdate
  is '创建时间';
comment on column T_CHECKSTOCK_MANAGE.createuser
  is '创建人';
comment on column T_CHECKSTOCK_MANAGE.updatedate
  is '修改时间';
comment on column T_CHECKSTOCK_MANAGE.updateuser
  is '修改人';
comment on column T_CHECKSTOCK_MANAGE.submitflag
  is '提交标识 1:未提交 2:已提交';
comment on column T_CHECKSTOCK_MANAGE.checkstocktype
  is '查库方式1：手工查库，2：设备查库';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CHECKSTOCK_MANAGE
  add constraint CHECKSTOCK_MANAGE_PK_ID primary key (ID)
  using index 
  tablespace ZD_SPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  create sequence SEQ_T_CHECKSTOCK_MANAGE
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
  ('201704130001',
   'zddb',
   'T_CHECKSTOCK_MANAGE',
   'SEQ_T_CHECKSTOCK_MANAGE',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');

  
  
  -- Create table
create table T_CHECKSTOCK_CAR
(
  id            NUMBER not null,
  checkstock_id NUMBER not null,
  vin           VARCHAR2(30) not null,
  model         VARCHAR2(50),
  color         VARCHAR2(30),
  currstate     NUMBER,
  actualstate   NUMBER
)
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
-- Add comments to the columns 
comment on column T_CHECKSTOCK_CAR.id
  is '主键ID';
comment on column T_CHECKSTOCK_CAR.checkstock_id
  is '日查库ID';
comment on column T_CHECKSTOCK_CAR.vin
  is '车架号';
comment on column T_CHECKSTOCK_CAR.model
  is '型号';
comment on column T_CHECKSTOCK_CAR.color
  is '颜色';
comment on column T_CHECKSTOCK_CAR.currstate
  is '车辆当前状态  1:本库  2：二库 3：移动
';
comment on column T_CHECKSTOCK_CAR.actualstate
  is '车辆实际状态  1:本库  2：二库 3：移动 4：出库
';
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_CHECKSTOCK_CAR
  add constraint CHECKSTOCK_CAR_PK_ID primary key (ID)
  using index 
  tablespace ZD_SPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

  
create sequence SEQ_T_CHECKSTOCK_CAR
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
  ('201704130001',
   'zddb',
   'T_CHECKSTOCK_CAR',
   'SEQ_T_CHECKSTOCK_CAR',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
 
 
 
 
 ---

-- Create table
create table T_RBAC_DEALERGROUP
(
  id         NUMBER(19) not null,
  name       VARCHAR2(50) not null,
  createtime DATE not null,
  modifytime DATE not null
)
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
-- Create/Recreate primary, unique and foreign key constraints 
alter table T_RBAC_DEALERGROUP
  add constraint PK_RBAC_DEALERGROUP primary key (ID)
  using index 
  tablespace ZD_SPC
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


create sequence SEQ_T_RBAC_DEALERGROUP
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
  ('201704140001',
   'zddb',
   'T_RBAC_DEALERGROUP',
   'SEQ_T_RBAC_DEALERGROUP',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
 

-- Create table
create table T_RBAC_DEALERGROUP_DEALER
(
  groupid  NUMBER(19) not null,
  dealerid NUMBER(19) not null
)
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
  
  
  create sequence SEQ_T_RBAC_DEALERGROUP_DEALER
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
  ('201704140002',
   'zddb',
   'T_RBAC_DEALERGROUP_DEALER',
   'SEQ_T_RBAC_DEALERGROUP_DEALER',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
  
  -- Create table
create table T_RBAC_DEALERGROUP_USER
(
  groupid NUMBER(19) not null,
  userid  NUMBER(19) not null
)
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

  
	create sequence SEQ_T_RBAC_DEALERGROUP_USER
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
  ('201704140003',
   'zddb',
   'T_RBAC_DEALERGROUP_USER',
   'SEQ_T_RBAC_DEALERGROUP_USER',
   1,
   sysdate,
   sysdate,
   'macongcong',
   '手动添加');
  






----------------lxh-----2017-6-9 9:25-----------------------------
alter table t_supervise_import  add  identifi number(19) default 0 ; 

COMMENT ON COLUMN t_supervise_import.identifi IS '车辆状态标识';
----------------sxs-----2017-6-7 16:20:48--------------------------------------------

alter table t_video_report add (last_check_time varchar2(50));
comment on column t_video_report.last_check_time is '最近检查时间';

alter table t_video_report add (supervisionMode varchar2(50));
comment on column t_video_report.supervisionMode is '操作模式';

create table t_video_report_question(
   id integer not null,
   type integer not null,
   question_classify integer not null,
   question_desc     varchar2(1000) not null,
   depart            integer not null
);


create index index_id on t_video_report_question(id);

comment on table t_video_report_question is '视频报告问题表';
comment on column t_video_report_question.id is '视频报告id';
comment on column t_video_report_question.type is '分类 1:电脑、保险柜、打印机、传真机、网络…2设备接收确认书、作息时间表3委任书、授权书、经销商告知函' ;
comment on column t_video_report_question.question_classify is '问题分类';
comment on column t_video_report_question.question_desc is '问题描述';
comment on column t_video_report_question.depart is '所属部门';





-------------------------------2017-6-7 16:20:19 之前-------------------------------------
--alter table column
alter table t_sign_record  add nextapproval  INTEGER default 0;
alter table t_sign_record  add state   INTEGER default 1;

---------sxs---------s2017-6-5 15:43:44---------------------------------------------

alter table t_video_report add (check_minute integer);
comment on column t_video_report.check_minute is '实际检查用时(分钟)';


alter table t_video_report add (actual_stock integer);
comment on column t_video_report.actual_stock is '实际库存';

alter table t_video_report add (way integer);
comment on column t_video_report.way is '在途';

alter table t_video_report add (actual_way integer);
comment on column t_video_report.actual_way is '实际在途';

alter table t_video_report add (evaluate varchar2(1000));
comment on column t_video_report.evaluate is '评价';


﻿----------sxs--------2017-6-1 10:32:20---------------------


alter table T_CHECKSTOCK_MANAGE add secretly_move_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.secretly_move_count is '私自移动';

alter table T_CHECKSTOCK_MANAGE add secretly_sale_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.secretly_sale_count is '私自销售';

alter table T_CHECKSTOCK_MANAGE add way_sale_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.way_sale_count is '在途销售';

alter table T_CHECKSTOCK_MANAGE add way_move_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.way_move_count is '在途移动';

alter table T_CHECKSTOCK_MANAGE add credit_line_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.credit_line_count is '信誉额度';

alter table T_CHECKSTOCK_MANAGE add exhibition_room_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.exhibition_room_count is '展厅';

alter table T_CHECKSTOCK_MANAGE add actual_abnormal_count NUMBER default 0 not null;
comment on column T_CHECKSTOCK_MANAGE.actual_abnormal_count is '异常合计';

alter table T_CHECKSTOCK_CAR add first_abnormal_time timestamp;
comment on column T_CHECKSTOCK_CAR.first_abnormal_time is '首次异常时间';

alter table T_CHECKSTOCK_CAR add remark varchar2(200) default '' not null;
comment on column T_CHECKSTOCK_CAR.remark is '备注';


----------sxs---------2017-5-29 21:14:48---之前---------------


﻿--添加列下一个审批人
alter table t_video_planinfo ADD  nextapproval INTEGER;  
alter table t_inspection_planinfo ADD  nextapproval INTEGER;

--授权菜单动作
--1.给招聘专员zpzy、监管员管理部经理jgyjl、运营管理部部长 yyglbbz、物流金融中心总监wljrzxzj       分配      账号管理菜单权限。-----sxs-----
--2.给“综合专员”，“调配专员”、“监管员管理部经理”、分配  交接记录管理台账功能
-- 链接：/handoverLog.do?method=HandoverRecordManagementLedgerList
--3.给“调配专员”、“监管员管理部经理”、分配   轮岗计划表台账
--链接：/handoverPlan.do?method=HandoverPlanLedgerList
--4.给“薪酬专员”、“综合专员”、“监管员管理部经理”、“业务专员”分配 资料邮寄费用台账
--链接：/dataMailcost.do?method=dataMailingFeeList

--部署授权菜单
1、监管物管理一级菜单增加删车功能，给系统管理员分配该菜单权限。
URL:/superviseImport.do?method=searchCarInfo

2、在台账管理一级菜单下，增加经销商考勤时间台账功能，分配菜单权限给风控部经理、业务部专员和业务部经理。
URL:/attSign.do?method=attendanceTime



--add---2017-5-26 15:51:27---sxs----

-- Don't forget commit;

INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES (81,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=81',0,1,1);--银行移动审批提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(82,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=82',0,1,1);--开票10个工作日未到车提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(83,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=83',0,1,1);--开票30天汇票未押满 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(84,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=84',0,1,1);--汇票到期前7天未清票提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(85,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=85',0,1,1);--银行释放审批提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(86,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=86',0,1,1);--零库存零汇票提醒  银行审批人
 
 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(87,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=87',0,1,1);--最后一笔业务提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(88,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=88',0,1,1);--连续三天无业务发生提醒  银行审批人
 
 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(89,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=89',0,1,1);--移动车辆超过25天未处理提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(90,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=90',0,1,1);--移动车辆超过总库存20%提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(91,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=91',0,1,1);--异常车辆超过总库存15%提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(92,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=92',0,2,1);--开票15个工作日未到车预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(93,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=93',0,2,1);--开票45天汇票未押满预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(94,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=94',0,2,1);--汇票到期当天未清票预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(95,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=95',0,2,1);--零库存零汇票延续7天预警  银行审批人   

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(96,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=96',0,2,1);--连续五天无业务发生预警  银行审批人  

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(97,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=97',0,2,1);--移动车辆超过30天未处理预警  银行审批人  

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(98,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=98',0,2,1);--异常车辆超过总库存20%预警  银行审批人 


--授予银行审批人
--银行释放审批提醒
--银行移动审批提醒
--开票10个工作日未到车提醒
--开票30天汇票未押满
--汇票到期前7天未清票提醒
--零库存零汇票提醒
--最后一笔业务提醒
--连续三天无业务发生提醒
--移动车辆超过25天未处理提醒
--移动车辆超过总库存20%提醒
--异常车辆超过总库存15%提醒
--开票15个工作日未到车预警
--开票45天汇票未押满预警
--汇票到期当天未清票预警
--零库存零汇票延续7天预警
--连续五天无业务发生预警
--移动车辆超过30天未处理预警
--异常车辆超过总库存20%预警
--菜单      
      
insert into t_rbac_role_resource(id,resource_id,role_id) 
(
 select 
       SEQ_T_RBAC_ROLE_RESOURCE.Nextval,id,31 
 from 
       t_rbac_resource 
 where 
       resource_name in(
       '银行释放审批提醒'
       ,'银行移动审批提醒'
       ,'开票10个工作日未到车提醒'
       ,'开票30天汇票未押满'
       ,'汇票到期前7天未清票提醒'
       ,'零库存零汇票提醒'
       ,'最后一笔业务提醒'
       ,'连续三天无业务发生提醒'
       ,'移动车辆超过25天未处理提醒'
       ,'移动车辆超过总库存20%提醒'
       ,'异常车辆超过总库存15%提醒'
       ,'开票15个工作日未到车预警'
       ,'开票45天汇票未押满预警'
       ,'汇票到期当天未清票预警'
       ,'零库存零汇票延续7天预警'
       ,'连续五天无业务发生预警'
       ,'移动车辆超过30天未处理预警'
       ,'异常车辆超过总库存20%预警') 
       and state = 1 and resource_url is not null and id not in (58,443));  
       
       
--停用两个相同的 '开票30天汇票未押满' 菜单
update t_rbac_resource set state = 2 where id in (58,443);
       
       
---------------2017年5月26日 17:26:37   之前------------------  


-- 给“监管员”、“监管员管理部经理”，“薪酬专员”、“综合专员”分配“设备维修费用申请”菜单权限
-- 去掉视频部数据专员的 视频检查计划 和 视频检查报告 菜单权限
-- 给风险部外控专员添加菜单巡检计划


--用户添加日志  创建人   修改人  修改时间
alter table t_rbac_user add  (createuserid INTEGER,upduserid integer,upddate date);

comment on column t_rbac_user.createuserid is '创建人';
comment on column t_rbac_user.upduserid is '修改人';
comment on column t_rbac_user.upddate is '修改时间';
     



--办公设备添加鼠标备注字段
alter table T_OFFICE_EQUIPMENT add mouseRemark VARCHAR2(500);
comment on column T_OFFICE_EQUIPMENT.mouseRemark is '鼠标备注';

--办公设备添加摄像头备注字段
alter table T_OFFICE_EQUIPMENT add cameraRemark VARCHAR2(500);
comment on column T_OFFICE_EQUIPMENT.cameraRemark is '摄像头备注'; 

--办公设备添加耳麦备注字段
alter table T_OFFICE_EQUIPMENT add headsetRemark varchar2(500);
comment on column T_OFFICE_EQUIPMENT.headsetRemark is '耳麦备注';


-- T_HANDOVER_LOG 添加 “接收方监管公司名称”字段
alter table t_handover_log add (recipientCompanyName varchar2(1000));

-- market_dealer_supervisor添加绑定时间字段
alter table market_dealer_supervisor add bindtime date;

-- 新增t_inspection_planinfo最后更新时间
alter table t_inspection_planinfo add lastupdatedate date;

update t_inspection_planinfo set lastupdatedate = createdate;

alter table T_TRANSFER_REPOSITORY add leavetime date;
alter table T_TRANSFER_REPOSITORY add leavenature varchar2(200);
comment on column T_TRANSFER_REPOSITORY.leavetime
  is '离岗时间';
comment on column T_TRANSFER_REPOSITORY.leavenature
  is '离岗性质'; 
