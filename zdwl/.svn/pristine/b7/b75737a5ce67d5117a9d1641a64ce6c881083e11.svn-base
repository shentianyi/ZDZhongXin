package com.zd.csms.ledger;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.supervisory.model.ExtRepairCostVO;
import com.zd.csms.supervisory.model.RepairCostQueryVO;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.csms.supervisory.service.RepairCostService;
import com.zd.csms.supervisory.web.form.RepairCostForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RepairCostAction extends ActionSupport {

	private RepairCostService service = new RepairCostService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	
	public ActionForward repairCostLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RepairCostForm rform = (RepairCostForm)form;
		RepairCostQueryVO query = rform.getRepaircostquery();
		
		query.setPageType(2);
		query.setCurrRole(-1);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("repairCostLedger", request);
		tools.saveQueryVO(query);
		
		List<RepairCostVO> list = service.searchRepairCostListByPage(query, tools,request);

		request.setAttribute("list", list);
		
		return mapping.findForward("repaircost_ledger");
	}
	
	public ActionForward repairCostDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RepairCostForm rform = (RepairCostForm) form;
		RepairCostVO bean = service.loadRepairCostById(rform.getRepaircost().getId());

		rform.setRepaircost(bean);
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.REPAIRCOST.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));
		UserService us = new UserService();
		UserVO user = us.loadUserById(bean.getPromoter());
		request.setAttribute("username", user.getUserName());
		request.setAttribute("repositoryId", user.getClient_id());
		
		return mapping.findForward("repaircost_detail");
	}
	
	/*
	 * 需求38
	 * 导出设备维修费用申请1
	*/
	public ActionForward ExtRepairCostLedger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		RepairCostForm rform = (RepairCostForm)form;
		RepairCostQueryVO query = rform.getRepaircostquery();
		query.setPageType(2);
		query.setCurrRole(-1);
		
		List<ExtRepairCostVO> list = service.ExtRepairCostLedger(query);
		String[] titles = {"序号","申报人",	"维修项目",	"维修费用",	"具体问题",	"审批状态",	"创建人",	"创建时间","修改人","修改时间"};
		String filename = "设备维修费用申请";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	ExtRepairCostVO vo = list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	tool.setCellValue(i+1, 1, vo.getDeclareUser());
	    	tool.setCellValue(i+1, 2, vo.getRepair_project());
	    	tool.setCellValue(i+1, 3, vo.getMoney());
	    	tool.setCellValue(i+1, 4, vo.getProblem());
	    	if (vo.getApprovalState() == 1) {
	    		tool.setCellValue(i+1, 5, "审批通过");
			}else if (vo.getApprovalState() == 2) {
	    		tool.setCellValue(i+1, 5, "审批不通过");
			}else if (vo.getApprovalState() == 3) {
	    		tool.setCellValue(i+1, 5, "正在审批");
			}else if (vo.getApprovalState() == 0) {
	    		tool.setCellValue(i+1, 5, "未提交");
			}else if (vo.getApprovalState() == -1) {
	    		tool.setCellValue(i+1, 5, "未通知");
			}
	    	
	    	tool.setCellValue(i+1, 6, vo.getCreateUser());
	    	if (vo.getCreatedate() !=null) {
				String createDate = format.format(vo.getCreatedate());
				tool.setCellValue(i+1, 7, createDate);
			}else {
				tool.setCellValue(i+1, 7, "");
			}
	    	tool.setCellValue(i+1, 8, vo.getUpdateUser());
	    	if (vo.getUpddate() !=null) {
				String updDate = format.format(vo.getUpddate());
				tool.setCellValue(i+1, 9, updDate);
			}else {
				tool.setCellValue(i+1, 9, "");
			}
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
	
	
	
	
	
	
	
	
	
}
