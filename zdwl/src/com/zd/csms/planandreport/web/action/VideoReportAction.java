package com.zd.csms.planandreport.web.action;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zd.core.ActionSupport;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.planandreport.model.ReportBaseInfoBean;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.model.VideoReportVO;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.planandreport.service.VideoPlanService;
import com.zd.csms.planandreport.service.VideoReportService;
import com.zd.csms.planandreport.web.form.VideoReportForm;
import com.zd.csms.rbac.constants.ClientTypeConstants;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.windcontrol.constants.CompleteStatusConstants;
import com.zd.csms.windcontrol.constants.EditStatusConstants;
import com.zd.tools.StringUtil;

public class VideoReportAction extends ActionSupport {
	private VideoReportService reportService = new VideoReportService();
	private VideoPlanService planService = new VideoPlanService();

	// 视频检查报告页面（新增/修改）
	public ActionForward reportEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VideoReportForm videoReportform = (VideoReportForm) form;
		int id = videoReportform.getVideoReport().getId();
		VideoPlanVO planVO = planService.getVideoPlanVO(id);
		if (planVO == null) {
			return null;
		}

		VideoReportVO vo = reportService.getVideoReportByPlanId(id);
		request.setAttribute("planId", id);
		request.setAttribute("plan", planVO);
		
		if (vo == null) {
			UserSession userSession = UserSessionUtil.getUserSession(request);
			request.setAttribute("check_name", userSession.getUser()
					.getUserName());
			request.setAttribute("check_time", new Date());
			ReportBaseInfoBean bi = reportService.findBaseInfoByDealerId(planVO.getDealerId());
			request.setAttribute("dealer",
					bi);
			return mapping.findForward("addReport");
		} else {
			request.setAttribute("videoReport", vo);
			videoReportform.setVideoReport(vo);
			/*request.setAttribute("reportItem",
					reportService.getReportItemByPlanId(id));*/
			request.setAttribute("reportQuestion", reportService.findVideoReportQuestionVOByVrid(id));
			return mapping.findForward("updateReport");
		}

	}

	public ActionForward addReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VideoReportForm videoReportform = (VideoReportForm) form;
		videoReportform.setReportStatus(EditStatusConstants.EDIT.getCode());
		if(videoReportform.getVideoReport().getSkip_fag() != 1){
			String[] question_classify1 = request.getParameterValues("question_classify1");
			String[] question_desc1 = request.getParameterValues("question_desc1");
			String[] depart1 = request.getParameterValues("depart1");
			
			String[] question_classify2 = request.getParameterValues("question_classify2");
			String[] question_desc2 = request.getParameterValues("question_desc2");
			String[] depart2 = request.getParameterValues("depart2");
			
			String[] question_classify3 = request.getParameterValues("question_classify3");
			String[] question_desc3 = request.getParameterValues("question_desc3");
			String[] depart3 = request.getParameterValues("depart3");
			
			reportService.mergeQuestion(1,question_classify1,question_desc1,depart1,videoReportform.getVrqList());
			reportService.mergeQuestion(2,question_classify2,question_desc2,depart2,videoReportform.getVrqList());
			reportService.mergeQuestion(3,question_classify3,question_desc3,depart3,videoReportform.getVrqList());
		}
		
		
		boolean result = reportService.addReport(videoReportform, request);
		if (result) {
			return findProgressList(mapping, videoReportform, request, response);
		} else {
			return reportEntry(mapping, form, request, response);
		}
	}

	public ActionForward updateReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VideoReportForm videoReportform = (VideoReportForm) form;
		
		if(videoReportform.getVideoReport().getSkip_fag() != 1){
			String[] question_classify1 = request.getParameterValues("question_classify1");
			String[] question_desc1 = request.getParameterValues("question_desc1");
			String[] depart1 = request.getParameterValues("depart1");
			
			String[] question_classify2 = request.getParameterValues("question_classify2");
			String[] question_desc2 = request.getParameterValues("question_desc2");
			String[] depart2 = request.getParameterValues("depart2");
			
			
			String[] question_classify3 = request.getParameterValues("question_classify3");
			String[] question_desc3 = request.getParameterValues("question_desc3");
			String[] depart3 = request.getParameterValues("depart3");
			
			reportService.mergeQuestion(1,question_classify1,question_desc1,depart1,videoReportform.getVrqList());
			reportService.mergeQuestion(2,question_classify2,question_desc2,depart2,videoReportform.getVrqList());
			reportService.mergeQuestion(3,question_classify3,question_desc3,depart3,videoReportform.getVrqList());
		}
		boolean result = reportService.updateReport(videoReportform, request);
		if (result) {
			return findProgressList(mapping, videoReportform, request, response);
		} else {
			return reportEntry(mapping, form, request, response);
		}
	}

	// 提交:视频检查报告
	public ActionForward submitReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VideoReportForm videoReportform = (VideoReportForm) form;
		videoReportform.setReportStatus(EditStatusConstants.SUBMIT.getCode());
		
		if(videoReportform.getVideoReport().getSkip_fag() != 1){
			String[] question_classify1 = request.getParameterValues("question_classify1");
			String[] question_desc1 = request.getParameterValues("question_desc1");
			String[] depart1 = request.getParameterValues("depart1");
			
			String[] question_classify2 = request.getParameterValues("question_classify2");
			String[] question_desc2 = request.getParameterValues("question_desc2");
			String[] depart2 = request.getParameterValues("depart2");
			
			
			String[] question_classify3 = request.getParameterValues("question_classify3");
			String[] question_desc3 = request.getParameterValues("question_desc3");
			String[] depart3 = request.getParameterValues("depart3");
			
			reportService.mergeQuestion(1,question_classify1,question_desc1,depart1,videoReportform.getVrqList());
			reportService.mergeQuestion(2,question_classify2,question_desc2,depart2,videoReportform.getVrqList());
			reportService.mergeQuestion(3,question_classify3,question_desc3,depart3,videoReportform.getVrqList());
		}
		
		boolean result = reportService.submitReport(videoReportform, request);
		if (result) {
			/*
			 * 视频检查报告提交后给  监管员管理部经理、市场部经理、业务部经理、风控部经理、视频部经理、财务部经理、
			 * 风险管理部部长、市场管理部部长、运营管理部部长、物流金融中心总监 发送信息提醒
			*/
			/*try {
				MessageUtil.sendMsg(new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
						RoleConstants.MARKET_AMALDAR.getCode()+"",
						RoleConstants.BUSINESS_AMALDAR.getCode()+"",
						RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
						RoleConstants.VIDEO_AMALDAR.getCode()+"",
						RoleConstants.FINANCE_AMALDAR.getCode()+"",
						RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, "视频检查报告",
						"/videoReport.do?method=findProgressList", 1,MessageTypeContant.VIDEOCHECKREPORT.getCode(),videoReportform.getQuery().getUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			try {
				//给部长、总监、视频经理、风控经理、内控专员发送信息提醒
				String[] roleIds = new String[]{RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode()+"",
						RoleConstants.MARKET_AMALDAR.getCode()+"",
						RoleConstants.BUSINESS_AMALDAR.getCode()+"",
						RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
						RoleConstants.VIDEO_AMALDAR.getCode()+"",
						RoleConstants.FINANCE_AMALDAR.getCode()+"",
						RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.MARKETMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode()+"",
						RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""};
						MessageUtil.sendOrUpdateMeg(roleIds,  1+"", 
										"/videoReport.do?method=findViewList", 1,MessageTypeContant.VIDEOCHECKREPORT.getCode(),UserSessionUtil.getUserSession(request).getUser().getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return findCompletedList(mapping, videoReportform, request,
					response);
		} else {
			return reportEntry(mapping, form, request, response);
		}
	}

	// 视频检查报告详情
	public ActionForward getDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		if (id<=0){
			return null;
		}
		VideoReportVO vo = reportService.getVideoReportByPlanId(id);
		request.setAttribute("videoReport", vo);
		request.setAttribute("reportQuestion", reportService.findVideoReportQuestionVOByVrid(vo.getId()));
//		request.setAttribute("reportItem",reportService.getReportItemByPlanId(id));
		return mapping.findForward("viewReport");
	}

	// 视频专员:视频检查报告列表
	public ActionForward findProgressList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		VideoReportForm queryForm = (VideoReportForm) form;
		List<VideoPlanQueryBean> list = reportService.findReportList(queryForm,request);
		request.setAttribute("list", list);
		return mapping.findForward("progressList");

	}

	// 视频专员:视频检查报告列表（已完成）
	public ActionForward findCompletedList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoReportForm queryForm = (VideoReportForm) form;
		queryForm.getQuery().setFlag(CompleteStatusConstants.COMPLETE.getCode());
		List<VideoPlanQueryBean> list = reportService.findReportList(queryForm,
				request);
		request.setAttribute("list", list);
		return mapping.findForward("completedList");

	}

	
	   // 视频部全员及各部门经理以上:视频检查报告列表（查看）
		public ActionForward findViewList(ActionMapping mapping,
				ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			VideoReportForm queryForm = (VideoReportForm) form;
			queryForm.getQuery().setFlag(CompleteStatusConstants.COMPLETE.getCode());
			List<VideoPlanQueryBean> list = reportService.findReportList(queryForm,
					request);
			request.setAttribute("list", list);
			return mapping.findForward("viewList");

		}
}
