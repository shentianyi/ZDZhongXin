package com.zd.csms.message.contant;

public enum MessageTypeContant {

	BILLING7NOCAR(1,"开票7个工作日未到车"),
	BILLING15NOCAR(2,"开票15个工作日未到车"),
	BILLING30NOFULL(3,"开票30天汇票未押满"),
	EXPIRE7DAY(4,"汇票到期前7天提醒"),
	EXPIRETHEDAY(5,"汇票到期当天提醒"),
	EXPIRENOTCLEAR(6,"汇票到期未清票"),
	NOSTOCKNODRAFT(7,"0库存0汇票"),
	CONTINUITY3OUT(8,"连续三天出库5台以上"),
	MOVE30DAY(9,"移动车辆超过30天未赎车或移回"),
	NOWORK3DAY(10,"连续3天无业务"),
	CARSTORAGE(11,"监管物入库"),
	CAROUT(12,"监管物出库"),
	CARMOVE(13,"监管物移动"),
	BENDUEDATE(14,"本库日查库"),
	TWODUEDATE(15,"二网日查库"),
	REPAIRCOST(16,"设备维修申请"),
	POSTAGEREQUISITION(17,"设备邮寄申请"),
	LASTBUSINESS(18,"最后一笔业务"),
	BINDDEALER(19,"经销商/金融机构绑定"),
	UNBINDDEALER(20,"经销商/金融机构拆分"),
	BUSINESSTRANSFER(21,"业务流转单"),
	DEALEREXIT(22,"经销商撤店"),
	DEALERREFUND(23,"经销商退费"),
	EXPENSECHANGE(24,"监管费变更"),
	INVOICE(25,"开票申请"),
	PROJECTCIRCULATION(26,"项目进驻"),
	MODECHANGE(27,"模式变更"),
	PAYMENT(28,"监管费标准单"),
	HANDOVERLOG(29,"监管员交接申请"),
	REPOSITORY(30,"储备库培训信息"),
	AGREEMENTEXPIRES30(31,"协议到期30天"),
	DATAMAILPOSTAGEREQUISITION(32,"资料邮寄费用申请"),
	COMPLAINTINFO(33,"投诉记录信息"),
	LEAVEAPPLY(34,"监管员请假申请"),
	EXTRAWORKAPPLY(35,"监管员加班申请"),
	RESIGNAPPLY(36,"监管员辞职申请"),
	VIDEOPLANINFO(37,"视频检查计划信息"),
	SUBMITVIDEOPLANREMIND(38,"提交视频检查计划提醒"),
	SUBMITINSPECTIONPLANREMIND(39,"提交巡检计划提醒"),
	
	//-------------------------监管员管理部信息提醒-------------------------
	HANDOVERPLAN(41,"每月25日提交轮岗计划提醒"),
	SUPERVISIONFEE(42,"每月三日提交前一月监管员管理费及社保费用"),
	SUPERVISORATTENDANCE(43,"每月一日提交监管员考勤提醒"),
	NOEXECUTEHANDOVERPLAN(44,"未按轮岗计划执行提醒"),
	SUPERVISORSIGNINABNORMAL(45,"监管员系统签到异常信息"),
	SUPERVISORSIGNOUTABNORMAL(46,"监管员系统签退异常信息"),
	PROJECTCIRCUNOSUPERVISOR(47,"项目进驻流转单发出后三天未录入人员信息提醒"),
	REPOSITORYNOTRAIN(48,"监管员面试完成一日未安排培训提醒"),
	REPFIFTEENDAYSNOPOST(49,"监管员进入储备库15天未安排上岗提醒"),
	RESIGNNOHANDOVERLOG(50,"监管员辞职离岗时间前十天未提交人员信息"),
	SUPWORKFIVEMONTH(51,"监管员在一家店连续工作五个月提醒"),
	SUPERVISORTHREEDAYSIGNABNORMAL(52,"监管员连续三天未正常出勤预警"),
	PROJECTCIRCUNOSUPERVISORWARNING(53,"项目进驻流转单发出后五天未匹配人员信息预警"),
	POSTNOTRAIN(54,"监管员上岗未培训考核预警"),
	REPOSITORYNOTRAINWARNING(55,"监管员面试完成三日未安排培训预警"),
	RESIGNNOHANDOVERLOGWARNING(56,"监管员辞职离岗时间前五天未提交人员信息预警"),
	SUPWORKSIXMONTHWARNING(57,"监管员在一家店工作六个月预警"),
	SUPERVISORASSESSMENTNOTPASS(58,"监管员培训考核不通过"),
	//-------------监管员信息提醒----------------------------
	SUPSERVISORBIRTHDAY(61,"监管员生日提醒"),
	SUPSERVISORBIRTHDAYYEARANDDAY(62,"监管员入职满一年提醒"),
	SUPSERVISREPAIRCOST(63,"设备维修申请单提醒"),
	SUPSERVISCOSTMAIL(64,"邮寄费用申请提醒"),
	SUPSERVISOVERTIMEAPPLICATION(65,"加班申请提醒"),
	SUPSERVISOUTSTOCK(66,"释放申请书提醒"),
	SUPSERVIMOVETSTOCK(67,"移动申请书提醒"),
	//-------------风控部--------------------------------------
    INSPECTIONSU(68,"每月20日提交巡店检查计划提醒"),
	INSPECTIONVIDEO(69,"每月20日提交视频检查计划提醒"),
	UNINSPECTIONVID(70,"未按风控巡检计划执行提醒"),
	UNINSPECTI(71,"巡店报告上传3日未上传新报告提醒"),
	COINSPECTI(72,"巡店报告提醒"),
	
	//-------------------------业务部消息提醒--------------------------
	BANKMOVESTORAGEREMIND(81,"银行移动审批提醒"),
	MSGBILLNOCARREMIND(82,"开票10个工作日未到车提醒"),
	MSGBILLNOFULLREMIND(83,"开票30天汇票未押满"),
	MSGBILLNODRAFTREMIND(84,"汇票到期前7天未清票提醒"),
	BANKOUTSTORAGEREMIND(85,"银行释放审批提醒"),
	MSGZEROSKUZERODRAFTREMIND(86,"零库存零汇票提醒"),
	MSGLASTBUSINESSREMIND(87,"最后一笔业务提醒"),
	MSGTHREEDAYNOBUSINESSREMIND(88,"连续三天无业务发生提醒"),
	MSGMOVEEXCEEDTWENTYFIVENOPROCESSREMIND(89,"移动车辆超过25天未处理提醒"),
	MSGMOVECARTEXCEEDKURTWENTYEMIND(90,"移动车辆超过总库存20%提醒"),
	MSGEXCEPTIONCAREXCEEDSKUFIFTEENREMIND(91,"异常车辆超过总库存15%提醒"),
	
	//-------------------------业务部消息预警--------------------------
	BILLNOCARFIFTEENWARING(92,"开票15个工作日未到车预警"),
	BILLFORTYFIVENOFULLWARING(93,"开票45天汇票未押满预警"),
	BILLNODRAFTNOWDATEWARING(94,"汇票到期当天未清票预警"),
	ZEROSKUZERODRAFTWARING(95,"零库存零汇票延续7天预警"),
	FIVEDAYNOBUSINESSWARING(96,"连续五天无业务发生预警"),
	MOVEEXCEEDTHIRTYNOPROCESSWARING(97,"移动车辆超过30天未处理预警"),
	EXCEPTIONCAREXCEEDSKUTWENTYWARING(98,"异常车辆超过总库存20%预警"),
	BANKOUTSTORAGEWARING(99,"银行释放审批一天未出库预警"),
	BANKMOVESTORAGEWARING(100,"银行移动审批一天未出库预警"),
	
	//市场部消息提醒
	SEVENDAYSNONREGULATORYFEE(101,"进驻7天未交监管费提醒"),
	SUPERVISIONFEE30DAYSAGO(102,"监管费到期前30天进行提醒"),
	THREEDAYSAFTERNOBILLING(103,"监管费收取3天后未开票提醒"),
	PROJECTINAGREEMENTNOTRECOVERED(104,"项目进驻当天协议未收回提醒"),
	SUPERVISIONAGREEMENT30DAYSAGO(105,"监管协议到期前30天提醒"),
	AGREEMENTSIGNING10DAYNOTRECOVERED(106,"监管协议签署10天未收回提醒"),
	AGREEMENTSIGNING15DAYNOTRANSFER(107,"协议签署超15天未发进店流转单提醒"),
	BUSINESSIN3DAYSAGONOTPERSONNEL(108,"业务进驻流转单进驻前3天未匹配人员提醒"),
	NO5CITICBANKSUPERVISIONFEE(109,"1,4,7,10月5日中信银行监管费明细提醒"),
	PROJECTIN(110,"项目进驻流转单"),
	BUSINESSIN(111,"业务进驻流转单"),
	PROJECTOUT(112,"项目撤出流转单"),
	PROJECTBINDING(113,"项目绑定流转单"),
	PROJECTUNBUNDLING(114,"项目解绑流转单"),
	
	//市场部信息预警
	FIFTEENDAYSNONREGULATORYFEE(115,"进驻15天未交监管费预警"),
	SUPERVISIONFEE10DAYSAGO(116,"监管费到期前10天进行预警"),
	TENDAYSAFTERNOBILLING(117,"监管费收取后7天未开票预警"),
	EXPIRENOFEE(118,"监管费到期日未收费预警"),
	FIFTEENDAYSAGREEMENTNOTRECOVERED(119,"项目进驻15天协议未收回预警"),
	AGREEMENT15DAYSAGO(120,"监管协议到期前15天预警"),
	AGREEMENTNOSIGNING(121,"协议到期未续签预警"),
	BUSINESSINONEDAYSAGONOTPERSONNEL(122,"业务进驻流转单进驻前1天未匹配人员预警"),
	
	
	
	APPROVALNOTICE(123,"审批公告提醒"),
	
	//-------------------------新增提醒--------------------------
	PAYMENTINFO(124,"薪酬信息"),
	EXCEPTIONDATE(125,"异常事件/异常数据"),
	VIDEO(126,"视频检查计划审批"),
	VIDEOCHECKREPORT(127,"视频检查报告"),
	PATROLPLAN(128,"巡检计划审批通过提醒"),
	PATROLREPORT(129,"巡检报告"),
	DAILYATTENDANCEDETAIL(130,"每日考勤详情");
	
	
	
	private int code;
	private String name;
	private MessageTypeContant(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
}
