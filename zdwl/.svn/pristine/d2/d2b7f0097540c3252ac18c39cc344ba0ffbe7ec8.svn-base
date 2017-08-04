package com.zd.csms.planandreport.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.marketing.contants.ApprovalContant;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.model.MarketApprovalVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.message.MessageUtil;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.message.model.MessageVO;
import com.zd.csms.message.service.MessageService;
import com.zd.csms.planandreport.contants.ApproveStateContants;
import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.model.VideoPlanQueryVO;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.querybean.VideoPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.VideoPlanQueryBean;
import com.zd.csms.planandreport.service.VideoPlanInfoService;
import com.zd.csms.planandreport.service.VideoPlanService;
import com.zd.csms.planandreport.web.form.VideoPlanForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.Util;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class VideoPlanAction extends ActionSupport {
	private VideoPlanService service = new VideoPlanService();
	private VideoPlanInfoService vpiService = new VideoPlanInfoService();
	private UserService uservice = new UserService();
	private MarketApprovalSerivce mSerivce = new MarketApprovalSerivce();

	/**
	 * 角色:根据不同的部门分开判断 (21,22,23)
	 */
	private static int[] approvalRole = new int[] {
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),//风险管理部长
			RoleConstants.VIDEO_COMMISSIONER.getCode(),//视频部专员
			RoleConstants.VIDEO_DATA.getCode(),//视频部数据专员
			RoleConstants.VIDEO_AMALDAR.getCode(),//视频部经理
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode() //物流金融中心总监
	};
	

	private UserVO gerUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 */
	private int getCurrRole(HttpServletRequest request) {
		UserSession user = UserSessionUtil.getUserSession(request);
		return RoleUtil.getCurrRole(user, approvalRole);
	}
	
	/**
	 * 分页查询(视频经理审批列表)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		query.setFlag(1);
		tools.saveQueryVO(query);
		List<VideoPlanInfoQueryBean> list = service.findList(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("video_plan_list_approve");
		
	}
	
	/**
	 * 分页查询已审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListApproveno(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<VideoPlanInfoQueryBean> list = service.findListAlreadyCheck(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("video_plan_list_approveno");
		
	}
	
	/**
	 * 未审批详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		
		VideoPlanInfoVO videoPlanInfo = videoPlanForm.getVideoPlanInfo();
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		int id = videoPlanForm.getVideoPlan().getId();
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		Integer status = videoPlanInfo.getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			tools.setPageSize(15);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			if (null != findListAlready) {
				for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
					userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
					if (null != userVO) {
						videoPlanQueryBean.setVideoUserName(userVO.getUserName());
					}
				}
			}
			
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			String unCheckPassCause = videoPlanInfoVO.getUnCheckPassCause();
			/*if (null != unCheckPassCause ) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
			}*/
			status = videoPlanInfoVO.getStatus();
			System.out.println("cihsdihidvsdbvid==:"+status);
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
			}
			loadApprovalLog(videoPlanInfoVO,request);
		}
		fillValues(findListAlready);
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("detail_dealer_approve");
		
	}
	/**
	 * 已审批详情
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailApproveno(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		VideoPlanInfoVO videoPlanInfo = videoPlanForm.getVideoPlanInfo();
		Integer status = videoPlanInfo.getStatus();
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
//		videoPlanForm.getVideoPlanInfo().
		int id = videoPlanForm.getVideoPlan().getId();
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			String unCheckPassCause = videoPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			//tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
				userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
				if (null != userVO) {
					videoPlanQueryBean.setVideoUserName(userVO.getUserName());
				}
			}
			loadApprovalLog(videoPlanInfoVO,request);
		}
		fillValues(findListAlready);
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("detail_dealer_approveno");
		
	}
	
	/**
	 * 未审核详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		
		VideoPlanInfoVO videoPlanInfo = videoPlanForm.getVideoPlanInfo();
		Integer status = videoPlanInfo.getStatus();
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		
		int id = videoPlanForm.getVideoPlan().getId();
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			String unCheckPassCause = videoPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
				userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
				if (null != userVO) {
					videoPlanQueryBean.setVideoUserName(userVO.getUserName());
				}
			}
			loadApprovalLog(videoPlanInfoVO,request);
		}
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		
		fillValues(findListAlready);
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("detail_dealer");
		
	}
	
	/**
	 * 审批操作(通过)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanInfoVO bean = videoPlanForm.getVideoPlanInfo();
		UserSession user = UserSessionUtil.getUserSession(request);
		
		String planCodes = request.getParameter("planCodes");
		String unCheckPassCause = request.getParameter("unCheckPassCauseInfo");
		String singleApproval = request.getParameter("singleApproval");//判断是否为单个审批通过  而不是批量审批通过
		int currRole = getCurrRole(request);
		boolean flag = false;
		VideoPlanInfoVO videoPlanInfoVO = null;
		int result=0;
		if (StringUtil.isNotEmpty(planCodes) && StringUtil.isNotEmpty(singleApproval) && singleApproval.equals("1")) {
			videoPlanInfoVO = vpiService.get(planCodes);
			if (null != videoPlanInfoVO) {
				if (null != unCheckPassCause) {
					videoPlanInfoVO.setUnCheckPassCause(unCheckPassCause);//设置审批意见
				}
				if (currRole==RoleConstants.VIDEO_AMALDAR.getCode()) {//视频经理角色
					videoPlanInfoVO.setStatus(ApproveStateContants.CHECKING.getCode());
					videoPlanInfoVO.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
				}else if (currRole==RoleConstants.RISKMANAGEMENT_MINISTER.getCode()) {//风险部管理部长
					videoPlanInfoVO.setStatus(ApproveStateContants.CHECKPASS.getCode());
					videoPlanInfoVO.setNextApproval(result);//下一审批人为0
					
					if (videoPlanInfoVO.getStatus() == ApproveStateContants.CHECKPASS.getCode()) {
						//给视频部经理、风险管理部部长、物流金融中心总监发送信息提醒
						/*try {
							MessageUtil.sendMsg(new String[]{RoleConstants.VIDEO_AMALDAR.getCode()+"",
									RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
									RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""}, "视频检查计划审批",
									"/videoPlan.do?method=findListApprove", 1,MessageTypeContant.VIDEO.getCode(),videoPlanInfoVO.getVideoUserId());
						} catch (Exception e) {
							e.printStackTrace();
						}*/
						try {
							String[] roleIds = new String[]{RoleConstants.VIDEO_AMALDAR.getCode()+"",
									RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
									RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+""};
								for (String rid : roleIds) {
								int uid = MessageUtil.getUserId(rid);
										MessageService msgService = new MessageService();
										MessageVO msg = msgService.loadMsgByUserAndType(Integer.valueOf(uid), MessageTypeContant.DEALEREXIT.getCode());
										if(msg != null){
											int name = 1;
											if(StringUtil.isNumber(msg.getName())){
												name = Integer.parseInt(msg.getName())+1;
											}
											msg.setName(name+"");
											msgService.updMessage(msg);
										}else{
											MessageUtil.sendOrUpdateMeg(roleIds,  1+"", 
													"/videoPlan.do?method=findListApprove", 1,MessageTypeContant.VIDEO.getCode(),videoPlanInfoVO.getVideoUserId());
										}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
				//videoPlanInfoVO.setStatus(ApproveStateContants.CHECKPASS.getCode());//提交后设置状态为审核通过
				flag = vpiService.update(videoPlanInfoVO);
				
				
				if (flag) {
					marketApproval(videoPlanInfoVO,request,unCheckPassCause,1);//记录审批日志
					
					return findListApprove(mapping, form, request, response);
					
				}
			}
			}
		}else if(StringUtil.isNotEmpty(planCodes) && StringUtil.isEmpty(singleApproval)){
			flag = vpiService.update(planCodes);
			String[] planCodesInfo = planCodes.split(",");
			if (flag) {
				for (String planCode : planCodesInfo) {
					videoPlanInfoVO = vpiService.get(planCode);
					if (null != videoPlanInfoVO) {
						marketApproval(videoPlanInfoVO,request,"无",1);//批量审批通过,记录审批日志
					}
				}
				return findListApprove(mapping, form, request, response);
			}
			
		}
		flag = vpiService.update(videoPlanInfoVO);
		//return null;
		return findListApprove(mapping, form, request, response);
	}
	
	
	/**
	 * 审批操作(不通过)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward approveno(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String planCodes = request.getParameter("planCodes");
		VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodes);
		//审批不通过原因
		String unCheckPassCause = request.getParameter("unCheckPassCauseInfo");
		if (null != videoPlanInfoVO) {
			if (null != unCheckPassCause) {
				videoPlanInfoVO.setUnCheckPassCause(unCheckPassCause);//设置审批不通过原因
			}
			videoPlanInfoVO.setStatus(ApproveStateContants.UNCHECKPASS.getCode());//提交后设置状态为审核未通过
			videoPlanInfoVO.setNextApproval(0);//不通过下一审批人为0;
			boolean flag = vpiService.update(videoPlanInfoVO);
			if (flag) {
				marketApproval(videoPlanInfoVO,request,unCheckPassCause,2);//批量审批通过,记录审批日志
				return findListApprove(mapping, form, request, response);
			}
		}
		return null;
	}
	
	/**
	 * 记录审批日志
	 * @param flag
	 * @param inspectionPlanInfoVO
	 * @param request
	 * @param unCheckPassCause
	 */
	public void marketApproval(VideoPlanInfoVO videoPlanInfoVO, 
			HttpServletRequest request, String unCheckPassCause,Integer approvalState){
		UserVO userVO = gerUserVO(request);
		int currRole = getCurrRole(request);
		MarketApprovalVO approval = new MarketApprovalVO();
		try {
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(videoPlanInfoVO.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.VIDEOLANAPPROVE.getCode());//巡检计划审批
			approval.setCreateDate(new Date());
			approval.setRemark(unCheckPassCause);
			approval.setApprovalResult(approvalState);//不同意
			approval.setApprovalUserId(userVO.getId());
			approval.setApprovalUserRole(currRole);
			mSerivce.add(approval);//审批不通过  记录审批日志
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取审批日志
	 * @param videoPlanInfoVO
	 * @param request
	 */
	public void loadApprovalLog(VideoPlanInfoVO videoPlanInfoVO,HttpServletRequest request){
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(videoPlanInfoVO.getId());
			approvalQuery.setObjectType(ApprovalTypeContant.VIDEOLANAPPROVE.getCode());
			request.setAttribute("approvals", mSerivce.findListByApprovalType(approvalQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分页查询未审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		List<VideoPlanInfoQueryBean> list = service.findList(query, tools);
		
		service.deleteInfo();//删除未进行新增操作的经销商信息
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("video_plan_list");
		
	}
	
	/**
	 * 分页查询已审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListAlreadyCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<VideoPlanInfoQueryBean> list = service.findListAlreadyCheck(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("video_plan_list_alreadycheck");
		
	}
	
	/**
	 * 跳转新增页面 经销商列表 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward preAdd(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		loadDealerList(mapping, form, request, response);
		return mapping.findForward("add_stay_dealer");
	}
	
	/**
	 * 新增列表 经销商列表 分页查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward loadDealerList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		Integer status = videoPlanForm.getVideoPlanInfo().getStatus();
		if (StringUtil.isNotEmpty(planCodeInfo)) {
			query.setPlanCode(planCodeInfo);
			request.setAttribute("planCodeInfo", planCodeInfo);//计划编号
		}else{
			planCodeInfo = request.getParameter("planCodeInfo");
			query.setPlanCode(planCodeInfo);
			request.setAttribute("planCodeInfo", planCodeInfo);
		}
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		
		List<VideoPlanQueryBean> list = service.loadDealerList(query, tools);
		
		if (list != null && list.size() > 0) {
			for (VideoPlanQueryBean videoPlanQueryBean : list) {
				if (null != videoPlanQueryBean && videoPlanQueryBean.getRecentCheckTime() != null) {
					videoPlanQueryBean.setRecentCheckTime(videoPlanQueryBean.getRecentCheckTime().substring(0,10));
				}
			}
		}
		
		fillValues(list);
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}else{
			String state = request.getParameter("status");
			if (StringUtil.isNotEmpty(state)) {
				request.setAttribute("status", Integer.parseInt(state));
			}
		}
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("add_stay_dealer");
		
	}
	
	
	/**
	 * 删除已选经销商记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePlanCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanVO bean = videoPlanForm.getVideoPlan();
		service.delete(bean);
		request.setAttribute("flag", "preUpdate");
		return findListAlreadyDealer(mapping, form, request, response);
	}
	
	/**
	 * 批量删除经销商记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward batchDelete(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String ids = request.getParameter("ids");
		VideoPlanVO vo = null;
		if (StringUtil.isNotEmpty(ids)) {
			String[] planIds = ids.split(",");
			if (null != planIds && planIds.length > 0) {
				for (String id : planIds) {
					if (StringUtil.isNotEmpty(id)) {
						vo = service.get(Integer.parseInt(id));
						if (null != vo) {
							service.delete(vo);
						}
					}
				}
			}
		}
		request.setAttribute("flag", "preUpdate");
		return findListAlreadyDealer(mapping, form, request, response);
	}
	
	/**
	 * 删除计划列表记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePlanCodeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanInfoVO bean = videoPlanForm.getVideoPlanInfo();
		String planCode = videoPlanForm.getVideoPlanInfo().getPlanCode();//计划编号
		vpiService.delete(bean);//根据主键Id删除计划表的记录
		service.deleteByPlanCode(planCode);//根据计划编号  删除该编号对应的经销商记录
		return findList(mapping, form, request, response);
	}

	/**
	 * 跳转更新页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward preUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("flag", "preUpdate");
		return findListAlreadyDealer(mapping, form, request, response);
	}
	
	/**
	 * 提交操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole=getCurrRole(request);
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanInfoVO bean = videoPlanForm.getVideoPlanInfo();
		VideoPlanInfoVO videoPlanInfoVO = vpiService.get(bean.getId());
		String planCode = videoPlanInfoVO.getPlanCode();
		if (StringUtil.isNotEmpty(planCode)) {
			service.update(planCode);
		}
		if (currRole==RoleConstants.VIDEO_COMMISSIONER.getCode()) {
			videoPlanInfoVO.setNextApproval(RoleConstants.VIDEO_AMALDAR.getCode());
		}
		videoPlanInfoVO.setStatus(ApproveStateContants.UNCHECK.getCode());//提交后设置状态为待审核
		vpiService.update(videoPlanInfoVO);
//		service.sendMsg("视频检查计划", "/videoPlan.do?method=findListDealerLedger", 
//				String.valueOf(RoleConstants.VIDEO_AMALDAR.getCode()));//向视频经理发送消息
		return findList(mapping, form, request, response);
	}
	
	public ActionForward findListAlreadyDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		Object attribute = request.getAttribute("flag");
		if (StringUtil.isNotEmpty((String)attribute)) {
			if (attribute.equals("preUpdate")) {
				tools = ToolsFactory.getThumbPageTools("preUpdate", request);
				request.setAttribute("toolsFlag", "preUpdate");
			}
		}else{
			request.setAttribute("toolsFlag", "other");
		}
		
		//查询条件
		query.setCurrRole(currRole);
		
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		VideoPlanInfoVO videoPlanInfo = videoPlanForm.getVideoPlanInfo();
		Integer state = videoPlanForm.getVideoPlanInfo().getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			String unCheckPassCause = videoPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && state != null && (state == 4 || state == 3)) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
				request.setAttribute("status", ApproveStateContants.UNCHECKPASS.getCode());
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
				if (null != videoPlanQueryBean && null != videoPlanQueryBean.getVideoUserId()) {
					userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
					if (null != userVO) {
						videoPlanQueryBean.setVideoUserName(userVO.getUserName());
					}
				}
			}
			loadApprovalLog(videoPlanInfoVO,request);
		}
		fillValues(findListAlready);
		if (null != state && state > 0) {
			request.setAttribute("status", state);
		}else{
			if (StringUtil.isNotEmpty(request.getParameter("status"))) {
				request.setAttribute("status", Integer.parseInt(request.getParameter("status")));
			}
		}
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("add_already_dealer");
		
	}
	
	/**
	 * 视频检查计划台账
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListDealerLedger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		
		//查询条件
		query.setCurrRole(currRole);
		
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		//查询条件
		findListAlready = service.findListDealerLedger(query, tools, request);//同一计划编号  多次选择   查询已选数据
		for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
			if (null != videoPlanQueryBean && null != videoPlanQueryBean.getVideoUserId()) {
				userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
				if (null != userVO) {
					videoPlanQueryBean.setVideoUserName(userVO.getUserName());
				}
			}
		}
		fillValues(findListAlready);
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("video_plan_ledger");
		
	}
	
	
	//已审核详情页
	public ActionForward detailAlreadyCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		int id = videoPlanForm.getVideoPlan().getId();
		List<VideoPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		VideoPlanInfoVO videoPlanInfo = videoPlanForm.getVideoPlanInfo();
		Integer status = videoPlanInfo.getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			String unCheckPassCause = videoPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				videoPlanInfo.setUnCheckPassCause(unCheckPassCause);
				videoPlanForm.setVideoPlanInfo(videoPlanInfo);
			}
			
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (VideoPlanQueryBean videoPlanQueryBean : findListAlready) {
				userVO = uservice.get(videoPlanQueryBean.getVideoUserId());
				if (null != userVO) {
					videoPlanQueryBean.setVideoUserName(userVO.getUserName());
				}
			}
			loadApprovalLog(videoPlanInfoVO,request);
		}
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		
		fillValues(findListAlready);
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("detail_dealer_alreadycheck");
		
	}
	
	/**
	 * 新增操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		VideoPlanQueryVO query = new VideoPlanQueryVO();
		//查询条件
		query.setCurrRole(currRole);
		
		String dealerIds = request.getParameter("ids");		
		
		VideoPlanVO videoPlanVO = null;
		VideoPlanQueryBean loadDealerInfo = null;
		VideoPlanInfoVO videoPlanInfoVO = new VideoPlanInfoVO();
		
		
		List<VideoPlanQueryBean> videoPlanQueryBeanList = new ArrayList<VideoPlanQueryBean>();
		String planCodeInfo = request.getParameter("planCodeInfo");
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			query.setPlanCode(planCodeInfo);
			
			if (StringUtil.isNotEmpty(dealerIds)) {
				String[] dealerIdsArr = dealerIds.split(",");
				if (null != dealerIdsArr && dealerIdsArr.length > 0) {
					for (String dealerId : dealerIdsArr) {
						//根据经销商Id查询相应对象   进行数据填充
						if(StringUtil.isNotEmpty(dealerId)){
							videoPlanVO = new VideoPlanVO();
							videoPlanVO.setVideoUserId(user.getId());
							int videoPlanVOId = service.add(videoPlanVO);
							loadDealerInfo = service.loadDealerInfo(Integer.parseInt(dealerId));
							if (null != loadDealerInfo) {
								loadDealerInfo.setId(videoPlanVOId);//设置主键videoPlan 表 主键Id
								videoPlanVO.setDealerId(loadDealerInfo.getDealerId());//t_video_plan表的经销商Id
								videoPlanVO.setStock(loadDealerInfo.getStock());//t_video_plan表的库存
								if (StringUtil.isNotEmpty(loadDealerInfo.getRecentCheckTime())) {
									loadDealerInfo.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));
									videoPlanVO.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));//最近检查时间
								}
							}
							if (Integer.parseInt(planCodeInfo) > 0) {
								videoPlanVO.setPlanCode(planCodeInfo);//t_video_plan表的计划编号
							}
							service.update(videoPlanVO);
						}
					}
					//保存数据到  videoPlan 表中
					
				}
			}
		}else{
			if (StringUtil.isNotEmpty(dealerIds)) {
				String[] dealerIdsArr = dealerIds.split(",");
				if (null != dealerIdsArr && dealerIdsArr.length > 0) {
					videoPlanInfoVO.setVideoUserId(user.getId());
//					videoPlanInfoVO.setVideoUserName(user.getUserName());//设置视频专员名称
					int id = vpiService.add(videoPlanInfoVO);
					for (String dealerId : dealerIdsArr) {
						//根据经销商Id查询相应对象   进行数据填充
						if(StringUtil.isNotEmpty(dealerId)){
							videoPlanVO = new VideoPlanVO();
							videoPlanVO.setVideoUserId(user.getId());
							int videoPlanVOId = service.add(videoPlanVO);
							loadDealerInfo = service.loadDealerInfo(Integer.parseInt(dealerId));
							if (null != loadDealerInfo) {
								loadDealerInfo.setId(videoPlanVOId);//设置主键videoPlan 表 主键Id
								loadDealerInfo.setVideoUserId(user.getId());
								videoPlanVO.setDealerId(loadDealerInfo.getDealerId());//t_video_plan表的经销商Id
								videoPlanVO.setStock(loadDealerInfo.getStock());//t_video_plan表的库存
								videoPlanQueryBeanList.add(loadDealerInfo);
								if (StringUtil.isNotEmpty(loadDealerInfo.getRecentCheckTime())) {
									loadDealerInfo.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));
									videoPlanVO.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));//最近检查时间
								}
								
							}
							if (id > 0) {
								videoPlanVO.setPlanCode(id + "");//t_video_plan表的计划编号
							}
							service.update(videoPlanVO);
						}
					}
					videoPlanInfoVO.setStatus(0);//不展示
					videoPlanInfoVO.setPlanCode(id + "");//t_video_planinfo 表的计划编号
					vpiService.update(videoPlanInfoVO);//程序到此      (计划执行时间	检查小时合计   审批不通过原因),后面程序维护
					//保存数据到  videoPlan 表中
					
				}
			}
		}
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			
			videoPlanQueryBeanList = service.findListAlready(query, tools);
			fillValues(videoPlanQueryBeanList);
			request.setAttribute("list", videoPlanQueryBeanList);
			request.setAttribute("planCodeInfo", planCodeInfo);//计划编号
			
		}else{
			fillValues(videoPlanQueryBeanList);
			request.setAttribute("list", videoPlanQueryBeanList);
			request.setAttribute("planCodeInfo", videoPlanInfoVO.getPlanCode());//计划编号
		}
		VideoPlanInfoVO videoPlanInfo = vpiService.get(planCodeInfo);
		if (null != videoPlanInfo) {
			loadApprovalLog(videoPlanInfo, request);
			videoPlanForm.setVideoPlanInfo(videoPlanInfo);
		}
		String state = request.getParameter("status");
		if (StringUtil.isNotEmpty(state)) {
			request.setAttribute("status", Integer.parseInt(state));
		}
		request.setAttribute("toolsFlag", "other");
		return mapping.findForward("add_already_dealer");
	}
	
	/**
	 * 保存经销商信息记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveDealers(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		//查询条件
		query.setCurrRole(currRole);
		
		String planCodeInfo = videoPlanForm.getVideoPlan().getPlanCode();
		if (StringUtil.isNotEmpty(planCodeInfo)) {
			//根据计划编号统计对应经销商信息
			VideoPlanInfoQueryBean dealerInfoByPlanCode = vpiService.getDealerInfoByPlanCode(planCodeInfo);
			double hours = 0;
			VideoPlanInfoVO videoPlanInfoVO = vpiService.get(planCodeInfo);
			if (null != videoPlanInfoVO) {
				if (null != dealerInfoByPlanCode) {
					videoPlanInfoVO.setStores(dealerInfoByPlanCode.getStores());//涉及店数
					videoPlanInfoVO.setStockAmount(dealerInfoByPlanCode.getStockAmount());//库存合计
					if (StringUtil.isNotEmpty(dealerInfoByPlanCode.getPlanExecuteTime())) {
						videoPlanInfoVO.setPlanExecuteTime(dealerInfoByPlanCode.getPlanExecuteTime().substring(0,10));//计划执行时间(取经销商列表的预计检查日期最大值)
					}
					Double checkHoursAmount = dealerInfoByPlanCode.getCheckHoursAmount();//检查小时合计
//					if (null != checkHoursAmount && checkHoursAmount > 0) {
//						hours = checkHoursAmount % 60 > 0?
//								((checkHoursAmount % 60)<=30?(Math.floor(checkHoursAmount / 60) + 0.5):(Math.floor(checkHoursAmount / 60) + 1))
//								:checkHoursAmount / 60;
//					}
					if (null != checkHoursAmount && checkHoursAmount > 0) {
						hours = checkHoursAmount % 60;
						if (hours > 0) {
							if(hours % 60 <=30){
								hours = Math.floor(checkHoursAmount / 60) + 0.5;
							}else{
								hours = Math.floor(checkHoursAmount / 60) + 1;
							}
						}else{
							hours = checkHoursAmount / 60;
						}
					}
				}
				videoPlanInfoVO.setStatus(ApproveStateContants.UNCOMMIT.getCode());//设置状态为未提交状态
				videoPlanInfoVO.setCheckHoursAmount(hours);
				//Date createDate = DateUtil.StringToDate(format.format(new Date()));
				videoPlanInfoVO.setCreateDate(new Date());//计划创建时间
				vpiService.update(videoPlanInfoVO);
			}
		}
		
		return findList(mapping, form, request, response);
	}
	
	//填充对象的各级银行名称
	public void fillValues(List<VideoPlanQueryBean> videoPlanQueryBeanList){
		if (null != videoPlanQueryBeanList && videoPlanQueryBeanList.size() > 0) {
			for (VideoPlanQueryBean videoPlanQueryBean : videoPlanQueryBeanList) {
				if (StringUtil.isNotEmpty(videoPlanQueryBean.getBankFullName())) {
					String[] bankNames = videoPlanQueryBean.getBankFullName().split("/");
					if (null != bankNames && bankNames.length > 0 && bankNames.length == 2) {
						videoPlanQueryBean.setOneBankName(bankNames[0]);
						videoPlanQueryBean.setTwoBankName(bankNames[1]);
						videoPlanQueryBean.setThreeBankName("");
					}else if (null != bankNames && bankNames.length > 0 && bankNames.length == 3) {
						videoPlanQueryBean.setOneBankName(bankNames[0]);
						videoPlanQueryBean.setTwoBankName(bankNames[1]);
						videoPlanQueryBean.setThreeBankName(bankNames[2]);
					}
				}
			}
		}
	}
	
	/*
	 * 需求38 -- 导出视频检查计划台账
	 * @time 20170518
	*/
	public ActionForward ExtVideoFindListDealerLedger(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request);
		
		VideoPlanForm videoPlanForm = (VideoPlanForm) form;
		VideoPlanQueryVO query = videoPlanForm.getQuery();
		
		String[] titles = {"序号","视频专员","经销商","总行/金融机构","分行/分支机构","支行","品牌","省份","城市",
				"库存数量","预计检查分钟","预计检查日期"};
		String filename = "视频检查计划台账";
		
		List<VideoPlanQueryBean> list = service.ExtVideoFindListDealerLedger(query);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	VideoPlanQueryBean vo = list.get(i);
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getVideoUserName());
	    	
	    	tool.setCellValue(i+1, 2, vo.getDealerName());
	    	
	    	tool.setCellValue(i+1, 3, vo.getOneBankName());
	    	
	    	tool.setCellValue(i+1, 4, vo.getTwoBankName());
	    	
	    	tool.setCellValue(i+1, 5, vo.getThreeBankName());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBrandName());
	    	
	    	tool.setCellValue(i+1, 7, vo.getProvinceName());
	    	
	    	tool.setCellValue(i+1, 8, vo.getCityName());
	    	
	    	tool.setCellValue(i+1, 9, vo.getStock());
	    	
	    	tool.setCellValue(i+1, 10, vo.getCheckMinute());
	    	
	    	if (vo.getPredictCheckDate()!=null) {
	    		String predictCheckDate = format.format(vo.getPredictCheckDate());
	    		tool.setCellValue(i+1, 11, predictCheckDate);
			}else {
				tool.setCellValue(i+1, 11, "");
			}
	    	
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
	    return null;
	}
	
}
