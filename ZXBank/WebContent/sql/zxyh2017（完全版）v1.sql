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
distribid number(19,0),--������id
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
insert into zx_customer(cust_id,cust_no,cust_organizationcode,cust_name,cust_createdate,cust_updatedate) 
values(zx_customerId.nextval,'12142623','454ddd755852','������',sysdate,sysdate);
commit;
--�ֿ��ѯ��
create table zx_warehouse(
zx_whid number(19,2) primary key,--�ֿ��ѯ������
zx_custno varchar2(50),--�ͻ���
zx_whname varchar2(200),--�ֿ�����
zx_whcode varchar2(20),--�ֿ����
zx_whlevel varchar2(20),--�ֿ⼶��
zx_whoperorg varchar2(100),--������
zx_loncpname varchar2(200),--�����ҵ���� --����
zx_whaddress varchar2(100),--�ֿ��ַ
zx_phone varchar2(12),--�绰
zx_createdate date,--����ͬ��ʱ��
zx_updatedate date--���ݸ���ʱ��
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'1222223','�����ֿ�','1234154','һ��','�ж�','�Ϻ�','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'�Ա���');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'4652223','��è�ֿ�','1234154','һ��','����','�Ϻ�','15138557776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'�Ա���');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'1222223','aa�ֿ�','1234154','һ��','ƽ��','����','15138337776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'�Ա���');
insert into zx_warehouse(zx_whid,zx_custno,zx_whname,zx_whcode,zx_whlevel,zx_whoperorg,zx_whaddress,zx_phone,zx_createdate,zx_updatedate,zx_loncpname) 
values(zx_warehouseId.nextval,'25621223','hh�ֿ�','1234154','һ��','����','����','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),'�Ա���');


--֪ͨ���ͱ�
create table zx_notice(
nt_id number(19,2) primary key,--֪ͨ���ͱ�����
nt_type number(2),--֪ͨ������1���ջ�2���ƿ�3�����Ѻ
nt_no varchar2(50),--֪ͨ����
nt_stdate date,--֪ͨ�����ʱ��
nt_enddate date,--֪ͨ�����ʱ��
nt_failflag number(2) --0����ʧ�ܣ�1�ѽ���
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
--���Э���ѯ��

create table zx_agreement(
ag_id number(19,2) primary key,--���Э��id
ag_custno varchar2(50),--�ͻ���
ag_loncpid varchar2(20),--�����ҵID
ag_loncpname varchar2(200),--�����ҵ����
ag_protocolno varchar2(50),--ϵͳ���Э����
ag_protocolcode varchar2(50),--ֽ�ʼ��Э����
ag_state number(2),--Э��״̬��0,ʧЧ,1,��Ч��
ag_stdate char(8),--Э����ʼ��
ag_enddate char(8),--Э�鵽����
ag_isonline number(2),--�Ƿ�ͨ����ҵ��
ag_ismove number(2),--�Ƿ������ƿ�
ag_operorg varchar2(100),--������
ag_totnum number(19,2),--�ܼ�¼��
ag_createdate date,--ͬ������ʱ��
ag_updatedate date--ͬ����������ʱ��
);
create sequence zx_agreementId
increment by 1
start with 1;
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145861813648','646461844','������ҵһ','56465181','654818461',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145845313648','64278844','������ҵ��','56462722181','65778998461',1,'20170312','20170320',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145878527648','646271844','������ҵ��','564785181','654798461',1,'20170312','20170320',1,1,'ƽ��',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'14586278213648','6278244','������ҵ��','5647895181','6547898461',1,'20170312','20170331',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'122782648','64782844','������ҵ��','564678181','65474461',1,'20170312','20170331',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(ag_id, ag_custno,ag_loncpid,ag_loncpname,ag_protocolno,ag_protocolcode,ag_state,ag_stdate,ag_enddate,ag_isonline,ag_ismove,ag_operorg,ag_totnum,ag_createdate,ag_updatedate)
values(zx_agreementId.nextval,'145861813648','62744','������ҵ��','5627275181','65487898461',1,'20170312','20170331',1,1,'�ж�',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--������Ϣ��ѯ��
create table zx_financing(
fiId number(19,2) primary key,--���ʲ�ѯ������
fgLonentNo varchar2(30) ,--�����ҵId
loncpname varchar2(200),--�����ҵ����
fgStDate char(8),--������ʼ���� ����ʽΪYYYYMMDD
fgEndDate char(8),--���ʽ������ڣ���ʽΪYYYYMMDD
fgLoanCode varchar2(40),-- ���ʱ��
fgScftxNo varchar2(20),--�ſ����κ�
fgLoanAmt number(19,2),--���ʽ��
fgBailRat decimal(9,6),--��֤�����
fgSlfcap decimal(9,6),--�����ʽ����
fgFstblRat decimal(9,6),--�׸���֤����������
fgProcrt varchar2(40),--���Ų�Ʒ�����гжһ�Ʊ
fgBizMod varchar2(20),--ҵ��ģʽ����Ʊ���
fgOperOrg varchar2(100),--������
fgCreateDate date,--��������ͬ��ʱ��
fgUpdateDate date--����ͬ������ʱ��
);
create sequence zx_financingId
increment by 1
start with 1;

commit;

�ջ�֪ͨ����Ϣ

create table zx_notify(
ny_id number(19,2) primary key,--�ջ�֪ͨ������id
ny_no Varchar2(50),--֪ͨ����
ny_corentnm Varchar2(200),--������ҵ����
ny_spventnm Varchar2(200),--(�ڿ�)�����ҵ����
ny_onwspvenm Varchar2(200),--����;�������ҵ����
ny_trsptentnm Varchar2(200),--������ҵ����
ny_lonentno Varchar2(30),--�����ҵid
ny_lonentname Varchar2(200),--�����ҵ����
ny_csndate date,--��������
ny_eta  date,--Ԥ�Ƶ���(��)����
ny_epa  varchar2(200),--Ԥ�Ƶ���
ny_offlnsatno Varchar2(100),--ֽ�ʼ��Э����
ny_ntcdate  date,--֪ͨ������
ny_ttlcmdval  decimal(19,2),--�����ֵ�ϼ�
ny_excplace Varchar2(200),--�����ص�
ny_remark Varchar2(500),--��ע
ny_totnum Number(19,2),--�ܼ�¼��
ny_createdate date,--����ʱ��
ny_updatedate date--����ʱ��
);
create sequence zx_notifyId
increment by 1
start with 1
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'165463514','�µ�������','�ж�','�ж�','��ͨ','168461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','1686181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2000000,'�Ϻ��ֶ�','����',10);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'1423412','�µ�������2','�ж�2','�ж�2','��ͨ1','161238461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','168126181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2005600,'�Ϻ��ֶ�','����',23);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'125123','�µ�������3','�ж�2','�ж�42','��ͨ1','16823461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','168611281',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),204000,'�Ϻ��ֶ�','����',10);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'1615153514','�µ�������4','�ж�3','�ж�4','��ͨ22','16823461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','168346181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),223200,'�Ϻ��ֶ�','����',44);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'16123512514','�µ�������5','�ж�3','�ж�4','��ͨ3','16823461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','16865181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),200300,'�Ϻ��ֶ�','����',15);
insert into zx_notify(ny_id,ny_no,ny_corentnm,ny_spventnm,ny_onwspvenm,ny_trsptentnm,ny_lonentno,ny_lonentname,ny_csndate,ny_eta,ny_epa,ny_offlnsatno,ny_ntcdate,ny_ttlcmdval,ny_excplace,ny_remark,ny_totnum) values(zx_notifyId.nextval,'162135514','�µ�������6','�ж�4','�ж�2','��3ͨ','16842361','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','168566181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),20230,'�Ϻ��ֶ�','����',15);
commit;


�ջ�֪ͨ������
create table zx_notifydetail(
nd_id number(19,2) primary key,--�ջ�������ϸ������id
nd_no varchar2(50) ,--֪ͨ����
nd_idtplanno varchar2(20),--ʵ��ֽ�ʶ������
nd_idtplannm varchar2(100),--ʵ�ʶ�������
nd_cmdcode varchar2(60),--��Ʒ����
nd_cmdname varchar2(200),--��Ʒ����
nd_csnnum number(19,2),--��������
nd_unit varchar2(2),--������λ
nd_csnprc number(19,2),--��������
nd_reqcsnval number(19,2),--������ֵ
nd_scflonno varchar2(20),--SCF�ſ����κ�
nd_mtgcntno varchar2(20),--��Ѻ��ͬ���
nd_remark varchar2(500),--��ע
nd_vin varchar2(40),--���ܺ�
nd_hgzno varchar2(50),--�ϸ�֤���
nd_carprice number(19,2),--����
nd_loancode varchar2(30)--���ʱ��
);
create sequence zx_notifydetailId
increment by 1
start with 1

insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'165463514','1646164','�ջ�֪ͨ��1325','61561','�µ�',1,'��',1000000,3151351,'1684161','16131431','����','C615986168','316513',1065400,'R1651315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'1423412','12323','�ջ�֪ͨ��112125','123123','�µ�',1,'��',1002000,3153351,'168414321','161342331','����','C61426168','3112313',1065400,'R161235');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'125123','12312','�ջ�֪ͨ��11235','612361','�µ�',1,'��',100300,312351,'1684161','12341431','����','C6153242168','31123513',1065400,'R165415');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'1615153514','123124124','�ջ�֪ͨ��1412425','6121561','�µ�',1,'��',10100,3151351,'16842341','123131431','����','C61523486168','3112313',1065400,'R16512315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'16123512514','4534534','�ջ�֪ͨ��13435','612361','�µ�',1,'��',100500,33351,'163161','16321431','����','C614546168','31123513',1065400,'R16123315');
insert into zx_notifydetail(nd_id,nd_no,nd_idtplanno,nd_idtplannm,nd_cmdcode,nd_cmdname,nd_csnnum,nd_unit,nd_csnprc,nd_reqcsnval,nd_scflonno,nd_mtgcntno,nd_remark,nd_vin,nd_hgzno,nd_carprice,nd_loancode) values(zx_notifydetailId.nextval,'162135514','567567','�ջ�֪ͨ��154525','615123161','�µ�',1,'��',100600,3121351,'16841','161341','����','C6155466168','3412513',1065400,'R165235');
commit;

--�����Ѻ֪ͨ��
create table zx_removepledge(
rp_id number(19,2) primary key,
rp_no varchar2(20) not null,--֪ͨ����
rp_operorg varchar2(100) not null,--������
rp_pldegeeName varchar2(80) not null,--����������
rp_loncpId varchar2(20) not null,--�����ҵid
rp_loncpName varchar2(200) not null,--�����ҵ����
rp_superviseName varchar2(200) not null,--�����ҵ����
rp_coreName varchar2(200) not null,--������ҵ����
rp_relievepdDate char(8) not null,--�����Ѻ����
rp_content varchar2(500),--����ԭ��
rp_noticeDate char(8),--֪ͨ������
rp_createdate date,--ͬ������ʱ��
rp_updatedate date--ͬ����������ʱ��
);
create sequence zx_removepledgeId
increment by 1
start with 1;

--�����������
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'111222333','����','����Ա1','444555666','����','�ж�','����','20170222','����','20170223',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'444555666','����','����Ա2','777888999','����','�ж�','���۳�','20170311','����','20170311',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'777888999','����','����Ա3','111222333','������','�ж�','��������','20170111','����','20170111',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'112233','����','����Ա4','445566','����','��������','�������᳧','20160322','����','20160322',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_removepledge(rp_id,rp_no,rp_operorg,rp_pldegeeName,rp_loncpId,rp_loncpName,rp_superviseName,rp_coreName,rp_relievepdDate,rp_content,rp_noticeDate,rp_createdate,rp_updatedate) 
values(zx_removepledgeId.nextval,'445566','����','����Ա5','778899','����','��˹��˹','��˹��˹��','20150222','����','20150222',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--�����Ѻ֪ͨ������
create table zx_removepledgedetail(
rd_id number(19,2) primary key,
rd_no varchar2(20) not null,--֪ͨ����
rd_cmdcode varchar2(60),--��Ʒ����
rd_cmdname varchar2(200),--��Ʒ����
rd_unit varchar2(2),--������λ
rd_stknum number(19,2),--�������
rd_rlsmgnum number(19,2),--�����Ѻ����
rd_whcode varchar2(20),--���ڲֿ���
rd_scflonno varchar2(20),--Scf�ſ����κ�
rd_chattelpdno varchar2(20),--������Ѻ������ͬ���
rd_number Decimal(8),--�ƿ�����
rd_chassisno varchar2(200),--���ܺ�
rd_certificationNo varchar2(200),--�ϸ�֤���
rd_carPrice Decimal(19,2),--����
rd_userName varchar2(20),--�������������
rd_usercardid varchar2(20)--������������֤����
);
create sequence zx_removepledgedetailId
increment by 1
start with 1;

--�����������
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','����Z','��','10','2','2222','3333','4444','0','12345678','87654321','100000','����Ա1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','����Z','��','20','5','3333','4444','1234','10','21313123','12421414','1000000','����Ա2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','������Z','��','30','10','4444','5555','2345','20','3435153251','5436788','2000000','����Ա3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','��������x','��','50','11','5555','6666','3456','30','674574235','95464363','3000000','����Ա4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,nd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','��˹��˹z','��','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','����Ա5','325252');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','����Y','��','10','2','2222','3333','4444','0','12345678','87654321','100000','����Ա1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','����Y','��','20','5','3333','4444','1234','10','21313123','12421414','1000000','����Ա2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','������Y','��','30','10','4444','5555','2345','20','3435153251','5436788','2000000','����Ա3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','��������Y','��','50','11','5555','6666','3456','30','674574235','95464363','3000000','����Ա4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','��˹��˹Y','��','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','����Ա5','325252');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'111222333','222333','����Z','��','10','2','2222','3333','4444','0','12345678','87654321','100000','����Ա1','345555555555');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'444555666','444555','����Z','��','20','5','3333','4444','1234','10','21313123','12421414','1000000','����Ա2','643634634634');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'777888999','666777','������Z','��','30','10','4444','5555','2345','20','3435153251','5436788','2000000','����Ա3','234234234');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'112233','888999','��������Z','��','50','11','5555','6666','3456','30','674574235','95464363','3000000','����Ա4','5325235');
insert into zx_removepledgedetail(rd_id,rd_no,rd_cmdcode,rd_cmdname,rd_unit,rd_stknum,rd_rlsmgnum,rd_whcode,rd_scflonno,rd_chattelpdno,rd_number,rd_chassisno,rd_certificationNo,rd_carPrice,rd_userName,rd_usercardid) 
values(zx_removepledgedetailId.nextval,'445566','000000','��˹��˹Z','��','60','12','6666','7777','5567','10','84T45Z12312','87956756','4000000','����Ա5','325252');

--�ƿ�֪ͨ��
create table zx_movenotice(
mn_id number(19,2) primary key,  --�ƿ�֪ͨ��id����
mn_no varchar2(20) not null,--����֪ͨ���ͱ�֪ͨ����
mn_operorg varchar2(100),   --������
mn_superviseName varchar2(200),  --�����ҵ����
mn_movedate char(8),  --�ƿ���������
mn_loncpName varchar2(200),  --�����ҵ����
mn_noticedate char(8),  --֪ͨ����
mn_totnum number(19,2),  --�ܼ�¼��
mn_createdate date,  --ͬ��ʱ��
mn_updatedate date  --��������ʱ��
);
create sequence zx_movenoticeId
increment by 1
start with 1;

--�����������
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'111222333','����','�ж�','20161212','����','20161212','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'222333444','����','�ж�','20170101','����','20170101','20',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'333444555','����','�ж�','20170202','������','20170202','30',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'44455566','����','�ж�','20170303','��������','20170303','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_movenotice(mn_id,mn_no,mn_operorg,mn_superviseName,mn_movedate,mn_loncpName,mn_noticedate,mn_totnum,mn_createdate,mn_updatedate) 
values(zx_movenoticeId.nextval,'555666777','����','�ж�','20170404','��˹��˹','20170404','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--�ƿ�֪ͨ�������
create table zx_movedetail(
md_id number(19,2) primary key, --�ƿ�֪ͨ�������id
md_no varchar2(20) not null,  --�����ƿ�֪ͨ���
md_removeoutno varchar2(20) not null, --�Ƴ��ֿ���
md_removeinno varchar2(20) not null, --����ֿ���
md_wareno varchar2(50) not null, --��Ʒ����
md_movenumber Decimal(8) not null, --�ƿ�����
md_chassisno varchar2(200) not null, --���ܺ�
md_certificationNo varchar2(200) not null, --�ϸ�֤���
md_carprice Decimal(19,2) not null --����
);
create sequence zx_movedetailId
increment by 1
start with 1;

--�����������
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

--��������
create table zx_gager(
ga_id number(19,2) primary key,--�������id����
ga_lonentno varchar2(30), --�����ҵid
ga_oprtName varchar2(40), --����������
ga_orderNo varchar2(20), --������ˮ�� Ψһ 
ga_pcgrtntNo varchar2(100), --ֽ�ʵ�����ͬ���
ga_cmgrtcntNo varchar2(30), --������Ѻ������ͬ��� 
ga_confirmNo varchar2(30), --������ȷ������
ga_lonentname varchar2(200),  --�����ҵ����
ga_remark varchar2(500),  --��ע
ga_bizMod varchar2(20), --ҵ��ģʽ
ga_createdate date, --�������ʱ�䣨����ʱ�䣩
ga_state Number(2), --�������״̬�������У��������룬�ȵȣ�
ga_count Number(19,2) --�ܼ�¼��
);
create sequence zx_gagerId
increment by 1
start with 1;


insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'685161','����','168451','3511','5743261','351361','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'425','����','75786','789','5743261','351361','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'8978','����','4786','789','789','351361','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),2,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'64161','����','786','3577911','5743261','351361','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),1,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'456','����','56','3511','789','79','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),0,5);
insert into zx_gager(ga_id,ga_lonentno,ga_oprtName,ga_orderNo,ga_pcgrtntNo,ga_cmgrtcntNo,ga_confirmNo,ga_lonentname,ga_remark,ga_bizMod,ga_createdate,ga_state,ga_count) values(zx_gagerId.nextval,'684561','����','12','3511','789','351361','��è','����','��Ʊ���',to_date('2017-9-20 20:20:20','YYYY-MM-DD HH24:MI:SS'),2,5);


--���������Ʒ��
create table zx_commodity(
cm_id number(19,2) primary key, --���������Ʒ������id
cm_cmdCode varchar2(60), --��Ʒ����
cm_stkNum Decimal(19,2), --�������
cm_istkPrc Decimal(19,2), --��ⵥ��
cm_whcode varchar2(30),  --�ֿ����
cm_vin varchar2(40), --���ܺ�
cm_hgzNo varchar2(50), --�ϸ�֤���
cm_carPrice Decimal(19,2), --����
cm_loancode varchar2(40), --���ʱ��
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




--�̿���Ϣ��
create table zx_checkstock(
cs_id number(19,2) primary key,--�̿��id
cs_loncpId Varchar2(50) Not null,--�����ҵid
cs_protocolno Varchar2(50) Not null,--ϵͳ���Э����
cs_protocolCode Varchar2(50) Not null,--ֽ�ʼ��Э����
cs_userNo Varchar2(50) Not null,--�����˱��
cs_userName Varchar2(20) Not null,--����������
cs_tradeid Varchar2(50)	Not null,--������ˮ��
cs_planDate Char(8) Not null,--�ƻ��̿�����
cs_factDate Char(8) Not null,--ʵ���̿�����
cs_errorReport Varchar2(1000) Not null,--�����
cs_remark Varchar2(1000),--��ע
Cs_createdate Date--����ʱ��
);
create sequence zx_checkstockId
increment by 1
start with 1


insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3651651','54651631','163546315','16981618','��è��','6514861581458','20170101','20170101','����','����',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));

insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'123421','546535431','1675676315','1692123418','��è��','65142345581458','20170201','20170201','����','����',to_date('2017-5-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'32345651','621631','16356456315','16981618','��è��','651123411581458','20170201','20170201','����','����',to_date('2017-6-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3536751','5465631','1678315','169456618','��è��','6514861554658','20170201','20170201','����','����',to_date('2017-7-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'36124551','54561631','1637896315','16967618','��è��','6511324581458','201704101','20170401','����','����',to_date('2017-8-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));
insert into zx_checkstock(cs_id,cs_loncpId,cs_protocolno,cs_protocolCode,cs_userNo,cs_userName,cs_tradeid,cs_planDate,cs_factDate,cs_errorReport,cs_remark,Cs_createdate) values(zx_checkstockId.nextval,'3614651','5461531','16987906315','1695789618','��è��','651513281458','20170501','20170501','����','����',to_date('2017-9-20 18:31:34','YYYY-MM-DD HH24:MI:SS'));




--��ֿܲ����Ʒ
create table zx_check(
ck_id number(19,2) primary key,--����	��ֿܲ����Ʒid
ck_csid	Number(19,2),--��������̿������
ck_spvwhcode Varchar2(20),--��ֿܲ����
ck_cmcode Number(19,2),--��Ʒ����
ck_cstkcmdnum Number(19,2),--�̿���Ʒ����
ck_cmgrtcntno Varchar2(20),--������Ѻ������ͬ���
ck_vin Varchar2(40)--���ܺ�
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




--��ֿܲ��б�
create table zx_checkwarehouse(
ch_id number(19,2) primary key,--���� ��ֿܲ�id
ch_ckid	Number(19,2),--������ü�ֿܲ����Ʒ���е�����
ch_whlevel Varchar2(100) not null,--�ֿ⼶��
ch_whcode varchar2(30) not null,--�ֿ����
ch_whaddr Varchar2(300),--�ֿ��ַ
ch_num Number(19,2)--��������
)
create sequence zx_checkwarehouseId
increment by 1
start with 1

insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A1','һ���ֿ�','�Ϻ�',3);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A3','�����ֿ�','�Ϻ�',6);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A2','һ���ֿ�','�Ϻ�',20);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A4','�����ֿ�','�Ϻ�',10);
insert into zx_checkwarehouse(ch_id,ch_whcode,ch_whlevel,ch_whaddr,ch_num) values(zx_checkwarehouseId.nextval,'A5','һ���ֿ�','�Ϻ�',2);
commit;
