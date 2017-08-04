package com.zd.csms.planandreport.web.action;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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
import com.zd.csms.planandreport.model.InspectionPlanInfoVO;
import com.zd.csms.planandreport.model.InspectionPlanQueryVO;
import com.zd.csms.planandreport.model.InspectionPlanVO;
import com.zd.csms.planandreport.model.VideoPlanInfoVO;
import com.zd.csms.planandreport.model.VideoPlanVO;
import com.zd.csms.planandreport.querybean.InspectionPlanInfoQueryBean;
import com.zd.csms.planandreport.querybean.InspectionPlanQueryBean;
import com.zd.csms.planandreport.service.InspectionPlanInfoService;
import com.zd.csms.planandreport.service.InspectionPlanService;
import com.zd.csms.planandreport.web.form.InspectionPlanForm;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.util.DateUtil;
import com.zd.csms.util.Util;
import com.zd.csms.windcontrol.model.InspectionInfoVO;
import com.zd.csms.windcontrol.service.InspectionService;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class InspectionPlanAction extends ActionSupport {
	private InspectionPlanService service = new InspectionPlanService();
	private InspectionPlanInfoService iservice = new InspectionPlanInfoService();
	private UserService uservice = new UserService();
	private InspectionService inspecService = new InspectionService();
	private MarketApprovalSerivce mSerivce = new MarketApprovalSerivce();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	private NumberFormat nt = NumberFormat.getPercentInstance();

	/**
	 * 角色:根据不同的部门分开判断 (20,26,18,17,19)
	 */
	private static int[] approvalRole = new int[] {
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),//总监
			RoleConstants.RISKMANAGEMENT_MINISTER.getCode(),//风险管理部部长
			RoleConstants.WINDCONRTOL_AMALDAR.getCode(),//风控部经理
			RoleConstants.WINDCONRTOL_INTERNAL.getCode(),//风控内控专员
			RoleConstants.WINDCONRTOL_DATA.getCode(),//风控部数据专员
			RoleConstants.WINDCONRTOL_EXTERNAL.getCode(),//风控外控专员
			RoleConstants.BANK_APPROVE.getCode()
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
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
		Integer status = inspectionPlanForm.getInspectionPlanInfo().getStatus();
		
		List<InspectionPlanQueryBean> list = service.loadDealerList(query, tools);
		if (list != null && list.size() > 0) {
			for (InspectionPlanQueryBean inspectionPlanQueryBean : list) {
				if (null != inspectionPlanQueryBean && inspectionPlanQueryBean.getRecentCheckTime() != null) {
					inspectionPlanQueryBean.setRecentCheckTime(inspectionPlanQueryBean.getRecentCheckTime().substring(0,10));
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
	 * 选择经销商信息跳转至已选列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	//TODO 陪同人员   最近检查时间(两个字段的值有待维护)
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		InspectionPlanQueryVO query = new InspectionPlanQueryVO();
		//查询条件
		query.setCurrRole(currRole);
		String dealerIds = request.getParameter("ids");		
		
		InspectionPlanVO inspectionPlanVO = null;
		InspectionPlanQueryBean loadDealerInfo = null;
		InspectionPlanInfoVO inspectionPlanInfoVO = new InspectionPlanInfoVO();
		
		List<InspectionPlanQueryBean> inspectionPlanQueryBeanList = new ArrayList<InspectionPlanQueryBean>();
		String planCodeInfo = request.getParameter("planCodeInfo");
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			query.setPlanCode(planCodeInfo);
			
			if (StringUtil.isNotEmpty(dealerIds)) {
				String[] dealerIdsArr = dealerIds.split(",");
				if (null != dealerIdsArr && dealerIdsArr.length > 0) {
					for (String dealerId : dealerIdsArr) {
						//根据经销商Id查询相应对象   进行数据填充
						if(StringUtil.isNotEmpty(dealerId)){
							inspectionPlanVO = new InspectionPlanVO();//已选经销商信息存储对象
							//inspectionPlanVO.setOutControlUserId(user.getId());
							int videoPlanVOId = service.add(inspectionPlanVO);
							loadDealerInfo = service.loadDealerInfo(Integer.parseInt(dealerId));
							if (null != loadDealerInfo) {
								loadDealerInfo.setId(videoPlanVOId);//设置主键videoPlan 表 主键Id
								inspectionPlanVO.setDealerId(loadDealerInfo.getDealerId());//t_video_plan表的经销商Id
								inspectionPlanVO.setStock(loadDealerInfo.getStock());//t_video_plan表的库存
								if (StringUtil.isNotEmpty(loadDealerInfo.getRecentCheckTime())) {
									loadDealerInfo.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));
									inspectionPlanVO.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));//最近检查时间
								}
								
							}
							if (Integer.parseInt(planCodeInfo) > 0) {
								inspectionPlanVO.setPlanCode(planCodeInfo);//t_video_plan表的计划编号
							}
							service.update(inspectionPlanVO);
						}
					}
					//保存数据到  videoPlan 表中
				}
			}
		}else{
			if (StringUtil.isNotEmpty(dealerIds)) {
				String[] dealerIdsArr = dealerIds.split(",");
				if (null != dealerIdsArr && dealerIdsArr.length > 0) {
					inspectionPlanInfoVO.setInControlUserId(user.getId());//巡检计划表内控专员Id
					inspectionPlanInfoVO.setLastUpdateDate(new Date());
					int id = iservice.add(inspectionPlanInfoVO);//添加到巡检计划表  返回主键Id
					for (String dealerId : dealerIdsArr) {
						//根据经销商Id查询相应对象   进行数据填充
						if(StringUtil.isNotEmpty(dealerId)){
							inspectionPlanVO = new InspectionPlanVO();
							//inspectionPlanVO.setOutControlUserId(user.getId());//已选经销商信息表外控专员Id
							int inspectionPlanVOId = service.add(inspectionPlanVO);
							loadDealerInfo = service.loadDealerInfo(Integer.parseInt(dealerId));
							if (null != loadDealerInfo) {
								loadDealerInfo.setId(inspectionPlanVOId);//设置主键videoPlan 表 主键Id
								//loadDealerInfo.setOutControlUserId(user.getId());
								inspectionPlanVO.setDealerId(loadDealerInfo.getDealerId());//t_video_plan表的经销商Id
								inspectionPlanVO.setStock(loadDealerInfo.getStock());//t_video_plan表的库存
								if (StringUtil.isNotEmpty(loadDealerInfo.getRecentCheckTime())) {
									loadDealerInfo.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));
									inspectionPlanVO.setRecentCheckTime(loadDealerInfo.getRecentCheckTime().substring(0, 10));//最近检查时间
								}
								inspectionPlanQueryBeanList.add(loadDealerInfo);
								
							}
							if (id > 0) {
								inspectionPlanVO.setPlanCode(id + "");//t_video_plan表的计划编号
							}
							service.update(inspectionPlanVO);
						}
					}
					inspectionPlanInfoVO.setStatus(0);//不展示
					inspectionPlanInfoVO.setPlanCode(id + "");//t_video_planinfo 表的计划编号
					inspectionPlanInfoVO.setMkTableUserId(user.getId());//设置制表人Id
					inspectionPlanInfoVO.setLastUpdateDate(new Date());//设置最后更新时间
					iservice.update(inspectionPlanInfoVO);//程序到此      (计划执行时间	检查小时合计   审批不通过原因),后面程序维护
					//保存数据到  videoPlan 表中
					
				}
			}
		}
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			getStatisticInfo(request,planCodeInfo);
			inspectionPlanQueryBeanList = service.findListAlready(query, tools);
			fillValues(inspectionPlanQueryBeanList);
			request.setAttribute("list", inspectionPlanQueryBeanList);
			request.setAttribute("planCodeInfo", planCodeInfo);//计划编号
			
		}else{
			fillValues(inspectionPlanQueryBeanList);
			getStatisticInfo(request,inspectionPlanInfoVO.getPlanCode());
			request.setAttribute("list", inspectionPlanQueryBeanList);
			request.setAttribute("planCodeInfo", inspectionPlanInfoVO.getPlanCode());//计划编号
		}
		
		InspectionPlanInfoVO inspectionPlanInfo = iservice.get(planCodeInfo);
		if (null != inspectionPlanInfo) {
			loadApprovalLog(inspectionPlanInfo, request);
			inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
		}
		
		String state = request.getParameter("status");
		if (StringUtil.isNotEmpty(state)) {
			request.setAttribute("status", Integer.parseInt(state));
		}
		
		request.setAttribute("toolsFlag", "other");
		return mapping.findForward("add_already_dealer");
	}
	
	/**
	 * 获取已选经销商列表信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListAlreadyDealer(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
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
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		Integer state = inspectionPlanForm.getInspectionPlanInfo().getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && state != null && state == 4) {
				inspectionPlanInfoVO.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfoVO);
				request.setAttribute("status", ApproveStateContants.UNCHECKPASS.getCode());
			}
			
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			if (null != findListAlready && findListAlready.size() > 0) {
				for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
					if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
						userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
						if (null != userVO) {
							inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
						}
					}
				}
			}
			loadApprovalLog(inspectionPlanInfoVO,request);
		}
		fillValues(findListAlready);
		if (null != state && state > 0) {
			request.setAttribute("status", state);
		}else{
			if (StringUtil.isNotEmpty(request.getParameter("status"))) {
				request.setAttribute("status", Integer.parseInt(request.getParameter("status")));
			}
		}
		getStatisticInfo(request,planCodeInfo);//填充经销商列表统计信息
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("add_already_dealer");
		
	}
	
	/**
	 * 巡检计划台账 -- 巡检计划未审核台账
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		query.setFlag(1);
		query.setUserVO(user);
		List<InspectionPlanInfoQueryBean> list = service.findListLedger(query, tools);
		getPerformance(query,list);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("inspection_plan_list_ledger");
		
	}
	
	/**
	 * 统计巡检计划完成情况
	 * @param query
	 * @param list
	 * @throws Exception
	 */
	public void getPerformance(InspectionPlanQueryVO query,
			List<InspectionPlanInfoQueryBean> list) throws Exception{
		String planCode = null;
		InspectionInfoVO inspectionInfoVO = null;
		List<InspectionPlanQueryBean> findListAlready = null;
		for (InspectionPlanInfoQueryBean inspectionPlanInfoQueryBean : list) {
			int inspectionPlans = 0;
			int value = 0;
			if (null != inspectionPlanInfoQueryBean) {
				planCode = inspectionPlanInfoQueryBean.getPlanCode();
				if (StringUtil.isNotEmpty(planCode)) {
					//根据编号查询所有经销商记录 
					query.setPlanCode(planCode);
					findListAlready = service.findListByPlanCode(query);
					if (null != findListAlready && findListAlready.size() > 0) {
						inspectionPlans = findListAlready.size();
						for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
							if (inspectionPlanQueryBean != null && inspectionPlanQueryBean.getId() > 0) {
								//根据经销商记录id判断  该记录是否已填写报告
								inspectionInfoVO = inspecService.getInspectionInfoVO(inspectionPlanQueryBean.getId());
								if(null != inspectionInfoVO){
									value++;
								}
							}
						}
						if (value > 0) {
							double i = (double) value / inspectionPlans;
							nt.setMinimumFractionDigits(2);
							if (StringUtil.isNotEmpty(nt.format(i))) {
								inspectionPlanInfoQueryBean.setPerformance(nt.format(i));
							}
						}else{
							inspectionPlanInfoQueryBean.setPerformance("未开始");
						}
					}
					
				}
				
			}
		}
	}
	
	
	
	/**
	 * 分页查询已审核列表(台账)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findListDealerLedgerNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);

		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		query.setUserVO(user);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<InspectionPlanInfoQueryBean> list = service.findListLedger(query, tools);
		getPerformance(query,list);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("inspection_plan_list_ledgerno");
		
	}

	/**
	 * 分页查询(未审核)
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
		boolean fkFlag;
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		tools.saveQueryVO(query);
		List<InspectionPlanInfoQueryBean> list = service.findList(query, tools);
		
		service.deleteInfo();//删除选择了经销商但未进行新增操作的数据
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		
		//确认角色是否是外控专员，如果是，则只能查看详情
		if(user!=null){
			fkFlag = inspecService.judgeUserRole(user.getId(), 19);
			if(fkFlag){
				request.setAttribute("wkzyflag", 1);
			}else{
				request.setAttribute("wkzyflag", 0);
			}
		}
		
		return mapping.findForward("inspection_plan_list");
		
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
	 * 提交巡检计划
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanInfoVO bean = inspectionPlanForm.getInspectionPlanInfo();
		
		InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(bean.getId());
		DateUtil.getStringFormatByDate(new Date(), "yyyy-MM-dd");
		inspectionPlanInfoVO.setPlanSubmitTime(DateUtil.getStringFormatByDate(new Date(), "yyyy-MM-dd"));
	
		inspectionPlanInfoVO.setStatus(ApproveStateContants.UNCHECK.getCode());//提交后设置状态为待审核
		inspectionPlanInfoVO.setNextApproval(RoleConstants.WINDCONRTOL_AMALDAR.getCode());		
		iservice.update(inspectionPlanInfoVO);
//		service.sendMsg("巡检计划", "/inspectionPlan.do?method=findList", 
//				String.valueOf(RoleConstants.WINDCONRTOL_AMALDAR.getCode()));//向风控部经理发送消息
		return findList(mapping, form, request, response);
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		if (StringUtil.isNotEmpty(planCodeInfo)) {
			//根据计划编号统计对应经销商信息
			InspectionPlanInfoQueryBean dealerInfoByPlanCode = iservice.getDealerInfoByPlanCode(planCodeInfo);
			InspectionPlanInfoQueryBean amountInfo = iservice.getDealerInfo(planCodeInfo);
			
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			if (null != inspectionPlanInfoVO && null != dealerInfoByPlanCode && null != amountInfo) {
				
				inspectionPlanInfoVO.setStores(null != amountInfo.getStores() ? amountInfo.getStores():0);//涉及店数
				inspectionPlanInfoVO.setStockAmount(null != amountInfo.getStockAmount() ? amountInfo.getStockAmount():0);//库存合计
				inspectionPlanInfoVO.setProvinceAmount(null != dealerInfoByPlanCode.getProvinceAmount() ? dealerInfoByPlanCode.getProvinceAmount() : 0);//省数
				inspectionPlanInfoVO.setCityAmount(null != dealerInfoByPlanCode.getCityAmount() ? dealerInfoByPlanCode.getCityAmount() : 0);
				inspectionPlanInfoVO.setBrandAmount(null != dealerInfoByPlanCode.getBrandAmount() ? dealerInfoByPlanCode.getBrandAmount() : 0);
				inspectionPlanInfoVO.setBankAmount(null != dealerInfoByPlanCode.getBankAmount() ? dealerInfoByPlanCode.getBankAmount() : 0);
				inspectionPlanInfoVO.setPlanBeginTime(StringUtil.isNotEmpty(amountInfo.getPlanBeginTime()) ? amountInfo.getPlanBeginTime().substring(0,10) : "");
//				if (StringUtil.isNotEmpty(amountInfo.getPlanBeginTimeMax()) &&
//						StringUtil.isNotEmpty(amountInfo.getPlanBeginTime())) {
//					String dateTimeErrand = DateUtil.dateTimeErrand(amountInfo.getPlanBeginTime(), amountInfo.getPlanBeginTimeMax());
//					if (StringUtil.isNotEmpty(dateTimeErrand) && dateTimeErrand.indexOf(":") != -1) {
//						String num = dateTimeErrand.substring(0, dateTimeErrand.indexOf(":"));
//						if (StringUtil.isNotEmpty(num)) {
//							inspectionPlanInfoVO.setPredictCheckdays(Integer.parseInt(num));
//						}
//					}
//				}
				//设置预计检查天数
				inspectionPlanInfoVO.setPredictCheckdays(null != amountInfo.getPredictCheckdays() ? amountInfo.getPredictCheckdays() : 0);
				inspectionPlanInfoVO.setStatus(ApproveStateContants.UNCOMMIT.getCode());//设置状态为未提交状态
				Date date = DateUtil.StringToDate(format.format(new Date()));
				inspectionPlanInfoVO.setCreateDate(date);//计划创建时间
				
				iservice.update(inspectionPlanInfoVO);
			}
		}
		
		return findList(mapping, form, request, response);
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

		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<InspectionPlanInfoQueryBean> list = service.findListAlreadyCheck(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("inspection_plan_list_alreadycheck");
		
	}
	
	/**
	 * 分页查询(风控经理未审批列表)
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		query.setFlag(1);
		tools.saveQueryVO(query);
		List<InspectionPlanInfoQueryBean> list = service.findList(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("inspection_plan_list_approve");
		
	}
	
	/**
	 * 分页查询(风控经理已审批列表)
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		
		//查询条件
		query.setCurrRole(currRole);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("list", request);
		//tools.saveQueryVO(query);
		List<InspectionPlanInfoQueryBean> list = service.findListAlreadyCheck(query, tools);
		
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", list);
		return mapping.findForward("inspection_plan_list_approveno");
		
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		//int id = inspectionPlanForm.getInspectionPlan().getId();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		//新增
		Integer status = inspectionPlanInfo.getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request, planCodeInfo);
			
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			status = inspectionPlanInfoVO.getStatus();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode() ||
					status == ApproveStateContants.UNCHECK.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			loadApprovalLog(inspectionPlanInfoVO,request);
			
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		Integer status = inspectionPlanInfo.getStatus();
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			//tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request,planCodeInfo);
			loadApprovalLog(inspectionPlanInfoVO, request);
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
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		query.setOutControlUserId(user.getId());
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		Integer status = inspectionPlanInfo.getStatus();
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		
		int id = inspectionPlanForm.getInspectionPlan().getId();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件

			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request,planCodeInfo);
			loadApprovalLog(inspectionPlanInfoVO,request);
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
	 * 台账未审批详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailLedger(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detailLedger", request);
		//查询条件
		query.setCurrRole(currRole);
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		Integer status = inspectionPlanInfo.getStatus();
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		
		int id = inspectionPlanForm.getInspectionPlan().getId();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request,planCodeInfo);
			loadApprovalLog(inspectionPlanInfoVO, request);
		}
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		
		fillValues(findListAlready);
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("inspection_plan_detail_ledger");
		
	}
	
	/**
	 * 台账已审批详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailLedgerNo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detailLedgerNo", request);
		//查询条件
		query.setCurrRole(currRole);
		
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		int id = inspectionPlanForm.getInspectionPlan().getId();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		Integer status = inspectionPlanInfo.getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request,planCodeInfo);
			loadApprovalLog(inspectionPlanInfoVO, request);
		}
		if (null != status && status > 0) {
			request.setAttribute("status", status);
		}
		
		fillValues(findListAlready);
		request.setAttribute("userId", user.getId());
		request.setAttribute("currRole", currRole);
		request.setAttribute("list", findListAlready);
		return mapping.findForward("inspection_plan_detail_ledgerno");
		
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
		
		String planCodes = request.getParameter("planCodes");	
		String unCheckPassCause = request.getParameter("unCheckPassCauseInfo");
		String singleApproval = request.getParameter("singleApproval");//判断是否为单个审批通过  而不是批量审批通过
		int currRole = getCurrRole(request);
		boolean flag = false;
		InspectionPlanInfoVO inspectionPlanInfoVO = null;
		if (StringUtil.isNotEmpty(planCodes) && StringUtil.isNotEmpty(singleApproval) && singleApproval.equals("1")) {
			inspectionPlanInfoVO = iservice.get(planCodes);
			if (null != inspectionPlanInfoVO) {
				if (null != unCheckPassCause) {
					inspectionPlanInfoVO.setUnCheckPassCause(unCheckPassCause);//设置审批意见
				}
				if (currRole == RoleConstants.WINDCONRTOL_AMALDAR.getCode()) {
					
					inspectionPlanInfoVO.setStatus(ApproveStateContants.CHECKING.getCode());//提交后设置状态为正在审核
					inspectionPlanInfoVO.setNextApproval(RoleConstants.RISKMANAGEMENT_MINISTER.getCode());
				
				}else if (currRole==RoleConstants.RISKMANAGEMENT_MINISTER.getCode()) {
					
					inspectionPlanInfoVO.setStatus(ApproveStateContants.CHECKPASS.getCode());//提交后设置状态为审核通过
					inspectionPlanInfoVO.setNextApproval(0);//下一审批人为0
					//给风控经理、风险管理部部长、物流金融中心总监、外控专员发送信息提醒
					/*try {
						MessageUtil.sendMsg(new String[]{RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
								RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
								RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+"",
								RoleConstants.WINDCONRTOL_EXTERNAL.getCode()+""}, "巡检计划审批通过提醒",
								"/inspectionPlan.do?method=findListApprove", 1,MessageTypeContant.PATROLPLAN.getCode(),inspectionPlanInfoVO.getMkTableUserId());
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					try {
						String[] roleIds = new String[]{RoleConstants.WINDCONRTOL_AMALDAR.getCode()+"",
								RoleConstants.RISKMANAGEMENT_MINISTER.getCode()+"",
								RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode()+"",
								RoleConstants.WINDCONRTOL_EXTERNAL.getCode()+""};
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
												"/inspectionPlan.do?method=findListApprove", 1,MessageTypeContant.PATROLPLAN.getCode(),inspectionPlanInfoVO.getMkTableUserId());
									}
								}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				//inspectionPlanInfoVO.setStatus(ApproveStateContants.CHECKPASS.getCode())//提交后设置状态为审核通过
				flag = iservice.update(inspectionPlanInfoVO);
				if (flag) {
					marketApproval(inspectionPlanInfoVO,request,unCheckPassCause,1);//记录审批日志
					return findListApprove(mapping, form, request, response);
				}
			//	inspectionPlanInfoVO.setStatus(ApproveStateContants.CHECKPASS.getCode())//提交后设置状态为审核通过
				flag = iservice.update(inspectionPlanInfoVO);
				if (flag) {
					marketApproval(inspectionPlanInfoVO,request,unCheckPassCause,1);//记录审批日志
					return findListApprove(mapping, form, request, response);
				}
			}
			
		}else if(StringUtil.isNotEmpty(planCodes) && StringUtil.isEmpty(singleApproval)){
			flag = iservice.update(planCodes);
			String[] planCodesInfo = planCodes.split(",");
			if (flag) {
				for (String planCode : planCodesInfo) {
					inspectionPlanInfoVO = iservice.get(planCode);
					if (null != inspectionPlanInfoVO) {
						marketApproval(inspectionPlanInfoVO,request,"无",1);//批量审批通过,记录审批日志
					}
				}
				return findListApprove(mapping, form, request, response);
			}
		}
		return null;
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
		InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodes);
		//审批意见
		String unCheckPassCause = request.getParameter("unCheckPassCauseInfo");
		if (null != inspectionPlanInfoVO) {
			if (null != unCheckPassCause) {
				inspectionPlanInfoVO.setUnCheckPassCause(unCheckPassCause);//设置审批意见
			}
			inspectionPlanInfoVO.setStatus(ApproveStateContants.UNCHECKPASS.getCode());//提交后设置状态为审核未通过
			inspectionPlanInfoVO.setNextApproval(0);//下一审批人为0
			boolean flag = iservice.update(inspectionPlanInfoVO);
			if (flag) {
				marketApproval(inspectionPlanInfoVO,request,unCheckPassCause,2);//记录审批日志
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
	public void marketApproval(InspectionPlanInfoVO inspectionPlanInfoVO, 
			HttpServletRequest request, String unCheckPassCause,Integer approvalState){
		UserVO userVO = gerUserVO(request);
		int currRole = getCurrRole(request);
		MarketApprovalVO approval = new MarketApprovalVO();
		try {
			approval.setId(Util.getID(MarketApprovalVO.class));
			approval.setApprovalObjectId(inspectionPlanInfoVO.getId());
			approval.setApprovalPerson(userVO.getUserName());
			approval.setApprovalType(ApprovalTypeContant.INSPECTIONPLANAPPROVE.getCode());//巡检计划审批
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
	 * @param inspectionPlanInfoVO
	 * @param request
	 */
	public void loadApprovalLog(InspectionPlanInfoVO inspectionPlanInfoVO,HttpServletRequest request){
		try {
			MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
			approvalQuery.setObjectId(inspectionPlanInfoVO.getId());
			approvalQuery.setObjectType(ApprovalTypeContant.INSPECTIONPLANAPPROVE.getCode());
			request.setAttribute("approvals", mSerivce.findListByApprovalType(approvalQuery));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 已审核详情页
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailAlreadyCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("detail", request);
		//查询条件
		query.setCurrRole(currRole);
		query.setOutControlUserId(user.getId());
		
		String planCodeInfo = inspectionPlanForm.getInspectionPlan().getPlanCode();
		int id = inspectionPlanForm.getInspectionPlan().getId();
		List<InspectionPlanQueryBean> findListAlready = null;
		UserVO userVO = null;
		
		InspectionPlanInfoVO inspectionPlanInfo = inspectionPlanForm.getInspectionPlanInfo();
		Integer status = inspectionPlanInfo.getStatus();
		
		if (StringUtil.isNotEmpty(planCodeInfo)) {//同一计划编号  多次选择
			InspectionPlanInfoVO inspectionPlanInfoVO = iservice.get(planCodeInfo);
			String unCheckPassCause = inspectionPlanInfoVO.getUnCheckPassCause();
			if (null != unCheckPassCause && (status == ApproveStateContants.UNCHECKPASS.getCode() || 
					status == ApproveStateContants.CHECKPASS.getCode())) {
				inspectionPlanInfo.setUnCheckPassCause(unCheckPassCause);
				inspectionPlanForm.setInspectionPlanInfo(inspectionPlanInfo);
			}
			
			request.setAttribute("planCodeInfo", planCodeInfo);
			//查询条件
			query.setPlanCode(planCodeInfo);
			tools.saveQueryVO(query);
			findListAlready = service.findListAlready(query, tools);//同一计划编号  多次选择   查询已选数据
			for (InspectionPlanQueryBean inspectionPlanQueryBean : findListAlready) {
				if (null != inspectionPlanQueryBean && null != inspectionPlanQueryBean.getOutControlUserId()) {
					userVO = uservice.get(inspectionPlanQueryBean.getOutControlUserId());
					if (null != userVO) {
						inspectionPlanQueryBean.setOutControlUserName(userVO.getUserName());
					}
				}
			}
			getStatisticInfo(request,planCodeInfo);
			loadApprovalLog(inspectionPlanInfoVO,request);
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
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanInfoVO bean = inspectionPlanForm.getInspectionPlanInfo();
		String planCode = bean.getPlanCode();//巡检编号
		iservice.delete(bean);//根据主键Id删除计划表的记录
		service.deleteByPlanCode(planCode);//根据计划编号  删除该编号对应的经销商记录
		return findList(mapping, form, request, response);
	}
	
	/**
	 * 删除已选经销商数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deletePlanCode(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanVO bean = inspectionPlanForm.getInspectionPlan();
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
		InspectionPlanVO vo = null;
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
	 * 填充对象的各级银行名称
	 * @param videoPlanQueryBeanList
	 * @author wdc
	 */
	public void fillValues(List<InspectionPlanQueryBean> inspectionPlanQueryBeanList){
		if (null != inspectionPlanQueryBeanList && inspectionPlanQueryBeanList.size() > 0) {
			for (InspectionPlanQueryBean inspectionPlanQueryBean : inspectionPlanQueryBeanList) {
				if (StringUtil.isNotEmpty(inspectionPlanQueryBean.getBankFullName())) {
					String[] bankNames = inspectionPlanQueryBean.getBankFullName().split("/");
					if (null != bankNames && bankNames.length > 0 && bankNames.length == 2) {
						inspectionPlanQueryBean.setOneBankName(bankNames[0]);
						inspectionPlanQueryBean.setTwoBankName(bankNames[1]);
						inspectionPlanQueryBean.setThreeBankName("");
					}else if (null != bankNames && bankNames.length > 0 && bankNames.length == 3) {
						inspectionPlanQueryBean.setOneBankName(bankNames[0]);
						inspectionPlanQueryBean.setTwoBankName(bankNames[1]);
						inspectionPlanQueryBean.setThreeBankName(bankNames[2]);
					}
				}
			}
		}
	}
	
	/**
	 * 获取经销商列表的统计信息
	 * @param request
	 * @param planCodeInfo
	 * @throws Exception
	 */
	public void getStatisticInfo(HttpServletRequest request,String planCodeInfo) throws Exception{
		//获取统计信息
		InspectionPlanInfoQueryBean dealerInfo = iservice.getDealerInfoByPlanCode(planCodeInfo);
		InspectionPlanInfoQueryBean amountInfo = iservice.getDealerInfo(planCodeInfo);
		InspectionPlanInfoVO inspectionPlanInfo = iservice.get(planCodeInfo);
		if (null != dealerInfo && null != amountInfo && null != inspectionPlanInfo) {
			request.setAttribute("provinceAmount", dealerInfo.getProvinceAmount());//省数
			request.setAttribute("cityAmount", dealerInfo.getCityAmount());//市数
			//request.setAttribute("stores", amount.getStores());//店数
			request.setAttribute("mkTableUserId", inspectionPlanInfo.getMkTableUserId());//制表人
			request.setAttribute("predictFinishDate", inspectionPlanInfo.getPredictFinishDate());//预计完成日期
			request.setAttribute("predictCostAmountValue", amountInfo.getPredictCostAmount());//预计产生费用合计
			request.setAttribute("storesAmount", amountInfo.getStoresAmount());//店数
			request.setAttribute("outControlUserAmount", amountInfo.getOutControlUserAmount());//外控专员合计
		}
		
	}
	
	/**
	 * 获取经销商列表的统计信息(台账)
	 * @param request
	 * @param planCodeInfo
	 * @throws Exception
	 */
	public void getLedgerStatisticInfo(HttpServletRequest request,InspectionPlanQueryVO query) throws Exception{
		//获取统计信息
		InspectionPlanInfoQueryBean dealerInfo = iservice.getLedgerDealerInfo(query);
		InspectionPlanInfoQueryBean amountInfo = iservice.getLedgerInfo(query);
		if (null != dealerInfo && null != amountInfo) {
			request.setAttribute("provinceAmount", dealerInfo.getProvinceAmount());//省数
			request.setAttribute("cityAmount", dealerInfo.getCityAmount());//市数
			//request.setAttribute("stores", amount.getStores());//店数
			request.setAttribute("predictCostAmountValue", amountInfo.getPredictCostAmount());//预计产生费用合计
			request.setAttribute("storesAmount", amountInfo.getStoresAmount());//店数
			request.setAttribute("outControlUserAmount", amountInfo.getOutControlUserAmount());//外控专员合计
		}
		
	}
	
	/*
	 * 需求38 -- 导出巡检计划台账（未审核）
	 * @time 20170519
	*/
	public ActionForward ExtFindListLedgerNotAudited(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		query.setCurrRole(currRole);
		query.setFlag(1);
		query.setUserVO(user);
		
		List<InspectionPlanInfoQueryBean> list = service.ExtFindListLedgerNotAudited(query);
		getPerformance(query,list);
		String[] titles = {"序号","巡检编号","内控专员","省数","市数","涉及店数","银行数","品牌数","库存合计",
				"预计检查天数",	"计划开始时间",	"计划提交时间",	"状态",	"完成情况"};
		String filename = "巡检计划台账(未审核)";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	InspectionPlanInfoQueryBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getPlanCode());
	    	
	    	tool.setCellValue(i+1, 2, vo.getInControlUserNTT());
	    	
	    	tool.setCellValue(i+1, 3, vo.getProvinceAmount());
	    	
	    	tool.setCellValue(i+1, 4, vo.getCityAmount());
	    	
	    	tool.setCellValue(i+1, 5, vo.getStores());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBankAmount());
	    	
	    	tool.setCellValue(i+1, 7, vo.getBrandAmount());
	    	
	    	tool.setCellValue(i+1, 8, vo.getStockAmount());
	    	
	    	tool.setCellValue(i+1, 9, vo.getPredictCheckdays());
	    	
	    	tool.setCellValue(i+1, 10, vo.getPlanBeginTime());
	    	
			tool.setCellValue(i+1, 11, vo.getPlanSubmitTime());		
			
			
			if (vo.getStatus() == 1) {
				tool.setCellValue(i+1,12, "未提交");
			}else if (vo.getStatus() == 2) {
				tool.setCellValue(i+1,12, "待审核");
			}else if (vo.getStatus() == 3) {
				tool.setCellValue(i+1,12, "审核通过");
			}else if (vo.getStatus() == 4) {
				tool.setCellValue(i+1,12, "审核未通过");
			}else if (vo.getStatus() == 5) {
				tool.setCellValue(i+1,12, "审核正在审核");
			}
			tool.setCellValue(i+1,13, vo.getPerformance());
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
		return null;
	}
	
	/*
	 * 需求38 -- 导出巡检计划台账（已审核）
	 * @time 20170519
	*/
	public ActionForward ExtFindListLedgerAudited(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		UserVO user = gerUserVO(request);
		int currRole = getCurrRole(request);
		
		InspectionPlanForm inspectionPlanForm = (InspectionPlanForm) form;
		InspectionPlanQueryVO query = inspectionPlanForm.getQuery();
		query.setCurrRole(currRole);
		query.setUserVO(user);
		List<InspectionPlanInfoQueryBean> list = service.ExtFindListLedgerNotAudited(query);
		getPerformance(query,list);
		String[] titles = {"序号","巡检编号","内控专员","省数","市数","涉及店数","银行数","品牌数","库存合计",
				"预计检查天数",	"计划开始时间",	"计划提交时间",	"状态",	"完成情况"};
		String filename = "巡检计划台账(已审核)";
		ExcelTool tool = new ExcelTool(list.size()+1,titles.length);
		//合并第一行
		tool.setTitle(titles, 0);
		tool.setRowHeight(0, 25);
	    for (int i = 0; list != null && i < list.size(); i++) {
	    	InspectionPlanInfoQueryBean vo =  list.get(i); 
	    	tool.setCellValue(i+1, 0, i+1);
	    	
	    	tool.setCellValue(i+1, 1, vo.getPlanCode());
	    	
	    	tool.setCellValue(i+1, 2, vo.getInControlUserNTT());
	    	
	    	tool.setCellValue(i+1, 3, vo.getProvinceAmount());
	    	
	    	tool.setCellValue(i+1, 4, vo.getCityAmount());
	    	
	    	tool.setCellValue(i+1, 5, vo.getStores());
	    	
	    	tool.setCellValue(i+1, 6, vo.getBankAmount());
	    	
	    	tool.setCellValue(i+1, 7, vo.getBrandAmount());
	    	
	    	tool.setCellValue(i+1, 8, vo.getStockAmount());
	    	
	    	tool.setCellValue(i+1, 9, vo.getPredictCheckdays());
	    	
	    	tool.setCellValue(i+1, 10, vo.getPlanBeginTime());
	    	
			tool.setCellValue(i+1, 11, vo.getPlanSubmitTime());	
			
			if (vo.getStatus() == 1) {
				tool.setCellValue(i+1,12, "未提交");
			}else if (vo.getStatus() == 2) {
				tool.setCellValue(i+1,12, "待审核");
			}else if (vo.getStatus() == 3) {
				tool.setCellValue(i+1,12, "审核通过");
			}else if (vo.getStatus() == 4) {
				tool.setCellValue(i+1,12, "审核未通过");
			}else if (vo.getStatus() == 5) {
				tool.setCellValue(i+1,12, "正在审核");
			}
			tool.setCellValue(i+1,13, vo.getPerformance());
	    	tool.allAutoColumnWidth(i);
	    }
	    tool.writeStream(response, filename);
		return null;
	}
	
}
