package com.zd.csms.marketing.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.contants.DealerContant;
import com.zd.csms.marketing.model.DealerExitQueryVO;
import com.zd.csms.marketing.model.DealerExitVO;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.DealerExitQueryBean;
import com.zd.csms.marketing.querybean.SupervisorQueryBean;
import com.zd.csms.marketing.service.DealerExitService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.form.DealerExitForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.supervisorymanagement.contants.AssetsTypeContant;
import com.zd.csms.supervisorymanagement.model.FixedAssetsQueryVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

/**
 * 经销商撤店信息流转
 * 
 * @author licheng
 *
 */
public class DealerExitAction extends ActionSupport {
	DealerExitService service = new DealerExitService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	RepositoryService repositoryService = new RepositoryService();
	DealerSupervisorService dealerSupervisorService = new DealerSupervisorService();
	FixedAssetsService fixedAssetsService = new FixedAssetsService();
	/**
	 * 旧流程角色:市场部专员-市场部经理、市场部部长-财务部会计-财务部经理-监管员管理部所有专员-监管员管理部经理-业务部专员-业务部经理-
	 * 运营管理部部长-物流金融中心总监
	 * 
	 * 新流程角色：市场专员（发起人）-市场经理（审批人）-市场部长（审批人）-财务会计（审批人）-
	 * 财务经理（审批人）-业务专员（审批人）-综合专员（审批人）
	 */
	/*private static int[] approvalRole = new int[] { RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(), RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
			RoleConstants.FINANCE_ACCOUNTING.getCode(), RoleConstants.FINANCE_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(), RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(), RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(), RoleConstants.SR.getCode() };
*/
	private static int[] approvalRole = new int[] { RoleConstants.MARKET_COMMISSIONER.getCode(),
		RoleConstants.MARKET_AMALDAR.getCode(), RoleConstants.MARKETMANAGEMENT_MINISTER.getCode(),
		RoleConstants.FINANCE_ACCOUNTING.getCode(), RoleConstants.FINANCE_AMALDAR.getCode(),
		RoleConstants.BUSINESS_COMMISSIONER.getCode(),RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
		RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),RoleConstants.BUSINESS_AMALDAR.getCode(),
		RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
		RoleConstants.SR.getCode() };

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
		initOption(request);
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = deForm.getDe();
		bean.setIsRefundByMarket(2);
		request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = deForm.getDe();
		bean.setCreateUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		bean.setCreateDate(new Date());
		//设置监管员信息
		DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
		dsQuery.setDealerId(bean.getDealerId());
		List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
		if(dsList!=null&&dsList.size()>0){
			bean.setSupervisorId(dsList.get(0).getRepositoryId());//设置储备库Id
			bean.setQq(dsList.get(0).getQq());
			bean.setPwd(dsList.get(0).getQqPwd());
		}
		
		service.add(bean);
		return findList(mapping, deForm, request, response);
	}

	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 权限不足
			return mapping.findForward("");
		}
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = service.get(deForm.getDe().getId());
		deForm.setDe(bean);
		initOption(request);

		if ((RoleConstants.MARKET_COMMISSIONER.getCode() == currRole || currRole == RoleConstants.SR.getCode())
				&& (bean.getApprovalState() == ApprovalContant.APPROVAL_NOT_SUBMIT.getCode()
						|| bean.getApprovalState() == ApprovalContant.APPROVAL_NOPASS.getCode())) {
			request.setAttribute("dealers", OptionUtil.getDealers(DealerContant.COOPERATIONSTATE_IN.getCode()));
			return mapping.findForward("update");
		}

		if ((RoleConstants.FINANCE_ACCOUNTING.getCode() == currRole || currRole == RoleConstants.SR.getCode())
				&& bean.getNextApproval() == RoleConstants.FINANCE_ACCOUNTING.getCode()) {
			bean.setIsRefundByFinance(2);
			if(bean.getIsArrears()==0)
				bean.setIsArrears(1);
			return mapping.findForward("update_cw");
		}
		if ((RoleConstants.BUSINESS_COMMISSIONER.getCode() == currRole || currRole == RoleConstants.SR.getCode())
				&& bean.getNextApproval() == RoleConstants.BUSINESS_COMMISSIONER.getCode()) {
			bean.setEndMode(1);
			bean.setIsBusinessEnd(1);
			if(StringUtil.isEmpty(bean.getBusinessCommissioner()))
				bean.setBusinessCommissioner(UserSessionUtil.getUserSession(request).getUser().getUserName());
			return mapping.findForward("update_yw");
		}
		if ((RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode() == currRole
				|| currRole == RoleConstants.SR.getCode())
				&& bean.getNextApproval() == RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode()) {
			setSupervisorInfo(bean, request);
			return mapping.findForward("update_zp");
		}

		return null;
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);

		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = deForm.getDe();
		bean.setLastModifyDate(new Date());
		bean.setLastModifyUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		service.update(bean, currRole);
		return findList(mapping, deForm, request, response);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = deForm.getDe();
		service.delete(bean);
		return findList(mapping, deForm, request, response);
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

		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitQueryVO query = deForm.getQuery();
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		query.setPageType(pageType);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<DealerExitQueryBean> list = service.findList(query, tools);

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
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitQueryVO query = deForm.getQuery();
		query.setCurrRole(getCurrRole(request));
		DealerExitVO bean = service.get(deForm.getDe().getId());
		deForm.setDe(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALEREXIT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setSupervisorInfo(bean, request);
		return mapping.findForward("approval");
	}

	/**
	 * 流程控制
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		if (currRole == -1) {
			// 没有权限
			return mapping.findForward("");
		}
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = deForm.getDe();
		UserSession user = UserSessionUtil.getUserSession(request);
		DealerExitQueryVO query = deForm.getQuery();
		query.setCurrRole(currRole);

		service.approval(user, query, bean);
		//query.setPageType(2);
		return findList(mapping, deForm, request, response);
	}

	/**
	 * 初始化参数
	 * 
	 * @param request
	 */
	private void initOption(HttpServletRequest request) {

		request.setAttribute("yesorno", OptionUtil.yesorno());
		request.setAttribute("endMode", OptionUtil.getEndMode());
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
		DealerExitForm deForm = (DealerExitForm) form;
		int id = deForm.getDe().getId();
		service.submit(id);
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
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = service.get(deForm.getDe().getId());
		deForm.setDe(bean);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALEREXIT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setSupervisorInfo(bean, request);
		return mapping.findForward("detailPage");
	}

	private void setSupervisorInfo(DealerExitVO exit,HttpServletRequest request) throws Exception {
		int repositorId = exit.getSupervisorId();
		if (repositorId > 0) {
			SupervisorQueryBean supervisor = service.getSupervisor(repositorId);
			if (supervisor != null) {
				supervisor.setQq(exit.getQq());
				supervisor.setQqPwd(exit.getPwd());
				FixedAssetsQueryVO fixQuery = new FixedAssetsQueryVO();
				fixQuery.setSendee(supervisor.getSupervisorBasicId());
				List<FixedAssetsVO> fixedList = fixedAssetsService.searchFixedAssets(fixQuery);
				for (FixedAssetsVO fa : fixedList) {
					if (fa.getAsset_type() == AssetsTypeContant.ELECTRONICS.getCode()) {
						supervisor.setComputerBrand(fa.getBrand());
						supervisor.setComputerModel(fa.getModel());
					} else if (fa.getAsset_type() == AssetsTypeContant.WORK.getCode()) {
						supervisor.setSafeBrand(fa.getBrand());
						supervisor.setSafeModel(fa.getModel());
						supervisor.setKey(fa.getKey());
					} else if (fa.getAsset_type() == AssetsTypeContant.OTHER.getCode()) {
						supervisor.setOther(fa.getAsset_name());
						supervisor.setSaveAddress(fa.getAddress());
					}
				}
			}
			request.setAttribute("rep", supervisor);
		}
	}
	
	public ActionForward DealerWithdrawalLedger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitQueryVO query = deForm.getQuery();

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("DealerWithdrawalLedger", request);
		query.setCurrUserId(UserSessionUtil.getUserSession(request).getUser().getId());
		List<DealerExitQueryBean> list = service.DealerWithdrawalLedger(query, tools);

		request.setAttribute("list", list);
		return mapping.findForward("DealerWithdrawalLedger");
	}
	
	public ActionForward detailLedgerPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		initOption(request);
		DealerExitForm deForm = (DealerExitForm) form;
		DealerExitVO bean = service.get(deForm.getDe().getId());
		deForm.setDe(bean);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DEALEREXIT.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		setSupervisorInfo(bean, request);
		return mapping.findForward("detailLedgerPage");
	}
}
