-----------------------新增菜单-----------------------------2017-6-30 10:29:01---------------------------------------------------------------------------------------------
--insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
--values(seq_t_rbac_resource.nextval,1,1,0,0,'1','薪酬管理','薪酬管理',null,'薪酬管理');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '84', '经销商撤店台账', '经销商撤店台账',
--'/market/dealerExit.do?method=DealerWithdrawalLedger',
 --'台账管理-经销商撤店台账');

--父级菜单：台账管理
--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '80', '业务流转单台账', '业务流转单台账', '/ledger/businessTransfer.do?method=findAllList',
-- '台账管理-业务流转单台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '81', '项目进驻流转单台账', '项目进驻流转单台账', '/market/projectCirculationForm.do?method=marketProjectListLedger',
-- '台账管理-项目进驻流转单台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '82', '经销商/金融机构绑定台账', '经销商/金融机构绑定台账',
-- '/market/bindDealer.do?method=dealerFinancialInstitutionBindingLedger',
-- '台账管理-经销商/金融机构绑定台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '83', '经销商/金融机构拆绑台账', '经销商/金融机构拆绑台账',
-- '/market/unbindDealer.do?method=dealerFinancialInstitutionLedger',
-- '台账管理-经销商/金融机构拆绑台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '84', '经销商撤店台账', '经销商撤店台账',
 --'/market/dealerExit.do?method=DealerWithdrawalLedger',
-- '台账管理-经销商撤店台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '85', '交接记录管理台账', '交接记录管理台账', 
--'/handoverLog.do?method=HandoverRecordManagementLedgerList',
-- '台账管理-交接记录管理台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '86', '加班申请台账', '加班申请台账', 
--'/ledger/extraworkApply.do?method=findPageList',
-- '台账管理-加班申请台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '87', '请假申请台账', '请假申请台账', 
--'/ledger/leaveApply.do?method=findPageList',
 --'台账管理-请假申请台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '88', '辞职申请台账', '辞职申请台账', 
--'/ledger/resignApply.do?method=findPageList',
-- '台账管理-辞职申请台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval,  1, 2, 1, 45, '89', '轮岗计划表台账', '轮岗计划表台账',
-- '/handoverPlan.do?method=HandoverPlanLedgerList', 
--'台账管理-轮岗计划表台账');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '90', '监管费变更管理台账', '监管费变更管理台账', '/market/expenseChange.do?method=managementLedger', '市场部');


--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '91','监管费发票管理台账', '监管费发票管理台账', '/market/invoice.do?method=draftVote', '市场部');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45,'92', '监管费退费管理台账', '监管费退费管理台账', '/market/refund.do?method=superviseRefund', '市场部');

--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45,'93', '经销商考勤时间台账', '经销商考勤时间台账', '/attTime.do?method=findDealerList', '经销商考勤时间台账');


--insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
--values (seq_t_rbac_resource.nextval, 1, 2, 1, 45, '23', '交接记录管理台账', '交接记录管理台账', '/handoverLog.do?method=HandoverRecordManagementLedgerList', '交接记录管理台账');
-----------------------------------------------------------以上完成--------------------------------------------------------------------------------------------------------------------------
--一级菜单
insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(seq_t_rbac_resource.nextval,1,1,0,0,'1','制度与流程','制度流程',null,'');

insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(seq_t_rbac_resource.nextval,1,1,0,0,'1','考勤管理','考勤管',null,'考勤管');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values (seq_t_rbac_resource.nextval, 1, 1, 0, 0, '150', '视频管理', '视频管理', null, '视频管理');

insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(seq_t_rbac_resource.nextval,1,1,0,0,'1','来电记录管理','来电记录管理',null,'来电记录管理');

--父级菜单：来电记录管理   原名：投诉记录信息管理
insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 
(select id from t_rbac_resource where RESOURCE_NAME='来电记录管理' and RESOURCE_SHORTNAME='来电记录管理' 
and des='来电记录管理'), '107', '来电记录单', '来电记录单', '/complaint.do?method=findList', '来电记录单');

--父级菜单：监管员信息管理
insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 44,'32', '辞职申请', '辞职申请', '/resignApply.do?method=findPageList', '辞职申请');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1,44,'34', '请假申请', '请假申请', '/leaveApply.do?method=findPageList', '请假申请');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval,  1, 2, 1,44, '33', '加班申请', '加班申请', '/extraworkApply.do?method=findPageList', '加班申请');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values (seq_t_rbac_resource.nextval, 1, 2, 1, 44, '5', '经销商考勤时间', '经销商考勤时间', '/attSign.do?method=attendanceTime', null);


--父级菜单：台账管理
insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 45, '128', '考勤每日详情台账', '考勤每日详情台账', '/attendance.do?method=ToExtSignRecordCheckSpList',
 '考勤每日详情台账');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval,  1, 2, 1,45,'16', '考勤表台帐', '考勤表台帐', '/ledger/attendance.do?method=findLedgerList', '考勤表台帐');

--父级菜单：制度与流程
insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(seq_t_rbac_resource.nextval,1,2,1,
(select id from t_rbac_resource where resource_name='制度与流程' and resource_shortname='制度流程'),
'1','操作流程管理','操作流','/flow.do?method=flowList','');


insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(seq_t_rbac_resource.nextval,1,2,1,
(select id from t_rbac_resource where resource_name='制度与流程' and resource_shortname='制度流程'),
'1','制度文件管理','制度文件','/notice/noticeContent.do?method=noticeContentList&query.pageType=1&query.contentType=2','');

--父级菜单：考勤管理
insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 
(select id from t_rbac_resource where RESOURCE_NAME='考勤管理' and RESOURCE_SHORTNAME='考勤管' and parent_id=0 and node_type=0 and des='考勤管'),
 '166', '考勤表', '考勤表', '/attendance.do?method=findAttendanceList', '考勤表');


insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 
(select id from t_rbac_resource where RESOURCE_NAME='考勤管理' and RESOURCE_SHORTNAME='考勤管' and parent_id=0 and node_type=0 and des='考勤管'),
 '20', '考勤每日详情', '考勤日详情', '/attendance.do?method=SignRecordCheckSpList', '考勤日详情');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1,
(select id from t_rbac_resource where RESOURCE_NAME='考勤管理' and RESOURCE_SHORTNAME='考勤管' and parent_id=0 and node_type=0 and des='考勤管'),
 '34', '请假申请', '请假申请', '/leaveApply.do?method=findPageList', '请假申请');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval, 1, 2, 1, 
(select id from t_rbac_resource where RESOURCE_NAME='考勤管理' and RESOURCE_SHORTNAME='考勤管' and parent_id=0 and node_type=0 and des='考勤管'),
 '32', '辞职申请', '辞职申请', '/resignApply.do?method=findPageList', '辞职申请');

insert into T_RBAC_RESOURCE (ID, STATE, RESOURCE_LEVEL, NODE_TYPE, PARENT_ID, RESOURCE_INDEX, RESOURCE_NAME, RESOURCE_SHORTNAME, RESOURCE_URL, DES)
values(seq_t_rbac_resource.nextval,  1, 2, 1,
(select id from t_rbac_resource where RESOURCE_NAME='考勤管理' and RESOURCE_SHORTNAME='考勤管' and parent_id=0 and node_type=0 and des='考勤管'),
  '33', '加班申请', '加班申请', '/extraworkApply.do?method=findPageList', '加班申请');



-----------------------------------------------------------------update-------------------------------------------------------------------------------------------

update t_rbac_resource set resource_name='巡检检查报告台账' where id=911 and parent_id=45;
update t_rbac_resource set resource_name='花名册台账' where id=121 and parent_id=45;

commit;

-----------------------关联表-----------------------------2017-6-30 10:28:07-------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 考勤专员role_id=8
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='辞职申请台账' and parent_id=45),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,911,8); 
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 考勤专员role_id=5

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45 ),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45 ),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45 ),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='交接记录管理台账' and parent_id=45 ),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='轮岗计划表台账' and parent_id=45),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,911,5); --巡检检查报告台账
-----------------------------------------------------------------------------------------------------------------------------------------------------------

--添加资源与角色关联SQL -- 招聘专员role_id=4

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),4);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),4);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),4);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),4);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 培训专员role_id=7

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),7);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),7);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),7);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),7);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,7);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,7);

-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 业务经理role_id=16
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),16);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),16);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),16);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),16);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,16);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,16);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),16);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 16);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 业务专员role_id=14
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),14);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),14);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),14);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),14);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,14);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),14);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 14);

-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 风险管理部部长role_id=26
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45 ),26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),26);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),26);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商考勤时间台账' 
and resource_url='/attTime.do?method=findDealerList' and resource_index='93' ),26);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 风控部经理role_id=20
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账'  and parent_id=45),20);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账'  and parent_id=45),20);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账'  and parent_id=45),20);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账'  and parent_id=45),20);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账'  and parent_id=45),20);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 20);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 风控部外控专员role_id=19
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),19);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),19);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),19);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),19);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),19);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,19);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商考勤时间台账' 
and resource_url='/attTime.do?method=findDealerList' and resource_index='93' ),19);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 风控部内控专员role_id=18
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),18);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),18);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),18);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),18);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),18);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,890,18);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,18);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,910,18);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,18);

---------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 视频部经理role_id=23
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商考勤时间台账' 
and resource_url='/attTime.do?method=findDealerList' and resource_index='93' ),23);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,890,23);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 23);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
--添加资源与角色关联SQL -- 视频部专员role_id=21
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),21);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),21);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),21);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),21);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),21);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商考勤时间台账' 
and resource_url='/attTime.do?method=findDealerList' and resource_index='93' ),21);

//-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 市场部经理role_id=13
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='项目进驻流转单台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费变更管理台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费发票管理台账' and parent_id=45),13);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费退费管理台账' and parent_id=45),13);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,13);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,13);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 市场部专员role_id=11
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='项目进驻流转单台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费变更管理台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费发票管理台账' and parent_id=45),11);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费退费管理台账' and parent_id=45),11);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,11);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,11);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 市场部部长role_id=27
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='项目进驻流转单台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费变更管理台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费发票管理台账' and parent_id=45),27);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='监管费退费管理台账' and parent_id=45),27);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,27);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,27);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 监管部经理role_id=9
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='辞职申请台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='交接记录管理台账' and parent_id=45),9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='轮岗计划表台账' and parent_id=45),9);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,9);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,9);
-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 薪酬专员role_id=6
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),6);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),6);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),6);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),6);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,130,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,132,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,133,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,135,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,6);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,123,6);

-----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 综合专员role_id=3
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='业务流转单台账' and parent_id=45),3);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构绑定台账' and parent_id=45),3);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商/金融机构拆绑台账' and parent_id=45),3);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商撤店台账' and parent_id=45),3);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='加班申请台账' and parent_id=45),3);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,896,3); 

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,911,3); 

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,130,3);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,132,3); 

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,133,3); 

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,135,3); 

---------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 考勤专员role_id=8
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理'  and resource_shortname='操作流'),8);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id  from t_rbac_resource  where resource_name = '考勤表'  and resource_shortname = '考勤表' 
and parent_id=(select id from t_rbac_resource where resource_name = '考勤管理')), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id  from t_rbac_resource  where resource_name = '考勤每日详情' and resource_shortname = '考勤每日详情'
and parent_id=(select id from t_rbac_resource where resource_name = '考勤管理')), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource  where resource_name = '请假申请' and resource_shortname = '请假申请' 
and parent_id=(select id from t_rbac_resource where resource_name = '考勤管理')), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '辞职申请' and resource_shortname = '辞职申请' 
and parent_id=(select id from t_rbac_resource where resource_name = '考勤管理')), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '加班申请' and resource_shortname = '加班申请'
and parent_id=(select id from t_rbac_resource where resource_name = '考勤管理')), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,913, 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '考勤表台帐' and parent_id=45), 8);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '考勤每日详情台账' and parent_id=45), 8);

--花名册
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,121,8);

--调动表
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,124,8);

--视频检查报告台帐
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,8);

--来电记录单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 8);

--提交轮岗计划
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,341, 8);

--管理费及社保费用
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,342, 8);

--提交监管员考勤
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,343, 8);

--未按轮岗计划执行
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,361, 8);

--监管员签退异常
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,382, 8);

--项目进驻流转单发出后三天未录入人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,401, 8);

--监管员面试完成一日未安排培训提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,402, 8);

--监管员进入储备库15天未安排上岗
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,403, 8);
--监管员辞职离岗时间前十天未提交人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,421, 8);

--监管员在一家店工作六个月提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504, 8);

--项目进驻流转单发出后五天未匹配人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,481, 8);

--监管员上岗未培训考核预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,501, 8);

--监管员面试完成三日未安排培训预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,502, 8);

--监管员辞职离岗时间前五天未提交人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,503, 8);

---------------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 调配专员role_id=5
 --制度与流程
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),5);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件'  ),5);

 --轮岗计划表
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,115,5);

 --交接记录管理
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,116,5);

 --辞职申请
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='辞职申请' and parent_id=44 ),5);

 --视频检查报告
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,913, 5);

 --调动表
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,124,5);

 --轮岗计划表台账
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '轮岗计划表台账' and parent_id=45 ), 5);

 --交接记录管理台账
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '交接记录管理台账' and parent_id=45 ), 5);

 --视频检查报告台帐
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,899,5);

 --来电记录单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 5);


 --管理费及社保费用
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,342, 5);

 --提交监管员考勤
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,343, 5);

 --监管员签退异常
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,382, 5);

 --项目进驻流转单发出后三天未录入人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,401, 5);

 --监管员面试完成一日未安排培训提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,402, 5);

 --监管员进入储备库15天未安排上岗
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,403, 5);

 --监管员辞职离岗时间前十天未提交人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,421, 5);
 --项目进驻流转单发出后五天未匹配人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,481, 5);

 --监管员上岗未培训考核预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,501, 5);

 --监管员面试完成三日未安排培训预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,502, 5);
 --监管员辞职离岗时间前五天未提交人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,503, 5);


----------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 招聘专员role_id=4
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),4);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),4);

 --账户管理
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,49,4);


 --视频检查报告
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,913, 4);


 --来电记录单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 4);

 --提交轮岗计划
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,341, 4);

 --管理费及社保费用
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,342, 4);

 --提交监管员考勤
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,343, 4);

 --未按轮岗计划执行
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,361, 4);

 --监管员签退异常
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,382, 4);

 --监管员在一家店工作六个月提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504, 4);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 培训专员role_id=7
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流'),7);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),7);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,913, 7);

 --来电记录单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name = '来电记录单' and resource_shortname = '来电记录单'
 and resource_url = '/complaint.do?method=findList' and resource_index = '107' and des = '来电记录单'), 7);

 --提交轮岗计划
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,341, 7);

 --管理费及社保费用
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,342, 7);

 --提交监管员考勤
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,343, 7);

 --未按轮岗计划执行
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,361, 7);

 --监管员签退异常
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,382, 7);

 --项目进驻流转单发出后三天未录入人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,401, 7);

 --监管员面试完成一日未安排培训提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,402, 7);

 --监管员进入储备库15天未安排上岗
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,403, 7);

 --监管员辞职离岗时间前十天未提交人员信息
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,421, 7);

 --监管员在一家店工作六个月提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504, 7);

 --项目进驻流转单发出后五天未匹配人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,481, 7);

 --监管员辞职离岗时间前五天未提交人员信息预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,503, 7);


----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 -- 银行审批role_id=31
 --车辆信息导入
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,103,31);
 --入库
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,843,31);
 --出库
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,844,31);
 --移动
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,845,31);
 --经销商名录表（业务）
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,890,31);
 --车辆监管台账
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,118,31);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------
 --市场部专员
 --项目进驻当天协议未收回提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504,11);

 --协议到期未续签预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,550,11);

 --业务进驻流转单进驻前1天未匹配人员预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,551,11);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 --市场部经理
 --项目进驻当天协议未收回提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504,13);

 --监管费到期前30天进行提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,538,13);

 --业务进驻流转单进驻前1天未匹配人员预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,551,13);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
 --市场部数据专员
 --进驻7天未交监管费提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,537,12);

 --项目进驻当天协议未收回提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,504,12);

--监管费到期前30天进行提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,538,12);

--项目进驻当天协议未收回提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,540,12);

--监管协议到期前30天提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,541,12);

--监管协议签署10天未收回提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,542,12);

--协议签署超15天未发进店流转单提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,543,12);

--进驻15天未交监管费预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,544,12);

--监管费到期日未收费预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,547,12);

--项目进驻15天协议未收回预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,548,12);

--监管协议到期前15天预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,549,12);

--协议到期未续签预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,550,12);

--业务进驻流转单进驻前1天未匹配人员预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,551,12);

--项目进驻流转单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,552,12);

--业务进驻流转单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,553,12);

--项目撤出流转单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,554,12);

--项目绑定流转单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,555,12);

--项目解绑流转单
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,556,12);

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
--业务部专员
--开票30天汇票未押满
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,443,14);

----------------------------------------------------------------------------------------------------------------------------------------------------------------
--业务部经理
--开票30天汇票未押满
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,443,16);
----------------------------------------------------------------------------------------------------------------------------------------------------------------
--业务部数据专员
--银行释放审批提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,509,15);

--银行移动审批提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,505,15);
--开票10个工作日未到车提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,506,15);

--开票30天汇票未押满
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,443,15);
--汇票到期前7天未清票提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,508,15);

--零库存零汇票提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,510,15);

--最后一笔业务提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,511,15);

--连续三天无业务发生提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,512,15);

--移动车辆超过25天未处理提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,513,15);

--移动车辆超过总库存20%提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,514,15);

--异常车辆超过总库存15%提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,515,15);

--银行释放审批一天未出库预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,581,15);

--银行移动审批一天未出库预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,583,15);
--开票15个工作日未到车预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,584,15);

--开票45天汇票未押满预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,585,15);

--汇票到期当天未清票预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,586,15);

--零库存零汇票延续7天预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,587,15);

--连续五天无业务发生预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,588,15);

--移动车辆超过30天未处理预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,589,15);

--异常车辆超过总库存20%预警
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,590,15);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
--风控部 -- 数据专员
--每月20日提交视频检查计划提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,569,17);

--巡店报告提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,572,17);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
--风控部 -- 内控专员
--每月20日提交视频检查计划提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,569,18);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
--风控部 -- 外控专员
--每月20日提交巡店检查计划提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,568,19);

--每月20日提交视频检查计划提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,569,19);

--未按风控巡检计划执行提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,570,19);

--巡店报告上传3日未上传新报告提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,571,19);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------
--风控部 -- 经理
--每月20日提交视频检查计划提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,569,20);

---------------------------------------------------------------------------------------------------------------------------------------------------------------------
--监管员
--释放申请书提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,563,10);

--移动申请书提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,564,10);

--设备维修申请单提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,565,10);

--邮寄费用申请提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,566,10);

--加班申请提醒
insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,567,10);

commit;

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--综合专员 role_id=3

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='操作流程管理' and
resource_url='/flow.do?method=flowList' and resource_shortname='操作流'),3);--操作流程管理

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='制度文件管理'  
and resource_shortname='制度文件'),3);--制度文件管理

 --删除公告管理
delete from t_rbac_role_resource where resource_id=55 and role_id=3;

--分配公告菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='公告' ),3);




--分配信息提醒权限

--监管员信息管理
delete from t_rbac_role_resource resource_id='117' and role_id=3;--删除邮寄费用申请

--一级菜单（资产管理与监管员费用管理）
insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)values(SEQ_T_RBAC_RESOURCE.NEXTVAL,
1,1,0,0,1,'资产管理与监管员费用管理','资产管理与监管员费用管理',null,'资产管理与监管员费用管理');

--创建一级菜单(资产管理与监管员费用管理)下的子菜单
insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)values(SEQ_T_RBAC_RESOURCE.NEXTVAL,
1,2,1,(select id from t_rbac_resource where resource_name='资产管理与监管员费用管理' and resource_level=1 and node_type=0 and resource_url is null),3,
'固定资产管理','固定资产管理','/fixedAssets.do?method=fixedAssetsList','固定资产管理');

insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)values(SEQ_T_RBAC_RESOURCE.NEXTVAL,
1,2,1,(select id from t_rbac_resource where resource_name='资产管理与监管员费用管理' and resource_level=1 and node_type=0 and resource_url is null),106,
'设备维修申请','设备维修申请','/repaircost.do?method=findList','设备维修申请');

insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)values(SEQ_T_RBAC_RESOURCE.NEXTVAL,
1,2,1,(select id from t_rbac_resource where resource_name='资产管理与监管员费用管理' and resource_level=1 and node_type=0 and resource_url is null),117,
'设备邮寄申请','设备邮寄申请','/message.do?method=messagePageList&messagequery.type=17','设备邮寄申请');

insert into t_rbac_resource(id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)values(SEQ_T_RBAC_RESOURCE.NEXTVAL,
1,2,1,(select id from t_rbac_resource where resource_name='资产管理与监管员费用管理' and resource_level=1 and node_type=0 and resource_url is null),114,
'设备邮寄申请','设备邮寄申请','/mailcost.do?method=findList','邮寄邮寄申请');

--分配二级菜单

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='固定资产管理' and node_type=1 and resource_level=2 and resource_index='3' and resource_url='/fixedAssets.do?method=fixedAssetsList'),3);


insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='设备维修申请' and node_type=1 and resource_level=2 and resource_index='106' and resource_url='/repaircost.do?method=findList'
),3);


insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='设备邮寄申请' and node_type=1 and resource_level=2 and resource_index='117')
,3);


insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='邮寄费用申请'and node_type=1 and resource_level=2 and resource_index='114' and resource_url ='/mailcost.do?method=findList'),3);

--分配二级菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='调动表' and node_type=1 and resource_level=2 and resource_index='120' and  resource_url='/ledger/transfer.do?method=transferLedger'),3);

--取消 资产管理菜单下的二级菜单(设备维修申请,固定资产管理)
delete from t_rbac_role_resource where resource_id in (112,885) and role_id=3;
 
--删除台账管理下的二级菜单设备维修费用申请
delete from t_rbac_role_resource where resource_id=123 and role_id=3;

--(台账管理)分配二级菜单调动表,视频检查报告台账,调动表
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='调动表' and node_type=1 and resource_level=2 and resource_index='120' and  resource_url='/ledger/transfer.do?method=transferLedger')
,3);



insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='邮寄费用申请台账' and node_type=1 and resource_level=2 and resource_index='106' and  resource_url='/ledger/mailcost.do?method=mailcostLedger')
,3);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='视频检查报告台帐' and node_type=1 and resource_level=2 and PARENT_ID=45 and  resource_url='/ledger/videoReport.do?method=reportLedger')
,3);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='来电记录单' and  resource_url= '/complaint.do?method=findList')
,3);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='交接记录管理台账' and  resource_url= '/handoverLog.do?method=HandoverRecordManagementLedgerList')
,3);


commit;
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--薪酬专员 role_id=6
--分配菜单制度与流程
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='操作流程管理' and
resource_url='/flow.do?method=flowList' and resource_shortname='操作流'),6);--操作流程管理

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='制度文件管理'  
and resource_shortname='制度文件'),6);--制度文件管理

 --删除公告管理
delete from t_rbac_role_resource where resource_id=55 and role_id=6;

--分配二级公告菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='公告' and node_type=1 and resource_level=2  and resource_index='102'),6);


--删除监管员信息管理下的二级菜单（邮寄费用申请，资料邮寄费用）
delete from t_rbac_role_resource where resource_id in (117,886) and role_id=6;




--分配资产管理与监管员费用管理下的二级菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='设备维修申请' and node_type=1 and resource_level=2 and resource_index='106' and resource_url='/repaircost.do?method=findList'
),6);


insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='设备邮寄申请' and node_type=1 and resource_level=2 and resource_index='117')
,6);


insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='邮寄费用申请'and node_type=1 and resource_level=2 and resource_index='114' and resource_url ='/mailcost.do?method=findList'),6);


--台帐管理分配二级菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='调动表' and node_type=1 and resource_level=2 and resource_index='120' and  resource_url='/ledger/transfer.do?method=transferLedger')
,6);

--删除台账管理下的二级菜单设备维修费用申请
delete into t_rbac_role_resource where resource_id=123 and role_id=6;

--添加二级菜单调动表和视频检查报告台账,邮寄费用申请台账
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='调动表' and node_type=1 and resource_level=2 and resource_index='120' and  resource_url='/ledger/transfer.do?method=transferLedger')
,6);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='视频检查报告台帐' and node_type=1 and resource_level=2 and PARENT_ID='45' and  resource_url='/ledger/videoReport.do?method=reportLedger')
,6);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='来电记录单' and  resource_url= '/complaint.do?method=findList')
,6);



insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='交接记录管理台账' and  resource_url= '/handoverLog.do?method=HandoverRecordManagementLedgerList')
,6);

commit;

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--监管员管理部经理 role_id=9

--系统管理与配置删除专员分配二级菜单
delete from t_rbac_role_resource where resource_id=851 and role_id=9;



--分配菜单制度与流程
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='操作流程管理' and
resource_url='/flow.do?method=flowList' and resource_shortname='操作流'),9);--操作流程管理

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='制度文件管理'  
and resource_shortname='制度文件'),9);--制度文件管理


--分配二级公告菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,(select id from t_rbac_resource where resource_name='公告' and node_type=1 and resource_level=2  and resource_index='102'),6);


--删除监管员信息管理下的二级菜单（邮寄费用申请，资料邮寄费用）
delete from t_rbac_role_resource where resource_id in (117,886) and role_id=9;




--分配资产管理与监管员费用管理下的二级菜单

--台帐管理分配二级菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='调动表' and  resource_url='/ledger/transfer.do?method=transferLedger')
,9);

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='经销商考勤时间' and parent_id=44 
and resource_url='/attSign.do?method=attendanceTime' 
and resource_index='5'  and resource_shortname='经销商考勤时间'),9);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id  from t_rbac_resource  where resource_name = '考勤每日详情' 
and resource_shortname = '考勤每日详情' 
and resource_url = '/attendance.do?method=SignRecordCheckSpList' and resource_index = '20'  and node_type = 1),9);

insert into t_rbac_role_resource(id, resource_id, role_id) values(seq_t_rbac_role_resource.nextval,
(select id  from t_rbac_resource  where resource_name = '加班申请' 
and resource_shortname = '加班申请' 
and resource_url = '/extraworkApply.do?method=findPageList' and parent_id=44),9);

--删除台账管理下的二级菜单设备维修费用申请,设备管理台账,投诉记录信息汇总表
delete into t_rbac_role_resource where resource_id in(123,122,120)  and role_id=9;

--（视频管理）增加子菜单分配二级菜单
insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='视频检查报告' and des='视频部全员及各部门经理以上角色可以查看'),9);


--添加二级菜单调动表和视频检查报告台账,邮寄费用申请台账

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='视频检查报告台帐' and node_type=1 and resource_level=2 and PARENT_ID=45 and  resource_url='/ledger/videoReport.do?method=reportLedger')
,9);

insert into t_rbac_role_resource(id,resource_id,role_id)values(SEQ_T_RBAC_ROLE_RESOURCE.Nextval,
(select id from t_rbac_resource where resource_name='来电记录单' and  resource_url= '/complaint.do?method=findList')
,9);

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--市场部专员
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,(select id from t_rbac_resource where resource_name='公告' and node_type=1 and resource_level=2  and resource_index='102'),11);

delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 11 ;

delete from t_rbac_role_resource t where t.resource_id=101 and t.role_id = 11 ;

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流'),11);
 
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),11);

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--市场部经理

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,(select id from t_rbac_resource where resource_name='公告' and node_type=1 and resource_level=2  and resource_index='102'),13);
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=913 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=880 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=873 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=875 and t.role_id = 13 ;
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 13 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),13);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),13);
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

--市场管理部部长

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,27);
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 27 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 27 ;
delete from t_rbac_role_resource t where t.resource_id=913 and t.role_id = 27 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),27);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),27);

--视频部专员

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,21);
delete from t_rbac_role_resource t where t.resource_id=55 and t.role_id = 21 ;
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 21 ;
delete from t_rbac_role_resource t where t.resource_id=146 and t.role_id = 21 ;
delete from t_rbac_role_resource t where t.resource_id=141 and t.role_id = 21 ;
delete from t_rbac_role_resource t where t.resource_id=142 and t.role_id = 21 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),21);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件'),21);

--视频部经理

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,23);
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 23 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 23 ;
delete from t_rbac_role_resource t where t.resource_id=124 and t.role_id = 23 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),23);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),23);
--风控部内控专员

delete from t_rbac_role_resource t where t.resource_id=55 and t.role_id = 18 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,18);
delete from t_rbac_role_resource t where t.resource_id=907 and t.role_id = 18 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 18 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),18);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),18);

--风控部外控专员

delete from t_rbac_role_resource t where t.resource_id=55 and t.role_id = 19 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,19);
delete from t_rbac_role_resource t where t.resource_id=907 and t.role_id = 19 ;
delete from t_rbac_role_resource t where t.resource_id=912 and t.role_id = 19 
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,142,19);
delete from t_rbac_role_resource t where t.resource_id=910 and t.role_id = 19 ;
delete from t_rbac_role_resource t where t.resource_id=911 and t.role_id = 19 ;
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 19 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流'),19);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),19);

--风控部经理

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,20);
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 20 ;
delete from t_rbac_role_resource t where t.resource_id=909 and t.role_id = 20 ;
delete from t_rbac_role_resource t where t.resource_id=913 and t.role_id = 20 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,141,20);
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,142,20);
delete from t_rbac_role_resource t where t.resource_id=910 and t.role_id = 20 ;
delete from t_rbac_role_resource t where t.resource_id=911 and t.role_id = 20 ;
delete from t_rbac_role_resource t where t.resource_id=826 and t.role_id = 20 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,118,20);
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,890,20);
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 20 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),20);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件'),20);

--风险管理部部长

delete from t_rbac_role_resource t where t.resource_id=903 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=909 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=907 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=101 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=820 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=107 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=898 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=911 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=129 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=130 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=131 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=132 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=135 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 26 ;
delete from t_rbac_role_resource t where t.resource_id=116 and t.role_id = 26 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流'),26);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),26);

--监管员

delete from t_rbac_role_resource t where t.resource_id=843 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=844 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=845 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=107 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=820 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=886 and t.role_id = 10 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,117,10);
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,112,10);
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='辞职申请' and parent_id=44 and resource_url='/resignApply.do?method=findPageList' 
and resource_index='32'  and resource_shortname='辞职申请'),10);
delete from t_rbac_role_resource t where t.resource_id=123 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=111 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=112 and t.role_id = 10 ;
delete from t_rbac_role_resource t where t.resource_id=146 and t.role_id = 10 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流'),10);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),10);

--业务部专员

delete from t_rbac_role_resource t where t.resource_id=55 and t.role_id = 14 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,14);
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,896,14);
delete from t_rbac_role_resource t where t.resource_id=101 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=935 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=948 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=953 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=98 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=130 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=132 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=133 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=135 and t.role_id = 14 ;
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 14 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),14);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件'),14);

--业务部经理

insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,145,16);
delete from t_rbac_role_resource t where t.resource_id=101 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=908 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=826 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=120 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=851 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=891 and t.role_id = 16 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='请假申请' and parent_id=44 and resource_url='/leaveApply.do?method=findPageList' 
and resource_index='34'  and resource_shortname='请假申请'),16);
delete from t_rbac_role_resource t where t.resource_id=130 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=134 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=135 and t.role_id = 16 ;
delete from t_rbac_role_resource t where t.resource_id=144 and t.role_id = 16 ;
insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='操作流程管理' and resource_shortname='操作流' ),16);
 insert into t_rbac_role_resource(id,resource_id,role_id) values(seq_t_rbac_role_resource.nextval,
(select id from t_rbac_resource where resource_name='制度文件管理' and resource_shortname='制度文件' ),16);
