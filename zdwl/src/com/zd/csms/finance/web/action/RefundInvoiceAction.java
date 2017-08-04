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
import com.zd.csms.finance.model.RefundInvoiceQueryVO;
import com.zd.csms.finance.model.RefundInvoiceVO;
import com.zd.csms.finance.service.RefundInvoiceService;
import com.zd.csms.finance.web.form.RefundInvoiceForm;
import com.zd.csms.rbac.login.common.UserSessionUtil;
import com.zd.csms.rbac.model.UserVO;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class RefundInvoiceAction extends ActionSupport {

	public ActionForward refundInvoiceListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return refundInvoiceList(mapping, form,request, response);
	}
	
	
	public ActionForward refundInvoiceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		RefundInvoiceForm riform = (RefundInvoiceForm)form;
		RefundInvoiceService service = new RefundInvoiceService();
		
		RefundInvoiceQueryVO riQuery = riform.getRefundInvoicequery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("refundInvoiceList",request);
		thumbPageTools.saveQueryVO(riQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<RefundInvoiceVO> list = service.searchRefundInvoiceListByPage(riQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("refund_invoice_list");
	}
	
	public ActionForward addRefundInvoiceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_refund_invoice");
	}
	
	public ActionForward addRefundInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RefundInvoiceForm riform = (RefundInvoiceForm)form;
		RefundInvoiceService service = new RefundInvoiceService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		RefundInvoiceVO rvo = riform.getRefundInvoice();
		rvo.setCreateuserid(user.getId());
		rvo.setCreatedate(new Date());
		//执行新增操作并获取操作结果
		boolean flag = service.addRefundInvoice(rvo);
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return refundInvoiceList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_refund_invoice");
	}
	
	public ActionForward updRefundInvoiceEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RefundInvoiceForm riform = (RefundInvoiceForm)form;
		RefundInvoiceService service = new RefundInvoiceService();
		
		//根据id获取修改对象
		RefundInvoiceVO vo = service.loadRefundInvoiceById(riform.getRefundInvoice().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return refundInvoiceList(mapping, form, request, response);
		}
		
		riform.setRefundInvoice(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_refund_invoice");
	}
	
	public ActionForward updRefundInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RefundInvoiceForm riform = (RefundInvoiceForm)form;
		RefundInvoiceService service = new RefundInvoiceService();
		UserVO user = UserSessionUtil.getUserSession(request).getUser();
		RefundInvoiceVO rvo = riform.getRefundInvoice();
		rvo.setUpduserid(user.getId());
		rvo.setUpddate(new Date());
		//执行修改操作并获取操作结果
		boolean flag = service.updRefundInvoice(rvo);
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return refundInvoiceList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_refund_invoice");
	}
	
	public ActionForward delRefundInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RefundInvoiceForm riform = (RefundInvoiceForm)form;
		RefundInvoiceService service = new RefundInvoiceService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteRefundInvoice(riform.getRefundInvoice().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return refundInvoiceList(mapping, form, request, response);
	}
	public void setOptions(HttpServletRequest request){
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
	}
}
