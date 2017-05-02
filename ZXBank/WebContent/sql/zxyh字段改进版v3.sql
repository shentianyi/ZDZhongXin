--�ͻ���ѯ��
create table zx_customer(
custid number(19,2) primary key,--�ͻ���ѯ������
custno varchar2(50),--�ͻ���
custorganizationcode varchar2(100) not null,--��֯��������
custname varchar2(100),--�ͻ�����
custcreatedate date,--���ݴ���ʱ��
custupdatedate date--���ݸ���ʱ��
);





--�ֿ���Ϣ��ѯ
create table zx_warehouse(
whid number(19,2) primary key,--�ֿ��ѯ������
custNo varchar2(50),--�ͻ���
whlonentnm varchar2(100),--�����ҵ����
whName varchar2(200),--�ֿ�����
whCode varchar2(20),--�ֿ���� 		���ж˲ֿ���루���Э���ȡ����Ҫȥ�أ�
whLevel number,--�ֿ⼶��1:һ���ֿ⣬2:�����ֿ⣩
whOperorg varchar2(100),--������
whAddress varchar2(100),--�ֿ��ַ
phone varchar2(12),--�绰
lonentid varchar2(200),--�����ҵid
whdistance varchar2(30),--������ֿܲ���뱾�⹫����
whContacts varchar2(60),--��ֿܲ���ϵ��
createDate date,--���ݴ���ʱ��
updateDate date --���ݸ���ʱ��
);
create sequence zx_warehouseId
increment by 1
start with 1;
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'1222223','��è�̳�','�����ֿ�','1234154',1,'�ж�֧��','�Ϻ��ֶ�','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'12341251','��è�̳�','�����ֿ�','1234154',1,'�ж�֧��','�Ϻ��ֶ�','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'1123412','��è�̳�','�����ֿ�','1234154',1,'�ж�֧��','�Ϻ��ֶ�','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_warehouse(Whid,custNo,whlonentnm,whName,whCode,whLevel,whOperorg,whAddress,phone,lonentid,createDate,updateDate)values(zx_warehouseId.nextval,'12234523','��è�̳�','�����ֿ�','1234154',1,'�ж�֧��','�Ϻ��ֶ�','13648756298','98793468',to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;



--���Э���ѯ��
create table zx_agreement(
agid number(19,2) primary key,--���Э��id
agcustno varchar2(50),--�ͻ���
agloncpid varchar2(20),--�����ҵID �����н����ҵECIF��ţ�
agloncpname varchar2(200),--�����ҵ����
agprotocolno varchar2(50),--ϵͳ���Э����
agprotocolcode varchar2(50),--ֽ�ʼ��Э����
agstate number(2),--Э��״̬��01-��Ч,02-ʧЧ��
agstdate char(8),--Э����ʼ��
agenddate char(8),--Э�鵽����
agisonline number(2),--�Ƿ�ͨ����ҵ��00-δ��ͨ,01-��ͨ��
agismove number(2),--�Ƿ������ƿ⣨00-������,01-����
agoperorg varchar2(100),--������
agtotnum number(19,2),--�ܼ�¼��
agcreatedate date,--���ݴ���ʱ��
agupdatedate date--���ݸ���ʱ��
);
create sequence zx_agreementId
increment by 1
start with 1;
insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'145861813648','646461844','������ҵһ','56465181','654818461',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
insert into zx_agreement(agid, agcustno,agloncpid,agloncpname,agprotocolno,agprotocolcode,agstate,agstdate,agenddate,agisonline,agismove,agoperorg,agtotnum,agcreatedate,agupdatedate)values(zx_agreementId.nextval,'12312','12312','������ҵһ','123','1231',1,'20170312','20170330',1,1,'����',50,to_date ( '2017-2-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ),to_date ( '2017-3-20 18:31:34' , 'YYYY-MM-DD HH24:MI:SS' ));
COMMIT;


--���Э���ѯ��   ����
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
  
  insert with 1;
  insert into zx_agreement(agid,hostno,agloncpid,lonnm,spvagtid,spvagtno,agtstt,startdate,enddate,isauth,ismv,operorg,totnum,agcreatedate,agupdatedate ) valuse()

