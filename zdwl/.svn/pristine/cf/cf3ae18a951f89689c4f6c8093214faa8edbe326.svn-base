package com.zd.csms.marketing.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.constants.Constants;
import com.zd.csms.marketing.model.AgreementSendQueryVO;
import com.zd.csms.marketing.model.AgreementSendVO;
import com.zd.csms.marketing.service.AgreementSendService;
import com.zd.csms.marketing.web.form.AgreementSendForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;
/**
 * 已废弃
 *
 */
public class AgreementSendAction extends ActionSupport {

	public ActionForward agreementSendListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return agreementSendList(mapping, form,request, response);
	}
	
	
	public ActionForward agreementSendList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		AgreementSendForm asform = (AgreementSendForm)form;
		AgreementSendService service = new AgreementSendService();
		
		AgreementSendQueryVO asQuery = asform.getAgreementSendquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("agreementSendList",request);
		thumbPageTools.saveQueryVO(asQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<AgreementSendVO> list = service.searchAgreementSendListByPage(asQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("agreement_send_list");
	}
	
	public ActionForward addAgreementSendEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_agreement_send");
	}
	
	public ActionForward addAgreementSend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementSendForm asform = (AgreementSendForm)form;
		AgreementSendService service = new AgreementSendService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addAgreementSend(asform.getAgreementSend());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return agreementSendList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_agreement_send");
	}
	
	public ActionForward updAgreementSendEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementSendForm asform = (AgreementSendForm)form;
		AgreementSendService service = new AgreementSendService();
		
		//根据id获取修改对象
		AgreementSendVO vo = service.loadAgreementSendById(asform.getAgreementSend().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return agreementSendList(mapping, form, request, response);
		}
		
		asform.setAgreementSend(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_agreement_send");
	}
	
	public ActionForward updAgreementSend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementSendForm asform = (AgreementSendForm)form;
		AgreementSendService service = new AgreementSendService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updAgreementSend(asform.getAgreementSend());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return agreementSendList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_agreement_send");
	}
	
	public ActionForward delAgreementSend(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AgreementSendForm asform = (AgreementSendForm)form;
		AgreementSendService service = new AgreementSendService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteAgreementSend(asform.getAgreementSend().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return agreementSendList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("brandOptions", OptionUtil.getBrand());
		
		
	}
}
