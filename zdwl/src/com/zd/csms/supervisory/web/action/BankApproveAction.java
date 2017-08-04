package com.zd.csms.supervisory.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.BankApproveQueryVO;
import com.zd.csms.supervisory.querybean.BankApproveQueryBean;
import com.zd.csms.supervisory.service.BankApproveService;
import com.zd.csms.supervisory.web.form.BankApproveForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class BankApproveAction extends ActionSupport {

	private static int[] approvalRole = new int[] {
		RoleConstants.SUPERVISORY.getCode(),
		RoleConstants.BANK_APPROVE.getCode(),
		RoleConstants.BUSINESS_COMMISSIONER.getCode(),
		RoleConstants.BUSINESS_AMALDAR.getCode()
	};
	
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	public ActionForward bankApproveListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return bankApproveList(mapping, form,request, response);
	}
	
	
	public ActionForward bankApproveList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		int currRole = getCurrRole(request);
		BankApproveForm bform = (BankApproveForm)form;
		BankApproveService service = new BankApproveService();
		
		BankApproveQueryVO bQuery = bform.getBankApprovequery();
		
		String type="";
		int code = bQuery.getType();
		String bankApproveType = request.getParameter("bankApproveType");
		if (StringUtil.isNotEmpty(bankApproveType)) {
			code = Integer.parseInt(bankApproveType);
			bQuery.setType(code);
		}
		if(code==2){
			type = "入库";
		}else if(code==3){
			type = "出库";
		}else if(code==4){
			type = "移动";
		}
		
		request.setAttribute("type", type);
		request.setAttribute("bankApproveType", code);
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		if(currRole==RoleConstants.BANK_APPROVE.getCode()){
			bQuery.setClientid(2);
			bQuery.setUserid(user.getClient_id());
		}else if(currRole==RoleConstants.SUPERVISORY.getCode()){
			bQuery.setClientid(1);
			bQuery.setUserid(user.getClient_id());
		}else if(currRole == RoleConstants.BUSINESS_COMMISSIONER.getCode()){
			bQuery.setClientid(3);
			bQuery.setUserid(user.getId());
		}
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("bankApproveList",request);
		
		//按条件查询分页数据
		List<BankApproveQueryBean> list = service.searchBankApproveListByPage(bQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		try {
			request.setAttribute("summary", service.getSummaryByBank(bQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setOptions(request);
		//返回列表页面
		return mapping.findForward("bank_approve_list");
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("bankStateOptions", OptionUtil.bankState());
		request.setAttribute("bankTypeOptions", OptionUtil.bankType());
	}
}
