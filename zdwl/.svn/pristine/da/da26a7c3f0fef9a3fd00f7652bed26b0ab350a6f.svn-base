package com.zd.csms.attendance.model;

import java.io.Serializable;
import java.util.Date;

import com.zd.core.annotation.table;
import com.zd.csms.attendance.contants.SignContants;
import com.zd.tools.StringUtil;

/**
 * 考勤签到表
 *
 */
@table(name = "T_SIGN_RECORD")
public class SignRecord implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1909843176647576049L;
	private int id;
	private Date morningStart;// 早上签到
	private Date morningEnd;// 中午签退
	private Date afternoonStart;// 下午签到
	private Date afternoonEnd;// 下午签退
	private Date createDate;
	private int createUserId;
	private Date updateDate;
	private int updateUserId;
	private int respId;// 对应的储备库Id
	private int isLate;// 是否迟到 0：否 1：是
	private int isEarly;// 是否早退 0：否 1：是
	private int isAbsenteeism;// 是否旷工0：否 1：是
	private int isNormal;// 是否正常出勤0：否 1：是
	private String workDays;// 1234567代表周几多个','隔开
	private String oldAttendance;// 记录历史出勤情况
	
	private int state;//审核状态 0:未提交 1:审核通过 2：审核不通过 3:正在审批
	private int nextApproval;
	private Date approveDate; //审批日期
	private String approveOpinion; // 审批意见
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getWorkDays() {
		return workDays;
	}

	public void setWorkDays(String workDays) {
		this.workDays = workDays;
	}

	public Date getMorningStart() {
		return morningStart;
	}

	public void setMorningStart(Date morningStart) {
		this.morningStart = morningStart;
	}

	public Date getMorningEnd() {
		return morningEnd;
	}

	public void setMorningEnd(Date morningEnd) {
		this.morningEnd = morningEnd;
	}

	public Date getAfternoonStart() {
		return afternoonStart;
	}

	public void setAfternoonStart(Date afternoonStart) {
		this.afternoonStart = afternoonStart;
	}

	public Date getAfternoonEnd() {
		return afternoonEnd;
	}

	public void setAfternoonEnd(Date afternoonEnd) {
		this.afternoonEnd = afternoonEnd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(int updateUserId) {
		this.updateUserId = updateUserId;
	}

	public int getRespId() {
		return respId;
	}

	public void setRespId(int respId) {
		this.respId = respId;
	}

	public int getIsLate() {
		return isLate;
	}

	public void setIsLate(int isLate) {
		this.isLate = isLate;
	}

	public int getIsEarly() {
		return isEarly;
	}

	public void setIsEarly(int isEarly) {
		this.isEarly = isEarly;
	}

	public int getIsAbsenteeism() {
		return isAbsenteeism;
	}

	public void setIsAbsenteeism(int isAbsenteeism) {
		this.isAbsenteeism = isAbsenteeism;
	}

	public int getIsNormal() {

		return isNormal;
	}

	public void setIsNormal(int isNormal) {
		this.isNormal = isNormal;
	}

	public void updateSIGN() {
		if (isAbsenteeism > 0) {
			isNormal = 0;
			isLate=0;
			isEarly=0;
		} 
	}

	public String getOldAttendance() {
        if(StringUtil.isNotEmpty(oldAttendance)){
        	return oldAttendance ;
        }
        if (isAbsenteeism > 0) {
        	this.oldAttendance = SignContants.ABSENTEEISM.getCode()+"";
        	return oldAttendance;
        }
        
		if (isNormal > 0&&(isLate <=0 && isEarly <=0)) {
			this.oldAttendance = SignContants.NORMAL.getCode()+"";
			return oldAttendance;
		}


		if (isLate > 0 && isEarly > 0) {
			this.oldAttendance = SignContants.LDATE.getCode()+","+SignContants.LEAVE_EARLY.getCode();
			return oldAttendance;
		} else {
			if (isLate > 0) {
				this.oldAttendance = SignContants.LDATE.getCode()+"";
			}

			if (isEarly > 0) {
				this.oldAttendance = SignContants.LEAVE_EARLY.getCode()+"";
			}

		}

		return oldAttendance;
	}

	public void setOldAttendance(String oldAttendance) {
		this.oldAttendance = oldAttendance;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getNextApproval() {
		return nextApproval;
	}

	public void setNextApproval(int nextApproval) {
		this.nextApproval = nextApproval;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getApproveOpinion() {
		return approveOpinion;
	}

	public void setApproveOpinion(String approveOpinion) {
		this.approveOpinion = approveOpinion;
	}
	

}
