package com.zd.csms.supervisorymanagement.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoJsonVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyBean;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.ExtraworkApplyVO;
import com.zd.csms.supervisorymanagement.service.ExtraworkApplyService;
import com.zd.csms.supervisorymanagement.web.form.ExtraworkApplyForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ExtraworkApplyAction extends ActionSupport {
	
	private ExtraworkApplyService service = new ExtraworkApplyService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	SupervisoryService  supervisoryService=new SupervisoryService();
	RepositoryService repositoryService=new RepositoryService();
	RosterService rosterService=new RosterService();
	BusinessTransferService businessTransferService = new BusinessTransferService();
	DealerSupervisorService dealerSupervisorService = new DealerSupervisorService();
	DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	/**
	 * 列表页需要使用的角色 -- 业务专员-->业务经理-->考勤专员-->监管员管理部经理-->运营管理部部长
	 */
	/**private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.WINDCONRTOL_INTERNAL.getCode(),
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),
			RoleConstants.MARKET_COMMISSIONER.getCode(),
			RoleConstants.MARKET_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode(),
			RoleConstants.SR.getCode()};*/
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.SR.getCode()};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	
	public ActionForward findPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		ExtraworkApplyForm extraworkApplyForm=(ExtraworkApplyForm) form;
		ExtraworkApplyQueryVO query=extraworkApplyForm.getQuery();
		int currRole = getCurrRole(request);
		if (query.getPageType() == 0) {
			query.setPageType(1);
		}
		int pageType=query.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("extraworkApplyList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrentRole(currRole);
		if(currRole==RoleConstants.SUPERVISORY.getCode()){
			query.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
		}
		List<ExtraworkApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		if(query.getPageType()==1){
			return mapping.findForward("list_wait_approval");
		}else {
			return mapping.findForward("list_already_approval");
		}
	}
	
	public ActionForward addExtraworkApplyEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) actionform;
		UserSession user = UserSessionUtil.getUserSession(request);
		ExtraworkApplyVO extraworkApply=extraworkForm.getExtraworkApply();
		if(user.getUser().getClient_type()==ClientTypeConstants.SUPERVISORY.getCode()){
			int repositoryId=user.getUser().getClient_id();
			extraworkApply.setRepositoryId(repositoryId);
			RepositoryVO repository=repositoryService.load(repositoryId);
			if(repository!=null){
				SupervisorBaseInfoJsonVO supervisorBaseInfoVO=supervisoryService.getBaseInfoJson(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("extraworkPerson", supervisorBaseInfoVO.getName());
					extraworkApply.setName(supervisorBaseInfoVO.getName());
					extraworkApply.setProvince(supervisorBaseInfoVO.getLiveAddressProvince());
					extraworkApply.setCity(supervisorBaseInfoVO.getLiveAddressCity());
				}
				RosterVO roster=rosterService.getRosterBySupervisorId(repository.getSupervisorId());
				if(roster!=null){
					extraworkApply.setStaffNo(roster.getStaffNo());
				}
			}
			List<CurrentDealerVO> currentDealerList=extraworkForm.getDealerList();
			//设置经销商列表信息
			DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
			dsQuery.setSupervisorId(repositoryId);
			List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
			if(dsList!=null && dsList.size()>0){
				for(DealerSupervisorVO ds:dsList){
					try {
						DealerQueryBean bean = dealerByIdJsonAction.getDealer(ds.getDealerId());
						if(bean!=null){
							CurrentDealerVO dealer=new CurrentDealerVO();
							dealer.setType(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
							dealer.setDealerId(bean.getId());
							dealer.setDealerName(bean.getDealerName());
							dealer.setBankId(bean.getBankId());
							dealer.setBankName(bean.getBankName());
							dealer.setBrandId(bean.getBrandId());
							dealer.setBrandName(bean.getBrand());
							currentDealerList.add(dealer);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			request.setAttribute("currentDealerList", currentDealerList);
		}
		//当前时间
		String applyTime=sdf.format(new Date());
		request.setAttribute("applyTime",applyTime);
		
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_extrawork_apply");
	}
	
	public ActionForward addExtraworkApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		ExtraworkApplyVO extraworkApply = extraworkForm.getExtraworkApply();
		extraworkApply.setCreateUser(user.getId());
		extraworkApply.setCreateTime(new Date());
		//extraworkApply.setLastModifiedUser(user.getId());
		//extraworkApply.setLastModifiedTime(new Date());
		extraworkApply.setApplyTime(sdf.parse(sdf.format(new Date())));
		//执行新增操作并获取操作结果
		boolean flag = service.addExtraworkApply(extraworkApply);
		if(flag){
			List<CurrentDealerVO> currentDealerList=extraworkForm.getDealerList();
			//设置经销商列表信息
			DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
			dsQuery.setSupervisorId(extraworkApply.getRepositoryId());
			List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
			if(dsList!=null && dsList.size()>0){
				for(DealerSupervisorVO ds:dsList){
					try {
						DealerQueryBean bean = dealerByIdJsonAction.getDealer(ds.getDealerId());
						if(bean!=null){
							CurrentDealerVO dealer=new CurrentDealerVO();
							dealer.setType(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
							dealer.setDealerId(bean.getId());
							dealer.setDealerName(bean.getDealerName());
							dealer.setBankName(bean.getBankName());
							dealer.setBrandId(bean.getBrandId());
							dealer.setBrandName(bean.getBrand());
							currentDealerList.add(dealer);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			if(currentDealerList!=null && currentDealerList.size()>0){
				for(CurrentDealerVO currentDealer:currentDealerList){
					currentDealer.setType(CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
					currentDealer.setClient_id(extraworkApply.getId());
				}
				flag=service.addCurrentDealerList(currentDealerList);
			}
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//新增成功时返回列表页面
			return findPageList(mapping, form, request, response);
		}
		//新增失败时返回新增页面
		return addExtraworkApplyEntry(mapping, form, request, response);
	}
	
	public ActionForward updateExtraworkApplyEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) actionform;
		int extraworkApplyId=extraworkForm.getExtraworkApply().getId();
		ExtraworkApplyVO extraworkApply=service.get(extraworkApplyId);
		//申请时间
		String applyTime=sdf.format(extraworkApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		String startTime=sdf.format(extraworkApply.getStartTime());
		request.setAttribute("startTime",startTime);
		String endTime=sdf.format(extraworkApply.getEndTime());
		request.setAttribute("endTime",endTime);
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByExtraworkApplyId(extraworkApplyId,CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		extraworkForm.setExtraworkApply(extraworkApply);
		extraworkForm.setDealerList(currentDealerList);
		request.setAttribute("currentDealerList", currentDealerList);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_extrawork_apply");
	}
	
	public ActionForward updateExtraworkApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		ExtraworkApplyVO extraworkApply = extraworkForm.getExtraworkApply();
		extraworkApply.setLastModifiedUser(user.getId());
		extraworkApply.setLastModifiedTime(new Date());
		extraworkApply.setApplyTime(sdf.parse(sdf.format(new Date())));
		//执行新增操作并获取操作结果
		boolean flag = service.updateExtraworkApply(extraworkApply);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功时返回列表页面
			return findPageList(mapping, form, request, response);
		}
		//修改失败时返回新增页面
		return updateExtraworkApplyEntry(mapping, form, request, response);
	}
	
	public ActionForward deleteExtraworkApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		int extraworkApplyId=extraworkForm.getExtraworkApply().getId();
		boolean flag=service.deleteExtraworkApply(extraworkApplyId);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findPageList(mapping, form, request, response);
	}
	
	
	public ActionForward detailPage(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) actionform;
		int extraworkApplyId=extraworkForm.getExtraworkApply().getId();
		ExtraworkApplyVO extraworkApply=service.get(extraworkApplyId);
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String applyTime=sdf.format(extraworkApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByExtraworkApplyId(extraworkApplyId,CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		extraworkForm.setExtraworkApply(extraworkApply);
		setOptions(request);
		
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(extraworkApplyId);
			approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
			request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回修改页面
		return mapping.findForward("detailPage");
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
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		int id = extraworkForm.getExtraworkApply().getId();
		boolean flag=service.submit(id);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findPageList(mapping, form, request, response);
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
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		int extraworkApplyId=extraworkForm.getExtraworkApply().getId();
		ExtraworkApplyVO extraworkApply=service.get(extraworkApplyId);
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		String applyTime=sdf.format(extraworkApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByExtraworkApplyId(extraworkApplyId,CurrentDealerTypeContants.EXTRAWORKAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		extraworkForm.setExtraworkApply(extraworkApply);
		setOptions(request);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(extraworkApplyId);
		approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYEXTRAWORKAPPLY.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

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
		ExtraworkApplyForm extraworkForm=(ExtraworkApplyForm) form;
		ExtraworkApplyQueryVO query = new ExtraworkApplyQueryVO();
		BeanUtils.copyProperties(extraworkForm.getExtraworkApply(),query);
		query.setApprovalState(extraworkForm.getQuery().getApprovalState());
		query.setRemark(extraworkForm.getQuery().getRemark());
		query.setCurrentRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag=service.approval(query, user);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		return findPageList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		//所有监管员
		request.setAttribute("superviseOptions", OptionUtil.getRepository());
		//所有经销商
		request.setAttribute("dealers", OptionUtil.getDealers());
		//所有品牌
		request.setAttribute("brands", OptionUtil.getBrands());
		//审批部门
		request.setAttribute("departments", OptionUtil.getApprovalDepartments());
	}

}
