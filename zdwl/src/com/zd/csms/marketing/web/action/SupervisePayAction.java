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
import com.zd.csms.marketing.model.SupervisePayQueryVO;
import com.zd.csms.marketing.model.SupervisePayVO;
import com.zd.csms.marketing.service.SupervisePayService;
import com.zd.csms.marketing.web.form.SupervisePayForm;
import com.zd.tools.thumbPage.IThumbPageTools;
import com.zd.tools.thumbPage.ToolsFactory;

public class SupervisePayAction extends ActionSupport {

	public ActionForward supervisePayListEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return supervisePayList(mapping, form,request, response);
	}
	
	
	public ActionForward supervisePayList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		SupervisePayForm spform = (SupervisePayForm)form;
		SupervisePayService service = new SupervisePayService();
		
		SupervisePayQueryVO spQuery = spform.getSupervisePayquery();
		//初始化分页查询工具
		IThumbPageTools thumbPageTools = ToolsFactory.getThumbPageTools("supervisePayList",request);
		thumbPageTools.saveQueryVO(spQuery);//记录查询条件,用于查询条件变更时返回第一页
		
		//按条件查询分页数据
		List<SupervisePayVO> list = service.searchSupervisePayListByPage(spQuery, thumbPageTools);
		//将查询结果设置request中，用于页面显示
		request.setAttribute("list", list);
		setOptions(request);
		//返回列表页面
		return mapping.findForward("supervise_pay_list");
	}
	
	public ActionForward addSupervisePayEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		setOptions(request);
		//返回新增页面
		return mapping.findForward("add_supervise_pay");
	}
	
	public ActionForward addSupervisePay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisePayForm spform = (SupervisePayForm)form;
		SupervisePayService service = new SupervisePayService();
		
		//执行新增操作并获取操作结果
		boolean flag = service.addSupervisePay(spform.getSupervisePay());
		
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//新增成功时返回列表页面
			return supervisePayList(mapping, form, request, response);
		}
		
		//新增失败时返回新增页面
		return mapping.findForward("add_supervise_pay");
	}
	
	public ActionForward updSupervisePayEntry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisePayForm spform = (SupervisePayForm)form;
		SupervisePayService service = new SupervisePayService();
		
		//根据id获取修改对象
		SupervisePayVO vo = service.loadSupervisePayById(spform.getSupervisePay().getId());
		
		//对象不存在时返回列表页
		if(vo==null){
			request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), "修改数据不存在");
			return supervisePayList(mapping, form, request, response);
		}
		
		spform.setSupervisePay(vo);
		setOptions(request);
		//返回修改页面
		return mapping.findForward("upd_supervise_pay");
	}
	
	public ActionForward updSupervisePay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisePayForm spform = (SupervisePayForm)form;
		SupervisePayService service = new SupervisePayService();
		
		//执行修改操作并获取操作结果
		boolean flag = service.updSupervisePay(spform.getSupervisePay());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		if(flag){
			//操作成功时返回列表页面
			return supervisePayList(mapping, form, request, response);
		}
		
		//操作失败时返回修改 页面
		return mapping.findForward("upd_supervise_pay");
	}
	
	public ActionForward delSupervisePay(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		SupervisePayForm spform = (SupervisePayForm)form;
		SupervisePayService service = new SupervisePayService();

		//执行删除操作并获取操作结果
		boolean flag = service.deleteSupervisePay(spform.getSupervisePay().getId());
		//将执行结果及消息设置到request为页面处理使用
		request.setAttribute(Constants.OPERATE_FLAG.getCode(), flag);
		request.setAttribute(Constants.OPERATE_MESSAGE.getCode(), service.getExecuteMessage());
		
		//返回列表页面
		return supervisePayList(mapping, form, request, response);
	}
	
	public void setOptions(HttpServletRequest request){
		request.setAttribute("yesornoOptions", OptionUtil.yesorno());
		request.setAttribute("dealersOptions", OptionUtil.getDealers());
	}
}
