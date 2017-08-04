package com.zd.csms.ledger;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.marketing.querybean.SupervisorJsonBean;
import com.zd.csms.marketing.service.BusinessTransferService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisorymanagement.model.TransferQueryVO;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.model.TransferSupervisorVO;
import com.zd.csms.supervisorymanagement.model.TransferVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.csms.supervisorymanagement.web.form.TransferForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class TransferAction extends ActionSupport {

	TransferService service=new TransferService();
	
	public ActionForward transferLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		TransferForm transferForm =(TransferForm) form;
		TransferQueryVO transferQuery=transferForm.getQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("transferLedger", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(transferQuery);
		//按条件查询分页数据
		List<TransferVO> list = service.searchTransferListByPage(transferQuery, tools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("dealers", OptionUtil.getDealers());
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		setOptions(request);
		//返回列表页面
		return mapping.findForward("transfer_ledger");
	}
	
	public ActionForward getSupervisorListByTransferId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		BusinessTransferService businessTransferService = new BusinessTransferService();
		TransferForm transferForm =(TransferForm) form;
		int dealerId=transferForm.getTransfer().getDealerId();
		List<TransferSupervisorVO> list=new ArrayList<TransferSupervisorVO>();
		TransferSupervisorVO ts=null;
		List<TransferRepositoryVO> transferRepositoryList=new ArrayList<TransferRepositoryVO>();
		transferRepositoryList=service.getSupervisorListByDealerId(dealerId);
		if(transferRepositoryList!=null && transferRepositoryList.size()>0){
			for(TransferRepositoryVO transferRepository:transferRepositoryList){
				int repositoryId=transferRepository.getRepositoryId();
				SupervisorJsonBean sBaseInfo;
				try {
					sBaseInfo = businessTransferService.getSupervisorByRepositoryId(repositoryId);
					if(sBaseInfo!=null){
						ts=new TransferSupervisorVO();
						ts.setName(sBaseInfo.getName());
						ts.setGender(sBaseInfo.getGender());
						ts.setContactNumber(sBaseInfo.getSelfTelephone());
						ts.setEntryDate(transferRepository.getEntryTime());
						ts.setEntryNature(transferRepository.getEntryNature());
						ts.setLeaveDate(transferRepository.getLeaveTime());
						ts.setLeaveNature(transferRepository.getLeaveNature());
						ts.setRemark("");
						ts.setWorkCondition("");
						RosterService rosterService=new RosterService();
						RosterVO roster=rosterService.getRosterBySupervisorId(sBaseInfo.getId());
						if(roster!=null){
							ts.setStaffNo(roster.getStaffNo());
						}
						list.add(ts);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("max", list.size());
		//返回列表页面
		return mapping.findForward("transfer_supervisor_list");
	}
	
	
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("handoverNatures", OptionUtil.handoverNatureOptions());
		request.setAttribute("resignReasons", OptionUtil.resignReasonOptions());
		request.setAttribute("receiveNatures", OptionUtil.receiveNatureOptions());
		request.setAttribute("afterHandoverNature", OptionUtil.afterHandoverNatureOptions());
		
	}
}
