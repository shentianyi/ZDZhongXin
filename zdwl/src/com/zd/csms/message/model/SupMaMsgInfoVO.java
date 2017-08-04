package com.zd.csms.message.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;

@table(name="t_SupMa_message_info")
public class SupMaMsgInfoVO implements Serializable {

	/**
	 * 消息提醒内容
	 */
	private static final long serialVersionUID = -2529738599974714287L;
	private int id;
	private int messageId;//消息ID
	private String content;//消息提示内容
	
	private String dealerName;//经销商名称
	private String brandName;//品牌名称
	private String bankName;//金融机构名称
	private String address;//地址
	private String customerManager;//客户经理
	private Date handoverPlanTime;//计划轮岗时间
	private Date inDate;//进驻时间
	private Date expectedInDate;//预计进驻时间
	
	private String supervisorName;//监管员姓名
	private String staffNo;//员工号
	private String supervisorAddress;//地址
	private Date interviewTime;//面试时间
	private Date trainTime;//培训时间
	private String isTrain;//是否经过培训
	private String intentionCity;//意向城市
	private Date trainCheckDate;//培训考核时间
	private Date entryTime;//进店时间
	private Date  dimissionDate;  //离岗时间
	private Date leaveTime; //离职时间
	private Date handoverDuedate;//轮岗到期时间
	private int  handoverOverDuedate;//轮岗超期时间
	private Date resignApplyDate;//辞职申请时间
	private Date expectResignTime;//期望离职时间
	
	private String shouldMorningStart;// 应早上签到
	private String shouldMorningEnd;// 应中午签退
	private String shouldAfternoonStart;// 应下午签到
	private String shouldAfternoonEnd;// 应下午签退
	private String actualMorningStart;// 实际早上签到
	private String actualMorningEnd;// 实际中午签退
	private String actualAfternoonStart;// 实际下午签到
	private String actualAfternoonEnd;// 实际下午签退
	
	private int isread;//是否读取1.未读2.已读
	private int ismask;//是否屏蔽1.未屏蔽2.已屏蔽
	
	private Date sendDate;//发送时间
	
	private int infoHashCode;//当前对象除id、sendDate字段外其他字段toString方法后生成的HashCode
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerManager() {
		return customerManager;
	}
	public void setCustomerManager(String customerManager) {
		this.customerManager = customerManager;
	}
	public Date getHandoverPlanTime() {
		return handoverPlanTime;
	}
	public void setHandoverPlanTime(Date handoverPlanTime) {
		this.handoverPlanTime = handoverPlanTime;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public Date getExpectedInDate() {
		return expectedInDate;
	}
	public void setExpectedInDate(Date expectedInDate) {
		this.expectedInDate = expectedInDate;
	}
	public String getSupervisorName() {
		return supervisorName;
	}
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getSupervisorAddress() {
		return supervisorAddress;
	}
	public void setSupervisorAddress(String supervisorAddress) {
		this.supervisorAddress = supervisorAddress;
	}
	public Date getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(Date interviewTime) {
		this.interviewTime = interviewTime;
	}
	public Date getTrainTime() {
		return trainTime;
	}
	public void setTrainTime(Date trainTime) {
		this.trainTime = trainTime;
	}
	public String getIsTrain() {
		return isTrain;
	}
	public void setIsTrain(String isTrain) {
		this.isTrain = isTrain;
	}
	public String getIntentionCity() {
		return intentionCity;
	}
	public void setIntentionCity(String intentionCity) {
		this.intentionCity = intentionCity;
	}
	public Date getTrainCheckDate() {
		return trainCheckDate;
	}
	public void setTrainCheckDate(Date trainCheckDate) {
		this.trainCheckDate = trainCheckDate;
	}
	public Date getEntryTime() {
		return entryTime;
	}
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}
	public Date getDimissionDate() {
		return dimissionDate;
	}
	public void setDimissionDate(Date dimissionDate) {
		this.dimissionDate = dimissionDate;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Date getHandoverDuedate() {
		return handoverDuedate;
	}
	public void setHandoverDuedate(Date handoverDuedate) {
		this.handoverDuedate = handoverDuedate;
	}
	public int getHandoverOverDuedate() {
		return handoverOverDuedate;
	}
	public void setHandoverOverDuedate(int handoverOverDuedate) {
		this.handoverOverDuedate = handoverOverDuedate;
	}
	public Date getResignApplyDate() {
		return resignApplyDate;
	}
	public void setResignApplyDate(Date resignApplyDate) {
		this.resignApplyDate = resignApplyDate;
	}
	public Date getExpectResignTime() {
		return expectResignTime;
	}
	public void setExpectResignTime(Date expectResignTime) {
		this.expectResignTime = expectResignTime;
	}
	public String getShouldMorningStart() {
		return shouldMorningStart;
	}
	public void setShouldMorningStart(String shouldMorningStart) {
		this.shouldMorningStart = shouldMorningStart;
	}
	public String getShouldMorningEnd() {
		return shouldMorningEnd;
	}
	public void setShouldMorningEnd(String shouldMorningEnd) {
		this.shouldMorningEnd = shouldMorningEnd;
	}
	public String getShouldAfternoonStart() {
		return shouldAfternoonStart;
	}
	public void setShouldAfternoonStart(String shouldAfternoonStart) {
		this.shouldAfternoonStart = shouldAfternoonStart;
	}
	public String getShouldAfternoonEnd() {
		return shouldAfternoonEnd;
	}
	public void setShouldAfternoonEnd(String shouldAfternoonEnd) {
		this.shouldAfternoonEnd = shouldAfternoonEnd;
	}
	public String getActualMorningStart() {
		return actualMorningStart;
	}
	public void setActualMorningStart(String actualMorningStart) {
		this.actualMorningStart = actualMorningStart;
	}
	public String getActualMorningEnd() {
		return actualMorningEnd;
	}
	public void setActualMorningEnd(String actualMorningEnd) {
		this.actualMorningEnd = actualMorningEnd;
	}
	public String getActualAfternoonStart() {
		return actualAfternoonStart;
	}
	public void setActualAfternoonStart(String actualAfternoonStart) {
		this.actualAfternoonStart = actualAfternoonStart;
	}
	public String getActualAfternoonEnd() {
		return actualAfternoonEnd;
	}
	public void setActualAfternoonEnd(String actualAfternoonEnd) {
		this.actualAfternoonEnd = actualAfternoonEnd;
	}
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	public int getIsmask() {
		return ismask;
	}
	public void setIsmask(int ismask) {
		this.ismask = ismask;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	public int getInfoHashCode() {
		return infoHashCode;
	}
	public void setInfoHashCode(int infoHashCode) {
		this.infoHashCode = infoHashCode;
	}
	@Override
	public String toString() {
		return "SupMaMsgInfoVO [messageId=" + messageId + ", content=" + content + ", dealerName=" + dealerName
				+ ", brandName=" + brandName + ", bankName=" + bankName + ", address=" + address + ", customerManager="
				+ customerManager + ", handoverPlanTime=" + handoverPlanTime + ", inDate=" + inDate
				+ ", expectedInDate=" + expectedInDate + ", supervisorName=" + supervisorName + ", staffNo=" + staffNo
				+ ", supervisorAddress=" + supervisorAddress + ", interviewTime=" + interviewTime + ", trainTime="
				+ trainTime + ", isTrain=" + isTrain + ", intentionCity=" + intentionCity + ", trainCheckDate="
				+ trainCheckDate + ", entryTime=" + entryTime + ", dimissionDate=" + dimissionDate + ", leaveTime="
				+ leaveTime + ", handoverDuedate=" + handoverDuedate + ", resignApplyDate=" + resignApplyDate
				+ ", expectResignTime=" + expectResignTime + ", shouldMorningStart=" + shouldMorningStart
				+ ", shouldMorningEnd=" + shouldMorningEnd + ", shouldAfternoonStart=" + shouldAfternoonStart
				+ ", shouldAfternoonEnd=" + shouldAfternoonEnd + ", actualMorningStart=" + actualMorningStart
				+ ", actualMorningEnd=" + actualMorningEnd + ", actualAfternoonStart=" + actualAfternoonStart
				+ ", actualAfternoonEnd=" + actualAfternoonEnd + ", isread=" + isread + ", ismask=" + ismask + "]";
	}
	
}
