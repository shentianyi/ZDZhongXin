--客户查询表
create table zx_customer(
custid number(19,2) primary key,--客户查询表主键
custno varchar2(50),--客户号
custorganizationcode varchar2(100) not null,--组织机构代码
custname varchar2(100),--客户名称
custcreatedate date,--数据创建时间
custupdatedate date--数据更新时间
);





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
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'1222223','天猫商城','京东仓库','1234154',1,'中都支行','上海浦东','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'12341251','天猫商城','京东仓库','1234154',1,'中都支行','上海浦东','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'1123412','天猫商城','京东仓库','1234154',1,'中都支行','上海浦东','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'12234523','天猫商城','京东仓库','1234154',1,'中都支行','上海浦东','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;



--监管协议查询表
create table zx_agreement(
agid number(19,2) primary key,--监管协议id
agcustno varchar2(50),--客户号
agloncpid varchar2(20),--借款企业ID （银行借款企业ECIF编号）
agloncpname varchar2(200),--借款企业名称
agprotocolno varchar2(50),--系统监管协议编号
agprotocolcode varchar2(50),--纸质监管协议编号
agstate number(2),--协议状态（01-生效,02-失效）
agstdate char(8),--协议起始日
agenddate char(8),--协议到期日
agisonline number(2),--是否开通线上业务（00-未开通,01-开通）
agismove number(2),--是否允许移库（00-不允许,01-允许）
agoperorg varchar2(100),--经办行
agtotnum number(19,2),--总记录数
agcreatedate date,--数据创建时间
agupdatedate date--数据更新时间
);
create sequence zx_agreementId
increment by 1
start with 1;
insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','测试企业一','56465181','654818461',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','测试企业一','123','1231',1,'20170312','20170330',1,1,'中信',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;


--监管协议查询表   最新
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
  
  insert with 1;
  insert into zx_agreement(agid,hostno,agloncpid,lonnm,spvagtid,spvagtno,agtstt,startdate,enddate,isauth,ismv,operorg,totnum,agcreatedate,agupdatedate ) valuse()

