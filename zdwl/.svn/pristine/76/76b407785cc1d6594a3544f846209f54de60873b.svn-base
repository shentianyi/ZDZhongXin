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
import com.zd.csms.finance.model.OpenInvoiceQueryVO;
import com.zd.csms.finance.model.OpenInvoiceVO;
import com.zd.csms.finance.service.OpenInvoiceService;
import com.zd.csms.finance.web.form.OpenInvoiceForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class OpenInvoiceAction extends ActionSupport {

	public ActionForward openInvoiceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return openInvoiceList(mapping, form,request, response);
	}
	
	
	public ActionForward openInvoiceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		OpenInvoiceForm oiform = (OpenInvoiceForm)form;
		OpenInvoiceService service = new OpenInvoiceService();
		
		OpenInvoiceQueryVO oiQuery = oiform.getOpenInvoicequery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("openInvoiceList",request);
		thumbPageTools.saveQueryVO(oiQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<OpenInvoiceVO> list = service.searchOpenInvoiceListByPage(oiQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		
		setOptions(request);
		
		//返回列表页面
		return mapping.findForward("open_invoice_list");
	}
	
	public ActionForward addOpenInvoiceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//返回新增页面
		setOptions(request);
		return mapping.findForward("add_open_invoice");
	}
	
	public ActionForward addOpenInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceForm oiform = (OpenInvoiceForm)form;
		OpenInvoiceService service = new OpenInvoiceService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		OpenInvoiceVO ovo = oiform.getOpenInvoice();
		ovo.setCreateuserid(user.getId());
		ovo.setCreatedate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addOpenInvoice(ovo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return openInvoiceList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_open_invoice");
	}
	
	public ActionForward updOpenInvoiceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceForm oiform = (OpenInvoiceForm)form;
		OpenInvoiceService service = new OpenInvoiceService();
		
		//根据id获取修改对象
		OpenInvoiceVO vo = service.loadOpenInvoiceById(oiform.getOpenInvoice().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return openInvoiceList(mapping, form, request, response);
		}
		
		oiform.setOpenInvoice(vo);
		setOptions(request);
		
		//返回修改页面
		return mapping.findForward("upd_open_invoice");
	}
	
	public ActionForward updOpenInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceForm oiform = (OpenInvoiceForm)form;
		OpenInvoiceService service = new OpenInvoiceService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		OpenInvoiceVO ovo = oiform.getOpenInvoice();
		ovo.setUpduserid(user.getId());
		ovo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updOpenInvoice(ovo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return openInvoiceList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_open_invoice");
	}
	
	public ActionForward delOpenInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		OpenInvoiceForm oiform = (OpenInvoiceForm)form;
		OpenInvoiceService service = new OpenInvoiceService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteOpenInvoice(oiform.getOpenInvoice().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return openInvoiceList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		
	}
}
