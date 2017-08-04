package com.zd.csms.supervisory.web.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.RepairCostQueryVO;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.csms.supervisory.service.RepairCostService;
import com.zd.csms.supervisory.web.form.RepairCostForm;
import com.zd.csms.supervisorymanagement.model.FixedAssetListVO;
import com.zd.csms.supervisorymanagement.model.FixedAssetsVO;
import com.zd.csms.supervisorymanagement.model.MailCostJsonVO;
import com.zd.csms.supervisorymanagement.service.FixedAssetListService;
import com.zd.csms.supervisorymanagement.service.FixedAssetsService;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RepairCostAction extends ActionSupport {

	private RepairCostService service = new RepairCostService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();

	/**
	 * 流程角色:监管员-业务专员
	 */
	private static int[] approvalRole = new int[] {
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SR.getCode() };

	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 * @throws IOException
	 */
	private int getCurrRole(HttpServletRequest request,
			HttpServletResponse response) {
		return RoleUtil.getCurrRole(approvalRole, request, response);
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
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int currRole = getCurrRole(request, response);

		RepairCostForm rform = (RepairCostForm) form;
		RepairCostQueryVO query = rform.getRepaircostquery();
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		query.setPageType(pageType);

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("findList",
				request);
		tools.saveQueryVO(query);
		query.setPromoter(UserSessionUtil.getUserSession(request).getUser()
				.getId());
		List<RepairCostVO> list = service.searchRepairCostListByPage(query,
				tools);

		request.setAttribute("list", list);
		if (pageType == 1) {
			return mapping.findForward("list_wait_approval");
		} else {
			return mapping.findForward("list_already_approval");
		}
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
	public ActionForward preApproval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RepairCostForm rform = (RepairCostForm) form;
		RepairCostVO bean = service.loadRepairCostById(rform.getRepaircost()
				.getId());
		rform.setRepaircost(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.REPAIRCOST.getCode());
		request.setAttribute("approvals",
				approvalService.findListByApprovalType(approvalQuery));
		UserService us = new UserService();
		UserVO user = us.loadUserById(bean.getPromoter());
		request.setAttribute("username", user.getUserName());
		request.setAttribute("repositoryId", user.getClient_id());
		List<OptionObject> fixedAssetsOptions = OptionUtil
				.fixedAssetsOptions(user.getId());
		request.setAttribute("fixedAssetsOptions", fixedAssetsOptions);
		
				// 查询历史记录
				RepairCostVO rvo = rform.getRepaircost();
				RepairCostQueryVO query = rform.getRepaircostquery();
				query.setCurrRole(-1);
				query.setPageType(-1);
				IThumbPageTools tools = ToolsFactory.getThumbPageTools(
						"findHistoryList", request);
				tools.saveQueryVO(query);

				query.setRepair_project(rvo.getRepair_project());
				query.setPromoter(user.getId());

				// 查询维修记录
				List<RepairCostVO> list = service.repairProjectHistoricalRecord(query,tools);
				if(list==null){
					list = new ArrayList<RepairCostVO>();
				}
				request.setAttribute("list", list);
				// 查询资产基础信息
				FixedAssetsVO fixedAssets = null;
				try {
					fixedAssets = new FixedAssetsService()
							.loadFixedAssetsById(StringUtil.intValue(
									rvo.getRepair_project(), -1));

					if (fixedAssets == null || fixedAssets.getId() == 0) {
						// 返回新增页面
						return mapping.findForward("approval");
					}
					// 查询对象使用资产列表
					List<FixedAssetListVO> fixedassetlistvoList = new FixedAssetListService()
							.searchfixedAssetListByClientId(user.getClient_id());
					for (FixedAssetListVO vo : fixedassetlistvoList) {

						if (vo.getFid() == fixedAssets.getId()) {
							request.setAttribute("asset_num",
									fixedAssets.getAsset_num());
							request.setAttribute("useTime", vo.getDeposit_time());
							break;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
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
	public ActionForward approval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int currRole = getCurrRole(request, response);
		RepairCostForm rform = (RepairCostForm) form;
		RepairCostQueryVO query = rform.getRepaircostquery();
		query.setCurrRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag = service.approval(query, user);
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
				service.getExecuteMessage());

		return findList(mapping, form, request, response);
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RepairCostForm rform = (RepairCostForm) form;
		int id = rform.getRepaircost().getId();

		RepairCostVO bean = service.loadRepairCostById(rform.getRepaircost()
				.getId());
		rform.setRepaircost(bean);

		// 封装设备提醒对象信息
		UserService us = new UserService();
		UserVO user = us.loadUserById(bean.getPromoter());
		// userId
		int userId = user.getId();
		// 储备库Id
		RepositoryService sepositoryService = new RepositoryService();
		RepositoryVO repositoryVO = sepositoryService.load(user.getClient_id());
		// 监管员的Id
		int supervisorId = repositoryVO.getSupervisorId();
		// 工号
		String gh = getON(repositoryVO.getId());
		// 经销商，金融机构
		MailCostJsonVO mJsonVO = getBankAndMerchant(repositoryVO.getId());
		// 设备申请提醒信息
		SupervisorRepairCostMessageVO srCostVO = new SupervisorRepairCostMessageVO();
		srCostVO.setIsread(MsgIsContants.NOREAD.getCode());
		srCostVO.setJobnumber(gh);
		srCostVO.setMerchantname(mJsonVO.getJxs());
		srCostVO.setBankname(mJsonVO.getJrjg());
		srCostVO.setName(user.getUserName());
		srCostVO.setSupervisorid(supervisorId);
		srCostVO.setCost(bean.getMoney());
		srCostVO.setMaintenanceitems(bean.getRepair_project());
		srCostVO.setMessagetype(MessageTypeContant.SUPSERVISREPAIRCOST
				.getName());

		// boolean flag=service.submit(id);

		boolean flag = service.submitAndAddRepairCost(id, srCostVO);
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
				service.getExecuteMessage());

		return findList(mapping, form, request, response);
	}

	public String getON(int supervisorId) {
		RosterService rservice = new RosterService();
		RosterQueryVO rqvo = new RosterQueryVO();
		rqvo.setRepositoryId(supervisorId);
		List<RosterVO> rList = rservice.searchRosterList(rqvo);

		// 员工工号
		String gh = "";
		if (rList != null && rList.size() > 0) {
			RosterVO rvo = rList.get(0);
			gh = rvo.getStaffNo();
		}
		return gh;

	}

	public MailCostJsonVO getBankAndMerchant(int repositoryId) throws Exception {
		String jxs = "";
		String jrjg = "";
		DealerSupervisorService dsservice = new DealerSupervisorService();
		DealerService dservice = new DealerService();
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		dsquery.setSupervisorId(repositoryId);
		List<DealerSupervisorVO> dsList = dsservice
				.searchDealerSupervisorList(dsquery);
		if (dsList != null && dsList.size() > 0) {
			for (DealerSupervisorVO ds : dsList) {
				DealerVO dvo = dservice.loadDealerById(ds.getDealerId());
				if (dvo != null) {
					jxs = jxs + " " + dvo.getDealerName();
				}
				jrjg = jrjg + " "
						+ dservice.getBankNameByDealerId(ds.getDealerId());
			}
		}

		MailCostJsonVO mcvo = new MailCostJsonVO();
		mcvo.setJxs(jxs);
		mcvo.setJrjg(jrjg);

		return mcvo;
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
	public ActionForward detailPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RepairCostForm rform = (RepairCostForm) form;
		RepairCostVO bean = service.loadRepairCostById(rform.getRepaircost()
				.getId());

		rform.setRepaircost(bean);

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.REPAIRCOST.getCode());
		request.setAttribute("approvals",
				approvalService.findListByApprovalType(approvalQuery));
		UserService us = new UserService();
		UserVO user = us.loadUserById(bean.getPromoter());
		request.setAttribute("username", user.getUserName());
		request.setAttribute("repositoryId", user.getClient_id());
		List<OptionObject> fixedAssetsOptions = OptionUtil
				.fixedAssetsOptions(user.getId());
		request.setAttribute("fixedAssetsOptions", fixedAssetsOptions);
		
				// 查询历史记录
				RepairCostVO rvo = rform.getRepaircost();
				RepairCostQueryVO query = rform.getRepaircostquery();
				query.setCurrRole(-1);
				query.setPageType(-1);
				IThumbPageTools tools = ToolsFactory.getThumbPageTools(
						"findHistoryList", request);
				tools.saveQueryVO(query);

				query.setRepair_project(rvo.getRepair_project());
				query.setPromoter(user.getId());

				// 查询维修记录
				List<RepairCostVO> list = service.repairProjectHistoricalRecord(query,tools);
				if(list==null){
					list = new ArrayList<RepairCostVO>();
				}
				request.setAttribute("list", list);
				// 查询资产基础信息
				FixedAssetsVO fixedAssets = null;
				try {
					fixedAssets = new FixedAssetsService()
							.loadFixedAssetsById(StringUtil.intValue(
									rvo.getRepair_project(), -1));

					if (fixedAssets == null || fixedAssets.getId() == 0) {
						// 返回新增页面
						return mapping.findForward("detailPage");
					}
					// 查询对象使用资产列表
					List<FixedAssetListVO> fixedassetlistvoList = new FixedAssetListService()
							.searchfixedAssetListByClientId(user.getClient_id());
					for (FixedAssetListVO vo : fixedassetlistvoList) {

						if (vo.getFid() == fixedAssets.getId()) {
							request.setAttribute("asset_num",
									fixedAssets.getAsset_num());
							request.setAttribute("useTime", vo.getDeposit_time());
							break;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
		return mapping.findForward("detailPage");
	}

	public ActionForward addRepairCostEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		request.setAttribute("repositoryId", user.getClient_id());
		request.setAttribute("username", user.getUserName());
		List<OptionObject> fixedAssetsOptions = OptionUtil
				.fixedAssetsOptions(user.getId());
		request.setAttribute("fixedAssetsOptions", fixedAssetsOptions);

		// 查询历史记录
		RepairCostForm rform = (RepairCostForm) form;

		RepairCostVO rvo = rform.getRepaircost();
		RepairCostQueryVO query = rform.getRepaircostquery();
		query.setCurrRole(-1);
		query.setPageType(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools(
				"findHistoryList", request);
		tools.saveQueryVO(query);

		query.setRepair_project(rvo.getRepair_project());
		query.setPromoter(user.getId());

		// 查询维修记录
		List<RepairCostVO> list = service.repairProjectHistoricalRecord(query,tools);
		request.setAttribute("list", list);
		// 查询资产基础信息
		FixedAssetsVO fixedAssets = null;
		try {
			fixedAssets = new FixedAssetsService()
					.loadFixedAssetsById(StringUtil.intValue(
							rvo.getRepair_project(), -1));

			if (fixedAssets == null || fixedAssets.getId() == 0) {
				// 返回新增页面
				return mapping.findForward("add_repair_cost");
			}
			// 查询对象使用资产列表
			List<FixedAssetListVO> fixedassetlistvoList = new FixedAssetListService()
					.searchfixedAssetListByClientId(user.getClient_id());
			for (FixedAssetListVO vo : fixedassetlistvoList) {

				if (vo.getFid() == fixedAssets.getId()) {
					request.setAttribute("asset_num",
							fixedAssets.getAsset_num());
					request.setAttribute("useTime", vo.getDeposit_time());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回新增页面
		return mapping.findForward("add_repair_cost");
	}

	public ActionForward addRepairCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RepairCostForm rform = (RepairCostForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		RepairCostVO rvo = rform.getRepaircost();
		rvo.setPromoter(user.getId());
		rvo.setCredatetime(new Date());
		rvo.setCreateuserid(user.getId());
		rvo.setCreatedate(new Date());
		// 执行新增操作并获取操作结果
		boolean flag = service.addRepairCost(rvo);

		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
				service.getExecuteMessage());

		if (flag) {
			// 新增成功时返回列表页面
			return findList(mapping, form, request, response);
		}

		// 新增失败时返回新增页面
		return mapping.findForward("add_repair_cost");
	}

	public ActionForward updRepairCostEntry(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RepairCostForm rform = (RepairCostForm) form;

		// 根据id获取修改对象
		RepairCostVO vo = service.loadRepairCostById(rform.getRepaircost()
				.getId());

		UserService us = new UserService();
		UserVO user = us.loadUserById(vo.getPromoter());
		request.setAttribute("username", user.getUserName());
		request.setAttribute("repositoryId", user.getClient_id());

		// 对象不存在时返回列表页
		if (vo == null) {
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return findList(mapping, form, request, response);
		}
		rform.setRepaircost(vo);
		List<OptionObject> fixedAssetsOptions = OptionUtil
				.fixedAssetsOptions(user.getId());
		request.setAttribute("fixedAssetsOptions", fixedAssetsOptions);

		// 查询历史记录
		RepairCostVO rvo = rform.getRepaircost();
		RepairCostQueryVO query = rform.getRepaircostquery();
		query.setCurrRole(-1);
		query.setPageType(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools(
				"findHistoryList", request);
		tools.saveQueryVO(query);

		query.setRepair_project(rvo.getRepair_project());
		query.setPromoter(user.getId());

		// 查询维修记录
		List<RepairCostVO> list = service.repairProjectHistoricalRecord(query,tools);
		if(list==null){
			list = new ArrayList<RepairCostVO>();
		}
		request.setAttribute("list", list);
		// 查询资产基础信息
		FixedAssetsVO fixedAssets = null;
		try {
			fixedAssets = new FixedAssetsService()
					.loadFixedAssetsById(StringUtil.intValue(
							rvo.getRepair_project(), -1));

			if (fixedAssets == null || fixedAssets.getId() == 0) {
				// 返回新增页面
				return mapping.findForward("upd_repair_cost");
			}
			// 查询对象使用资产列表
			List<FixedAssetListVO> fixedassetlistvoList = new FixedAssetListService()
					.searchfixedAssetListByClientId(user.getClient_id());
			for (FixedAssetListVO fixedassetlist : fixedassetlistvoList) {

				if (fixedassetlist.getFid() == fixedAssets.getId()) {
					request.setAttribute("asset_num",
							fixedAssets.getAsset_num());
					request.setAttribute("useTime", fixedassetlist.getDeposit_time());
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 返回修改页面
		return mapping.findForward("upd_repair_cost");
	}

	public ActionForward updRepairCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RepairCostForm rform = (RepairCostForm) form;

		RepairCostVO rvo = rform.getRepaircost();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		rvo.setUpduserid(user.getId());
		rvo.setUpddate(new Date());

		// 执行修改操作并获取操作结果
		boolean flag = service.updRepairCost(rform.getRepaircost());
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
				service.getExecuteMessage());

		if (flag) {
			// 操作成功时返回列表页面
			return findList(mapping, form, request, response);
		}

		// 操作失败时返回修改 页面
		return mapping.findForward("upd_repair_cost");
	}

	public ActionForward delRepairCost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RepairCostForm rform = (RepairCostForm) form;

		// 执行删除操作并获取操作结果
		boolean flag = service.deleteRepairCost(rform.getRepaircost().getId());
		// 将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(),
				service.getExecuteMessage());

		// 返回列表页面
		return findList(mapping, form, request, response);
	}
}
