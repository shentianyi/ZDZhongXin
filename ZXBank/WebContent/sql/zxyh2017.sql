----�����û�
create user c##zxyh identified by 123456;
grant connect,resource,dba to c##zxyh;
--�������о����̲�����

create table t_distribset(
id number(19,2) primary key,
moveperc varchar2(100),--������ƶ��ٷֱ�
distribid number(19,0),--������id
bankdocktype number,--�Խ��������� 0���Խ�1��������2��������
zscustno varchar2(100),--�������пͻ���
contract varchar2(100)--��ͬ���
);
create sequence t_distribsetId
increment by 1
start with 1;
--�������о����̲�����
create table zx_distribset(
zx_did number(19,2) primary key,--�����̲�����id ����
zx_moveperc varchar2(100),--������ƶ��ٷֱ�
zx_bankdocktype number,--�Խ��������� 0���Խ�1��������2��������
contractno varchar2(100),--��ͬ���
organizationcode varchar2(100) not null,--��֯��������
zx_createdate date,--�������ݴ���ʱ��
zx_updatedate date,--���������޸�ʱ��
zx_createuser varchar2(50)--�������ݴ�����
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
--�ͻ���ѯ��
create table zx_customer(
cust_id number(19,2) primary key,--�ͻ���ѯ������
cust_no varchar2(50),--�ͻ���
cust_organizationcode varchar2(100) not null,--��֯��������
cust_name varchar2(100),--�ͻ�����
cust_createdate date,--����ͬ��ʱ��
cust_updatedate date--����ͬ������ʱ��
);
create sequence zx_customerId
increment by 1
start with 1;
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'1222223','454ddd755852','����һ',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'4652223','4547558dd52','���Զ�',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'6632223','4547558dd52','������',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'25621223','454ddd755852','������',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'12142623','454ddd755852','������',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
--�ֿ��ѯ��
create table zx_warehouse(
zx_whid number(19,2) primary key,--�ֿ��ѯ������
zx_custno varchar2(50),--�ͻ���
zx_whname varchar2(200),--�ֿ�����
zx_whcode varchar2(20),--�ֿ����
zx_whlevel varchar2(20),--�ֿ⼶��
zx_whoperorg varchar2(100),--������
zx_whaddress varchar2(100),--�ֿ��ַ
zx_phone varchar2(12),--�绰
zx_createdate date,--����ͬ��ʱ��
zx_updatedate date--���ݸ���ʱ��
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'1222223','�����ֿ�','1234154','һ��','�ж�','�Ϻ�','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'4652223','��è�ֿ�','1234154','һ��','����','�Ϻ�','15138557776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'1222223','aa�ֿ�','1234154','һ��','ƽ��','����','15138337776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate) 
values(zx_warehouseId.nextval,'25621223','hh�ֿ�','1234154','һ��','����','����','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

commit;
