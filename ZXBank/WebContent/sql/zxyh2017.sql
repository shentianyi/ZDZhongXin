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
custId number(19,2) primary key,--�ͻ���ѯ������
custNo varchar2(50),--�ͻ���
custOrganizationcode varchar2(100) not null,--��֯��������
custName varchar2(100),--�ͻ�����
custCreatedate date,--����ͬ��ʱ��
custUpdatedate date--����ͬ������ʱ��
);
create sequence zx_customerId
increment by 1
start with 1;
insert into zx_customer(custId,custNo,custOrganizationcode,custName,custCreatedate,custUpdatedate)values(zx_customerId.nextval,'1222223','454ddd755852','����һ',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;
--�ֿ��ѯ��
create table zx_warehouse(
Whid number(19,2) primary key,--�ֿ��ѯ������
custNo varchar2(50),--�ͻ���
whName varchar2(200),--�ֿ�����
whCode varchar2(20),--�ֿ����
whLevel number,--�ֿ⼶��
whOperorg varchar2(100),--������
loncpname varchar2(100),--�����ҵ����
whAddress varchar2(100),--�ֿ��ַ
phone varchar2(12),--�绰
createDate date,--����ͬ��ʱ��
updateDate date--���ݸ���ʱ��
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(Whid,custNo,whName,whCode,whLevel,whOperorg,loncpname,whAddress,phone,createDate,updateDate)values(zx_warehouseId.nextval,'1222223','�����ֿ�','1234154',1,'�ж�','��è','�Ϻ�','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;
--֪ͨ����
create table zx_notice(
ntid number(19,2) primary key,--֪ͨ���ͱ�����
nttype number(2),--֪ͨ������1���ջ�2���ƿ�3�����Ѻ
ntno varchar2(50),--֪ͨ����
ntstdate date,--֪ͨ�����ʱ��
ntenddate date,--֪ͨ�����ʱ��
ntfailflag number(2) --0����ʧ�ܣ�1�ѽ���
);
create sequence zx_noticeId
increment by 1
start with 1;

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

--�ջ�֪ͨ����Ϣ
create table zx_notify(
nyId number(19,2) primary key,--�ջ�֪ͨ������id
nyNo Varchar2(50),--֪ͨ����
nyCorentnm Varchar2(200),--������ҵ����
nySpventnm Varchar2(200),--(�ڿ�)�����ҵ����
nyOnwspvenm Varchar2(200),--����;�������ҵ����
nyTrsptentnm Varchar2(200),--������ҵ����
nyLonentno Varchar2(30),--�����ҵid
nyLonentname Varchar2(200),--�����ҵ����
nyCsndate date,--��������
nyEta  date,--Ԥ�Ƶ���(��)����
nyEpa  varchar2(200),--Ԥ�Ƶ���
nyOfflnsatno Varchar2(100),--ֽ�ʼ��Э����
nyNtcdate  date,--֪ͨ������
nyTtlcmdval  decimal(19,2),--�����ֵ�ϼ�
nyExcplace Varchar2(200),--�����ص�
nyRemark Varchar2(500),--��ע
nyTotnum Number(19,2),--�ܼ�¼��
nyCreatedate date,--����ʱ��
nyUpdatedate date--����ʱ��
);
create sequence zx_notifyId
increment by 1
start with 1;
insert into zx_notify(nyId,nyNo,nyCorentnm,nySpventnm,nyOnwspvenm,nyTrsptentnm,nyLonentno,nyLonentname,nyCsndate,nyEta,nyEpa,nyOfflnsatno,nyNtcdate,nyTtlcmdval,nyExcplace,nyRemark,nyTotnum) values(zx_notifyId.nextval,'165463514','�µ�������','�ж�','�ж�','��ͨ','168461','��è',to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),to_date('2017-2-20 18:31:34','YYYY-MM-DD HH24:MI:SS'),'��è�ֿ�','1686181',to_date('2017-3-3 18:18:18','YYYY-MM-DD HH24:MI:SS'),2000000,'�Ϻ��ֶ�','����',10);
commit;


--�ջ�֪ͨ������
create table zx_notifydetail(
ndId number(19,2) primary key,--�ջ�������ϸ������id
ndNo varchar2(50) ,--֪ͨ����
ndIdtplanno varchar2(20),--ʵ��ֽ�ʶ������
ndIdtplannm varchar2(100),--ʵ�ʶ�������
ndCmdcode varchar2(60),--��Ʒ����
ndCmdname varchar2(200),--��Ʒ����
ndCsnnum number(19,2),--��������
ndUnit varchar2(2),--������λ
ndCsnprc number(19,2),--��������
ndReqcsnval number(19,2),--������ֵ
ndScflonno varchar2(20),--SCF�ſ����κ�
ndMtgcntno varchar2(20),--��Ѻ��ͬ���
ndRemark varchar2(500),--��ע
ndVin varchar2(40),--���ܺ�
ndHgzno varchar2(50),--�ϸ�֤���
ndCarprice number(19,2),--����
ndLoancode varchar2(30)--���ʱ��
);
create sequence zx_notifydetailId
increment by 1
start with 1;

insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'165463514','1646164','�ջ�֪ͨ��1325','61561','�µ�',1,'��',1000000,3151351,'1684161','16131431','����','C615986168','316513',1065400,'R1651315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1423412','12323','�ջ�֪ͨ��112125','123123','�µ�',1,'��',1002000,3153351,'168414321','161342331','����','C61426168','3112313',1065400,'R161235');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'125123','12312','�ջ�֪ͨ��11235','612361','�µ�',1,'��',100300,312351,'1684161','12341431','����','C6153242168','31123513',1065400,'R165415');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1615153514','123124124','�ջ�֪ͨ��1412425','6121561','�µ�',1,'��',10100,3151351,'16842341','123131431','����','C61523486168','3112313',1065400,'R16512315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'16123512514','4534534','�ջ�֪ͨ��13435','612361','�µ�',1,'��',100500,33351,'163161','16321431','����','C614546168','31123513',1065400,'R16123315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'162135514','567567','�ջ�֪ͨ��154525','615123161','�µ�',1,'��',100600,3121351,'16841','161341','����','C6155466168','3412513',1065400,'R165235');
commit;

--�����Ѻ֪ͨ��
create table zx_removepledge(
rpId number(19,2) primary key,
rpNo varchar2(20) not null,--֪ͨ����
rpOperorg varchar2(100) not null,--������
rpPldegeeName varchar2(80) not null,--����������
rpLoncpId varchar2(20) not null,--�����ҵid
rpLoncpName varchar2(200) not null,--�����ҵ����
rpSuperviseName varchar2(200) not null,--�����ҵ����
rpCoreName varchar2(200) not null,--������ҵ����
rpRelievepdDate char(8) not null,--�����Ѻ����
rpContent varchar2(500),--����ԭ��
rpNoticeDate char(8),--֪ͨ������
rpCreatedate date,--ͬ������ʱ��
rpUpdatedate date--ͬ����������ʱ��
);
create sequence zx_removepledgeId
increment by 1
start with 1;

--�����������
insert into zx_removepledge(rpId,rpNo,rpOperorg,rpPldegeeName,rpLoncpId,rpLoncpName,rpSuperviseName,rpCoreName,rpRelievepdDate,rpContent,rpNoticeDate,rpCreatedate,rpUpdatedate)values(zx_removepledgeId.nextval,'111222333','����','����Ա1','444555666','����','�ж�','����','20170222','����','20170223',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));

--�����Ѻ֪ͨ������
create table zx_removepledgedetail(
rdId number(19,2) primary key,
rdNo varchar2(20) not null,--֪ͨ����
rdCmdcode varchar2(60),--��Ʒ����
rdCmdname varchar2(200),--��Ʒ����
rdUnit varchar2(2),--������λ
rdStknum number(19,2),--�������
rdRlsmgnum number(19,2),--�����Ѻ����
rdWhcode varchar2(20),--���ڲֿ���
rdScflonno varchar2(20),--Scf�ſ����κ�
rdChattelpdno varchar2(20),--������Ѻ������ͬ���
rdNumber Decimal(8),--�ƿ�����
rdChassisno varchar2(200),--���ܺ�
rdCertificationNo varchar2(200),--�ϸ�֤���
rdCarPrice Decimal(19,2),--����
rdUserName varchar2(20),--�������������
rdUsercardid varchar2(20)--������������֤����
);
create sequence zx_removepledgedetailId
increment by 1
start with 1;

--�����������
insert into zx_removepledgedetail(rdId,rdNo,rdCmdcode,rdCmdname,rdUnit,rdStknum,rdRlsmgnum,rdWhcode,rdScflonno,rdChattelpdno,rdNumber,rdChassisno,rdCertificationNo,rdCarPrice,rdUserName,rdUsercardid)values(zx_removepledgedetailId.nextval,'111222333','222333','����Z','��','10','2','2222','3333','4444','0','12345678','87654321','100000','����Ա1','345555555555');
commit;

--�ƿ�֪ͨ��
create table zx_movenotice(
mnId number(19,2) primary key,  --�ƿ�֪ͨ��id����
mnNo varchar2(20) not null,--����֪ͨ���ͱ�֪ͨ����
mnOperorg varchar2(100),   --������
mnSuperviseName varchar2(200),  --�����ҵ����
mnMovedate char(8),  --�ƿ���������
mnLoncpName varchar2(200),  --�����ҵ����
mnNoticedate char(8),  --֪ͨ����
mnTotnum number(19,2),  --�ܼ�¼��
mnCreatedate date,  --ͬ��ʱ��
mnUpdatedate date  --��������ʱ��
);
create sequence zx_movenoticeId
increment by 1
start with 1;

--�����������
insert into zx_movenotice(mnId,mnNo,mnOperorg,mnSuperviseName,mnMovedate,mnLoncpName,mnNoticedate,mnTotnum,mnCreatedate,mnUpdatedate) values(zx_movenoticeId.nextval,'111222333','����','�ж�','20161212','����','20161212','10',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;
--�ƿ�֪ͨ�������
create table zx_movedetail(
mdId number(19,2) primary key, --�ƿ�֪ͨ�������id
mdNo varchar2(20) not null,  --�����ƿ�֪ͨ���
mdRemoveoutno varchar2(20) not null, --�Ƴ��ֿ���
mdRemoveinno varchar2(20) not null, --����ֿ���
mdWareno varchar2(50) not null, --��Ʒ����
mdMovenumber Decimal(8) not null, --�ƿ�����
mdChassisno varchar2(200) not null, --���ܺ�
mdCertificationNo varchar2(200) not null, --�ϸ�֤���
mdCarprice Decimal(19,2) not null --����
);
create sequence zx_movedetailId
increment by 1
start with 1;

--�����������
insert into zx_movedetail(mdId,mdNo,mdRemoveoutno,mdRemoveinno,mdWareno,mdMovenumber,mdChassisno,mdCertificationNo,mdCarprice)values(zx_movedetailId.nextval,'111222333','111222','222333','222222','10','234234','234235465','1000000.00');
COMMIT;

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
