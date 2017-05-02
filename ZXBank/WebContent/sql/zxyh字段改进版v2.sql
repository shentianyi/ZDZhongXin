--�ջ�֪ͨ
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
start with 1
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
start with 1

insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'165463514','1646164','�ջ�֪ͨ��1325','61561','�µ�',1,'��',1000000,3151351,'1684161','16131431','����','C615986168','316513',1065400,'R1651315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1423412','12323','�ջ�֪ͨ��112125','123123','�µ�',1,'��',1002000,3153351,'168414321','161342331','����','C61426168','3112313',1065400,'R161235');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'125123','12312','�ջ�֪ͨ��11235','612361','�µ�',1,'��',100300,312351,'1684161','12341431','����','C6153242168','31123513',1065400,'R165415');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'1615153514','123124124','�ջ�֪ͨ��1412425','6121561','�µ�',1,'��',10100,3151351,'16842341','123131431','����','C61523486168','3112313',1065400,'R16512315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'16123512514','4534534','�ջ�֪ͨ��13435','612361','�µ�',1,'��',100500,33351,'163161','16321431','����','C614546168','31123513',1065400,'R16123315');
insert into zx_notifydetail(ndId,ndNo,ndIdtplanno,ndIdtplannm,ndCmdcode,ndCmdname,ndCsnnum,ndUnit,ndCsnprc,ndReqcsnval,ndScflonno,ndMtgcntno,ndRemark,ndVin,ndHgzno,ndCarprice,ndLoancode) values(zx_notifydetailId.nextval,'162135514','567567','�ջ�֪ͨ��154525','615123161','�µ�',1,'��',100600,3121351,'16841','161341','����','C6155466168','3412513',1065400,'R165235');
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


---�ͻ���Ϣ��ѯ��

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


--�ֿ���Ϣ��ѯ
create table zx_warehouse(
Whid number(19,2) primary key,--�ֿ��ѯ������
custNo varchar2(50),--�ͻ���
whName varchar2(200),--�ֿ�����
whCode varchar2(20),--�ֿ����
whLevel number,--�ֿ⼶��
whOperorg varchar2(100),--������
loncpname varchar2(100),--�����ҵ����
lonentid varchar2(200),--�����ҵid --����
whAddress varchar2(100),--�ֿ��ַ
phone varchar2(12),--�绰
createDate date,--����ͬ��ʱ��
updateDate date--���ݸ���ʱ��
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(Whid,custNo,whName,whCode,whLevel,whOperorg,loncpname,lonentid,whAddress,phone,createDate,updateDate)values(zx_warehouseId.nextval,'1222223','�����ֿ�','1234154',1,'�ж�','��è','16546814','�Ϻ�','15138887776',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;







--������Ϣ��ѯ��
create table zx_financing(
fiId number(19,2) primary key,--���ʲ�ѯ������
fgLonentNo varchar2(30) ,--�����ҵId
fgLoncpName varchar2(100),--�����ҵ������
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



--���Э���ѯ��
	create table zx_agreement(
	agid number(19,2) primary key,--���Э��id
	hostno varchar2(50),--�ͻ���
	agloncpid varchar2(20),--�����ҵID
	lonnm varchar2(200),--�����ҵ����
	spvagtid varchar2(50),--ϵͳ���Э����
	spvagtno varchar2(50),--ֽ�ʼ��Э����
	agtstt varchar2(2),--Э��״̬��0,ʧЧ,1,��Ч��
	startdate char(8),--Э����ʼ��
	enddate char(8),--Э�鵽����
	isauth varchar2(2),--�Ƿ�ͨ����ҵ��
	ismv varchar2(2),--�Ƿ������ƿ�
	operorg varchar2(100),--������
	totnum Integer,--�ܼ�¼��
	agcreatedate date,--ͬ������ʱ��
	agupdatedate date--ͬ����������ʱ��
	);
	create sequence zx_agreementId
	increment by 1
	start with 1;
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','������ҵһ','56465181','654818461',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','������ҵһ','123','1231',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	COMMIT;

--�ͻ���ѯ��
create table zx_customer(
custid number(19,2) primary key,--�ͻ���ѯ������
custno varchar2(50),--�ͻ���
custorganizationcode varchar2(100) not null,--��֯��������
custname varchar2(100),--�ͻ�����
custcreatedate date,--����ͬ��ʱ��
custupdatedate date--����ͬ������ʱ��
);










--���������Ʒ��
create table zx_commodity(
cmid number(19,2) primary key, --���������Ʒ������id
cmgaid number(19,2),--��������id ���
cmcmdCode varchar2(60), --��Ʒ����
cmstkNum Decimal(19,2), --�������
cmistkPrc Decimal(19,2), --��ⵥ��
cmvin varchar2(40), --���ܺ�
cmhgzNo varchar2(50), --�ϸ�֤���
cmcarPrice Decimal(19,2), --����
cmloancode varchar2(40)--���ʱ��
);
create sequence zx_commodityId
increment by 1
start with 1;

insert into zx_commodity(cmid,cmgaid,cmcmdCode,cmstkNum,cmistkPrc,cmvin,cmhgzNo,cmcarPrice,cmloancode) values(zx_commodityId.nextval,1,'656518',1,46131,'C63583184','H54651356',65135,'3256146');
commit;


--��������
create table zx_gager(
gaid number(19,2) primary key,--�������id����
galonentno varchar2(30), --�����ҵid
galonentname varchar2(200),  --�����ҵ����
gaoprtName varchar2(40), --����������
gaorderNo varchar2(20), --������ˮ�� Ψһ 
gapcgrtntNo varchar2(100), --ֽ�ʵ�����ͬ���
gacmgrtcntNo varchar2(30), --������Ѻ������ͬ��� 
gawhCode varchar2(20),--�ֿ����
garemark varchar2(500),  --��ע
gaconfirmNo varchar2(30), --������ȷ������
gacount Number(19,2), --�ܼ�¼��
gastate Number(2), --�������״̬�������У��������룬�ȵȣ�
gacreatedate date --�������ʱ�䣨����ʱ�䣩
);
create sequence zx_gagerId
increment by 1
start with 1;

insert into zx_gager(gaid,galonentno,galonentname,gaoprtName,gaorderNo,gapcgrtntNo,gacmgrtcntNo,gawhCode,garemark,gaconfirmNo,gacount,gastate,gacreatedate) values(zx_gagerId.nextval,'56343','��ès','caicaicai','658631','635361','65361','A1','����','3531165',5,1,to_date ( '2017-12-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
commit;



--֪ͨ���ͱ�
create table zx_notice(
nid number(19,2) primary key,--֪ͨ���ͱ�����
ntcno varchar2(20),--֪ͨ����
ntctp number(10),--֪ͨ������1���ջ�	2���ƿ�   3�������Ѻ 	4�����������ʹ�ϵ���֪ͨ
ntbranchid varchar2��100����--����id ����
ntcdate timestamp,--֪ͨ�����ʱ��
nttotnum integer,--�ܼ�¼��
ntfailflag number(2) --0��ִʧ�ܣ�1����ʧ�ܣ�2�ѽ���
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


--֪ͨ������ϸ
create table zx_push_notice_detail(
pndid number(19,2) primary key,--֪ͨ������ϸid
nid number(19,2),--֪ͨ���ͱ�id  ���
pndEcifcode varchar2(20),--��������ECIF�ͻ���
pndOperorg varchar2(100),--������
pndVin varchar2(40),--���ܺ�
pndLoancode varchar2(50)--���ʱ��
);
create sequence zx_push_notice_detailId
increment by 1
start with 1;

insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,44,'123123','�Ϻ�֧��');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,44,'789789','�Ϻ�֧��');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,45,'1123','�Ϻ�֧��');
insert into zx_push_notice_detail(pndid,nid,pndecifcode,pndOperorg) values(zx_push_notice_detailId.nextval,45,'456789','�Ϻ�֧��');





--���Э���ѯ��
	create table zx_agreement(
	agid number(19,2) primary key,--���Э��id
	hostno varchar2(50),--�ͻ���
	agloncpid varchar2(20),--�����ҵID
	lonnm varchar2(200),--�����ҵ����
	spvagtid varchar2(50),--ϵͳ���Э����
	spvagtno varchar2(50),--ֽ�ʼ��Э����
	agtstt varchar2(2),--Э��״̬��0,ʧЧ,1,��Ч��
	startdate char(8),--Э����ʼ��
	enddate char(8),--Э�鵽����
	isauth varchar2(2),--�Ƿ�ͨ����ҵ��
	ismv varchar2(2),--�Ƿ������ƿ�
	operorg varchar2(100),--������
	totnum Integer,--�ܼ�¼��
	agcreatedate date,--ͬ������ʱ��
	agupdatedate date--ͬ����������ʱ��
	);
	create sequence zx_agreementId
	increment by 1
	start with 1;
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','������ҵһ','56465181','654818461',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','������ҵһ','123','1231',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
	COMMIT;