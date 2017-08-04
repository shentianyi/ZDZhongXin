-- Create table
create table t_payment_info
(
  id                         NUMBER(19) not null,
  
  paymentId             	 NUMBER(19) not null,
  status           			 NUMBER(19),
  staffNo             		 VARCHAR2(128),
  staffName                  VARCHAR2(128),
  cardNo            		 VARCHAR2(128),
  
  dealerNames                VARCHAR2(128),
  provinceId            	 NUMBER(19),
  provinceName               VARCHAR2(256),
  cityId                	 NUMBER(19),
  cityName                	 VARCHAR2(256),
  
  cityType                	 NUMBER(19),
  isFormal                	 NUMBER(19),
  isFar                	 	 NUMBER(19),
  stationedPro               NUMBER(19),
  actualAttendance           NUMBER(10,2),
  
  shouldAttendance           NUMBER(10,2),
  isFullTime                 NUMBER(19),
  entryDate                	 DATE,
  companyAge                 NUMBER(10,2),
  overtimeDays               NUMBER(10,2),
  
-- 以下为核算项 18  

  basicSalary                NUMBER(10,2),
  basePay                	 NUMBER(10,2),
  
  companyAgePay              NUMBER(10,2),
  mealSubsidy                NUMBER(10,2),
  phoneSubsidy               NUMBER(10,2),
  trafficSubsidy             NUMBER(10,2),
  houseSubsidy               NUMBER(10,2),
  
  manySubsidy                NUMBER(10,2),
  farSubsidy                 NUMBER(10,2),
  fullTimeSubsidy            NUMBER(10,2),
  settleCost                 NUMBER(10,2),
  replaceCost                NUMBER(10,2),
  
  overtimeCost               NUMBER(10,2),
  
  elseSubsidy                NUMBER(10,2),
  subsidyOne                 NUMBER(10,2),
  subsidyTwo                 NUMBER(10,2),
  deductOne                	 NUMBER(10,2),
  deductTwo                	 NUMBER(10,2),
  
-- 以下为审批特有 6
  annuity                	 NUMBER(10,2),
  medical                	 NUMBER(10,2),
  jobInjury                	 NUMBER(10,2),
  unemployment               NUMBER(10,2),
  bearing                	 NUMBER(10,2),
  subsidyMedical             NUMBER(10,2),
  
-- 以下为合计项 10
  
  shouldMoney                NUMBER(10,2),
  revenue                	 NUMBER(10,2),
  subsidyOneTotal            NUMBER(10,2),
  subsidyTwoTotal            NUMBER(10,2),
  deductOneTotal             NUMBER(10,2),
  
  deductTwoTotal             NUMBER(10,2),
  praticleMoney              NUMBER(10,2),
  remark  		 			 VARCHAR2(256),
  bankCardNo  		 		 VARCHAR2(128),
  openBankName  		 	 VARCHAR2(256)
)
-- Add comments to the table 
comment on table t_payment_info
  is '薪酬信息表';

-- Add comments to the columns 
comment on column t_payment_info.id
  is '主键ID';
 
comment on column t_payment_info.paymentId
  is '薪酬表主键';
comment on column t_payment_info.status
  is '审批状态 (不通过:0,通过:1)';
comment on column t_payment_info.staffNo
  is '员工工号';
comment on column t_payment_info.staffName
  is '员工姓名';
comment on column t_payment_info.cardNos
  is '身份证号' ;
  
comment on column t_payment_info.dealerNames
  is '店名多个用,隔开' ;
comment on column t_payment_info.provinceId
  is '省份Id' ;
comment on column t_payment_info.provinceName
  is '省份名称' ;
comment on column t_payment_info.cityId
  is '城市Id' ;
comment on column t_payment_info.cityName
  is '城市名称' ;
  
comment on column t_payment_info.cityType
  is '城市类型' ;
comment on column t_payment_info.isFormal
  is '是否转正(0:未转正 1::转正)' ;
comment on column t_payment_info.isFar
  is '是否远疆(0:否 1:是)' ;
comment on column t_payment_info.stationedPro
  is '驻派属性' ;
comment on column t_payment_info.actualAttendance
  is '实际出勤天数' ;
  
comment on column t_payment_info.shouldAttendance
  is '应出勤天数' ;
comment on column t_payment_info.isFullTime
  is '当月是否全勤(0：否  1：是)' ;
comment on column t_payment_info.entryDate
  is '入职日期' ;
comment on column t_payment_info.companyAge
  is '司龄' ;
comment on column t_payment_info.overtimeDays
  is '加班天数' ;
  
-- 以下为核算项 18
  
comment on column t_payment_info.basicSalary
  is '基本工资(固定)' ;
comment on column t_payment_info.basePay
  is '基本工资' ;
comment on column t_payment_info.companyAgePay
  is '司龄工资' ;
  
comment on column t_payment_info.mealSubsidy
  is '餐补' ;
comment on column t_payment_info.phoneSubsidy
  is '话补' ;
comment on column t_payment_info.trafficSubsidy
  is '交通补' ;
comment on column t_payment_info.houseSubsidy
  is '房补' ;
comment on column t_payment_info.manySubsidy
  is '多店多行补助' ;
  
comment on column t_payment_info.farSubsidy
  is '远疆补助' ;
comment on column t_payment_info.fullTimeSubsidy
  is '全勤奖' ;
comment on column t_payment_info.settleCost
  is '安家费' ;
comment on column t_payment_info.replaceCost
  is '替岗费' ;
comment on column t_payment_info.overtimeCost
  is '加班费' ;
  
comment on column t_payment_info.elseSubsidy
  is '异常补助' ;
comment on column t_payment_info.subsidyOne
  is '核算补款一' ;
comment on column t_payment_info.subsidyTwo
  is '核算补款二' ;
comment on column t_payment_info.deductOne
  is '核算扣款一' ;
comment on column t_payment_info.deductTwo
  is '核算扣款二' ;
  
-- 以下为合计项 10
  
comment on column t_payment_info.shouldMoney
  is '应发金额' ;
comment on column t_payment_info.revenue
  is '个人所得税' ;
comment on column t_payment_info.subsidyOneTotal
  is '合计补款一' ;
comment on column t_payment_info.subsidyTwoTotal
  is '合计补款二' ;
comment on column t_payment_info.deductOneTotal
  is '合计扣款一' ;
 
comment on column t_payment_info.deductTwoTotal
  is '合计扣款二' ;
comment on column t_payment_info.praticleMoney
  is '实发金额' ;
comment on column t_payment_info.remark
  is '备注' ;
comment on column t_payment_info.bankCardNo
  is '银行卡号' ;
comment on column t_payment_info.openBankName
  is '开户行' ;
 
comment on column t_payment_info.annuity
  is '养老金' ;
comment on column t_payment_info.medical
  is '医疗' ;
comment on column t_payment_info.jobInjury
  is '工伤' ;
comment on column t_payment_info.unemployment
  is '失业' ;
comment on column t_payment_info.bearing
  is '生育' ;
comment on column t_payment_info.subsidyMedical
  is '补充医疗' ;
  
  
-- Create sequence 
create sequence SEQ_T_PAYMENTINFO
minvalue 1
maxvalue 999999999999999999999999999
start with 123
increment by 1
cache 20;


insert into t_seqencedict
  (id,
   dbname,
   tablename,
   seqname,
   seqinitvalue,
   addseqdate,
   createtime,
   addsequser,
   memo)
values
  ((select max(id) + 1 from t_seqencedict),
   'zddb',
   't_payment_info',
   'SEQ_T_PAYMENTINFO',
   1,
   sysdate,
   sysdate,
   'wdc',
   '手动添加');