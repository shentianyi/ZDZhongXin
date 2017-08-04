package com.zd.csms.attendance.model;

import com.zd.csms.attendance.contants.SignContants;
import com.zd.tools.StringUtil;

/**
 * 修改考勤签到列表页使用的对象，添加日期属性
 *
 */
public class SignRecordBean extends SignRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String todayDate;
	private String oldAttendanceName;// 历史出勤情况

	public String getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public String getOldAttendanceName() {
		return oldAttendanceName;
	}


	public void setOldAttendanceName(String oldAttendanceName) {
		if (StringUtil.isNotEmpty(oldAttendanceName)) {
			if ("1,2".equals(oldAttendanceName)) {
				 this.oldAttendanceName = "迟到早退";
			}else if (StringUtil.isNumber(oldAttendanceName)){
				int ss= Integer.parseInt(oldAttendanceName);
				switch (ss) {
				case 1:
					 this.oldAttendanceName =SignContants.LDATE.getName();
					break;
				case 2:
					 this.oldAttendanceName =SignContants.LEAVE_EARLY.getName();
					break;
				case 3:
					 this.oldAttendanceName =SignContants.ABSENTEEISM.getName();
					break;
				case 4:
					 this.oldAttendanceName =SignContants.NORMAL.getName();
					break;
				
				default:
					 this.oldAttendanceName="";
					break;
				}
			}
			
		}
	}

}
