package com.zd.csms.finance.web.action;

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
import com.zd.csms.finance.model.OpenInvoiceListQueryVO;
import com.zd.csms.finance.model.OpenInvoiceListVO;
import com.zd.csms.finance.service.OpenInvoiceListService;
import com.zd.csms.finance.web.form.OpenInvoiceListForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.StringUtil;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class OpenInvoiceListAction extends ActionSupport {

	public ActionForward openInvoiceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return openInvoiceLists(mapping, form,request, response);
	}
	
	
	public ActionForward openInvoiceLists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		OpenInvoiceListService service = new OpenInvoiceListService();
		
		OpenInvoiceListQueryVO oilQuery = oilform.getOpenInvoiceListquery();
		
		String openid = request.getParameter("openid");
		
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		
		request.setAttribute("openid", openid);
		
		oilform.setOpenid(openid);
		
		oilQuery.setOpen_invoice_id(Integer.parseInt(openid));
		
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("openInvoiceLists",request);
		thumbPageTools.saveQueryVO(oilQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<OpenInvoiceListVO> list = service.searchOpenInvoiceListByPage(oilQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		setOptions(request);
		
		//返回列表页面
		return mapping.findForward("open_invoice_lists");
	}
	
	public ActionForward addOpenInvoiceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		String openid = request.getParameter("openid");
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		request.setAttribute("openid", openid);
		oilform.setOpenid(openid);
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_open_invoice_list");
	}
	
	public ActionForward addOpenInvoiceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		OpenInvoiceListService service = new OpenInvoiceListService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String openid = request.getParameter("openid");
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		request.setAttribute("openid", openid);
		oilform.setOpenid(openid);
		if(!StringUtil.isEmpty(openid)){
			oilform.getOpenInvoiceList().setOpen_invoice_id(Integer.parseInt(openid));
		}
		OpenInvoiceListVO ovo = oilform.getOpenInvoiceList();
		ovo.setCreateuserid(user.getId());
		ovo.setCreatedate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addOpenInvoiceList(ovo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return openInvoiceLists(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_open_invoice_list");
	}
	
	public ActionForward updOpenInvoiceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		OpenInvoiceListService service = new OpenInvoiceListService();
		
		//根据id获取修改对象
		OpenInvoiceListVO vo = service.loadOpenInvoiceListById(oilform.getOpenInvoiceList().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return openInvoiceLists(mapping, form, request, response);
		}
		
		String openid = request.getParameter("openid");
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		request.setAttribute("openid", openid);
		oilform.setOpenid(openid);
		
		oilform.setOpenInvoiceList(vo);
		setOptions(request);
		
		//返回修改页面
		return mapping.findForward("upd_open_invoice_list");
	}
	
	public ActionForward updOpenInvoiceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		OpenInvoiceListService service = new OpenInvoiceListService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		String openid = request.getParameter("openid");
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		request.setAttribute("openid", openid);
		oilform.setOpenid(openid);
		OpenInvoiceListVO ovo = oilform.getOpenInvoiceList();
		ovo.setUpduserid(user.getId());
		ovo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updOpenInvoiceList(ovo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return openInvoiceLists(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_open_invoice_list");
	}
	
	public ActionForward delOpenInvoiceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceListForm oilform = (OpenInvoiceListForm)form;
		OpenInvoiceListService service = new OpenInvoiceListService();

		String openid = request.getParameter("openid");
		if(!StringUtil.isEmpty(openid)){
			openid = oilform.getOpenid();
		}
		request.setAttribute("openid", openid);
		oilform.setOpenid(openid);
		//执行删除操作并获取操作结果
		boolean flag = service.deleteOpenInvoiceList(oilform.getOpenInvoiceList().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return openInvoiceLists(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
		
	}
}
