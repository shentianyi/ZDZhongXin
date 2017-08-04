package com.zd.csms.ledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.attendance.excel.AttendanceRowMapper;
import com.zd.csms.attendance.model.AttendanceLegerBean;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.attendance.web.form.AttendanceForm;
import com.zd.csms.base.option.OptionUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AttendanceAction extends ActionSupport{
	private AttendanceService service  = new AttendanceService();
	
	/**
	 * 考勤表台账：经理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findLedgerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm attendanceForm = (AttendanceForm) form;
		AttendanceQueryVO query=attendanceForm.getQuery();
		query.setCurrentRole(-1);
		query.setApprovalState(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("attendanceLegerList", request);
		tools.saveQueryVO(query);
		tools.setPageSize(40);
		List<AttendanceLegerBean> list=service.findAttendanceLegerList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		return mapping.findForward("attendance_ledger");
	}

	public void setOptions(HttpServletRequest request){
		request.setAttribute("years", OptionUtil.getYears());
		request.setAttribute("months", OptionUtil.getMonths());
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("banks", OptionUtil.getBanks());
		//列表页面审批状态
		request.setAttribute("queryApprovalState", OptionUtil.getQueryApprovalState());
		
	}
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		AttendanceForm attendanceForm = (AttendanceForm) form;
		AttendanceQueryVO query=attendanceForm.getQuery();
		query.setCurrentRole(-1);
		query.setApprovalState(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("attendanceLegerList", request);
		tools.setPageSize(400000);
		List<AttendanceLegerBean> list=service.findAttendanceLegerList(query,tools);
		IExportFile export = new ExportFileExcelImpl();
		try {
			export.export(list, new AttendanceRowMapper(), export.createDefaultFileName("考勤表"),"考勤表", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回列表页面
		return null;
	}
}
