package com.zd.csms.planandreport.model;

import java.io.Serializable;

import com.zd.core.annotation.table;

/**
 * 视频检查报告-检查项
 */
@table(name = "t_video_report_item")
public class VideoReportItemVO implements Serializable {
	private static final long serialVersionUID = -8583112702148550140L;
	private int id;// 视频检查计划 id
	private int check_minute;// 实际检查用时(分钟)
	private int stock_standard;// 库存数是否符合
	private String stock_remark;// 库存数不符合：备注
	private int certification_standard;// 合格证数量是否符合
	private String certification_remark;// 合格证数量不符合：备注
	private int key_standard;// 钥匙数是否符合
	private String key_remark;// 钥匙数不符合：备注
	private String manual_ledger;// 手工台账
	private int manual_standard;// 手工台账 :是否规范
	private String manual_remark;// 手工台账不规范：备注

	private String shop_license;// 4S店授权书
	private int license_standard;// 4S店授权书 :是否规范
	private String license_remark;// 4S店授权书不规范：备注

	private String appointment_book;// 监管员委任书
	private int appointment_standard;// 监管员委任书 :是否规范
	private String appointment_remark;// 监管员委任书不规范：备注

	private String general_ledger;// 电子总账
	private int ledger_standard;// 电子总账 :是否规范
	private String ledger_remark;// 电子总账不规范：备注

	private String due_date;// 日查库相关表单
	private int date_standard;// 日查库相关表单 :是否规范
	private String date_remark;// 日查库相关表单不规范：备注

	private String key_transfer;// 钥匙交接表
	private int transfer_standard;// 钥匙交接表 :是否规范
	private String transfer_remark;// 钥匙交接表不规范：备注

	private String supervisor_confirmation;// 监管确认书
	private int confirm_standard;// 监管确认书 :是否规范
	private String confirm_remark;// 监管确认书不规范：备注

	private String release;// 释放申领书
	private int release_standard;// 释放申领书 :是否规范
	private String release_remark;// 释放申领书不规范：备注

	private String move_application;// 移动申请书
	private int move_standard;// 移动申请书 :是否规范
	private String move_remark;// 移动申请书不规范：备注

	private String check_list;// 月库存核对清单
	private int check_standard;// 月库存核对清单 :是否规范
	private String check_remark;// 月库存核对清单不规范：备注

	private String work_card;// 工服/工牌
	private int card_standard;// 工服/工牌 :是否规范
	private String card_remark;// 工服/工牌不规范：备注

	private String other_remark;// 其他异常情况
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCheck_minute() {
		return check_minute;
	}

	public void setCheck_minute(int check_minute) {
		this.check_minute = check_minute;
	}

	public int getStock_standard() {
		return stock_standard;
	}

	public void setStock_standard(int stock_standard) {
		this.stock_standard = stock_standard;
	}

	public String getStock_remark() {
		return stock_remark;
	}

	public void setStock_remark(String stock_remark) {
		this.stock_remark = stock_remark;
	}

	public int getCertification_standard() {
		return certification_standard;
	}

	public void setCertification_standard(int certification_standard) {
		this.certification_standard = certification_standard;
	}

	public String getCertification_remark() {
		return certification_remark;
	}

	public void setCertification_remark(String certification_remark) {
		this.certification_remark = certification_remark;
	}

	public int getKey_standard() {
		return key_standard;
	}

	public void setKey_standard(int key_standard) {
		this.key_standard = key_standard;
	}

	public String getKey_remark() {
		return key_remark;
	}

	public void setKey_remark(String key_remark) {
		this.key_remark = key_remark;
	}

	public String getManual_ledger() {
		return manual_ledger;
	}

	public void setManual_ledger(String manual_ledger) {
		this.manual_ledger = manual_ledger;
	}

	public int getManual_standard() {
		return manual_standard;
	}

	public void setManual_standard(int manual_standard) {
		this.manual_standard = manual_standard;
	}

	public String getManual_remark() {
		return manual_remark;
	}

	public void setManual_remark(String manual_remark) {
		this.manual_remark = manual_remark;
	}

	public String getShop_license() {
		return shop_license;
	}

	public void setShop_license(String shop_license) {
		this.shop_license = shop_license;
	}

	public int getLicense_standard() {
		return license_standard;
	}

	public void setLicense_standard(int license_standard) {
		this.license_standard = license_standard;
	}

	public String getLicense_remark() {
		return license_remark;
	}

	public void setLicense_remark(String license_remark) {
		this.license_remark = license_remark;
	}

	public String getAppointment_book() {
		return appointment_book;
	}

	public void setAppointment_book(String appointment_book) {
		this.appointment_book = appointment_book;
	}

	public int getAppointment_standard() {
		return appointment_standard;
	}

	public void setAppointment_standard(int appointment_standard) {
		this.appointment_standard = appointment_standard;
	}

	public String getAppointment_remark() {
		return appointment_remark;
	}

	public void setAppointment_remark(String appointment_remark) {
		this.appointment_remark = appointment_remark;
	}

	public String getGeneral_ledger() {
		return general_ledger;
	}

	public void setGeneral_ledger(String general_ledger) {
		this.general_ledger = general_ledger;
	}

	public int getLedger_standard() {
		return ledger_standard;
	}

	public void setLedger_standard(int ledger_standard) {
		this.ledger_standard = ledger_standard;
	}

	public String getLedger_remark() {
		return ledger_remark;
	}

	public void setLedger_remark(String ledger_remark) {
		this.ledger_remark = ledger_remark;
	}

	public String getDue_date() {
		return due_date;
	}

	public void setDue_date(String due_date) {
		this.due_date = due_date;
	}

	public int getDate_standard() {
		return date_standard;
	}

	public void setDate_standard(int date_standard) {
		this.date_standard = date_standard;
	}

	public String getDate_remark() {
		return date_remark;
	}

	public void setDate_remark(String date_remark) {
		this.date_remark = date_remark;
	}

	public String getKey_transfer() {
		return key_transfer;
	}

	public void setKey_transfer(String key_transfer) {
		this.key_transfer = key_transfer;
	}

	public int getTransfer_standard() {
		return transfer_standard;
	}

	public void setTransfer_standard(int transfer_standard) {
		this.transfer_standard = transfer_standard;
	}

	public String getTransfer_remark() {
		return transfer_remark;
	}

	public void setTransfer_remark(String transfer_remark) {
		this.transfer_remark = transfer_remark;
	}

	public String getSupervisor_confirmation() {
		return supervisor_confirmation;
	}

	public void setSupervisor_confirmation(String supervisor_confirmation) {
		this.supervisor_confirmation = supervisor_confirmation;
	}

	public int getConfirm_standard() {
		return confirm_standard;
	}

	public void setConfirm_standard(int confirm_standard) {
		this.confirm_standard = confirm_standard;
	}

	public String getConfirm_remark() {
		return confirm_remark;
	}

	public void setConfirm_remark(String confirm_remark) {
		this.confirm_remark = confirm_remark;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}

	public int getRelease_standard() {
		return release_standard;
	}

	public void setRelease_standard(int release_standard) {
		this.release_standard = release_standard;
	}

	public String getRelease_remark() {
		return release_remark;
	}

	public void setRelease_remark(String release_remark) {
		this.release_remark = release_remark;
	}

	public String getMove_application() {
		return move_application;
	}

	public void setMove_application(String move_application) {
		this.move_application = move_application;
	}

	public int getMove_standard() {
		return move_standard;
	}

	public void setMove_standard(int move_standard) {
		this.move_standard = move_standard;
	}

	public String getMove_remark() {
		return move_remark;
	}

	public void setMove_remark(String move_remark) {
		this.move_remark = move_remark;
	}

	public String getCheck_list() {
		return check_list;
	}

	public void setCheck_list(String check_list) {
		this.check_list = check_list;
	}

	public int getCheck_standard() {
		return check_standard;
	}

	public void setCheck_standard(int check_standard) {
		this.check_standard = check_standard;
	}

	public String getCheck_remark() {
		return check_remark;
	}

	public void setCheck_remark(String check_remark) {
		this.check_remark = check_remark;
	}

	public String getWork_card() {
		return work_card;
	}

	public void setWork_card(String work_card) {
		this.work_card = work_card;
	}

	public int getCard_standard() {
		return card_standard;
	}

	public void setCard_standard(int card_standard) {
		this.card_standard = card_standard;
	}

	public String getCard_remark() {
		return card_remark;
	}

	public void setCard_remark(String card_remark) {
		this.card_remark = card_remark;
	}

	public String getOther_remark() {
		return other_remark;
	}

	public void setOther_remark(String other_remark) {
		this.other_remark = other_remark;
	}

}
