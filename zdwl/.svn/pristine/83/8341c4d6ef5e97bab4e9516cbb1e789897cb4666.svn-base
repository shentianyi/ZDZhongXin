package com.zd.csms.attendance.web.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.zd.csms.attendance.model.Attendance;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecordBean;
import com.zd.csms.attendance.model.SignRecordCheckSpAll;
import com.zd.csms.attendance.model.SignRecordCheckSpInfo;
import com.zd.csms.attendance.querybean.LedgerQueryVO;
import com.zd.csms.marketing.model.DealerQueryVO;

public class AttendanceForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AttendanceTime time = new AttendanceTime();
	private DealerQueryVO dealerQuery = new DealerQueryVO();
	private AttendanceQueryVO query=new AttendanceQueryVO();
	private Attendance attendance=new Attendance();
	private AttendanceInfo attendanceInfo=new AttendanceInfo();
	private List<SignRecordBean> signRecordList=new ArrayList<SignRecordBean>();
	
	//需求86
	private SignRecordCheckSpAll squery = new SignRecordCheckSpAll(); 
	
	private LedgerQueryVO ledgerQuery = new LedgerQueryVO();//考勤表台账查询条件实体
	public AttendanceTime getTime() {
		return time;
	}
	public void setTime(AttendanceTime time) {
		this.time = time;
	}
	public DealerQueryVO getDealerQuery() {
		return dealerQuery;
	}
	public void setDealerQuery(DealerQueryVO dealerQuery) {
		this.dealerQuery = dealerQuery;
	}
	public AttendanceQueryVO getQuery() {
		return query;
	}
	public void setQuery(AttendanceQueryVO query) {
		this.query = query;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public AttendanceInfo getAttendanceInfo() {
		return attendanceInfo;
	}
	public void setAttendanceInfo(AttendanceInfo attendanceInfo) {
		this.attendanceInfo = attendanceInfo;
	}
	public List<SignRecordBean> getSignRecordList() {
		return signRecordList;
	}
	public void setSignRecordList(List<SignRecordBean> signRecordList) {
		this.signRecordList = signRecordList;
	}
	public LedgerQueryVO getLedgerQuery() {
		return ledgerQuery;
	}
	public void setLedgerQuery(LedgerQueryVO ledgerQuery) {
		this.ledgerQuery = ledgerQuery;
	}
	public SignRecordCheckSpAll getSquery() {
		return squery;
	}
	public void setSquery(SignRecordCheckSpAll squery) {
		this.squery = squery;
	}
	
	
}
