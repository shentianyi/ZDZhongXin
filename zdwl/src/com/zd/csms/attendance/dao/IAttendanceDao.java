package com.zd.csms.attendance.dao;

import java.util.Date;
import java.util.List;

import com.zd.core.IDAO;
import com.zd.csms.attendance.model.AttendanceBean;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.model.SignRecordBean;
import com.zd.csms.attendance.model.SignRecordCheckSp;
import com.zd.csms.attendance.model.SignRecordCheckSpAll;
import com.zd.csms.attendance.model.SignRecordCheckSpDays;
import com.zd.csms.attendance.model.SignRecordCheckSpInfo;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryVO;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.tools.thumbPage.IThumbPageTools;

public interface IAttendanceDao extends IDAO {
	
	/**
	 * 根据用户Id获取所在经销商签到信息集合
	 * 
	 * @param id
	 * @return
	 */
	public List<AttendanceTimeQueryBean> getAttendanceTimeByUserId(int id);

	/**
	 * 根据监管员储备库Id获取所在经销商签到信息集合
	 * 
	 * @param repositoryid 监管员储备库Id
	 * @return
	 */
	public List<AttendanceTime> getAttendanceTimeByRepId(int repositoryid);
	/**
	 * 根据用户id查询当天签到记录
	 * 
	 * @param id
	 * @return
	 */
	public SignRecord getSignRecordByUserId(Date date,int respId);

	/**
	 * 根据用户id查询这个月的签到记录
	 * 
	 * @param userId
	 * @return
	 */
	public List<SignRecord> findListByUserId(int userId, int year, int month);

	/**
	 * 考勤表台账
	 * 
	 * @param ledgerQuery
	 * @param tools
	 * @return
	 */
	public List<LedgerQueryBean> findLedgerList(LedgerQueryVO ledgerQuery,
			IThumbPageTools tools);

	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query,
			IThumbPageTools tools);

	public boolean updateAttendanceInfoState(int id, int state);

	public boolean updateAttendanceInfoStateByAttendanceId(int attendanceId,
			int state);

	public boolean updateAttendanceState(int id, int approvalState,
			int nextApproval);

	public List<AttendanceInfo> getAttendanceInfoByState(int attendanceId,
			int approvalState);

	public List<SignRecord> findAttendanceSignRecordByRepositoryId(
			int repositoryId, Date monthStartDay, Date monthEndDay);

	public boolean updateAttendanceSignRecord(int id, int isLate, int isEarly,
			int isAbsenteeism, int isNormal);

	/**
	 * 根据储备库id查询某个月的签到记录
	 * @param respId
	 * @param year
	 * @param month
	 * @return
	 */
	public AttendanceInfo getAttendanceInfo(int respId, int  year,int month);
    /**
     * 更新迟到，早退,旷工，实际出勤，是否全勤
     * @param info
     * @return
     */
	public boolean updateAttendanceInfo(AttendanceInfo info);
	/**
	 * 考勤列表修改事假,病假,倒休，正休，加班
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public boolean updateAttendance(int attendanceInfoId, String name,
			double value);
	
	public List<AttendanceBean> findAttendanceList(AttendanceQueryVO query);

	/**
	 * @param date 日期
	 * @param isLate 是否迟到 0：否 1：是
	 * @param isEarly 是否早退 0：否 1：是
	 * @param isAbsenteeism 是否旷工 0：否 1：是
	 * @param isNormal 是否正常出勤 0：否 1：是
	 * @return
	 */
	public List<SignRecord> findSignRecordListDateAndStatus(Date date, int isLate, int isEarly, int isAbsenteeism, int isNormal,int respId);
	/**
	 * @param date 日期
	 * @param isLate 是否迟到 0：否 1：是
	 * @param isEarly 是否早退 0：否 1：是
	 * @param isAbsenteeism 是否旷工 0：否 1：是
	 * @param isNormal 是否正常出勤 0：否 1：是
	 * @return
	 */
	public List<SignRecord> findAbnormalSignRecordList(Date date, int respId);

	public List<AttendanceTime> getAttendanceTimes(int dealerId,
			IThumbPageTools tools);

	/*
	 * 每日考勤详情 -- 需求86
	*/
	public List<SignRecordCheckSpAll> findRespIdCS(SignRecordCheckSpAll query,
			IThumbPageTools tools);

	public List<SignRecordCheckSpAll> findSignRecordRespIdAndDayCheckCS(
			SignRecordCheckSpAll query);

	public List<SignRecordCheckSpAll> findSignRecordDealerByRespIdCS(
			SignRecordCheckSpAll query);

	public List<SignRecordCheckSpAll> searchSignRecordCheckSpListDetails(
			int repositoryId, int year, int month);

	public List<SignRecordCheckSpAll> ExtfindRespId(SignRecordCheckSpAll query);

	public boolean SubmitSignRecordCheckSpToApprove(int repositoryId, int year,
			int month);
	//审批通过/不通过 -- 监管员管理部经理
	public boolean ApproveSignRecordCheckSpList(SignRecordCheckSpAll query);

	public boolean updateSignRecord(SignRecord sr);



}
