package com.zd.csms.supervisory.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.zd.core.ActionSupport;
import com.zd.csms.business.model.TwoAddressVO;
import com.zd.csms.business.service.TwoAddressService;
import com.zd.csms.constants.Constants;
import com.zd.csms.file.model.UploadfileVO;
import com.zd.csms.file.service.UploadfileService;
import com.zd.csms.file.util.UploadFileUtil;
import com.zd.csms.marketing.contants.ApprovalTypeContant;
import com.zd.csms.marketing.model.MarketApprovalQueryVO;
import com.zd.csms.marketing.service.MarketApprovalSerivce;
import com.zd.csms.rbac.constants.RoleConstants;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.csms.rbac.util.RoleUtil;
import com.zd.csms.rbac.util.UserSession;
import com.zd.csms.supervisory.model.DuedateQueryVO;
import com.zd.csms.supervisory.model.DuedateVO;
import com.zd.csms.supervisory.model.SuperviseImportQueryVO;
import com.zd.csms.supervisory.model.SuperviseImportVO;
import com.zd.csms.supervisory.service.DuedateService;
import com.zd.csms.supervisory.service.SuperviseImportService;
import com.zd.csms.supervisory.util.CreatePDFUtil;
import com.zd.csms.supervisory.web.form.DuedateForm;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class TwodateAction extends ActionSupport {
	
	private DuedateService service = new DuedateService();
	private MarketApprovalSerivce approvalService = new MarketApprovalSerivce();
	
	/**
	 * 流程角色:监管员-业务专员
	 */
	private static int[] approvalRole = new int[] { 
			RoleConstants.SUPERVISORY.getCode(),
			RoleConstants.BUSINESS_COMMISSIONER.getCode(),
			RoleConstants.SR.getCode()};
	
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
		
		DuedateForm dform = (DuedateForm)form;
		DuedateQueryVO query = dform.getDuedatequery();
		query.setCurrRole(currRole);
		if (query.getPageType() == null) {
			query.setPageType(1);
		}
		int pageType = query.getPageType();
		query.setPageType(pageType);
		query.setType(2);

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("findList", request);
		tools.saveQueryVO(query);
		
		List<DuedateVO> list = service.searchDuedateListByPage(query, tools);

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
	/*public ActionForward preApproval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DuedateForm dform = (DuedateForm) form;
		DuedateVO bean = service.loadDuedateById(dform.getDuedate().getId());
		dform.setDuedate(bean);
		
		UploadfileService ufservice = new UploadfileService();
		UploadfileVO uf1 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf1 != null){
			request.setAttribute("pic1", uf1.getId());
			request.setAttribute("file1", uf1.getFile_path());
		}
		UploadfileVO uf2 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf2 != null){
			request.setAttribute("pic2", uf2.getId());
			request.setAttribute("file2", uf2.getFile_path());
		}
		UploadfileVO uf3 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf3 != null){
			request.setAttribute("pic3", uf3.getId());
			request.setAttribute("file3", uf3.getFile_path());
		}
		UploadfileVO uf4 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf4 != null){
			request.setAttribute("pic4", uf4.getId());
			request.setAttribute("file4", uf4.getFile_path());
		}
		UploadfileVO uf5 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf5 != null){
			request.setAttribute("pic5", uf5.getId());
			request.setAttribute("file5", uf5.getFile_path());
		}

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.TWODUEDATE.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("approval");
	}*/

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
	/*public ActionForward approval(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int currRole = getCurrRole(request,response);
		DuedateForm dform = (DuedateForm) form;
		DuedateQueryVO query = dform.getDuedatequery();
		query.setCurrRole(currRole);
		UserSession user = UserSessionUtil.getUserSession(request);
		service.twoapproval(query, user);

		return findList(mapping, dform, request, response);
	}*/
	
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
	/*public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DuedateForm dform = (DuedateForm) form;
		int id = dform.getDuedate().getId();
		service.twosubmit(id);
		return findList(mapping, form, request, response);
	}*/

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
	/*public ActionForward detailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DuedateForm dform = (DuedateForm) form;
		DuedateVO bean = service.loadDuedateById(dform.getDuedate().getId());

		dform.setDuedate(bean);
		
		UploadfileService ufservice = new UploadfileService();
		UploadfileVO uf1 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf1 != null){
			request.setAttribute("pic1", uf1.getId());
			request.setAttribute("file1", uf1.getFile_path());
		}
		UploadfileVO uf2 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf2 != null){
			request.setAttribute("pic2", uf2.getId());
			request.setAttribute("file2", uf2.getFile_path());
		}
		UploadfileVO uf3 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf3 != null){
			request.setAttribute("pic3", uf3.getId());
			request.setAttribute("file3", uf3.getFile_path());
		}
		UploadfileVO uf4 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf4 != null){
			request.setAttribute("pic4", uf4.getId());
			request.setAttribute("file4", uf4.getFile_path());
		}
		UploadfileVO uf5 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf5 != null){
			request.setAttribute("pic5", uf5.getId());
			request.setAttribute("file5", uf5.getFile_path());
		}

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.TWODUEDATE.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("detailPage");
	}*/
	
	
	public ActionForward addTwodateEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		return mapping.findForward("add_twodate");
	}
	
	public ActionForward addTwodate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DuedateForm dform = (DuedateForm)form;
		DuedateService service = new DuedateService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		boolean flag = false;
		
		int pic1 = 0;
		FormFile file1 = dform.getBen();
		if(file1 != null){
			pic1 = UploadFileUtil.savefile(file1, UserSessionUtil.getUserSession(request), request);
		}
		DuedateVO dvo = new DuedateVO();
		dvo = dform.getDuedate();
		dvo.setSuperviseid(UserSessionUtil.getUserSession(request).getUser().getId());
		dvo.setPicid(pic1);
		dvo.setCreateuserid(user.getId());
		dvo.setCreatedate(new Date());
		flag = service.addDuedate(dvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return findList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_twodate");
	}
	
	public ActionForward delTwodate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		DuedateForm dform = (DuedateForm)form;
		DuedateService service = new DuedateService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteDuedate(dform.getDuedate().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return findList(mapping, form, request, response);
	}
	
	public ActionForward twoList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DuedateForm dform = (DuedateForm)form;
		SuperviseImportService service = new SuperviseImportService();
		SuperviseImportQueryVO siQuery = dform.getSuperviseImportquery();
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		siQuery.setType(1);
		siQuery.setUserid(user.getId());
		//移动
		siQuery.setState(4);
		
		//按条件查询分页数据
		List<SuperviseImportVO> list = service.searchSuperviseImportList(siQuery);
		TwoAddressService taservice = new TwoAddressService();
		
		if(list != null && list.size()>0){
			for(SuperviseImportVO sivo : list){
				if(!StringUtil.isEmpty(sivo.getTwo_name())){
					try {
						TwoAddressVO tavo = taservice.loadTwoAddressById(Integer.parseInt(sivo.getTwo_name()));
						sivo.setTwo_name(tavo.getTwo_address());
					}catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		//返回列表页面
		return mapping.findForward("two_list");
	}
	
	public ActionForward twoLedger(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		DuedateForm dform = (DuedateForm)form;
		DuedateQueryVO query = dform.getDuedatequery();
		query.setApprovalState(1);
		query.setType(2);

		IThumbPageTools tools = ToolsFactory.getThumbPageTools("twoLedger", request);
		tools.saveQueryVO(query);
		
		List<DuedateVO> list = service.searchDuedateListByPage(query, tools);

		request.setAttribute("list", list);
		
		return mapping.findForward("two_ledger");
	}
	
	/*public ActionForward twodetail(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DuedateForm dform = (DuedateForm) form;
		DuedateVO bean = service.loadDuedateById(dform.getDuedate().getId());

		dform.setDuedate(bean);
		
		UploadfileService ufservice = new UploadfileService();
		UploadfileVO uf1 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf1 != null){
			request.setAttribute("pic1", uf1.getId());
			request.setAttribute("file1", uf1.getFile_path());
		}
		UploadfileVO uf2 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf2 != null){
			request.setAttribute("pic2", uf2.getId());
			request.setAttribute("file2", uf2.getFile_path());
		}
		UploadfileVO uf3 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf3 != null){
			request.setAttribute("pic3", uf3.getId());
			request.setAttribute("file3", uf3.getFile_path());
		}
		UploadfileVO uf4 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf4 != null){
			request.setAttribute("pic4", uf4.getId());
			request.setAttribute("file4", uf4.getFile_path());
		}
		UploadfileVO uf5 = ufservice.loadUploadfileById(bean.getPicid());
		if(uf5 != null){
			request.setAttribute("pic5", uf5.getId());
			request.setAttribute("file5", uf5.getFile_path());
		}

		MarketApprovalQueryVO approvalQuery = new MarketApprovalQueryVO();
		approvalQuery.setObjectId(bean.getId());
		approvalQuery.setObjectType(ApprovalTypeContant.TWODUEDATE.getCode());
		request.setAttribute("approvals", approvalService.findListByApprovalType(approvalQuery));

		return mapping.findForward("two_detail");
	}
	*/
	public ActionForward twoDownLoad(ActionMapping mapping, ActionForm actionform,
			HttpServletRequest request, HttpServletResponse response) {
		
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int id = 0;
		if(user != null){
			id =  user.getId();
			String path = CreatePDFUtil.two(id, request);
			request.setAttribute("path", path);
			request.setAttribute("fileName", "二网日盘点表");
			return mapping.findForward("down_upfile");
		}else{
			return null;
		}
		
	}
	
}
