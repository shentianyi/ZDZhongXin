package com.zd.csms.constants;

/**
 * 常用缓存块名枚举
 * 用于约定缓存块命名，避免因重复使用命名或错误命名导致的问题
 * */
public enum CacheStorageNameConstant {

	/**
	 * 代码示例
	 * */
	DEMO("demo"),
	
	//服务于按逻辑缓存的缓存名集合
	/**
	 * 校验
	 * */
	VALIDATE("validate"),
	/**
	 * 菜单
	 * */
	MENU("menu"),
	
	//服务于按表缓存的缓存名集合
	/**
	 * 资源
	 * */
	RESOURCE("resource"),
	/**
	 * 角色
	 * */
	ROLE("role"),
	/**
	 * 账户
	 * */
	USER("user"),
	/**
	 * 账户客户关系
	 * */
	USERCLIENT("userClient"),
	/**
	 * 操作日志
	 */
	LOGSYSTEM("LogSystem"),
	/**
	 * 上传附件
	 * */
	
	UPLOADFILE("uploadfile"),
	/**
	 * 监管机构
	 */
	ORG("org"),
	/**
	 * 监管员
	 */
	SUPERVISOR("supervisor"),
	/**
	 * 信息采集员
	 */
	INFORMATIONCOLLECTOR("InformationCollector"),
	/**
	 * 监管员轮换
	 */
	SUPERVISORYROTATE("supervisoryRotate"),
	/**
	 * 评估师
	 */
	APPRAISER("appraiser"),
	/**
	 * 评估费收取
	 */
	ASSESSMENTCOST("assessmentcost"),
	/**
	 * 评估业务组
	 */
	GROUP("group"),
	/**
	 * 车辆信息
	 */
	VEHICLE("vehicle"),
	/**
	 * 品牌信息
	 */
	VEHICLE_BRAND("vehicle_brand"),
	/**
	 * 车型信息
	 */
	VEHICLE_TYPE("vehicle_type"),
	/**
	 * 车辆历史
	 */
	VEHICLE_HISTORY("vehicle_history"),
	/**
	 * 车辆隐藏数据
	 */
	VEHICLEYC("t_scardetail"),
	/**
	 * 抵押车辆信息
	 */
	MORTGAGEVEHICLE("mortgageVehicle"),
	/**
	 * 抵押车辆参数
	 */
	PARAMS("params"),
	/**
	 * 抵押过户信息
	 */
	TRANSFER("transfer"),
	/**
	 * 解押过户信息
	 */
	RELEASE_TRANSFER("release_transfer"),
	/**
	 * 经销商基本信息
	 * */
	DISTRIB("distrib"),
	
	/**
	 * 经销商银行的基本信息
	 * */
	FINANCECLIENT("financeclient"),
	/**
	 * 经销商银行的财务信息
	 * */
	FINANCE("finance"),
	/**
	 * 股东信息
	 * */
	SHAREHOLDER("shareholder"),
	/**
	 * 额度信息
	 * */
	CREDIT("credit"),
	/**
	 * 平台基本信息
	 * */
	PLATFORM("platform"),
	/**
	 * 抵押物质接收人
	 * */
	RECIPIENT("recipient"),
	/**
	 * 银行
	 * */
	BANK("bank"),
	/**
	 * 银行与监管机构关系
	 * */
	BANK_SUPERVISORY("banksupervisory"),
	/**
	 * 字典表
	 * */
	DICTIONARY("dictionary"),
	/**
	 * 质押车辆抽查
	 * */
	CHECKCAR("t_check_car"),
	/**
	 * 质押车辆抽查日志
	 * */
	CHECKCARLOG("t_check_car_log"),
	/**
	 * 质押车辆抽查日志
	 * */
	CHECKCARPARAMS("t_check_car_params"),
	
	/**
	 * 经销商销售信息
	 * */
	SELLINFO("sellinfo"),
	/**
	 * 经销商参数
	 * */
	SETTING("setting"),
	
	/**
	 * 银行业务日志表
	 * */
	BANKLOG("banklog"),
	
	/**
	* 银行业务表
	* */
	BANKAUDIT("bankaudit"),
	/**
	 * 经销商额度明细
	 * */
	CREDITDETAIL("creditdetail"),
	/**
	 * 经销商授信资质
	 * */
	CREDIT_QUALITY("credit_quality"),
	/**
	 * 经销商授信资质明细
	 * */
	CREDIT_QUALITY_DETAIL("credit_quality_detail"),
	
	/**
	 * 经销商额度台账
	 * */
	ACCOUNT_CREDIT("account_credit"),
	
	/**
	 * 贷款还款
	 * */
	LOANREPAY("loanrepay"),
	/**
	 * 一一对应
	 */
	LOANCORRESPONDING("loan_corresponding"),
	/**
	 * 一一对应历史
	 */
	LOANCORRESPOND("loan_correspond"),
	/**
	 * 置换车辆
	 */
	REPLACEMENTVEHICLE("replacement_vehicle"),
	/**
	 * 换入车辆图片
	 */
	REPLACEMENTVEHICLETRANSFER("replacement_vehicle_transfer"),
	/**
	 * 银行参数设置
	 * */
	BANK_SETTING("bank_setting"),
	/**
	 * 地区
	 * */
	AREA("area"),
	/**
	 * 经销商申请额度
	 * */
	DISTRIB_APP_CREDIT("distrib_app_credit"),
	/**
	 * 共享库存车辆
	 */
	SHARE_VEHICLE("share_vehicle"),
	
	/**
	 * 共享车辆参数
	 */
	SHAREPARAMS("share_params"),
	/**
	 * 厂商参数
	 */
	CARFIRM("carfirm"),
	
	/**
	 * 厂商品牌参数
	 */
	CARFIRMBRAND("carfirmbrand"),
	/**
	 * 车辆打包
	 */
	SHARE_PACK("share_pack"),
	/**
	 * 车辆打包关联
	 */
	SHARE_PACK_RELATION("share_pack_relation"),
	/**
	 * 共享车辆参数
	 */
	SHAREPARAMSLOG("share_params_log"),
	/**
	 * 关注车辆参数
	 */
	ATTENTION("attention"),
	
	/**
	 * 在线车源和经销商关系
	 */
	SHARE_DISTRIB("t_financing_share_distrib"),
	
	/**
	 * 银行和经销商关系
	 */
	BANK_DISTRIB("t_bank_distrib")
	
	;
	
	
	private String code;
	private CacheStorageNameConstant(String code){
		this.code = code;
	}
	
	public String getCode(){
		return code;
	}
}
