package com.zd.csms.attendance.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.attendance.model.Attendance;
import com.zd.csms.attendance.model.AttendanceBean;
import com.zd.csms.attendance.model.AttendanceInfo;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.model.SignRecordBean;
import com.zd.csms.attendance.model.SignRecordCheckSpAll;
import com.zd.csms.attendance.quartz.CreateAttendance;
import com.zd.csms.attendance.querybean.LedgerQueryBean;
import com.zd.csms.attendance.querybean.LedgerQueryVO;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.attendance.web.form.AttendanceForm;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.ExtDealerVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.payment.service.SalaryCalc;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AttendanceAction extends ActionSupport{
	private AttendanceService service  = new AttendanceService();
	private DealerService dealerService= new DealerService();
	
	/**
	 * 新增或修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward addorupdateAttendanceTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AttendanceForm iform = (AttendanceForm) form;
		AttendanceTime attendanceTime=iform.getTime();
		boolean flag = service.addorupdateAttendanceTime(attendanceTime);
		if(flag){
			DealerVO dealer=dealerService.get(attendanceTime.getId());
			if(dealer!=null && dealer.getDdorbd()==1 && StringUtil.isNotEmpty(dealer.getBindInfo()) ){
				String[] bindInfos=dealer.getBindInfo().split(",");
				for(String bindInfo:bindInfos){
					attendanceTime.setId(Integer.parseInt(bindInfo));
					flag = service.addorupdateAttendanceTime(attendanceTime);
				}
			}
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return preUpdateAttendanceTime(mapping, iform, request, response);
	}
	
	public ActionForward preUpdateAttendanceTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String dealerId = request.getParameter("dealerId");
		request.setAttribute("dealer", dealerService.get(Integer.parseInt(dealerId)));
		AttendanceTime vo = service.getAttendanceTime(Integer.parseInt(dealerId));
		request.setAttribute("attendance", vo);
		/*if(DateUtil.getStringFormatByDate(new Date(), "dd").equals("01")){
			request.setAttribute("isOneDay", true);
		}else{
			request.setAttribute("isOneDay", false);
		}*/
		request.setAttribute("isOneDay", true);
		return mapping.findForward("dealertimeset");
	}
	
	/**
	 * 查询经销商列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findDealerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm ifrom = (AttendanceForm) form;
		DealerQueryVO query = ifrom.getDealerQuery();
		int currClientType = UserSessionUtil.getUserSession(request).getUser().getClient_type();
		query.setCurrClientType(currClientType);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList", request);
		tools.setPageSize(10);
		List<DealerQueryBean> querBeanList = dealerService.findList(query, tools);
		request.setAttribute("list", querBeanList);
		request.setAttribute("cooperationState", OptionUtil.getCooperationState());
		request.setAttribute("currClientType", currClientType);
		return mapping.findForward("dealerlist");
	}
	
	
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
		LedgerQueryVO ledgerQuery = attendanceForm.getLedgerQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("pagelist", request);
		tools.setPageSize(40);
		List<LedgerQueryBean> querBeanList =service.findLedgerList(ledgerQuery, tools); 
		request.setAttribute("list", querBeanList);
		return mapping.findForward("ledgerList");
	}
	public ActionForward abc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		new CreateAttendance().run();
		return null;
	}
	public ActionForward def(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		new Thread(new SalaryCalc()).start();
		return null;
	}
	
	public ActionForward findAttendanceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		afrom.getAttendance().setId(0);
		AttendanceQueryVO query=afrom.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getApprovalState()==0){
			query.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if(query.getApprovalState()==0){
			query.setApprovalState(-1);
		}
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("attendanceList", request);
		tools.saveQueryVO(query);
		List<AttendanceBean> list=service.findAttendanceList(query,tools);
		request.setAttribute("list", list);
		if(list!=null && list.size()>0){
			afrom.getAttendance().setId(list.get(0).getAttendanceId());
			afrom.getAttendance().setState(list.get(0).getAttendanceState());
		}
		request.setAttribute("listSize", list.size());
		setOptions(request);
		return mapping.findForward("attendance_list");
	}
	
	public ActionForward updateAttendanceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		return mapping.findForward("attendance_list");
	}
	
	
	
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SR.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("years", OptionUtil.getYears());
		request.setAttribute("months", OptionUtil.getMonths());
		//列表页面审批状态
		request.setAttribute("queryApprovalState", OptionUtil.getQueryApprovalState());
		
	}
	/**
	 * 监管员管理部经理审批某一条记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		AttendanceInfo attendanceInfo=afrom.getAttendanceInfo();
		boolean flag=service.updateAttendanceInfoState(attendanceInfo.getId(),ApprovalContant.APPROVAL_NOPASS.getCode());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findAttendanceList(mapping,form,request,response);
	}
	/**
	 * 列表页面提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		Attendance attendance=afrom.getAttendance();
		int currRole = getCurrRole(request);
		boolean flag=service.updateAttendanceState(currRole,attendance.getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findAttendanceList(mapping,form,request,response);
	}
	
	public ActionForward updateAttendanceSignRecordEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm aform = (AttendanceForm) form;
		int repositoryId=aform.getAttendanceInfo().getRespId();
		int year=aform.getQuery().getYear();
		int month=aform.getQuery().getMonth();
		
		Date MonthStartDay = new Date();
		Date MonthEndDay = new Date();
		GregorianCalendar gc = new GregorianCalendar(year,month-1,1);
		MonthStartDay = gc.getTime();
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));//设置天
		gc.set(Calendar.HOUR_OF_DAY, gc.getActualMaximum(Calendar.HOUR_OF_DAY));//设置小时
		gc.set(Calendar.MINUTE,gc.getActualMaximum(Calendar.MINUTE));//分
		gc.set(Calendar.SECOND,gc.getActualMaximum(Calendar.SECOND));//秒
		MonthEndDay=gc.getTime();
		List<SignRecordBean> signRecordList=service.findAttendanceSignRecordByRepositoryId(repositoryId,MonthStartDay,MonthEndDay);
		request.setAttribute("signRecords", signRecordList);
		request.setAttribute("repositoryId", repositoryId);
		return mapping.findForward("update_signRecord");
	}
	public ActionForward updateAttendanceSignRecordDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm aform = (AttendanceForm) form;
		int repositoryId=aform.getAttendanceInfo().getRespId();
		int year=aform.getQuery().getYear();
		int month=aform.getQuery().getMonth();
		
		Date MonthStartDay = new Date();
		Date MonthEndDay = new Date();
		GregorianCalendar gc = new GregorianCalendar(year,month-1,1);
		MonthStartDay = gc.getTime();
		gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));//设置天
		gc.set(Calendar.HOUR_OF_DAY, gc.getActualMaximum(Calendar.HOUR_OF_DAY));//设置小时
		gc.set(Calendar.MINUTE,gc.getActualMaximum(Calendar.MINUTE));//分
		gc.set(Calendar.SECOND,gc.getActualMaximum(Calendar.SECOND));//秒
		MonthEndDay=gc.getTime();
		List<SignRecordBean> signRecordList=service.findAttendanceSignRecordByRepositoryId(repositoryId,MonthStartDay,MonthEndDay);
		request.setAttribute("signRecords", signRecordList);
		request.setAttribute("repositoryId", repositoryId);
		return mapping.findForward("signRecord_detail");
	}
	/**
	 * 考勤详情页更新
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateAttendanceSignRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	     String json=request.getParameter("signRecord");
		List<SignRecordBean> signRecordList = JSON.parseArray(json,SignRecordBean.class);
		boolean flag=service.updateAttendanceSignRecordList(signRecordList);
	    if(!flag){
	    	return updateAttendanceSignRecordEntry(mapping,form,request,response);
	    }
	   return  findAttendanceList(mapping,  form, request,response);
	}
	
	/**
	 * 考勤列表修改事假,病假,倒休
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int attendanceInfoId = Integer.parseInt(request
				.getParameter("id"));
		String name = request.getParameter("updateKey");
		double value =Double.valueOf(request.getParameter("updateValue"))  ; 
		System.out.println("========修改考勤:"+attendanceInfoId+"/"+name+"/"+value);
		boolean bool =service.updateAttendance(attendanceInfoId,name,value);
		result(response, bool);
		return null;
	}
	
	
	private void result(HttpServletResponse response, boolean result)
			throws IOException {
		PrintWriter out = getWrite(response);
		JSONObject jsonObject = new JSONObject();
		try {
			if (result) {
				jsonObject.put("success", true);
				jsonObject.put("message", "成功");
				out.write(jsonObject.toJSONString());
				out.flush();
				out.close();
			} else {
				jsonObject.put("success", false);
				jsonObject.put("message", "失败");
				out.write(jsonObject.toJSONString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}

	}
	
	
	
	/*
	 * 需求87 --考勤表台账列表
	 * /attendance.do?method=attendanceSheetLedger
	 * @time 20170520
	*/
	public ActionForward attendanceSheetLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		afrom.getAttendance().setId(0);
		AttendanceQueryVO query=afrom.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getApprovalState()==0){
			query.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if(query.getApprovalState()==0){
			query.setApprovalState(-1);
		}
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("attendanceSheetLedger", request);
		tools.saveQueryVO(query);
		List<AttendanceBean> list=service.findAttendanceList(query,tools);
		request.setAttribute("list", list);
		if(list!=null && list.size()>0){
			afrom.getAttendance().setId(list.get(0).getAttendanceId());
			afrom.getAttendance().setState(list.get(0).getAttendanceState());
		}
		request.setAttribute("listSize", list.size());
		setOptions(request);
		return mapping.findForward("attendance_sheet_ledger_list");
	} 
	
	/*
	 * 需求87 --导出考勤表台账
	 * @time 20170520
	*/
	public ActionForward ExtAttendanceSheetLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AttendanceForm afrom = (AttendanceForm) form;
		afrom.getAttendance().setId(0);
		AttendanceQueryVO query=afrom.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getApprovalState()==0){
			query.setApprovalState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if(query.getApprovalState()==0){
			query.setApprovalState(-1);
		}
		List<AttendanceBean> list=service.findAttendanceListToExt(query);
		
		String[] titles ={"序号","员工工号","姓名"," 省 "," 市 ",	"经销商","金融机构","事假（修改前）","事假（修改后）",
				"病假（修改前）","病假（修改后）","迟到","早退","旷工",
				"倒休（修改前）","倒休（修改后）",	"正休（修改前）","正休（修改后）",	"加班（修改前）","加班（修改后）",	"轮岗明细",	"实际出勤",	"全勤",	"备注",	"审批状态"};
		String filename = "考勤表台账";
 		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	AttendanceBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getStaffNo());
	    	
	    	tool.setCellValue(i+1, 2, vo.getName());
	    	
	    	tool.setCellValue(i+1, 3, vo.getProvinceName());
	    	
	    	tool.setCellValue(i+1, 4, vo.getCityName());
	    	
	    	tool.setCellValue(i+1, 5, vo.getDealerNames());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBankNames());
	    	
	    	tool.setCellValue(i+1, 7, vo.getMatterHoliday());
	    	
	    	tool.setCellValue(i+1, 8, vo.getMatterHolidayUpdate());
	    	
	    	tool.setCellValue(i+1, 9, vo.getAilingHoliday());
	    	
	    	tool.setCellValue(i+1, 10, vo.getAilingHolidayUpdate());
	    	
	    	tool.setCellValue(i+1, 11, vo.getLateDay());
	    	
	    	tool.setCellValue(i+1, 12, vo.getEarlyDay());
	    	
	    	tool.setCellValue(i+1, 13, vo.getAbsenteeism());
	    	
	    	tool.setCellValue(i+1, 14, vo.getDaoxiu());
	    	
	    	tool.setCellValue(i+1, 15, vo.getDaoxiuUpdate());
	    	
	    	tool.setCellValue(i+1, 16, vo.getZhengxiu());
	    	
	    	tool.setCellValue(i+1, 17, vo.getZhengxiuUpdate());
	    	
	    	tool.setCellValue(i+1, 18, vo.getOvertime());
	    	
	    	tool.setCellValue(i+1, 19, vo.getOvertimeUpdate());
	    	
	    	tool.setCellValue(i+1, 20, vo.getChangePostInfo());
	    	
	    	tool.setCellValue(i+1, 21, vo.getActualAttendance());
	    	
	    	if (vo.getFullTime() == 1) {
	    		tool.setCellValue(i+1, 22, "是");
			}else if (vo.getFullTime() == 2){
				tool.setCellValue(i+1, 22, "否");
			}
	    	
	    	tool.setCellValue(i+1, 23, vo.getRemark());
	    	
	    	if (vo.getState() == 0) {
	    		tool.setCellValue(i+1, 24, "未提交");
			}else if (vo.getState() == 1) {
	    		tool.setCellValue(i+1, 24, "审批通过");
			}else if (vo.getState() == 2) {
	    		tool.setCellValue(i+1, 24, "审批不通过");
			}else if (vo.getState() == 3) {
	    		tool.setCellValue(i+1, 24, "正在审批");
			}
	    	
	    	tool.allAutoColumnWidth(i);
	    }
		tool.writeStream(response, filename);
		return null;
	}
	
	/*
	 * 需求38 
	 * 导出经销商考勤时间台账 
	*/
	public ActionForward ExtFindDealerList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm ifrom = (AttendanceForm) form;
		DealerQueryVO query = ifrom.getDealerQuery();
		int currClientType = UserSessionUtil.getUserSession(request).getUser().getClient_type();
		query.setCurrClientType(currClientType);
		List<ExtDealerVO> list = dealerService.ExtFindDealerList(query);
		String[] titles = {"序号",	"经销商全称",	"金融机构",	"品牌","	经销商所在省","	经销商所在市",	"经销商具体地址","早上签到","中午签退","下午签到","下午签退","工作日(星期)","考勤制度"};
		String filename = "经销商考勤时间台账";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	ExtDealerVO vo = list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	tool.setCellValue(i+1, 1, vo.getDealerName());
	    	tool.setCellValue(i+1, 2, vo.getBankName());
	    	tool.setCellValue(i+1, 3, vo.getBrandName());
	    	tool.setCellValue(i+1, 4, vo.getProvinceName());
	    	tool.setCellValue(i+1, 5, vo.getCityName());
	    	tool.setCellValue(i+1, 6, vo.getAddress());
	    	tool.setCellValue(i+1, 7, vo.getMorningStart());
	    	tool.setCellValue(i+1, 8, vo.getMorningEnd());
	    	tool.setCellValue(i+1, 9, vo.getAfternoonStart());
	    	tool.setCellValue(i+1, 10, vo.getAfternoonEnd());
	    	tool.setCellValue(i+1, 11, vo.getWorkDays());
	    	tool.setCellValue(i+1, 12, vo.getSystemContent());
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
	
	
	/*
	 * 需求86 -- 每日考勤详情
	*/
	public void setOptionsSp(HttpServletRequest request){
		request.setAttribute("years", OptionUtil.getYears());
		request.setAttribute("months", OptionUtil.getMonths());
		//列表页面审批状态
		request.setAttribute("queryApprovalState", OptionUtil.getSQueryApprovalState());
		
	}
	public ActionForward SignRecordCheckSpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		afrom.getAttendance().setId(0);
		SignRecordCheckSpAll query = afrom.getSquery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		//calendar.add(Calendar.MONTH,0);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getState()==0){
			query.setState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if (query.getState() == 0) {
			query.setState(-2);
		}
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("SignRecordCheckSpList", request);
		tools.saveQueryVO(query);
		List<SignRecordCheckSpAll> list=service.SignRecordCheckSpList(query,tools);
		request.setAttribute("role", currRole);
		request.setAttribute("list", list);
		request.setAttribute("months", OptionUtil.getMonths());
		setOptionsSp(request);
		return mapping.findForward("sign_record_check_sp_list");
	}
	//进入修改页
	public ActionForward updateSignRecordCheckSpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm aform = (AttendanceForm) form;
		int repositoryId=aform.getSquery().getRespId();
		int year=aform.getSquery().getYear();
		int month=aform.getSquery().getMonth();
		List<SignRecordCheckSpAll> list = service.searchSignRecordCheckSpListDetails(repositoryId,year,month);
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("respId", repositoryId);
		return mapping.findForward("update_signRecordCheckSpList");
	}
	
	//查看每日考勤详情
	public ActionForward searchSignRecordCheckSpListDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm aform = (AttendanceForm) form;
		int repositoryId=aform.getSquery().getRespId();
		int year=aform.getSquery().getYear();
		int month=aform.getSquery().getMonth();
		List<SignRecordCheckSpAll> list = service.searchSignRecordCheckSpListDetails(repositoryId,year,month);
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("respId", repositoryId);
		return mapping.findForward("search_signRecordCheckSpList_details");
	}
	
	/*
	 * 需求86 
	 * 导出每日考勤详情
	*/
	public ActionForward ToExtSignRecordCheckSpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		afrom.getAttendance().setId(0);
		SignRecordCheckSpAll query = afrom.getSquery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		//calendar.add(Calendar.MONTH,0);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getState()==0){
			query.setState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if (query.getState() == 0) {
			query.setState(-2);
		}
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("ToExtSignRecordCheckSpList", request);
		tools.saveQueryVO(query);
		List<SignRecordCheckSpAll> list=service.SignRecordCheckSpList(query,tools);
		request.setAttribute("role", currRole);
		request.setAttribute("list", list);
		request.setAttribute("months", OptionUtil.getMonths());
		setOptionsSp(request);
		return mapping.findForward("ToExtSign_record_check_sp_list");
	}
	public ActionForward ExtSignRecordCheckSpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm afrom = (AttendanceForm) form;
		UserService us = new UserService();
		afrom.getAttendance().setId(0);
		SignRecordCheckSpAll query = afrom.getSquery();
		int currRole = getCurrRole(request);
		query.setCurrentRole(currRole);
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH,0);
		int year=calendar.get(Calendar.YEAR);
		int month=calendar.get(Calendar.MONTH);
		if(query.getYear()<=0){
			query.setYear(year);
		}
		if(query.getMonth()<=0){
			query.setMonth(month+1);
		}
		if(currRole==RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode() && query.getState()==0){
			query.setState(ApprovalContant.APPROVAL_WAIT.getCode());
		}else if(query.getState()==0){
			query.setState(-1);
		}
		List<SignRecordCheckSpAll> list=service.ExtSignRecordCheckSpList(query);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] titles = {"序号","姓名",	"员工工号",	"经销商",	"金融机构","	省",	"市",
				"1日",	"2日",	"3日",	"4日",	"5日",	"6日",	"7日",	"8日",	"9日",	"10日",	
				"11日",	"12日",	"13日",	"14日", "15日",	"16日",	"17日",	"18日",	"19日",	"20日",	
				"21日",	"22日",	"23日",	"24日", "25日",	"26日",	"27日",	"28日",	"29日",	"30日",
				"31日","最后修改人"	,"最后修改时间","审批状态","	审批人"	};
		String filename = "每日考勤详情";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	SignRecordCheckSpAll vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getName());
	    	tool.setCellValue(i+1, 2, vo.getStaffNo());
	    	tool.setCellValue(i+1, 3, vo.getDealersName());
	    	tool.setCellValue(i+1, 4, vo.getBanksName());
	    	tool.setCellValue(i+1, 5, vo.getProvinceName());
	    	tool.setCellValue(i+1, 6, vo.getCityName());
	    	tool.setCellValue(i+1, 7, vo.getDays().getDay_one());
	    	tool.setCellValue(i+1, 8, vo.getDays().getDay_two());
	    	tool.setCellValue(i+1, 9, vo.getDays().getDay_three());
	    	tool.setCellValue(i+1, 10, vo.getDays().getDay_four());
	    	tool.setCellValue(i+1, 11, vo.getDays().getDay_five());
	    	tool.setCellValue(i+1, 12, vo.getDays().getDay_six());
	    	tool.setCellValue(i+1, 13, vo.getDays().getDay_seven());
	    	tool.setCellValue(i+1, 14, vo.getDays().getDay_eight());
	    	tool.setCellValue(i+1, 15, vo.getDays().getDay_nine());
	    	tool.setCellValue(i+1, 16, vo.getDays().getDay_ten());
	    	tool.setCellValue(i+1, 17, vo.getDays().getDay_Eleven());
	    	tool.setCellValue(i+1, 18, vo.getDays().getDay_twelve());
	    	tool.setCellValue(i+1, 19, vo.getDays().getDay_thirteen());
	    	tool.setCellValue(i+1, 20, vo.getDays().getDay_fourteen());
	    	tool.setCellValue(i+1, 21, vo.getDays().getDay_fifteen());
	    	tool.setCellValue(i+1, 22, vo.getDays().getDay_sixteen());
	    	tool.setCellValue(i+1, 23, vo.getDays().getDay_seventeen());
	    	tool.setCellValue(i+1, 24, vo.getDays().getDay_eighteen());
	    	tool.setCellValue(i+1, 25, vo.getDays().getDay_nineteen());
	    	tool.setCellValue(i+1, 26, vo.getDays().getDay_twenty());
	    	tool.setCellValue(i+1, 27, vo.getDays().getDay_twentyOne());
	    	tool.setCellValue(i+1, 28, vo.getDays().getDay_twentyTwo());
	    	tool.setCellValue(i+1, 29, vo.getDays().getDay_twentyThree());
	    	tool.setCellValue(i+1, 30, vo.getDays().getDay_twentyFour());
	    	tool.setCellValue(i+1, 31, vo.getDays().getDay_twentyFive());
	    	tool.setCellValue(i+1, 32, vo.getDays().getDay_twentySix());
	    	tool.setCellValue(i+1, 33, vo.getDays().getDay_twentySeven());
	    	tool.setCellValue(i+1, 34, vo.getDays().getDay_twentyEight());
	    	tool.setCellValue(i+1, 35, vo.getDays().getDay_twentyNine());
	    	tool.setCellValue(i+1, 36, vo.getDays().getDay_thirty());
	    	tool.setCellValue(i+1, 37, vo.getDays().getDay_thirtyOne());
	    	if (vo.getUpdateUserId() > 0) {
				UserVO userVO = us.loadUserById(vo.getUpdateUserId());
				tool.setCellValue(i+1, 38, userVO.getUserName());
			}else{
				tool.setCellValue(i+1, 38, "");
			}
	    	if (vo.getUpdateDate() !=null) {
				String updateDate = format.format(vo.getUpdateDate());
				tool.setCellValue(i+1, 39, updateDate);
			}else {
				tool.setCellValue(i+1, 39, "");
			}
	    	if (vo.getState() == 1) {
	    		tool.setCellValue(i+1, 40, "审批通过");
			}else if (vo.getState() == 2) {
	    		tool.setCellValue(i+1, 40, "审批不通过");
			}else if (vo.getState() == 3) {
	    		tool.setCellValue(i+1, 40, "正在审批");
			}else if (vo.getState() == 0) {
	    		tool.setCellValue(i+1, 40, "未提交");
			}else if (vo.getState() == -1) {
	    		tool.setCellValue(i+1, 40, "未通知");
			}
	    	if (vo.getNextApproval() == 9) {
				tool.setCellValue(i+1, 41, "监管员管理部经理");
			}else{
				tool.setCellValue(i+1, 41, "");
			}
	    	
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
		
	}
	//提交 -- 监管员管理部考勤专员
	public ActionForward SubmitSignRecordCheckSpToApprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		AttendanceForm aform = (AttendanceForm) form;
		SignRecordCheckSpAll query = aform.getSquery();
		int repositoryId=query.getRespId();
		int year=query.getYear();
		int month=query.getMonth();
		if (repositoryId>0 && year >0 && month >0) {
			boolean flag = service.SubmitSignRecordCheckSpToApprove(repositoryId,year,month);
			if (flag) {
				//新增-提交后给监管员管理部经理、薪酬专员发送信息提醒
				/*try {
					MessageUtil.sendMsg(new String[]{
							RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
							RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""}, "每日考勤详情",
							"/attendance.do?method=SignRecordCheckSpList", 1,MessageTypeContant.DAILYATTENDANCEDETAIL.getCode(),query.getCreateUserId());
				} catch (Exception e) {
					e.printStackTrace();
				}*/
				try {
					String[] roleIds = new String[]{
							RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
							RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode()+""};
						for (String rid : roleIds) {
						int uid = MessageUtil.getUserId(rid);
								MessageService msgService = new MessageService();
								MessageVO msg = msgService.loadMsgByUserAndType(Integer.valueOf(uid), MessageTypeContant.DEALEREXIT.getCode());
								if(msg != null){
									int name = 1;
									if(StringUtil.isNumber(msg.getName())){
										name = Integer.parseInt(msg.getName())+1;
									}
									msg.setName(name+"");
									msgService.updMessage(msg);
								}else{
									MessageUtil.sendOrUpdateMeg(roleIds,  1+"", 
											"/attendance.do?method=SignRecordCheckSpList", 1,MessageTypeContant.DAILYATTENDANCEDETAIL.getCode(),query.getCreateUserId());
								}
							}
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute(Constants.OPERATE_MESSAGE_SUCCEEDED.getCode(), "提交成功");
			}else {
				request.setAttribute(Constants.OPERATE_MESSAGE_FAILED.getCode(), "提交失败");
			}
		}
		return SignRecordCheckSpList(mapping, aform, request, response);
	}
	
	//进入审批页面  -- 监管员管理部经理
	public ActionForward toApprovalPage(ActionMapping mapping, ActionForm form,			
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		AttendanceForm aform = (AttendanceForm) form;
		int repositoryId=aform.getSquery().getRespId();
		int year=aform.getSquery().getYear();
		int month=aform.getSquery().getMonth();
		List<SignRecordCheckSpAll> list = service.searchSignRecordCheckSpListDetails(repositoryId,year,month);
		request.setAttribute("list", list);
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("respId", repositoryId);
		return mapping.findForward("toApprovalPage");
	}
	//审批通过/不通过 -- 监管员管理部经理
	public ActionForward ApproveSignRecordCheckSpList(ActionMapping mapping, ActionForm form,			
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		AttendanceForm aform = (AttendanceForm) form;
		SignRecordCheckSpAll query = aform.getSquery();
		String iden = request.getParameter("iden");
		if (iden.equals("1")) {
			query.setState(1);
		}else if (iden.equals("2")) {
			query.setState(2);
		}
		boolean flag = service.ApproveSignRecordCheckSpList(query);
		if (flag) {
			request.setAttribute(Constants.OPERATE_MESSAGE_SUCCEEDED.getCode(), "审批成功");
		}else {
			request.setAttribute(Constants.OPERATE_MESSAGE_FAILED.getCode(), "审批失败");
		}
		return SignRecordCheckSpList(mapping, aform, request, response);
	}
	//修改每日考勤详情
	public ActionForward update(ActionMapping mapping, ActionForm form,			
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		String json = request.getParameter("jsonData");
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		JSONArray ja = JSONArray.parseArray(json);
		SignRecord sr = null;
		if(ja.size()==0){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "没有需要修改内容");
			return updateSignRecordCheckSpList(mapping, form, request, response);
		}
		
		
		for(int i = 0; i < ja.size();i++){
			String jsonStr = ja.getString(i);
			JSONObject jo = JSONObject.parseObject(jsonStr);
			
			sr = new SignRecord();
			sr.setId(jo.getIntValue("id"));
			sr.setIsLate(jo.getIntValue("isLate"));
			sr.setIsEarly(jo.getIntValue("isEarly"));
			sr.setIsAbsenteeism(jo.getIntValue("isAbsenteeism"));
			sr.setIsNormal(jo.getIntValue("isNormal"));
			sr.setUpdateUserId(user.getId());
			sr.setState(0);
			sr.setUpdateDate(new Date());
			boolean flag = service.updateSignRecord(sr);
		}
		
		
		
		
		
		
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改成功");
		//return updateSignRecordCheckSpList(mapping, form, request, response);
		return SignRecordCheckSpList(mapping, form, request, response);
	}
	
	//每日考勤详情提交状态			
	public ActionForward doSubmit (ActionMapping mapping, ActionForm form,			
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		AttendanceForm aform = (AttendanceForm) form;		
		SignRecordCheckSpAll bean = aform.getSquery();		
		SignRecordCheckSpAll signrecord= service.getSignRecordCheckSpAllId(bean.getId());		
//		DateUtil.getStringFormatByDate(new Date(), "yyyy-MM-dd");		
				
		signrecord.setState(ApproveStateContants.UNCHECK.getCode());//提交后设置状态为待审核		
		signrecord.setNextApproval(RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode());		
		service.update(signrecord);		
		return searchSignRecordCheckSpListDetails(mapping,form,request,response);		
	}			
	
}
