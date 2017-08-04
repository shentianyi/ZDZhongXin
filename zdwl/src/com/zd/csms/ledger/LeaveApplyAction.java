package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.BeanUtils;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.StateConstants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
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
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
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
	private ExtraworkApplyService extraworkApplyService = new ExtraworkApplyService();
	
	public ActionForward findPageList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		LeaveApplyForm leaveApplyForm=(LeaveApplyForm) form;
		LeaveApplyQueryVO query=leaveApplyForm.getQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("leaveApplyList", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(query);
		query.setCurrentRole(-1);
		List<LeaveApplyBean> list=service.findPageList(query,tools);
		request.setAttribute("list", list);
		setOptions(request);
		return mapping.findForward("leaveApply_ledger");
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
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
		return mapping.findForward("leaveApply_detail");
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
		//所有银行
		request.setAttribute("banks", OptionUtil.getBanks());
		//是否替岗
		request.setAttribute("isReplace", OptionUtil.yesorno());
		//替岗监管员
		request.setAttribute("leaveReplaceRepositorys", OptionUtil.getStaffNo(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		
	}

}
