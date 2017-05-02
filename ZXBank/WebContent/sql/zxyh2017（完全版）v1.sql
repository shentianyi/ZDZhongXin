----创建用户
create user c##zxyh identified by 123456;
grant connect,resource,dba to c##zxyh;
--浙商银行经销商参数表

create table t_distribset(
id number(19,2) primary key,
moveperc varchar2(100),--监管物移动百分比
distribid number(19,0),--经销商id
bankdocktype number,--对接银行类型 0不对接1浙商银行2中信银行
zscustno varchar2(100),--浙商银行客户号
contract varchar2(100)--合同编号
);
create sequence t_distribsetId
increment by 1
start with 1;
--中信银行经销商参数表 
create table zx_distribset(
zx_did number(19,2) primary key,--经销商参数表id 主键
distribid number(19,0),--经销商id
zx_moveperc varchar2(100),--监管物移动百分比
zx_bankdocktype number,--对接银行类型 0不对接1浙商银行2中信银行
contractno varchar2(100),--合同编号
organizationcode varchar2(100) not null,--组织机构代码
zx_createdate date,--表中数据创建时间
zx_updatedate date,--表中数据修改时间
zx_createuser varchar2(50)--表中数据创建人
);
create sequence zx_distribsetId
increment by 1
start with 1;
insert into zx_distribset(zx_did,distribid,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,6,'50',2,'hz454455454','454ddd755852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'aa' );
insert into zx_distribset(zx_did,distribid,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,5,'30',2,'hz454455454','454755dd852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2009-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'bb' );
insert into zx_distribset(zx_did,distribid,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,6,'50',2,'hz454455454','4547558dd52',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2010-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'cc' );
insert into zx_distribset(zx_did,distribid,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,6,'40',2,'hz454455454','454755d852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'dd' );
insert into zx_distribset(zx_did,distribid,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,3,'20',2,'hz454455454','454755852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2016-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'ee' );
--客户查询表
create table zx_customer(
cust_id number(19,2) primary key,--客户查询表主键
cust_no varchar2(50),--客户号
cust_organizationcode varchar2(100) not null,--组织机构代码
cust_name varchar2(100),--客户名称
cust_createdate date,--数据同步时间
cust_updatedate date--数据同步更新时间
);
create sequence zx_customerId
increment by 1
start with 1;
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'1222223','454ddd755852','测试一',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'4652223','4547558dd52','测试二',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'6632223','4547558dd52','测试三',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'25621223','454ddd755852','测试四',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'12142623','454ddd755852','测试五',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'12142623','454ddd755852','测试五',sysdate,sysdate);
commit;
--仓库查询表
create table zx_warehouse(
zx_whid number(19,2) primary key,--仓库查询表主键
zx_custno varchar2(50),--客户号
zx_whname varchar2(200),--仓库名字
zx_whcode varchar2(20),--仓库代码
zx_whlevel varchar2(20),--仓库级别
zx_whoperorg varchar2(100),--经办行
zx_loncpname varchar2(200),--借款企业名称 --新添
zx_whaddress varchar2(100),--仓库地址
zx_phone varchar2(12),--电话
zx_createdate date,--数据同步时间
zx_updatedate date--数据更新时间
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'1222223','京东仓库','1234154','一级','中都','上海','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'淘宝店');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'4652223','天猫仓库','1234154','一级','中信','上海','15138557776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'淘宝店');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'1222223','aa仓库','1234154','一级','平安','安徽','15138337776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'淘宝店');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'25621223','hh仓库','1234154','一级','中信','北京','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'淘宝店');


--通知推送表
create table zx_notice(
nt_id number(19,2) primary key,--通知推送表主键
nt_type number(2),--通知书类型1、收货2、移库3解除质押
nt_no varchar2(50),--通知书编号
nt_stdate date,--通知书接收时间
nt_enddate date,--通知书跟新时间
nt_failflag number(2) --0接收失败，1已接收
);
create sequence zx_noticeId
increment by 1
start with 1;
insert into zx_notice(nt_id,nt_type,nt_no,nt_stdate,nt_enddate,nt_failflag) 
values(zx_noticeId.nextval,1,'sh12142254',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),1);
insert into zx_notice(nt_id,nt_type,nt_no,nt_stdate,nt_enddate,nt_failflag) 
values(zx_noticeId.nextval,1,'sh1345254',to_date ( '2007-4-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),1);
insert into zx_notice(nt_id,nt_type,nt_no,nt_stdate,nt_enddate,nt_failflag) 
values(zx_noticeId.nextval,2,'ss555142254',to_date ( '2007-11-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),1);
insert into zx_notice(nt_id,nt_type,nt_no,nt_stdate,nt_enddate,nt_failflag) 
values(zx_noticeId.nextval,3,'sd12787854',to_date ( '2007-5-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),1);
--监管协议查询表

create table zx_agreement(
ag_id number(19,2) primary key,--监管协议id
ag_custno varchar2(50),--客户号
ag_loncpid varchar2(20),--借款企业ID
ag_loncpname varchar2(200),--借款企业名称
ag_protocolno varchar2(50),--系统监管协议编号
ag_protocolcode varchar2(50),--纸质监管协议编号
ag_state number(2),--协议状态（0,失效,1,生效）
ag_stdate char(8),--协议起始日
ag_enddate char(8),--协议到期日
ag_isonline number(2),--是否开通线上业务
ag_ismove number(2),--是否允许移库
ag_operorg varchar2(100),--经办行
ag_totnum number(19,2),--总记录数
ag_createdate date,--同步数据时间
ag_updatedate date--同步更新数据时间
);
create sequence zx_agreementId
increment by 1
start with 1;
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145861813648','646461844','测试企业一','56465181','654818461',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145845313648','64278844','测试企业二','56462722181','65778998461',1,'20170312','20170320',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145878527648','646271844','测试企业三','564785181','654798461',1,'20170312','20170320',1,1,'平安',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'14586278213648','6278244','测试企业四','5647895181','6547898461',1,'20170312','20170331',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'122782648','64782844','测试企业五','564678181','65474461',1,'20170312','20170331',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145861813648','62744','测试企业六','5627275181','65487898461',1,'20170312','20170331',1,1,'中都',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--融资信息查询表
create table zx_financing(
fiId number(19,2) primary key,--融资查询表主键
fgLonentNo varchar2(30) ,--借款企业Id
loncpname varchar2(200),--借款企业名称
fgStDate char(8),--融资起始日期 ，格式为YYYYMMDD
fgEndDate char(8),--融资结束日期，格式为YYYYMMDD
fgLoanCode varchar2(40),-- 融资编号
fgScftxNo varchar2(20),--放款批次号
fgLoanAmt number(19,2),--融资金额
fgBailRat decimal(9,6),--保证金比例
fgSlfcap decimal(9,6),--自有资金比例
fgFstblRat decimal(9,6),--首付保证金可提货比例
fgProcrt varchar2(40),--授信产品，银行承兑汇票
fgBizMod varchar2(20),--业务模式，先票后货
fgOperOrg varchar2(100),--经办行
fgCreateDate date,--融资数据同步时间
fgUpdateDate date--融资同步更新时间
);
create sequence zx_financingId
increment by 1
start with 1;

commit;

收货通知书信息

create table zx_notify(
ny_id number(19,2) primary key,--收货通知表主键id
ny_no Varchar2(50),--通知书编号
ny_corentnm Varchar2(200),--核心企业名称
ny_spventnm Varchar2(200),--(在库)监管企业名称
ny_onwspvenm Varchar2(200),--（在途）监管企业名称
ny_trsptentnm Varchar2(200),--运输企业名称
ny_lonentno Varchar2(30),--借款企业id
ny_lonentname Varchar2(200),--借款企业名称
ny_csndate date,--发货日期
ny_eta  date,--预计到港(库)日期
ny_epa  varchar2(200),--预计到库
ny_offlnsatno Varchar2(100),--纸质监管协议编号
ny_ntcdate  date,--通知书日期
ny_ttlcmdval  decimal(19,2),--货物价值合计
ny_excplace Varchar2(200),--交货地点
ny_remark Varchar2(500),--备注
ny_totnum Number(19,2),--总记录数
ny_createdate date,--创建时间
ny_updatedate date--更新时间
);
create sequence zx_notifyId
increment by 1
start with 1
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'165463514','奥迪生产厂','中都','中都','申通','168461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','1686181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2000000,'上海浦东','暂无',10);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'1423412','奥迪生产厂2','中都2','中都2','申通1','161238461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','168126181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2005600,'上海浦东','暂无',23);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'125123','奥迪生产厂3','中都2','中都42','申通1','16823461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','168611281',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),204000,'上海浦东','暂无',10);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'1615153514','奥迪生产厂4','中都3','中都4','申通22','16823461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','168346181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),223200,'上海浦东','暂无',44);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'16123512514','奥迪生产厂5','中都3','中都4','申通3','16823461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','16865181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),200300,'上海浦东','暂无',15);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'162135514','奥迪生产厂6','中都4','中都2','申3通','16842361','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','168566181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),20230,'上海浦东','暂无',15);
commit;


收货通知书详情
create table zx_notifydetail(
nd_id number(19,2) primary key,--收货质物明细表主键id
nd_no varchar2(50) ,--通知书编号
nd_idtplanno varchar2(20),--实际纸质订单编号
nd_idtplannm varchar2(100),--实际订单名称
nd_cmdcode varchar2(60),--商品代码
nd_cmdname varchar2(200),--商品名称
nd_csnnum number(19,2),--发货数量
nd_unit varchar2(2),--计量单位
nd_csnprc number(19,2),--发货单价
nd_reqcsnval number(19,2),--发货价值
nd_scflonno varchar2(20),--SCF放款批次号
nd_mtgcntno varchar2(20),--质押合同编号
nd_remark varchar2(500),--备注
nd_vin varchar2(40),--车架号
nd_hgzno varchar2(50),--合格证编号
nd_carprice number(19,2),--车价
nd_loancode varchar2(30)--融资编号
);
create sequence zx_notifydetailId
increment by 1
start with 1

insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'165463514','1646164','收货通知单1325','61561','奥迪',1,'辆',1000000,3151351,'1684161','16131431','暂无','C615986168','316513',1065400,'R1651315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'1423412','12323','收货通知单112125','123123','奥迪',1,'辆',1002000,3153351,'168414321','161342331','暂无','C61426168','3112313',1065400,'R161235');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'125123','12312','收货通知单11235','612361','奥迪',1,'辆',100300,312351,'1684161','12341431','暂无','C6153242168','31123513',1065400,'R165415');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'1615153514','123124124','收货通知单1412425','6121561','奥迪',1,'辆',10100,3151351,'16842341','123131431','暂无','C61523486168','3112313',1065400,'R16512315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'16123512514','4534534','收货通知单13435','612361','奥迪',1,'辆',100500,33351,'163161','16321431','暂无','C614546168','31123513',1065400,'R16123315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'162135514','567567','收货通知单154525','615123161','奥迪',1,'辆',100600,3121351,'16841','161341','暂无','C6155466168','3412513',1065400,'R165235');
commit;

--解除质押通知书
create table zx_removepledge(
rp_id number(19,2) primary key,
rp_no varchar2(20) not null,--通知书编号
rp_operorg varchar2(100) not null,--经办行
rp_pldegeeName varchar2(80) not null,--出质人名称
rp_loncpId varchar2(20) not null,--借款企业id
rp_loncpName varchar2(200) not null,--借款企业名称
rp_superviseName varchar2(200) not null,--监管企业名称
rp_coreName varchar2(200) not null,--核心企业名称
rp_relievepdDate char(8) not null,--解除质押日期
rp_content varchar2(500),--出库原因
rp_noticeDate char(8),--通知书日期
rp_createdate date,--同步数据时间
rp_updatedate date--同步更新数据时间
);
create sequence zx_removepledgeId
increment by 1
start with 1;

--插入测试数据
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'111222333','中信','测试员1','444555666','宝马','中都','宝马厂','20170222','出库','20170223',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'444555666','中信','测试员2','777888999','奔驰','中都','奔驰厂','20170311','出库','20170311',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'777888999','中信','测试员3','111222333','法拉利','中都','法拉利厂','20170111','出库','20170111',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'112233','中信','测试员4','445566','宝马','兰博基尼','兰博基尼厂','20160322','出库','20160322',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'445566','中信','测试员5','778899','宝马','劳斯莱斯','劳斯莱斯厂','20150222','出库','20150222',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--解除质押通知书详情
create table zx_removepledgedetail(
rd_id number(19,2) primary key,
rd_no varchar2(20) not null,--通知书编号
rd_cmdcode varchar2(60),--商品代码
rd_cmdname varchar2(200),--商品名称
rd_unit varchar2(2),--计量单位
rd_stknum number(19,2),--库存数量
rd_rlsmgnum number(19,2),--解除质押数量
rd_whcode varchar2(20),--所在仓库编号
rd_scflonno varchar2(20),--Scf放款批次号
rd_chattelpdno varchar2(20),--动产质押担保合同编号
rd_number Decimal(8),--移库数量
rd_chassisno varchar2(200),--车架号
rd_certificationNo varchar2(200),--合格证编号
rd_carPrice Decimal(19,2),--车价
rd_userName varchar2(20),--赎货经办人姓名
rd_usercardid varchar2(20)--赎货经办人身份证号码
);
create sequence zx_removepledgedetailId
increment by 1
start with 1;

--插入测试数据
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','宝马Z','辆','10','2','2222','3333','4444','0','12345678','87654321','100000','测试员1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','奔驰Z','辆','20','5','3333','4444','1234','10','21313123','12421414','1000000','测试员2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','法拉利Z','辆','30','10','4444','5555','2345','20','3435153251','5436788','2000000','测试员3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','兰博基尼x','辆','50','11','5555','6666','3456','30','674574235','95464363','3000000','测试员4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','劳斯莱斯z','辆','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','测试员5','325252');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','宝马Y','辆','10','2','2222','3333','4444','0','12345678','87654321','100000','测试员1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','奔驰Y','辆','20','5','3333','4444','1234','10','21313123','12421414','1000000','测试员2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','法拉利Y','辆','30','10','4444','5555','2345','20','3435153251','5436788','2000000','测试员3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','兰博基尼Y','辆','50','11','5555','6666','3456','30','674574235','95464363','3000000','测试员4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','劳斯莱斯Y','辆','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','测试员5','325252');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','宝马Z','辆','10','2','2222','3333','4444','0','12345678','87654321','100000','测试员1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','奔驰Z','辆','20','5','3333','4444','1234','10','21313123','12421414','1000000','测试员2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','法拉利Z','辆','30','10','4444','5555','2345','20','3435153251','5436788','2000000','测试员3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','兰博基尼Z','辆','50','11','5555','6666','3456','30','674574235','95464363','3000000','测试员4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','劳斯莱斯Z','辆','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','测试员5','325252');

--移库通知书
create table zx_movenotice(
mn_id number(19,2) primary key,  --移库通知书id主键
mn_no varchar2(20) not null,--引用通知推送表通知书编号
mn_operorg varchar2(100),   --经办行
mn_superviseName varchar2(200),  --监管企业名称
mn_movedate char(8),  --移库申请日期
mn_loncpName varchar2(200),  --借款企业名称
mn_noticedate char(8),  --通知日期
mn_totnum number(19,2),  --总记录数
mn_createdate date,  --同步时间
mn_updatedate date  --更新数据时间
);
create sequence zx_movenoticeId
increment by 1
start with 1;

--插入测试数据
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'111222333','中信','中都','20161212','宝马','20161212','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'222333444','中信','中都','20170101','奔驰','20170101','20',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'333444555','中信','中都','20170202','法拉利','20170202','30',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'44455566','中信','中都','20170303','兰博基尼','20170303','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'555666777','中信','中都','20170404','劳斯莱斯','20170404','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--移库通知书详情表
create table zx_movedetail(
md_id number(19,2) primary key, --移库通知书详情表id
md_no varchar2(20) not null,  --引用移库通知书表
md_removeoutno varchar2(20) not null, --移除仓库编号
md_removeinno varchar2(20) not null, --移入仓库编号
md_wareno varchar2(50) not null, --商品代码
md_movenumber Decimal(8) not null, --移库数量
md_chassisno varchar2(200) not null, --车架号
md_certificationNo varchar2(200) not null, --合格证编号
md_carprice Decimal(19,2) not null --车价
);
create sequence zx_movedetailId
increment by 1
start with 1;

--插入测试数据
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'111222333','111222','222333','222222','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'111222333','111222','222333','333333','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'111222333','111222','222333','444444','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'222333444','222333','333444','555555','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'222333444','222333','333444','666666','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'222333444','222333','333444','777777','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'333444555','333444','444555','888888','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'333444555','333444','444555','3123123','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'333444555','333444','444555','123213','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'44455566','444555','555666','234645456','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'44455566','444555','555666','436685685','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'44455566','444555','555666','12312312','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'555666777','555666','666777','5645767','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'555666777','555666','666777','3423525','10','234234','234235465','1000000.00');
insert into zx_movedetail(md_id,md_no,md_removeoutno,md_removeinno,md_wareno,md_movenumber,md_chassisno,md_certificationNo,md_carprice) 
values(zx_movedetailId.nextval,'555666777','555666','666777','784363445','10','234234','234235465','1000000.00');

--质物入库表
create table zx_gager(
ga_id number(19,2) primary key,--质物入库id主键
ga_lonentno varchar2(30), --借款企业id
ga_oprtName varchar2(40), --操作人名称
ga_orderNo varchar2(20), --交易流水号 唯一 
ga_pcgrtntNo varchar2(100), --纸质担保合同编号
ga_cmgrtcntNo varchar2(30), --动产质押担保合同编号 
ga_confirmNo varchar2(30), --质物监管确认书编号
ga_lonentname varchar2(200),  --借款企业名称
ga_remark varchar2(500),  --备注
ga_bizMod varchar2(20), --业务模式
ga_createdate date, --数据添加时间（创建时间）
ga_state Number(2), --质物入库状态（申请中，正在申请，等等）
ga_count Number(19,2) --总记录数
);
create sequence zx_gagerId
increment by 1
start with 1;


insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'685161','张氏','168451','3511','5743261','351361','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'425','张氏','75786','789','5743261','351361','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'8978','龙氏','4786','789','789','351361','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),2,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'64161','新氏','786','3577911','5743261','351361','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'456','张氏','56','3511','789','79','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),0,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'684561','张氏','12','3511','789','351361','天猫','暂无','先票后货',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),2,5);


--质物入库商品表
create table zx_commodity(
cm_id number(19,2) primary key, --质物入库商品表主键id
cm_cmdCode varchar2(60), --商品代码
cm_stkNum Decimal(19,2), --入库数量
cm_istkPrc Decimal(19,2), --入库单价
cm_whcode varchar2(30),  --仓库代码
cm_vin varchar2(40), --车架号
cm_hgzNo varchar2(50), --合格证编号
cm_carPrice Decimal(19,2), --车价
cm_loancode varchar2(40), --融资编号
cm_gaid number(19,2)
);
create sequence zx_commodityId
increment by 1
start with 1;

insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'656518',1,46131,'68461','C63583184','H54651356',65135,'3256146',1);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'4563',1,46131,'6823461','234523','H546154356',65135,'312346146',2);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'3734562',1,46131,'623461','C234563184','H1241356',65135,'32512446',3);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'2542',1,46131,'6123461','C6345673184','H5462556',65135,'3225146',4);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'456745',1,46131,'68461','C6123484','H54251356',65135,'31246146',5);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'2345',1,46131,'682341','C63356184','H54651356',65135,'3251436',6);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'467',1,46131,'68461','C61234184','H546123456',65135,'3256146',7);
insert into zx_commodity(cm_id,cm_cmdCode,cm_stkNum,cm_istkPrc,cm_whcode,cm_vin,cm_hgzNo,cm_carPrice,cm_loancode,cm_gaid) values(zx_commodityId.nextval,'145',1,46131,'461','C63645184','H54651356',65135,'31234146',8);




--盘库信息表
create table zx_checkstock(
cs_id number(19,2) primary key,--盘库表id
cs_loncpId Varchar2(50) Not null,--借款企业id
cs_protocolno Varchar2(50) Not null,--系统监管协议编号
cs_protocolCode Varchar2(50) Not null,--纸质监管协议编号
cs_userNo Varchar2(50) Not null,--操作人编号
cs_userName Varchar2(20) Not null,--操作人名称
cs_tradeid Varchar2(50)	Not null,--交易流水号
cs_planDate Char(8) Not null,--计划盘库日期
cs_factDate Char(8) Not null,--实际盘库日期
cs_errorReport Varchar2(1000) Not null,--差错报告
cs_remark Varchar2(1000),--备注
Cs_createdate Date--创建时间
);
create sequence zx_checkstockId
increment by 1
start with 1


insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3651651','54651631','163546315','16981618','天猫商','6514861581458','20170101','20170101','暂无','暂无',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));

insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'123421','546535431','1675676315','1692123418','天猫商','65142345581458','20170201','20170201','暂无','暂无',to_date('2017-5-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'32345651','621631','16356456315','16981618','天猫商','651123411581458','20170201','20170201','暂无','暂无',to_date('2017-6-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3536751','5465631','1678315','169456618','天猫商','6514861554658','20170201','20170201','暂无','暂无',to_date('2017-7-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'36124551','54561631','1637896315','16967618','天猫商','6511324581458','201704101','20170401','暂无','暂无',to_date('2017-8-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3614651','5461531','16987906315','1695789618','天猫商','651513281458','20170501','20170501','暂无','暂无',to_date('2017-9-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));




--监管仓库和商品
create table zx_check(
ck_id number(19,2) primary key,--主键	监管仓库和商品id
ck_csid	Number(19,2),--外键引用盘库表主键
ck_spvwhcode Varchar2(20),--监管仓库代码
ck_cmcode Number(19,2),--商品代码
ck_cstkcmdnum Number(19,2),--盘库商品数量
ck_cmgrtcntno Varchar2(20),--动产质押担保合同编号
ck_vin Varchar2(40)--车架号
)
create sequence zx_checkId
increment by 1
start with 1

insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,2,'A1','31651','1','13515361','C651615515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,21,'A2','31651','1','13515361','C651615515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,22,'A4','31651','1','13515361','C651615515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,23,'A3','31651','1','13515361','C651615515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,25,'A5','31651','1','13515361','C651615515');

insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,2,'A2','31651','1','13515361','C61235515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,21,'A2','31651','1','13515361','C651235515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,22,'A2','31651','1','13515361','C6512315515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,23,'A2','31651','1','13515361','C651235515');
insert into zx_check(ck_id,ck_csid,ck_spvwhcode,ck_cmcode,ck_cstkcmdnum,ck_cmgrtcntno,ck_vin) values(zx_checkId.nextval,25,'A2','31651','1','13515361','C61235515');




--监管仓库列表
create table zx_checkwarehouse(
ch_id number(19,2) primary key,--主键 监管仓库id
ch_ckid	Number(19,2),--外键引用监管仓库和商品表中的主键
ch_whlevel Varchar2(100) not null,--仓库级别
ch_whcode varchar2(30) not null,--仓库代码
ch_whaddr Varchar2(300),--仓库地址
ch_num Number(19,2)--车辆数量
)
create sequence zx_checkwarehouseId
increment by 1
start with 1

insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A1','一级仓库','上海',3);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A3','二级仓库','上海',6);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A2','一级仓库','上海',20);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A4','三级仓库','上海',10);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A5','一级仓库','上海',2);
commit;
