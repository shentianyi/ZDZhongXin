package com.zd.csms.business.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zd.core.ActionSupport;
import com.zd.csms.base.option.OptionUtil;
import com.zd.csms.business.model.MailingQueryVO;
import com.zd.csms.business.model.MailingVO;
import com.zd.csms.business.service.MailingService;
import com.zd.csms.business.web.form.MailingForm;
import com.zd.csms.constants.Constants;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class MailingAction extends ActionSupport {

	public ActionForward mailingListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mailingList(mapping, form,request, response);
	}
	
	
	public ActionForward mailingList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		MailingForm mform = (MailingForm)form;
		MailingService service = new MailingService();
		
		MailingQueryVO mQuery = mform.getMailingquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("mailingList",request);
		thumbPageTools.saveQueryVO(mQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<MailingVO> list = service.searchMailingListByPage(mQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("mailing_list");
	}
	
	public ActionForward addMailingEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		setOptions(request);
		return mapping.findForward("add_mailing");
	}
	
	public ActionForward addMailing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailingForm mform = (MailingForm)form;
		MailingService service = new MailingService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addMailing(mform.getMailing());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return mailingList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_mailing");
	}
	
	public ActionForward updDraftEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailingForm mform = (MailingForm)form;
		MailingService service = new MailingService();
		
		//根据id获取修改对象
		MailingVO vo = service.loadMailingById(mform.getMailing().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return mailingList(mapping, form, request, response);
		}
		
		mform.setMailing(vo);
		setOptions(request);
		
		//返回修改页面
		return mapping.findForward("upd_mailing");
	}
	
	public ActionForward updMailing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailingForm mform = (MailingForm)form;
		MailingService service = new MailingService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updMailing(mform.getMailing());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return mailingList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_mailing");
	}
	
	public ActionForward delMailing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MailingForm mform = (MailingForm)form;
		MailingService service = new MailingService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteMailing(mform.getMailing().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return mailingList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("superviseOptions", OptionUtil.getAllSupervise());
	}
}
