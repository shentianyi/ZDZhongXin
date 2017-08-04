package com.zd.csms.messageAndWaring.message.model.market;

import java.util.Date;
import com.zd.core.annotation.table;
import com.zd.csms.messageAndWaring.message.model.WaringInfoVO;

/**
 * 市场部-信息预警实体
 * @author zhangzhicheng
 *
 */
@table(name = "t_marketWaring_info")
public class MarketWaringInfoVO extends WaringInfoVO {

	private static final long serialVersionUID = -8511880718885122632L;
	private int id;// 主键id
	private int type;// 消息类别
	private int dealerId;
	private String dealerName;// 经销商名称
	private String brandName;// 品牌名称
	private String bankFullName;// 银行全名
	private String manager;// 银行客户经理
	private Date inDate;// 进驻时间
    private Date feePayDate;// 监管费缴纳时间 
    private String payAmount;// 监管费缴纳金额
	private String balance;// 余额 
	private Date mailingDate;// 协议邮寄时间
	private Date recoveryDate;// 协议回收时间
	private Date protocolExpire;// 协议到期时间
	private Date proInTransferDate;// 项目进驻流转单时间 
	private Date busInTransferDate;// 业务进驻流转单进驻时间 
	private String feeStandard;// 监管费标准
	private int signId;// 屏蔽的信息标识
	private Date createDate;// 创建时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDealerId() {
		return dealerId;
	}
	public void setDealerId(int dealerId) {
		this.dealerId = dealerId;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBankFullName() {
		return bankFullName;
	}
	public void setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getFeePayDate() {
		return feePayDate;
	}
	public void setFeePayDate(Date feePayDate) {
		this.feePayDate = feePayDate;
	}
	public String getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(String payAmount) {
		this.payAmount = payAmount;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public Date getMailingDate() {
		return mailingDate;
	}
	public void setMailingDate(Date mailingDate) {
		this.mailingDate = mailingDate;
	}
	public Date getRecoveryDate() {
		return recoveryDate;
	}
	public void setRecoveryDate(Date recoveryDate) {
		this.recoveryDate = recoveryDate;
	}
	public Date getProtocolExpire() {
		return protocolExpire;
	}
	public void setProtocolExpire(Date protocolExpire) {
		this.protocolExpire = protocolExpire;
	}
	public Date getProInTransferDate() {
		return proInTransferDate;
	}
	public void setProInTransferDate(Date proInTransferDate) {
		this.proInTransferDate = proInTransferDate;
	}
	public Date getBusInTransferDate() {
		return busInTransferDate;
	}
	public void setBusInTransferDate(Date busInTransferDate) {
		this.busInTransferDate = busInTransferDate;
	}
	public String getFeeStandard() {
		return feeStandard;
	}
	public void setFeeStandard(String feeStandard) {
		this.feeStandard = feeStandard;
	}
	public int getSignId() {
		return signId;
	}
	public void setSignId(int signId) {
		this.signId = signId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


}
