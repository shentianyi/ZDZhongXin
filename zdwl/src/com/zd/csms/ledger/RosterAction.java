package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.CommonUtil;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.roster.model.PostChangeVO;
import com.zd.csms.roster.model.RosterEntity;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.roster.web.form.RosterForm;
import com.zd.csms.supervisorymanagement.model.TransferRepositoryVO;
import com.zd.csms.supervisorymanagement.service.TransferService;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RosterAction extends ActionSupport {

	RosterService rosterService=new RosterService();
	
	public ActionForward rosterLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		RosterQueryVO rosterQuery=rosterForm.getRosterQuery();
		//初始化分页查询工具
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("rosterLedger", request);
		//记录查询条件,用于查询条件变更时返回第一页
		tools.saveQueryVO(rosterQuery);
		
		//按条件查询分页数据
		List<RosterEntity> list = rosterService.searchPageList(rosterQuery,tools);
		
		for(RosterEntity rosterList: list){
			if(rosterList.getPostChangeList().size()>0){
				if(rosterList.getPostChangeList().get(0).getMissionDate()!=null){
					rosterList.getRoster().setMissionDateNTT(rosterList.getPostChangeList().get(0).getMissionDate());
				}
				if(rosterList.getPostChangeList().get(0).getMissionNature()!=null){
					rosterList.getRoster().setMissionNatureNTT(rosterList.getPostChangeList().get(0).getMissionNature());
				}
				if(rosterList.getPostChangeList().get(0).getDimissionDate()!=null){
					rosterList.getRoster().setDimissionDateNTT(rosterList.getPostChangeList().get(0).getDimissionDate());
					if(rosterList.getPostChangeList().get(0).getDimissionNature()!=null){
						rosterList.getRoster().setDimissionNatureNTT(rosterList.getPostChangeList().get(0).getDimissionNature());
					}
				}else{
					if(rosterList.getPostChangeList().size()>1){
						if(rosterList.getPostChangeList().get(1).getDimissionDate()!=null){
							rosterList.getRoster().setDimissionDateNTT(rosterList.getPostChangeList().get(1).getDimissionDate());
							if(rosterList.getPostChangeList().get(1).getDimissionNature()!=null){
								rosterList.getRoster().setDimissionNatureNTT(rosterList.getPostChangeList().get(1).getDimissionNature());
							}
						}
					}
				}
				
			}
		}
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//返回列表页面
		return mapping.findForward("roster_ledger");
	}
	
	public ActionForward rosterDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		RosterForm rosterForm =(RosterForm) form;
		int rosterId=rosterForm.getRoster().getId();
		RosterVO roster=rosterService.load(rosterId);
		if (roster.getRepositoryId() >0) {
			TransferService transferService = new TransferService();
			List<TransferRepositoryVO> tsList = transferService.searchLeaveTimeAndEntryTimeByRepositoryId(roster.getRepositoryId());
			if (tsList!=null) {
				 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				 String leavetime ;
				 String entrytime ;
				 int months = 0;
				 for (int i = 0; i < tsList.size(); i++) {
					if (tsList.get(i).getLeaveTime() !=null && tsList.get(i).getEntryTime() !=null) {
						leavetime = sdf.format(tsList.get(i).getLeaveTime());
						entrytime = sdf.format(tsList.get(i).getEntryTime());
						try {
							months =months + CommonUtil.countMonths(leavetime,entrytime,"yyyy-MM-dd");
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}else {
						leavetime = "0";
						entrytime = "0";
					}
				 }
				int year = months / 12;
				 months = months % 12;
				 roster.setDriveYear(year);
				 roster.setDriveMonth(months);
			}
			request.setAttribute("list", tsList);
		}
		
		/* 更新显示账户列表 */
		//roster.setSystemAccount(rosterService.getLoginidList(roster.getRepositoryId()));
		
		request.setAttribute("supervisors", OptionUtil.getAllSupervise());
		//按条件查询分页数据
		//List<PostChangeVO> list = rosterService.searchPostChangeListByRosterId(rosterId);
		//将查询结果设置request中，用于页面显示
		//request.setAttribute("list", list);
		rosterForm.setRoster(roster);
		//返回列表页面
		return mapping.findForward("roster_detail");	
	}
	
	
	
	
}
