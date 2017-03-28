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
insert into zx_distribset(zx_did,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,'50',2,'hz454455454','454ddd755852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'aa' );
insert into zx_distribset(zx_did,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,'30',2,'hz454455454','454755dd852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2009-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'bb' );
insert into zx_distribset(zx_did,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,'50',2,'hz454455454','4547558dd52',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2010-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'cc' );
insert into zx_distribset(zx_did,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,'40',2,'hz454455454','454755d852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'dd' );
insert into zx_distribset(zx_did,zx_moveperc,zx_bankdocktype,contractno,organizationcode,zx_createdate,zx_updatedate,zx_createuser) 
values(zx_distribsetId.nextval,'20',2,'hz454455454','454755852',to_date ( '2007-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ) ,to_date ( '2016-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'ee' );
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
--仓库查询表
create table zx_warehouse(
zx_whid number(19,2) primary key,--仓库查询表主键
zx_custno varchar2(50),--客户号
zx_whname varchar2(200),--仓库名字
zx_whcode varchar2(20),--仓库代码
zx_whlevel varchar2(20),--仓库级别
zx_whoperorg varchar2(100),--经办行
zx_whaddress varchar2(100),--仓库地址
zx_phone varchar2(12),--电话
zx_createdate date,--数据同步时间
zx_updatedate date--数据更新时间
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'1222223','京东仓库','1234154','一级','中都','上海','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'4652223','天猫仓库','1234154','一级','中信','上海','15138557776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'1222223','aa仓库','1234154','一级','平安','安徽','15138337776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'25621223','hh仓库','1234154','一级','中信','北京','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

commit;
