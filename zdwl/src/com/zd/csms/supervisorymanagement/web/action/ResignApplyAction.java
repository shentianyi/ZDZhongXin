package com.zd.csms.supervisorymanagement.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.beans.BeanUtils;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.ApprovalQueryBean;
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
import com.zd.csms.supervisorymanagement.model.ResignApplyBean;
import com.zd.csms.supervisorymanagement.model.ResignApplyVO;
import com.zd.csms.supervisorymanagement.model.ResignApplyQueryVO;
import com.zd.csms.supervisorymanagement.service.ResignApplyService;
import com.zd.csms.supervisorymanagement.web.form.ResignApplyForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class ResignApplyAction extends ActionSupport {
	
	private ResignApplyService service = new ResignApplyService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	SupervisoryService  supervisoryService=new SupervisoryService();
	RepositoryService repositoryService=new RepositoryService();
	RosterService rosterService=new RosterService();
	BusinessTransferService businessTransferService = new BusinessTransferService();
	DealerSupervisorService dealerSupervisorService = new DealerSupervisorService();
	DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
	UploadfileService uploadfileService = new UploadfileService();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_RECRUIT.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
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
		
		ResignApplyForm resignForm=(ResignApplyForm) form;
		ResignApplyQueryVO query=resignForm.getQuery();
		int currRole = getCurrRole(request);
		if (query.getPageType() == 0) {
			query.setPageType(1);
		}
		int pageType=query.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("resignList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrentRole(currRole);
		if(currRole==RoleConstants.SUPERVISORY.getCode()){
			query.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
		}
		List<ResignApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		if(query.getPageType()==1){
			return mapping.findForward("list_wait_approval");
		}else {
			return mapping.findForward("list_already_approval");
		}
	}
	
	public ActionForward addResignEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ResignApplyForm resignForm=(ResignApplyForm) actionform;
		//当前时间
		String applyTime=sdf.format(new Date());
		request.setAttribute("applyTime",applyTime);
		UserSession user = UserSessionUtil.getUserSession(request);
		if(user.getUser().getClient_type()==ClientTypeConstants.SUPERVISORY.getCode()){
			int repositoryId=user.getUser().getClient_id();
			ResignApplyVO resign=service.getByResignPerson(repositoryId);
			if(resign==null){
				resign=new ResignApplyVO();
				resign.setRepositoryId(repositoryId);
				RepositoryVO repository=repositoryService.load(repositoryId);
				if(repository!=null){
					resign.setAttribute(repository.getAttribute());
					SupervisorBaseInfoJsonVO supervisorBaseInfoVO=supervisoryService.getBaseInfoJson(repository.getSupervisorId());
					if(supervisorBaseInfoVO!=null){
						//当前监管员名称
						request.setAttribute("resignPerson", supervisorBaseInfoVO.getName());
						resign.setName(supervisorBaseInfoVO.getName());
						resign.setIdNumber(supervisorBaseInfoVO.getIdNumber());
					}
					RosterVO roster=rosterService.getRosterBySupervisorId(repository.getSupervisorId());
					if(roster!=null){
						resign.setStaffNo(roster.getStaffNo());
					}
					List<CurrentDealerVO> currentDealerList=resignForm.getDealerList();
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
									dealer.setType(CurrentDealerTypeContants.RESIGNAPPLY.getCode());
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
			}else{
				try {
					UploadfileVO ufvo = uploadfileService.loadUploadfileById(resign.getPictureId());
					if(ufvo!=null){
						request.setAttribute("filepath", ufvo.getFile_path());
						request.setAttribute("picname", ufvo.getFile_name());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByResignApplyId(resign.getId(),CurrentDealerTypeContants.RESIGNAPPLY.getCode());
				request.setAttribute("currentDealerList",currentDealerList);
				try {
					MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
					approvalQuery.setObjectId(resign.getId());
					approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
					List<ApprovalQueryBean> approvals=approvalService.findListByApprovalType(approvalQuery);
					request.setAttribute("approvals", approvals);
					request.setAttribute("approvalSize", approvals.size());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				resign.setApplyTime(sdf.parse(applyTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			resignForm.setResignApply(resign);
			if(resign.getExpectResignTime()!=null){
				String expectResignTime=sdf.format(resign.getExpectResignTime());
				request.setAttribute("expectResignTime",expectResignTime);
			}
		}
		setOptions(request);
		
		//返回新增页面
		return mapping.findForward("add_resign_apply");
	}
	
	public ActionForward addResign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		addOrUpdateResign(mapping, form, request, response);
		//返回新增页面
		return addResignEntry(mapping, form, request, response);
	}
	public boolean addOrUpdateResign(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		ResignApplyForm resignForm=(ResignApplyForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		ResignApplyVO resign = resignForm.getResignApply();
		resign.setApplyTime(sdf.parse(sdf.format(new Date())));
		FormFile file =resignForm.getDealerPicture();
		if(file != null && file.getFileName()!=""){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			resign.setPictureId(ufid);
		}
		boolean flag =false;
		if(resign.getId()>0){
			resign.setLastModifiedUser(user.getId());
			resign.setLastModifiedTime(new Date());
			//执行修改操作并获取操作结果
			flag=service.updateResignApply(resign);
		}else{
			resign.setCreateUser(user.getId());
			resign.setCreateTime(new Date());
			resign.setLastModifiedUser(user.getId());
			resign.setLastModifiedTime(new Date());
			//执行新增操作并获取操作结果
			flag= service.addResignApply(resign);
			if(flag){
				List<CurrentDealerVO> currentDealerList=resignForm.getDealerList();
				//设置经销商列表信息
				DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
				dsQuery.setSupervisorId(resign.getRepositoryId());
				List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
				if(dsList!=null && dsList.size()>0){
					for(DealerSupervisorVO ds:dsList){
						try {
							DealerQueryBean bean = dealerByIdJsonAction.getDealer(ds.getDealerId());
							if(bean!=null){
								CurrentDealerVO dealer=new CurrentDealerVO();
								dealer.setType(CurrentDealerTypeContants.RESIGNAPPLY.getCode());
								dealer.setDealerId(bean.getId());
								dealer.setDealerName(bean.getDealerName());
								dealer.setBankName(bean.getBankName());
								dealer.setBrandId(bean.getBrandId());
								dealer.setBrandName(bean.getBrand());
								dealer.setClient_id(resign.getId());
								currentDealerList.add(dealer);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if(currentDealerList!=null && currentDealerList.size()>0){
					flag=service.addCurrentDealerList(currentDealerList);
				}
			}
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return flag;
	}
	public ActionForward detailPage(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		ResignApplyForm resignForm=(ResignApplyForm) actionform;
		int resignId=resignForm.getResignApply().getId();
		ResignApplyVO resign=service.get(resignId);
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(resign.getPictureId());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String applyTime=sdf.format(resign.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByResignApplyId(resignId,CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		resignForm.setResignApply(resign);
		setOptions(request);
		
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(resignId);
			approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
			List<ApprovalQueryBean> approvals=approvalService.findListByApprovalType(approvalQuery);
			request.setAttribute("approvals",approvals);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		addOrUpdateResign(mapping, form, request, response);
		ResignApplyForm resignForm=(ResignApplyForm) form;
		int id = resignForm.getResignApply().getId();
		boolean flag=service.submit(id);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return addResignEntry(mapping, form, request, response);
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
		ResignApplyForm resignForm=(ResignApplyForm) form;
		int resignId=resignForm.getResignApply().getId();
		ResignApplyVO resign=service.get(resignId);
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(resign.getPictureId());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//申请时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
		String applyTime=sdf.format(resign.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		
		List<CurrentDealerVO> currentDealerList=service.findCurrentDealerListByResignApplyId(resignId,CurrentDealerTypeContants.RESIGNAPPLY.getCode());
		request.setAttribute("currentDealerList",currentDealerList);
		resignForm.setResignApply(resign);
		setOptions(request);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(resignId);
		approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYRESIGNAPPLY.getCode());
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
		ResignApplyForm resignForm=(ResignApplyForm) form;
		ResignApplyQueryVO query = new ResignApplyQueryVO();
		BeanUtils.copyProperties(resignForm.getResignApply(),query);
		query.setApprovalState(resignForm.getQuery().getApprovalState());
		query.setRemark(resignForm.getQuery().getRemark());
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
		//所有银行
		request.setAttribute("banks", OptionUtil.getBanks());
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
	public ActionForward deleteResign(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResignApplyForm resignForm=(ResignApplyForm) form;
		int id = resignForm.getResignApply().getId();
		boolean flag=service.delete(id);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return addResignEntry(mapping, form, request, response);
	}
}
