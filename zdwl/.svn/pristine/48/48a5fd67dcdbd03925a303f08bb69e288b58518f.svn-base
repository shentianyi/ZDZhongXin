package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.bank.model.BankVO;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.ComplaintInfoQueryBean;
import com.zd.csms.business.model.ComplaintInfoQueryVO;
import com.zd.csms.business.model.ComplaintInfoVO;
import com.zd.csms.business.model.ComplaintQueryBean;
import com.zd.csms.business.model.ComplaintQueryVO;
import com.zd.csms.business.model.ComplaintVO;
import com.zd.csms.business.service.ComplaintService;
import com.zd.csms.business.web.form.ComplaintForm;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 投诉记录信息表
 * @author ly
 *
 */

public class ComplaintAction extends ActionSupport {
	
	private ComplaintService service = new ComplaintService();
	private UserService uservice = new UserService();
	private BankService bankService = new BankService();

	/**
	 * 角色:根据不同的部门分开判断 (各部门经理   --- 9,13,16,20,23,25  /  各部门部长   --- 26,27,28,29)
	 */
	private static int[] approvalRole = new int[] {
			
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			RoleConstants.FINANCE_AMALDAR.getCode(),
			
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()
	};
	
	private UserVO gerUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	public ActionForward complaintLedger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoQueryVO query = complaintForm.getQuery();
		//complaintLedger
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//查询条件
		query.setCurrRole(currRole);
		query.setCreateUserId(user.getId());
		List<ComplaintInfoQueryBean> list = service.findListLedger(query, tools);
		tools.saveQueryVO(query);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		request.setAttribute("complaintObjs", OptionUtil.complaintObjs());
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		
		return mapping.findForward("complaint_ledger");
	}
	
	
	public ActionForward complaintDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoVO bean = service.getComplaintInfoVO(complaintForm.getComplaint().getId());
		Integer processingId = bean.getProcessingId();
		if (null != processingId) {
			UserVO userVO = uservice.get(processingId);
			if(null != userVO){
				request.setAttribute("userName", userVO.getUserName());
			}
		}
		initOption(request);
		if(bean.getFinanceId()>0){
			BankVO bank = bankService.get(bean.getFinanceId());
			request.setAttribute("bank", bank);
		}
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		request.setAttribute("reps", OptionUtil.getRepository(RepositoryStatus.ALREADYPOST.getCode()));
		request.setAttribute("telephoneTypes", OptionUtil.getTelephoneTypes());
		request.setAttribute("rosterId", bean.getRosterId());
		request.setAttribute("storeId", bean.getStoreId());
		request.setAttribute("processingIdinit", bean.getProcessingId());
		
		complaintForm.setComplaint(bean);
		
		return mapping.findForward("complaint_detail");
	}

	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {

		request.setAttribute("complaintSorts", OptionUtil.complaintSorts());
		request.setAttribute("processingDepartment", OptionUtil.processingDepartment());
		request.setAttribute("complaintObjs", OptionUtil.complaintObjs());
	}

	/*
	 * 需求38
	 * 投诉记录信息汇总表
	*/
	public ActionForward ExtComplaintLedger (ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		ComplaintForm complaintForm = (ComplaintForm) form;
		ComplaintInfoQueryVO query = complaintForm.getQuery();
		query.setCurrRole(currRole);
		query.setCreateUserId(user.getId());
		List<ComplaintInfoQueryBean> list = service.ExtComplaintLedger(query);
		String[] titles = {"序号",	"创建日期",	"创建时间",	"记录人",	"所属部门",	"来电类型",	"来电人类型",	"来电对象名称",	"状态"};
		String filename = "投诉记录信息汇总表";
		
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	ComplaintInfoQueryBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	if (vo.getCreateDate() !=null) {
				String createDate = format.format(vo.getCreateDate());
				tool.setCellValue(i+1, 1, createDate);
			}else {
				tool.setCellValue(i+1, 1, "");
			}
	    	tool.setCellValue(i+1, 2, vo.getCreateTime());
	    	tool.setCellValue(i+1, 3, vo.getCreateUserName());
	    	tool.setCellValue(i+1, 4, vo.getCreateUserDept());
	    	if (vo.getPhoneType().equals("1")) {
	    		tool.setCellValue(i+1, 5, "业务问题");
			}else if (vo.getPhoneType().equals("2")) {
	    		tool.setCellValue(i+1, 5, "市场问题");
			}else if (vo.getPhoneType().equals("3")) {
	    		tool.setCellValue(i+1, 5, "风控问题");
			}else if (vo.getPhoneType().equals("4")) {
	    		tool.setCellValue(i+1, 5, "投诉监管员");
			}else if (vo.getPhoneType().equals("5")) {
	    		tool.setCellValue(i+1, 5,"投诉总部人员" );
			}else if (vo.getPhoneType().equals("6")) {
	    		tool.setCellValue(i+1, 5, "其他");
			}
	    	
	    	if (vo.getComplaintObjId() == 1) {
	    		tool.setCellValue(i+1, 6, "经销商");
			}else if (vo.getComplaintObjId() == 2) {
	    		tool.setCellValue(i+1, 6, "金融机构");
			}else if (vo.getComplaintObjId() == 3) {
	    		tool.setCellValue(i+1, 6,"监管员" );
			}
	    	tool.setCellValue(i+1, 7, vo.getObjName());
	    	
	    	if (vo.getStatus().equals("1")) {
	    		tool.setCellValue(i+1, 8, "未提交");
			}else if (vo.getStatus().equals("2")) {
	    		tool.setCellValue(i+1, 8, "已发送");
			}else if (vo.getStatus().equals("3")) {
	    		tool.setCellValue(i+1, 8, "已修正");
			}else if (vo.getStatus().equals("4")) {
	    		tool.setCellValue(i+1, 8, "已完成");
			}
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
}
