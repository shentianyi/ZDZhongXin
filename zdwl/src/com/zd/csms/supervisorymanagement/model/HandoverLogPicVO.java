package com.zd.csms.supervisorymanagement.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
@table(name="T_HANDOVER_LOG_PIC")
public class HandoverLogPicVO implements Serializable {

	/**
	 * 交接书图片
	 */
	private static final long serialVersionUID = -7934939329442326385L;
	/**
	 * 主键ID
	 */
	private int id;       
	/**
	 * 图片一
	 */
	private int pic1;
	/**
	 * 图片二
	 */
	private int pic2;
	/**
	 * 图片三
	 */
	private int pic3;
	
	/**
	 * 图片四
	 */
	private int pic4;
	/**
	 * 图片五
	 */
	private int pic5;
	/**
	 * 图片六
	 */
	private int pic6;
	/**
	 * 图片七
	 */
	private int pic7;
	/**
	 * 图片八
	 */
	private int pic8;
	/**
	 * 图片九
	 */
	private int pic9;
	/**
	 * 图片十
	 */
	private int pic10;
	/**
	 * 上传人
	 */
	private int createUser;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 下一审批人
	 */
	private int nextApproval;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 交际记录ID
	 */
	private int handoverLogId;
	/**
	 * 是否可编辑:0:可编辑，1：不可编辑
	 */
	private int isEdit;
	/**
	 * 审批状态 0：未审批，1：正在审批，3：审批完成
	 */
	private int approveStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPic1() {
		return pic1;
	}
	public void setPic1(int pic1) {
		this.pic1 = pic1;
	}
	public int getPic2() {
		return pic2;
	}
	public void setPic2(int pic2) {
		this.pic2 = pic2;
	}
	public int getPic3() {
		return pic3;
	}
	public void setPic3(int pic3) {
		this.pic3 = pic3;
	}
	public int getPic4() {
		return pic4;
	}
	public void setPic4(int pic4) {
		this.pic4 = pic4;
	}
	public int getPic5() {
		return pic5;
	}
	public void setPic5(int pic5) {
		this.pic5 = pic5;
	}
	public int getPic6() {
		return pic6;
	}
	public void setPic6(int pic6) {
		this.pic6 = pic6;
	}
	public int getPic7() {
		return pic7;
	}
	public void setPic7(int pic7) {
		this.pic7 = pic7;
	}
	public int getPic8() {
		return pic8;
	}
	public void setPic8(int pic8) {
		this.pic8 = pic8;
	}
	public int getPic9() {
		return pic9;
	}
	public void setPic9(int pic9) {
		this.pic9 = pic9;
	}
	public int getPic10() {
		return pic10;
	}
	public void setPic10(int pic10) {
		this.pic10 = pic10;
	}
	public int getCreateUser() {
		return createUser;
	}
	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getNextApproval() {
		return nextApproval;
	}
	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getHandoverLogId() {
		return handoverLogId;
	}
	public void setHandoverLogId(int handoverLogId) {
		this.handoverLogId = handoverLogId;
	}
	public int getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(int isEdit) {
		this.isEdit = isEdit;
	}
	public int getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(int approveStatus) {
		this.approveStatus = approveStatus;
	}        
	
}
