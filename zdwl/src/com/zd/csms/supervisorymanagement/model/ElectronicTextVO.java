package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_ELECTRONIC_TEXT")
public class ElectronicTextVO implements Serializable {

	/**
	 * 交接书电子文本资料
	 */
	private static final long serialVersionUID = -7934939329442326385L;
	/**
	 * 主键ID
	 */
	private int id;        
	/**
	 * 监管员ID
	 */
	private int supervisorId;
	/**
	 * 电子台账
	 */
	private String electronBill;    
	/**
	 * 接车明细数量
	 */
	private int receiveCarDetailAmount;
	/**
	 * 接车明细开始时间
	 */
	private Date receiveCarDetailStartTime;
	/**
	 * 接车明细结束时间
	 */
	private Date receiveCarDetailEndTime;
	/**
	 * 周库存核对清单数量
	 */
	private int weekBillAmount;
	/**
	 * 周库存核对清单开始时间
	 */
	private Date weekBillStartTime;
	/**
	 * 周库存核对清单结束时间
	 */
	private Date weekBillEndTime;
	/**
	 * 月库存核对清单数量
	 */
	private int monthBillAmount;
	/**
	 * 月库存核对清单开始时间
	 */
	private Date monthBillStartTime;
	/**
	 * 月库存核对清单结束时间
	 */
	private Date monthBillEndTime;
	/**
	 * 申领释放书数量
	 */
	private int applyFreeBookAmount;
	/**
	 * 申领释放书开始时间
	 */
	private Date applyFreeBookStartTime;
	/**
	 * 申领释放书结束时间
	 */
	private Date applyFreeBookEndTime;
	/**
	 * 监管确认书数量
	 */
	private int confirmationAmount;
	/**
	 * 监管确认书开始时间
	 */
	private Date confirmationStartTime;
	/**
	 * 监管确认书结束时间
	 */
	private Date confirmationEndTime;
	/**
	 * 二网申请数量
	 */
	private int towApplyAmount;
	/**
	 * 二网申请开始时间
	 */
	private Date towApplyStartTime;
	/**
	 * 二网申请结束时间
	 */
	private Date towApplyEndTime;
	/**
	 * 其他数量
	 */
	private int otherAmount;
	/**
	 * 其他开始时间
	 */
	private Date otherStartTime;
	/**
	 * 其他结束时间
	 */
	private Date otherEndTime;
	/**
	 * 备注
	 */
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}
	public String getElectronBill() {
		return electronBill;
	}
	public void setElectronBill(String electronBill) {
		this.electronBill = electronBill;
	}
	public int getReceiveCarDetailAmount() {
		return receiveCarDetailAmount;
	}
	public void setReceiveCarDetailAmount(int receiveCarDetailAmount) {
		this.receiveCarDetailAmount = receiveCarDetailAmount;
	}
	public Date getReceiveCarDetailStartTime() {
		return receiveCarDetailStartTime;
	}
	public void setReceiveCarDetailStartTime(Date receiveCarDetailStartTime) {
		this.receiveCarDetailStartTime = receiveCarDetailStartTime;
	}
	public Date getReceiveCarDetailEndTime() {
		return receiveCarDetailEndTime;
	}
	public void setReceiveCarDetailEndTime(Date receiveCarDetailEndTime) {
		this.receiveCarDetailEndTime = receiveCarDetailEndTime;
	}
	public int getWeekBillAmount() {
		return weekBillAmount;
	}
	public void setWeekBillAmount(int weekBillAmount) {
		this.weekBillAmount = weekBillAmount;
	}
	public Date getWeekBillStartTime() {
		return weekBillStartTime;
	}
	public void setWeekBillStartTime(Date weekBillStartTime) {
		this.weekBillStartTime = weekBillStartTime;
	}
	public Date getWeekBillEndTime() {
		return weekBillEndTime;
	}
	public void setWeekBillEndTime(Date weekBillEndTime) {
		this.weekBillEndTime = weekBillEndTime;
	}
	public int getMonthBillAmount() {
		return monthBillAmount;
	}
	
	public Date getMonthBillStartTime() {
		return monthBillStartTime;
	}
	public void setMonthBillStartTime(Date monthBillStartTime) {
		this.monthBillStartTime = monthBillStartTime;
	}
	public Date getMonthBillEndTime() {
		return monthBillEndTime;
	}
	public void setMonthBillEndTime(Date monthBillEndTime) {
		this.monthBillEndTime = monthBillEndTime;
	}
	public void setMonthBillAmount(int monthBillAmount) {
		this.monthBillAmount = monthBillAmount;
	}
	public int getApplyFreeBookAmount() {
		return applyFreeBookAmount;
	}
	public void setApplyFreeBookAmount(int applyFreeBookAmount) {
		this.applyFreeBookAmount = applyFreeBookAmount;
	}
	public Date getApplyFreeBookStartTime() {
		return applyFreeBookStartTime;
	}
	public void setApplyFreeBookStartTime(Date applyFreeBookStartTime) {
		this.applyFreeBookStartTime = applyFreeBookStartTime;
	}
	public Date getApplyFreeBookEndTime() {
		return applyFreeBookEndTime;
	}
	public void setApplyFreeBookEndTime(Date applyFreeBookEndTime) {
		this.applyFreeBookEndTime = applyFreeBookEndTime;
	}
	public int getConfirmationAmount() {
		return confirmationAmount;
	}
	public void setConfirmationAmount(int confirmationAmount) {
		this.confirmationAmount = confirmationAmount;
	}
	public Date getConfirmationStartTime() {
		return confirmationStartTime;
	}
	public void setConfirmationStartTime(Date confirmationStartTime) {
		this.confirmationStartTime = confirmationStartTime;
	}
	public Date getConfirmationEndTime() {
		return confirmationEndTime;
	}
	public void setConfirmationEndTime(Date confirmationEndTime) {
		this.confirmationEndTime = confirmationEndTime;
	}
	public int getTowApplyAmount() {
		return towApplyAmount;
	}
	public void setTowApplyAmount(int towApplyAmount) {
		this.towApplyAmount = towApplyAmount;
	}
	public Date getTowApplyStartTime() {
		return towApplyStartTime;
	}
	public void setTowApplyStartTime(Date towApplyStartTime) {
		this.towApplyStartTime = towApplyStartTime;
	}
	public Date getTowApplyEndTime() {
		return towApplyEndTime;
	}
	public void setTowApplyEndTime(Date towApplyEndTime) {
		this.towApplyEndTime = towApplyEndTime;
	}
	public int getOtherAmount() {
		return otherAmount;
	}
	public void setOtherAmount(int otherAmount) {
		this.otherAmount = otherAmount;
	}
	public Date getOtherStartTime() {
		return otherStartTime;
	}
	public void setOtherStartTime(Date otherStartTime) {
		this.otherStartTime = otherStartTime;
	}
	public Date getOtherEndTime() {
		return otherEndTime;
	}
	public void setOtherEndTime(Date otherEndTime) {
		this.otherEndTime = otherEndTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
