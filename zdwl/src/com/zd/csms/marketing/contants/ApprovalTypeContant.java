package com.zd.csms.marketing.contants;

public enum ApprovalTypeContant {
	
	/**
	 * 项目进驻流转单
	 */
	MARKETPROJECTCIRCULATION(1,"项目进驻流转单"),
	/**
	 * 操作模式变更流转单
	 */
	MODECHANGE(2,"操作模式变更流转单"),
	/**
	 * 监管费用变更流转单
	 */
	EXPENSECHANGE(3,"监管费用变更流转单"),
	/**
	 * 经销商/金融机构绑定信息
	 */
	BINDDEALER(4,"经销商/金融机构绑定信息"),
	/**
	 * 设备邮寄费用申请单
	 */
	POSTAGEREQUISITION(5,"设备邮寄费用申请单"),
	/**
	 * 业务流转单
	 */
	BUSINESSRANSFER(6,"业务流转单"),
	
	/**
	 * 经销商/金融机构解绑信息
	 */
	UNBINDDEALER(7,"经销商/金融机构解绑信息"),
	
	/**
	 * 经销商撤店信息流转
	 */
	DEALEREXIT(8,"经销商撤店信息流转"),
	
	/**
	 * 经销商退费流转单
	 */
	DEALERREFUND(9,"经销商退费流转单"),
	
	/**
	 * 开票申请流转单
	 */
	INVOICE(10,"开票申请流转单"),
	
	/**
	 * 缴费记录单
	 */
	PAYMENT(11,"缴费记录单"),
	/**
	 * 日查库(包含本库日查库和二网日查库)
	 */
	BENDUEDATE(12,"日查库"),
	
	/**
	 * 投诉表
	 */
	COMPLAINT(13,"投诉表"),
	/**
	 * 二网日查库
	 */
	//TWODUEDATE(14,"二网日查库"),
	/**
	 * 查库频次申请
	 */
	CHECKSTOCK(15,"查库频次申请"),
	/**
	 * 设备维修申请单
	 */
	REPAIRCOST(16,"设备维修申请单"),
	/**
	 * 监管员交接记录
	 */
	HANDOVERLOG(17,"监管员交接记录"),
	/**
	 * 车辆移动
	 */
	CARMOVE(18,"车辆移动"),
	/**
	 * 车辆出库
	 */
	CAROUT(19,"车辆出库"),
	/**
	 * 车辆入库
	 */
	CARSTORAGE(20,"车辆入库"),
	/**
	 * 轮岗计划
	 */
	HANDOVERPLAN(21,"轮岗计划"),
	
	/**
	 * 通知公告
	 */
	GONGGAO(22,"通知公告"),
	
	/**
	 * 制度
	 */
	ZHIDU(23,"制度"),
	
	/**
	 * 资料邮寄费用申请单
	 */
	DATAMAILPOSTAGEREQUISITION(24,"资料邮寄费用申请单"),
	/**
	 * 监管员请假申请
	 */
	SUPERVISORYLEAVEAPPLY(25,"监管员请假申请"),
	/**
	 * 监管员加班申请
	 */
	SUPERVISORYEXTRAWORKAPPLY(26,"监管员加班申请"),
	/**
	 * 监管员辞职申请
	 */
	SUPERVISORYRESIGNAPPLY(27,"监管员辞职申请"),
	/**
	 * 视频检查计划审批
	 */
	VIDEOLANAPPROVE(28,"视频计划审批"),
	/**
	 * 巡检计划审批
	 */
	INSPECTIONPLANAPPROVE(29,"巡检计划审批");
	private int code;
	private String name;
	public int getCode() {
		return code;
	}
	public String getName() {
		return name;
	}
	private ApprovalTypeContant(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
}
