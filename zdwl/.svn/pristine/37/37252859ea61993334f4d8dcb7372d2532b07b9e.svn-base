------------cym----------------------2017-6-30 17:58:49--------------------------------------------------
update t_seqencedict set seqname = 'seq_t_current_dealer' where tablename = 't_current_dealer';

alter table T_TRANSFER_REPOSITORY add leavetime date;
alter table T_TRANSFER_REPOSITORY add leavenature varchar2(200);
alter table windcontrol_inspec_communion add (car_count_xx varchar2(10));
ALTER TABLE windcontrol_inspec_communion RENAME COLUMN car_count TO car_count_xy;
ALTER TABLE windcontrol_inspec_communion RENAME COLUMN car_count_xx TO car_count;
alter table windcontrol_inspec_communion drop column car_count_xy;
alter table  T_DRAFT   add bailscale varchar2(50) default 0;
alter table t_sign_record add (approveDate date);
alter table t_supervise_import  add  identifi number(19) default 0 ; 
alter table t_sign_record add (approveOpinion varchar2(250));
alter table t_video_report add (last_check_time varchar2(50));
alter table t_video_report add (supervisionMode varchar2(50));
alter table T_SUPERVISE_IMPORT add nextapproval number;
alter table t_sign_record  add nextapproval  INTEGER default 0;
alter table t_sign_record  add state   INTEGER default 1;
alter table t_video_report add (check_minute integer);
alter table t_video_report add (actual_stock integer);
alter table t_video_report add (way integer);
alter table t_video_report add (actual_way integer);
alter table t_video_report add (evaluate varchar2(1000));
alter table T_CHECKSTOCK_MANAGE add secretly_sale_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add secretly_move_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add way_sale_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add way_move_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add credit_line_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add exhibition_room_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_MANAGE add actual_abnormal_count NUMBER default 0 not null;
alter table T_CHECKSTOCK_CAR add remark varchar2(200) default '' not null;
alter table t_video_planinfo ADD  nextapproval INTEGER;  
alter table t_inspection_planinfo ADD  nextapproval INTEGER;
alter table t_rbac_user add  (createuserid INTEGER,upduserid integer,upddate date);
alter table T_OFFICE_EQUIPMENT add mouseRemark VARCHAR2(500);
alter table T_CHECKSTOCK_CAR add first_abnormal_time timestamp;
alter table T_OFFICE_EQUIPMENT add cameraRemark VARCHAR2(500);
alter table T_OFFICE_EQUIPMENT add headsetRemark varchar2(500);
alter table t_handover_log add (recipientCompanyName varchar2(1000));
alter table market_dealer_supervisor add bindtime date;
alter table T_TRANSFER_REPOSITORY add leavetime date;
alter table T_TRANSFER_REPOSITORY add leavenature varchar2(200);
alter table t_inspection_planinfo add lastupdatedate date;


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


-- Create/Recreate primary, unique and foreign key constraints 
alter table t_modechange_log
  add constraint PK_t_modechange_log primary key (ID);
create index index_t_modechange_log on t_modechange_log (dealerId);

create index INDEX_csmid on T_CHECKSTOCK_MANAGE_PIC(csmid);

create index INDEX_indexs on T_CHECKSTOCK_MANAGE_PIC(indexs);

create index index_csmid_indexs on T_CHECKSTOCK_MANAGE_PIC(csmid,indexs);

create index index_id on t_video_report_question(id);


-- Add comments to the columns 
comment on column T_TRANSFER_REPOSITORY.leavetime
  is '离岗时间';
comment on column T_TRANSFER_REPOSITORY.leavenature
  is '离岗性质'; 
comment on column T_OFFICE_EQUIPMENT.headsetRemark is '耳麦备注';
comment on column T_OFFICE_EQUIPMENT.cameraRemark is '摄像头备注'; 
comment on column T_OFFICE_EQUIPMENT.mouseRemark is '鼠标备注';
comment on column t_rbac_user.createuserid is '创建人';
comment on column t_rbac_user.upduserid is '修改人';
comment on column t_rbac_user.upddate is '修改时间';
comment on column T_CHECKSTOCK_CAR.remark is '备注';
comment on column T_CHECKSTOCK_CAR.first_abnormal_time is '首次异常时间';
comment on column T_CHECKSTOCK_MANAGE.actual_abnormal_count is '异常合计';
comment on column T_CHECKSTOCK_MANAGE.exhibition_room_count is '展厅';
comment on column T_CHECKSTOCK_MANAGE.credit_line_count is '信誉额度';
comment on column T_CHECKSTOCK_MANAGE.way_move_count is '在途移动';
comment on column T_CHECKSTOCK_MANAGE.way_sale_count is '在途销售';
comment on column T_CHECKSTOCK_MANAGE.secretly_sale_count is '私自销售';
comment on column T_CHECKSTOCK_MANAGE.secretly_move_count is '私自移动';
comment on column t_video_report.evaluate is '评价';
comment on column t_video_report.actual_way is '实际在途';
comment on column t_video_report.way is '在途';
comment on column t_video_report.actual_stock is '实际库存';
comment on column t_video_report.check_minute is '实际检查用时(分钟)';
comment on column T_SUPERVISE_IMPORT.nextapproval is '下一审批人';
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
comment on table t_video_report_question is '视频报告问题表';
comment on column t_video_report_question.id is '视频报告id';
comment on column t_video_report_question.type is '分类 1:电脑、保险柜、打印机、传真机、网络…2设备接收确认书、作息时间表3委任书、授权书、经销商告知函' ;
comment on column t_video_report_question.question_classify is '问题分类';
comment on column t_video_report_question.question_desc is '问题描述';
comment on column t_video_report_question.depart is '所属部门';
comment on column T_TRANSFER_REPOSITORY.leavetime
  is '离岗时间';
comment on column T_TRANSFER_REPOSITORY.leavenature
  is '离岗性质';  
comment on table T_CHECKSTOCK_MANAGE_PIC is '日查库照片表';
comment on column T_CHECKSTOCK_MANAGE_PIC.csmid is 'T_CHECKSTOCK_MANAGE主键ID';
comment on column T_CHECKSTOCK_MANAGE_PIC.picid is 'PIC主键ID';
comment on column T_CHECKSTOCK_MANAGE_PIC.indexs is '图片序号';

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

comment on table t_supervise_import_delete is
'监管物导入删除日志表';
comment on column t_supervise_import_delete.deleteUserId
  is '删除人ID';
  comment on column t_supervise_import_delete.delete_date
  is '删除时间';
comment on column t_supervise_import_delete.certificate_date
  is '合格证发证日期';
comment on column t_supervise_import_delete.certificate_num
  is '合格证号';
comment on column t_supervise_import_delete.car_model
  is '车辆型号';
comment on column t_supervise_import_delete.car_structure
  is '车身结构';
comment on column t_supervise_import_delete.displacement
  is '排量';
comment on column t_supervise_import_delete.color
  is '颜色';
comment on column t_supervise_import_delete.engine_num
  is '发动机号';
comment on column t_supervise_import_delete.vin
  is '车架号';
comment on column t_supervise_import_delete.key_num
  is '钥匙号';
comment on column t_supervise_import_delete.money
  is '金额';
comment on column t_supervise_import_delete.des
  is '备注';
  
  comment on table t_modechange_log
  is '经销商操作模式变更日志';
-- Add comments to the columns 
comment on column t_modechange_log.id
  is '主键ID';
comment on column t_modechange_log.dealerId
  is '经销商ID';
comment on column t_modechange_log.beforeOperationMode
  is '变更前操作模式';
comment on column t_modechange_log.lastOperationMode
  is '变更后操作模式';
comment on column t_modechange_log.beforeSuperviseMoneyDate
  is '变更前监管费';
comment on column t_modechange_log.lastSuperviseMoneyDate
  is '变更后监管费';
comment on column t_modechange_log.modifyTime
  is '操作模式变更时间';
comment on column t_modechange_log.changeSuperviseMoneyDate
  is '监管费变更时间';
comment on column t_modechange_log.createUserId
  is '创建人id';
comment on column t_modechange_log.lastModifyUserId
  is '最后修改人id';
comment on column t_modechange_log.createDate
  is '创建时间';
comment on column t_modechange_log.lastModifyDate
  is '最后修改时间';
  comment on column   T_DRAFT.bailscale  is '保证金比例';
  comment on column t_sign_record.approveDate is '审批时间';
comment on column t_sign_record.approveOpinion is '审批意见';
  comment on column t_video_report.supervisionMode is '操作模式';
comment on column t_video_report.last_check_time is '最近检查时间';
COMMENT ON COLUMN t_supervise_import.identifi IS '车辆状态标识';
  


update T_MESSAGE MSG 
set MSG.name = (select count(id) from T_WARING_INFO_USER WIU where WIU.USERID=MSG.USERID  AND MSG.TYPE=WIU.MSGTYPE);

update t_video_report set check_minute = 0 where check_minute is null;
update t_video_report set actual_stock = 0 where actual_stock is null;
update t_video_report set way = 0 where way is null;
update t_video_report set actual_way = 0 where actual_way is null;

update windcontrol_inspec_result set danger_level = 0 where danger_level is null;
update windcontrol_inspec_result set dealer_level = 0 where dealer_level is null;
update windcontrol_inspec_result set supervisor_level = 0 where supervisor_level is null;

update t_supervise_import t set t.NEXTAPPROVAL=0 where t.NEXTAPPROVAL is null;

update t_inspection_planinfo set lastupdatedate = createdate;

update windcontrol_inspec_result set dealer_level=0 where dealer_level is null;

update t_supervise_import t set  t.nextApproval = 31 where t.state = 2 and t.apply = 1 ;
update  t_supervise_import set  nextApproval = 0;
 update T_DRAFT d set d.bailscale=0;
 update t_rbac_resource set state = 2 where id in (58,443);
  
  ----------------备注-----------------
 -- T_EXTEND_DISTRIBSET表中的SPECIAL_OPER字段长度更改为varchar2(80)
  
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

--授权菜单动作
--1.给招聘专员zpzy、监管员管理部经理jgyjl、运营管理部部长 yyglbbz、物流金融中心总监wljrzxzj       分配      账号管理菜单权限。-----sxs-----
--2.给“综合专员”，“调配专员”、“监管员管理部经理”、分配  交接记录管理台账功能
-- 链接：/handoverLog.do?method=HandoverRecordManagementLedgerList
--3.给“调配专员”、“监管员管理部经理”、分配   轮岗计划表台账
--链接：/handoverPlan.do?method=HandoverPlanLedgerList
--4.给“薪酬专员”、“综合专员”、“监管员管理部经理”、“业务专员”分配 资料邮寄费用台账
--链接：/dataMailcost.do?method=dataMailingFeeList

--部署授权菜单
--1、监管物管理一级菜单增加删车功能，给系统管理员分配该菜单权限。
--URL:/superviseImport.do?method=searchCarInfo

--2、在台账管理一级菜单下，增加经销商考勤时间台账功能，分配菜单权限给风控部经理、业务部专员和业务部经理。
--URL:/attSign.do?method=attendanceTime
