package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_fixed_asset_list")
public class FixedAssetListVO implements Serializable {

	/**
	 * 固定资产管理表
	 */
	private static final long serialVersionUID = 6582108613477173250L;
	
	private int id;
	private int fid;
	private String department;//使用部门
	private int username;//使用人
	private String trade;//存放店
	private int trade_province;//存放地址（省）
	private int trade_city;//存放地址（市）
	private String address;//地址
	private String password;//密码
	private String key;//钥匙
	private Date deposit_time;//存放时间
	private Date out_time;//转出时间
	private String express;//运输公司
	private String express_num;//单号
	private String express_money;//运费
	private Date repair_time;//维修时间
	private String repair_money;//维修金额
	private String repair_des;//维修内容
	private int receive_pic;//设备接收单
	private String des;//备注
	
	private int createuserid;//创建人
	private Date createdate;//创建时间
	private int upduserid;//修改人
	private Date upddate;//修改时间
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getUsername() {
		return username;
	}
	public void setUsername(int username) {
		this.username = username;
	}
	public String getTrade() {
		return trade;
	}
	public void setTrade(String trade) {
		this.trade = trade;
	}
	public int getTrade_province() {
		return trade_province;
	}
	public void setTrade_province(int tradeProvince) {
		trade_province = tradeProvince;
	}
	public int getTrade_city() {
		return trade_city;
	}
	public void setTrade_city(int tradeCity) {
		trade_city = tradeCity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getDeposit_time() {
		return deposit_time;
	}
	public void setDeposit_time(Date depositTime) {
		deposit_time = depositTime;
	}
	public Date getOut_time() {
		return out_time;
	}
	public void setOut_time(Date outTime) {
		out_time = outTime;
	}
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getExpress_num() {
		return express_num;
	}
	public void setExpress_num(String expressNum) {
		express_num = expressNum;
	}
	public String getExpress_money() {
		return express_money;
	}
	public void setExpress_money(String expressMoney) {
		express_money = expressMoney;
	}
	public Date getRepair_time() {
		return repair_time;
	}
	public void setRepair_time(Date repairTime) {
		repair_time = repairTime;
	}
	public String getRepair_money() {
		return repair_money;
	}
	public void setRepair_money(String repairMoney) {
		repair_money = repairMoney;
	}
	public String getRepair_des() {
		return repair_des;
	}
	public void setRepair_des(String repairDes) {
		repair_des = repairDes;
	}
	public int getReceive_pic() {
		return receive_pic;
	}
	public void setReceive_pic(int receivePic) {
		receive_pic = receivePic;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(int createuserid) {
		this.createuserid = createuserid;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public int getUpduserid() {
		return upduserid;
	}
	public void setUpduserid(int upduserid) {
		this.upduserid = upduserid;
	}
	public Date getUpddate() {
		return upddate;
	}
	public void setUpddate(Date upddate) {
		this.upddate = upddate;
	}
	
	
	

}
