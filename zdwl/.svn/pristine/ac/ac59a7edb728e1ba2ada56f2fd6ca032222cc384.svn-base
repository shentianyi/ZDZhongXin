package com.zd.csms.attendance.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.attendance.model.AttendanceQueryVO;
import com.zd.csms.attendance.model.AttendanceTime;
import com.zd.csms.attendance.model.SignRecord;
import com.zd.csms.attendance.querybean.AttendanceTimeQueryBean;
import com.zd.csms.attendance.service.AttendanceService;
import com.zd.csms.attendance.web.form.AttendanceForm;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AttendanceSignAction extends ActionSupport{
	private AttendanceService service  = new AttendanceService();
	private DealerService dealerService= new DealerService();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 

	private UserVO gerUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	
	/**
	 * 考勤签到页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setAttendanceSign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		UserVO userVO = gerUserVO(request);//取当前登录用户
		List<AttendanceTimeQueryBean> attendanceTimeVOList = null;
		int userId = 0;
		int respId = 0;
		AttendanceTimeQueryBean attendanceTimeQueryBean = null;
		if (null != userVO) {
			userId = userVO.getId();//当前用户Id
			respId = userVO.getClient_id();//监管用储备库Id
			//查询当前用户所对应经销商的签到时间
			attendanceTimeVOList = service.getAttendanceTimeByUserId(userId);
			for (AttendanceTimeQueryBean bean : attendanceTimeVOList) {
				if (null != bean.getAfternoonStart() && 
						null != bean.getAfternoonStart() && 
						null != bean.getAfternoonStart() && 
						null != bean.getAfternoonStart()) {
					attendanceTimeQueryBean = bean;
					break;
				}
			}
			request.setAttribute("userId", userId);
		}
		String currTime = request.getParameter("currTime");
		Date date=null;
		if(currTime==null){
			date = new Date();
		}else{
			date = DateUtil.getDateFormatByString(currTime, "yyyy-MM-dd HH:mm");//Test专用
			
		}
		request.setAttribute("currTime", date);
		//查询当天签到记录
		SignRecord signRecord = service.getSignRecordByUserId(date,respId);
		if (null != attendanceTimeQueryBean) {
			if (StringUtil.isNotEmpty(attendanceTimeQueryBean.getWorkDays())) {
				request.setAttribute("workDays", attendanceTimeQueryBean.getWorkDays());
			}
			request.setAttribute("attendance", attendanceTimeQueryBean);
		}else{
			request.setAttribute("message", "您所在经销商未设置签到时间");
			request.setAttribute("flag", 2);
		}
		if (null != signRecord) {
			if (null != signRecord.getMorningStart()) {
				request.setAttribute("signMorningStart", sdf.format(signRecord.getMorningStart()));
			}
			if (null != signRecord.getMorningEnd()) {
				request.setAttribute("signMorningEnd", sdf.format(signRecord.getMorningEnd()));
			}
			if (null != signRecord.getAfternoonStart()) {
				request.setAttribute("signAfternoonStart", sdf.format(signRecord.getAfternoonStart()));
			}
			if (null != signRecord.getAfternoonEnd()) {
				request.setAttribute("signAfternoonEnd", sdf.format(signRecord.getAfternoonEnd()));
			}
			request.setAttribute("signRecord", signRecord);
			request.setAttribute("flag", 1);
		}
		request.setAttribute("respId", userVO.getClient_id());
		return mapping.findForward("attendanceSign");
	}
	
	/**
	 * 经销商考勤时间
	 */
	public ActionForward attendanceTime(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
				AttendanceForm attfrom=(AttendanceForm) form;
			  int dealerId = attfrom.getDealerQuery().getId();
			 IThumbPageTools tools = ToolsFactory.getThumbPageTools("dealerList", request);
			 tools.setPageSize(10);
				List<AttendanceTime> list = service.getAttendanceTimes(dealerId,tools);
				request.setAttribute("list",list);
				request.setAttribute("dealers",OptionUtil.getDealers());
		return mapping.findForward("dealer_attendanceTime");
	
	}
	
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
		//return updateAttendanceTime(mapping, iform, request, response);
		return attendanceTime(mapping, iform, request, response);
	}
	//进入修改页
	public ActionForward updateAttendanceTime(ActionMapping mapping, ActionForm form,
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
}
