package com.zd.csms.supervisory.querybean;

import java.util.Date;

import com.zd.csms.supervisory.model.SuperviseImportVO;

public class BankApproveQueryBean{
	private int id;
	private int sid;//车id
	private Date createtime;//创建时间
	private Date approvetime;//审批时间
	private int type;//车辆状态:1导入2在库3出库4移动
	
	private Date certificate_date;//合格证发证日期
	private String certificate_num;//合格证号
	private String car_model;//车辆型号
	private String car_structure;//车身结构
	private String displacement;//排量
	private String color;//颜色
	private String engine_num;//发动机号
	private String vin;//车架号
	private String key_num;//钥匙号
	private String money;//金额
	private String des;//备注
	private String draft_num;//汇票号码
	private String key_amount;//钥匙数量
	private int state;//车辆状态:1导入2在库3出库4移动5:私自售卖，6：私自移动
	private String price;//单价
	private String payment_amount;//回款金额
	private String two_name;//二网名称
	private Date certificate_intime;//合格证接收时间
	private Date storagetime;//入库时间
	private Date movetime;//移动时间
	private Date outtime;//释放时间
	private int brandid;//品牌
	private int apply;//申请状态 1:审批中 0:未在审批状态
	private Date importtime;//导入明细时间
	private String bond;//保证金
	
	private String dealerName;
	private String bankFullName;
	private String brandName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getApprovetime() {
		return approvetime;
	}
	public void setApprovetime(Date approvetime) {
		this.approvetime = approvetime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getCertificate_date() {
		return certificate_date;
	}
	public void setCertificate_date(Date certificate_date) {
		this.certificate_date = certificate_date;
	}
	public String getCertificate_num() {
		return certificate_num;
	}
	public void setCertificate_num(String certificate_num) {
		this.certificate_num = certificate_num;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getCar_structure() {
		return car_structure;
	}
	public void setCar_structure(String car_structure) {
		this.car_structure = car_structure;
	}
	public String getDisplacement() {
		return displacement;
	}
	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getEngine_num() {
		return engine_num;
	}
	public void setEngine_num(String engine_num) {
		this.engine_num = engine_num;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getKey_num() {
		return key_num;
	}
	public void setKey_num(String key_num) {
		this.key_num = key_num;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getDraft_num() {
		return draft_num;
	}
	public void setDraft_num(String draft_num) {
		this.draft_num = draft_num;
	}
	public String getKey_amount() {
		return key_amount;
	}
	public void setKey_amount(String key_amount) {
		this.key_amount = key_amount;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPayment_amount() {
		return payment_amount;
	}
	public void setPayment_amount(String payment_amount) {
		this.payment_amount = payment_amount;
	}
	public String getTwo_name() {
		return two_name;
	}
	public void setTwo_name(String two_name) {
		this.two_name = two_name;
	}
	public Date getCertificate_intime() {
		return certificate_intime;
	}
	public void setCertificate_intime(Date certificate_intime) {
		this.certificate_intime = certificate_intime;
	}
	public Date getStoragetime() {
		return storagetime;
	}
	public void setStoragetime(Date storagetime) {
		this.storagetime = storagetime;
	}
	public Date getMovetime() {
		return movetime;
	}
	public void setMovetime(Date movetime) {
		this.movetime = movetime;
	}
	public Date getOuttime() {
		return outtime;
	}
	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}
	public int getBrandid() {
		return brandid;
	}
	public void setBrandid(int brandid) {
		this.brandid = brandid;
	}
	public int getApply() {
		return apply;
	}
	public void setApply(int apply) {
		this.apply = apply;
	}
	public Date getImporttime() {
		return importtime;
	}
	public void setImporttime(Date importtime) {
		this.importtime = importtime;
	}
	public String getBond() {
		return bond;
	}
	public void setBond(String bond) {
		this.bond = bond;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
}
