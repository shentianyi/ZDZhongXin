package com.zd.csms.ledger;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.constants.Constants;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.planandreport.model.VideoReportVO;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.planandreport.service.VideoReportService;
import com.zd.csms.planandreport.web.form.VideoReportForm;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;

public class VideoReportAction extends ActionSupport {
	private VideoReportService reportService = new VideoReportService();

	/**
	 * 列表页需要使用的角色//
	 */
	private static int[] approvalRole = new int[]{
			//品牌集团
			RoleConstants.BRANDMASTER.getCode(),
			//经销商集团
			RoleConstants.DEALERMASTER.getCode(),
			RoleConstants.DEALERMASTERA.getCode(),
			RoleConstants.VIDEO_AMALDAR.getCode()};
	
	
	private static int[] bankRole = new int[]{
		//品牌集团
		RoleConstants.BANK_APPROVE.getCode()
		};
	/**
	 * 获取当前用户的权限
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		if (user.getUser().getClient_type() == ClientTypeConstants.BANK.getCode()) {
			return RoleUtil.getCurrRole(user, bankRole);
		}
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	// 视频检查报告台账
	public ActionForward reportLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		VideoReportForm videoPlanForm = (VideoReportForm) form;
		int currRole=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		videoPlanForm.getQuery().setCurrRole(currRole);
		videoPlanForm.getQuery().setUserId(user.getId());
		videoPlanForm.getQuery().setUserVO(user);
		List<VideoPlanQueryBean> list = reportService.findReportLedger(videoPlanForm, request);
		request.setAttribute("list", list);
		return mapping.findForward("reportLedger");

	}
	/*
	 * 需求38
	 * 导出视频检查报告台账
	*/
	public ActionForward ExtReportLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception{
		VideoReportForm videoPlanForm = (VideoReportForm) form;
		int currRole=getCurrRole(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		videoPlanForm.getQuery().setCurrRole(currRole);
		videoPlanForm.getQuery().setUserId(user.getId());
		videoPlanForm.getQuery().setUserVO(user);
		List<VideoPlanQueryBean> list = reportService.ExtReportLedger(videoPlanForm, request);
		String[] titles = {"序号","视频检查专员","计划次数",	"计划编号",	"经销商",	"省份",	"城市",	"总行/金融机构","分行/分支机构","支行",	"品牌"};
		String filename = "视频检查报告台账";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	VideoPlanQueryBean vo = list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	tool.setCellValue(i+1, 1, vo.getVideoUserName());
	    	tool.setCellValue(i+1, 2, vo.getPlanCount());
	    	tool.setCellValue(i+1, 3, vo.getPlanCode());
	    	tool.setCellValue(i+1, 4, vo.getDealerName());
	    	tool.setCellValue(i+1, 5, vo.getProvinceName());
	    	tool.setCellValue(i+1, 6, vo.getCityName());
	    	tool.setCellValue(i+1, 7, vo.getOneBankName());
	    	tool.setCellValue(i+1, 8, vo.getTwoBankName());
	    	tool.setCellValue(i+1, 9, vo.getThreeBankName());
	    	tool.setCellValue(i+1, 10, vo.getBrandName());
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
	
	// 视频检查报告台账详情
	public ActionForward getDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		if (id<=0){
			return null;
		}
		VideoReportVO vo = reportService.getVideoReportByPlanId(id);
		request.setAttribute("videoReport", vo);
		if (vo.getId() > 0) {
			request.setAttribute("reportQuestion", reportService.findVideoReportQuestionVOByVrid(vo.getId()));	
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE_FAILED.getCode(), "数据不存在！");
			return reportLedger(mapping, form, request, response);
		}
		
		return mapping.findForward("videoReport_lenger_detail");
	}
	
}
