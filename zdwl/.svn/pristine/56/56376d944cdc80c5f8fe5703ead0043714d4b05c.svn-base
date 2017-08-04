package com.zd.csms.supervisorymanagement.web.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.core.util.ExcelTool;
import com.zd.csms.bank.service.BankService;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerQueryVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.querybean.DealerQueryBean;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.marketing.web.excel.DealerRowMapper;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.contants.DataMailcostReceiverTypeContants;
import com.zd.csms.supervisorymanagement.model.DataMailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostToExtVO;
import com.zd.csms.supervisorymanagement.model.DataMailcostVO;
import com.zd.csms.supervisorymanagement.service.DataMailcostService;
import com.zd.csms.supervisorymanagement.web.form.DataMailcostForm;
import com.zd.tools.file.importFile.IExportFile;
import com.zd.tools.file.importFile.impl.ExportFileExcelImpl;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class DataMailcostAction extends ActionSupport {

	private DataMailcostService service = new DataMailcostService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	SupervisoryService  supervisoryService=new SupervisoryService();
	RepositoryService repositoryService=new RepositoryService();
	RosterService rosterService=new RosterService();
	
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORY.getCode(),
			/*RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),*/
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.BUSINESS_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.FINANCE_ACCOUNTING.getCode(),
			RoleConstants.SR.getCode()
			
	};
	
	
	/**
	 * 获取当前用户的权限
	 * 
	 * @return
	 * @throws IOException 
	 */
	private int getCurrRole(HttpServletRequest request,HttpServletResponse response) {
		return RoleUtil.getCurrRole(approvalRole, request, response);
	}
	
	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int currRole = getCurrRole(request,response);
		
		DataMailcostForm mform = (DataMailcostForm)form;
		DataMailcostQueryVO query = mform.getDataMailcostquery();
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("findList", request);
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setPromoter( UserSessionUtil.getUserSession(request).getUser().getId());
		List<DataMailcostQueryVO> list = service.searchDataMailcostListByPage(query, tools);

		request.setAttribute("list", list);
		if (pageType == 1){
			return mapping.findForward("list_wait_approval");
		}else{
			return mapping.findForward("list_already_approval");
		}
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
		DataMailcostForm mform = (DataMailcostForm)form;
		//根据id获取修改对象
		DataMailcostVO bean = service.loadDataMailcostById(mform.getDataMailcost().getId());
		//对象不存在时返回列表页
		if(bean==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "该数据不存在");
			return findList(mapping, form, request, response);
		}else{
			RepositoryVO repository=repositoryService.load(bean.getMailPerson());
			if(repository!=null){
				SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("mailPerson", supervisorBaseInfoVO.getName());
				}
			}
		}
		mform.setDataMailcost(bean);
		setOptions(request);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
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
		
		int currRole = getCurrRole(request,response);
		DataMailcostForm dform = (DataMailcostForm) form;
		DataMailcostQueryVO query = new DataMailcostQueryVO();
		BeanUtils.copyProperties(query, dform.getDataMailcost());
		query.setApprovalState(dform.getDataMailcostquery().getApprovalState());
		query.setRemark(dform.getDataMailcostquery().getRemark());
		query.setCurrRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		boolean flag=service.approval(query, user);
		if(flag){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "审批操作成功！");
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "审批操作失败！");
		}
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		return findList(mapping, dform, request, response);
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
		DataMailcostForm mform = (DataMailcostForm) form;
		int id = mform.getDataMailcost().getId();
		boolean flag=service.submit(id);
		if(flag){
			//将执行结果及消息设置到request为页面处理使用
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "提交成功，进入审批流程！");
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "提交失败！");
		}
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		return findList(mapping, mform, request, response);
	}

	/**
	 * 详情页
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DataMailcostForm mform = (DataMailcostForm)form;
		//根据id获取修改对象
		DataMailcostVO bean = service.loadDataMailcostById(mform.getDataMailcost().getId());
		//对象不存在时返回列表页
		if(bean==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "该数据不存在");
			return findList(mapping, form, request, response);
		}else{
			RepositoryVO repository=repositoryService.load(bean.getMailPerson());
			if(repository!=null){
				SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("mailPerson", supervisorBaseInfoVO.getName());
				}
			}
		}
		mform.setDataMailcost(bean);
		setOptions(request);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("detailPage");
	}
	
	public ActionForward addDataMailcostEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DataMailcostForm dataMailcost=(DataMailcostForm) form;
		setOptions(request);
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int repositoryId=user.getClient_id();
		dataMailcost.getDataMailcost().setMailPerson(repositoryId);
		
		RepositoryVO repository=repositoryService.load(repositoryId);
		if(repository!=null){
			SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
			if(supervisorBaseInfoVO!=null){
				//当前监管员名称
				request.setAttribute("mailPerson", supervisorBaseInfoVO.getName());
			}
			RosterVO roster=rosterService.getRosterBySupervisorId(repository.getSupervisorId());
			if(roster!=null){
				dataMailcost.getDataMailcost().setMailPersonStaffNo(roster.getStaffNo());
			}
		}
	
		return mapping.findForward("add_datamail_cost");
	}
	
	public ActionForward addDataMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DataMailcostForm mform = (DataMailcostForm)form;
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		DataMailcostVO mcvo = mform.getDataMailcost();
		mcvo.setPromoter(user.getId());
		mcvo.setCreateuserid(user.getId());
		mcvo.setCreatedate(new Date());
		
		//执行新增操作并获取操作结果
		boolean flag = service.addDataMailcost(mcvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		if(flag){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "新增成功！");
			//新增成功时返回列表页面
			return findList(mapping, form, request, response);
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "新增失败！");
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_datamail_cost");
	}
	
	public ActionForward updDataMailcostEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DataMailcostForm mform = (DataMailcostForm)form;
		//根据id获取修改对象
		DataMailcostVO vo = service.loadDataMailcostById(mform.getDataMailcost().getId());
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return findList(mapping, form, request, response);
		}else{
			RepositoryVO repository=repositoryService.load(vo.getMailPerson());
			if(repository!=null){
				SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("mailPerson", supervisorBaseInfoVO.getName());
				}
			}
		}
		mform.setDataMailcost(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_datamail_cost");
	}
	
	public ActionForward updDataMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DataMailcostForm mform = (DataMailcostForm)form;
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		DataMailcostVO mcvo = mform.getDataMailcost();
		mcvo.setUpduserid(user.getId());
		mcvo.setUpddate(new Date());
		
		//执行修改操作并获取操作结果
		boolean flag = service.updDataMailcost(mcvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		if(flag){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改成功！");
			//操作成功时返回列表页面
			return findList(mapping, form, request, response);
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改失败！");
		}
		//操作失败时返回修改 页面
		return mapping.findForward("upd_datamail_cost");
	}
	
	public ActionForward delDataMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DataMailcostForm mform = (DataMailcostForm)form;
		//执行删除操作并获取操作结果
		boolean flag = service.deleteDataMailcost(mform.getDataMailcost().getId());
		if(flag){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "删除成功！");
		}else{
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "删除失败！");
		}
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		
		//返回列表页面
		return findList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int repositoryId=user.getClient_id();
		//邮寄项目
		request.setAttribute("dataMailcostStateOptions", OptionUtil.mailcostState());
		//request.setAttribute("superviseOptions", OptionUtil.superviseOptions());
		//当前角色监管员监管的经销商
		request.setAttribute("mailPersonDealers", OptionUtil.getDealerByRepositoryId(repositoryId));
		
		//当前接收人部门
		request.setAttribute("receiverTypes", OptionUtil.getDataMailCostReceiverTypes());
		
		//所有监管员
		request.setAttribute("supervisorys", OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
		
		//所有业务部专员
		request.setAttribute("businessOfficers", OptionUtil.getUserByRole(RoleConstants.BUSINESS_COMMISSIONER.getCode()));
		
		//所有经销商
		request.setAttribute("allDealers", OptionUtil.getDealers());
		//返回新增页面
		
	}
	
	/*
	 * 需求54 -- 台账管理--增加资料邮寄费用台账
	 * @time 20170514
	*/
	public ActionForward dataMailingFeeList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request,response);
		
		DataMailcostForm mform = (DataMailcostForm)form;
		DataMailcostQueryVO query = mform.getDataMailcostquery();
		query.setCurrRole(currRole);
		query.setPageType(2); //返回审批通过和不通过数据
		int pageType = query.getPageType();
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("dataMailingFeeList", request);
		tools.saveQueryVO(query);
		query.setPageType(pageType);
		query.setPromoter( UserSessionUtil.getUserSession(request).getUser().getId());
		List<DataMailcostQueryVO> list = service.searchDataMailcostListByPage(query, tools);

		request.setAttribute("list", list);
		return mapping.findForward("dataMailingFeeList");
	}
	
	//导出资料邮寄费用台账 -- 需求54
	private UserService userService=new UserService();
	public ActionForward extDataMailingFeeExcel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		int currRole = getCurrRole(request,response);
		DataMailcostForm mform = (DataMailcostForm)form;
		DataMailcostQueryVO query = mform.getDataMailcostquery();
		
		query.setCurrRole(currRole);
		query.setPromoter( UserSessionUtil.getUserSession(request).getUser().getId());
		query.setPageType(2);
		
		try {
			List<DataMailcostToExtVO> list = service.searchDataMailcostListToExt(query);
			
           String[] titles={"序号","邮寄项目","发起时间","邮寄人","邮寄人经销商","快递公司","预计金额","运输城市起","运输城市止",
  				 "接收人部门","接收人","审批状态","下一审批人","创建人","创建时间","修改人","修改时间"};
           String filename ="资料邮寄费用台账";
           ExcelTool tool = new ExcelTool((list.size()+1),titles.length);
	         //合并第一行
		  tool.setTitle(titles, 0);
		  tool.setRowHeight(0, 25);
		  String receiver=""; //设置接收人
		  SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		  for (int i = 0; list != null && i < list.size(); i++) {
			  DataMailcostToExtVO vo = (DataMailcostToExtVO)list.get(i);
			  System.out.println("测试数据："+list.get(i).getMailItems()+"/"+vo.getFqDate()+"/"+vo.getMailPersonNTT()+"/"+
				vo.getMailPersonDealerNTT()+"/"+vo.getExpress());
			  tool.setCellValue(i+1, 0, i+1);
			  if (vo.getMailItems().equals("1")) {
				  tool.setCellValue(i+1, 1, "保险柜");
			  }else if (vo.getMailItems().equals("2")) {
				  tool.setCellValue(i+1, 1, "笔记本电脑");
			  }else if (vo.getMailItems().equals("3")) {
				  tool.setCellValue(i+1, 1, "配件");
			  }else if (vo.getMailItems().equals("4")) {
				  tool.setCellValue(i+1, 1, "资料");
			  }else if (vo.getMailItems().equals("5")) {
				  tool.setCellValue(i+1, 1, "其他");
			  }
			  if (vo.getFqDate() != null) {
				String fqDate = format.format(vo.getFqDate());
				tool.setCellValue(i+1, 2, fqDate);
			  }
			  tool.setCellValue(i+1, 3, vo.getMailPersonNTT());
			  tool.setCellValue(i+1, 4, vo.getMailPersonDealerNTT());
			  tool.setCellValue(i+1, 5, vo.getExpress());
			  tool.setCellValue(i+1, 6, vo.getMoney());
			  tool.setCellValue(i+1, 7, vo.getTransportBegin());
			  tool.setCellValue(i+1, 8, vo.getTransportEnd());
			  if(vo.getReceiverType() == DataMailcostReceiverTypeContants.BUSINESSOFFICER.getCode()){
				  tool.setCellValue(i+1, 9, "业务专员");
				  UserVO user=userService.get(vo.getBusinessOfficer());
					if(user!=null){
						receiver=user.getUserName();
					}
			  }else if(vo.getReceiverType() == DataMailcostReceiverTypeContants.BANK.getCode()){
				  tool.setCellValue(i+1, 9, "金融机构");
				  BankService bankService=new BankService();
					receiver=bankService.getBankNameById(vo.getHeadBank())+"/"
							+bankService.getBankNameById(vo.getBranch())+"/"
							+bankService.getBankNameById(vo.getSubbranch());
			  }else if(vo.getReceiverType() == DataMailcostReceiverTypeContants.DEALER.getCode()){
				  tool.setCellValue(i+1, 9, "经销商");
				  DealerService dealerService=new DealerService();
					DealerVO dealer=dealerService.get(vo.getDealerId());
					if(dealer!=null){
						receiver=dealer.getDealerName();
					}
			  }else if(vo.getReceiverType() == DataMailcostReceiverTypeContants.SUPERVISORY.getCode()){
				  tool.setCellValue(i+1, 9, "监管员");
				  SupervisoryService  supervisoryService=new SupervisoryService();
					RepositoryService repositoryService=new RepositoryService();
					
					RepositoryVO repository=repositoryService.load(vo.getSupervisoryId());
					if(repository!=null){
						SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
						if(supervisorBaseInfoVO!=null){
							//当前监管员名称
							receiver=supervisorBaseInfoVO.getName();
						}
					}
			  }
			  
			  tool.setCellValue(i+1, 10, receiver);
			  if (vo.getApprovalState() == 1) {
				  tool.setCellValue(i+1, 11, "审批不通过");
			  }else  if (vo.getApprovalState() == 2) {
				  tool.setCellValue(i+1, 11, "审批通过");
			  }
			  tool.setCellValue(i+1, 12, vo.getNextApprovalName());
			  tool.setCellValue(i+1, 13, vo.getCreateuserName());
			  if (vo.getCreatedate() != null) {
					String Createdate = format.format(vo.getCreatedate());
					tool.setCellValue(i+1, 14, Createdate);
			  }
			  tool.setCellValue(i+1, 15, vo.getUpduserName());
			  if (vo.getUpddate() != null) {
					String Upddate = format.format(vo.getUpddate());
					tool.setCellValue(i+1, 16, Upddate);
			  }
		      tool.allAutoColumnWidth(i);
		    }
			tool.writeStream(response, filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回列表页面
		return null;
	}
	/*
	 * 资料邮寄费用台账详情
	*/
	public ActionForward dataMailingFeeListDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		DataMailcostForm mform = (DataMailcostForm)form;
		//根据id获取修改对象
		DataMailcostVO bean = service.loadDataMailcostById(mform.getDataMailcost().getId());
		//对象不存在时返回列表页
		if(bean==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "该数据不存在");
			return findList(mapping, form, request, response);
		}else{
			RepositoryVO repository=repositoryService.load(bean.getMailPerson());
			if(repository!=null){
				SupervisorBaseInfoVO supervisorBaseInfoVO=supervisoryService.getBaseInfo(repository.getSupervisorId());
				if(supervisorBaseInfoVO!=null){
					//当前监管员名称
					request.setAttribute("mailPerson", supervisorBaseInfoVO.getName());
				}
			}
		}
		mform.setDataMailcost(bean);
		setOptions(request);
		
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.DATAMAILPOSTAGEREQUISITION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("dataMailingFee_detail");
	}
}
