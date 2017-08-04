package com.zd.csms.supervisorymanagement.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.base.option.model.OptionObject;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.DealerSupervisorQueryVO;
import com.zd.csms.marketing.model.DealerSupervisorVO;
import com.zd.csms.marketing.model.DealerVO;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.DealerService;
import com.zd.csms.marketing.service.DealerSupervisorService;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.message.contant.MessageTypeContant;
import com.zd.csms.messagequartz.contants.MsgIsContants;
import com.zd.csms.messagequartz.model.SupervisorVO;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.service.UserService;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.repository.constants.RepositoryStatus;
import com.zd.csms.repository.model.RepositoryVO;
import com.zd.csms.repository.service.RepositoryService;
import com.zd.csms.roster.model.RosterQueryVO;
import com.zd.csms.roster.model.RosterVO;
import com.zd.csms.roster.service.RosterService;
import com.zd.csms.supervisory.model.RepairCostVO;
import com.zd.csms.supervisory.model.SupervisorBaseInfoVO;
import com.zd.csms.supervisory.model.costmail.SupervisorCostMailMessageVO;
import com.zd.csms.supervisory.model.supervisorrepair.SupervisorRepairCostMessageVO;
import com.zd.csms.supervisory.service.SupervisoryService;
import com.zd.csms.supervisorymanagement.model.MailCostJsonVO;
import com.zd.csms.supervisorymanagement.model.MailcostQueryVO;
import com.zd.csms.supervisorymanagement.model.MailcostVO;
import com.zd.csms.supervisorymanagement.service.MailcostService;
import com.zd.csms.supervisorymanagement.web.form.MailcostForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class MailcostAction extends ActionSupport {

	private MailcostService service = new MailcostService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	
	
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORYMANAGEMENT_COMPREHENSIVE.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_AMALDAR.getCode(),
			RoleConstants.OPERATIONMANAGEMENT_MINISTER.getCode(),
			RoleConstants.LOGISTICSFINANCECENTER_MAJORDOMO.getCode(),
			RoleConstants.SUPERVISORYMANAGEMENT_PAYMENT.getCode(),
			RoleConstants.SUPERVISORY.getCode(),
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
	
	private UserVO gerUserVO(HttpServletRequest request){
		UserSession user = UserSessionUtil.getUserSession(request);
		return user.getUser();
	}
	
	
	/**
	 * 分页查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return //
	 * @throws Exception
	 */
	public ActionForward findList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		int currRole = getCurrRole(request,response);
		
		MailcostForm mform = (MailcostForm)form;
		MailcostQueryVO query = mform.getMailcostquery();
		query.setCurrRole(currRole);
		if (query.getPageType() == null && currRole != RoleConstants.SUPERVISORY.getCode()) {
			query.setPageType(1);
		}else if (currRole == RoleConstants.SUPERVISORY.getCode()){
			int userid = UserSessionUtil.getUserSession(request).getUser().getClient_id();
			query.setPageType(-1);
			query.setReceiveid(userid);
		}
		int pageType = query.getPageType();
		query.setPageType(pageType);
		IThumbPageTools tools = ToolsFactory.getThumbPageTools("findList", request);
		tools.saveQueryVO(query);
		/*query.setPromoter(UserSessionUtil.getUserSession(request).getUser().getId());*/
		List<MailcostVO> list = service.searchMailcostListByPage(query, tools);

		request.setAttribute("list", list);
		setOptions(request);
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
		MailcostForm mform = (MailcostForm) form;
		MailcostVO bean = service.loadMailcostById(mform.getMailcost().getId());
		mform.setMailcost(bean);
		String mailItems=bean.getMailingitems();
		if(mailItems!=null && mailItems!=""){
			String[] mailingitemsStr=mailItems.split(",");
			Integer[] mailingitems=new Integer[mailingitemsStr.length];
			for(int i=0;i<mailingitemsStr.length;i++){
				mailingitems[i]=Integer.parseInt(mailingitemsStr[i]);
			}
			mform.setMailingitemss(mailingitems);
		}
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.POSTAGEREQUISITION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		setOptions(request);
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
		MailcostForm dform = (MailcostForm) form;
		MailcostQueryVO query = dform.getMailcostquery();
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
		setOptions(request);
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
		MailcostForm mform = (MailcostForm) form;
		int id = mform.getMailcost().getId();
		
		//封装设备提醒对象信息
		MailcostVO bean = service.loadMailcostById(id);
		UserService us = new UserService();
		
		//储备库Id
		RepositoryService sepositoryService = new RepositoryService();
		RepositoryVO repositoryVO = sepositoryService.load(bean.getMailperson());
		//监管员的Id
		SupervisoryService sservice = new SupervisoryService();
		int supervisorId = repositoryVO.getSupervisorId();
		SupervisorBaseInfoVO  supervisorVO = sservice.getBaseInfo(supervisorId);
		//工号
		String gh = getON(repositoryVO.getId());
		//经销商，金融机构
		MailCostJsonVO mJsonVO = getBankAndMerchant(repositoryVO.getId());
		//邮寄项目
		String mailItems=bean.getMailingitems();
		String costmail = getCostMail(mailItems);
		String cost =costmail.substring(1);
		
		//设备申请提醒信息
		SupervisorCostMailMessageVO srCostVO = new SupervisorCostMailMessageVO();
		srCostVO.setIsread(MsgIsContants.NOREAD.getCode());
		srCostVO.setJobnumber(gh);
		srCostVO.setMerchantname(mJsonVO.getJxs());
		srCostVO.setBankname(mJsonVO.getJrjg());
		srCostVO.setName(supervisorVO.getName());
		srCostVO.setSupervisorid(supervisorId);
		srCostVO.setCost(bean.getMoney());
		srCostVO.setCostmail(cost);
		srCostVO.setMessagetype(MessageTypeContant.SUPSERVISCOSTMAIL.getName());
		srCostVO.setCreateDate(new Date());
		
		//boolean flag=service.submit(id);
		
		boolean flag=service.submitAndCostMail(id,srCostVO);
		
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
	 * 获取邮寄项目
	 */
	public String getCostMail(String mailItems){
		Integer[] mailingitems = null;
		String costMail="";
		if(mailItems!=null && mailItems!=""){
			String[] mailingitemsStr=mailItems.split(",");
			mailingitems =new Integer[mailingitemsStr.length];
			for(int i=0;i<mailingitemsStr.length;i++){
				mailingitems[i]=Integer.parseInt(mailingitemsStr[i]);
				switch (mailingitems[i]) {
				case 1:
					costMail+="/保险柜";
					break;
				case 2:
					costMail+="/笔记本电脑";
					break;
				case 3:
					costMail+="/配件";
					break;
				case 4:
					costMail+="/资料";
					break;
				case 5:
					costMail+="/其他";
					break;
				default:
					break;
				}
			}
		
		}
		return costMail;
	}
	
	/**
	 * 工号
	 * @param supervisorId
	 * @return
	 */
	public String getON(int supervisorId){
		RosterService rservice = new RosterService();
		RosterQueryVO rqvo = new RosterQueryVO();
		rqvo.setRepositoryId(supervisorId);
		List<RosterVO> rList = rservice.searchRosterList(rqvo);
		
		//员工工号
		String gh = "";
		if(rList != null && rList.size()>0){
			RosterVO rvo = rList.get(0);
			gh =rvo.getStaffNo();
		}
		return gh;
		
	}
	/**
	 * 金融机构
	 * @param repositoryId
	 * @return
	 * @throws Exception
	 */
	public MailCostJsonVO getBankAndMerchant(int repositoryId) throws Exception{
		String jxs = "";
		String jrjg = "";
		DealerSupervisorService dsservice = new DealerSupervisorService();
		DealerService dservice = new DealerService();
		DealerSupervisorQueryVO dsquery = new DealerSupervisorQueryVO();
		dsquery.setSupervisorId(repositoryId);
		List<DealerSupervisorVO> dsList = dsservice.searchDealerSupervisorList(dsquery);
		if(dsList != null && dsList.size()>0){
			for(DealerSupervisorVO ds:dsList){
				DealerVO dvo = dservice.loadDealerById( ds.getDealerId());
				if(dvo != null){
					jxs =jxs+" "+ dvo.getDealerName();
				}
				jrjg =jrjg+" "+ dservice.getBankNameByDealerId( ds.getDealerId());
			}
		}
		
		MailCostJsonVO mcvo = new MailCostJsonVO();
		mcvo.setJxs(jxs);
		mcvo.setJrjg(jrjg);
		
		return mcvo;
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
		MailcostForm mform = (MailcostForm) form;
		MailcostVO bean = service.loadMailcostById(mform.getMailcost().getId());

		mform.setMailcost(bean);
		String mailItems=bean.getMailingitems();
		if(mailItems!=null && mailItems!=""){
			String[] mailingitemsStr=mailItems.split(",");
			Integer[] mailingitems=new Integer[mailingitemsStr.length];
			for(int i=0;i<mailingitemsStr.length;i++){
				mailingitems[i]=Integer.parseInt(mailingitemsStr[i]);
			}
			mform.setMailingitemss(mailingitems);
		}
		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.POSTAGEREQUISITION.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		setOptions(request);
		return mapping.findForward("detailPage");
	}
	
	public ActionForward addMailcostEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_mail_cost");
	}
	
	public ActionForward addMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailcostForm mform = (MailcostForm)form;
		
		Integer[] mailingitemss = mform.getMailingitemss();
		StringBuffer sb = new StringBuffer();
		if(mailingitemss != null){
			for(int i = 0; i< mailingitemss.length;i++){
				sb.append(mailingitemss[i]+",");
			}
			if(sb!=null&&sb.length()>0)
				sb.deleteCharAt(sb.length()-1);
			mform.getMailcost().setMailingitems(sb.toString());
		}
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		MailcostVO mcvo = mform.getMailcost();
		mcvo.setPromoter(user.getId());
		mcvo.setCreateuserid(user.getId());
		mcvo.setCreatedate(new Date());
		
		//执行新增操作并获取操作结果
		boolean flag = service.addMailcost(mcvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return findList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_mail_cost");
	}
	
	public ActionForward updMailcostEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailcostForm mform = (MailcostForm)form;
		
		//根据id获取修改对象
		MailcostVO vo = service.loadMailcostById(mform.getMailcost().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return findList(mapping, form, request, response);
		}
		String mailItems=vo.getMailingitems();
		if(mailItems!=null && mailItems!=""){
			String[] mailingitemsStr=mailItems.split(",");
			Integer[] mailingitems=new Integer[mailingitemsStr.length];
			for(int i=0;i<mailingitemsStr.length;i++){
				mailingitems[i]=Integer.parseInt(mailingitemsStr[i]);
			}
			mform.setMailingitemss(mailingitems);
		}
		mform.setMailcost(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_mail_cost");
	}
	
	public ActionForward updMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailcostForm mform = (MailcostForm)form;
		
		Integer[] mailingitemss = mform.getMailingitemss();
		StringBuffer sb = new StringBuffer();
		if(mailingitemss != null){
			for(int i = 0; i< mailingitemss.length;i++){
				sb.append(mailingitemss[i]+",");
			}
			mform.getMailcost().setMailingitems(sb.toString());
		}
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		MailcostVO mcvo = mform.getMailcost();
		mcvo.setUpduserid(user.getId());
		mcvo.setUpddate(new Date());
		
		//执行修改操作并获取操作结果
		boolean flag = service.updMailcost(mcvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return findList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_mail_cost");
	}
	
	public ActionForward delMailcost(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailcostForm mform = (MailcostForm)form;

		//执行删除操作并获取操作结果
		boolean flag = service.deleteMailcost(mform.getMailcost().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return findList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("mailcostStateOptions", OptionUtil.mailcostState());
		request.setAttribute("superviseOptions", OptionUtil.getRepository(RepositoryStatus.VALID.getCode(),RepositoryStatus.ALREADYPOST.getCode()));
	}
}
