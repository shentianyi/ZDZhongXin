package com.zd.csms.payment.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.PaymentService;
import com.zd.csms.payment.model.PaymentInfoQueryVO;
import com.zd.csms.payment.model.PaymentVO;
import com.zd.csms.payment.querybean.PaymentInfoQueryBean;
import com.zd.csms.payment.service.PaymentInfoService;
import com.zd.csms.payment.web.excel.PaymentInfoRowMapper;
import com.zd.csms.payment.web.form.PaymentInfoForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.DateUtil;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class PaymentInfoAction extends ActionSupport{
	private DealerService dealerService= new DealerService();
	private PaymentInfoService service = new PaymentInfoService();
	
	private static int[] approvalRole = new int[]{
		RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
		RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
		RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
		RoleConstants.FINANCE_ACCOUNTING.getCode(),
		RoleConstants.FINANCE_AMALDAR.getCode(),
		RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
		RoleConstants.SR.getCode()
	};
	
	private UserVO getUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 薪酬信息记录导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward exportExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int currRole = getCurrRole(request);
		PaymentInfoForm pform = (PaymentInfoForm) form;
		PaymentInfoQueryVO query = pform.getQuery();
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		
		//按条件查询分页数据
		IExportFile export = new ExportFileExcelImpl();
		try {
			List<PaymentInfoQueryBean> list = service.findList(query, tools);
			export.export(list, new PaymentInfoRowMapper(), export.createDefaultFileName("薪酬管理"),"薪酬信息记录", response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回列表页面
		return null;
	}
	
	/**
	 * 薪酬列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PaymentInfoForm pform = (PaymentInfoForm) form;
		PaymentInfoQueryVO query = pform.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		String[] lastDate = getLastDate();
		if(query.getYear() <= 0){
			query.setYear(Integer.parseInt(lastDate[0]));
		}
		if(query.getMonth() <= 0){
			query.setMonth(Integer.parseInt(lastDate[1]));
		}
		
		
		
		int currClientType = UserSessionUtil.getUserSession(request).getUser().getClient_type();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<PaymentInfoQueryBean> list = service.findList(query, tools);

		if(list != null && list.size() > 0){
			pform.getPayment().setId(list.get(0).getPaymentId());
			pform.getPayment().setState(list.get(0).getPayState());//设置薪酬表状态
		}
		
		
	
		
		pform.getPayment().setYear(Integer.parseInt(lastDate[0]));
		pform.getPayment().setMonth(Integer.parseInt(lastDate[1]));
		request.setAttribute("cooperationState", OptionUtil.getCooperationState());
		request.setAttribute("currClientType", currClientType);
		request.setAttribute("list", list);

		setOptions(request);
		return mapping.findForward("paymentInfoList");
	}
	
	/**
	 * 审批列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PaymentInfoForm pform = (PaymentInfoForm) form;
		PaymentInfoQueryVO query = pform.getQuery();
		int currRole = getCurrRole(request);
		query.setCurrRole(currRole);
		String[] lastDate = getLastDate();
		if(query.getYear() <= 0){
			query.setYear(Integer.parseInt(lastDate[0]));
		}
		if(query.getMonth() <= 0){
			query.setMonth(Integer.parseInt(lastDate[1]));
		}
		
	
		
		int currClientType = UserSessionUtil.getUserSession(request).getUser().getClient_type();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		List<PaymentInfoQueryBean> list = service.findList(query, tools);
		
		if(list != null && list.size() > 0){
			pform.getPayment().setId(list.get(0).getPaymentId());
			pform.getPayment().setState(list.get(0).getPayState());//设置薪酬表状态
		}
		
		
		
		
		int currState = 0;
		// 2：经理审核 3：部长审核4：财务审核 5：总监审核 6:审核通过
		switch(currRole){
			case 9 : 
				currState = 2;
				break;
			case 24 :
				currState = 7;
				break;
			case 25 :
				currState = 4;
				break;
			case 28 : 
				currState = 3;
				break;
			case 29 : 
				currState = 5;
				break;
			default:
				currState = 0;
				break;
		}
		PaymentVO payment = null;
		if(list.size()!=0){
			payment = service.getPaymentVOById(list.get(0).getPaymentId());
		}else{
			payment = new PaymentVO();
		}
		
		
		pform.getPayment().setYear(Integer.parseInt(lastDate[0]));
		pform.getPayment().setMonth(Integer.parseInt(lastDate[1]));
		request.setAttribute("currRole", currRole);
		request.setAttribute("cooperationState", OptionUtil.getCooperationState());
		request.setAttribute("currClientType", currClientType);
		request.setAttribute("list", list);
		
		
		request.setAttribute("currState", currState);
		request.setAttribute("payment", payment);
		setOptions(request);
		return mapping.findForward("paymentInfoListApproval");
	}
	
	/**
	 * 薪酬专员提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PaymentInfoForm pform = (PaymentInfoForm) form;
		PaymentVO payment = pform.getPayment();
		int currRole = getCurrRole(request);
		service.updatePaymentState(currRole,payment.getId());//更新状态
//		service.updatePaymentInfoState(currRole,payment.getId());//更新薪酬表审批状态
		//将执行结果及消息设置到request为页面处理使用
		//request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping,form,request,response);
	}
	
	/**
	 * 审批人员提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward submitApprove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PaymentInfoForm pform = (PaymentInfoForm) form;
		PaymentVO payment = pform.getPayment();
		int currRole = getCurrRole(request);
		
		boolean flag = service.updateApprovalState(currRole, payment.getId(), request);//更新薪酬表审批状态
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findListApproval(mapping,form,request,response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("years", OptionUtil.getYears());
		request.setAttribute("months", OptionUtil.getMonths());
		//列表页面审批状态
		request.setAttribute("approvalStates", OptionUtil.getApprovalStates());
		request.setAttribute("cityTypes", OptionUtil.getCityType());
		
	}
	
	/**
	 * 获取 年-月
	 * @return
	 */
	private String[] getLastDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date nowDate = new Date();
		String[] dates = null;
		String date = sdf.format(DateUtil.getLastDate(nowDate));
		if (StringUtil.isNotEmpty(date)) {
			dates = date.split("-");
		}
		return dates;
	}
	
}
