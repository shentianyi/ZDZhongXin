package com.zd.csms.marketing.web.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.AgreementBackQueryVO;
import com.zd.csms.marketing.model.AgreementBackVO;
import com.zd.csms.marketing.querybean.AgreementQueryBean;
import com.zd.csms.marketing.service.AgreementBackService;
import com.zd.csms.marketing.web.form.AgreementBackForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class AgreementBackAction extends ActionSupport {

	public ActionForward agreementBackListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return agreementBackList(mapping, form,request, response);
	}
	
	
	public ActionForward agreementBackList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		
		AgreementBackQueryVO abQuery = abform.getAgreementBackquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("agreementBackList",request);
		thumbPageTools.saveQueryVO(abQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<AgreementQueryBean> list = service.searchAgreementBackListByPage(abQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("agreement_back_list");
	}
	
	public ActionForward addAgreementBackEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_agreement_back");
	}
	
	public ActionForward addAgreementBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		AgreementBackVO abvo = abform.getAgreementBack();
		abvo.setCreateuserid(user.getId());
		abvo.setCreatedate(new Date());
		abvo.setIsback(1);
		
		//执行新增操作并获取操作结果
		boolean flag = service.addAgreementBack(abvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return agreementBackList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_agreement_back");
	}
	
	public ActionForward updAgreementBackEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		
		//根据id获取修改对象
		AgreementBackVO vo = service.loadAgreementBackById(abform.getAgreementBack().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return agreementBackList(mapping, form, request, response);
		}
		
		abform.setAgreementBack(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_agreement_back");
	}
	
	public ActionForward updAgreementBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		
		AgreementBackVO abvo = abform.getAgreementBack();
		abvo.setUpduserid(user.getId());
		abvo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updAgreementBack(abvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return agreementBackList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_agreement_back");
	}
	
	public ActionForward goBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		int id = abform.getAgreementBack().getId();
		AgreementBackVO abvo = service.loadAgreementBackById(id);
		if(abvo != null){
			abvo.setBack_date(new Date());
			abvo.setIsback(2);
			//abvo.setUpduserid(user.getId());
			//abvo.setUpddate(new Date());
			//service.updAgreementBack(abvo);
			service.updBackDateAndIsback(abvo);
		}
		
		return agreementBackList(mapping, form, request, response);
	}
	
	
	public ActionForward delAgreementBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementBackForm abform = (AgreementBackForm)form;
		AgreementBackService service = new AgreementBackService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteAgreementBack(abform.getAgreementBack().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return agreementBackList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("brandOptions", OptionUtil.getBrands());
		request.setAttribute("supervisorSources", OptionUtil.getSupervisorSources());
		request.setAttribute("supervisorAttributes", OptionUtil.getSupervisorAttrubutes());
	}
}
