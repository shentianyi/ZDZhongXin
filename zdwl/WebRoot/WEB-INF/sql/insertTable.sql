----------sxs------------------------2017-7-2 17:44:33----------------------------------

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706300002,'zddb','t_payment_info','seq_t_paymentinfo','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706300001,'zddb','t_payment','seq_t_payment','1',SYSDATE,SYSDATE,'sxs','手动录入');

﻿------LXH-------------------------2017-6-28 16:12-----------------------------------------------
需求233
insert into t_rbac_role (id,state,role_name,client_type,des)values(SEQ_T_RBAC_ROLE.NEXTVAL,1,'经销商集团',14,'经销商集团');
---------sxs----------------------------2017-6-29 16:41:40---------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290004,'zddb','T_ATTENDANCE','SEQ_T_ATTENDANCE','1',SYSDATE,SYSDATE,'sxs','手动录入');

-------cym-------------------------2017-6-29 16:40:15-----------------------------------------------
insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290003,'zddb','t_extrawork_apply','SEQ_T_EXTRAWORK_APPLY','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290002,'zddb','t_leave_replace','seq_t_leave_replace','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706290001,'zddb','t_supervisory_leaveApply','seq_t_supervisory_leaveapply','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706270001,'zddb','T_SIGN_RECORD','seq_t_sign_record','1',SYSDATE,SYSDATE,'cym','手动录入');
commit;

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
  ('201706180001',
   'zddb',
   't_modechange_log',
   'SEQ_MODECHANGE_LOG',
   1,
   sysdate,
   sysdate,
   'sxs',
   '手动添加');
   
   -------add Date-----------------------------
   
   insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706100001,'zddb','t_rbac_dealergroup','SEQ_T_RBAC_DEALERGROUP','1',SYSDATE,SYSDATE,'sxs','手动录入');
   
   insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110004,'zddb','t_extrawork_apply','SEQ_T_EXTRAWORK_APPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');


insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110001,'zddb','t_resign_apply','SEQ_T_RESIGN_APPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110002,'zddb','t_current_dealer','t_current_dealer','1',SYSDATE,SYSDATE,'sxs','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110003,'zddb','t_supervisory_leaveapply','SEQ_T_SUPERVISORY_LEAVEAPPLY','1',SYSDATE,SYSDATE,'sxs','手动录入');

   
   insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110005,'zddb','market_dealer_bank','seq_market_dealer_bank','1',SYSDATE,SYSDATE,'cym','手动录入');

   insert into t_rbac_role_resource(id,role_id,resource_id) (select seq_t_rbac_role_resource.nextval, id,145 from t_rbac_role where id not in (select role_id from t_rbac_role_resource where resource_id = 145));
   
   insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110007,'zddb','T_CHECKSTOCK_CAR','seq_t_checkstock_car','1',SYSDATE,SYSDATE,'cym','手动录入');

insert into t_seqencedict(id,dbname,tablename,seqname,seqinitvalue,addseqdate,createtime,addsequser,memo)
values(201706110006,'zddb','T_CHECKSTOCK_MANAGE','seq_t_checkstock_manage','1',SYSDATE,SYSDATE,'cym','手动录入');

   insert into t_rbac_resource (id,state,resource_level,node_type,parent_id,resource_index,resource_name,resource_shortname,resource_url,des)
values(SEQ_T_RBAC_RESOURCE.Nextval,1,2,1,37,10,'业务进驻流转单进驻前3天未匹配人员提醒','业务进驻流转单进驻前3天未匹配人员提醒','/message.do?method=messagePageList&messagequery.type=108','业务进驻流转单进驻前3天未匹配人员提醒');

insert into t_rbac_role_resource(id,resource_id,role_id)  select SEQ_T_RBAC_ROLE_RESOURCE.Nextval as id,id as resource_id,80 as role_id from t_rbac_resource where resource_name like '%业务进驻流转单进驻前3天未匹配人员提醒%';



INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES (81,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=81',0,1,1);
 --银行移动审批提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(82,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=82',0,1,1);
 --开票10个工作日未到车提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(83,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=83',0,1,1);
 --开票30天汇票未押满 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(84,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=84',0,1,1);
 --汇票到期前7天未清票提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(85,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=85',0,1,1);
 --银行释放审批提醒 银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(86,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=86',0,1,1);
 --零库存零汇票提醒  银行审批人
 
 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(87,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=87',0,1,1);
 --最后一笔业务提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(88,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=88',0,1,1);
 --连续三天无业务发生提醒  银行审批人
 
 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(89,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=89',0,1,1);
 --移动车辆超过25天未处理提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(90,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=90',0,1,1);
 --移动车辆超过总库存20%提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(91,31,'业务部','/businessRemindAndWaring.do?method=findMessageList&msgType=91',0,1,1);
 --异常车辆超过总库存15%提醒  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(92,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=92',0,2,1);
 --开票15个工作日未到车预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(93,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=93',0,2,1);
 --开票45天汇票未押满预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(94,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=94',0,2,1);
 --汇票到期当天未清票预警  银行审批人

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(95,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=95',0,2,1);
 --零库存零汇票延续7天预警  银行审批人   

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(96,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=96',0,2,1);
 --连续五天无业务发生预警  银行审批人  

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(97,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=97',0,2,1);
 --移动车辆超过30天未处理预警  银行审批人  

 INSERT INTO T_MSG_RECEIVE (MSGTYPE,ROLEID,DEPNAME,TYPEURL,DEPID,TYPE,RECEIVETYPE) 
 VALUES(98,31,'业务部','/businessRemindAndWaring.do?method=findWaringList&msgType=98',0,2,1);
 --异常车辆超过总库存20%预警  银行审批人 
 
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
