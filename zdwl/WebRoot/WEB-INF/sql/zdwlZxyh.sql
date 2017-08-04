--银行经销商参数表
alter table t_distribset add zxOrgCode varchar(100);

--仓库
alter table t_two_address add bkwhcode varchar(100);

--通知推送表
create table zx_notice(
nid number(19,2) primary key,--通知推送表主键
ntcno varchar2(20),--通知书编号
ntctp number(2),--通知书类型1、收货	2、移库   3、解除质押 	4、质物与融资关系变更通知 5-入库通知
ntbranchid varchar2(100),--分行id 新增
ntcdate timestamp,--通知书发送时间
nttotnum integer,--总记录数
ntfailflag number(2)--0回执失败，1接收失败，2已接收
);
create sequence zx_noticeId
increment by 1
start with 1;

-- Add comments to the columns 
comment on table zx_notice is
'通知推送表';
comment on column zx_notice.ntcno
  is '通知书编号';
comment on column zx_notice.ntctp
  is '通知书类型1收货2移库3解除质押4质物与融资关系变更通知5入库通知';
comment on column zx_notice.ntbranchid
  is '分行id';
comment on column zx_notice.ntcdate
  is '通知书发送时间';
  comment on column zx_notice.nttotnum
  is '总记录数';
  comment on column zx_notice.ntfailflag
  is '0回执失败1接收失败2已接收';

--通知推送明细
create table zx_push_notice_detail(
id number(19,2) primary key,--通知推送明细id
ntcno varchar2(20),--变更通知编号
pndEcifcode varchar2(20),--中信银行ECIF客户号
pndOperorg varchar2(100),--经办行
pndVin varchar2(40),--车架号
pndLoancode varchar2(50),--融资编号
state number(2)--变更状态  1.变更成功。2.变更失败

);
create sequence zx_push_notice_detailId
increment by 1
start with 1;

-- Add comments to the columns 
comment on table zx_push_notice_detail is
'通知推送明细';
comment on column zx_push_notice_detail.ntcno
  is '变更通知编号';
comment on column zx_push_notice_detail.pndEcifcode
  is '中信银行ECIF客户号';
comment on column zx_push_notice_detail.pndOperorg
  is '经办行';
comment on column zx_push_notice_detail.pndVin
  is '车架号';
  comment on column zx_push_notice_detail.pndLoancode
  is '融资编号';
  comment on column zx_push_notice_detail.state
  is '变更状态1变更成功2变更失败';

--客户查询表
create table zx_customer(
custid number(19,2) primary key,--客户查询表主键
custno varchar2(50),--客户号
custorganizationcode varchar2(100) not null,--组织机构代码
custname varchar2(100),--客户名称
custcreatedate date,--数据创建时间
custupdatedate date,--数据更新时间
cusconnumber number(19,2) --监管确认书编号次数
);
create sequence zx_customerId
increment by 1
start with 1;

-- Add comments to the columns 
comment on table zx_customer is
'客户查询表';
comment on column zx_customer.custno
  is '客户号';
comment on column zx_customer.custorganizationcode
  is '组织机构代码';
comment on column zx_customer.custname
  is '客户名称';
comment on column zx_customer.custcreatedate
  is '数据创建时间';
  comment on column zx_customer.custupdatedate
  is '数据更新时间';
  comment on column zx_customer.cusconnumber
  is '监管确认书编号次数';

--仓库信息查询
create table zx_warehouse(
whid number(19,2) primary key,--仓库查询表主键
custNo varchar2(50),--客户号
whlonentnm varchar2(100),--借款企业名称
whName varchar2(200),--仓库名字
whCode varchar2(20),--仓库代码 		银行端仓库代码（监管协议获取，需要去重）
whLevel number,--仓库级别（1:一级仓库，2:二级仓库）
whOperorg varchar2(100),--经办行
whAddress varchar2(100),--仓库地址
phone varchar2(12),--电话
lonentid varchar2(200),--借款企业id
whdistance varchar2(30),--二级监管仓库距离本库公里数
whContacts varchar2(60),--监管仓库联系人
createDate date,--数据创建时间
updateDate date --数据更新时间
);
create sequence zx_warehouseId
increment by 1
start with 1;

comment on table zx_warehouse is
'客户查询表';
comment on column zx_warehouse.custNo
  is '客户号';
comment on column zx_warehouse.whlonentnm
  is '借款企业名称';
comment on column zx_warehouse.whName
  is '仓库名字';
comment on column zx_warehouse.whCode
  is '仓库代码';
  comment on column zx_warehouse.whLevel
  is '仓库级别1一级仓库2二级仓库）';
  comment on column zx_warehouse.whOperorg
  is '经办行';
comment on column zx_warehouse.whAddress
  is '仓库地址';
comment on column zx_warehouse.phone
  is '电话';
comment on column zx_warehouse.lonentid
  is '借款企业id';
  comment on column zx_warehouse.whdistance
  is '二级监管仓库距离本库公里数';
  comment on column zx_warehouse.whContacts
  is '监管仓库联系人';
   comment on column zx_warehouse.createDate
  is '数据创建时间';
  comment on column zx_warehouse.updateDate
  is '数据更新时间';

--监管协议查询表 
	create table zx_agreement(
	agid number(19,2) primary key,--监管协议id
	hostno varchar2(50),--客户号
	agloncpid varchar2(20),--借款企业ID
	lonnm varchar2(200),--借款企业名称
	spvagtid varchar2(50),--系统监管协议编号
	spvagtno varchar2(50),--纸质监管协议编号
	agtstt varchar2(2),--协议状态（01,失效,02,生效）
	startdate char(8),--协议起始日（长度 8）
	enddate char(8),--协议到期日（长度 8）
	isauth varchar2(2),--是否开通线上业务（00-未开通,01-开通）
	ismv varchar2(2),--是否允许移库（00-不允许,01-允许）
	operorg varchar2(100),--经办行
	totnum Integer,--总记录数
	agcreatedate date,--数据创建时间
	agupdatedate date--数据更新时间
	);
	create sequence zx_agreementId
	increment by 1
	start with 1;

	comment on table zx_agreement is
'监管协议表';
comment on column zx_agreement.hostno
  is '客户号';
comment on column zx_agreement.agloncpid
  is '借款企业ID';
comment on column zx_agreement.lonnm
  is '借款企业名称';
comment on column zx_agreement.spvagtid
  is '系统监管协议编号';
  comment on column zx_agreement.spvagtno
  is '纸质监管协议编号';
  comment on column zx_agreement.agtstt
  is '协议状态1失效2生效';
comment on column zx_agreement.startdate
  is '协议起始日';
comment on column zx_agreement.enddate
  is '协议到期日';
comment on column zx_agreement.isauth
  is '是否开通线上业务0未开通1开通';
  comment on column zx_agreement.ismv
  is '是否允许移库0不允许1允许';
  comment on column zx_agreement.operorg
  is '经办行';
   comment on column zx_agreement.totnum
  is '总记录数';
  comment on column zx_agreement.agcreatedate
  is '数据创建时间';
  comment on column zx_agreement.agupdatedate
  is '数据更新时间';
  

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
fgagtid varchar2(50),--协议编号
fgCreateDate date,--融资数据同步时间
fgUpdateDate date--融资同步更新时间
);
create sequence zx_financingId
increment by 1
start with 1;

comment on table zx_financing is
'融资信息表';
comment on column zx_financing.fgLonentNo
  is '借款企业ID';
comment on column zx_financing.fgLoncpName
  is '借款企业名称';
comment on column zx_financing.fgStDate
  is '融资起始日期';
comment on column zx_financing.fgEndDate
  is '融资结束日期';
  comment on column zx_financing.fgLoanCode
  is '融资编号';
  comment on column zx_financing.fgScftxNo
  is '放款批次号';
comment on column zx_financing.fgLoanAmt
  is '融资金额';
comment on column zx_financing.fgBailRat
  is '保证金比例';
comment on column zx_financing.fgSlfcap
  is '自有资金比例';
  comment on column zx_financing.fgFstblRat
  is '首付保证金可提货比例';
  comment on column zx_financing.fgProcrt
  is '授信产品，银行承兑汇票';
   comment on column zx_financing.fgBizMod
  is '业务模式，先票后货';
  comment on column zx_financing.fgOperOrg
  is '经办行';
  comment on column zx_financing.fgagtid
  is '协议编号';
   comment on column zx_financing.fgCreateDate
  is '融资数据同步时间';
  comment on column zx_financing.fgUpdateDate
  is '融资同步更新时间';

--收货通知
create table zx_notify(
id number(19,2) primary key,--收货通知表主键id
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
start with 1;

comment on table zx_notify is
'收货通知表';
comment on column zx_notify.nyNo
  is '通知书编号';
comment on column zx_notify.nyCorentnm
  is '核心企业名称';
comment on column zx_notify.nySpventnm
  is '(在库)监管企业名称';
comment on column zx_notify.nyOnwspvenm
  is '(在途)监管企业名称';
  comment on column zx_notify.nyTrsptentnm
  is '运输企业名称';
  comment on column zx_notify.nyLonentno
  is '借款企业id';
comment on column zx_notify.nyLonentname
  is '借款企业名称';
comment on column zx_notify.nyCsndate
  is '发货日期';
comment on column zx_notify.nyEta
  is '预计到港(库)日期';
  comment on column zx_notify.nyEpa
  is '预计到库';
  comment on column zx_notify.nyOfflnsatno
  is '纸质监管协议编号';
   comment on column zx_notify.nyNtcdate
  is '通知书日期';
  comment on column zx_notify.nyTtlcmdval
  is '货物价值合计';
  comment on column zx_notify.nyExcplace
  is '交货地点';
   comment on column zx_notify.nyRemark
  is '备注';
  comment on column zx_notify.nyTotnum
  is '总记录数';
   comment on column zx_notify.nyCreatedate
  is '创建时间';
  comment on column zx_notify.nyUpdatedate
  is '更新时间';

--收货通知书详情
create table zx_notifydetail(
id number(19,2) primary key,--收货质物明细表主键id
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
start with 1;

comment on table zx_notifydetail is
'收货通知书详情表';
comment on column zx_notifydetail.ndNo
  is '通知书编号';
comment on column zx_notifydetail.ndIdtplanno
  is '实际纸质订单编号';
comment on column zx_notifydetail.ndIdtplannm
  is '实际订单名称';
comment on column zx_notifydetail.ndCmdcode
  is '商品代码';
  comment on column zx_notifydetail.ndCmdname
  is '商品名称';
  comment on column zx_notifydetail.ndCsnnum
  is '发货数量';
comment on column zx_notifydetail.ndUnit
  is '计量单位';
comment on column zx_notifydetail.ndCsnprc
  is '发货单价';
comment on column zx_notifydetail.ndReqcsnval
  is '发货价值';
  comment on column zx_notifydetail.ndScflonno
  is 'SCF放款批次号';
  comment on column zx_notifydetail.ndMtgcntno
  is '质押合同编号';
   comment on column zx_notifydetail.ndRemark
  is '备注';
  comment on column zx_notifydetail.ndVin
  is '车架号';
  comment on column zx_notifydetail.ndHgzno
  is '合格证编号';
   comment on column zx_notifydetail.ndCarprice
  is '车价';
  comment on column zx_notifydetail.ndLoancode
  is '融资编号';

--移库通知书
create table zx_movenotice(
id number(19,2) primary key,  --移库通知书id主键
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

comment on table zx_movenotice is
'移库通知书';
comment on column zx_movenotice.mnNo
  is '通知书编号';
comment on column zx_movenotice.mnOperorg
  is '经办行';
comment on column zx_movenotice.mnSuperviseName
  is '监管企业名称';
comment on column zx_movenotice.mnMovedate
  is '移库申请日期';
  comment on column zx_movenotice.mnLoncpName
  is '借款企业名称';
  comment on column zx_movenotice.mnNoticedate
  is '通知日期';
comment on column zx_movenotice.mnTotnum
  is '总记录数';
comment on column zx_movenotice.mnCreatedate
  is '同步时间';
comment on column zx_movenotice.mnUpdatedate
  is '更新时间';

--移库通知书详情表
create table zx_movedetail(
id number(19,2) primary key, --移库通知书详情表id
mdNo varchar2(20) not null,  --引用移库通知书表
mdRemoveoutno varchar2(20), --移除仓库编号
mdRemoveinno varchar2(20), --移入仓库编号
mdWareno varchar2(50), --商品代码   --修改为可空
mdMovenumber Decimal(8), --移库数量
mdChassisno varchar2(200), --车架号
mdCertificationNo varchar2(200), --合格证编号
mdCarprice Decimal(19,2)--车价
);
create sequence zx_movedetailId
increment by 1
start with 1;

comment on table zx_movedetail is
'移库通知书详情表';
comment on column zx_movedetail.mdNo
  is '引用移库通知书表';
comment on column zx_movedetail.mdRemoveoutno
  is '移除仓库编号';
comment on column zx_movedetail.mdRemoveinno
  is '移入仓库编号';
comment on column zx_movedetail.mdWareno
  is '商品代码';
  comment on column zx_movedetail.mdMovenumber
  is '移库数量';
  comment on column zx_movedetail.mdChassisno
  is '车架号';
comment on column zx_movedetail.mdCertificationNo
  is '合格证编号';
comment on column zx_movedetail.mdCarprice
  is '车价';

--解除质押通知书
create table zx_removepledge(
id number(19,2) primary key,
rpNo varchar2(20) not null,--通知书编号
rpOperorg varchar2(100),--经办行
rpPldegeeName varchar2(80),--出质人名称
rpLoncpId varchar2(20),--借款企业id
rpLoncpName varchar2(200),--借款企业名称
rpSuperviseName varchar2(200),--监管企业名称
rpCoreName varchar2(200),--核心企业名称
rpRelievepdDate char(8),--解除质押日期
rpContent varchar2(500),--出库原因
rpNoticeDate char(8),--通知书日期
rpCreatedate date,--同步数据时间
rpUpdatedate date--同步更新数据时间
);
create sequence zx_removepledgeId
increment by 1
start with 1;

comment on table zx_removepledge is
'解除质押通知书';
comment on column zx_removepledge.rpNo
  is '通知书编号';
comment on column zx_removepledge.rpOperorg
  is '经办行';
comment on column zx_removepledge.rpPldegeeName
  is '出质人名称';
comment on column zx_removepledge.rpLoncpId
  is '借款企业id';
  comment on column zx_removepledge.rpLoncpName
  is '借款企业名称';
  comment on column zx_removepledge.rpSuperviseName
  is '监管企业名称';
comment on column zx_removepledge.rpCoreName
  is '核心企业名称';
comment on column zx_removepledge.rpRelievepdDate
  is '解除质押日期';
    comment on column zx_removepledge.rpContent
  is '出库原因';
  comment on column zx_removepledge.rpNoticeDate
  is '通知书日期';
comment on column zx_removepledge.rpCreatedate
  is '同步数据时间';
comment on column zx_removepledge.rpUpdatedate
  is '同步更新数据时间';

--解除质押通知书详情
create table zx_removepledgedetail(
id number(19,2) primary key,
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

comment on table zx_removepledgedetail is
'解除质押通知书详情';
comment on column zx_removepledgedetail.rdNo
  is '通知书编号';
comment on column zx_removepledgedetail.rdCmdcode
  is '商品代码';
comment on column zx_removepledgedetail.rdCmdname
  is '商品名称';
comment on column zx_removepledgedetail.rdUnit
  is '计量单位';
  comment on column zx_removepledgedetail.rdStknum
  is '库存数量';
  comment on column zx_removepledgedetail.rdRlsmgnum
  is '解除质押数量';
comment on column zx_removepledgedetail.rdWhcode
  is '所在仓库编号';
comment on column zx_removepledgedetail.rdScflonno
  is 'Scf放款批次号';
    comment on column zx_removepledgedetail.rdChattelpdno
  is '动产质押担保合同编号';
  comment on column zx_removepledgedetail.rdNumber
  is '移库数量';
comment on column zx_removepledgedetail.rdChassisno
  is '车架号';
comment on column zx_removepledgedetail.rdCertificationNo
  is '合格证编号';
    comment on column zx_removepledgedetail.rdCarPrice
  is '车价';
comment on column zx_removepledgedetail.rdUserName
  is '赎货经办人姓名';
comment on column zx_removepledgedetail.rdUsercardid
  is '赎货经办人身份证号码';


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

comment on table zx_commodity is
'质物入库商品表';
comment on column zx_commodity.cmgaid
  is '质物入库表id';
comment on column zx_commodity.cmcmdCode
  is '商品代码';
comment on column zx_commodity.cmstkNum
  is '入库数量';
comment on column zx_commodity.cmistkPrc
  is '入库单价';
  comment on column zx_commodity.cmvin
  is '车架号';
  comment on column zx_commodity.cmhgzNo
  is '合格证编号';
comment on column zx_commodity.cmcarPrice
  is '车价';
comment on column zx_commodity.cmloancode
  is '融资编号';


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

comment on table zx_gager is
'质物入库表';
comment on column zx_gager.galonentno
  is '借款企业id';
comment on column zx_gager.galonentname
  is '借款企业名称';
comment on column zx_gager.gaoprtName
  is '操作人名称';
comment on column zx_gager.gaorderNo
  is '交易流水号';
  comment on column zx_gager.gapcgrtntNo
  is '纸质担保合同编号';
  comment on column zx_gager.gacmgrtcntNo
  is '动产质押担保合同编号';
comment on column zx_gager.gawhCode
  is '仓库代码';
comment on column zx_gager.garemark
  is '备注';
    comment on column zx_gager.gaconfirmNo
  is '质物监管确认书编号';
  comment on column zx_gager.gacount
  is '总记录数';
comment on column zx_gager.gastate
  is '质物入库状态';
comment on column zx_gager.gacreatedate
  is '创建时间';

--盘库信息表
create table zx_checkstock(
csId number(19,2) primary key,--盘库表id
csLoncpId Varchar2(50) Not null,--借款企业id
csLoncpname Varchar2(122) Not null,--借款企业名称
csProtocolno Varchar2(50) Not null,--系统监管协议编号
csProtocolCode Varchar2(50) Not null,--纸质监管协议编号
csUserNo Varchar2(50) Not null,--操作人编号
csUserName Varchar2(20) Not null,--操作人名称
csTradeid Varchar2(50)	Not null,--交易流水号
csPlanDate Char(8),--计划盘库日期
csFactDate Char(8) Not null,--实际盘库日期
csErrorReport Varchar2(1000) Not null,--差错报告
csRemark Varchar2(1000),--备注
csCreatedate Date--创建时间
);
create sequence zx_checkstockId
increment by 1
start with 1;

comment on table zx_checkstock is
'盘库信息表';
comment on column zx_checkstock.csLoncpId
  is '借款企业id';
comment on column zx_checkstock.csLoncpname
  is '借款企业名称';
comment on column zx_checkstock.csProtocolno
  is '系统监管协议编号';
comment on column zx_checkstock.csProtocolCode
  is '纸质监管协议编号';
  comment on column zx_checkstock.csUserNo
  is '操作人编号';
  comment on column zx_checkstock.csUserName
  is '操作人名称';
comment on column zx_checkstock.csTradeid
  is '交易流水号';
comment on column zx_checkstock.csPlanDate
  is '计划盘库日期';
    comment on column zx_checkstock.csFactDate
  is '实际盘库日期';
  comment on column zx_checkstock.csErrorReport
  is '差错报告';
comment on column zx_checkstock.csRemark
  is '备注';
comment on column zx_checkstock.csCreatedate
  is '创建时间';

--盘库信息-监管仓库和商品
create table zx_check(
ckId number(19,2) primary key,--主键	监管仓库和商品id
ckCsid	Number(19,2),--外键引用盘库表主键
ckSpvwhcode Varchar2(20),--监管仓库代码
ckVin Varchar2(40)--车架号
);
create sequence zx_checkId
increment by 1
start with 1;

comment on table zx_check is
'盘库信息-监管仓库和商品';
comment on column zx_check.ckCsid
  is '盘库表主键';
comment on column zx_check.ckSpvwhcode
  is '监管仓库代码';
comment on column zx_check.ckVin
  is '车架号';

--盘库信息-监管仓库列表
create table zx_checkwarehouse(
chId number(19,2) primary key,--主键 监管仓库id
chCkid	Number(19,2),--外键引用监管仓库和商品表中的主键
chWhlevel Varchar2(100) not null,--仓库级别
chWhcode varchar2(30) not null,--仓库代码
chWhname varchar2(100),--仓库名称
chWhaddr Varchar2(300),--仓库地址
chNum Number(19,2)--车辆数量
);
create sequence zx_checkwarehouseId
increment by 1
start with 1;

comment on table zx_checkwarehouse is
'盘库信息-监管仓库列表';
comment on column zx_checkwarehouse.chCkid
  is '引用监管仓库和商品表中的主键';
comment on column zx_checkwarehouse.chWhlevel
  is '仓库级别';
comment on column zx_checkwarehouse.chWhcode
  is '仓库代码';
  comment on column zx_checkwarehouse.chWhname
  is '仓库名称';
comment on column zx_checkwarehouse.chWhaddr
  is '仓库地址';
comment on column zx_checkwarehouse.chNum
  is '车辆数量';
  
commit;

