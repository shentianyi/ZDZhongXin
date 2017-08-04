package com.zd.csms.supervisorymanagement.web.action;

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
import com.zd.csms.constants.StateConstants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.jsonaction.DealerByIdJsonAction;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.CurrentDealerTypeContants;
import com.zd.csms.supervisorymanagement.model.CurrentDealerVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyBean;
import com.zd.csms.supervisorymanagement.model.LeaveApplyQueryVO;
import com.zd.csms.supervisorymanagement.model.LeaveApplyVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceDynamicVO;
import com.zd.csms.supervisorymanagement.model.LeaveReplaceVO;
import com.zd.csms.supervisorymanagement.service.ExtraworkApplyService;
import com.zd.csms.supervisorymanagement.service.LeaveApplyService;
import com.zd.csms.supervisorymanagement.web.form.LeaveApplyForm;
import com.zd.tools.dynamic.DynamicStateFlagConstants;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class LeaveApplyAction extends ActionSupport {
	
	private LeaveApplyService service = new LeaveApplyService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	SupervisoryService  supervisoryService=new SupervisoryService();
	RepositoryService repositoryService=new RepositoryService();
	RosterService rosterService=new RosterService();
	BusinessTransferService businessTransferService = new BusinessTransferService();
	DealerSupervisorService dealerSupervisorService = new DealerSupervisorService();
	UploadfileService uploadfileService = new UploadfileService();
	DealerByIdJsonAction dealerByIdJsonAction=new DealerByIdJsonAction();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	private ExtraworkApplyService extraworkApplyService = new ExtraworkApplyService();
	/**
	 * 列表页需要使用的角色
	 */
	private static int[] approvalRole = new int[]{
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_ATTENDANCE.getCode(),
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
		
		LeaveApplyForm leaveApplyForm=(LeaveApplyForm) form;
		LeaveApplyQueryVO query=leaveApplyForm.getQuery();
		int currRole = getCurrRole(request);
		if (query.getPageType() == 0) {
			query.setPageType(1);
		}
		int pageType=query.getPageType();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("leaveApplyList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setCurrentRole(currRole);
		if(currRole==RoleConstants.SUPERVISORY.getCode()){
			query.setCurrentRepositoryId(UserSessionUtil.getUserSession(request).getUser().getClient_id());
		}
		List<LeaveApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		if(query.getPageType()==1){
			return mapping.findForward("list_wait_approval");
		}else {
			return mapping.findForward("list_already_approval");
		}
	}
	
	public ActionForward addLeaveApplyEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		LeaveApplyForm leaveForm=(LeaveApplyForm) actionform;
		UserSession user = UserSessionUtil.getUserSession(request);
		if(user.getUser().getClient_type()==ClientTypeConstants.SUPERVISORY.getCode()){
			int repositoryId=user.getUser().getClient_id();
			leaveForm.getLeaveApply().setLeavePerson(repositoryId);
			RepositoryVO repository=repositoryService.load(repositoryId);
			if(repository!=null){
				SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("leavePerson", supervisorBaseInfoVO.getName());
				}
				RosterVO roster=rosterService.getRosterBySupervisorId(repository.getSupervisorId());
				if(roster!=null){
					leaveForm.getLeaveApply().setLeavePersonStaffNo(roster.getStaffNo());
				}
			}
			List<CurrentDealerVO> currentDealerList=leaveForm.getDealerList();
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
							dealer.setType(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
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
		
		//创建模板对象，用于新增行使用
		LeaveReplaceDynamicVO templet = new LeaveReplaceDynamicVO();
		templet.setState(StateConstants.UNDONE.getCode());
		templet.setStateFlag(DynamicStateFlagConstants.TEMPLET.getCode());
		leaveForm.getLeaveReplaceDynamicList().add(templet);
		
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_leave_apply");
	}
	
	public ActionForward addLeaveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		LeaveApplyVO leaveApply = leaveForm.getLeaveApply();
		leaveApply.setCreateUser(user.getId());
		leaveApply.setCreateTime(new Date());
		leaveApply.setLastModifiedUser(user.getId());
		leaveApply.setLastModifiedTime(new Date());
		leaveApply.setApplyTime(sdf.parse(sdf.format(new Date())));
		FormFile file =leaveForm.getDealerPicture();
		if(file != null && file.getFileName()!=""){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			leaveApply.setPicture(ufid);
		}
		//执行新增操作并获取操作结果
		boolean flag = service.addLeaveApply(leaveApply);
		if(flag){
			if(leaveForm.getLeaveReplaceDynamicList()!=null && leaveForm.getLeaveReplaceDynamicList().size()>0){
				for(LeaveReplaceDynamicVO leaveReplaceDynamic:leaveForm.getLeaveReplaceDynamicList()){
					leaveReplaceDynamic.setLeaveApplyId(leaveApply.getId());
				}
				flag=service.manageLeaveReplace(leaveForm.getLeaveReplaceDynamicList());
			}
			if(flag){
				List<CurrentDealerVO> currentDealerList=leaveForm.getDealerList();
				//设置经销商列表信息
				DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
				dsQuery.setSupervisorId(leaveApply.getLeavePerson());
				List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
				if(dsList!=null && dsList.size()>0){
					for(DealerSupervisorVO ds:dsList){
						try {
							DealerQueryBean bean = dealerByIdJsonAction.getDealer(ds.getDealerId());
							if(bean!=null){
								CurrentDealerVO dealer=new CurrentDealerVO();
								dealer.setType(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
								dealer.setDealerId(bean.getId());
								dealer.setDealerName(bean.getDealerName());
								dealer.setBankName(bean.getBankName());
								dealer.setBrandId(bean.getBrandId());
								dealer.setBrandName(bean.getBrand());
								dealer.setType(CurrentDealerTypeContants.LEAVEAPPLY.getCode());
								dealer.setClient_id(leaveApply.getId());
								currentDealerList.add(dealer);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				if(currentDealerList!=null && currentDealerList.size()>0){
					flag=extraworkApplyService.addCurrentDealerList(currentDealerList);
				}
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
		return addLeaveApplyEntry(mapping, form, request, response);
	}
	
	public ActionForward updateLeaveApplyEntry(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		LeaveApplyForm leaveForm=(LeaveApplyForm) actionform;
		int leaveApplyId=leaveForm.getLeaveApply().getId();
		LeaveApplyVO leaveApply=service.get(leaveApplyId);
		int repositoryId=leaveApply.getLeavePerson();
		RepositoryVO repository=repositoryService.load(repositoryId);
		if(repository!=null){
			SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
			if(supervisorBaseInfoVO!=null){
				//当前监管员名称
				request.setAttribute("leavePerson", supervisorBaseInfoVO.getName());
			}
		}
		
		//申请时间
		String applyTime=sdf.format(leaveApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		request.setAttribute("leaveStartTime", sdf.format(leaveApply.getLeaveStartTime()));
		request.setAttribute("leaveEndTime",sdf.format(leaveApply.getLeaveEndTime()));
		request.setAttribute("leaveDays",leaveApply.getLeaveDays());
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(leaveApply.getPicture());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<LeaveReplaceDynamicVO>  leaveReplaceDynamicList=leaveForm.getLeaveReplaceDynamicList();
		List<LeaveReplaceVO> leaveReplaceList=service.findLeaveReplaceListByLeaveApplyId(leaveApplyId);
		if(leaveReplaceList!=null && leaveReplaceList.size()>0){
			for(LeaveReplaceVO leaveReplace:leaveReplaceList){
				try {
					LeaveReplaceDynamicVO leaveReplaceDynamic= new LeaveReplaceDynamicVO();
					BeanUtils.copyProperties(leaveReplace, leaveReplaceDynamic);
					//设置监管员名称
					SupervisorJsonBean bean = businessTransferService.getSupervisorByRepositoryId(leaveReplaceDynamic.getReplaceSupervisory());
					leaveReplaceDynamic.setLeaveReplaceName(bean.getName());
					//设置经销商名称
					DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
					DealerService dealerService=new DealerService();
					dsQuery.setSupervisorId(leaveReplaceDynamic.getReplaceSupervisory());
					String dealerName="";
					List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
					if(dsList!=null && dsList.size()>0){
						for(DealerSupervisorVO ds:dsList){
							DealerQueryBean dealer=dealerService.detailInfo(ds.getDealerId(),ClientTypeConstants.SUPERVISORY.getCode());
							dealerName=dealerName+" "+dealer.getDealerName();
						}
					}
					leaveReplaceDynamic.setLeaveReplaceDealer(dealerName);
					
					leaveReplaceDynamic.setState(StateConstants.USING.getCode());
					leaveReplaceDynamic.setStateFlag(DynamicStateFlagConstants.NORMAL.getCode());
					leaveReplaceDynamicList.add(leaveReplaceDynamic);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		
		//创建模板对象，用于新增行使用
		LeaveReplaceDynamicVO templet = new LeaveReplaceDynamicVO();
		templet.setState(StateConstants.UNDONE.getCode());
		templet.setStateFlag(DynamicStateFlagConstants.TEMPLET.getCode());
		leaveReplaceDynamicList.add(templet);
		leaveForm.setLeaveApply(leaveApply);
		
		List<CurrentDealerVO> currentDealerList=extraworkApplyService.findCurrentDealerListByExtraworkApplyId(leaveApplyId,CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		leaveForm.setDealerList(currentDealerList);
		request.setAttribute("currentDealerList", currentDealerList);
		
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_leave_apply");
	}
	
	public ActionForward updateLeaveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		LeaveApplyVO leaveApply = leaveForm.getLeaveApply();
		leaveApply.setLastModifiedUser(user.getId());
		leaveApply.setLastModifiedTime(new Date());
		leaveApply.setApplyTime(sdf.parse(sdf.format(new Date())));
		FormFile file =leaveForm.getDealerPicture();
		if(file != null && file.getFileName()!=""){
			int ufid = UploadFileUtil.savefile(file, UserSessionUtil.getUserSession(request), request);
			leaveApply.setPicture(ufid);
		}
		//执行新增操作并获取操作结果
		boolean flag = service.updateLeaveApply(leaveApply);
		if(flag){
			if(leaveForm.getLeaveReplaceDynamicList()!=null && leaveForm.getLeaveReplaceDynamicList().size()>0){
				for(LeaveReplaceDynamicVO leaveReplaceDynamic:leaveForm.getLeaveReplaceDynamicList()){
					leaveReplaceDynamic.setLeaveApplyId(leaveApply.getId());
				}
				flag=service.manageLeaveReplace(leaveForm.getLeaveReplaceDynamicList());
			}
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		if(flag){
			//修改成功时返回列表页面
			return findPageList(mapping, form, request, response);
		}
		//修改失败时返回新增页面
		return updateLeaveApplyEntry(mapping, form, request, response);
	}
	
	public ActionForward deleteLeaveApply(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		int leaveApplyId=leaveForm.getLeaveApply().getId();
		boolean flag=service.deleteLeaveApply(leaveApplyId);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		return findPageList(mapping, form, request, response);
	}
	
	
	public ActionForward detailPage(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response){
		LeaveApplyForm leaveForm=(LeaveApplyForm) actionform;
		int leaveApplyId=leaveForm.getLeaveApply().getId();
		LeaveApplyVO leaveApply=service.get(leaveApplyId);
		int repositoryId=leaveApply.getLeavePerson();
		RepositoryVO repository=repositoryService.load(repositoryId);
		if(repository!=null){
			SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
			if(supervisorBaseInfoVO!=null){
				//当前监管员名称
				request.setAttribute("leavePerson", supervisorBaseInfoVO.getName());
			}
		}
		
		//申请时间
		String applyTime=sdf.format(leaveApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		request.setAttribute("leaveStartTime",sdf.format(leaveApply.getLeaveStartTime()));
		request.setAttribute("leaveEndTime",sdf.format(leaveApply.getLeaveEndTime()));
		
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(leaveApply.getPicture());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<LeaveReplaceDynamicVO>  leaveReplaceDynamicList=leaveForm.getLeaveReplaceDynamicList();
		List<LeaveReplaceVO> leaveReplaceList=service.findLeaveReplaceListByLeaveApplyId(leaveApplyId);
		if(leaveReplaceList!=null && leaveReplaceList.size()>0){
			for(LeaveReplaceVO leaveReplace:leaveReplaceList){
				try {
					LeaveReplaceDynamicVO leaveReplaceDynamic= new LeaveReplaceDynamicVO();
					BeanUtils.copyProperties(leaveReplace, leaveReplaceDynamic);
					//设置监管员名称
					SupervisorJsonBean bean = businessTransferService.getSupervisorByRepositoryId(leaveReplaceDynamic.getReplaceSupervisory());
					leaveReplaceDynamic.setLeaveReplaceName(bean.getName());
					//设置经销商名称
					DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
					DealerService dealerService=new DealerService();
					dsQuery.setSupervisorId(leaveReplaceDynamic.getReplaceSupervisory());
					String dealerName="";
					List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
					if(dsList!=null && dsList.size()>0){
						for(DealerSupervisorVO ds:dsList){
							DealerQueryBean dealer=dealerService.detailInfo(ds.getDealerId(),ClientTypeConstants.SUPERVISORY.getCode());
							dealerName=dealerName+" "+dealer.getDealerName();
						}
					}
					leaveReplaceDynamic.setLeaveReplaceDealer(dealerName);
					
					leaveReplaceDynamic.setState(StateConstants.USING.getCode());
					leaveReplaceDynamic.setStateFlag(DynamicStateFlagConstants.NORMAL.getCode());
					leaveReplaceDynamicList.add(leaveReplaceDynamic);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		request.setAttribute("leaveReplaceDynamicList",leaveReplaceDynamicList);
		leaveForm.setLeaveApply(leaveApply);
		setOptions(request);
		
		List<CurrentDealerVO> currentDealerList=extraworkApplyService.findCurrentDealerListByExtraworkApplyId(leaveApplyId,CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		leaveForm.setDealerList(currentDealerList);
		request.setAttribute("currentDealerList", currentDealerList);
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(leaveApplyId);
			approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYLEAVEAPPLY.getCode());
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
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		int id = leaveForm.getLeaveApply().getId();
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
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		int leaveApplyId=leaveForm.getLeaveApply().getId();
		LeaveApplyVO leaveApply=service.get(leaveApplyId);
		int repositoryId=leaveApply.getLeavePerson();
		RepositoryVO repository=repositoryService.load(repositoryId);
		if(repository!=null){
			SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
			if(supervisorBaseInfoVO!=null){
				//当前监管员名称
				request.setAttribute("leavePerson", supervisorBaseInfoVO.getName());
			}
		}
		
		//申请时间
		String applyTime=sdf.format(leaveApply.getApplyTime());
		request.setAttribute("applyTime",applyTime);
		request.setAttribute("leaveStartTime",sdf.format(leaveApply.getLeaveStartTime()));
		request.setAttribute("leaveEndTime",sdf.format(leaveApply.getLeaveEndTime()));
		try {
			UploadfileVO ufvo = uploadfileService.loadUploadfileById(leaveApply.getPicture());
			if(ufvo!=null){
				request.setAttribute("filepath", ufvo.getFile_path());
				request.setAttribute("picname", ufvo.getFile_name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<LeaveReplaceDynamicVO>  leaveReplaceDynamicList=leaveForm.getLeaveReplaceDynamicList();
		List<LeaveReplaceVO> leaveReplaceList=service.findLeaveReplaceListByLeaveApplyId(leaveApplyId);
		if(leaveReplaceList!=null && leaveReplaceList.size()>0){
			for(LeaveReplaceVO leaveReplace:leaveReplaceList){
				try {
					LeaveReplaceDynamicVO leaveReplaceDynamic= new LeaveReplaceDynamicVO();
					BeanUtils.copyProperties(leaveReplace, leaveReplaceDynamic);
					//设置监管员名称
					SupervisorJsonBean bean = businessTransferService.getSupervisorByRepositoryId(leaveReplaceDynamic.getReplaceSupervisory());
					leaveReplaceDynamic.setLeaveReplaceName(bean.getName());
					//设置经销商名称
					DealerSupervisorQueryVO dsQuery = new DealerSupervisorQueryVO();
					DealerService dealerService=new DealerService();
					dsQuery.setSupervisorId(leaveReplaceDynamic.getReplaceSupervisory());
					String dealerName="";
					List<DealerSupervisorVO> dsList = dealerSupervisorService.searchDealerSupervisorList(dsQuery);
					if(dsList!=null && dsList.size()>0){
						for(DealerSupervisorVO ds:dsList){
							DealerQueryBean dealer=dealerService.detailInfo(ds.getDealerId(),ClientTypeConstants.SUPERVISORY.getCode());
							dealerName=dealerName+" "+dealer.getDealerName();
						}
					}
					leaveReplaceDynamic.setLeaveReplaceDealer(dealerName);
					
					leaveReplaceDynamic.setState(StateConstants.USING.getCode());
					leaveReplaceDynamic.setStateFlag(DynamicStateFlagConstants.NORMAL.getCode());
					leaveReplaceDynamicList.add(leaveReplaceDynamic);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		request.setAttribute("leaveReplaceDynamicList",leaveReplaceDynamicList);
		leaveForm.setLeaveApply(leaveApply);
		setOptions(request);
		
		List<CurrentDealerVO> currentDealerList=extraworkApplyService.findCurrentDealerListByExtraworkApplyId(leaveApplyId,CurrentDealerTypeContants.LEAVEAPPLY.getCode());
		leaveForm.setDealerList(currentDealerList);
		request.setAttribute("currentDealerList", currentDealerList);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(leaveApplyId);
		approvalQuery.setObjectType(ApprovalTypeContant.SUPERVISORYLEAVEAPPLY.getCode());
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
		LeaveApplyForm leaveForm=(LeaveApplyForm) form;
		LeaveApplyQueryVO query = new LeaveApplyQueryVO();
		BeanUtils.copyProperties(leaveForm.getLeaveApply(),query);
		query.setApprovalState(leaveForm.getQuery().getApprovalState());
		query.setRemark(leaveForm.getQuery().getRemark());
		query.setCurrentRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag=service.approval(query, user);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		return findPageList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		//离职类型
		request.setAttribute("leaveTypes", OptionUtil.leaveType());
		//所有监管员
		request.setAttribute("superviseOptions", OptionUtil.getRepository());
		//所有经销商
		request.setAttribute("dealers", OptionUtil.getDealers());
		//所有品牌
		request.setAttribute("brands", OptionUtil.getBrands());
		//是否替岗
		request.setAttribute("isReplace", OptionUtil.yesorno());
		//替岗监管员
		request.setAttribute("leaveReplaceRepositorys", OptionUtil.getStaffNo(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		
	}

}
