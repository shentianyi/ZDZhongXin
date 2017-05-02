--收货通知
create table zx_notify(
nyId number(19,2) primary key,--收货通知表主键id
nyNo Varchar2(50),--通知书编号
nyCorentnm Varchar2(200),--核心企业名称
nySpventnm Varchar2(200),--(在库)监管企业名称
nyOnwspvenm Varchar2(200),--（在途）监管企业名称
nyTrsptentnm Varchar2(200),--运输企业名称
nyLonentno Varchar2(30),--借款企业id
nyLonentname Varchar2(200),--借款企业名称
nyCsndate date,--发货日期
nyEta  date,--预计到港(库)日期
nyEpa  varchar2(200),--预计到库
nyOfflnsatno Varchar2(100),--纸质监管协议编号
nyNtcdate  date,--通知书日期
nyTtlcmdval  decimal(19,2),--货物价值合计
nyExcplace Varchar2(200),--交货地点
nyRemark Varchar2(500),--备注
nyTotnum Number(19,2),--总记录数
nyCreatedate date,--创建时间
nyUpdatedate date--更新时间
);
create sequence zx_notifyId
increment by 1
start with 1
insert into zx_notify(nyId,nyNo,nyCorentnm,nySpventnm,nyOnwspvenm,nyTrsptentnm,nyLonentno,nyLonentname,nyCsndate,nyEta,nyEpa,nyOfflnsatno,nyNtcdate,nyTtlcmdval,nyExcplace,nyRemark,nyTotnum) values(zx_notifyId.nextval,'165463514','奥迪生产厂','中都','中都','申通','168461','天猫',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'天猫仓库','1686181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2000000,'上海浦东','暂无',10);
commit;


--收货通知书详情
create table zx_notifydetail(
ndId number(19,2) primary key,--收货质物明细表主键id
ndNo varchar2(50) ,--通知书编号
ndIdtplanno varchar2(20),--实际纸质订单编号
ndIdtplannm varchar2(100),--实际订单名称
ndCmdcode varchar2(60),--商品代码
ndCmdname varchar2(200),--商品名称
ndCsnnum number(19,2),--发货数量
ndUnit varchar2(2),--计量单位
ndCsnprc number(19,2),--发货单价
ndReqcsnval number(19,2),--发货价值
ndScflonno varchar2(20),--SCF放款批次号
ndMtgcntno varchar2(20),--质押合同编号
ndRemark varchar2(500),--备注
ndVin varchar2(40),--车架号
ndHgzno varchar2(50),--合格证编号
ndCarprice number(19,2),--车价
ndLoancode varchar2(30)--融资编号
);
create sequence zx_notifydetailId
increment by 1
start with 1

insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'165463514','1646164','收货通知单1325','61561','奥迪',1,'辆',1000000,3151351,'1684161','16131431','暂无','C615986168','316513',1065400,'R1651315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1423412','12323','收货通知单112125','123123','奥迪',1,'辆',1002000,3153351,'168414321','161342331','暂无','C61426168','3112313',1065400,'R161235');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'125123','12312','收货通知单11235','612361','奥迪',1,'辆',100300,312351,'1684161','12341431','暂无','C6153242168','31123513',1065400,'R165415');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1615153514','123124124','收货通知单1412425','6121561','奥迪',1,'辆',10100,3151351,'16842341','123131431','暂无','C61523486168','3112313',1065400,'R16512315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'16123512514','4534534','收货通知单13435','612361','奥迪',1,'辆',100500,33351,'163161','16321431','暂无','C614546168','31123513',1065400,'R16123315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'162135514','567567','收货通知单154525','615123161','奥迪',1,'辆',100600,3121351,'16841','161341','暂无','C6155466168','3412513',1065400,'R165235');
commit;



--移库通知书
create table zx_movenotice(
mnId number(19,2) primary key,  --移库通知书id主键
mnNo varchar2(20) not null,--引用通知推送表通知书编号
mnOperorg varchar2(100),   --经办行
mnSuperviseName varchar2(200),  --监管企业名称
mnMovedate char(8),  --移库申请日期
mnLoncpName varchar2(200),  --借款企业名称
mnNoticedate char(8),  --通知日期
mnTotnum number(19,2),  --总记录数
mnCreatedate date,  --同步时间
mnUpdatedate date  --更新数据时间
);
create sequence zx_movenoticeId
increment by 1
start with 1;

--插入测试数据
insert into zx_movenotice(mnId,mnNo,mnOperorg,mnSuperviseName,mnMovedate,mnLoncpName,mnNoticedate,mnTotnum,mnCreatedate,mnUpdatedate) values(zx_movenoticeId.nextval,'111222333','中信','中都','20161212','宝马','20161212','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;


--移库通知书详情表
create table zx_movedetail(
mdId number(19,2) primary key, --移库通知书详情表id
mdNo varchar2(20) not null,  --引用移库通知书表
mdRemoveoutno varchar2(20) not null, --移除仓库编号
mdRemoveinno varchar2(20) not null, --移入仓库编号
mdWareno varchar2(50) not null, --商品代码
mdMovenumber Decimal(8) not null, --移库数量
mdChassisno varchar2(200) not null, --车架号
mdCertificationNo varchar2(200) not null, --合格证编号
mdCarprice Decimal(19,2) not null --车价
);
create sequence zx_movedetailId
increment by 1
start with 1;

--插入测试数据
insert into zx_movedetail(mdId,mdNo,mdRemoveoutno,mdRemoveinno,mdWareno,mdMovenumber,mdChassisno,mdCertificationNo,mdCarprice)values(zx_movedetailId.nextval,'111222333','111222','222333','222222','10','234234','234235465','1000000.00');
COMMIT;


--解除质押通知书
create table zx_removepledge(
rpId number(19,2) primary key,
rpNo varchar2(20) not null,--通知书编号
rpOperorg varchar2(100) not null,--经办行
rpPldegeeName varchar2(80) not null,--出质人名称
rpLoncpId varchar2(20) not null,--借款企业id
rpLoncpName varchar2(200) not null,--借款企业名称
rpSuperviseName varchar2(200) not null,--监管企业名称
rpCoreName varchar2(200) not null,--核心企业名称
rpRelievepdDate char(8) not null,--解除质押日期
rpContent varchar2(500),--出库原因
rpNoticeDate char(8),--通知书日期
rpCreatedate date,--同步数据时间
rpUpdatedate date--同步更新数据时间
);
create sequence zx_removepledgeId
increment by 1
start with 1;

--插入测试数据
insert into zx_removepledge(rpId,rpNo,rpOperorg,rpPldegeeName,rpLoncpId,rpLoncpName,rpSuperviseName,rpCoreName,rpRelievepdDate,rpContent,rpNoticeDate,rpCreatedate,rpUpdatedate)values(zx_removepledgeId.nextval,'111222333','中信','测试员1','444555666','宝马','中都','宝马厂','20170222','出库','20170223',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));




--解除质押通知书详情
create table zx_removepledgedetail(
rdId number(19,2) primary key,
rdNo varchar2(20) not null,--通知书编号
rdCmdcode varchar2(60),--商品代码
rdCmdname varchar2(200),--商品名称
rdUnit varchar2(2),--计量单位
rdStknum number(19,2),--库存数量
rdRlsmgnum number(19,2),--解除质押数量
rdWhcode varchar2(20),--所在仓库编号
rdScflonno varchar2(20),--Scf放款批次号
rdChattelpdno varchar2(20),--动产质押担保合同编号
rdNumber Decimal(8),--移库数量
rdChassisno varchar2(200),--车架号
rdCertificationNo varchar2(200),--合格证编号
rdCarPrice Decimal(19,2),--车价
rdUserName varchar2(20),--赎货经办人姓名
rdUsercardid varchar2(20)--赎货经办人身份证号码
);
create sequence zx_removepledgedetailId
increment by 1
start with 1;



--插入测试数据
insert into zx_removepledgedetail(rdId,rdNo,rdCmdcode,rdCmdname,rdUnit,rdStknum,rdRlsmgnum,rdWhcode,rdScflonno,rdChattelpdno,rdNumber,rdChassisno,rdCertificationNo,rdCarPrice,rdUserName,rdUsercardid)values(zx_removepledgedetailId.nextval,'111222333','222333','宝马Z','辆','10','2','2222','3333','4444','0','12345678','87654321','100000','测试员1','345555555555');
commit;


---客户信息查询表

create table zx_customer(
custId number(19,2) primary key,--客户查询表主键
custNo varchar2(50),--客户号
custOrganizationcode varchar2(100) not null,--组织机构代码
custName varchar2(100),--客户名称
custCreatedate date,--数据同步时间
custUpdatedate date--数据同步更新时间
);
create sequence zx_customerId
increment by 1
start with 1;
insert into zx_customer(custId,custNo,custOrganizationcode,custName,custCreatedate,custUpdatedate)values(zx_customerId.nextval,'1222223','454ddd755852','测试一',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;


--仓库信息查询
create table zx_warehouse(
Whid number(19,2) primary key,--仓库查询表主键
custNo varchar2(50),--客户号
whName varchar2(200),--仓库名字
whCode varchar2(20),--仓库代码
whLevel number,--仓库级别
whOperorg varchar2(100),--经办行
loncpname varchar2(100),--借款企业名称
lonentid varchar2(200),--借款企业id --新增
whAddress varchar2(100),--仓库地址
phone varchar2(12),--电话
createDate date,--数据同步时间
updateDate date--数据更新时间
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(Whid,custNo,whName,whCode,whLevel,whOperorg,loncpname,lonentid,whAddress,phone,createDate,updateDate)values(zx_warehouseId.nextval,'1222223','京东仓库','1234154',1,'中都','天猫','16546814','上海','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;







--融资信息查询表
create table zx_financing(
fiId number(19,2) primary key,--融资查询表主键
fgLonentNo varchar2(30) ,--借款企业Id
fgLoncpName varchar2(100),--借款企业的名称
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



--监管协议查询表
	create table zx_agreement(
	agid number(19,2) primary key,--监管协议id
	hostno varchar2(50),--客户号
	agloncpid varchar2(20),--借款企业ID
	lonnm varchar2(200),--借款企业名称
	spvagtid varchar2(50),--系统监管协议编号
	spvagtno varchar2(50),--纸质监管协议编号
	agtstt varchar2(2),--协议状态（0,失效,1,生效）
	startdate char(8),--协议起始日
	enddate char(8),--协议到期日
	isauth varchar2(2),--是否开通线上业务
	ismv varchar2(2),--是否允许移库
	operorg varchar2(100),--经办行
	totnum Integer,--总记录数
	agcreatedate date,--同步数据时间
	agupdatedate date--同步更新数据时间
	);
	create sequence zx_agreementId
	increment by 1
	start with 1;
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','测试企业一','56465181','654818461',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','测试企业一','123','1231',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	COMMIT;

--客户查询表
create table zx_customer(
custid number(19,2) primary key,--客户查询表主键
custno varchar2(50),--客户号
custorganizationcode varchar2(100) not null,--组织机构代码
custname varchar2(100),--客户名称
custcreatedate date,--数据同步时间
custupdatedate date--数据同步更新时间
);










--质物入库商品表
create table zx_commodity(
cmid number(19,2) primary key, --质物入库商品表主键id
cmgaid number(19,2),--质物入库表id 外键
cmcmdCode varchar2(60), --商品代码
cmstkNum Decimal(19,2), --入库数量
cmistkPrc Decimal(19,2), --入库单价
cmvin varchar2(40), --车架号
cmhgzNo varchar2(50), --合格证编号
cmcarPrice Decimal(19,2), --车价
cmloancode varchar2(40)--融资编号
);
create sequence zx_commodityId
increment by 1
start with 1;

insert into zx_commodity(cmid,cmgaid,cmcmdCode,cmstkNum,cmistkPrc,cmvin,cmhgzNo,cmcarPrice,cmloancode) values(zx_commodityId.nextval,1,'656518',1,46131,'C63583184','H54651356',65135,'3256146');
commit;


--质物入库表
create table zx_gager(
gaid number(19,2) primary key,--质物入库id主键
galonentno varchar2(30), --借款企业id
galonentname varchar2(200),  --借款企业名称
gaoprtName varchar2(40), --操作人名称
gaorderNo varchar2(20), --交易流水号 唯一 
gapcgrtntNo varchar2(100), --纸质担保合同编号
gacmgrtcntNo varchar2(30), --动产质押担保合同编号 
gawhCode varchar2(20),--仓库代码
garemark varchar2(500),  --备注
gaconfirmNo varchar2(30), --质物监管确认书编号
gacount Number(19,2), --总记录数
gastate Number(2), --质物入库状态（申请中，正在申请，等等）
gacreatedate date --数据添加时间（创建时间）
);
create sequence zx_gagerId
increment by 1
start with 1;

insert into zx_gager(gaid,galonentno,galonentname,gaoprtName,gaorderNo,gapcgrtntNo,gacmgrtcntNo,gawhCode,garemark,gaconfirmNo,gacount,gastate,gacreatedate) values(zx_gagerId.nextval,'56343','天猫s','caicaicai','658631','635361','65361','A1','暂无','3531165',5,1,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;



--通知推送表
create table zx_notice(
nid number(19,2) primary key,--通知推送表主键
ntcno varchar2(20),--通知书编号
ntctp number(10),--通知书类型1、收货	2、移库   3、解除质押 	4、质物与融资关系变更通知
ntbranchid varchar2（100），--分行id 新增
ntcdate timestamp,--通知书接收时间
nttotnum integer,--总记录数
ntfailflag number(2) --0回执失败，1接收失败，2已接收
);
create sequence zx_noticeId
increment by 1
start with 1;

insert into zx_notice(nid,ntcno,ntctp,ntbranchid,ntcdate,nttotnum,ntfailflag) values(zx_noticeId.nextval,'123456','1','194194',to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),10,2);
insert into zx_notice(nid,ntcno,ntctp,ntbranchid,ntcdate,nttotnum,ntfailflag) values(zx_noticeId.nextval,'456456','2','194194',to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),10,2);
insert into zx_notice(nid,ntcno,ntctp,ntbranchid,ntcdate,nttotnum,ntfailflag) values(zx_noticeId.nextval,'789789','3','194194',to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),10,2);
insert into zx_notice(nid,ntcno,ntctp,ntbranchid,ntcdate,nttotnum,ntfailflag) values(zx_noticeId.nextval,'126126','1','194194',to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),10,1);
insert into zx_notice(nid,ntcno,ntctp,ntbranchid,ntcdate,nttotnum,ntfailflag) values(zx_noticeId.nextval,'123123','2','194194',to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),10,0);

commit;


--通知推送明细
create table zx_push_notice_detail(
pndid number(19,2) primary key,--通知推送明细id
nid number(19,2),--通知推送表id  外键
pndEcifcode varchar2(20),--中信银行ECIF客户号
pndOperorg varchar2(100),--经办行
pndVin varchar2(40),--车架号
pndLoancode varchar2(50)--融资编号
);
create sequence zx_push_notice_detailId
increment by 1
start with 1;

insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,44,'123123','上海支行');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,44,'789789','上海支行');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,45,'1123','上海支行');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,45,'456789','上海支行');





--监管协议查询表
	create table zx_agreement(
	agid number(19,2) primary key,--监管协议id
	hostno varchar2(50),--客户号
	agloncpid varchar2(20),--借款企业ID
	lonnm varchar2(200),--借款企业名称
	spvagtid varchar2(50),--系统监管协议编号
	spvagtno varchar2(50),--纸质监管协议编号
	agtstt varchar2(2),--协议状态（0,失效,1,生效）
	startdate char(8),--协议起始日
	enddate char(8),--协议到期日
	isauth varchar2(2),--是否开通线上业务
	ismv varchar2(2),--是否允许移库
	operorg varchar2(100),--经办行
	totnum Integer,--总记录数
	agcreatedate date,--同步数据时间
	agupdatedate date--同步更新数据时间
	);
	create sequence zx_agreementId
	increment by 1
	start with 1;
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','测试企业一','56465181','654818461',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','测试企业一','123','1231',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	COMMIT;