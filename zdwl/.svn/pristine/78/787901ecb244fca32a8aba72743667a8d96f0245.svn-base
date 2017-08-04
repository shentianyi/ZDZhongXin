package com.zd.csms.supervisory.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.AbnormalQueryBean;
import com.zd.csms.supervisory.model.AbnormalQueryVO;
import com.zd.csms.supervisory.model.AbnormalVO;
import com.zd.csms.supervisory.service.AbnormalService;
import com.zd.csms.supervisory.web.excel.AbnormalRowMapper;
import com.zd.csms.supervisory.web.form.AbnormalForm;
import com.zd.tools.StringUtil;
import com.zd.tools.file.importFile.IImportFile;
import com.zd.tools.file.importFile.impl.ImportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 异常事件/异常数据
 *
 */
public class AbnormalAction extends ActionSupport{
	private AbnormalService service = new AbnormalService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	private DealerService dealerService=new DealerService();
	private UserService userService=new UserService();
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORY.getCode(), //监管员
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),//业务专员
			RoleConstants.BUSINESS_AMALDAR.getCode(),//业务经理
			RoleConstants.BANK_APPROVE.getCode(),//银行
			RoleConstants.BRANDMASTER.getCode(),//品牌集团
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),
			RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_INTERNAL.getCode()};

	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}

	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		initOption(request);
		request.setAttribute("dealers", OptionUtil.getDealersByAbnormal(user.getClient_id()));
		request.setAttribute("business", OptionUtil.getBusinessByAbnormal(user.getClient_id()));
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserSession user = UserSessionUtil.getUserSession(request);
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = abnormalForm.getAbnormal();
		bean.setCreateUser(user.getUser().getId());
		bean.setCreateDate(new Date());
		boolean flag=service.add(bean);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
				
		return findList(mapping, abnormalForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 权限不足
			return mapping.findForward("");
		}
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = service.get(abnormalForm.getAbnormal().getId());
		UserVO user = userService.get(bean.getBusiness());
		abnormalForm.setAbnormal(bean);
		AbnormalQueryVO query = abnormalForm.getQuery();
		query.setCurrRole(currRole);
		initOption(request);
		
		request.setAttribute("dealer", dealerService.loadDealerById(bean.getDealerId()));
		request.setAttribute("userName", user.getUserName());
		return mapping.findForward("update");
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);

		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = abnormalForm.getAbnormal();
		boolean flag=service.update(bean, currRole);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping, abnormalForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = abnormalForm.getAbnormal();
		boolean flag=service.delete(bean);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping, abnormalForm, request, response);
	}

	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}
		UserSession user= UserSessionUtil.getUserSession(request);
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalQueryVO query = abnormalForm.getQuery();
		query.setUser(user.getUser());
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		List<AbnormalQueryBean> list = service.findList(query, tools);

		request.setAttribute("list", list);
		if (pageType == 1)
			return mapping.findForward("list_wait_approval");
		else
			return mapping.findForward("list_already_approval");
	}

	/**
	 * 跳转到流程页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = service.get(abnormalForm.getAbnormal().getId());
		abnormalForm.setAbnormal(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALEREXIT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		return mapping.findForward("approval");
	}



	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {

	}

	/**
	 * 申请单提交操作，提交后禁止修改和删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AbnormalForm abnormalForm = (AbnormalForm) form;
		int id = abnormalForm.getAbnormal().getId();
		boolean flag=service.submit(id);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findList(mapping, form, request, response);
	}

	/**
	 * 详情页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		AbnormalForm abnormalForm = (AbnormalForm) form;
		AbnormalVO bean = service.get(abnormalForm.getAbnormal().getId());

		abnormalForm.setAbnormal(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALEREXIT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		return mapping.findForward("detailPage");
	}
	
	public ActionForward importExcelEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("import_abnormal");
	}
	
	public ActionForward importExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		AbnormalForm aForm = (AbnormalForm) form;
		
		FormFile file =  aForm.getExcelfile();
		
		IImportFile importFile = new ImportFileExcelImpl(file.getInputStream(), 0);
		
		List<AbnormalVO> list = (List<AbnormalVO>) importFile.readAll(1,new AbnormalRowMapper());
		for (AbnormalVO vo : list) {
			if(!StringUtil.isEmpty(vo.getTotalStock())){
				UserVO user = UserSessionUtil.getUserSession(request).getUser();
				vo.setCreateUser(user.getId());
				service.add(vo);
			}
		}
		
		return mapping.findForward("list_wait_approval");
	}

	
}
